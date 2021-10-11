package org.w3c.css.util;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class UnescapeFilterReader extends FilterReader {

    public UnescapeFilterReader(Reader r) {
        super(r);
    }

    @Override
    public int read()
            throws IOException {
        int esc;
        int c = in.read();
        // https://www.w3.org/TR/css-syntax-3/#input-preprocessing
        if (c == 13) { // U+000D CARRIAGE RETURN (CR)
            mark(1);
            c = in.read();
            // eat any LF
            if (c != 10) { // U+000A LINE FEED (LF)
                reset();
            }
            return 10; // U+000A LINE FEED (LF)
        }
        if (c == 12) { //U+000C FORM FEED (FF)
            return 10;// U+000A LINE FEED (LF)
        }
        if (c == 0) { // U+0000 NULL
            return 65533; // U+FFFD REPLACEMENT CHARACTER
        }

        // now specific case of CSS unicode escape for ascii values [A-Za-z0-9].
        if (c != '\\') {
            return c;
        }
        mark(6);
        int val = 0;
        for (int i = 0; i < 6; i++) {
            esc = in.read();
            // 0-9
            if (esc > 47 && esc < 58) {
                val = (val << 4) + (esc - 48);
            } else if (esc > 64 && esc < 71) {
                // A_F
                val = (val << 4) + (esc - 55);
            } else if (esc > 96 && esc < 103) {
                val = (val << 4) + (esc - 87);
            } else if (esc == 10 || esc == 9 || esc == 32) { // CSS whitespace.
                // U+000A LINE FEED, U+0009 CHARACTER TABULATION, or U+0020 SPACE.
                if ((val > 96 && val < 124) || (val > 64 && val < 91) || (val > 47 && val < 58)) {
                    return val;
                }
            } else {
                if ((val > 96 && val < 124) || (val > 64 && val < 91) || (val > 47 && val < 58)) {
                    //we must unread 1
                    reset();
                    i++;
                    for (int j = 0; j < i; j++) {
                        in.read();
                    }
                    return val;
                }
                reset();
                return c;
            }
        }
        // we read up to 6 char test value first
        if ((val <= 96 || val >= 124) && (val <= 64 || val >= 91) && (val <= 47 || val >= 58)) {
            reset();
            return c;
        }
        mark(1);
        c = in.read();
        // not a CSS WHITESPACE
        if (c != 10 && c != 9 && c != 32) {
            reset();
        }
        return val;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int i, j, k, l, cki;
        char[] chars = new char[len];
        in.mark(len);
        l = super.read(chars, 0, len);
        if (l <= 0) {
            return l;
        }
        for (i = 0, j = 0; i < l; i++) {
            // pre-processing
            if (chars[i] == 13) {
                chars[j++] = 10;
                // test for CRLF
                if (i + 1 < l && chars[i + 1] == 10) {
                    i++;
                }
            } else if (chars[i] == 12) {
                chars[j++] = 10;
            } else if (chars[i] == 0) {
                chars[j++] = 65533;
            }
            // escaping

            if (chars[i] == '\\') {
                int val = 0;
                boolean escaped = false;
                for (k = 1; k < 7 && k + i < l; k++) {
                    cki = chars[k + i];
                    // 0-9
                    if (cki > 47 && cki < 58) {
                        val = (val << 4) + (cki - 48);
                    } else if (cki > 64 && cki < 71) {
                        // A_F
                        val = (val << 4) + (cki - 55);
                    } else if (cki > 96 && cki < 103) {
                        val = (val << 4) + (cki - 87);
                    } else if (cki == 10 || cki == 9 || cki == 32) { // CSS whitespace.
                        // U+000A LINE FEED, U+0009 CHARACTER TABULATION, or U+0020 SPACE.
                        if ((val > 96 && val < 124) || (val > 64 && val < 91) || (val > 47 && val < 58)) {
                            chars[j++] = (char) val;
                            escaped = true;
                            i += k;
                            break;
                        }
                    } else {
                        if (val == 0) {
                            if ((cki > 96 && cki < 124) || (cki > 64 && cki < 91)) {
                                // so we found a regular char, just remove the escaping
                                break;
                            }
                        }
                        if ((val > 96 && val < 124) || (val > 64 && val < 91) || (val > 47 && val < 58)) {
                            chars[j++] = (char) val;
                            escaped = true;
                            i += k - 1;
                            break;
                        }
                    }
                }
                if (k == 7 && !escaped) {
                    if ((val > 96 && val < 124) || (val > 64 && val < 91) || (val > 47 && val < 58)) {
                        chars[j++] = (char) val;
                        escaped = true;
                        i += k - 1;
                        if (i + 1 < l) {
                            cki = chars[i + 1];
                            // skip extra space
                            if (cki == 10 || cki == 9 || cki == 32) {
                                i++;
                            }
                        }
                    } else {
                        // do nothing
                        chars[j++] = chars[i];
                    }
                } else {
                    // we reached the end, unescaping didn't happen let's stop here
                    // unless we are the last
                    if (!escaped) {
                        if (j != 0) {
                            in.reset();
                            in.skip(i);
                            break;
                        } else {
                            chars[j++] = chars[i];
                        }
                    }
                }
            } else {
                chars[j++] = chars[i];
            }
        }

        System.arraycopy(chars, 0, cbuf, off, j);
        return j;
    }
}

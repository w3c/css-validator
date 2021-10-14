//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * Be Careful this version is not the original version.
 * I modified some sources.          Philippe Le Hegaret
 *
 * @(#)Codecs.java					0.2-2 23/03/1997
 *
 *  This file is part of the HTTPClient package
 *  Copyright (C) 1996,1997  Ronald Tschalaer
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Library General Public
 *  License as published by the Free Software Foundation; either
 *  version 2 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Library General Public License for more details.
 *
 *  You should have received a copy of the GNU Library General Public
 *  License along with this library; if not, write to the Free
 *  Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,
 *  MA 02111-1307, USA
 *
 *  For questions, suggestions, bug-reports, enhancement-requests etc.
 *  I may be contacted at:
 *
 *  ronald@innovation.ch
 *  Ronald.Tschalaer@psi.ch
 *
 */

package org.w3c.css.util;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * This class collects various encoders and decoders.
 *
 * @author Ronald Tschal&auml;r
 * @version 0.2 (bug fix 2)  23/03/1997
 */

public class Codecs {

    /**
     * I have problems with multipart/form-data so I integrated auto-debug here
     */
    private static boolean debugMode = false;

    // Constructors

    /**
     * This class isn't meant to be instantiated.
     */
    private Codecs() {
    }


    // Methods

    /**
     * This method decodes a multipart/form-data encoded string. The boundary
     * is parsed from the <var>cont_type</var> parameter, which must be of the
     * form 'multipart/form-data; boundary=...'.
     * <BR>Any encoded files are created in the directory specified by
     * <var>dir</var> using the encoded filename.
     * <BR>Note: Does not handle nested encodings (yet).
     * <BR>Example:
     * <PRE>
     * NVPair[] opts = Codecs.mpFormDataDecode(resp.getData(),
     * resp.getHeader("Content-length"),
     * ".");
     * </PRE>
     * Assuming the data received looked something like:
     * <PRE>
     * -----------------------------114975832116442893661388290519
     * Content-Disposition: form-data; name="option"
     * &nbsp;
     * doit
     * -----------------------------114975832116442893661388290519
     * Content-Disposition: form-data; name="comment"; filename="comment.txt"
     * &nbsp;
     * Gnus and Gnats are not Gnomes.
     * -----------------------------114975832116442893661388290519--
     * </PRE>
     * you would get one file called <VAR>comment.txt</VAR> in the current
     * directory, and opts would contain two elements: {"option", "doit"}
     * and {"comment", "comment.txt"}
     *
     * @param data      the form-data to decode.
     * @param cont_type the content type header (must contain the
     *                  boundary string).
     * @return an array of name/value pairs, one for each part;
     * the name is the 'name' attribute given in the
     * Content-Disposition header; the value is either
     * the name of the file if a filename attribute was
     * found, or the contents of the part.
     * @throws IOException If any file operation fails.
     */
    public final static synchronized ArrayList<Pair<String, ?>> mpFormDataDecode(byte[] data,
                                                                                 String cont_type)
            throws IOException {

        ArrayList<Pair<String, ?>> pList = new ArrayList<>();

        // Find and extract boundary string
        String bndstr = getParameter("boundary", cont_type);
        if (bndstr == null) {
            throw new IOException("\'boundary\' parameter " +
                    "not found in Content-type: " + cont_type);
        }

        byte[] srtbndry = new byte[bndstr.length() + 4],
                boundary = new byte[bndstr.length() + 6],
                endbndry = new byte[bndstr.length() + 6];

        srtbndry = ("--" + bndstr + "\n").getBytes(StandardCharsets.ISO_8859_1);
        boundary = ("\n--" + bndstr + "\n").getBytes(StandardCharsets.ISO_8859_1);
        endbndry = ("\n--" + bndstr + "--").getBytes(StandardCharsets.ISO_8859_1);

        if (debugMode) {
            System.err.println("[START OF DATA]");
            printData(data);
            System.err.println("[END OF DATA]");
            System.err.print("boundary : ");
            printData(srtbndry);
            System.err.println();
            printData(boundary);
            System.err.println();
            printData(endbndry);
            System.err.println();
        }


        // slurp

        // setup search routines

        int[] bs = Util.compile_search(srtbndry);
        int[] bc = Util.compile_search(boundary);
        int[] be = Util.compile_search(endbndry);


        // let's start parsing the actual data

        int start = Util.findStr(srtbndry, bs, data, 0, data.length);
        if (start == -1) {    // didn't even find the start
            if (!debugMode) {
                debugMode = true;
                mpFormDataDecode(data, cont_type);
                return null;
            } else {
                debugMode = false;
                throw new IOException("Starting boundary not found: " +
                        new String(srtbndry));
            }
        }

        start += srtbndry.length;

        boolean done = false;
        int idx;

        for (idx = 0; !done; idx++) {
            // find end of this part

            int end = Util.findStr(boundary, bc, data, start, data.length);
            if (end == -1) {        // must be the last part
                end = Util.findStr(endbndry, be, data, start, data.length);
                if (end == -1) {
                    /*		    if (!debugMode) {
                 debugMode = true;
                 mpFormDataDecode(data, cont_type);
                 return null;
                 } else {
                             debugMode = false;
                 System.err.println( "[Ending boundary not found in]" );
                 printData(data, start);
                 System.err.println( "[END DATA BOUNDARY SEARCH]");
                 throw new IOException("Ending boundary not found: " +
                               new String(endbndry));
                 */
                    end = data.length - 1;
                    while (end >= 0 && (data[end] == '\n' || data[end] == ' ')) {
                        end--;
                    }
                    end++;
                    /* } */
                }
                done = true;
            }

            // parse header(s)

            String hdr, lchdr, name = null, filename = null, cont_disp = null, mimeType = null;
            Object value;

            while (true) {
                int next = findEOL(data, start) + 1;

                if (next - 1 <= start) break;    // empty line -> end of headers
                hdr = new String(data, start, next - 1 - start);

                if (debugMode) {
                    System.err.println(" start = " + start +
                            " end = " + next);
                }

                // handle line continuation
                byte ch;
                while (next < data.length - 1 &&
                        ((ch = data[next]) == ' ' || ch == '\t')) {
                    next = findEOL(data, start) + 1;
                    String result = new String(data, start, next - 1 - start);
                    hdr += result;
                    start = next;
                }
                start = next;

                if (debugMode) {
                    System.err.println("hdr=" + hdr);
                    System.err.println("(New) start = " + start +
                            " end = " + next);
                }

                lchdr = hdr.toLowerCase();

                if (lchdr.startsWith("content-type")) {
                    mimeType = lchdr.substring("content-type: ".length());
                    continue;
                } else if (!lchdr.startsWith("content-disposition")) continue;

                int off = lchdr.indexOf("form-data", 20);

                if (off == -1) {
                    if (!debugMode) {
                        debugMode = true;
                        mpFormDataDecode(data, cont_type);
                        return null;
                    } else {
                        debugMode = false;
                        throw new IOException("Expected \'Content-Disposition: form-data\' in line: " + hdr);
                    }
                }

                name = getParameter("name", hdr);

                if (debugMode) {
                    System.err.println("[ADD name is " + name + ']');
                }

                if (name == null) {
                    if (!debugMode) {
                        debugMode = true;
                        mpFormDataDecode(data, cont_type);
                        return null;
                    } else {
                        debugMode = false;
                        throw new IOException("\'name\' parameter not found in header: " + hdr);
                    }
                }

                filename = getParameter("filename", hdr);
                if (debugMode) {
                    System.err.println("[ADD filename is " + filename + ']');
                }
                cont_disp = hdr;
            }

            start += 1;

            if (debugMode) {
                System.err.println("(End) start = " + start +
                        " end = " + end);
            }

            if (start > end) {
                if (!debugMode) {
                    debugMode = true;
                    mpFormDataDecode(data, cont_type);
                    return null;
                } else {
                    debugMode = false;
                    throw new IOException("End of header not found at offset " + end);
                }
            }

            if (cont_disp == null) {
                if (!debugMode) {
                    debugMode = true;
                    mpFormDataDecode(data, cont_type);
                    return null;
                } else {
                    debugMode = false;
                    throw new IOException("Missing \'Content-Disposition\' header at offset " + start);
                }
            }

            // handle data for this part

            if (filename != null) {            // It's a file
                FakeFile file = new FakeFile(filename);

                file.write(data, start, end - start);
                file.setContentType(mimeType);

                value = file;
            } else {                    // It's simple data
                if (name.equals("text")) {
                    try {
                        CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
                        decoder.decode(ByteBuffer.wrap(data, start, end - start));
                        value = new String(data, start, end - start, StandardCharsets.UTF_8);
                        pList.add(new ImmutablePair<>("textcharset", StandardCharsets.UTF_8));
                    } catch (CharacterCodingException ignoredEx) {
                        value = new String(data, start, end - start, StandardCharsets.ISO_8859_1);
                        pList.add(new ImmutablePair<>("textcharset", StandardCharsets.ISO_8859_1));
                    }
                } else {
                    value = new String(data, start, end - start, StandardCharsets.ISO_8859_1);
                }
                //               value = new String(data, start, end - start);
            }
            // reserved name for fake text charset
            if (!name.equals("textcharset")) {
                pList.add(new ImmutablePair<>(name, value));
            }
            if (debugMode) {
                System.err.println("[ADD " + name + ',' + value + ','
                        + value.getClass() + ']');
            }
            start = end + boundary.length;
        }
        return pList;
    }


    /**
     * retrieves the value associated with the parameter <var>param</var> in
     * a given header string. This is used especially in headers like
     * 'Content-type' and 'Content-Disposition'. Here is the syntax it
     * expects:<BR>
     * ";" <var>param</var> "=" ( token | quoted-string )
     *
     * @param param the parameter name
     * @param hdr   the header value
     * @return the value for this parameter, or null if not found.
     */
    public final static String getParameter(String param, String hdr) {
        int pbeg,    // parameter name begin
                pend,    // parameter name end
                vbeg,    // parameter value begin
                vend = -1,    // parameter value end
                len = hdr.length();

        param = param.trim();

        while (true) {
            // mark parameter name
            if (debugMode) {
                System.err.println("[DEBUG] looks for " + param + " in ");
                System.err.println("[" + hdr.substring(vend + 1) + ']');
            }
            if (vend == -1) {
                pbeg = hdr.indexOf(';', vend + 1);    // get ';'
                if (pbeg == -1) return null;
            } else {
                pbeg = vend + 1;
            }
            while (pbeg < len - 1
                    && (Character.isWhitespace(hdr.charAt(pbeg)) || (hdr.charAt(pbeg) == ';'))) {
                pbeg++;
            }
            if (pbeg == len - 1) return null;
            pend = hdr.indexOf('=', pbeg + 1);    // get '='
            if (pend == -1) return null;
            vbeg = pend + 1;
            while (Character.isWhitespace(hdr.charAt(--pend))) ;
            pend++;

            if (debugMode) {
                System.err.println("[DEBUG] p is [" + hdr.substring(pbeg, pend) + "]");
            }
            // mark parameter value

            while (vbeg < len && Character.isWhitespace(hdr.charAt(vbeg))) vbeg++;
            if (vbeg == len) return null;

            vend = vbeg;
            if (hdr.charAt(vbeg) == '\"') {        // is a quoted-string
                vbeg++;
                vend = hdr.indexOf('\"', vbeg);
                if (vend == -1) return null;
            } else {                    // is a simple token
                vend = hdr.indexOf(';', vbeg);
                if (vend == -1) vend = hdr.length();
                while (Character.isWhitespace(hdr.charAt(--vend))) ;
                vend++;
            }
            if (hdr.regionMatches(true, pbeg, param, 0, pend - pbeg)) {
                break;                    // found it
            }
        }
        return hdr.substring(vbeg, vend);
    }


    private static void printData(byte[] data) {
        printData(data, 0);
    }

    private static void printData(byte[] data, int offset) {
        for (int i = offset; i < data.length; i++) {
            if (data[i] == '\n' || data[i] == '\r') {
                System.err.print("&" + ((int) data[i]));
                System.err.println();
            } else {
                System.err.print((char) data[i]);
            }
        }
    }

    /**
     * Searches for the next LF in an array.
     *
     * @param arr the byte array to search.
     * @param off the offset at which to start the search.
     * @return the position of the CR or (arr.length-2) if not found
     */
    private final static int findEOL(byte[] arr, int off) {
        while (off < arr.length - 1 &&
                !(arr[off++] == '\n')) ;
        return off - 1;
    }
}


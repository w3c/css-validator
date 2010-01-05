//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * AtRuleMedia.java
 * $Id$
 */
package org.w3c.css.parser;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import java.util.Enumeration;

/**
 * This class manages all media defines by CSS2
 *
 * @author Philippe Le Hegaret
 * @version $Revision$
 */
public class AtRuleMediaCSS2 extends AtRuleMedia {

    static final String[] mediaCSS2 = {
            "all", "aural", "braille", "embossed", "handheld", "print", "projection",
            "screen", "tty", "tv", "presentation"
    };

    String[] media = new String[mediaCSS2.length];

    boolean empty = true;

    /**
     * Adds a medium.
     *
     * @throws InvalidParamException the medium doesn't exist
     */
    public AtRuleMedia addMedia(String medium,
                                ApplContext ac) throws InvalidParamException {

        //This medium didn't exist for CSS2
        //	if ((!cssversion.equals("css3")) && medium.equals("presentation")) {
        // throw new InvalidParamException("media", medium, ac);
        //}

        for (int i = 0; i < mediaCSS2.length; i++) {
            if (medium.equals(mediaCSS2[i])) {
                media[i] = mediaCSS2[i];
                empty = false;
                return this;
            }
        }

        throw new InvalidParamException("media", medium, ac);
    }

    /**
     * Returns the at rule keyword
     */
    public String keyword() {
        return "media";
    }

    public boolean isEmpty() {
        return empty;
    }

    /**
     * The second must be exactly the same of this one
     */
    public boolean canApply(AtRule atRule) {
        if (atRule instanceof AtRuleMedia) {
            AtRuleMedia second = (AtRuleMedia) atRule;

            for (int i = 0; i < media.length; i++) {
                // strings are exactly the same so I don't have to use equals
                if (media[i] != second.media[i]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * The second must only match this one
     */
    public boolean canMatched(AtRule atRule) {
        if (atRule instanceof AtRuleMedia) {
            AtRuleMedia second = (AtRuleMedia) atRule;

            for (int i = 0; i < media.length; i++) {
                // strings are exactly the same so I don't have to use equals
                if (media[i] == second.media[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public Enumeration elements() {
        return new MediaEnumeration(this);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("@");
        boolean first = true;
        sb.append(keyword());
        for (int i = 0; i < media.length; i++) {
            if (media[i] != null) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }
                sb.append(media[i]);
            }
        }
        return sb.toString();
    }


}


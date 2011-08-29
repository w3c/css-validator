//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * AtRulePage.java
 * $Id$
 */
package org.w3c.css.parser;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;


/**
 * This class manages all media defines by CSS2
 *
 * @author Philippe Le H�garet
 * @version $Revision$
 */
public class AtRulePage extends AtRule {

    static final String[] pseudo = {
            ":left", ":right", ":first"
    };

    String name;

    String ident;

    /**
     * Returns the at rule keyword
     */
    public String keyword() {
        return "page";
    }

    /**
     * Sets the name of the page
     * name will be a pseudo name :first, :left, :right
     * or a random name without semi-colon at the beginning
     */
    public AtRulePage setName(String name, ApplContext ac)
            throws InvalidParamException {
        if (name.charAt(0) == ':') {
            for (int i = 0; i < pseudo.length; i++) {
                if (name.equals(pseudo[i])) {
                    this.name = pseudo[i];
                    return this;
                }
            }
            throw new InvalidParamException("page", name, ac);
        } else {
            this.name = name;
        }

        return this;
    }

    public AtRulePage setIdent(String ident) {
        this.ident = ident;
        return this;
    }

    public String getIdent() {
        return ident;
    }

    /**
     * Return true if atRule is exactly the same as current
     */
    public boolean equals(Object atRule) {
        AtRulePage other;
        try {
            other = (AtRulePage) atRule;
        } catch (ClassCastException cce) {
            // not an AtRulePage, fail
            return false;
        }
        if ((name != null) && (other.name != null)) {
            if (!name.equals(other.name)) {
                return false;
            }
        } else {
            if ((name != null) || (other.name != null)) {
                return false;
            }
        }
        if ((ident != null) && (other.ident != null)) {
            return ident.equals(((AtRulePage) atRule).ident);
        } else {
            if ((ident != null) || (other.ident != null)) {
                return false;
            }
        }
        return true;
    }

    /**
     * The second must be exactly the same of this one
     */
    public boolean canApply(AtRule atRule) {
        AtRulePage other;
        try {
            other = (AtRulePage) atRule;
        } catch (ClassCastException cce) {
            // not an AtRulePage, fail
            return false;
        }
        if ((name != null) && (other.name != null)) {
            if (!name.equals(other.name)) {
                return false;
            }
        } else {
            if ((name != null) || (other.name != null)) {
                return false;
            }
        }
        if ((ident != null) && (other.ident != null)) {
            return ident.equals(((AtRulePage) atRule).ident);
        } else {
            if ((ident != null) || (other.ident != null)) {
                return false;
            }
        }
        return true;
    }

    /**
     * The second must only match this one
     */
    public boolean canMatched(AtRule atRule) {
        AtRulePage atRulePage;
        try {
            atRulePage = (AtRulePage) atRule;
        } catch (ClassCastException cce) {
            // not an AtRulePage, fail
            return false;
        }
        if ((name != null) && !name.equals(atRulePage.name)) {
            return false;
        }
        if ((ident != null) && !ident.equals(atRulePage.ident)) {
            return false;
        }
        return true;
    }


    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append('@').append(keyword());
        if (ident != null) {
            ret.append(' ').append(ident);
            if (name != null) {
                ret.append(name);
            }
        } else if (name != null) {
            ret.append(' ').append(name);
        }
        return ret.toString();
    }

}

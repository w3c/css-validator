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

import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;


/**
 * This class manages all media defines by CSS2
 * 
 * @version $Revision$
 * @author  Philippe Le Hégaret
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
	if (atRule instanceof AtRulePage) {
	    AtRulePage other = (AtRulePage) atRule;
	    boolean res = true;
	    if ((name != null) && (other.name != null)) {
		res = res &&  name.equals(other.name);
	    } else {
		if ((name != null) || (other.name != null)) {
		    return false;
		}
	    }
	    if ((ident != null) && (other.ident != null)) {
		res = res &&  ident.equals(((AtRulePage) atRule).ident);
	    } else {
		if ((ident != null) || (other.ident != null)) {
		    return false;
		}
	    }
	    return res;
	} else {
	    return false;
	}
    }

    /**
     * The second must be exactly the same of this one
     */    
    public boolean canApply(AtRule atRule) {
	if (atRule instanceof AtRulePage) {
	    AtRulePage other = (AtRulePage) atRule;
	    boolean res = true;
	    if ((name != null) && (other.name != null)) {
		res = res &&  name.equals(other.name);
	    } else {
		if ((name != null) || (other.name != null)) {
		    return false;
		}
	    }
	    if ((ident != null) && (other.ident != null)) {
		res = res &&  ident.equals(((AtRulePage) atRule).ident);
	    } else {
		if ((ident != null) || (other.ident != null)) {
		    return false;
		}
	    }
	    return res;
	} else {
	    return false;
	}
    }

    /**
     * The second must only match this one
     */    
    public boolean canMatched(AtRule atRule) {
	if (atRule instanceof AtRulePage) {
	    boolean res = true;
	    if (name != null) {
		res = res &&  name.equals(((AtRulePage) atRule).name);
	    }
	    if (ident != null) {
		res = res &&  ident.equals(((AtRulePage) atRule).ident);
	    }
	    return res;
	} else {
	    return false;
	}
    }


    public String getName() {
	return name;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	String ret = "@" + keyword() ;
	if (ident!=null) {
	    ret += " " + ident;
	    if (name!=null) {
		ret += name;
	    }
	} else if (name != null) {
	    ret += " " + name;
	}
	return ret;
    }
    
}

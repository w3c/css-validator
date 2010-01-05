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

import java.util.Enumeration;
import java.util.Vector;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

/**
 * This class manages all media defines by CSS2
 *
 * @version $Revision$
 * @author  Philippe Le Hegaret
 */
public class AtRuleMedia extends AtRule {

    static final String[] mediaCSS3 = {
	"all", "aural", "braille", "embossed", "handheld", "print",
	"projection", "screen", "tty", "tv", "presentation", "atsc-tv"
    };

    String restrictor = new String();
    String[] media = new String[mediaCSS3.length];
    // media list coming from the stylesheet (ie. with case)
    String[] originalMedia = new String[mediaCSS3.length];
    Vector<String> mediafeatures = new Vector<String>();

    boolean empty = true;

    /**
     * Adds a medium.
     *
     * @exception InvalidParamException the medium doesn't exist
     */
    public AtRuleMedia addMedia(String medium,
				ApplContext ac) throws InvalidParamException {

	//This medium didn't exist for CSS2
	// if ((!cssversion.equals("css3")) && medium.equals("presentation")) {
	// throw new InvalidParamException("media", medium, ac);
	//}

	for (int i = 0; i < mediaCSS3.length; i++) {
	    if (medium.toLowerCase().equals(mediaCSS3[i])) {
		media[i] = mediaCSS3[i];
                originalMedia[i] = medium;
		empty = false;
		return this;
	    }
	}

	throw new InvalidParamException("media", medium, ac);
    }

    public void addMediaRestrictor(String restrictor, ApplContext ac) {
	if (restrictor.toUpperCase().equals("ONLY") ||
	    restrictor.toUpperCase().equals("NOT")) {
	    this.restrictor = restrictor;
	}
    }

    public void addMediaFeature(CssProperty prop) {
	if (prop != null) {
	    StringBuilder expression = new StringBuilder(prop.getPropertyName());
	    String propval = prop.toString();
	    if (propval != null) {
		expression.append(" : ").append(propval);
	    }
	    mediafeatures.addElement(expression.toString());
	}
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
	    return false;
	} else {
	    return false;
	}
    }

    public Enumeration elements() {
	return new MediaEnumeration(this);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	StringBuilder ret  = new StringBuilder();

	ret.append('@');
	ret.append(keyword());
	ret.append(' ');
	if (!restrictor.equals("")) {
	    ret.append(restrictor);
	    ret.append(' ');
	}
	boolean f = true;
	for (int i = 0; i < media.length; i++) {
	    if (originalMedia[i] != null) {
		if (!f) {
		    ret.append(',');
		    ret.append(' ');
		} else {
		    f = false;
		}
		ret.append(originalMedia[i]);
	    }
	}

	for (int i = 0; i < mediafeatures.size(); i++) {
	    ret.append(" and (");
	    ret.append(mediafeatures.elementAt(i));
	    ret.append(')');
	}
	return ret.toString();
    }

    public String getValueString() {
	StringBuilder ret  = new StringBuilder();
	if (!restrictor.equals("")) {
	    ret.append(restrictor);
	    ret.append(' ');
	}
	boolean f = true;
	for (int i = 0; i < media.length; i++) {
	    if (originalMedia[i] != null) {
		if (!f) {
		    ret.append(',');
		    ret.append(' ');
		} else {
		    f = false;
		}
		ret.append(originalMedia[i]);
	    }
	}

	for (int i = 0; i < mediafeatures.size(); i++) {
	    ret.append(" and (");
	    ret.append(mediafeatures.elementAt(i));
	    ret.append(')');
	}
	return ret.toString();
    }
}


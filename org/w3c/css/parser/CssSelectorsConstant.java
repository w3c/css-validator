//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 20th 2000 Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 */
package org.w3c.css.parser;

/**
 * @version $Revision$
 */
public interface CssSelectorsConstant {

    public static final String[] PSEUDOCLASS_CONSTANTS =
    { "first-child", 
      "link", "visited", 
      "target", 
      "hover", "active", "focus", "enabled", "disabled",
      "checked", "indeterminate", "root", "last-child", 
      "first-of-type", "last-of-type", "only-of-type",
      "only-child", "empty" };

    // lang is special (and not available for mobile profile)

    public static final String[] PSEUDOELEMENT_CONSTANTS =
    { "first-line", "first-letter", "before", "after", 
      "selection", "menu", "subject"
    };

    public static final String[] PSEUDOCLASS_CONSTANTSCSS2 =
    { "first-child", 
      "link", "visited", 
      "target", 
      "hover", "active", "focus"
    };

    public static final String[] PSEUDOCLASS_CONSTANTSCSS1 =
    { 
      "link", "visited", 
      "target", "active"
    };

    // lang is special, and contains and nth-element and nth-type-of

    public static final String[] PSEUDOELEMENT_CONSTANTSCSS2 =
    { "first-line", "first-letter",
      "before", "after"  
    };

    public static final String[] PSEUDOELEMENT_CONSTANTSCSS1 = 
    {
	"first-line", "first-letter"
    };

    /** [lang="fr"] */
    public static final int ATTRIBUTE_EXACT = ' ';
    /** [lang~="fr"] */
    public static final int ATTRIBUTE_ONE_OF = '~';
    /** [lang|="fr"] */
    public static final int ATTRIBUTE_BEGIN = '|';
    /** [lang] */
    public static final int ATTRIBUTE_ANY = -1;
    /** [foo^="bar"] */
    public static final int ATTRIBUTE_START = '^';
    /** [foo$="bar"] */
    public static final int ATTRIBUTE_SUFFIX = '$';
    /** [foo*="bar"] */
    public static final int ATTRIBUTE_SUBSTR = '*';

    /** Maximun of ATTRIBUTE_ONE_OF */
    public static final int ATTRIBUTE_LENGTH = 10;

    /** descendant connector  */    
    public static final char DESCENDANT = ' ';
    /** child connector */
    public static final char CHILD = '>';
    /** adjacent connector */
    public static final char ADJACENT = '+';
}  

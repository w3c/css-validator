//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 20th 2000 Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.6  2002/12/20 16:02:31  sijtsche
 * new Basic User Interface pseudo classes and elements
 *
 * Revision 1.5  2002/08/21 09:00:28  sijtsche
 * pseudo element ::marker added
 *
 * Revision 1.4  2002/08/19 07:24:39  sijtsche
 * tv profile restrictions added
 *
 * Revision 1.3  2002/07/22 14:19:06  sijtsche
 * new elements and classes added and order of constants changed because of representation errors in output
 *
 * Revision 1.2  2002/05/23 09:03:47  dejong
 * new elements and classes added and order of constants changed due to representation errors in output
 *
 */
package org.w3c.css.parser;

/**
 * @version $Revision$
 */
public interface CssSelectorsConstant {

    public static final String[] PSEUDOCLASS_CONSTANTS =
    { "link", "visited", "active", "focus", "target",
      "hover", "first-child",
      "enabled", "disabled",
      "checked", "indeterminate", "root", "last-child",
      "first-of-type", "last-of-type", "only-of-type",
      "only-child", "empty", "valid", "invalid", "required",
      "optional", "read-only", "read-write",
      "default", "in-range", "out-of-range"
      };

    public static final String[] PSEUDOCLASS_CONSTANTSCSS2 =
    {
      "link", "visited", "active", "target", "focus",
      "hover", "first-child"
    };

    public static final String[] PSEUDOCLASS_CONSTANTSTV =
    {
	  "link", "visited", "active", "focus", "first-child"
	};

    public static final String[] PSEUDOCLASS_CONSTANTSCSS1 =
    {
      "link", "visited", "active", "target"
    };

	public static final String[] PSEUDOCLASS_CONSTANTS_MOBILE =
	{
	   "link", "visited", "active", "focus"
	};

    // lang is special (and not available for mobile profile)

    public static final String[] PSEUDOELEMENT_CONSTANTS =
    { "first-line", "first-letter", "before", "after",
      "selection", "marker", "value", "choices", "repeat-item", "repeat-index"
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
    /** class selector == like [lang~="fr"] */
    public static final int ATTRIBUTE_CLASS_SEL = '.';
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

//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.6  2002/12/24 12:32:47  sijtsche
 * new values added
 *
 * Revision 1.5  2002/12/20 16:03:36  sijtsche
 * new values added
 *
 * Revision 1.4  2002/08/21 09:01:24  sijtsche
 * new values added for list-style-type
 *
 * Revision 1.3  2002/08/19 07:40:17  sijtsche
 * new values added
 *
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 1.2  1997/07/30 13:20:06  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/25 15:20:46  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

/**
 * @version $Revision$
 */
public interface CssListStyleConstants {
    
    public static String[] LISTSTYLETYPE = {
	"box", "check", "circle", "diamond", "disc", "hyphen", "square",
	"armenian", "cjk-ideographic", "ethiopic-numeric", "georgian", "hebrew",
	"japanese-formal", "japanese-informal", "lower-armenian", "lower-roman",
	"simp-chinese-formal", "simp-chinese-informal", "syriac", "tamil", 
	"trad-chinese-formal", "trad-chinese-informal", "upper-armenian",
	"upper-roman", "arabic-indic", "binary", "bengali", "cambodian", 
	"decimal", "decimal-leading-zero", "devanagari", "gujarati", "gurmukhi",
	"kannada", "khmer", "lao", "lower-hexadecimal", "malayalam",
	"mongolian", "myanmar", "octal", "oriya", "persian", "telugu", 
	"tibetan", "thai", "upper-hexadecimal", "urdu",	"afar", "amharic", 
	"amharic-abegede", "cjk-earthly-branch", "cjk-heavenly-stem", "ethiopic",
	"ethiopic-abegede", "ethiopic-abegede-am-et", "ethiopic-abegede-gez",
	"ethiopic-abegede-ti-er", "ethiopic-abegede-ti-et", 
	"ethiopic-halehame-aa-er", "ethiopic-halehame-aa-et", 
	"ethiopic-halehame-am-et", "ethiopic-halehame-gez", 
	"ethiopic-halehame-om-et", "ethiopic-halehame-sid-et",
	"ethiopic-halehame-so-et", "ethiopic-halehame-ti-er", 
	"ethiopic-halehame-ti-et", "ethiopic-halehame-tig", "hangul", 
	"hangul-consonant", "hiragana", "hiragana-iroha", "katakana", 
	"katakana-iroha", "lower-alpha", "lower-greek", "lower-norwegian", 
	"lower-latin", "oromo", "sidama", "somali", "tigre", "tigrinya-er",
	"tigrinya-er-abegede", "tigrinya-et", "tigrinya-et-abegede", 
	"upper-alpha", "upper-greek", "upper-norwegian", "upper-latin", 
	"asterisks", "footnotes", "circled-decimal", "circled-lower-latin", 
	"circled-upper-latin", "dotted-decimal", "double-circled-decimal",
	"filled-circled-decimal", "parenthesised-decimal", 
	"parenthesised-lower-latin", "normal", "none", "inherit"
    };

    public static String[] LISTSTYLETYPECSS1 = {
	"disc", "circle", "square", "decimal",
	"lower-roman", "upper-roman",
	"lower-alpha", "upper-alpha",
	"none"
    };

    public static String[] LISTSTYLETYPETV = {
	"disc", "circle", "square", "decimal",
	"lower-roman", "upper-roman",
	"lower-alpha", "upper-alpha",
	"none", "lower-latin", "upper-latin", "inherit"
	};

    /* inherit is not accepted by CSS1, but is filtered out automatically
     by the parser, now LISTSTYLETYPECSS1 can be used by the mobile profile
     which does accept the same values as the CSS1 property + inherit */

    //for CSS1 inherit is already corriged by the parser itself
  public static String[] LISTSTYLEPOSITION = {
      "outside", "inside", "inherit" };
}

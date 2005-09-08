//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

/**
 * @version $Revision$
 */
public interface CssFontConstantCSS2 {

  /**
   * Array of font-style values
   */  
  static String[] FONTSTYLE = { "normal", "italic", "oblique", "inherit" };

  /**
   * Array of font-variant values
   */  
  static String[] FONTVARIANT = { "normal", "small-caps", "inherit" };

  /**
   * Array of font-size values
   */  
  static String[] FONTSIZE = { 
    "xx-small", "x-small", "small", "medium", "large", "x-large", "xx-large",
    "larger", "smaller", "inherit"
  }; // relative-size

  /**
   * Array of font-weight values
   */  
  static String[] FONTWEIGHT = { "normal", "bold", "bolder", 
				 "lighter", "inherit" };
  
  /**
   * Array of font-stretch values
   */  
  static String[] FONTSTRETCH = { "normal", "wider", "narrower", 
				  "ultra-condensed", "extra-condensed", 
				  "condensed", "semi-condensed", 
				  "semi-expanded", "expanded", "extra-expanded",
				  "ultra-expanded", "inherit" };
  
  /**
   * Array of font values
   */  
  static String[] FONT = { "caption", "icon", "menu", 
			   "message-box", "small-caption", 
			   "status-bar", "inherit" };

}

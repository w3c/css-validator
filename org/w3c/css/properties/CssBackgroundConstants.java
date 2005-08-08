//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:17:42  plehegar
 * New
 *
 * Revision 1.4  1997/07/30 13:19:45  plehegar
 * Updated package
 *
 * Revision 1.3  1997/07/23 21:21:57  plehegar
 * Added POSITION
 *
 * Revision 1.2  1997/07/23 14:50:12  plehegar
 * Added POSITION
 *
 * Revision 1.1  1997/07/22 17:50:56  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

/**
 * @version $Revision$
 */
public interface CssBackgroundConstants {
  static String[] REPEAT = { "repeat", "repeat-x", "repeat-y", "no-repeat", "inherit" };

  static String[] ATTACHMENT = { "scroll", "fixed", "inherit" };
    static String[] ATTACHMENTMOB = { "scroll", "inherit" };

  static String[] POSITION = { "top", "center", "bottom", "left", "right", "inherit" };

  /**
   * The top position
   */  
  static int POSITION_TOP = 0;

  /**
   * The center position
   */  
  static int POSITION_CENTER = 1;

  /**
   * The bottom position
   */  
  static int POSITION_BOTTOM = 2;

  /**
   * The left position
   */  
  static int POSITION_LEFT = 3;

  /**
   * The right position
   */  
  static int POSITION_RIGHT = 4;
  
  /**
   * Inherit
   */  
  static int POSITION_INHERIT = 5;
}

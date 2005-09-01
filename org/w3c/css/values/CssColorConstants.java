//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:19:46  plehegar
 * New
 *
 * Revision 1.3  1997/07/30 13:19:32  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/22 17:48:26  plehegar
 * Bug fix marron
 *
 * Revision 1.1  1997/07/21 22:07:31  plehegar
 * Initial revision
 *
 */
package org.w3c.css.values;

/**
 * This class is ued by CssColor
 *
 * @version $Revision$
 * @see org.w3c.css.values.CssColor
 */
public interface CssColorConstants {

  /**
   * All named colors.
   */  
  public static final String[] COLORNAME = {
    "aqua", "black", "blue", "fushia", "gray", "green", "lime", "maroon", 
    "navy", "olive", "purple", "red", "silver", "teal", "white", "yellow" };

  public static final String[] COLORNAME_CSS21 = {
      "aqua", "black", "blue", "fushia", "gray", "green", "lime", "maroon", 
      "navy", "olive", "orange", "purple", "red", "silver", "teal", "white",
      "yellow" };
  
  public static final String[] SYSTEMCOLORS = {
      "ActiveBorder", "ActiveCaption", "AppWorkspace", "Background", 
      "ButtonFace", "ButtonHighlight", "ButtonShadow", "ButtonText",
      "CaptionText", "GrayText", "Highlight", "HighlightText", "InactiveBorder",
      "InactiveCaption", "InactiveCaptionText", "InfoBackground", "InfoText",
      "Menu", "MenuText", "Scrollbar", "ThreeDDarkShadow", "ThreeDFace",
      "ThreeDHighlight", "ThreeDLightShadow", "ThreeDShadow", "Window",
      "WindowFrame", "WindowText"
  };
}/*
ActiveBorder
ActiveCaption
AppWorkspace
Background
ButtonFace
ButtonHighlight
ButtonShadow
ButtonText
CaptionText
GrayText
Highlight
HighlightText
InactiveBorder
InactiveCaption
InactiveCaptionText
InfoBackground
InfoText
Menu
MenuText
Scrollbar
ThreeDDarkShadow
ThreeDFace
ThreeDHighlight
ThreeDLightShadow
ThreeDShadow
Window
WindowFrame
WindowText*/
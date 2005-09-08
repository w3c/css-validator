//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
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
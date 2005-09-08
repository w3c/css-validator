//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

/**
 * @version $Revision$
 */
public interface CssTextPropertiesConstantsCSS3 {
  public static String[] TEXTDECORATION = {
    "none", "underline", "overline", "line-through", "blink", "inherit" };

  public static String[] TEXTDECORATIONMOB = {
    "underline", "inherit" };

  public static String[] VERTICALALIGN = {
    "baseline", "sub", "super", "top", "text-top", "middle",
    "bottom", "text-bottom", "inherit" };

  public static String[] VERTICALALIGNMOB = {
    "baseline", "sub", "super", "inherit" };

  public static String[] TEXTTRANSFORM = {
    "none", "capitalize", "uppercase", "lowercase", "inherit" };

  public static String[] TEXTALIGN = {
    "left", "right", "center", "justify", "inherit", "start", "end" };

}

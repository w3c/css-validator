//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 1.2  1997/07/30 13:20:21  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/24 01:39:42  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties3;

/**
 * @version $Revision$
 */
public interface CssTextPropertiesConstantsCSS3 {
  public static String[] TEXTDECORATION = {
    "underline", "overline", "line-through", "blink", "inherit", "none", "solid", "double", "dotted", "thick",
    "dashed", "dot-dash", "dot-dot-dash", "wave"};

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

//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.4  2002/08/20 08:42:10  sijtsche
 * new values added
 *
 * Revision 1.3  2002/08/19 07:40:17  sijtsche
 * new values added
 *
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
package org.w3c.css.properties;

/**
 * @version $Revision$
 */
public interface CssTextPropertiesConstants {
  public static String[] TEXTDECORATION = {
    "underline", "overline", "line-through", "blink", "inherit" };

  public static String[] TEXTDECORATIONTV = {
	"none", "underline", "overline", "line-through", "inherit" };

  public static String[] TEXTDECORATIONMOB = {
    "underline", "inherit" };

  public static String[] VERTICALALIGN = {
    "auto", "use-script", "baseline", "sub", "super", "top", "text-top",
    "central", "middle", "bottom", "text-bottom", "inherit", "initial" };

  public static String[] VERTICALALIGNMOB = {
    "baseline", "sub", "super", "inherit" };

  public static String[] VERTICALALIGNTV = {
    "baseline", "sub", "super", "top", "middle", "bottom", "inherit" };

  public static String[] TEXTTRANSFORM = {
    "none", "capitalize", "uppercase", "lowercase", "inherit" };

  public static String[] TEXTALIGN = {
    "left", "right", "center", "justify", "inherit", "start", "end" };

  public static String[] TEXTALIGNTV = {
	"left", "right", "center", "justify", "inherit" };

}

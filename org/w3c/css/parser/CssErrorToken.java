//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 2.2  1997/08/20 14:15:13  plehegar
 * Added context and property in CssErrorToken
 *
 * Revision 2.1  1997/08/08 15:50:52  plehegar
 * Freeze
 *
 * Revision 1.3  1997/07/30 13:20:33  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/21 14:58:57  plehegar
 * Updated
 *
 * Revision 1.1  1997/07/21 14:36:53  plehegar
 * Initial revision
 *
 */
package org.w3c.css.parser;

import java.util.Vector;

/**
 * @version $Revision$
 */
public class CssErrorToken extends CssError {

  /**
   * The list of context when the error appears
   */  
  Vector context;

  /**
   * the property name
   */  
  String property;

  /**
   * the string description of the error
   */  
  String errorString;

  /**
   * the expected text
   */  
  String[] expectedTokens;

  /**
   * the skipped text
   */  
  String skippedString;

  /**
   * Create a new CssErrorToken.
   *
   * @param lin      The line number
   * @param error    The string description of the error
   * @param expected The expected text
   */
  CssErrorToken(int lin, String error, String[] expected) {
    line = lin;
    errorString = error;
    expectedTokens = expected;
  }  

  /**
   * Get contexts
   */
  public Vector getContexts() {
    return context;
  }

  /**
   * Get the name of the property.
   */  
  public String getPropertyName() {
    return property;
  }

  /**
   * Get the string description of the error.
   */
  public String getErrorDescription() {
    return errorString;
  }

  /**
   * Get the expected text.
   */
  public String[] getExpected() {
    return expectedTokens;
  }

  /**
   * Get the skipped text.
   */  
  public String getSkippedString() {
    return skippedString;
  }
}

//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

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

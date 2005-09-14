//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.parser;

/**
 * This class represents an unknown error during the parse.
 *
 * @version $Revision$
 */
public class CssError {

  /**
   * The source file
   */
  String sourceFile;

  /**
   * The line number in the file
   */
  int line;

  /**
   * The unknown error
   */
  Exception error;

  /**
   * Create a new CssError
   */
  public CssError() {
  }

  /**
   * Create a new CssError
   *
   * @param sourceFile The source file
   * @param line       The error line number
   * @param error      The exception
   */
  public CssError(String sourceFile, int line, Exception error) {
    this.sourceFile = sourceFile;
    this.line = line;
    this.error = error;
  }

  /**
   * Create a new CssError
   *
   * @param error      The exception
   */
  public CssError(Exception error) {
    this.error = error;
  }

  /**
   * Get the source file
   */
  public String getSourceFile() {
    return sourceFile;
  }

  /**
   * get the line number
   */
  public int getLine() {
    return line;
  }

  /**
   * get the unknown error
   */
  public Exception getException() {
    return error;
  }
}

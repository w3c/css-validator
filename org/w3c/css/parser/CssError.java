//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 2.2  1997/08/11 13:01:41  plehegar
 * *** empty log message ***
 *
 * Revision 1.3  1997/07/30 13:20:31  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/21 14:59:06  plehegar
 * Updated
 *
 * Revision 1.1  1997/07/21 14:37:24  plehegar
 * Initial revision
 *
 */
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

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
 * Revision 2.2  1997/08/20 11:39:49  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:53:07  plehegar
 * Nothing
 *
 * Revision 1.3  1997/07/30 13:19:37  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/24 00:23:40  plehegar
 * Added equals()
 *
 * Revision 1.1  1997/07/18 20:28:51  plehegar
 * Initial revision
 *
 */
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

/**
 * @version $Revision$
 */
public abstract class CssValue {

    String cssversion;

  /**
   * Set the value of this value.
   *
   * @param s     the string representation of the value.
   * @param frame For errors and warnings reports.
   * @exception InvalidParamException The unit is incorrect
   */  
  public abstract void set(String s, ApplContext ac) 
      throws InvalidParamException;

  /**
   * Returns the internal value
   */  
  public abstract Object get();

  /**
   * Compares two values for equality.
   *
   * @param value The other value.
   */  
  public boolean equals(Object value) {
    return super.equals(value);
  }

    public void setCssVersion(String cssversion) {
	this.cssversion = cssversion;
    }
    
    public boolean isDefault() {
	return false;
    }

}

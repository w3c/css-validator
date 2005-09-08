//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;

/**
 * <H3> Frequencies</H3>
 *
 * <P>Frequency units are used with aural cascading style sheets.
 *
 * <p>There are two legal frequency units:
 *
 * <ul>
 * <li>Hz: Hertz 
 * <li>kHz: kilo Hertz
 * </ul>
 *
 * <P> For example, 200Hz is a bass sound, and 6kHz is a treble sound.
 *
 * @version $Revision$ 
 */
public class CssFrequency extends CssValue {

  /**
   * Create a new CssFrequency
   */
  public CssFrequency() {
    value = defaultValue;
  }  

  /**
   * Create a new CssFrequency with a float number.
   *
   * @param value the float number.
   */
  public CssFrequency(Float value) {
    this.value = value;
  }
  
  /**
   * Set the value of this frequency.
   *
   * @param s     the string representation of the frequency.
   * @param frame For errors and warnings reports.
   * @exception InvalidParamException The unit is incorrect
   */  
  public void set(String s, ApplContext ac) throws InvalidParamException {
    s = s.toLowerCase();
    int length = s.length();
    String unit;
    float v;
    if (s.charAt(length-3) == 'k') {
      unit = s.substring(length-3, length);
      v = new Float(s.substring(0, length-3)).floatValue();
    } else {
      unit = s.substring(length-2, length);
      v = new Float(s.substring(0, length-2)).floatValue();
    }
    int hash = unit.hashCode();


    int i = 0;
    while (i<units.length) {
      if (hash == hash_units[i]) {
	this.unit = i;
	break;
      }
      i++;
    }

    if (i == units.length) {
      throw new InvalidParamException("unit", unit, ac);
    }

    this.value = new Float(v);

  }

  /**
   * Returns the current value
   */  
  public Object get() {
    if (unit == 1) {
      return new Float(value.floatValue() * 1000);
    }
    return value;
  }
  
  /**
   * Returns the current value
   */  
  public String getUnit() {
    return units[unit];
  }

  /**
   * Returns a string representation of the object.
   */
  public String toString() {
      if (value.floatValue() != 0) {
	  return Util.displayFloat(value) + getUnit();
      } else {
	  return Util.displayFloat(value);
      }
  }

  /**
   * Compares two values for equality.
   *
   * @param value The other value.
   */  
  public boolean equals(Object value) {
    return (value instanceof CssFrequency 
	        && this.value.equals(((CssFrequency) value).value) 
	        && unit == ((CssFrequency) value).unit);
  }

  private Float value;
  private int unit;
  private static String[] units = { "Hz", "kHz" };
  private static int[] hash_units;
  private static Float defaultValue = new Float(0);

  static {
    hash_units = new int[units.length];
    for (int i=0; i<units.length; i++)
      hash_units[i] = units[i].toLowerCase().hashCode();
  }
}


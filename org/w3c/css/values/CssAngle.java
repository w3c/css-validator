//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.1  1997/08/29 13:10:38  plehegar
 * Initial revision
 *
 * Revision 1.1  1997/08/21 08:29:35  plehegar
 * Initial revision
 *
 */
package org.w3c.css.values;

import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Util;

/**
 * <H3>Angle</H3>
 
 * <P>Angle units are used with aural cascading style sheets.
 *
 * <P>These are the legal angle units:
 *
 * <UL>
 * <LI>deg: degrees
 * <LI>grad: gradians
 * <LI>rad: radians
 * </UL>
 *
 * <p>Values in these units may be negative. They should be normalized to the
 * range 0-360deg by the UA. For example, -10deg and 350deg are equivalent.
 *
 * @version $Revision$ */
public class CssAngle extends CssValue implements CssValueFloat {
    
    Float value;
    int unit;
    static String[] units = { "deg", "grad", "rad" };
    static int[] hash_units;
    static Float defaultValue = new Float(0);
    
    /**
     * Create a new CssAngle.
     */
    public CssAngle() {
	this(defaultValue);
    }  
    
    /**
     * Create a new CssAngle
     */
    public CssAngle(float v) {
	this(new Float(v));
    } 
    
    /**
     * Create a new CssAngle
     */
    public CssAngle(Float angle) {
	value = angle;
    }  
    
    /**
     * Set the value of this angle.
     *
     * @param s The string representation of the angle
     * @param frame For errors and warnings reports
     * @exception InvalidParamException The unit is incorrect
     */  
    public void set(String s, ApplContext ac) throws InvalidParamException {
	s = s.toLowerCase();
	int length = s.length();
	String unit;
	float v;
	if (s.indexOf("grad") == -1) {
	    unit = s.substring(length-3, length);
	    value = new Float(s.substring(0, length-3));
	} else {
	    unit = "grad";
	    value = new Float(s.substring(0, length-4));
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
	
	if (i > 2) {
	    throw new InvalidParamException("unit", unit, ac);
	}
	
	this.unit = i; // there is no unit by default
	
	/* clipping with degree */
	/*
	  while (v < 0) {
	  v += 360;
	  }
	  while (v > 360) {
	  v -= 360;
	  }
	*/
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	return value;
    }
    
    public float getValue() {
	return value.floatValue();
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
	return (value instanceof CssAngle && 
		this.value.equals(((CssAngle) value).value) &&
		unit == ((CssAngle) value).unit);
    }


    //@@FIXME I should return the remainder for all ...

    public float getDegree() {
	float deg = value.floatValue();
	switch (unit) {
	case 0:
	    // deg % 360
	    return deg;
	case 1:
	    return (deg * (180 / ((float) Math.PI)));
	case 2:
	    return (deg * (9 / 5));
	default:
	    System.err.println("[ERROR] in org.w3c.css.values.CssAngle");
	    System.err.println("[ERROR] Please report (" + unit + ")");
	    return (float) 0;
	}
    }

    public float getGradian() {
	float grad = value.floatValue();
	switch (unit) {
	case 0:
	    return (grad * (((float) Math.PI) / 180));
	case 1:
	    return grad;
	case 2:
	    return (grad * (((float) Math.PI) / 100));
	default:
	    System.err.println("[ERROR] in org.w3c.css.values.CssAngle");
	    System.err.println("[ERROR] Please report (" + unit + ")");
	    return (float) 0;
	}
    }

    public float getRadian() {
	float rad = value.floatValue();
	switch (unit) {
	case 0:
	    return (rad * (5 / 9));
	case 1:
	    return (rad * (100 / ((float) Math.PI)));
	case 2:
	    return rad;
	default:
	    System.err.println("[ERROR] in org.w3c.css.values.CssAngle");
	    System.err.println("[ERROR] Please report (" + unit + ")");
	    return (float) 0;
	}
    }

    public boolean isDegree() {
	return unit == 0;
    }
    
    public boolean isGradian() {
	return unit == 1;
    }
    
    public boolean isRadian() {
	return unit == 2;
    }
    
    static {
	hash_units = new int[units.length];
	for (int i=0; i<units.length; i++)
	    hash_units[i] = units[i].hashCode();
    }
}


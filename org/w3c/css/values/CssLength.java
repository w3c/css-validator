//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 25th 2000 Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;

/**
 *   <H3>
 *     &nbsp;&nbsp; Length units
 *   </H3>
 *   <P>
 *   The format of a length value is an optional sign character ('+' or '-', with
 *   '+' being the default) immediately followed by a number (with or without
 *   a decimal point) immediately followed by a unit identifier (a two-letter
 *   abbreviation). After a '0' number, the unit identifier is optional.
 *   <P>
 *   Some properties allow negative length units, but this may complicate the
 *   formatting model and there may be implementation-specific limits. If a negative
 *   length value cannot be supported, it should be clipped to the nearest value
 *   that can be supported.
 *   <P>
 *   There are two types of length units: relative and absolute. Relative units
 *   specify a length relative to another length property. Style sheets that use
 *   relative units will more easily scale from one medium to another (e.g. from
 *   a computer display to a laser printer). Percentage
 *   units (described below) and keyword values (e.g. 'x-large') offer similar
 *   advantages.
 *   <P>
 *   These relative units are supported:
 *   <PRE>
 *   H1 { margin: 0.5em }      /* ems, the height of the element's font * /
 *   H1 { margin: 1ex }        /* x-height, ~ the height of the letter 'x' * /
 *   P  { font-size: 12px }    /* pixels, relative to canvas * /
 *   P  { layout-grid: strict both 20 pt 15 pt; margin 1gd 3gd 1gd 2gd } /* grid units * /
 *  </PRE>
 *   <P>
 *   The relative units 'em' and 'ex' are relative to the font size of the element
 *   itself. The only exception to this rule in CSS1 is the 'font-size' property
 *   where 'em' and 'ex' values refer to the font size of the parent element.
 *   <P>
 *   The existence of a grid in an element makes it possible and very useful to express various
 *   measurements in that element in terms of grid units. Grid units are used very frequently
 *   in East Asian typography, especially for the left, right, top and bottom element margins.
 *   Therefore a new length unit is necessary: gd to enable the author to specify the various
 *   measurements in terms of the grid.
 *   <P>
 *   Pixel units, as used in the last rule, are relative to the resolution of
 *   the canvas, i.e. most often a computer display. If the pixel density of the
 *   output device is very different from that of a typical computer display,
 *   the UA should rescale pixel values. The suggested <EM>reference pixel</EM>
 *   is the visual angle of one pixel on a device with a pixel density of 90dpi
 *   and a distance from the reader of an arm's length. For a nominal arm's length
 *   of 28 inches, the visual angle is about 0.0227 degrees.
 *   <P>
 *   Child elements inherit the computed value, not the relative value:
 *   <PRE>
 *   BODY {
 *     font-size: 12pt;
 *     text-indent: 3em;  /* i.e. 36pt * /
 *   }
 *   H1 { font-size: 15pt }
 * </PRE>
 *   <P>
 *   In the example above, the 'text-indent' value of 'H1' elements will be 36pt,
 *   not 45pt.
 *   <P>
 *   Absolute length units are only useful when the physical properties of the
 *   output medium are known. These absolute units are supported:
 *   <PRE>
 *   H1 { margin: 0.5in }      /* inches, 1in = 2.54cm * /
 *   H2 { line-height: 3cm }   /* centimeters * /
 *   H3 { word-spacing: 4mm }  /* millimeters * /
 *   H4 { font-size: 12pt }    /* points, 1pt = 1/72 in * /
 *   H4 { font-size: 1pc }     /* picas, 1pc = 12pt * /
 * </PRE>
 *   <P>
 *   In cases where the specified length cannot be supported, UAs should try to
 *   approximate. For all CSS1 properties, further computations and inheritance
 *   should be based on the approximated value.
 *
 * @see CssPercentage
 * @version $Revision$
 */
public class CssLength extends CssValue {

    public static final int type = CssTypes.CSS_LENGTH;
    
    public final int getType() {
	return type;
    }
    
    /**
     * Create a new CssLength
     */
    public CssLength() {
	value = defaultValue;
    }

    /**
     * Set the value of this length.
     *
     * @param s     the string representation of the length.
     * @param frame For errors and warnings reports.
     * @exception InvalidParamException The unit is incorrect
     */
    public void set(String s, ApplContext ac) throws InvalidParamException {
	s = s.toLowerCase();
	int length = s.length();
	String unit = s.substring(length-2, length);
	this.value = new Float(s.substring(0, length-2));

	this.unit = 2; // there is no unit by default

	if (unit.equals("gd") && (cssversion.equals("css2"))) {
	    throw new InvalidParamException("unit", unit, ac);
	}

	if (value.floatValue() != 0) {
	    int hash = unit.hashCode();
	    int i = 0;
	    while (i<units.length) {
		if (hash == hash_units[i]) {
		    this.unit = i;
		    return;
		}
		i++;
	    }
	} else {
	    return;
	}

	throw new InvalidParamException("unit", unit, ac);
    }

    /**
     * Returns the current value
     */
    public Object get() {
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
	return (value instanceof CssLength &&
		this.value.equals(((CssLength) value).value) &&
		unit == ((CssLength) value).unit);
    }

    private Float value;
    private int unit;
    private static String[] units = { "mm", "cm", "pt", "pc", "em", 
				      "ex", "px", "in", "gd" };
    private static int[] hash_units;
    private static Float defaultValue = new Float(0);

    static {
	hash_units = new int[units.length];
	for (int i=0; i<units.length; i++)
	    hash_units[i] = units[i].hashCode();
    }
}


//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

/**
 * @spec http://www.w3.org/TR/2008/REC-CSS1-20080411/#units
 */
public class CssUnitsCSS1 {
	private static final String[] relative_length_units = {
			"em", "ex", "px"
	};

	private static final String[] absolute_length_units = {
			"in", "cm", "mm", "pt", "pc"
	};

	protected static String getRelativeLengthUnit(String unit) {
		for (String s : relative_length_units) {
			if (s.equals(unit)) {
				return s;
			}
		}
		return null;
	}

	protected static String getAbsoluteLengthUnit(String unit) {
		for (String s : absolute_length_units) {
			if (s.equals(unit)) {
				return s;
			}
		}
		return null;
	}

	protected static void parseLengthUnit(String unit, CssLength length, ApplContext ac)
		throws InvalidParamException {
		String matchedUnit = getRelativeLengthUnit(unit);
		if (matchedUnit != null) {
			length.absolute = false;
		} else {
			matchedUnit = getAbsoluteLengthUnit(unit);
			if (matchedUnit == null) {
				throw new InvalidParamException("unit", unit, ac);
			}
			length.absolute = true;
		}
		length.unit = matchedUnit;
	}
}

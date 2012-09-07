// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

/**
 * @spec http://www.w3.org/TR/2011/REC-CSS2-20110607/syndata.html#values
 */
public class CssUnitsCSS21 {
	private static final String[] relative_length_units = {
			"em", "ex"
	};

	private static final String[] absolute_length_units = {
			"in", "cm", "mm", "pt", "pc", "px"
	};

	// defined in Appendix A.2
	// http://www.w3.org/TR/2011/REC-CSS2-20110607/aural.html#aural-intro
	public static final String[] angle_units = {
			"deg", "grad", "rad"
	};

	public static final String[] time_units = {
			"ms", "s"
	};

	public static final String[] frequency_units = {
			"kHz", "Hz"
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

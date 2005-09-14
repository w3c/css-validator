// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors.pseudofunctions;

import org.w3c.css.selectors.PseudoFunctionSelector;

/**
 * PseudoFunctionContains<br />
 * Created: Sep 2, 2005 4:25:06 PM<br />
 */
public class PseudoFunctionContains extends PseudoFunctionSelector {

    public PseudoFunctionContains(String name, String text) {
	setName(name);
	setParam(text);
    }

}

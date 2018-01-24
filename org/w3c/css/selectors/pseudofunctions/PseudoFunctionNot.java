// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors.pseudofunctions;

import org.w3c.css.selectors.PseudoFunctionSelector;

/**
 * PseudoFunctionNot<br />
 * Created: Sep 2, 2005 4:25:20 PM<br />
 */
public class PseudoFunctionNot extends PseudoFunctionSelector {

    public PseudoFunctionNot(String name, String value) {
        setName(name);
        setParam(value);
    }

}

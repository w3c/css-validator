// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors.pseudofunctions;

import org.w3c.css.selectors.PseudoFunctionSelector;

/**
 * PseudoFunctionLang<br />
 * Created: Sep 2, 2005 4:24:48 PM<br />
 */
public class PseudoFunctionLang extends PseudoFunctionSelector {
    
    public PseudoFunctionLang(String name, String lang) {
	setName(name);
	setParam(lang);
    }
   
}

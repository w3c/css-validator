// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors;

/**
 * Universal<br />
 * Created: Sep 1, 2005 3:45:13 PM<br />
 */
public class UniversalSelector implements Selector {

    /**
     * @see Selector#toString()
     */
    public String toString() {
	return "*";
    }

    /**
     * @see Selector#canApply(Selector)
     */
    public boolean canApply(Selector other) {
	return false;
    }

    /**
     * @see Selector#getName()
     */
    public String getName() {
	return "*";
    }

}

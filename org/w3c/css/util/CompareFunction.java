//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.util;

/**
 * The comparaison function for the SortAlgorithm class
 *
 * @version $Revision$
 * @author  Philippe Le Hegaret
 */
public abstract class CompareFunction {
    public abstract boolean compare(Object obj1, Object obj2);
}

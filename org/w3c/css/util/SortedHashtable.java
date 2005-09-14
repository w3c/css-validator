//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.util;


import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @version $Revision$
 */
public class SortedHashtable extends Hashtable {

    /**
     * Constructs a new, empty hashtable with the specified initial
     * capacity and the specified load factor.
     *
     * @param      initialCapacity   the initial capacity of the hashtable.
     * @param      loadFactor        a number between 0.0 and 1.0.
     * @exception  IllegalArgumentException  if the initial capacity is less
     *               than or equal to zero, or if the load factor is less than
     *               or equal to zero.
     */
    public SortedHashtable(int initialCapacity, float loadFactor) {
	super(initialCapacity, loadFactor);
    }

    /**
     * Constructs a new, empty hashtable with the specified initial capacity
     * and default load factor.
     *
     * @param   initialCapacity   the initial capacity of the hashtable.
     */
    public SortedHashtable(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Constructs a new, empty hashtable with a default capacity and load
     * factor.
     *
     * @since   JDK1.0
     */
    public SortedHashtable() {
	super();
    }

    public Object[] getSortedArray() {
	SortAlgorithm sort = new QuickSortAlgorithm();
	Object[] sortedArray = new Object[size()];
	int i = 0;

	for (Enumeration e = elements(); e.hasMoreElements(); i++) {
	    sortedArray[i] = e.nextElement();
	}

	sort.sort(sortedArray, 0, size()-1, new CompareString());

	return sortedArray;
    }
}

class CompareString extends CompareFunction {
    public boolean compare(Object obj1, Object obj2) {
	return (obj1.toString().compareTo(obj2.toString()) > 0);
    }
}

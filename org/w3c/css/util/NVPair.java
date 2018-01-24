//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * Be Careful this version is not the original version.
 * I modified some sources.          Philippe Le Hegaret
 *
 * @(#)NVPair.java					0.2-2 23/03/1997
 *
 *  This file is part of the HTTPClient package
 *  Copyright (C) 1996,1997  Ronald Tschalaer
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Library General Public
 *  License as published by the Free Software Foundation; either
 *  version 2 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Library General Public License for more details.
 *
 *  You should have received a copy of the GNU Library General Public
 *  License along with this library; if not, write to the Free
 *  Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,
 *  MA 02111-1307, USA
 *
 *  For questions, suggestions, bug-reports, enhancement-requests etc.
 *  I may be contacted at:
 *
 *  ronald@innovation.ch
 *  Ronald.Tschalaer@psi.ch
 *
 */

package org.w3c.css.util;

/**
 * This class holds a Name/Value pair of strings. It's used for form-data
 * and various internal things.
 *
 * @version 0.2 (bug fix 2)  23/03/1997
 * @author Ronald Tschal&auml;r
 */

public class NVPair {
    /**
     * the name
     */
    String name;

    /**
     * the value
     */
    Object value;

    // Constructors

    /**
     * Creates an empty name/value pair.
     */
    NVPair() {
        this("", "");
    }

    /**
     * Creates a copy of a given name/value pair.
     *
     * @param p the name/value pair to copy
     */
    public NVPair(NVPair p) {
        this(p.name, p.value);
    }

    /**
     * Creates a new name/value pair and initializes it to the
     * specified name and value.
     *
     * @param name  the name
     * @param value the value
     */
    public NVPair(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    // Methods

    /**
     * get the name
     *
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * get the value
     *
     * @return the value
     */
    public final Object getValue() {
        return value;
    }

    /**
     * produces a string containing the name and value of this instance.
     *
     * @return a string containing the class name and the name and value
     */
    public String toString() {
        return getClass().getName() + "[name=" + name + ",value=" + value + "]";
    }
}


/*
 * @(#)Tag.java	1.2 95/05/03
 *
 * Copyright (c) 1994 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies. Please refer to the file "copyright.html"
 * for further important copyright and licensing information.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package html.parser;

/**
 * A generic Tag interface. It is the API used by the parser to
 * correctly deal with tags inside documents. Its attributes define
 * how white space is interpreted.
 */

public interface Tag {

    /**
     * Return true if the tag is a block tag and not
     * a flow tag.
     */
    boolean isBlock();

    /**
     * Return if the tag's content is pre-formatted.
     */
    boolean isPreformatted();

    /**
     * Get the element of this tag.
     */
    Element getElement();

    /**
     * Get the attributes of this tag.
     */
    Attributes getAttributes();
}

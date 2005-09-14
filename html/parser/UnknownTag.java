/*
 * @(#)UnknownTag.java	1.3 95/05/03
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
 * An unknow Tag implementation. It is only used when the parser
 * is used in a standalone manner.
 */

public class UnknownTag implements Tag {
    Element elem;
    Attributes atts;

    public UnknownTag(Element elem, Attributes atts) {
	this.elem = elem;
	this.atts = atts;
    }

    public void initialize(Element elem, Attributes atts) {
	this.elem = elem;
	this.atts = atts;
    }

    public boolean isBlock() {
	return false;
    }

    public boolean isPreformatted() {
	return true;
    }

    public Element getElement() {
	return elem;
    }

    public Attributes getAttributes() {
	return atts;
    }
}










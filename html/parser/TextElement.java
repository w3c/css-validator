/* Copyright (c) 1996 by Groupe Bull.  All Rights Reserved */
/* $Id$ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */


package html.parser;

public class TextElement extends Element {
    static public final String TEXT="text";
    public TextElement() {
	super(TEXT, 0);
    }
}

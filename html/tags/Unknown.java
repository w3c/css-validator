/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id$ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tags;

import html.tree.*;

public class Unknown extends HtmlTree {

    public boolean isBlock() {
	return false;
    }

    public boolean isPreformatted() {
	return true;
    }

}

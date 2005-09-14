/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id$ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.util;

import java.util.*;
import java.net.*;

public class URLHistory {
    private Vector urls = new Vector(1, 1);
    private int index = -1;

    public void append(URL url) {
	urls.addElement(url);
	index = urls.size()-1;
    }

    public URL moveTo(URL url) {
	int n = urls.indexOf(url);
	if(n != -1) {
	    index = n;
	}
	return url;
    }

    public URL next() {
	if(index < urls.size()-1) {
	    return (URL)(urls.elementAt(index+1));
	}
	return null;
    }

    public URL previous() {
	if(index > 0) {
	    return (URL)(urls.elementAt(index-1));
	}
	return null;
    }
}

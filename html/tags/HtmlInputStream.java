/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id$ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tags;

import java.io.*;
import java.util.Vector;

class HtmlInputStream extends FilterInputStream {
    Vector listeners = new Vector();
    int lines = 0;
    int bytes = 0;
    
    public HtmlInputStream(InputStream  in) {
	super(in);
    }

    public int read() throws IOException {
	int c = super.read();
	if(c == '\n') {
	    lines++;
	}
	if(c != -1) {
	    bytes++;
	    notifyActivity();
	}
	return c;
    }
    
    public int read(byte  b[]) throws IOException {
	int c = super.read(b);
	for(int i = 0; i < c; i++) {
	    if(b[i] == '\n') {
		lines++;
	    }
	}
	if(c != -1) {
	    bytes += c;
	    notifyActivity();
	}
	return c;
    }
    
    public int read(byte  b[], int  off, int  len) throws IOException {
	int c = super.read(b, off, len);
	
	for(int i = 0; i < c; i++) {
	    if(b[i+off] == '\n') {
		lines++;
	    }
	}
	if(c != -1) {
	    bytes += c;
	    notifyActivity();
	}
	return c;
    }

    public void addHtmlStreamListener(HtmlStreamListener l) {
	listeners.addElement(l);
    }
    public void removeHtmlStreamListener(HtmlStreamListener l) {
	listeners.removeElement(l);
    }

    protected void notifyActivity() {
 	for(int i = 0; i < listeners.size(); i++) {
 	    HtmlStreamListener l =
 		(HtmlStreamListener)listeners.elementAt(i);
 	    l.notifyActivity(lines, bytes);
  	}
    }
    
}

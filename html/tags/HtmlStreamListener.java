/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id$ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tags;

public interface HtmlStreamListener {
    public void notifyActivity(int lines, long bytes);
}

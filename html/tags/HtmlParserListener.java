/* Copyright (c) 1996 by Groupe Bull.  All Rights Reserved */
/* $Id$ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */
/* modified: Vincent.Mallet@sophia.inria.fr */
package html.tags;

import html.parser.*;

import java.net.*;


/**
 * An HtmlParserListener observes an HtmlParser and is notified of the
 * creation of the root node and the end of the parsing.
 */
public interface HtmlParserListener extends HtmlStreamListener {

   /**
    * Notifies root creation.
    *
    * Sent when the parser builds the root of the HTML tree.
    *
    * @param url the URL being parsed.
    * @param root the new root Tag for this parser.
    */    
    public void notifyCreateRoot(URL url, HtmlTag root);

    public void notifyConnection(URLConnection cnx);

   /**
    * Notifies successful termination.
    *
    * @param root the root of the current Tree.
    */    
    public void notifyEnd(HtmlTag root, String contenttype);


   /**
    * Notifies a fatal error.
    *
    * This notification is sent when the parser need to stop during or before
    * parsing, due to an unexpected exception.
    *
    * @param root the root of the current Tree, if any.
    * @param x the exception that cause the Parser stop
    * @param msg an error message information
    */
    public void notifyFatalError(HtmlTag root, Exception x, String s);
}

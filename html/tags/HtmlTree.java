/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id$ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */
/* Modified: Vincent Mallet (Vincent.Mallet@sophia.inria.fr) */

package html.tags;

import html.parser.*;
import html.tree.*;

import org.w3c.css.css.StyleSheet;
import org.w3c.css.parser.CssSelectors;

import java.io.ByteArrayInputStream;
import java.util.*;


/**
 *
 */

public abstract class HtmlTree extends ActiveTree implements HtmlTag {
    Element    elem;
    Attributes atts;
    int        line; // 970801 -vm
    
    /**
     *
     */
    ParserFrame parserFrame = null; // 970724 -vm
    
    
    //============================================================
    //      implements: html.parser.Tag
    //============================================================
    
    public void initialize(Element elem, Attributes atts, ParserFrame parserFrame) {
	this.elem        = elem;
	this.atts        = atts;
	this.parserFrame = parserFrame;      // 970724 -vm
	this.line        = parserFrame.line; // 970801 -vm
	
	checkStyleAttribute(); // 970724 -vm
    }
    
    public abstract boolean isBlock();
    
    public boolean isPreformatted() {
	return false;
    }
    
    public Element getElement() {
	return elem;
    }
    
    public Attributes getAttributes() {
	return atts;
    }
    
    
    public StyleSheet getStyleSheet() {
	return parserFrame.styleSheetParser.getStyleSheet();  // 970724 -vm
    }        
    
    /**
     * @@ [DESCRIBEME]
     */
    
    CssSelectors context = null;
    
    /**
     * Get the stylesheet context for this level in the Html tree. It will be used to
     * retrieve the actual stylesheet properties for the element.
     * @@ [CONTINUE ME]
     */
    
    public CssSelectors getContext() {
	//    ContextStack context;

	if (context != null) {
	    //      System.out.println("opt!");
	    return context;
	    //      return (CssSelectors) context.clone();
	}
	
	// get context from parent
	HtmlTree atl = (HtmlTree) getParent();
	
	if (atl == null) {
	    context = new CssSelectors(parserFrame.ac);
	} else {
	    context = new CssSelectors(atl.getContext());
	}
	
	// add own context
	//    CssContextList thisContext = new CssContextList(getElement().getName());
	context.setElement(getElement().getName().toUpperCase());
	
	//    Attributes atts = htmlNode.getAttributes();
	if (atts != null) {
	    for (int i = 0; i < atts.length(); i++) {
		//	  thisContext = new CssContextList("." + st.nextToken(), thisContext);	
		try {
		    context.addAttribute(atts.getName(i), atts.get(i));	
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}
	
	//    context.push(thisContext);
	
	//    return (CssContextStack) context.clone();      
	return context;
    }
    
    
    
    
    
    
    
    
    /**
     * Private counter to produce unique IDs.
     */
    protected static int autoIdCount = 0;
    
    /**
     * Check current element to see if it has a <code>STYLE</code> attribute.
     * In that case, parse it using the StyleSheet object, and store it with
     * an <code>id</code> attribute. If the <code>id</code> attribute already
     * exists, reuse it, otherwise create a new one (it will be unique).
     */
    public void checkStyleAttribute() { // 970724 -vm
        
	//    boolean debug = true;
	//    Attributes atts = htmlNode.getAttributes();
	
	if (atts == null)     //@@ I dont think this should ever happen...
	    return; 
	
	String style = atts.get("style");
	String id    = atts.get("id");
	
	if (style != null) { // here we have a style attribute
	    
	    if (id == null) { // but we have no id: create one.
		id = "#auto" + autoIdCount; // a normal id should NOT contain a "#" character.
		id += "" + autoIdCount++;   // workaround a java hashcode bug.
		if (Boolean.getBoolean("html.tree.debug"))
		    System.out.println("HtmlTree::checkStyleAttribute(): adding id: " + id + " to node: " + elem.getName());
		atts.put("id", id);
	    }
	    
	    // parse the style attribute.
	    parserFrame.styleSheetParser
		.parseStyleAttribute(parserFrame.ac,
				     new ByteArrayInputStream(style.getBytes()), 
				     id, parserFrame.url, line);
	}
    }
    
    public String toString() {
	return getClass().getName() + "[" + elem + "," + atts + "," + arity() + "]";
    }
    
}

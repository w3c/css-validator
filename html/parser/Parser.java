/*
 * @(#)Parser.java	1.10 95/06/07  
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


/**
 * List of bug fixes:

 * FIX1 - jml - 5/7/97.  make the parser accept unknown tags. just use
 * DTD.findElement, instead of DTD.getElement

 * FIX2 - vm - 97.07.07. Fixed the bug which made the parser case sensitive
 * when parsing a literal element and looking for an end tag (ie for 
 * the <style> literal </style> )

 * FIX3 - vm - 97.08.20. Added explicit casts to convert char to byte in three
 * places, to get the code 1.1 compliant.

 * FIX4 - vm - 97.08.27. The parser was said to treat "\r\n" as "\n". Well
 * in fact it treated "\n\r" as "\n", so I added "\r\n"->\n. We should 
 * probably remove the "\n\r"->\n.
 
 */

package html.parser;

import java.net.URL;   //??dk
import java.util.Properties;  //??dk
import java.io.IOException;   //??dk
import java.io.File;
import java.io.InputStream;
import java.util.Hashtable;

/**
 * A simple DTD driven HTML parser. The parser reads an
 * HTML file from an InputStream and calls various methods
 * (which should be overridden in a subclass) when tags and
 * data is encountered.<p>
 *
 * Unfortunately there are many badly implemented HTML parsers
 * out there, and as a result there are many badly formatted
 * HTML files. This parser attempts to parse most HTML files.
 * This means that the implementation sometimes deviates from
 * the SGML specification in favor of HTML.<p>
 *
 * The parser treats \r and \r\n as \n. Newlines after starttags 
 * and before end tags are ignore just as specified in the SGML/HTML 
 * specification. <p>
 *
 * @version 	1.10, 07 Jun 1995
 * @author Arthur van Hoff
 * @modified Dmitri Kondratiev for JDK Beta 2 05/01/96 //??dk
 * @modified Jean-Michel Leon for JDK 1.1 - jml - 3/6/97.
 * @modified Vincent Mallet (little bugfix) - vm - 970707-970828
 */
public
class Parser implements DTDConstants {
    static final byte notext[] = new byte[0];
    byte text[] = new byte[1024];
    int textpos = 0;
    Tag last;
    boolean space;
    boolean verbose;
    HTMLOutputStream out;

    char str[] = new char[128];
    int strpos = 0;

    protected Properties props;
    protected DTD dtd;

    int ch;
    public int ln;
    InputStream in;
    Element recent;
    TagStack stack;

    /**
     * Make a tag. Should be overridden in a subclass to
     * create the appropriate Tag instance.
     */
    protected Tag makeTag(Element elem, Attributes atts) {
	return new UnknownTag(elem, atts);
    }

    //FIX1 - start - jml - 5/7/97.
    protected void handleIllegalTag(String name) {
	error("illegal.tag", name);
    }
    //FIX1 - end - jml - 5/7/97.

    /**
     * Called when PCDATA is encountered.
     */
    protected void handleText(byte text[]) throws IOException {           //??dk
	if (verbose && (out != null)) {
	    out.text(text);
	}
    }

    /**
     * Called when an HTML comment is encountered.
     */
    protected void handleComment(String str) throws IOException {         //??dk
	if (verbose && (out != null)) {
	    out.comment(str);
	}
    }

    /**
     * Called when an empty tag is encountered.
     */
    protected void handleEmptyTag(Tag tag) throws IOException  {          //??dk
	if (verbose && (out != null)) {
	    out.emptyTag(tag);
	}
    }

    /**
     * Called when a start tag is encountered.
     */
    protected void handleStartTag(Tag tag) throws IOException {           //??dk
	if (verbose && (out != null)) {
	    out.startTag(tag);
	}
    }

    /**
     * Called when an end tag is encountered.
     */
    protected void handleEndTag(Tag tag) throws IOException {             //??dk
	if (verbose && (out != null)) {
	    out.endTag(tag);
	}
    }

    /**
     * An error has occurred.
     */
    void handleError(int ln, String msg) {
      if(Boolean.getBoolean("html.parser.debug")) { // @@ force it to be debug-verbose
	    System.out.println("**** " + stack);
	    System.out.println("line " + ln + ": error: " + msg);
	    System.out.println();
	}
    }

    /**
     * Output text.
     */
    void handleText(Tag tag) throws IOException {               //??dk
	if (tag.isBlock()) {
	    space = false;
	}
	if (textpos == 0) {
	    if ((!space) || (stack == null) || last.isBlock() || !stack.advance(dtd.pcdata)) {
		last = tag;
		space = false;
		return;
	    }
	}
	if (space) {
	    // enlarge buffer if needed
	    if (textpos + 1 > text.length) {
		byte newtext[] = new byte[text.length * 2];
		System.arraycopy(text, 0, newtext, 0, text.length);
		text = newtext;
	    }

	    // output pending space
	    text[textpos++] = (byte) ' ';  //vm 980722
	    space = false;
	}
	byte newtext[] = new byte[textpos];
	System.arraycopy(text, 0, newtext, 0, textpos);
	handleText(newtext);
	textpos = 0;
	last = tag;
	space = false;
    }

    /**
     * Invoke the error handler.
     */
    void error(String err, String arg1, String arg2, String arg3) {
	String str = DTD.props.getProperty("htmlerr." + err);
	if (str == null) {
	    str = err;
	}
	for (int i ; (i = str.indexOf('%')) > 0 ; ) {
	    str = str.substring(0, i) + arg1 + str.substring(i+1);
	    arg1 = arg2;
	    arg2 = arg3;
	}
	handleError(ln, str);
    }
    void error(String err, String arg1, String arg2) {
	error(err, arg1, arg2, "?");
    }
    void error(String err, String arg1) {
	error(err, arg1, "?", "?");
    }
    void error(String err) {
	error(err, "?", "?", "?");
    }

    /**
     * Handle a start tag. The new tag is pushed
     * onto the tag stack. The attribute list is
     * checked for required attributes.
     */
    void startTag(Tag tag) throws IOException {                       //??dk
	handleText(tag);

	Element elem = tag.getElement();
	Attributes atts = tag.getAttributes();

	// check required attributes
	for (AttributeList a = elem.atts ; a != null ; a = a.next) {
	    if ((a.modifier == REQUIRED) && ((atts == null) || (atts.get(a.name) == null))) {
		error("req.att", a.getName(), elem.getName());
	    }
	}

	if (elem.isEmpty()) {
	    handleEmptyTag(tag);
	} else {
	    recent = elem;
	    stack = new TagStack(tag, stack);
	    handleStartTag(tag);
	}
    }

    /**
     * Handle an end tag. The end tag is popped
     * from the tag stack.
     */
    void endTag(boolean omitted) throws IOException {                   //??dk
	handleText(stack.tag);

	if (omitted && !stack.elem.omitEnd()) {
	    error("end.missing", stack.elem.getName());
	} else if (!stack.terminate()) {
	    error("end.unexpected", stack.elem.getName());
	}

	// handle the tag
	handleEndTag(stack.tag);
	stack = stack.next;
	recent = (stack != null) ? stack.elem : null;
    }

    /**
     * Create a legal content for an element.
     */
    boolean legalElementContext(Element elem) throws IOException {        //??dk
	//System.out.println("-- legalContext -- " + elem);

	// Deal with the empty stack
	if (stack == null) {
	    //System.out.println("-- stack is empty");
	    if (elem != dtd.html) {
		//System.out.println("-- pushing html");
		startTag(makeTag(dtd.html, null));
		return legalElementContext(elem);
	    }
	    return true;
	}

	// Is it allowed in the current context
	if (stack.advance(elem)) {
	    //System.out.println("-- legal context");
	    return true;
	}

	// Find a legal context by omitting end tags
	if (stack.elem.omitEnd() && stack.terminate()) {
	    for (TagStack s = stack.next ; s != null ; s = s.next) {
		if (s.advance(elem)) {
		    while (stack != s) {
			endTag(true);
		    }
		    //System.out.println("-- found legal context by omitting end tags");
		    return true;
		}
		if ((!s.elem.omitEnd()) || (!s.terminate())) {
		    break;
		}
	    }
	}

	// Omit start tag
	Element next = stack.first();
	if ((next != null) && next.omitStart()) {
	    //System.out.println("-- omitting start tag: " + next);
	    Tag t = makeTag(next, null);
	    legalTagContext(t);
	    startTag(t);
	    return legalElementContext(elem);
	}

	// Omit end tag
	if (stack.elem.omitEnd() && stack.terminate()) {
	    //System.out.println("-- omitting end tag: " + stack.elem);
	    endTag(true);
	    return legalElementContext(elem);
	}

	// At this point we know that something is screwed up.
	return false;
    }

    /**
     * Create a legal context for a tag.
     */
    void legalTagContext(Tag tag) throws IOException {              //??dk
	if (legalElementContext(tag.getElement())) {
	    return;
	}
	//sSystem.out.println("illegal tag context for " + tag);
	// Avoid putting a block tag in a flow tag
 	if (tag.isBlock() && (stack != null) && !stack.tag.isBlock()) {
 	    endTag(true);
 	    legalTagContext(tag);
 	    return;
 	}

	// Everything failed
	error("tag.unexpected", tag.getElement().getName());
	//errorContext();		//  - jml - 1/30/97.
    }

    protected void startErrorRecovery() {
	// nothing
    }

    protected void endErrorRecovery() {
	// nothing
    }

    /**
     * Error context. Something went wrong, make sure we are in
     * the document's body context
     */
    void errorContext() throws IOException {              //??dk
	
	//startErrorRecovery();
	for (; (stack != null) && (stack.tag != dtd.body) ; stack = stack.next) {
	    handleEndTag(stack.tag);
	}
	if (stack == null) {
	    legalElementContext(dtd.body);
	    startTag(makeTag(dtd.body, null));
	}
	//endErrorRecovery();
    }

    /**
     * Add a char to the string buffer.
     */
    void addString(int c) {
	if (strpos == str.length) {
	    char newstr[] = new char[str.length * 2];
	    System.arraycopy(str, 0, newstr, 0, str.length);
	    str = newstr;
	}
	str[strpos++] = (char)c;
    }

    /**
     * Get the string.
     */
    String getString(int pos) {
	char newstr[] = new char[strpos - pos];
	System.arraycopy(str, pos, newstr, 0, strpos - pos);
	strpos = pos;
	return new String(newstr);
    }

    /**
     * Skip space.
     * [5] 297:5
     */
    void skipSpace() throws IOException {                             //??dk
	while (true) {
	    switch (ch) {
	      case '\n':
		ln++;
		if ((ch = in.read()) == '\r') {
		    ch = in.read();
		}
		break;

	      case '\r':
		ln++;
		if ((ch = in.read()) == '\n') {
		  ch = in.read();
		}
		break;

	      case ' ':
	      case '\t':
		ch = in.read();
		break;

	      default:
		return;
	    }
	}
    }

    /**
     * Parse identifier. Uppercase characters are folded
     * to lowercase when lower is true. Returns falsed if 
     * no identifier is found. [55] 346:17
     */
    boolean parseIdentifier(boolean lower) throws IOException {               //??dk
	switch (ch) {
	  case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
	  case 'G': case 'H': case 'I': case 'J': case 'K': case 'L':
	  case 'M': case 'N': case 'O': case 'P': case 'Q': case 'R':
	  case 'S': case 'T': case 'U': case 'V': case 'W': case 'X':
	  case 'Y': case 'Z':
	    if (lower) {
		ch = 'a' + (ch - 'A');
	    }

	  case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
	  case 'g': case 'h': case 'i': case 'j': case 'k': case 'l':
	  case 'm': case 'n': case 'o': case 'p': case 'q': case 'r':
	  case 's': case 't': case 'u': case 'v': case 'w': case 'x':
	  case 'y': case 'z':
	    break;

	  default:
	    return false;
	}

	while (true) {
	    addString(ch);

	    switch (ch = in.read()) {
	      case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
	      case 'G': case 'H': case 'I': case 'J': case 'K': case 'L':
	      case 'M': case 'N': case 'O': case 'P': case 'Q': case 'R':
	      case 'S': case 'T': case 'U': case 'V': case 'W': case 'X':
	      case 'Y': case 'Z':
		if (lower) {
		    ch = 'a' + (ch - 'A');
		}

	      case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
	      case 'g': case 'h': case 'i': case 'j': case 'k': case 'l':
	      case 'm': case 'n': case 'o': case 'p': case 'q': case 'r':
	      case 's': case 't': case 'u': case 'v': case 'w': case 'x':
	      case 'y': case 'z':

	      case '0': case '1': case '2': case '3': case '4':
	      case '5': case '6': case '7': case '8': case '9':

	      case '.': case '-':

	      case '_': // not officially allowed
		break;

	      default:
		return true;
	    }
	}
    }

    /**
     * Parse an entity reference. [59] 350:17
     */
    private byte parseEntityReference()[] throws IOException {              //??dk
	int pos = strpos;

	if ((ch = in.read()) == '#') {
	    int n = 0;
	    ch = in.read();
	    if ((ch >= '0') && (ch <= '9')) {
		while ((ch >= '0') && (ch <= '9')) {
		    n = (n * 10) + ch - '0';
		    ch = in.read();
		}
		switch (ch) {
		  case '\n':
		    ln++;
		    if ((ch = in.read()) == '\r') {
			ch = in.read();
		    }
		    break;

		  case '\r':
		    ln++;
		    if ((ch = in.read()) == '\n') {  //vm
			ch = in.read();
		    }
		    break;

		  case ';':
		    ch = in.read();
		    break;
		}
		byte data[] = {(byte)(n & 0xFF)};
		return data;
	    }
	    addString('#');
	    if (!parseIdentifier(false)) {
		error("ident.expected");
		strpos = pos;
		byte data[] = {(byte) '&', (byte) '#'}; //vm 970822
		return data;
	    }
	} else if (!parseIdentifier(false)) {
	    byte data[] = {(byte) '&'}; //vm 980722
	    return data;
	}
	switch (ch) {
	  case '\n':
	    ln++;
	    if ((ch = in.read()) == '\r') {
		ch = in.read();
	    }
	    break;

	  case '\r':
	    ln++;
	    if ((ch = in.read()) == '\n') { //vm 
	      ch = in.read();
	    }
	    break;
	  case ';':
	    ch = in.read();
	    break;
	}

	String nm = getString(pos);
	Entity ent = dtd.getEntity(nm);
	if ((ent == null) || !ent.isGeneral()) {
	    String str;
	    if ((props == null) || ((str = props.getProperty(nm)) == null)) {
		error("invalid.entref", nm);
		return new byte[0];
	    }

	    // deprecated 1.1  - jml - 12/10/96.

	    // 1.0 style
//  	    byte b[] = new byte[str.length()];
//  	    str.getBytes(0, b.length, b, 0);
	    // 1.1 style
	    byte b[] = str.getBytes();
	    
	    // deprecated 1.1  - jml - 12/10/96.
	    return b;
	}
	return ent.getData();
    }

    /**
     * Parse a comment. [92] 391:7
     */
    void parseComment() throws IOException {                              //??dk
	while (true) {
	    int c = ch;
	    switch (c) {
	      case '-':
		  //  - jml - 1/30/97.
		  //		if ((ch = in.read()) == '-') {
		if (((ch = in.read()) == '-') && ((ch = in.read()) == '>')){
		    //ch = in.read(); - jml - 1/30/97.
		    return;
		}
		break;

	      case -1:
		error("eof.comment");
		return;

	      case '\n':
		ln++;
		if ((ch = in.read()) == '\r') {
		    ch = in.read();
		}
		break;

	      case '\r':
		ln++;
		if ((ch = in.read()) == '\n') {   //vm
		  ch = in.read();
		}
		c = '\n';
		break;

	      default:
		ch = in.read();
		break;
	    }
	    addString(c);
	}
    }

    /**
     * Parse literal content. [46] 343:1 and [47] 344:1
     */
  void parseLiteral(boolean replace) throws IOException  {                  //??dk

    while (true) {
      int c = ch;
      switch (c) {
      case -1:
	error("eof.literal", stack.elem.getName());
	endTag(true);
	return;

      case '>':
	ch = in.read();
	int i = textpos - (stack.elem.name.length() + 2), j = 0;

	// match end tag
	if ((i >= 0) && (text[i++] == '<') && (text[i] == '/')) {
	  
	  // FIX2 - start - vm 970707
	  //@@ while ((++i < textpos) && (text[i] == stack.elem.name.charAt(j++))) ;
	  while ((++i < textpos) &&
		 (Character.toUpperCase((char)text[i]) == Character.toUpperCase(stack.elem.name.charAt(j++)))) ;
	  // FIX2 - end - vm 970707
	  
	  if (i == textpos) {
	    textpos -= (stack.elem.name.length() + 2);
	    if ((textpos > 0) && (text[textpos-1] == '\n')) {
	      textpos--;
	    }
	    endTag(false);
	    return;
	  }
	}
	break;
	
      case '&':
	byte data[] = parseEntityReference();
	if (textpos + data.length > text.length) {
	  byte newtext[] = new byte[Math.max(textpos + data.length + 128, text.length * 2)];
	  System.arraycopy(text, 0, newtext, 0, text.length);
	  text = newtext;
	}
	System.arraycopy(data, 0, text, textpos, data.length);
	textpos += data.length;
	continue;
	
      case '\n':
	ln++;
	if ((ch = in.read()) == '\r') {
	  ch = in.read();
	}	
	break;
	
      case '\r':
	ln++;
	if ((ch = in.read()) == '\n') { //vm
	  ch = in.read();
	}
	c = '\n';
	break;

      default:
	ch = in.read();
	break;
      }
      
      // output character
      if (textpos == text.length) {
	byte newtext[] = new byte[text.length * 2];
	System.arraycopy(text, 0, newtext, 0, text.length);
	text = newtext;
      }
      text[textpos++] = (byte)(c & 0xFF);
    }
  }

    /**
     * Parse attribute value. [33] 331:1
     */
    String parseAttributeValue(boolean lower) throws IOException {            //??dk
	int delim = -1;

	// Check for a delimiter
	switch(ch) {
	  case '\'':
	  case '"':
	    delim = ch;
	    ch = in.read();
	    break;
	}

	// Parse the rest of the value
	while (true) {
	    int c = ch;

	    switch (c) {
	      case '\n':
		ln++;
		if ((ch = in.read()) == '\r') {
		    ch = in.read();
		}
		if (delim < 0) {
		    return getString(0);
		}
		c = ' ';
		break;

	      case '\r':
		ln++;
		if ((ch = in.read()) == '\n') {  //vm
		    ch = in.read();
		}
		if (delim < 0) {
		    return getString(0);
		}
		c = ' ';
		break;

	      case '\t':
		c = ' ';
	      case ' ':
		ch = in.read();
		if (delim< 0) {
		    return getString(0);
		} 
		break;

	      case '>':
	      case '<':
		if (delim < 0) {
		    return getString(0);
		}
		ch = in.read();
		break;

	      case '\'':
	      case '"':
		ch = in.read();
		if (c == delim) {
		    return getString(0);
		}
		break;
				//  - jml - 1/22/97.
// 	      case '&':
// 		if (delim < 0) {
// 		    ch = in.read();
// 		    break;
// 		}

//  		byte data[] = parseEntityReference();
//  		for (int i = 0 ; i < data.length ; i++) {
//  		    c = data[i];1
//  		    addString((lower && (c >= 'A') && (c <= 'Z')) ? 'a' + c - 'A' : c);
//  		}
// 		continue;
				//  - jml - 1/22/97.

	      case -1:
		return getString(0);

	      default:
		if (lower && (c >= 'A') && (c <= 'Z')) {
		    c = 'a' + c - 'A';
		}
		ch = in.read();
		break;
	    }
	    addString(c);
	}
    }

    /**
     * Parse attribute specification List. [31] 327:17
     */
    Attributes parseAttributeSpecificationList(Element elem) throws IOException {     //??dk
	Attributes atts = null;

	while (true) {
	    skipSpace();

	    switch (ch) {
	      case '/':
	      case '>':
	      case '<':
	      case -1:
		return atts;

	      case '-':
		ch = in.read();
		if ((ch = in.read()) == '-') {	
		    ch = in.read();
		    parseComment();
		    strpos = 0;
		} else {
		    error("invalid.tagchar", "-", elem.getName());
		    ch = in.read();
		}
		continue;
	    }

	    AttributeList att = null;
	    String attname = null;
	    String attvalue = null;

	    if (parseIdentifier(true)) {
		attname = getString(0);
		skipSpace();
		if (ch == '=') {
		    ch = in.read();
		    skipSpace();
		    att = elem.getAttribute(attname);
		    attvalue = parseAttributeValue((att != null) && (att.type != CDATA) && (att.type != NOTATION));
		} else {
		    attvalue = attname;
		    att = elem.getAttributeByValue(attvalue);
		}
	    } else {
		char str[] = {(char)ch};
		error("invalid.tagchar", new String(str), elem.getName());
		return atts;
	    }

	    if (att != null) {
		attname = att.getName();
	    } else  if (elem != dtd.app) {
		error("invalid.tagatt", attname, elem.getName());
	    }

	    // Check out the value
	    if (atts == null) {
		atts = new Attributes();
	    } else if (atts.get(attname) != null) {
		error("multi.tagatt", attname, elem.getName());
	    }
	    if (attvalue == null) {
		attvalue = ((att != null) && (att.value != null)) ? att.value : "";
	    } else if ((att != null) && (att.values != null) && !att.values.contains(attvalue)) {
		error("invalid.tagattval", attname, elem.getName());
	    }
	    atts.append(attname, attvalue);
	}
    }

    /**
     * Parse a start or end tag.
     */
    void parseTag() throws IOException {                                        //??dk
	Element elem = null;
	boolean net = false;
	//FIX1 - start - jml - 5/7/97.
	// ugly, but avoid two calls to getString
	String els = null;
	//FIX1 - end - jml - 5/7/97.
	
	
	switch (ch = in.read()) {
	  case '!':
	    // Parse comment. [92] 391:7
	    switch (ch = in.read()) {
	      case '-':
		while (true) {
		    if ((ch != '-') || ((ch = in.read()) != '-')) {
			char data[] = {(char)ch};
			error("invalid.commentchar", new String(data));
			if ((ch != '\n') && (ch != '\r')) {
			    ch = in.read();
			}
		    } else {
			ch = in.read();
			parseComment();
			handleComment(getString(0));
		    }
		    skipSpace();
		    switch (ch) {
		      case '>':
			ch = in.read();
		      case -1:
			return;
		    }
		}

	      default:
		// REMIND: need to deal with marked sections
		error("invalid.markup");
		while (true) {
		    switch(ch) {
		      case '>':
			ch = in.read();
		      case -1:
			return;
		      case '\n':
			ln++;
			if ((ch = in.read()) == '\r') {
			    ch = in.read();
			}
			break;
		      case '\r':
			ln++;
			if ((ch = in.read()) == '\n') {  //vm
			    ch = in.read();
			}
			break;

		      default:
			ch = in.read();
			break;
		    }
		}
	    }

	  case '/':
	    // parse end tag [19] 317:4
	    switch (ch = in.read()) {
	      case '>':
		ch = in.read();
	      case '<':
		// empty end tag. either </> or </<
		if (recent == null) {
		    error("invalid.shortend");
		    return;
		}
		elem = recent;
		break;

	      default:
		if (!parseIdentifier(true)) {
		    error("expected.endtagname");
		    return;
		}
		switch (ch) {
		  case '>':
		    ch = in.read();
		  case '<':
		    break;

		  default:
		    error("expected", "'>'");
		    break;
		}
		//FIX1 - start - jml - 5/7/97.
		//@@	elem = dtd.getElement(getString(0));
		els = getString(0);
		elem = dtd.findElement(els);
		if(elem == null) {
		    handleIllegalTag(els);
		    return;
		}
		//FIX1 - end - jml - 5/7/97.
		break;
	    }

	    // Ignore RE before end tag
            if ((textpos > 0) && (text[textpos-1] == '\n')) {
		textpos--;
	    }

	    // find the corresponding start tag
	    TagStack sp = stack;
	    while ((sp != null) && (elem != sp.elem)) {
		sp = sp.next;
	    }
	    if (sp == null) {
		error("unmatched.endtag", elem.getName());
		return;
	    }

	    // end tags
	    while (stack != sp) {
		endTag(true);
	    }
	    endTag(false);
	    return;

	  case -1:
	    error("eof");
	    return;
	}

	// start tag [14] 314:1
	if (!parseIdentifier(true)) {
	    elem = recent;
	    if ((ch != '>') || (elem == null)) {
		error("expected.tagname");
		return;
	    }
	} else {
	    // FIX1 - start - jml - 5/7/97.
	    //@@    elem = dtd.getElement(getString(0));
	    els = getString(0);
	    elem = dtd.findElement(els);
	    // FIX1 - end - jml - 5/7/97.
	}

	// Parse attributes
	// FIX1 - start - jml - 5/7/97.
	//@@	Attributes atts = parseAttributeSpecificationList(elem);
	// the start tag and its parameters have been parsed, just leave.
	Attributes atts = null;
	if(elem == null) {
	    // this tag is unknown, skip parameters
	    while(ch != '>') {
		ch = in.read();
		// <FIX> Spif 30/06/2000, fixing endless loop
		if (ch == -1) {
		    error("eof");
		    return;
		}
		// </FIX>
	    }
	}
	else {
	    // this is a real tag, parse its attributes
	     atts = parseAttributeSpecificationList(elem);
	}
	// FIX1 - end - jml - 5/7/97.

	switch (ch) {
	  case '/':
	    net = true;
	  case '>':
	    ch = in.read();
	  case '<':
	    break;

	  default:
	    error("expected", "'>'");
	    break;
	}

	// ignore RE after start tag
	// FIX1 - start - jml - 5/7/97.
	//@@ if (!elem.isEmpty()) {
	if ((elem != null) && (!elem.isEmpty())) {
	// FIX1 - end - jml - 5/7/97.
	    if (ch == '\n') {
		ln++;
		if ((ch = in.read()) == '\r') {
		    ch = in.read();
		}
	    } else if (ch == '\r') {
	      ln++;
	      if ((ch = in.read()) == '\n') { //vm
		ch = in.read();
	      }
	    }
	}
	// FIX1 - start - jml - 5/7/97.
	if(elem == null) {
	    handleIllegalTag(els);
	    return;
	}
	// FIX1 - end - jml - 5/7/97.

	// ensure a legal context for the tag
	try {
	    Tag tag = makeTag(elem, atts);
	    legalTagContext(tag);
	    startTag(tag);
	}
	catch(IOException x) {
	    System.out.println("exiting abnormally");
	    x.printStackTrace(System.out);
	}

	if (!elem.isEmpty()) {
	    switch (elem.getType()) {
	      case CDATA:
		parseLiteral(false);
		break;
	      case RCDATA:
		parseLiteral(true);
		break;
	      default:
		stack.net = net;
		break;
	    }
	}
    }

    /**
     * Parse Content. [24] 320:1
     */
    void parseContent() throws IOException {                //??dk
	while (true) {
	    int c = ch;
	    switch (c) {
	      case '<':
		parseTag();
		continue;

	      case '/':
		ch = in.read();
		if ((stack != null) && stack.net) {
		    // null end tag.
		    endTag(false);
		    continue;
		}
		break;
		
	      case -1:
		return;

	      case '&':
		if (textpos == 0) {
		    if (!legalElementContext(dtd.pcdata)) {
			error("unexpected.pcdata");
		    }
		    if (last.isBlock()) {
			space = false;
		    }
		}
		byte data[] = parseEntityReference();
		if (textpos + data.length + 1 > text.length) {
		    byte newtext[] = new byte[Math.max(textpos + data.length + 128, text.length * 2)];
		    System.arraycopy(text, 0, newtext, 0, text.length);
		    text = newtext;
		}
		if (space) {
		    space = false;
		    text[textpos++] = (byte) ' '; //vm 980722
		}
		System.arraycopy(data, 0, text, textpos, data.length);
		textpos += data.length;
		continue;

	      case '\n':
		ln++;
		if ((ch = in.read()) == '\r') {
		    ch = in.read();
		}
		if ((stack != null) && stack.pre) {
		    break;
		}
		space = true;
		continue;

	      case '\r':
		ln++;
		if ((ch = in.read()) == '\n') { //vm
		    ch = in.read();
		}
		c = '\n';
		if ((stack != null) && stack.pre) {
		    break;
		}
		space = true;
		continue;

	      case '\t':
	      case ' ':
		ch = in.read();
		if ((stack != null) && stack.pre) {
		    break;
		}
		space = true;
		continue;

	      default:
		if (textpos == 0) {
		    if (!legalElementContext(dtd.pcdata)) {
			error("unexpected.pcdata");
		    }
		    if (last.isBlock()) {
			space = false;
		    }
		}
		ch = in.read();
		break;
	    }

	    // enlarge buffer if needed
	    if (textpos + 2 > text.length) {
		byte newtext[] = new byte[text.length * 2];
		System.arraycopy(text, 0, newtext, 0, text.length);
		text = newtext;
	    }

	    // output pending space
	    if (space) {
		text[textpos++] = (byte) ' '; //vm 980722
		space = false;
	    }
	    text[textpos++] = (byte)(c & 0xFF);
	}
    }

    /**
     * Parse an an HTML stream, given a dtd.
     */
    public synchronized void parse(InputStream in, DTD dtd) throws IOException, Exception {  //??dk
      if (in == null) //vm970811 if the input is empty, just dont do anything.
	return;       //vm970811
            
	this.in = in;
	this.dtd = dtd;
	this.ln = 1;

	/*added by Sijtsche de Jong (sy.de.jong@let.rug.nl)
	  Check whether content is HTML or XHTML */

	int ch1, ch2;
	boolean ready = false;
	//String contenttype = new String();

	if (in.markSupported()) {
	    in.mark(in.available());
	    ch1 = in.read();
	    while (ch1 != '<')
		ch1 = in.read();

	    // now it should have found '<'
	    
	    while (!ready) {
		ch1 = in.read();
		ch2 = in.read(); // 'D' or '-' or else it's wrong
		
		if (ch1 != '!') {
		    ready = true;
		} else {
		    if (ch2 == '-') { // comment
			while (ch2 != '<') {
			    ch2 = in.read();
			}
			// here next '<' is found
		    } else if (ch2 == 'D') {
			byte [] bity = new byte[50];
			int got = in.read(bity);
			String str = new String(bity);
			if (str.toLowerCase().indexOf("octype") != -1 &&
			    str.toLowerCase().indexOf("xhtml") != -1) {
			    throw new XMLInputException(null);
			} else {
			    ready = true;
			}
		    } else {
			//error, invalid tag, assume it is normal HTML
			ready = true;
		    }
		}
	    }
	}
	
	in.reset();
	
	
	// end of added part by Sijtsche de Jong
    


	try {
	    try {
		ch = in.read();
		text = new byte[1024];
		str = new char[128];

		parseContent();
		while (stack != null) {
		    endTag(true);
		}
	    } catch (IOException e) {
		errorContext();
		error("exception", e.getClass().getName());
		throw e;
	    } catch (Exception e) {
		errorContext();
		error("exception", e.getClass().getName());
		e.printStackTrace();
		throw e;
	    } catch (ThreadDeath e) {
		errorContext();
		error("exception", e.getClass().getName());
		throw e;
	    }
	} finally {
	    for (; stack != null ; stack = stack.next) {
		handleEndTag(stack.tag);
	    }
	    text = null;
	    str = null;
	    in.close();
	}
    }

    /**
     * Test the parser.
     */
    public static void main(String argv[]) throws IOException, Exception  {   //??dk
	// Load properties
	DTD.props = new Properties();
	try {
	    URL url = Parser.class.getResource("Parser.properties");
	    DTD.props.load(url.openStream());
	} catch (Exception e) {
	    System.out.println("Failed to load properties...");
	    System.exit(1);
	}

	// load user properties
	DTD.props = new Properties(DTD.props);

	Parser p = new Parser();

	int i = 0;
	DTD dtd = null;

	for (; i < argv.length ; i++) {
	    if (argv[i].equals("-verbose")) {
		p.verbose = true;
	    } else if (argv[i].equals("-dtd") && (i+1 < argv.length) && (dtd == null)) {
		System.out.println("name == " + argv[i+1]);
		dtd = DTD.getDTD(argv[++i]);
	    } else if (argv[i].startsWith("-")) {
		System.out.println("invalid argument: " + argv[i]);
		System.exit(1);
	    } else {
		break;
	    }	
	}
	//	p.verbose = true;
	if (dtd == null) {
	  //dtd = DTD.getDTD("html2-net");  // vm 970704
	  dtd = DTD.getDTD("html320");       // vm 970704
	}
	if (p.verbose) {
	    p.out = new HTMLOutputStream(System.out, dtd);
	}

	for (; i < argv.length ; i++) {
	    InputStream in;
	    try {
		if (argv[i].indexOf(':') > 0) {
		    in = new URL(null, argv[i]).openStream();
		} else {
		    in = new java.io.BufferedInputStream(new java.io.FileInputStream(argv[i]));
		}
	    } catch (Exception e) {
		e.printStackTrace();
		System.out.println("failed to open: " + argv[i]);
		continue;
	    }
	    //int tm = System.nowMillis();            //??dk
	    long tm = System.currentTimeMillis();     //??dk
	    System.out.println("----- Trace - 1 ------");
	    p.parse(in, dtd);
	    //tm = System.nowMillis() - tm;           //??dk
	    tm = System.currentTimeMillis() - tm;       //??dk
	    System.out.println("[Parsed " + argv[i] + " in " + tm + "ms]");
	}
    }
}

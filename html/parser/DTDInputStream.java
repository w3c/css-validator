/*
 * @(#)DTDInputStream.java	1.1 95/04/23  
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

import java.io.InputStream;
//import java.io.InputStreamBuffer;
import java.io.ByteArrayInputStream;  //??dk
import java.io.IOException;           //??dk
import java.util.Properties;          //??dk
import java.io.FilterInputStream;
import java.util.Stack;

/**
 * A stream for reading HTML files. This stream takes care
 * of \r\n conversions and parameter entity expansion.
 *
 * @version 	1.1, 23 Apr 1995
 * @author Arthur van Hoff
 * @modified Dmitri Kondratiev for JDK Beta 2 05/01/96 //??dk
 */
final
class DTDInputStream extends FilterInputStream implements DTDConstants {
    DTD dtd;
    Stack stack = new Stack();
    char str[] = new char[64];
    int replace = 0;
    int ln = 1;
    int ch;

    /**
     * Create the stream.
     */
    DTDInputStream(InputStream in, DTD dtd) {
	super(in);
	this.dtd = dtd;
  try {                         //??dk
	  this.ch = in.read();
  } catch (IOException e) {
    System.out.println("DTDInputStream::DTDInputStream : IOException");
  }
    }

    /**
     * Error
     */
    void error(String msg) {
	System.out.println("line " + ln + ": dtd input error: " + msg);
    }

    /**
     * Push a single character
     */
    void push(int ch) {
	byte data[] = {(byte)(ch & 0xFF)};
	//push(new InputStreamBuffer(data));
  push(new ByteArrayInputStream(data));      //??dk
    }


    /**
     * Push an array of bytes.
     */
    void push(byte data[]) {
	if (data.length > 0) {
	    //push(new InputStreamBuffer(data));
      push(new ByteArrayInputStream(data));    //??dk
	}
    }

    /**
     * Push an entire input stream
     */
    void push(InputStream in) {
	stack.push(new Integer(ln));
	stack.push(new Integer(ch));
	stack.push(this.in);
	this.in = in;
  try {                                         //??dk    
  	ch = in.read();
  } catch (IOException e) {
    System.out.println("DTDInputStream::push : IOException");
  }

    }

    /**
     * Read a character from the input. Automatically pop
     * a stream of the stack if the EOF is reached. Also replaces
     * parameter entities.
     * [60] 350:22
     */
    public int read() {
	switch (ch) {
	  case '%': {
    try {                                             //??dk
  	  ch = in.read();
    } catch (IOException e) {
      System.out.println("DTDInputStream::read<1> : IOException");
    }

	    if (replace > 0) {
		return '%';
	    }

	    int pos = 0;
	    while (((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z')) ||
		   ((ch >= '0') && (ch <= '9')) || (ch == '.') || (ch == '-')) {
		str[pos++] = (char)ch;
    try {                                             //??dk
  		ch = in.read();
    } catch (IOException e) {
      System.out.println("DTDInputStream::read<3> : IOException");
    }

	    }
	    if (pos == 0) {
		return '%';
	    }

	    String nm = new String(str, 0, pos);
	    Entity ent = dtd.getEntity(nm);
	    if (ent == null) {
		error("undefined entity reference: " + nm);
		return read();
	    }

	    // Skip ; or RE
	    switch (ch) {
	      case '\r':
		ln++;
	      case ';':
          try {                                       //??dk
		        ch = in.read();
          } catch (IOException e) {
            System.out.println("DTDInputStream::read<2+> : IOException");
          }

		break;
	      case '\n':
		ln++;
    try {                                           //??dk
		  if ((ch = in.read()) == '\r') {
  		    ch = in.read();
		  }
    } catch (IOException e) {
      System.out.println("DTDInputStream::read<2> : IOException");
    }

		break;
	    }

	    // Push the entity.
	    try {
		push(ent.getInputStream());
	    } catch (Exception e) {
		error("entity data not found: " + ent + ", " + ent.getString());
	    }
	    return read();
	  }

	  case '\n':
	    ln++;
      try {                                             //??dk
  	    if ((ch = in.read()) == '\r') {
  		    ch = in.read();
  	    }
      } catch (IOException e) {
        System.out.println("DTDInputStream::read<4> : IOException");
      }

	    return '\n';

	  case '\r':
	    ln++;
      try {                                             //??dk
	      ch = in.read();
      } catch (IOException e) {
        System.out.println("DTDInputStream::read<5> : IOException");
      }

	    return '\n';
	    
	  case -1:
	    if (stack.size() > 0) {
		in = (InputStream)stack.pop();
		ch = ((Integer)stack.pop()).intValue();
		ln = ((Integer)stack.pop()).intValue();
		return read();
	    }
	    return -1;

	  default:
	    int c = ch;
      try {                                             //??dk
	      ch = in.read();
      } catch (IOException e) {
        System.out.println("DTDInputStream::read<6> : IOException");
      }

	    return c;
	}
    }
}

//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.3  2003/07/02 14:39:02  plehegar
 * Removed HttpServletResponse import
 *
 * Revision 1.2  2002/04/08 21:16:38  plehegar
 * New
 *
 */
package org.w3c.css.css;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.w3c.css.util.ApplContext;

/**
 * This class describes how to implements your cascading
 * style sheet parser.
 * 
 * You must implements this interfaces if you want to have
 * a backward compatibilitie with other CSS parser.
 * <p>
 * Typically, it is used like this :
 * <p>
 * <code>
 *  YourParser parser = new YourParser();<br>
 *  parser.parseURL(yourURLDocument, StyleSheetOrigin.USER);<br>
 *  StyleSheet style = parser.getStyleSheet();<br>
 *  // here, i want an HTML document to output<br>
 *  StyleSheetGenerator.setDocumentBase("html.properties");<br>
 *  StyleSheetGenerator generator = new StyleSheetGenerator("foo", 
 *                                                          style,
 *                                                          "foo.css",
 *                                                          2);<br>
 *  generator.print(new PrintStream(System.out));<br>
 * </code>
 *
 * @see org.w3c.css.css.StyleSheetParser
 * @version $Revision$
 */
public interface CssParser {

  /**
   * Reinitialize this parser
   */  
  public abstract void reInit();

  /**
   * Get the style sheet after a parse.
   *
   * @return The resulted style sheet
   */  
  public abstract StyleSheet getStyleSheet();

    /**
     * @param url the URL containing the style sheet
     * @param title the title of the stylesheet
     * @param kind may be a stylesheet or an alternate stylesheet
     * @param media the media to apply this 
     * @param origin the origin of the style sheet
     * @exception IOException an IO error
     */
    public void parseURL(ApplContext ac, URL url, String title, String kind, 
			 String media, int origin);

  /**
   * Parse a STYLE element.
   * The real difference between this method and the precedent
   * is that this method can take a string. The URL is used
   * to resolve import statement and URL statement in the style
   * sheet.
   * <p>
   * For a backward compatibility, <code>parseStyleElement</code> and
   * <code>parseStyleAttribute</code> use a string for the input.
   *
   * @param input the input string.
   * @param url  the URL where the input stream comes from.
   * @param lineno The number line in the source document. 
   *               It is used for error message
   * @deprecated Replaced by parseStyleElement
   * @see #parseStyleElement(InputStream, URL, int)
   */  
  public abstract void parseStyleElement(ApplContext ac, String input, 
					 URL url, int lineno);

  /**
   * Parse a STYLE element.
   * The real difference between this method and the precedent
   * is that this method can take an InputStream. The URL is used
   * to resolve import statement and URL statement in the style
   * sheet.
   *
   * @param input the input stream.
   * @param title the title of the style element
   * @param media the media of the style element
   * @param url  the URL where the input stream comes from.
   * @param lineno The number line in the source document. It is used for error message
   */  
  public abstract void parseStyleElement(ApplContext ac, InputStream input, 
					 String title, String media, URL url,
					 int lineno);

  /**
   * Parser a STYLE attribute.
   * Here, you must generate your own uniq id for the context.
   * After, you can reference this style attribute by the id.
   * <p>
   * <strong>Be careful, the id must be uniq !</strong>
   * <p>
   * For a backward compatibility, <code>parseStyleElement</code> and
   * <code>parseStyleAttribute</code> use a string for the input.
   *
   * @param input the input string.
   * @param id your uniq id to reference this style attribute.
   * @param url  the URL where the input stream comes from.
   * @param lineno The number line in the source document. It is used for error message.
   * @deprecated Replaced by parseStyleAttribute
   * @see #parseStyleAttribute(InputStream, String, URL, int)
   */  
  public abstract void parseStyleAttribute(ApplContext ac, String input, String id, URL url, int lineno);

  /**
   * Parser a STYLE attribute.
   * Here, you must generate your own uniq id for the context.
   * After, you can reference this style attribute by the id.
   * <p>
   * <strong>Be careful, the id must be uniq !</strong>
   *
   * @param input the input stream.
   * @param id your uniq id to reference this style attribute.
   * @param url  the URL where the input stream comes from.
   * @param lineno The number line in the source document. It is used for error message.
   */  
  public abstract void parseStyleAttribute(ApplContext ac, InputStream input, 
					   String id, URL url, int lineno);

}

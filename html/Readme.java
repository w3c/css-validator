/*
 * Readme.java
 *
 * $Id$
 *
 * @author Vincent Mallet (Vincent.Mallet@sophia.inria.fr)
 * @version $Revision$
 */

package html;

import html.parser.*; // imports are usefull for "see also" doc tags
import html.tags.*;
import html.tree.*;

/**
 * <H2>Introduction</H2>
 *
 * This is the <code>html</code> Package Readme. This is 
 * not java code, this is only some docs to help you use this
 * package while using the standard javadoc presentation.<br><br>
 *
 * The <code>html</code> package is a DTD driven HTML parser 
 * designed to parse HTML files following specific DTDs and
 * construct corresponding abstract HTML trees that can be
 * used by applications. The parser can also be used directly
 * to avoid the tree construction if necessary.<BR><BR>
 
 * The HTML parser is composed of five different packages:
 * <DL>

 * <DT><code>html.css</code>
 * <DD><i>Deprecated.</i> This package was used to parse
 * <A HREF="http://w3c1.inria.fr/TR/REC-CSS1">Cascading
 * Style Sheets level 1</A>. It will soon be replaced by the new CSS
 * parser done by <a href="mailto:Philippe.Le_Hegaret@sophia.inria.fr">
 * Philippe Le H&eacute;garet</a>. Actually not so soon because the
 * values cannot be easily extracted from Philippe's parser properties, 
 * so this old-buggy-incorrect css parser will stay there a little 
 * while..
 
 
 * <DT><code>html.parser</code>
 * <DD>This is the actual parser, based on the parser done
 * by <A HREF="mailto:avh@eng.sun.com">Arthur Van Hoff</A>
 * from Sun. It does nothing but the actual parsing of the file.
 
 * <DT><code>html.tags</code>
 * <DD>This is the HtmlParser. It is based on top of the parser
 * raw parser and introduces the building of an Html tree and 
 * the ParserListener technique. This makes the task of using 
 * the Html parser much simpler. It has been written by
 * <a href="mailto:Jean-Michel.Leon@sophia.inria.fr">Jean-Michel 
 * Leon</A>, and has been modified by 
 * <a href="mailto:Vincent.Mallet@sophia.inria.fr"> Vincent Mallet</A>
 * so as to handle all the CSS data properly (using the CSS
 * parser, see above).

 * <DT><code>html.tree</code>
 * <DD>This is the tree built by the HtmlParser. It provides
 * the TreeListener interface so all the tree construction can
 * be followed without modifying or subclassing these classes.

 * <DT><code>html.util</code>
 * <DD>Some utility classes.
 
 * </DL>

 
 * <H2>Using the HTML Parser</H2>
 
 * Using the HTML parser in your own code should not be 
 * something too difficult. You have two ways of doing it:
 * using the listener technique (recommanded), and subclassing
 * the raw parser classes (much work in perspective). 
 * Subclassing the raw parser implies the abstract tree will 
 * not be constructed during parsing.
 
 * <P><U><I>Using the listeners</I></U>

 * <P>This is the easiest way of using the parser. The classes
 * to look at are <code>html.tags.HtmlParser</code>, 
 * <code>html.tags.HtmlParserListener</code> and
 * <code>html.tree.TreeListener</code>.
 
 * <UL>

 * <LI>First create an instance of the HtmlParser, specifying what
 * DTD to use and what file to parse. The DTDs should be located
 * in the html/parser/dtds directory.
 
 * <LI>Add one (or more) <code>HtmlParserListener</code> to the 
 * parser. This <code>HtmlParserListener</code> will be notified
 * of the tree creation and of the end of parsing.


 * <LI>If you want to use the HTML tree you can do it at two
 * different moments@@CHECKME:
 * <UL>
 * <LI> After the tree has been built. This is the simplest way to
 * go. When the parser is finished, it invokes the method 
 * <code>notifyEnd()</code> of the <code>HtmlParserListener</code>
 * interface and the only parameter given is the root of the tree.
 * Just walk the tree the way you like.
 * <LI> While the tree is being constructed. To do this, you have
 * to register a TreeListener when the parser notifies of the creation
 * of the tree root. The TreeListener must be added to the tree root
 * given as a parameter to the <code>notifyCreateRoot</code> method
 * of the <code>HtmlParserListener</code> interface.
 * </UL>
 * </UL>

 * <P>Example 1: here's some really simple code that shows how to 
 * start the parser in the current thread and parse the content of 
 * a web page. The HTML tree built is used at the end of parsing 
 * only. See next example to see how to use it while it's being 
 * constructed.

 * <P><BlockQuote><Pre>
 *
 * public class Sample implements HtmlParserListener { <br>
 * 
 *  public Sample() {
 *    String     globaldtd = "html4";
 *    String     urlname   = "http://www.w3.org/";
 *    HtmlParser parser;<br>
 *
 *    try {
 *      parser = new HtmlParser(globaldtd, urlname);
 *    }
 *    catch(ParserException e) {
 *      // The parser could not initialize... 
 *      // do whatever is appropriate 
 *      return;
 *    } <br>
 * 
 *    // add a parser listener - us
 *    parser.addParserListener(this);<br>
 *
 *    // parse the url in the current thread 
 *    parser.run();<br>
 *
 *    // that's all!
 *  }<br>
 * 
 *  public static void main(String args[]) {
 *    new Sample();
 *  }<br><br>
 *  
 * 
 *  // implements HtmlParserListener<br>
 * 
 *  public void notifyCreateRoot(URL url, HtmlTag root) { 
 *    // invoked when the root of the Html tree is created. 
 *  }<br>
 * 
 *  public void notifyEnd(HtmlTag root) { 
 *    // invoked when the parser has finished the job.
 *    System.out.println("Wow - parsed the file!  root=" + root);
 *  }<br>
 *
 *  public void notifyConnection(URLConnection cnx) { }
 *  public void notifyFatalError(HtmlTag root, Exception x, String s) { }
 *  public void notifyActivity(int a, long b) { }<br>
 * 
 * }  
 *
 * </Pre></BlockQuote>
 
 * <P>Example 2: [Another example here]

 * <P><I>Subclassing the parser</I>
 * <P>[Fill Me]
 * <P>[Example here]
 
 * <H2>Copyrights</H2>
 * <P>[Fill Me]

 * @see parser
 * @see HtmlParserListener
 * @see TreeListener 
 * @see HtmlTree
 * 
 * @author Vincent Mallet (Vincent.Mallet@sophia.inria.fr)
 *
 * @version $Revision$
 */
public abstract class Readme {
  /* This class intentionaly left empty */

  /**
   * No, don't read me.. Read the Class description instead :)
   */
  private Readme() { }
}

/*
 * HtmlEngine
 *
 * $Id$
 *
 * @author Vincent Mallet (Vincent.Mallet@sophia.inria.fr)
 *
 */

package html.engine;

import java.util.*;
import java.net.*;

// import browser.aural.*;
// import browser.gui.*;

import html.parser.*;
import html.tags.*;

/**
 * The <code>HtmlEngine</code> class implements the basics of a little browser.
 * It fully controls the Html Parser and can open documents. It actually does
 * not many things but notify all <code>HtmlEngineListener</code>
 *
 * @see HtmlEngineListener
 * @author Vincent Mallet  (Vincent.Mallet@sophia.inria.fr)
 * @version $Revision$
 */
public class HtmlEngine {
  static final int PARSER_PRIORITY    = Thread.NORM_PRIORITY-1;


  HtmlParser parser;
  private Thread    parseThread = null;
  
  Vector listeners              = new Vector(1, 1);
      
  static String     file        = null;
  static String     globaldtd   = System.getProperty("dtd", "loose");
  static boolean    gui         = true; // do we want a gui ? (see -nogui)
  static boolean    audio       = true; // do we want aural ? (see -noaudio)

  static boolean    debug       = false;

  static Properties sysProps    = System.getProperties();


  /**
   * Launch the HTML Engine: initialize all listeners and open the
   * first document.
   */
  public void launch() {
    for(int i = 0; i < listeners.size(); i++) {
      HtmlEngineListener l = (HtmlEngineListener) listeners.elementAt(i);
      l.initialize(this);
    }
    
    openDocument(file);
  }
  

  /**
   * Open the document pointed to by <code>name</code>.
   * See the <code>openDocument(URL url)</code>.
   * @see openDocument
   */
  public void openDocument(String name) {
    URL url;
    try {
      url = new URL(null, name);
    }
    catch(Exception x) {
      x.printStackTrace();
      return;
    }

    openDocument(url);
  }


  /**
   * Notifies all the listeners that a new document is going to
   * be opened, and launch the parser in a separate thread.
   * @param url the URL pointing to the document to be opened.
   */
  public void openDocument(URL url) {
    try {
      parser = new HtmlParser(null, globaldtd, "" + url);
    }
    catch(ParserException e) {
      e.printStackTrace();
      return;
    }
    
    for(int i = 0; i < listeners.size(); i++) {
      HtmlEngineListener l = (HtmlEngineListener) listeners.elementAt(i);
      l.openDocument(url, parser);
    }
    
    parseThread = new Thread(parser);
    parseThread.setPriority(PARSER_PRIORITY);
    parseThread.start();
  }
  

  /**
   * Notifies all listeners that the engine is shutting down, and perform the 
   * shut down.
   */

  public synchronized void terminate() {
    for(int i = 0; i < listeners.size(); i++) {
      HtmlEngineListener l = (HtmlEngineListener) listeners.elementAt(i);
      l.terminate();
    }

    System.exit(0);
  }

  
  
  /**
   * Main method. Used to run the engine.
   */
    /*
  public static void main(String args[]) {
    parseArgs(args);

    HtmlEngine engine = new HtmlEngine();
    
    if (gui) {
      HtmlFrame hf = HtmlFrame.openFrame("HtmlFrame", globaldtd);
      engine.addEngineListener(hf);
    }
      
    if (audio)
      engine.addEngineListener(new AuralHtmlParserListener());
    
    engine.launch();
  }
    */

  /**
   *
   */

  static private void parseArgs(String args[]) {
    for(int i = 0 ; i < args.length; i++) {
      
      if(args[i].startsWith("-tree")) {
	
      }
      else if (args[i].startsWith("-nogui")) {
	gui = false;
      }
      else if (args[i].startsWith("-noaudio")) {
	audio = false;
      }
      else if (args[i].equals("-v")) {
	sysProps.put("html.parser.DTDParser.verbose", "true");
	sysProps.put("html.tags.verbose", "true");
      }
      else if (args[i].startsWith("-debug")) {
	debug = true;
      }
      else if (args[i].startsWith("-dp")) {
	sysProps.put("html.parser.debug", "true");
      }
      else if (args[i].startsWith("-dC")) {
	sysProps.put("CSS.StyleSheet.debug", "true");
      }
      else if (args[i].startsWith("-dS")) {
	System.out.println("speech should be debugged");
	sysProps.put("speech.debug", "true");
      }
      else if (args[i].startsWith("-d")) {
	sysProps.put("html.parser.debug", "true");
	sysProps.put("html.aural.debug", "true");
	sysProps.put("html.tree.debug", "true");
	sysProps.put("html.tags.debug", "true");
	sysProps.put("speech.debug", "true");
	sysProps.put("CSS.StyleSheet.debug", "true");	
      }
      else if(args[i].startsWith("-dtd")) {
	i++;
	globaldtd = args[i];
      }
      else {
	file = args[i];
	if(file.indexOf(':') == -1 ) {
	  file = "file://" +
	    System.getProperty("user.dir") + "/" + file;
	}
      }
    }
  }
  

  /**
   * Add a listener for this engine. The listener will be notified 
   * of various tasks performed by the engine.
   * @param listener the listener to be added.
   */ 

  public void addEngineListener(HtmlEngineListener listener) {
    listeners.addElement(listener);
  }

  
  /**
   * Remove a HtmlEngineListener from the list of listeners.
   * @param listener the HtmlEngineListener to remove from the list.
   */

  public void removeEngineListener(HtmlEngineListener listener) {
    listeners.removeElement(listener);
    listeners.trimToSize();
  }  
}

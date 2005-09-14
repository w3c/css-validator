/**
 * HtmlEngineListener
 *
 * @author Vincent Mallet (Vincent.Mallet@sophia.inria.fr)
 *
 * $Id$
 *
 * @Revision $Revision$
 */


package html.engine;

import java.net.*;

import html.tags.*;

/**
 * An object of class <code>HtmlEngineListener</code> is notified
 * of actions taken by an <code>HtmlEngine</code>.

 * @see HtmlEngine
 *
 * @author Vincent Mallet  (Vincent.Mallet@sophia.inria.fr)
 * @version $Revision$
 */

public interface HtmlEngineListener {

  /**
   * Initialize this engine listener. Invoked when the Html engine is
   * booting up.
   * @param engine the engine that will be listened to.
   */

  public void initialize(HtmlEngine engine);


  /**
   * Invoked when the Html Engine is about to perform a shutdown.
   */

  public void terminate();


  /**
   * Invoked when the engine wants to open a new document.
   * @param url the url that is being opened
   * @param parser the parser used to open the document.
   */

  public void openDocument(URL url, HtmlParser parser);

}

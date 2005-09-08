//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

import org.w3c.css.parser.AtRule;
import org.w3c.css.parser.AtRuleMedia;
import org.w3c.css.parser.AtRulePage;
import org.w3c.css.parser.CssError;
import org.w3c.css.parser.CssFouffa;
import org.w3c.css.parser.CssParseException;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssValidatorListener;
import org.w3c.css.parser.Errors;
import org.w3c.css.parser.analyzer.TokenMgrError;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.selectors.IdSelector;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;

/**
 * @version $Revision$
 */
public final class StyleSheetParser 
    implements CssValidatorListener, CssParser {

    CssFouffa  cssFouffa;
    StyleSheet style = new StyleSheet();

    public void reInit() {
	style = new StyleSheet();
    }
    
    public StyleSheet getStyleSheet() {
	return style;
    }
 
    public void notifyErrors(Errors errors) {
	style.addErrors(errors);
    }
    
    public void notifyWarnings(Warnings warnings) {
	style.addWarnings(warnings);
    }
    
    /**
     * Adds a vector of properties to a selector.
     *
     * @param selector     the selector
     * @param declarations Properties to associate with contexts
     */  
    public void handleRule(ApplContext ac, CssSelectors selector, Vector properties) {
	if (selector.getAtRule() instanceof AtRulePage) {
	    style.remove(selector);
	}
	for (Enumeration e2 = properties.elements();e2.hasMoreElements();) {
	    CssProperty property = (CssProperty) e2.nextElement();
	    property.setSelectors(selector);
	    style.addProperty(selector, property);
	}
    }

    // part added by Sijtsche de Jong

    public void addCharSet(String charset) {
	style.addCharSet(charset);
    }

    public void newAtRule(AtRule atRule) {
	style.newAtRule(atRule);
    }

    public void endOfAtRule() {
	style.endOfAtRule();	
    }

    public void setImportant(boolean important) {
	style.setImportant(important);
    }

    public void setSelectorList(Vector selectors) {
	style.setSelectorList(selectors);
    }

    public void setProperty(Vector properties) {
	style.setProperty(properties);
    }

    public void endOfRule() {
	style.endOfRule();
    }

    public void removeThisRule() {
	style.removeThisRule();
    }

    public void removeThisAtRule() {
	style.removeThisAtRule();
    }

    //end of part added by Sijtsche de Jong

    /**
     * Handles an at-rule.
     *
     * <p>The parameter <code>value</code> can be :
     * <DL>
     * <DT>CssString
     * <DD>The value coming from a string.
     * <DT>CssURL
     * <DD>The value coming from an URL.
     * <DT>Vector
     * <DD>The value is a vector of declarations (it contains properties).
     *     This feature is not legal, so be careful.
     * </DL>
     *
     * @param ident The ident for this at-rule (for example: 'font-face')
     * @param string The string representation if this at-rule
     */  
    public void handleAtRule(ApplContext ac, String ident, String string) {
	style.getWarnings().addWarning(new Warning(cssFouffa.getSourceFile(),
						   cssFouffa.getLine(),
						   "at-rule",
						   2,
						   ident,
						   string, ac));
	//stylesheet.addAtRule(atRule);
    }
    
    /**
     * @param url the URL containing the style sheet
     * @param title the title of the stylesheet
     * @param kind may be a stylesheet or an alternate stylesheet
     * @param media the media to apply this 
     * @param origin the origin of the style sheet
     * @exception IOException an IO error
     */
    public void parseURL(ApplContext ac, URL url, String title, 
			 String kind, String media, 
			 int origin) {
	if (Util.onDebug) {
	    System.err.println( "StyleSheet.parseURL(" + url + ", " 
				+ title + ", "
				+ kind + ", " + media + ", "
				+ origin + ")" );
	}
	if (kind != null) {
	    kind = kind.trim().toLowerCase();
	    if (!kind.equals("stylesheet")) {
		return;
	    }
	}
	
	try {
	    ac.setOrigin(origin);
//	    if (cssFouffa == null) {
	    cssFouffa = new CssFouffa(ac, url);
	    cssFouffa.addListener(this);
//	    } else {
//		cssFouffa.ReInit(ac, url);
//	    }

	    //	    cssFouffa.setResponse(res);

	    // removed plh 2001-03-08
	    // cssFouffa.setOrigin(origin);
	    //	    cssFouffa.setDefaultMedium(defaultmedium);
	    //	    cssFouffa.doConfig();
	    if (media == null) {
		if (ac.getMedium() == null) {
		    media = "all";
		} else {
		    media = ac.getMedium();
		}
	    }
	    AtRuleMedia m = new AtRuleMedia();
	    try {
		StringTokenizer tokens = new StringTokenizer(media, ",");
		while (tokens.hasMoreTokens()) {
		    m.addMedia(tokens.nextToken().trim(), ac);
		}
		cssFouffa.setAtRule(m);
	    } catch (org.w3c.css.util.InvalidParamException e) {
		Errors er = new Errors();
		er.addError(new org.w3c.css.parser.CssError(url.toString(), 
							    -1, e));
		notifyErrors(er);
		return;
	    }
	    cssFouffa.parseStyle();
	} catch (Exception e) {
	    Errors er = new Errors();
	    er.addError(new org.w3c.css.parser.CssError(url.toString(), 
							-1, e));
	    notifyErrors(er);
	}
    }
    
    /**
     * Parse a style element. The Style element always comes from the user
     *
     * @param input the inputStream containing the style data
     * @param url the name of the file the style element was read in.
     * @exception IOException an IO error
     */
    public void parseStyleElement(ApplContext ac, InputStream input,
				  String title, String media, 
				  URL url, int lineno) {
	if (Util.onDebug) {
	    System.err.println("StyleSheet.parseStyleElement(" + title + ", " 
			       + media + ", " + url 
			       + "," + lineno + ")" );
	}
	try {

//	    if (cssFouffa == null) {
		cssFouffa = new CssFouffa(ac, input, url, lineno);
		cssFouffa.addListener(this);
//	    } else {
//		cssFouffa.ReInit(ac, input, url, lineno);
//	    } 
	    
	    //	    cssFouffa.setResponse(res);
	    //	    cssFouffa.setDefaultMedium(defaultmedium);
	    //	    cssFouffa.doConfig();

	    if (media == null) {
		media = "all";
	    }

	    AtRuleMedia m = new AtRuleMedia();
	    try {
		m.addMedia(media, ac);
		cssFouffa.setAtRule(m);
	    } catch (org.w3c.css.util.InvalidParamException e) {
		Errors er = new Errors();
		er.addError(new org.w3c.css.parser.CssError(url.toString(), 
							    -1, e));
		notifyErrors(er);
		return;
	    }
	    cssFouffa.parseStyle();
	} catch (IOException e) {
	    Errors er = new Errors();
	    er.addError(new org.w3c.css.parser.CssError(url.toString(), 
							-1, e));
	    notifyErrors(er);
	} catch(TokenMgrError e) {
	    Errors er = new Errors();	    
	    er.addError(new org.w3c.css.parser.CssError(url.toString(), e.getErrorLine(), new CssParseException(new Exception(e))));
	    notifyErrors(er);
	} catch(RuntimeException e) {
	    Errors er = new Errors();	    
	    er.addError(new org.w3c.css.parser.CssError(url.toString(), cssFouffa.getLine(), new CssParseException(e)));
	    notifyErrors(er);
	}
    }
    
    /**
     * @param input the inputStream containing the style data
     * @param url   the name of the file the style element was read in.
     * @exception IOException an IO error
     * @deprecated Replaced by parseStyleElement
     * @see #parseStyleElement(InputStream, URL, int)
     */
    public void parseStyleElement(ApplContext ac, String input, URL url, int lineno) {
	parseStyleElement(ac, new ByteArrayInputStream(input.getBytes()), 
			  null, null, url, lineno);
    }
    
    /**
     * Parse some declarations. All declarations always comes from the user
     *
     * @param input the inputStream containing the style data
     * @param id the uniq id
     * @param filename the name of the file the style element was read in.
     * @exception IOException an IO error
     */
    public void parseStyleAttribute(ApplContext ac, InputStream input, String id, 
				    URL url, int lineno) {
	lineno--; // why ?!?!
	if (Util.onDebug) {
	    System.err.println("StyleSheet.parseStyleAttribute(" + id + "," 
			       + url + "," + lineno + ")" );
	}
	
	try {
//	    if (cssFouffa == null) {
		cssFouffa = new CssFouffa(ac, input, url, lineno);
		cssFouffa.addListener(this);
//	    } else
//		cssFouffa.ReInit(ac, input, url, lineno);
	    CssSelectors selector = new CssSelectors(ac);

	    try {
		AtRuleMedia media = new AtRuleMedia();
		media.addMedia("all", ac);
		cssFouffa.setAtRule(media);
	    } catch (InvalidParamException e) {} //ignore

	    try {
		selector.addId(new IdSelector(id.substring(1)));; 
	    } catch  (InvalidParamException e) {
		style.removeThisRule();
		ac.getFrame().addError(new CssError(e));
	    }
	    cssFouffa.parseDeclarations(selector);
	} catch (IOException e) {
	    Errors er = new Errors();
	    er.addError(new org.w3c.css.parser.CssError(url.toString(), 
							-1, e));
	    notifyErrors(er);
	}
    }
    
    /**
     * @param input the inputStream containing the style data
     * @param id the uniq id
     * @param url the name of the file the style element was read in.
     * @exception IOException an IO error
     * @deprecated Replaced by parseStyleAttribute
     * @see #parseStyleAttribute(InputStream, URL, int)
     */
    public void parseStyleAttribute(ApplContext ac, String input, String id, 
				    URL url, int lineno) {
	parseStyleAttribute(ac, new ByteArrayInputStream(input.getBytes()), 
			    id, url, lineno);
    }
    
    public void setStyle(Class style) {
	cssFouffa.setStyle(style);
    }
    
}

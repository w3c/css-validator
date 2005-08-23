//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.3  2002/07/12 20:36:25  plehegar
 * s/System.out/System.err/
 *
 * Revision 1.2  2002/04/08 21:19:15  plehegar
 * New
 *
 * Revision 2.2  1997/09/08 13:35:45  plehegar
 * Added level
 *
 * Revision 2.1  1997/08/08 15:51:50  plehegar
 * Nothing
 *
 */
package org.w3c.css.util;

import org.w3c.css.parser.CssSelectors;
import org.w3c.css.properties.css1.CssProperty;

/**
 * This class is use to manage all warning every where
 * @version $Revision$
 */
public class Warning {
    String sourceFile;
    int hashSource;
    int line;
    CssSelectors selector;
    String warningMessage;
    
    /**
     * Create a new Warning with message parameters.
     *
     * @param sourceFile the source file
     * @param line the line number in the source file
     * @param warningMessage the warning message to find in the properties file
     * @param level the warning level
     * @param message1 the first message to add
     * @param message2 the second message to add
     *
     * @see org.w3c.css.util.Messages
     */
    public Warning(String sourceFile, int line, String warningMessage, int level,
		   String message1, String message2, ApplContext ac) {
	this.sourceFile = sourceFile;
	this.hashSource = sourceFile.hashCode() % 100;
	this.line = line;
	this.warningMessage = warm(warningMessage, message1, message2, ac);
	this.line = getLevel(warningMessage, level, ac) + (line * 10); 
    }  
    
    /**
     * Create a new Warning.
     *
     * @param sourceFile the source file
     * @param line the line number in the source file
     * @param warningMessage the warning message to find in the properties file
     * @param level the warning level
     *
     * @see org.w3c.css.util.Messages
     */
    public Warning(String sourceFile, int line, 
		   String warningMessage, int level, ApplContext ac) {
	this(sourceFile, line, warningMessage, level, "", "", ac);
    }  
    
    /**
     * Create a new Warning with a property and insert two messages inside.
     *
     * @param property The property where the warning came
     * @param warningMessage The warning message to find in the properties file
     * @param level the warning level
     * @param message1 the first message to add
     * @param message2 the second message to add
     *
     * @see org.w3c.css.util.Messages
     */
    public Warning(CssProperty property, String warningMessage, int level,
		   String message1, String message2, ApplContext ac) {
	this.sourceFile = property.getSourceFile();
	if (sourceFile != null) {
	    this.hashSource = sourceFile.hashCode() % 100;
	}
	this.warningMessage = warm(warningMessage, message1, message2, ac);
	this.line = getLevel(warningMessage, level, ac) 
	    + (property.getLine() * 10); 
    }
    
    /**
     * Create a new Warning with a property.
     * <P>Be careful ! Be sure that all informations in your property is
     * available.
     *
     * @param property The property where the warning came
     * @param warningMessage The warning message to find in the properties file
     * @param level the warning level
     *
     * @see org.w3c.css.util.Messages
     * @see org.w3c.css.properties.css1.CssProperty#setInfo
     */
    public Warning(CssProperty property, String warningMessage, int level, 
		   ApplContext ac) {
	this(property, warningMessage, level, 
	     property.getPropertyName(), "", ac);
	this.selector = property.getSelectors();
    }
    
    /**
     * Create a new Warning with a property and insert an other property name
     * inside.
     *
     * @param property The property where the warning came
     * @param warningMessage The warning message to find in the properties file
     * @param level the warning level
     * @param property2 The property in conflicts with the first
     *
     * @see org.w3c.css.util.Messages 
     */
    public Warning(CssProperty property, String warningMessage, int level,
		   CssProperty property2, ApplContext ac) {
	this(property, warningMessage, level, 
	     property.getPropertyName(), property2.getPropertyName(), ac);
	this.selector = property.getSelectors();
    }
    
    /**
     * Get the source file
     */  
    public String getSourceFile() {
	return sourceFile;
    }
    
    /**
     * Get the line number.
     */  
    public int getLine() {
	return line / 10;
    }
    
    /**
     * Get the message.
     */  
    public String getWarningMessage() {
	return warningMessage;
    }
    
    /**
     * Get the warning level.
     */  
    public int getLevel() {
	return line % 10;
    }
    
    /**
     * Get the context.
     */  
    public CssSelectors getContext() {
	return selector;
    }
    
    public int getInternalOrder() {
	return (hashSource * 100000) + line;
    }
    
    /**
     * debug trace
     */
    public void dump() {
	System.err.println( getSourceFile() );
	System.err.println( getLine() );
	System.err.println( getWarningMessage() );
	System.err.println( getLevel() );
    }
    
    private String warm(String warning, String arg1, String arg2, 
			ApplContext ac) {
	String str = ac.getMsg().getWarningString(warning);
	if (str == null) {
	    return "can't find the warning message for " + warning;
	} else {
	    // replace all parameters.
	    for (int i = 0; (i = str.indexOf("%s", i)) >= 0 ; ) {
		StringBuffer stb = new StringBuffer(str.substring(0, i));
		str = stb.append(arg1).append(str.substring(i+2)).toString();
		arg1 = arg2;
	    }
	    return str;
	}
    }
    
    private int getLevel(String warning, int defaultLevel, ApplContext ac) {
	String str = ac.getMsg().getWarningLevelString(warning);
	if (str == null)
	    return defaultLevel;
	else {
	    try {
		int level = Integer.parseInt(str);
		if (level > 9 || level < 0) {
		    return defaultLevel;
		}
		return level;
	    } catch (Exception e) {
		return defaultLevel;
	    }
	}
    }
    
}

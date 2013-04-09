//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.util;

import org.w3c.css.parser.CssSelectors;
import org.w3c.css.properties.css.CssProperty;

/**
 * This class is use to manage all warning every where
 *
 * @version $Revision$
 */
public class Warning implements Comparable<Warning> {
    String sourceFile;
    int hashSource = 0;
    int level = 0;
    int line = 0;
    CssSelectors selector;
    String warningMessage;
	String warningType = null;

    /**
     * Create a new Warning with message parameters.
     *
     * @param sourceFile     the source file
     * @param line           the line number in the source file
     * @param warningMessage the warning message to find in the properties file
     * @param level          the warning level
     * @param messages       the array of messages to add
     * @see org.w3c.css.util.Messages
     */
    public Warning(String sourceFile, int line, String warningMessage,
                   int level, String[] messages, ApplContext ac) {
        this.sourceFile = sourceFile;
        this.hashSource = sourceFile.hashCode() % 100;
        this.line = line;
		this.warningType = warningMessage;
        this.warningMessage = warn(warningMessage, messages, ac);
        this.level = getLevel(warningMessage, level, ac);
    }

    /**
     * Create a new Warning.
     *
     * @param sourceFile     the source file
     * @param line           the line number in the source file
     * @param warningMessage the warning message to find in the properties file
     * @param level          the warning level
     * @see org.w3c.css.util.Messages
     */
    public Warning(String sourceFile, int line,
                   String warningMessage, int level, ApplContext ac) {
        this(sourceFile, line, warningMessage, level, null, ac);
    }

    /**
     * Create a new Warning with a property and insert two messages inside.
     *
     * @param property       The property where the warning came
     * @param warningMessage The warning message to find in the properties file
     * @param level          the warning level
     * @param message1       the first message to add
     * @param message2       the second message to add
     * @see org.w3c.css.util.Messages
     */
    public Warning(CssProperty property, String warningMessage, int level,
                   String message1, String message2, ApplContext ac, int i) {
        this.sourceFile = property.getSourceFile();
        if (sourceFile != null) {
            this.hashSource = sourceFile.hashCode() % 100;
        }
		this.warningType = warningMessage;
        this.warningMessage = warn(warningMessage, new String[]{message1,
                message2}, ac);
        this.level = getLevel(warningMessage, level, ac);
        this.line = property.getLine();
    }

    /**
     * Create a new Warning with a property and insert n message(s) inside.
     *
     * @param property       The property where the warning came
     * @param warningMessage The warning message to find in the properties file
     * @param level          the warning level
     * @param messages       the list of messages to add
     * @see org.w3c.css.util.Messages
     */
    public Warning(CssProperty property, String warningMessage, int level,
                   String[] messages, ApplContext ac) {
        this.sourceFile = property.getSourceFile();
        if (sourceFile != null) {
            this.hashSource = sourceFile.hashCode() % 100;
        }
		this.warningType = warningMessage;
        this.warningMessage = warn(warningMessage, messages, ac);
        this.level = getLevel(warningMessage, level, ac);
        this.line = property.getLine();
    }

    /**
     * Create a new Warning with a property.
     * <P>Be careful ! Be sure that all informations in your property is
     * available.
     *
     * @param property       The property where the warning came
     * @param warningMessage The warning message to find in the properties file
     * @param level          the warning level
     * @see org.w3c.css.util.Messages
     * @see org.w3c.css.properties.css.CssProperty#setInfo
     */
    public Warning(CssProperty property, String warningMessage, int level,
                   ApplContext ac) {
        this(property, warningMessage, level,
                new String[]{property.getPropertyName()}, ac);
        this.selector = property.getSelectors();
    }

    /**
     * Create a new Warning with a property and insert an other property name
     * inside.
     *
     * @param property       The property where the warning came
     * @param warningMessage The warning message to find in the properties file
     * @param level          the warning level
     * @param property2      The property in conflicts with the first
     * @see org.w3c.css.util.Messages
     */
    public Warning(CssProperty property, String warningMessage, int level,
                   CssProperty property2, ApplContext ac) {
        this(property, warningMessage, level,
                new String[]{property.getPropertyName(),
                        property2.getPropertyName()}, ac);
        this.selector = property.getSelectors();
    }

    /**
     * Compares this <tt>Warning</tt> instance with another.
     *
     * @param w the <tt>Warning</tt> instance to be compared
     * @see Comparable
     */
    public int compareTo(Warning w) {
        int wo = w.getInternalOrder();
        int o = getInternalOrder();
        if (wo == o) {
            return 0;
        }
        if (o < wo) {
            return -1;
        }
        return 1;
    }

    /**
     * Get the source file
     */
    public String getSourceFile() {
        return sourceFile;
    }

    /**
     * Get the source file
     */
    public String getSourceFileEscaped() {
        return Messages.escapeString(sourceFile);
    }

    /**
     * Get the line number.
     */
    public int getLine() {
        return line;
    }

    /**
     * Get the message.
     */
    public String getWarningMessage() {
        return warningMessage;
    }

	/**
	 * Get the warning type
	 */
	public String getWarningType() {
		return warningType;
	}

    /**
     * Get the message.
     */
    public String getWarningMessageEscaped() {
        return Messages.escapeString(warningMessage);
    }

    /**
     * Get the warning level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Get the context.
     */
    public CssSelectors getContext() {
        return selector;
    }

    public int getInternalOrder() {
        return (100000 * hashSource + 10 * line + level);
    }

    /**
     * debug trace
     */
    public void dump() {
        System.err.println(getSourceFile());
        System.err.println(getLine());
        System.err.println(getWarningMessage());
        System.err.println(getLevel());
    }

    private String warn(String warning, String[] args, ApplContext ac) {
        String str = ac.getMsg().getWarningString(warning);
        int j;
        if (str == null) {
            return "can't find the warning message for " + warning;
        } else {
            // replace all parameters.
            if (args != null) {
                StringBuilder sb = new StringBuilder();
                int idx = 0;
                String[] msg_parts = str.split("%s", -1);

                sb.append(msg_parts[0]);
                for (int i = 1; i < msg_parts.length; i++) {
                    if (idx < args.length) {
                        sb.append(args[idx++]);
                    } else {
                         // TODO report error
                        System.err.println("*** WARNING ISSUE: "+warning);
                        System.err.println("*** WARNING ISSUE: "+ac.getMsg().getWarningString(warning));
                        System.err.println("*** WARNING ISSUE: got "+args.length+" args entries");
                    }
                    sb.append(msg_parts[i]);
                }
                // and add the last part
                return sb.toString();
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

//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.5  2005/08/08 13:18:11  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.4  2004/11/19 20:26:06  sijtsche
 * error message linenr bug fixed
 *
 * Revision 1.3  2002/08/19 07:23:08  sijtsche
 * compile bug fixed: getLine erroneous?
 *
 * Revision 1.2  2002/04/08 21:24:12  plehegar
 * New
 *
 * Revision 2.1  1997/08/08 15:51:49  plehegar
 * Nothing
 *
 */

package org.w3c.css.parser;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;

/**
 * @version $Revision$
 */
public class Frame {

    public  ApplContext ac;
    private Errors errors;
    private Warnings warnings;
    private CssFouffa cssFouffa;

    private String sourceFile;
    private int line;

    /**
     * Create a new Frame.
     *
     * @param cssFouffa  The current parser.
     * @param sourceFile The name of the source file.
     */
    public Frame(CssFouffa cssFouffa, String sourceFile) {
    this.sourceFile = sourceFile;
    this.cssFouffa = cssFouffa;
    errors = new Errors();
    warnings = new Warnings();
    }

    /**
     * Create a new Frame with a line number.
     *
     * @param cssFouffa  The current parser.
     * @param sourceFile The name of the source file.
     * @param beginLine  The begin line
     */
    public Frame(CssFouffa cssFouffa, String sourceFile, int beginLine) {
    this.sourceFile = sourceFile;
    this.cssFouffa = cssFouffa;
    line = beginLine;
    errors = new Errors();
    warnings = new Warnings();
    }

    /**
     * Adds an error to this frame.
     *
     * @param error The new error.
     */
    public void addError(CssError error) {
    error.sourceFile = getSourceFile();
    error.line = getLine();
    errors.addError(error);
    }

    /**
     * Returns all errors.
     */
    public Errors getErrors() {
    return errors;
    }

    /**
     * Adds a warning to this frame.
     *
     * @param warningMessage the warning message
     *                       (see org.w3c.css.util.Messages.properties).
     * @see                  org.w3c.css.util.Warning
     */
    public void addWarning(String warningMessage) {
    warnings.addWarning(new Warning(getSourceFile(), getLine(),
                    warningMessage, 0, ac));
    }

    /**
     * Adds a warning to this frame with a message.
     *
     * @param warningMessage the warning message
     *                       (see org.w3c.css.util.Messages.properties).
     * @param message        An add-on message.
     * @see                  org.w3c.css.util.Warning
     */
    public void addWarning(String warningMessage, String message) {
    warnings.addWarning(new Warning(getSourceFile(), getLine(),
                    warningMessage, 0, message, "", ac));
    }

    /**
     * Adds a warning to this frame with a message.
     *
     * @param warningMessage the warning message
     *                       (see org.w3c.css.util.Messages.properties).
     * @param message        An add-on message.
     * @see                  org.w3c.css.util.Warning
     */
    public void addWarning(String warningMessage, String message1,
	    String message2) {
    warnings.addWarning(new Warning(getSourceFile(), getLine(),
                    warningMessage, 0, message1, message2, ac));
    }
    
    /**
     * Get all warnings.
     */
    public Warnings getWarnings() {
    return warnings;
    }

    /**
     * Get the name of the source file.
     */
    public String getSourceFile() {
    return sourceFile;
    }

    /**
     * Get the begin line.
     */
    public int getBeginLine() {
    return line;
    }

    /**
     * Get the current line.
     */
    public int getLine() {
    //return line; //+ cssFouffa.token.beginLine;
    return line + cssFouffa.token.beginLine;
    }

    /**
     * Merge two frames.
     *
     * @param frame The other frame for merging.
     */
    public void join(Frame frame) {
    errors.addErrors(frame.errors);
    warnings.addWarnings(frame.warnings);
    }
}


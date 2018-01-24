//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.util;


import org.w3c.css.parser.analyzer.ParseException;

/**
 * @version $Revision$
 */
public class InvalidParamException extends ParseException {
    String errorType = null;

    /**
     * Create a new InvalidParamException.
     */
    public InvalidParamException() {
        super();
    }

    /**
     * Create a new InvalidParamException with an error message.
     *
     * @param message the error message
     */
    public InvalidParamException(String message, ApplContext ac) {
        super(ac.getMsg().getErrorString((message != null) ? message : ""));
        errorType = message;
    }

    /**
     * Create a new InvalidParamException with an error message class.
     *
     * @param error   the error message class.
     * @param message a message to add
     */
    public InvalidParamException(String error, Object message, ApplContext ac) {
        super(processError(error, (message != null) ? message : null, ac));
        errorType = error;
    }

    /**
     * Create a new InvalidParamException with an error message class.
     *
     * @param error the error message class.
     * @param args  a string array of messages to add
     */
    public InvalidParamException(String error, String[] args, ApplContext ac) {
        super(processError(error, args, ac));
        errorType = error;
    }

    /**
     * Create a new InvalidParamException.
     *
     * @param error    the error message class
     * @param message1 the first message to add
     * @param message1 the second message to add
     */
    public InvalidParamException(String error, Object message1,
                                 Object message2, ApplContext ac) {
        super(processError(error,
                (message1 != null) ? message1.toString() : null,
                (message2 != null) ? message2.toString() : null,
                ac));
        errorType = error;
    }

    /**
     * Get the error type if defined
     *
     * @return a String or null if undefined
     */
    public String getErrorType() {
        return errorType;
    }

    private static String processError(String error, String[] args, ApplContext ac) {
        StringBuilder sb = new StringBuilder();
        String str = null;

        if (error != null) {
            str = ac.getMsg().getErrorString(error);
        }
        if (str == null) {
            return "can't find the error message for " + error;
        }
        // replace all parameters
        String[] msg_parts = str.split("%s", -1);
        int j = 0;
        sb.append(msg_parts[0]);
        for (int i = 1; i < msg_parts.length; i++) {
            if (j < args.length) {
                sb.append(args[j++]);
            }
            sb.append(msg_parts[i]);
        }
        return sb.toString();
    }

    private static String processError(String error, Object args, ApplContext ac) {
        String sa[] = {args.toString()};
        return processError(error, sa, ac);
    }

    private static String processError(String error, String arg1,
                                       String arg2, ApplContext ac) {
        String sa[] = {arg1, arg2};
        return processError(error, sa, ac);
    }

} // InvalidParamException

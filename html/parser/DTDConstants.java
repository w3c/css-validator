/*
 * @(#)DTDConstants.java	1.1 95/04/23  
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

/**
 * SGML constants used in a DTD.
 *
 * @version 	1.1, 23 Apr 1995
 * @author Arthur van Hoff
 */
public
interface DTDConstants {
    // Attribute value types
    int CDATA 		= 1;
    int ENTITY		= 2;
    int ENTITIES	= 3;
    int ID		= 4;
    int IDREF		= 5;
    int IDREFS		= 6;
    int NAME		= 7;
    int NAMES		= 8;
    int NMTOKEN		= 9;
    int NMTOKENS	= 10;
    int NOTATION	= 11;
    int NUMBER		= 12;
    int NUMBERS		= 13;
    int NUTOKEN		= 14;
    int NUTOKENS	= 15;

    // Content model types
    int RCDATA		= 16;
    int EMPTY		= 17;
    int MODEL		= 18;
    int ANY		= 19;

    // Attribute value modifiers
    int FIXED		= 1;
    int REQUIRED	= 2;
    int CURRENT		= 3;
    int CONREF		= 4;
    int IMPLIED		= 5;

    // Entity types
    int PUBLIC		= 10;
    int SDATA		= 11;
    int PI		= 12;
    int STARTTAG	= 13;
    int ENDTAG		= 14;
    int MS		= 15;
    int MD		= 16;
    int SYSTEM		= 17;

    int GENERAL		= 1<<16;
    int DEFAULT		= 1<<17;
    int PARAMETER	= 1<<18;
}

//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css2.table;

/**
 * @version $Revision$
 */
public class Css2Style extends org.w3c.css.properties.css2.Css2Style {

    SpeakHeader speakHeader;
    SpeakHeaderATSC speakHeaderATSC;

    /**
     * Get the emtpy-header property
     */
    public final SpeakHeader getSpeakHeader() {
	if (speakHeader == null) {
	    speakHeader = (SpeakHeader)
		style.CascadingOrder(new SpeakHeader(), style, selector);
	}
	return speakHeader;
    }

    public final SpeakHeaderATSC getSpeakHeaderATSC() {
	if (speakHeaderATSC == null) {
	    speakHeaderATSC = (SpeakHeaderATSC)
		style.CascadingOrder(new SpeakHeaderATSC(), style, selector);
	}
	return speakHeaderATSC;
    }


}

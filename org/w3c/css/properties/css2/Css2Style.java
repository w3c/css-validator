//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css2;

import org.w3c.css.properties.aural.ACssStyle;

/**
 * @version $Revision$
 */
public class Css2Style extends ACssStyle {

    /**
     * aural properties
     */
    public org.w3c.css.properties.css.CssAzimuth cssAzimuth;
    public org.w3c.css.properties.css.CssElevation cssElevation;

    /**
     * aural properties
     *
     */

    /**
     * Get the azimuth
     */
    public org.w3c.css.properties.css.CssAzimuth getAzimuth() {
        if (cssAzimuth == null) {
            cssAzimuth = (org.w3c.css.properties.css.CssAzimuth) style.CascadingOrder(new org.w3c.css.properties.css.CssAzimuth(),
                    style, selector);
        }
        return cssAzimuth;
    }

    /**
     * Get the elevation
     */
    public org.w3c.css.properties.css.CssElevation getElevation() {
        if (cssElevation == null) {
            cssElevation = (org.w3c.css.properties.css.CssElevation) style.CascadingOrder(new org.w3c.css.properties.css.CssElevation(),
                    style, selector);
        }
        return cssElevation;
    }

}

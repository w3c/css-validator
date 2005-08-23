//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.3  2005/08/08 13:18:54  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.2  2002/04/08 21:18:00  plehegar
 * New
 *
 * Revision 2.1  1997/08/29 13:11:50  plehegar
 * Updated
 *
 * Revision 1.6  1997/08/26 14:25:55  plehegar
 * nothing
 *
 * Revision 1.5  1997/08/22 18:11:29  plehegar
 * Added speech-rate and pitch
 *
 * Revision 1.4  1997/08/22 15:24:21  plehegar
 * Udpated
 *
 * Revision 1.3  1997/08/22 15:05:41  plehegar
 * Updated
 *
 * Revision 1.2  1997/08/21 21:12:01  vmallet
 * Minor modifications so we could compile it.
 *
 * Revision 1.1  1997/08/20 18:42:05  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.css2;

import org.w3c.css.properties.aural.ACssStyle;
import org.w3c.css.parser.CssPrinterStyle;

/**
 * @version $Revision$
 */
public class Css2Style extends ACssStyle {

  public void print(CssPrinterStyle printer) {
    super.print(printer);
  }


}

//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 */
package org.w3c.css.parser.analyzer;

import org.w3c.css.values.CssExpression;

/**
 * This class represents a couple : an at-rule property and an at-rule
 * expression.
 *
 * @version $Revision$ 
 */
public class Couple {

  /**
   * The at-rule property.
   */  
  protected String property;
  
  /**
   * The at-rule expression.
   */  
  protected CssExpression expression;

  /**
   * Is this at-rule is important
   */
  protected boolean important;

  /**
   * Create a new Couple
   *
   * @param property The at-rule property.
   * @param expression The at-rule expression.
   * @param important Is this at-rule is important
   */
  public Couple(String property, CssExpression expression, boolean important) {
    this.property = property;
    this.expression = expression;
    this.important = important;
  }  

  /**
   * Get the at-rule property
   */  
  public final String getProperty() {
    return property;
  }

  /**
   * get the at-rule expression
   */  
  public final CssExpression getExpression() {
    return expression;
  }

  /**
   * Is this at-rule is important
   */  
  public final boolean getImportant() {
    return important;
  }

}

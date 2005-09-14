//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import java.util.Vector;

/**
 * This class is used by the CSS1 parser to generate all expressions.
 *
 * @version $Revision$
 */
public class CssExpression implements CssOperator {

  /**
   * Add a value to the end of the expression
   * By default the next operator is a space
   *
   * @param value The value to append
   */
  public void addValue(CssValue value) {
    items.addElement(new ValueOperator(value));
    count++;
  }

  /**
   * Change the next operator
   * Don't check if the operator is correct
   *
   * @param operator The operator
   * @see CssOperator
   */
  public void setOperator(char operator) {
    ((ValueOperator) items.elementAt(count-1)).operator = operator;
  }

  /**
   * Change the next operator for the current position
   * Don't check if the operator is correct
   *
   * @param operator The operator
   * @see CssOperator
   */
  public void setCurrentOperator(char operator) {
    ((ValueOperator) items.elementAt(index)).operator = operator;
  }

  /**
   * Returns the current value of the expression
   * don't change the position in the expression
   */
  public CssValue getValue() {
    if (index == count)
      return null;
    else
      return ((ValueOperator) items.elementAt(index)).value;
  }

  /**
   * Returns the current value of the expression
   * don't change the position in the expression
   */
  public CssValue getNextValue() {
    if (index+1 >= count)
      return null;
    else
      return ((ValueOperator) items.elementAt(index+1)).value;
  }

    /* Modified by Sijtsche de Jong */
  /**
   * Returns the operator <strong>after</strong> the current value
   * don't change the position in the expression
   */
  public char getOperator() {
    if (index == count)
      return SPACE;
    else
      return ((ValueOperator) items.elementAt(index)).operator;
  }

  /**
   * Returns the number of elements
   */
  public int getCount() {
    return count;
  }

  /**
   * Insert the current value at the current position.
   *
   * @param value The value to insert
   */
  public void insert(CssValue value) {
    items.insertElementAt(new ValueOperator(value), index);
    count++;
  }

  /**
   * Removes the current value and his operator
   */
  public void remove() {
    if (index != count)
      items.removeElementAt(index);
    count--;
  }

  /**
   * Returns true if there is no other element
   */
  public boolean end() {
    return index == count;
  }

  /**
   * Change the position to the beginning
   */
  public void starts() {
    index = 0;
  }

  /**
   * Change the position to the end
   */
  public void ends() {
    index = count;
  }

  /**
   * Change the position to the next
   */
  public void next() {
    if (index < count)
      index++;
  }

  /**
   * Change the position to the precedent
   */
  public void precedent() {
    if (index > 0)
      index--;
  }

  /**
   * Returns a string representation of the object from the current position.
   */
  public String toString() {
    String s = "";
    for (int i = index; i < count; i++) {
      ValueOperator vo = (ValueOperator) items.elementAt(i);
      s += vo.value.toString() + vo.operator;
    }

    if (s.length() > 0) {
	return s.substring(0, s.length()-1);
    } else {
	return "**invalid state**";
    }
  }

  /**
   * Returns a string representation of the object before the current position.
   */
  public String toStringFromStart() {
    String s = "";
    for (int i = 0; i < index; i++) {
      ValueOperator vo = (ValueOperator) items.elementAt(i);
      s += vo.value.toString() + vo.operator;
    }

    return s;
  }

  class ValueOperator {
    ValueOperator(CssValue value) {
      this.value = value;
      this.operator = SPACE;
    }
    CssValue value;
    char operator;
  }

  private Vector items = new Vector();
  private int count = 0;
  private int index = 0;
}

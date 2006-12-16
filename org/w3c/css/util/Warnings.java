//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.util;

/**
 * Controls all warnings in the validator
 *
 * @version $Revision$
 * @see java.util.Vector
 */
public final class Warnings {

  private Warning[] warningData = new Warning[20];

  private int       warningCount = 0;

  private int ignoredWarningCount = 0;

  private int warningLevel = 0;
  
  public Warnings() {
	  
  }
  
  public Warnings(int level) {
	  this.warningLevel = level;
  }
  
  	public int getWarningLevel() {
  		return warningLevel;
  	}

  	public void setWarningLevel(int warningLevel) {
  		this.warningLevel = warningLevel;
  	}

/**
   * Add a warning.
   *
   * @param warm the warning
   */
  public final void addWarning(Warning warm) {
	  if(warm.getLevel() > warningLevel) {
		  ignoredWarningCount++;
	  }
	  else {
		  resize(1);
		  warningData[warningCount++] = warm;
	  }
  }

  /**
   * Add warnings.
   *
   * @param warnings All warnings
   */
  public final void addWarnings(Warnings warnings) {
    //resize(warnings.warningCount);
    for(int i=0; i < warnings.warningCount; i++) {
    	addWarning(warnings.warningData[i]);
    }
  }

  /**
   * Get the number of warnings
   */
  public final int getWarningCount() {
    return warningCount;
  }

  /**
   * Get the number of ignored warnings (not corresponding to the warning level)
   */
  public final int getIgnoredWarningCount() {
    return ignoredWarningCount;
  }
  
  /**
   * Get an array with all warnings.
   */
  public final Warning[] getWarnings() {
    int oldCapacity = warningData.length;
    if (warningCount < oldCapacity) {
      Warning oldData[] = warningData;
      warningData = new Warning[warningCount];
      System.arraycopy(oldData, 0, warningData, 0, warningCount);
    }
    return warningData;
  }

  /**
   * Sort all warnings by line and level
   */
  public final void sort() {
    quickSort(0, warningCount-1);
  }

  private int partition(int part_low_ind, int part_high_ind) {
    int lastsmall;
    long median_val;
    int comp1;
    Warning transit;

    // swap median value an first value of array
    comp1 = ( part_low_ind + part_high_ind ) / 2;

    transit = warningData[part_low_ind];
    warningData[part_low_ind] = warningData[comp1];
    warningData[comp1] = transit;
    median_val = warningData[part_low_ind].getInternalOrder();
    lastsmall = part_low_ind;
    for (int i = part_low_ind + 1; i<=part_high_ind; i++) {
      if (warningData[i].getInternalOrder() < median_val) {
          lastsmall++;
          // swap lastsmall and i
          transit=warningData[lastsmall];
          warningData[lastsmall]=warningData[i];
          warningData[i]=transit;
        }
    }
    // swap part_low_ind and lastsmall
    transit=warningData[part_low_ind];
    warningData[part_low_ind]=warningData[lastsmall];
    warningData[lastsmall]=transit;

    return lastsmall;
  }

  private void quickSort(int qk_low_ind, int qk_high_ind) {
    if (qk_low_ind < qk_high_ind) {
      int median = partition(qk_low_ind, qk_high_ind);
      quickSort(qk_low_ind, median);
      quickSort(median+1, qk_high_ind);
    }
  }

  /**
   * Get a warning with an index.
   * @param index the warning index.
   */
  public final Warning getWarningAt(int index) {
    return warningData[index];
  }

  private final void resize(int increment) {
    int oldCapacity = warningData.length;
    if (warningCount + increment + 1 > oldCapacity) {
      Warning oldData[] = warningData;
      warningData = new Warning[oldCapacity + increment + 1];
      System.arraycopy(oldData, 0, warningData, 0, warningCount);
    }
  }
}

//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.util;

import java.util.Arrays;

/**
 * Controls all warnings in the validator
 *
 * @version $Revision$
 * @see java.util.Vector
 */
public final class Warnings {
    private Warning[] warningData = new Warning[20];
    private int warningCount = 0;
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
        if (warm.getLevel() > warningLevel) {
            ignoredWarningCount++;
        } else {
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
        for (int i = 0; i < warnings.warningCount; i++) {
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
     * Get the number of ignored warnings 
     * (not corresponding to the warning level)
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
        if (warningCount > 0) {
            Arrays.sort(warningData, 0, warningCount);
        }
    }

    /**
     * Get a warning with an index.
     *
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

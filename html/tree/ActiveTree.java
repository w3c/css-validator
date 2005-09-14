/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id$ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */
/* Modified: Vincent Mallet (Vincent.Mallet@sophia.inria.fr) */


package html.tree;

import java.util.*;

/**
 * An Active implementation of Tree.
 */
public class ActiveTree extends SimpleTree {
    Vector listeners = null;

    private final void checkListeners() {
	if(listeners == null) {
	    listeners = new Vector(1, 1);
	}
    }

    public void addTreeListener(TreeListener listener) {
	if(listeners == null) {
	    listeners = new Vector(1);
	}
	listeners.addElement(listener);
	listeners.trimToSize();
    }

    public void removeTreeListener(TreeListener listener) {
	listeners.removeElement(listener);
	if(listeners.size() == 0) {
	    listeners = null;
	}
	else {
	    listeners.trimToSize();
	}
    }

    public void setParent(Tree parent) {
	super.setParent(parent);
	if (listeners == null)
	  return;
	for(int i = 0; i < listeners.size(); i++) {
	    TreeListener l = (TreeListener)listeners.elementAt(i);
	    l.notifySetParent(parent);
	}
    }


    public void enter() {
	if (listeners == null)
	  return;
	for(int i = 0; i < listeners.size(); i++) {
	    TreeListener l = (TreeListener)listeners.elementAt(i);
	    l.notifyEnter();
	}
    }

    public void exit() {
	if (listeners == null)
	  return;
	for(int i = 0; i < listeners.size(); i++) {
	    TreeListener l = (TreeListener)listeners.elementAt(i);
	    l.notifyExit();
	}
    }


    public void replace(Tree child, int rank) {
	super.replace(child, rank);
	if (listeners == null)
	  return;
	for(int i = 0; i < listeners.size(); i++) {
	    TreeListener l = (TreeListener)listeners.elementAt(i);
	    l.notifyReplace(child, rank);
	}
    }

    public void attach(Tree child, int rank) {
	super.attach(child, rank);
	if (listeners == null)
	  return;
	for(int i = 0; i < listeners.size(); i++) {
	    TreeListener l = (TreeListener)listeners.elementAt(i);
	    l.notifyAttach(child, rank);
	}
    }

    public void attach(Tree child, Tree after) {
	super.attach(child, after);
	if (listeners == null)
	  return;
	int rank = indexOf(child);
	for(int i = 0; i < listeners.size(); i++) {
	    TreeListener l = (TreeListener)listeners.elementAt(i);
	    l.notifyAttach(child, rank);
	}
    }

    public void detach(Tree child) {
	int rank = indexOf(child);
	super.detach(child);
	if (listeners == null)
	  return;
	for(int i = 0; i < listeners.size(); i++) {
	    TreeListener l = (TreeListener)listeners.elementAt(i);
	    l.notifyDetach(child, rank);
	}
    }

    public void move(Tree child, int rank) {
	int oldRank = indexOf(child);
	super.move(child, rank);
	if (listeners == null)
	  return;
	for(int i = 0; i < listeners.size(); i++) {
	    TreeListener l = (TreeListener)listeners.elementAt(i);
	    l.notifyDetach(child, oldRank);
	    l.notifyAttach(child, rank);
	}
    }
}

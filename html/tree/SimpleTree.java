/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id$ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tree;

import html.util.EmptyEnumeration;
import java.util.*;

/**
 * A simple implementation of Tree.
 */
public class SimpleTree implements Tree {
    private static Enumeration NO_CHILD = new EmptyEnumeration();

    Vector children = null;
    Tree parent;

    //============================================================
    //      implements: Tree
    //============================================================

    public Tree getParent() {
	return parent;
    }

    public void setParent(Tree child) {
	parent = child;
    }

    public int getRank() {
	return parent.indexOf(this);
    }

    public int indexOf(Tree child) {
	return children.indexOf(child);
    }

    public Tree getChild(int r) {
	return (Tree)children.elementAt(r);
    }

    public void replace(Tree child, int r) {
	Tree old;
	synchronized(children) {
	    old = (Tree)children.elementAt(r);
	    children.removeElementAt(r);
	    children.insertElementAt(child, r);
	}
    }

    public void attach(Tree child, int rank) {
	checkChildren();
	children.insertElementAt(child, rank);
	child.setParent(this);
    }

    public void attach(Tree child, Tree after) {
	checkChildren();
	synchronized(children) {
	    int r = children.indexOf(after);
	    children.insertElementAt(child, r);
	}
	child.setParent(this);
    }

    public void detach(Tree child) {
	children.removeElement(child);
	child.setParent(null);
    }

    public void detachChildren() {
	children = null;
    }

    public void move(Tree child, int rank) {
	synchronized(children) {
	    children.removeElement(child);
	    children.insertElementAt(child, rank);
	}
    }

    public int arity() {
	return (children != null ) ? children.size() : 0;
    }

    public Enumeration children() {
	return (children != null ) ? children.elements() : NO_CHILD;
    }

    private final void checkChildren() {
	if(children == null) {
	    children = new Vector(1, 1);
	}
    }
}

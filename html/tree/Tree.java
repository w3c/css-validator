/* Copyright (c) 1996 by Groupe Bull.  All Rights Reserved */
/* $Id$ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tree;

import java.util.*;


/**
 * Defines the general interface for Tree manipulation.
 *
 * The design problem here, is that we need to manipulate Nodes and Leaves the
 * same way: For example, for a given Node, A factory may choose to return a
 * Node or a Leaf. so its create() method should return a Leaf and this would
 * force us to dynamically cast the Leaf into Node when needed.
 *
 * For both code clarity and performance I think its better to imagine we are
 * manipulating only Nodes and let the node itself decide if it is a leaf or
 * not. Leaves, should throw an IllegalTreeOperation for methods that have no
 * semantic.
 */
public interface Tree {

   /**
    * @return the parent Tree of this Tree or null.
    */
    Tree getParent();

   /**
    * Sets the parent for this tree.
    *
    * If the 'tree' has no notifier, it inherits its parent's one.
    */
    void setParent(Tree parent);


   /**
    * @return the index of this Tree in its parent.
    */
    int getRank();

   /**
    * @return index of 'child' in children
    */
    int indexOf(Tree child);

   /**
    * @return the 'rank'th child
    *
    * If rank is invalid, throws an ArrayOutOfBoundsException.
    */
    Tree getChild(int rank);

   /**
    * Replace a child.
    *
    * replace the 'rank'th child by 'child'.
    * If rank is invalid, throws an ArrayOutOfBoundsException.
    */
    void replace(Tree child, int rank);

   /**
    * Add a child.
    *
    * inserts a new child at rank 'rank'.
    * If rank is invalid, throws an ArrayOutOfBoundsException.
    */
    void attach(Tree child, int rank);

   /**
    * Add a child.
    *
    * inserts a new child after the child 'after'.
    * If 'after' does not exist, throws an ArrayOutOfBoundsException.
    */
    void attach(Tree child, Tree after);

   /**
    * Remove a child.
    *
    * removes the child at rank 'rank'.
    * If rank is invalid, throws an ArrayOutOfBoundsException.
    */
    void detach(Tree child);

   /**
    * @return children count
    */
    int arity();

   /**
    * list of children
    */
    Enumeration children();

}

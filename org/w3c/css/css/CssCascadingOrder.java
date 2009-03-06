//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.util.Enumeration;

import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.CompareFunction;
import org.w3c.css.util.QuickSortAlgorithm;
import org.w3c.css.util.SortAlgorithm;
import org.w3c.css.util.Util;

/**
 *   <H3>
 *      &nbsp;&nbsp; Cascading order
 *   </H3>
 *
 * <P> Conflicting rules are intrinsic to the CSS mechanism. To find the value
 * for an element/property combination, the following algorithm must be
 * followed:
 *
 * <OL>
 * <LI> Find all declarations that apply to the element/property in question.
 * Declarations apply if the selector matches the element in question. If no
 * declarations apply, the inherited value is used. If there is no inherited
 * value (this is the case for the 'HTML' element and for properties that do
 * not inherit), the initial value is used.
 *
 * <LI> Sort the declarations by explicit weight: declarations marked
 * '!important' carry more weight than unmarked (normal) declarations.
 *
 * <LI> Sort by origin: the author's style sheets override the reader's style
 * sheet which override the UA's default values. An imported style sheet has
 * the same origin as the style sheet from which it is imported.
 *
 * <LI> Sort by specificity of selector: more specific selectors will override
 * more general ones. To find the specificity, count the number of ID
 * attributes in the selector (a), the number of CLASS attributes in the
 * selector (b), and the number of tag names in the selector (c). Concatenating
 * the three numbers (in a number system with a large base) gives the
 * specificity. Some examples:
 *
 * <PRE>
 *   LI            {...}  /* a=0 b=0 c=1 -&gt; specificity =   1 * /
 *   UL LI         {...}  /* a=0 b=0 c=2 -&gt; specificity =   2 * /
 *   UL OL LI      {...}  /* a=0 b=0 c=3 -&gt; specificity =   3 * /
 *   LI.red        {...}  /* a=0 b=1 c=1 -&gt; specificity =  11 * /
 *   UL OL LI.red  {...}  /* a=0 b=1 c=3 -&gt; specificity =  13 * /
 *   #x34y         {...}  /* a=1 b=0 c=0 -&gt; specificity = 100 * /
 * </PRE>
 *
 * <P> Pseudo-elements and pseudo-classes are counted as normal elements and
 * classes, respectively.
 *
 * <LI> Sort by order specified: if two rules have the same weight, the latter
 * specified wins. Rules in imported style sheets are considered to be before
 * any rules in the style sheet itself.
 * </OL>
 *
 * <P> The search for the property value can be terminated whenever one rule
 * has a higher weight than the other rules that apply to the same
 * element/property combination.
 *
 * <P> This strategy gives author's style sheets considerably higher weight
 * than those of the reader. It is therefore important that the reader has the
 * ability to turn off the influence of a certain style sheet, e.g. through a
 * pull-down menu.
 *
 * <P> A declaration in the 'STYLE' attribute of an element has the same weight
 * as a declaration with an ID-based selector that is specified at the end of
 * the style sheet:
 *
 * <PRE>
 * &lt;STYLE TYPE="text/css"&gt;
 *   #x97z { color: blue }
 * &lt;/STYLE&gt;
 * &lt;P ID=x97z STYLE="color: red"&gt;
 * </PRE>
 *
 * <P> In the above example, the color of the 'P' element would be
 * red. Although the specificity is the same for both declarations, the
 * declaration in the 'STYLE' attribute will override the one in the 'STYLE'
 * element because of cascading rule number 5.
 *
 * <P> The UA may choose to honor other stylistic HTML attributes, for example
 * 'ALIGN'.  If so, these attributes are translated to the corresponding CSS
 * rules with specificity equal to 1. The rules are assumed to be at the start
 * of the author style sheet and may be overridden by subsequent style sheet
 * rules. In a transition phase, this policy will make it easier for stylistic
 * attributes to coexist with style sheets.
 *
 * @version $Revision$
 */
public final class CssCascadingOrder {

    CssProperty[] propertyData;
    int propertyCount;
    final int capacityIncrement = 10;

    /**
     * Order all properties and returns the winner.
     *
     * @param property The default value returned if there is no property.
     * @param style The current style sheet where we can find all properties.
     * @param selector The current context.
     * @return the property with the right value.
     */
    public CssProperty order(CssProperty property,
			     StyleSheet style, CssSelectors selector) {
	//int i = 0;
	propertyData = new CssProperty[10];
	propertyCount = 0;
	Util.verbose("CASCADING ORDER " + property.getPropertyName()
		     + " in " + selector);

	// looking for all declarations that apply to the element/property in
	// question. (step 1)
	for (Enumeration e = style.getRules().elements(); e.hasMoreElements();) {
	    CssSelectors context = (CssSelectors) e.nextElement();

	    Util.verbose("######## test with " + context
			 + " and " + selector);
	    //	    if (!selector.equals(context) && context.canApply(selector)) {
	    if (context.canApply(selector)) {
		// here, don't try to resolve
		CssProperty prop =
		    property.getPropertyInStyle(context.getStyle(), false);
		Util.verbose("%%%%%%%%%%%%%%%%% Found " + context);
		if (prop != null) {
		    addProperty(prop);
		}
	    }
	}

	if (propertyCount == 0) {
	    // if I found nothing
	    if (selector.getNext() != null && property.inherited()) {
		// here, I can try to resolve
		Util.verbose("Found nothing ... try the next "
			     + selector.getNext());
		CssStyle s = style.getStyle(selector.getNext());
		property = property.getPropertyInStyle(s, true);
	    } // else use the default value
	} else {
	    Util.verbose("@@@@@@@@@@@@@@ FOUND "
			 + propertyCount + " properties");
	    // runs the cascading order
	    property = getProperty(selector);

	    if (property.isSoftlyInherited()
		&& selector.getNext() != null) {
		// the value of the property is inherited,
		// recompute again ....
		CssStyle s = style.getStyle(selector.getNext());
		property = property.getPropertyInStyle(s, true);
	    }
	}
	// duplicate the property because I change the context ...
	property = property.duplicate();
	property.setSelectors(selector);

	return property;
    }

    // here you can find the algorithm for the cascading order
    private CssProperty getProperty(CssSelectors selector) {
	SortAlgorithm sort = new QuickSortAlgorithm();

	// sort by explicit weight and origin (step 2 and 3)
	sort.sort(propertyData, 0, propertyCount - 1,
		  new CompareExplicitWeight());
	int old = propertyData[0].getExplicitWeight();
	int end = 0;
	while (end < propertyCount &&
	       propertyData[end].getExplicitWeight() == old) {
	    end++;
	}

	// sort by specificity (step 4)
	sort.sort(propertyData, 0, end-1, new CompareSpecificity());
	old = propertyData[0].getSelectors().getSpecificity();
	end = 0;
	while (end < propertyCount &&
	       propertyData[end].getSelectors().getSpecificity() == old) {
	    end++;
	}

	// sort by order specified (step 5)
	sort.sort(propertyData, 0, end - 1, new CompareOrderSpecified());

	return propertyData[0];
    }

    private final void addProperty(CssProperty property) {
	int oldCapacity = propertyData.length;
	if (propertyCount + 1 > oldCapacity) {
	    CssProperty oldData[] = propertyData;
	    propertyData = new CssProperty[oldCapacity + capacityIncrement];
	    System.arraycopy(oldData, 0, propertyData, 0, propertyCount);
	}
	propertyData[propertyCount++] = property;
    }

}

// all compare functions used in the cascading order

class CompareExplicitWeight extends CompareFunction {
    public boolean compare(Object obj1, Object obj2) {
	return (((CssProperty) obj1).getExplicitWeight() >
		((CssProperty) obj2).getExplicitWeight());
    }
}

class CompareSpecificity extends CompareFunction {
    public boolean compare(Object obj1, Object obj2) {
	return (((CssProperty) obj1).getSelectors().getSpecificity() >
		((CssProperty) obj2).getSelectors().getSpecificity());
    }
}

class CompareOrderSpecified extends CompareFunction {
    public boolean compare(Object obj1, Object obj2) {
	return (((CssProperty) obj1).getOrderSpecified() >
		((CssProperty) obj2).getOrderSpecified());
    }
}

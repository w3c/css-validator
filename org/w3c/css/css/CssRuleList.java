// $Id$
// Author: Sijtsche de Jong
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.util.ArrayList;
import java.util.List;

import org.w3c.css.parser.AtRule;
import org.w3c.css.util.Messages;

public class CssRuleList implements ICssStyleRuleOrCssRuleList {

    AtRule atRule;
    ArrayList<ICssStyleRuleOrCssRuleList> rulelist;
    public String pseudopage;
    String indent;

    public CssRuleList() {
        atRule = null;
        rulelist = new ArrayList<>();
        indent = new String();
    }

    public void addStyleRule(CssStyleRule stylerule) {
        rulelist.add(stylerule);
    }

    public ArrayList<CssStyleRule> getStyleRules() {
        ArrayList<CssStyleRule> r = new ArrayList<>(this.rulelist.size());
        for(ICssStyleRuleOrCssRuleList u: this.rulelist) {
        	if (u instanceof CssStyleRule) {
        		r.add((CssStyleRule) u);
        	}
        }
		return r;
    }

    public List<ICssStyleRuleOrCssRuleList> getStyleRulesTree() {
        return rulelist;
    }

    public List<CssStyleRule> getStyleRulesAllInTree() {
        ArrayList<CssStyleRule> r = new ArrayList<>();
        this.appendStyleRulesTree(r);
        return r;
    }
    
    protected void appendStyleRulesTree(List<CssStyleRule> r) {
        for(ICssStyleRuleOrCssRuleList u: this.rulelist) {
        	if (u instanceof CssStyleRule) {
        		r.add((CssStyleRule) u);
        	}else if (u instanceof CssRuleList) {
        		CssRuleList l = (CssRuleList) u;
        		l.appendStyleRulesTree(r);
        	}
        }
    }

    /**
     * This should be rather named setAtRule
     * @param atRule
     */
    public void addAtRule(AtRule atRule) {
        this.atRule = atRule;
    }

    public String getAtRule() {
        return (atRule != null) ? atRule.toString() : "";
    }
    
    public AtRule getAtRuleObject() {
        return atRule;
    }

    public String getAtRuleEscaped() {
        return Messages.escapeString(atRule.toString());
    }

    public boolean isEmpty() {
        return atRule.isEmpty() /*&& rulelist.isEmpty() */;
    }

    public String toString() {
        String atRuleString = atRule.toString();
        StringBuilder ret = new StringBuilder();
        if (null != atRule && atRule.isEmpty()) {
            if (atRuleString.length() != 0) {
                ret.append(atRuleString);
                ret.append("\n\n");
            }
        } else {
            if (atRuleString.length() != 0) {
                ret.append(atRuleString);
                ret.append(" {\n\n");
            }
            for (ICssStyleRuleOrCssRuleList styleRule : rulelist) {
                ret.append(styleRule);
            }
            if (atRuleString.length() != 0) {
                ret.append("}\n");
            }
        }
        return ret.toString();
    }

	public void addSubRulelist(CssRuleList rulelist) {
		this.rulelist.add(rulelist);
	}

}

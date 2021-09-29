// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors.pseudofunctions;

import org.w3c.css.selectors.PseudoFunctionSelector;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import java.util.IllformedLocaleException;
import java.util.Locale;
import java.util.Locale.Builder;

/**
 * PseudoFunctionLang<br />
 * Created: Sep 2, 2005 4:24:48 PM<br />
 */
public class PseudoFunctionLang extends PseudoFunctionSelector {

    public PseudoFunctionLang(String name, String lang, ApplContext ac)
            throws InvalidParamException {
        setName(name);
        parseLang(ac, lang, name);
        setParam(lang);
    }

    /**
     * verify a language tag per BCP47
     *
     * @param ac     the ApplContext
     * @param lang   the language tag
     * @param caller the property/selector/context calling for verification
     * @throws InvalidParamException if invalid
     */
    public static final void parseLang(ApplContext ac, String lang, String caller)
            throws InvalidParamException {
        try {
            // FIXME validate ranges and not only TAGS.
            if (lang.contains("*")) {
                return;
            }
            String lang_tag = lang;
            if (lang.charAt(0) == '"' || lang.charAt(0) == '\'') {
                // trim the string
                lang_tag = lang.substring(1, lang.lastIndexOf(lang.charAt(0)));
            }
            // use Locale builder parsing to check BCP 47 values
            Builder builder = new Builder();
            builder.setLanguageTag(lang_tag);
            Locale l = builder.build();
        } catch (IllformedLocaleException ex) {
            throw new InvalidParamException("value", lang, caller, ac);
        }
    }

}

//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.util.Warnings;
import org.w3c.css.util.Warning;
import org.w3c.css.util.ApplContext;
import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.properties2.Css2Style;
import org.w3c.css.values.CssValue;

public class Css3Style extends org.w3c.css.atsc.ATSCStyle {

    CssOpacity cssOpacity;
    CssColorProfile cssColorProfile;
    CssRenderIntent cssRenderIntent;
    CssFontEffect cssFontEffect;
    CssFontSmooth cssFontSmooth;
    CssFontEmphasizeStyle cssFontEmphasizeStyle;
    CssFontEmphasizePos cssFontEmphasizePos;
    CssFontEmphasize cssFontEmphasize;
    CssRubyPosition cssRubyPosition;
    CssRubyAlign cssRubyAlign;
    CssRubyOverhang cssRubyOverhang;
    CssBoxSizing cssBoxSizing;
    CssResizer cssResizer;
    CssKeyEq cssKeyEq;
    CssTabIndex cssTabIndex;
    CssUserInput cssUserInput;
    CssUserModify cssUserModify;
    CssUserSelect cssUserSelect;
    CssUserFocus cssUserFocus;
    CssUserFocusKey cssUserFocusKey;
    CssUserFocusPointer cssUserFocusPointer;
    CssToggleGroup cssToggleGroup;
    CssGroupReset cssGroupReset;
    CssWritingMode cssWritingMode;
    CssGlyphOrVert cssGlyphOrVert;
    CssGlyphHor cssGlyphHor;
    CssTextJustify cssTextJustify;
    CssTextAlignLast cssTextAlignLast;
    CssMinFontSize cssMinFontSize;
    CssMaxFontSize cssMaxFontSize;
    CssTextJustifyTrim cssTextJustifyTrim;
    CssTextKashidaSpace cssTextKashidaSpace;
    CssDominantBaseLine cssDominantBaseLine;
    CssAlignmentBaseLine cssAlignmentBaseLine;
    CssAlignmentAdjust cssAlignmentAdjust;
    CssBaselineShift cssBaselineShift;
    CssLineBreak cssLineBreak;
    CssWordBreakCJK cssWordBreakCJK;
    CssWordBreakInside cssWordBreakInside;
    CssWordBreak cssWordBreak;
    CssTextSpace cssTextSpace;
    CssTextEndOverflow cssTextEndOverflow;
    CssTextAfterOverflow cssTextAfterOverflow;
    CssPunctuationTrim cssPunctuationTrim;
    CssTextAutoSpace cssTextAutoSpace;
    CssKerningMode cssKerningMode;
    CssKerningPairTreshold cssKerningPairTreshold;
    CssTextUlStyle cssTextUlStyle;
    CssTextUlColor cssTextUlColor;
    CssTextUlMode cssTextUlMode;
    CssTextUlPos cssTextUlPos;
    CssTextUnderLine cssTextUnderLine;
    CssTextLTStyle cssTextLTStyle;
    CssTextLTColor cssTextLTColor;
    CssTextLTMode cssTextLTMode;
    CssTextLineThrough cssTextLineThrough;
    CssTextOverLine cssTextOverLine;
    CssTextOLStyle cssTextOLStyle;
    CssTextOLColor cssTextOLColor;
    CssTextOLMode cssTextOLMode;
    CssTextCombine cssTextCombine;
    CssMedia cssMedia;
    CssDisplayModel cssDisplayModel;
    CssDisplayRole cssDisplayRole;
    CssMarginOutside cssMarginOutside;
    CssMarginInside cssMarginInside;
    CssBoxWidth cssBoxWidth;
    CssBoxHeight cssBoxHeight;
    CssFit cssFit;
    CssLink cssLink;
    CssLinkBehavior cssLinkBehavior;
    CssCollapse cssCollapse;
    CssFloatCSS3 cssFloatCSS3;
    CssClearCSS3 cssClearCSS3;
    CssLineHeightPolicy cssLineHeightPolicy;
    CssLineBoxContain cssLineBoxContain;
    CssColumnWidthPol cssColumnWidthPol;
    CssColumnSpaceDistribution cssColumnSpaceDistribution;
    CssColumnCount cssColumnCount;
    CssColumnWidth cssColumnWidth;
    CssColumnPadding cssColumnPadding;
    CssColumnBorder cssColumnBorder;
    CssColumnBorderColor cssColumnBorderColor;
    CssColumnBorderStyle cssColumnBorderStyle;
    CssColumnBorderWidth cssColumnBorderWidth;
    CssColumnSpan cssColumnSpan;
    //ACssVolumeCSS3 cssACssVolume;
	CssBackgroundClip cssBackgroundClip;
	CssBackgroundSize cssBackgroundSize;
	CssBackgroundOrigin cssBackgroundOrigin;
	CssBackgroundQuantity cssBackgroundQuantity;
	CssBackgroundSpacing cssBackgroundSpacing;
	CssTextDecorationCSS3 cssTextDecoration;
	CssWhiteSpaceCSS3 cssWhiteSpace;
	CssWordSpacingCSS3 cssWordSpacing;

    public CssOpacity getOpacity() {
	if (cssOpacity == null) {
	    cssOpacity  =
		(CssOpacity) style.CascadingOrder(new CssOpacity(),
						  style, selector);
	}
	return cssOpacity;
    }

    public CssColorProfile getColorProfile() {
	if (cssColorProfile == null) {
	    cssColorProfile  =
		(CssColorProfile) style.CascadingOrder(new CssOpacity(),
						       style, selector);
	}
	return cssColorProfile;
    }

    public CssRenderIntent getRenderIntent() {
	if (cssRenderIntent == null) {
	    cssRenderIntent =
		(CssRenderIntent) style.CascadingOrder(new CssRenderIntent(),
						       style, selector);
	}
	return cssRenderIntent;
    }

    public CssFontEffect getFontEffect() {
	if (cssFontEffect == null) {
	    cssFontEffect =
		(CssFontEffect) style.CascadingOrder(new CssFontEffect(),
						     style, selector);
	}
	return cssFontEffect;
    }

    public CssFontSmooth getFontSmooth() {
	if (cssFontSmooth == null) {
	    cssFontSmooth =
		(CssFontSmooth) style.CascadingOrder(new CssFontSmooth(),
						     style, selector);
	}
	return cssFontSmooth;
    }

    public CssFontEmphasizeStyle getFontEmphasizeStyle() {
	if (cssFontEmphasizeStyle == null) {
	    cssFontEmphasizeStyle =
		(CssFontEmphasizeStyle) style.CascadingOrder(
                            new CssFontEmphasizeStyle(), style, selector);
	}
	return cssFontEmphasizeStyle;
    }

    public CssFontEmphasizePos getFontEmphasizePos() {
	if (cssFontEmphasizePos == null) {
	    cssFontEmphasizePos =
		(CssFontEmphasizePos) style.CascadingOrder(
			    new CssFontEmphasizePos(), style, selector);
	}
	return cssFontEmphasizePos;
    }

    public CssFontEmphasize getFontEmphasize() {
	if (cssFontEmphasize == null) {
	    cssFontEmphasize =
		(CssFontEmphasize) style.CascadingOrder(
			    new CssFontEmphasizePos(), style, selector);
	}
	return cssFontEmphasize;
    }

    public CssRubyPosition getRubyPosition() {
	if (cssRubyPosition == null) {
	    cssRubyPosition =
		(CssRubyPosition) style.CascadingOrder (
			    new CssRubyPosition(), style, selector);
	}
	return cssRubyPosition;
    }

    public CssRubyAlign getRubyAlign() {
	if (cssRubyAlign == null) {
	    cssRubyAlign =
		(CssRubyAlign) style.CascadingOrder (
			    new CssRubyAlign(), style, selector);
	}
	return cssRubyAlign;
    }

    public CssRubyOverhang getRubyOverhang() {
	if (cssRubyOverhang == null) {
	    cssRubyOverhang =
		(CssRubyOverhang) style.CascadingOrder (
			    new CssRubyOverhang(), style, selector);
	}
	return cssRubyOverhang;
    }

    public CssBoxSizing getBoxSizing() {
	if (cssBoxSizing == null) {
	    cssBoxSizing =
		(CssBoxSizing) style.CascadingOrder (
			    new CssBoxSizing(), style, selector);
	}
	return cssBoxSizing;
    }

    public CssResizer getResizer() {
	if (cssResizer == null) {
	    cssResizer =
		(CssResizer) style.CascadingOrder (
			    new CssResizer(), style, selector);
	}
	return cssResizer;
    }

    public CssKeyEq getKeyEq() {
	if (cssKeyEq == null) {
	    cssKeyEq =
		(CssKeyEq) style.CascadingOrder (
			    new CssKeyEq(), style, selector);
	}
	return cssKeyEq;
    }

    public CssTabIndex getTabIndex() {
	if (cssTabIndex == null) {
	    cssTabIndex =
		(CssTabIndex) style.CascadingOrder (
			    new CssTabIndex(), style, selector);
	}
	return cssTabIndex;
    }

    public CssUserInput getUserInput() {
	if (cssUserInput == null) {
	    cssUserInput =
		(CssUserInput) style.CascadingOrder (
			    new CssUserInput(), style, selector);
	}
	return cssUserInput;
    }

    public CssUserModify getUserModify() {
	if (cssUserModify == null) {
	    cssUserModify =
		(CssUserModify) style.CascadingOrder (
			    new CssUserModify(), style, selector);
	}
	return cssUserModify;
    }

    public CssUserSelect getUserSelect() {
	if (cssUserSelect == null) {
	    cssUserSelect =
		(CssUserSelect) style.CascadingOrder (
			    new CssUserSelect(), style, selector);
	}
	return cssUserSelect;
    }

    public CssUserFocus getUserFocus() {
	if (cssUserFocus == null) {
	    cssUserFocus =
		(CssUserFocus) style.CascadingOrder (
  			    new CssUserFocus(), style, selector);
	}
	return cssUserFocus;
    }

    public CssUserFocusKey getUserFocusKey() {
	if (cssUserFocusKey == null) {
	    cssUserFocusKey =
		(CssUserFocusKey) style.CascadingOrder (
			    new CssUserFocusKey(), style, selector);
	}
	return cssUserFocusKey;
    }

    public CssUserFocusPointer getUserFocusPointer() {
	if (cssUserFocusPointer == null) {
	    cssUserFocusPointer =
		(CssUserFocusPointer) style.CascadingOrder (
			    new CssUserFocusPointer(), style, selector);
	}
	return cssUserFocusPointer;
    }

    public CssToggleGroup getToggleGroup() {
	if (cssToggleGroup == null) {
	    cssToggleGroup =
		(CssToggleGroup) style.CascadingOrder (
			    new CssToggleGroup(), style, selector);
	}
	return cssToggleGroup;
    }

    public CssGroupReset getGroupReset() {
	if (cssGroupReset == null) {
	    cssGroupReset =
		(CssGroupReset) style.CascadingOrder (
			    new CssGroupReset(), style, selector);
	}
	return cssGroupReset;
    }

    public CssWritingMode getWritingMode() {
	if (cssWritingMode == null) {
	    cssWritingMode =
		(CssWritingMode) style.CascadingOrder (
			    new CssWritingMode(), style, selector);
	}
	return cssWritingMode;
    }

    public CssGlyphOrVert getGlyphOrVert() {
	if (cssGlyphOrVert == null) {
	    cssGlyphOrVert =
		(CssGlyphOrVert) style.CascadingOrder (
			    new CssGlyphOrVert(), style, selector);
	}
	return cssGlyphOrVert;
    }

    public CssGlyphHor getGlyphHor() {
	if (cssGlyphHor == null) {
	    cssGlyphHor =
		(CssGlyphHor) style.CascadingOrder (
			    new CssGlyphHor(), style, selector);
	}
	return cssGlyphHor;
    }

    public CssTextJustify getTextJustify() {
	if (cssTextJustify == null) {
	    cssTextJustify =
		(CssTextJustify) style.CascadingOrder (
			    new CssTextJustify(), style, selector);
	}
	return cssTextJustify;
    }

    public CssTextAlignLast getTextAlignLast() {
	if (cssTextAlignLast == null) {
	    cssTextAlignLast =
		(CssTextAlignLast) style.CascadingOrder (
			    new CssTextAlignLast(), style, selector);
	}
	return cssTextAlignLast;
    }

    public CssMinFontSize getMinFontSize() {
	if (cssMinFontSize == null) {
	    cssMinFontSize =
		(CssMinFontSize) style.CascadingOrder (
			    new CssMinFontSize(), style, selector);
	}
	return cssMinFontSize;
    }

    public CssMaxFontSize getMaxFontSize() {
	if (cssMaxFontSize == null) {
	    cssMaxFontSize =
		(CssMaxFontSize) style.CascadingOrder (
			    new CssMaxFontSize(), style, selector);
	}
	return cssMaxFontSize;
    }

    public CssTextJustifyTrim getTextJustifyTrim() {
	if (cssTextJustifyTrim == null) {
	    cssTextJustifyTrim =
		(CssTextJustifyTrim) style.CascadingOrder (
			    new CssTextJustifyTrim(), style, selector);
	}
	return cssTextJustifyTrim;
    }

    public CssTextKashidaSpace getTextKashidaSpace() {
	if (cssTextKashidaSpace == null) {
	    cssTextKashidaSpace =
		(CssTextKashidaSpace) style.CascadingOrder (
			    new CssTextJustifyTrim(), style, selector);
	}
	return cssTextKashidaSpace;
    }

    public CssDominantBaseLine getDominantBaseLine() {
	if (cssDominantBaseLine == null) {
	    cssDominantBaseLine =
		(CssDominantBaseLine) style.CascadingOrder (
			    new CssDominantBaseLine(), style, selector);
	}
	return cssDominantBaseLine;
    }

    public CssAlignmentBaseLine getAlignmentBaseLine() {
	if (cssAlignmentBaseLine == null) {
	    cssAlignmentBaseLine =
		(CssAlignmentBaseLine) style.CascadingOrder (
			    new CssAlignmentBaseLine(), style, selector);
	}
	return cssAlignmentBaseLine;
    }

    public CssAlignmentAdjust getAlignmentAdjust() {
	if (cssAlignmentAdjust == null) {
	    cssAlignmentAdjust =
		(CssAlignmentAdjust) style.CascadingOrder (
			    new CssAlignmentAdjust(), style, selector);
	}
	return cssAlignmentAdjust;
    }

    public CssBaselineShift getBaselineShift() {
	if (cssBaselineShift == null) {
	    cssBaselineShift =
		(CssBaselineShift) style.CascadingOrder (
			    new CssBaselineShift(), style, selector);
	}
	return cssBaselineShift;
    }

    public CssLineBreak getLineBreak() {
	if (cssLineBreak == null) {
	    cssLineBreak =
		(CssLineBreak) style.CascadingOrder (
			    new CssLineBreak(), style, selector);
	}
	return cssLineBreak;
    }

    public CssWordBreakCJK getWordBreakCJK() {
	if (cssWordBreakCJK == null) {
	    cssWordBreakCJK =
		(CssWordBreakCJK) style.CascadingOrder (
			    new CssWordBreakCJK(), style, selector);
	}
	return cssWordBreakCJK;
    }

    public CssWordBreakInside getWordBreakInside() {
	if (cssWordBreakInside == null) {
	    cssWordBreakInside =
		(CssWordBreakInside) style.CascadingOrder (
			    new CssWordBreakInside(), style, selector);
	}
	return cssWordBreakInside;
    }

    public CssWordBreak getWordBreak() {
	if (cssWordBreak == null) {
	    cssWordBreak =
		(CssWordBreak) style.CascadingOrder (
			    new CssWordBreak(), style, selector);
	}
	return cssWordBreak;
    }

    public CssTextSpace getTextSpace() {
	if (cssTextSpace == null) {
	    cssTextSpace =
		(CssTextSpace) style.CascadingOrder (
			    new CssTextSpace(), style, selector);
	}
	return cssTextSpace;
    }

    public CssTextEndOverflow getTextEndOverflow() {
	if (cssTextEndOverflow == null) {
	    cssTextEndOverflow =
		(CssTextEndOverflow) style.CascadingOrder (
			    new CssTextEndOverflow(), style, selector);
	}
	return cssTextEndOverflow;
    }

    public CssTextAfterOverflow getTextAfterOverflow() {
	if (cssTextAfterOverflow == null) {
	    cssTextAfterOverflow =
		(CssTextAfterOverflow) style.CascadingOrder (
			    new CssTextAfterOverflow(), style, selector);
	}
	return cssTextAfterOverflow;
    }

    public CssPunctuationTrim getPunctuationTrim() {
	if (cssPunctuationTrim == null) {
	    cssPunctuationTrim =
		(CssPunctuationTrim) style.CascadingOrder (
			    new CssPunctuationTrim(), style, selector);
	}
	return cssPunctuationTrim;
    }

    public CssTextAutoSpace getTextAutoSpace() {
	if (cssTextAutoSpace == null) {
	    cssTextAutoSpace =
		(CssTextAutoSpace) style.CascadingOrder (
			    new CssTextAutoSpace(), style, selector);
	}
	return cssTextAutoSpace;
    }

    public CssKerningMode getKerningMode() {
	if (cssKerningMode == null) {
	    cssKerningMode =
		(CssKerningMode) style.CascadingOrder (
     		            new CssKerningMode(), style, selector);
	}
	return cssKerningMode;
    }

    public CssKerningPairTreshold getKerningPairTreshold() {
	if (cssKerningPairTreshold == null) {
	    cssKerningPairTreshold =
		(CssKerningPairTreshold) style.CascadingOrder (
			    new CssKerningPairTreshold(), style, selector);
	}
	return cssKerningPairTreshold;
    }

    public CssTextUlStyle getTextUlStyle() {
	if (cssTextUlStyle == null) {
	    cssTextUlStyle =
		(CssTextUlStyle) style.CascadingOrder (
			    new CssTextUlStyle(), style, selector);
	}
	return cssTextUlStyle;
    }

    public CssTextUlColor getTextUlColor() {
	if (cssTextUlColor == null) {
	    cssTextUlColor =
		(CssTextUlColor) style.CascadingOrder (
			    new CssTextUlColor(), style, selector);
	}
	return cssTextUlColor;
    }

    public CssTextUlMode getTextUlMode() {
	if (cssTextUlMode == null) {
	    cssTextUlMode =
		(CssTextUlMode) style.CascadingOrder (
			    new CssTextUlMode(), style, selector);
	}
	return cssTextUlMode;
    }

    public CssTextUlPos getTextUlPos() {
	if (cssTextUlPos == null) {
	    cssTextUlPos =
		(CssTextUlPos) style.CascadingOrder (
			    new CssTextUlPos(), style, selector);
	}
	return cssTextUlPos;
    }

    public CssTextUnderLine getTextUnderLine() {
	if (cssTextUnderLine == null) {
	    cssTextUnderLine =
		(CssTextUnderLine) style.CascadingOrder (
			    new CssTextUnderLine(), style, selector);
	}
	return cssTextUnderLine;
    }

    public CssTextLTStyle getTextLTStyle() {
	if (cssTextLTStyle == null) {
	    cssTextLTStyle =
		(CssTextLTStyle) style.CascadingOrder (
			    new CssTextLTStyle(), style, selector);
	}
	return cssTextLTStyle;
    }

    public CssTextLTColor getTextLTColor() {
	if (cssTextLTColor == null) {
	    cssTextLTColor =
		(CssTextLTColor) style.CascadingOrder (
			    new CssTextLTColor(), style, selector);
	}
	return cssTextLTColor;
    }

    public CssTextLTMode getTextLTMode() {
	if (cssTextLTMode == null) {
	    cssTextLTMode =
		(CssTextLTMode) style.CascadingOrder (
			    new CssTextLTMode(), style, selector);
	}
	return cssTextLTMode;
    }

    public CssTextLineThrough getTextLineThrough() {
	if (cssTextLineThrough == null) {
	    cssTextLineThrough =
		(CssTextLineThrough) style.CascadingOrder (
	         	    new CssTextLineThrough(), style, selector);
	}
	return cssTextLineThrough;
    }

    public CssTextOverLine getTextOverLine() {
	if (cssTextOverLine == null) {
	    cssTextOverLine =
		(CssTextOverLine) style.CascadingOrder (
	         	    new CssTextOverLine(), style, selector);
	}
	return cssTextOverLine;
    }

        public CssTextOLStyle getTextOLStyle() {
	if (cssTextOLStyle == null) {
	    cssTextOLStyle =
		(CssTextOLStyle) style.CascadingOrder (
	         	    new CssTextOLStyle(), style, selector);
	}
	return cssTextOLStyle;
    }

    public CssTextOLColor getTextOLColor() {
	if (cssTextOLColor == null) {
	    cssTextOLColor =
		(CssTextOLColor) style.CascadingOrder (
	         	    new CssTextOLColor(), style, selector);
	}
	return cssTextOLColor;
    }

    public CssTextOLMode getTextOLMode() {
	if (cssTextOLMode == null) {
	    cssTextOLMode =
		(CssTextOLMode) style.CascadingOrder (
	         	    new CssTextOLMode(), style, selector);
	}
	return cssTextOLMode;
    }

    public CssTextCombine getTextCombine() {
	if (cssTextCombine == null) {
	    cssTextCombine =
		(CssTextCombine) style.CascadingOrder (
			    new CssTextCombine(), style, selector);
	}
	return cssTextCombine;
    }

    public CssMedia getMedia() {
	if (cssMedia == null) {
	    cssMedia =
		(CssMedia) style.CascadingOrder (
                            new CssMedia(), style, selector);
	}
	return cssMedia;
    }

    public CssDisplayModel getDisplayModel() {
	if (cssDisplayModel == null) {
	    cssDisplayModel =
		(CssDisplayModel) style.CascadingOrder (
			    new CssDisplayModel(), style, selector);
	}
	return cssDisplayModel;
    }

    public CssDisplayRole getDisplayRole() {
	if (cssDisplayRole == null) {
	    cssDisplayRole =
		(CssDisplayRole) style.CascadingOrder (
			    new CssDisplayRole(), style, selector);
	}
	return cssDisplayRole;
    }

    public CssMarginOutside getMarginOutside() {
	if (cssMarginOutside == null) {
	    cssMarginOutside =
		(CssMarginOutside) style.CascadingOrder (
			    new CssMarginOutside(), style, selector);
	}
	return cssMarginOutside;
    }

    public CssMarginInside getMarginInside() {
	if (cssMarginInside == null) {
	    cssMarginInside =
		(CssMarginInside) style.CascadingOrder (
			    new CssMarginInside(), style, selector);
	}
	return cssMarginInside;
    }

    public CssBoxWidth getBoxWidth() {
	if (cssBoxWidth == null) {
	    cssBoxWidth =
		(CssBoxWidth) style.CascadingOrder (
			    new CssBoxWidth(), style, selector);
	}
	return cssBoxWidth;
    }

    public CssBoxHeight getBoxHeight() {
	if (cssBoxHeight == null) {
	    cssBoxHeight =
		(CssBoxHeight) style.CascadingOrder (
			    new CssBoxHeight(), style, selector);
	}
	return cssBoxHeight;
    }

    public CssFit getFit() {
	if (cssFit == null) {
	    cssFit =
		(CssFit) style.CascadingOrder (
			    new CssFit(), style, selector);
	}
	return cssFit;
    }

    public CssLink getLink() {
	if (cssLink == null) {
	    cssLink =
		(CssLink) style.CascadingOrder (
			    new CssLink(), style, selector);
	}
	return cssLink;
    }

    public CssLinkBehavior getLinkBehavior() {
	if (cssLinkBehavior == null) {
	    cssLinkBehavior =
		(CssLinkBehavior) style.CascadingOrder (
			    new CssLinkBehavior(), style, selector);
	}
	return cssLinkBehavior;
    }

    public CssCollapse getCollapse() {
	if (cssCollapse == null) {
	    cssCollapse =
		(CssCollapse) style.CascadingOrder (
			    new CssCollapse(), style, selector);
	}
	return cssCollapse;
    }

    public CssFloatCSS3 getFloatCSS3() {
	if (cssFloatCSS3 == null) {
	    cssFloatCSS3 =
		(CssFloatCSS3) style.CascadingOrder (
			    new CssFloatCSS3(), style, selector);
	}
	return cssFloatCSS3;
    }

    public CssClearCSS3 getClearCSS3() {
	if (cssClearCSS3 == null) {
	    cssClearCSS3 =
		(CssClearCSS3) style.CascadingOrder (
			    new CssClearCSS3(), style, selector);
	}
	return cssClearCSS3;
    }

    public CssLineHeightPolicy getLineHeightPolicy() {
	if (cssLineHeightPolicy == null) {
	    cssLineHeightPolicy =
		(CssLineHeightPolicy) style.CascadingOrder (
			    new CssLineHeightPolicy(), style, selector);
	}
	return cssLineHeightPolicy;
    }

    public CssLineBoxContain getLineBoxContain() {
	if (cssLineBoxContain == null) {
	    cssLineBoxContain =
		(CssLineBoxContain) style.CascadingOrder (
			    new CssLineBoxContain(), style, selector);
	}
	return cssLineBoxContain;
    }

    public CssColumnWidthPol getColumnWidthPol() {
	if (cssColumnWidthPol == null) {
	    cssColumnWidthPol =
		(CssColumnWidthPol) style.CascadingOrder (
			     new CssColumnWidthPol(), style, selector);
	}
	return cssColumnWidthPol;
    }

    public CssColumnSpaceDistribution getColumnSpaceDistribution() {
	if (cssColumnSpaceDistribution == null) {
	    cssColumnSpaceDistribution =
		(CssColumnSpaceDistribution) style.CascadingOrder(
			     new CssColumnSpaceDistribution(), style, selector);
	}
	return cssColumnSpaceDistribution;
    }

    public CssColumnCount getColumnCount() {
	if (cssColumnCount == null) {
	    cssColumnCount =
		(CssColumnCount) style.CascadingOrder(
			     new CssColumnCount(), style, selector);
	}
	return cssColumnCount;
    }

    public CssColumnWidth getColumnWidth() {
	if (cssColumnWidth == null) {
	    cssColumnWidth =
		(CssColumnWidth) style.CascadingOrder(
			     new CssColumnWidth(), style, selector);
	}
	return cssColumnWidth;
    }

    public CssColumnPadding getColumnPadding() {
	if (cssColumnPadding == null) {
	    cssColumnPadding =
		(CssColumnPadding) style.CascadingOrder(
			     new CssColumnPadding(), style, selector);
	}
	return cssColumnPadding;
    }

    public CssColumnBorder getColumnBorder() {
	if (cssColumnBorder == null) {
	    cssColumnBorder =
		(CssColumnBorder) style.CascadingOrder(
			     new CssColumnBorder(), style, selector);
	}
	return cssColumnBorder;
    }

    public CssColumnBorderColor getColumnBorderColor() {
	if (cssColumnBorderColor == null) {
	    cssColumnBorderColor =
		(CssColumnBorderColor) style.CascadingOrder(
			     new CssColumnBorderColor(), style, selector);
	}
	return cssColumnBorderColor;
    }

    public CssColumnBorderStyle getColumnBorderStyle() {
	if (cssColumnBorderStyle == null) {
	    cssColumnBorderStyle =
		(CssColumnBorderStyle) style.CascadingOrder(
			     new CssColumnBorderStyle(), style, selector);
	}
	return cssColumnBorderStyle;
    }

    public CssColumnBorderWidth getColumnBorderWidth() {
	if (cssColumnBorderWidth == null) {
	    cssColumnBorderWidth =
		(CssColumnBorderWidth) style.CascadingOrder(
			     new CssColumnBorderWidth(), style, selector);
	}
	return cssColumnBorderWidth;
    }

    /*
    public CssColumnSpan getColumnSpan() {
	if (cssColumnSpan == null) {
	    cssColumnSpan =
		(CssColumnSpan) style.CascadingOrder(
			     new CssColumnSpan(), style, selector);
	}
	return cssColumnSpan;
    }
    */
/*
    public ACssVolume getACssVolume() {
	if (cssACssVolume == null) {
	    cssACssVolume =
		(CssACssVolume) style.CascadingOrder(
			     new CssACssVolume(), style, selector);
	}
	return cssACssVolume;
    }
*/
	public CssBackgroundClip getCssBackgroundClip() {
		if (cssBackgroundClip == null) {
			cssBackgroundClip =
				(CssBackgroundClip) style.CascadingOrder(
						new CssBackgroundClip(), style, selector);
		}
		return cssBackgroundClip;
	}

	public CssBackgroundSize getCssBackgroundSize() {
		if (cssBackgroundSize == null) {
			cssBackgroundSize =
				(CssBackgroundSize) style.CascadingOrder(
						new CssBackgroundSize(), style, selector);
		}
		return cssBackgroundSize;
	}

	public CssBackgroundOrigin getCssBackgroundOrigin() {
		if (cssBackgroundOrigin == null) {
			cssBackgroundOrigin =
				(CssBackgroundOrigin) style.CascadingOrder(
						new CssBackgroundOrigin(), style, selector);
		}
		return cssBackgroundOrigin;
	}

	public CssBackgroundQuantity getCssBackgroundQuantity() {
		if (cssBackgroundQuantity == null) {
			cssBackgroundQuantity =
				(CssBackgroundQuantity) style.CascadingOrder(
						new CssBackgroundQuantity(), style, selector);
		}
		return cssBackgroundQuantity;
	}

	public CssBackgroundSpacing getCssBackgroundSpacing() {
		if (cssBackgroundSpacing == null) {
			cssBackgroundSpacing =
				(CssBackgroundSpacing) style.CascadingOrder(
						new CssBackgroundSpacing(), style, selector);
		}
		return cssBackgroundSpacing;
	}

	public CssTextDecorationCSS3 getCssTextDecoration() {
		if (cssTextDecoration == null) {
			cssTextDecoration =
				(CssTextDecorationCSS3) style.CascadingOrder(
						new CssTextDecorationCSS3(), style, selector);
		}
		return cssTextDecoration;
	}

	public CssWhiteSpaceCSS3 getCssWhiteSpace() {
		if (cssWhiteSpace == null) {
			cssWhiteSpace =
				(CssWhiteSpaceCSS3) style.CascadingOrder(
						new CssWhiteSpaceCSS3(), style, selector);
		}
		return cssWhiteSpace;
	}

	public CssWordSpacingCSS3 getCssWordSpacing() {
		if (cssWordSpacing == null) {
			cssWordSpacing =
				(CssWordSpacingCSS3) style.CascadingOrder(
						new CssWordSpacingCSS3(), style, selector);
		}
		return cssWordSpacing;
	}


    /**
     * Print this style
     *
     * @param printer The printer interface
     */
    public void print(CssPrinterStyle printer) {
	super.print(printer);

	if (cssOpacity != null) {
	    cssOpacity.print(printer);
	}
	if (cssRenderIntent != null) {
	    cssRenderIntent.print(printer);
	}
	if (cssColorProfile != null) {
	    cssColorProfile.print(printer);
	}
	if (cssFontEffect != null) {
	    cssFontEffect.print(printer);
	}
	if (cssFontSmooth != null) {
	    cssFontEffect.print(printer);
	}
	if (cssFontEmphasizeStyle != null) {
	    cssFontEmphasizeStyle.print(printer);
	}
	if (cssFontEmphasizePos != null) {
	    cssFontEmphasizeStyle.print(printer);
	}
	if (cssFontEmphasize != null) {
	    cssFontEmphasize.print(printer);
	}
	if (cssRubyPosition != null) {
	    cssRubyPosition.print(printer);
	}
	if (cssRubyAlign != null) {
	    cssRubyAlign.print(printer);
	}
	if (cssRubyOverhang != null) {
	    cssRubyOverhang.print(printer);
	}
	if (cssBoxSizing != null) {
	    cssBoxSizing.print(printer);
	}
	if (cssResizer != null) {
	    cssResizer.print(printer);
	}
	if (cssKeyEq != null) {
	    cssKeyEq.print(printer);
	}
	if (cssTabIndex != null) {
	    cssTabIndex.print(printer);
	}
	if (cssUserInput != null) {
	    cssUserInput.print(printer);
	}
	if (cssUserModify != null) {
	    cssUserModify.print(printer);
	}
	if (cssUserSelect != null) {
	    cssUserSelect.print(printer);
	}
	if (cssUserFocus != null) {
	    cssUserFocus.print(printer);
	}
	if (cssUserFocusKey != null) {
	    cssUserFocusKey.print(printer);
	}
	if (cssUserFocusPointer != null) {
	    cssUserFocusPointer.print(printer);
	}
	if (cssToggleGroup != null) {
	    cssToggleGroup.print(printer);
	}
	if (cssGroupReset != null) {
	    cssGroupReset.print(printer);
	}
	if (cssWritingMode != null) {
	    cssWritingMode.print(printer);
	}
	if (cssGlyphOrVert != null) {
	    cssGlyphOrVert.print(printer);
	}
	if (cssGlyphHor != null) {
	    cssGlyphHor.print(printer);
	}
	if (cssTextJustify != null) {
	    cssTextJustify.print(printer);
	}
	if (cssTextAlignLast != null) {
	    cssTextAlignLast.print(printer);
	}
	if (cssMinFontSize != null) {
	    cssMinFontSize.print(printer);
	}
	if (cssMaxFontSize != null) {
	    cssMaxFontSize.print(printer);
	}
	if (cssTextJustifyTrim != null) {
	    cssTextJustifyTrim.print(printer);
	}
	if (cssTextKashidaSpace != null) {
	    cssTextKashidaSpace.print(printer);
	}
	if (cssDominantBaseLine != null) {
	    cssDominantBaseLine.print(printer);
	}
	if (cssAlignmentBaseLine != null) {
	    cssAlignmentBaseLine.print(printer);
	}
	if (cssAlignmentAdjust != null) {
	    cssAlignmentAdjust.print(printer);
	}
	if (cssBaselineShift != null) {
	    cssBaselineShift.print(printer);
	}
	if (cssLineBreak != null) {
	    cssLineBreak.print(printer);
	}
	if (cssWordBreakCJK != null) {
	    cssWordBreakCJK.print(printer);
	}
	if (cssWordBreakInside != null) {
	    cssWordBreakInside.print(printer);
	}
	if (cssWordBreak != null) {
	    cssWordBreak.print(printer);
	}
	if (cssTextSpace != null) {
	    cssTextSpace.print(printer);
	}
	if (cssTextEndOverflow != null) {
	    cssTextEndOverflow.print(printer);
	}
	if (cssTextAfterOverflow != null) {
	    cssTextAfterOverflow.print(printer);
	}
	if (cssPunctuationTrim != null) {
	    cssPunctuationTrim.print(printer);
	}
	if (cssTextAutoSpace != null) {
	    cssTextAutoSpace.print(printer);
	}
	if (cssKerningMode != null) {
	    cssKerningMode.print(printer);
	}
	if (cssKerningPairTreshold != null) {
	    cssKerningPairTreshold.print(printer);
	}
	if (cssTextUlStyle != null) {
	    cssTextUlStyle.print(printer);
	}
	if (cssTextUlColor != null) {
	    cssTextUlColor.print(printer);
	}
	if (cssTextUlMode != null) {
	    cssTextUlMode.print(printer);
	}
	if (cssTextUlPos != null) {
	    cssTextUlPos.print(printer);
	}
	if (cssTextUnderLine != null) {
	    cssTextUnderLine.print(printer);
	}
	if (cssTextLTStyle != null) {
	    cssTextLTStyle.print(printer);
	}
	if (cssTextLTColor != null) {
	    cssTextLTColor.print(printer);
	}
	if (cssTextLTMode != null) {
	    cssTextLTMode.print(printer);
	}
	if (cssTextLineThrough != null) {
	    cssTextLineThrough.print(printer);
	}
	if (cssTextOLStyle != null) {
	    cssTextOLStyle.print(printer);
	}
	if (cssTextOLColor != null) {
	    cssTextOLColor.print(printer);
	}
	if (cssTextOLMode != null) {
	    cssTextOLMode.print(printer);
	}
	if (cssTextOverLine != null) {
	    cssTextOverLine.print(printer);
	}
	if (cssTextCombine != null) {
	    cssTextCombine.print(printer);
	}
	if (cssMedia != null) {
	    cssMedia.print(printer);
	}
	if (cssDisplayModel != null) {
	    cssDisplayModel.print(printer);
	}
	if (cssDisplayRole != null) {
	    cssDisplayRole.print(printer);
	}
	if (cssMarginInside != null) {
	    cssMarginInside.print(printer);
	}
	if (cssMarginOutside != null) {
	    cssMarginOutside.print(printer);
	}
	if (cssFit != null) {
	    cssFit.print(printer);
	}
	if (cssLink != null) {
	    cssLink.print(printer);
	}
	if (cssLinkBehavior != null) {
	    cssLinkBehavior.print(printer);
	}
	if (cssCollapse != null) {
	    cssCollapse.print(printer);
	}
	if (cssFloatCSS3 != null) {
	    cssFloatCSS3.print(printer);
	}
	if (cssClearCSS3 != null) {
	    cssClearCSS3.print(printer);
	}
	if (cssLineHeightPolicy != null) {
	    cssLineHeightPolicy.print(printer);
	}
	if (cssLineBoxContain != null) {
	    cssLineBoxContain.print(printer);
	}
	if (cssColumnWidthPol != null) {
	    cssColumnWidthPol.print(printer);
	}
	if (cssColumnSpaceDistribution != null) {
	    cssColumnSpaceDistribution.print(printer);
	}
	if (cssColumnCount != null) {
	    cssColumnCount.print(printer);
	}
	if (cssColumnWidth != null) {
	    cssColumnWidth.print(printer);
	}
	if (cssColumnPadding != null) {
	    cssColumnPadding.print(printer);
	}
	if (cssColumnBorder != null) {
	    cssColumnBorder.print(printer);
	}
	if (cssColumnBorderColor != null) {
	    cssColumnBorder.print(printer);
	}
	if (cssColumnBorderStyle != null) {
	    cssColumnBorderStyle.print(printer);
	}
	if (cssColumnBorderWidth != null) {
	    cssColumnBorderWidth.print(printer);
	}
	if (cssColumnSpan != null) {
	    cssColumnSpan.print(printer);
	}
	//if (cssACssVolume != null) {
	//    cssACssVolume.print(printer);
	//}
	if (cssBackgroundClip != null) {
		cssBackgroundClip.print(printer);
	}
	if (cssBackgroundSize != null) {
		cssBackgroundSize.print(printer);
	}
	if (cssBackgroundOrigin != null) {
		cssBackgroundOrigin.print(printer);
	}
	if (cssBackgroundQuantity != null) {
		cssBackgroundQuantity.print(printer);
	}
	if (cssBackgroundSpacing != null) {
		cssBackgroundSpacing.print(printer);
	}
	if (cssTextDecoration != null) {
		cssTextDecoration.print(printer);
	}
	if (cssWhiteSpace != null) {
		cssWhiteSpace.print(printer);
	}
	if (cssWordSpacing != null) {
		cssWordSpacing.print(printer);
	}
    }

    /**
     * Returns the name of the actual selector
     */
    public String getSelector()
    {
	return (selector.getElement().toLowerCase());
    }

    /*    public boolean isRubyText() {
	  return(((selector.getElement()).toLowerCase() == "ruby") ||
	  ((selector.getElement()).toLowerCase() == "rb") ||
	  ((selector.getElement()).toLowerCase() == "rt") ||
	  ((selector.getElement()).toLowerCase() == "rbc") ||
	  ((selector.getElement()).toLowerCase() == "rtc"));
	  }

	  public void findConflicts(ApplContext ac) {
	  if ((cssRubyPosition != null)
	  && (selector != null)
	  && (!isRubyText())) {
	  warnings.addWarning(new Warning(cssRubyPosition,
	  "ruby-text", 1, ac));
	  }

	  if ((cssRubyOverhang != null)
	  && (selector != null)
	  && (!isRubyText())) {
	  warnings.addWarning(new Warning(cssRubyOverhang,
	  "ruby-text", 1, ac));
	  }
	  }
    */

}

//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssPrinterStyle;

public class Css3Style extends org.w3c.css.properties.atsc.ATSCStyle {

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
    CssColumnSpan cssColumnSpan;
	CssBackgroundClip cssBackgroundClip;
	CssBackgroundSize cssBackgroundSize;
	CssBackgroundOrigin cssBackgroundOrigin;
	CssBackgroundQuantity cssBackgroundQuantity;
	CssBackgroundSpacing cssBackgroundSpacing;
	CssTextDecorationCSS3 cssTextDecoration;
	CssWhiteSpaceCSS3 cssWhiteSpace;
	CssWordSpacingCSS3 cssWordSpacing;
	CssAllSpaceTreatment cssAllSpaceTreatment;
	CssHangingPunctuation cssHangingPunctuation;
	CssLineGrid cssLineGrid;
	CssLineGridMode cssLineGridMode;
	CssLineGridProgression cssLineGridProgression;
	CssLinefeedTreatment cssLinefeedTreatment;
	CssScript cssScript;
	CssTextOverflow cssTextOverflow;
	CssTextOverflowEllipsis cssTextOverflowEllipsis;
	CssTextOverflowMode cssTextOverflowMode;
	CssWhiteSpaceTreatment cssWhiteSpaceTreatment;
	CssWrapOption cssWrapOption;
	CssColumnGap cssColumnGap;
	CssColumnRuleColor cssColumnRuleColor;
	CssColumnRuleStyle cssColumnRuleStyle;
	CssColumnRuleWidth cssColumnRuleWidth;
	CssColumnRule cssColumnRule;
	CssDropInitialAfterAdjust cssDropInitialAfterAdjust;
	CssDropInitialAfterAlign cssDropInitialAfterAlign;
	CssDropInitialBeforeAdjust cssDropInitialBeforeAdjust;
	CssDropInitialBeforeAlign cssDropInitialBeforeAlign;
	CssDropInitialSize cssDropInitialSize;
	CssDropInitialValue cssDropInitialValue;
	CssInlineBoxAlign cssInlineBoxAlign;
	CssLineStacking cssLineStacking;
	CssLineStackingRuby cssLineStackingRuby;
	CssLineStackingShift cssLineStackingShift;
	CssLineStackingStrategy cssLineStackingStrategy;
	CssTextHeight cssTextHeight;
	CssAppearance cssAppearance;
	CssIcon cssIcon;
	CssNavIndex cssNavIndex;
	CssNavUp cssNavUp;
	CssNavRight cssNavRight;
	CssNavDown cssNavDown;
	CssNavLeft cssNavLeft;
	CssOutlineOffset cssOutlineOffset;
	CssOverflowX cssOverflowX;
	CssOverflowY cssOverflowY;
	CssRubySpan cssRubySpan;
	CssTextBlink cssTextBlink;
	CssClearAfter cssClearAfter;
	CssCrop cssCrop;
	CssFitPosition cssFitPosition;
	CssFloatDisplace cssFloatDisplace;
	CssIndentEdgeReset cssIndentEdgeReset;
	CssVisibilityCSS3 cssVisibilityCSS3;
	CssOverflowCSS3 cssOverflowCSS3;
	CssOverflowClip cssOverflowClip;
	CssMaxWidthCSS3 cssMaxWidthCSS3;
	CssMaxHeightCSS3 cssMaxHeightCSS3;
	CssMinWidthCSS3 cssMinWidthCSS3;
	CssMinHeightCSS3 cssMinHeightCSS3;
	CssPaddingCSS3 cssPaddingCSS3;
	CssPaddingBottomCSS3 cssPaddingBottomCSS3;
	CssPaddingTopCSS3 cssPaddingTopCSS3;
	CssPaddingLeftCSS3 cssPaddingLeftCSS3;
	CssPaddingRightCSS3 cssPaddingRightCSS3;
	CssHeightCSS3 cssHeightCSS3;
	CssWidthCSS3 cssWidthCSS3;
	CssMarquee cssMarquee;
	CssMarqueeDirection cssMarqueeDirection;
	CssMarqueeRepetition cssMarqueeRepetition;
	CssMarqueeSpeed cssMarqueeSpeed;
	CssMarqueeStyle cssMarqueeStyle;
	CssBorderTopRightRadius cssBorderTopRightRadius;
	CssBorderBottomRightRadius cssBorderBottomRightRadius;
	CssBorderBottomLeftRadius cssBorderBottomLeftRadius;
	CssBorderTopLeftRadius cssBorderTopLeftRadius;
	CssBorderRadius cssBorderRadius;
	CssBorderTopImage cssBorderTopImage;
	CssBorderBottomImage cssBorderBottomImage;
	CssBorderLeftImage cssBorderLeftImage;
	CssBorderRightImage cssBorderRightImage;
	CssBorderImage cssBorderImage;
	CssBorderTopLeftImage cssBorderTopLeftImage;
	CssBorderTopRightImage cssBorderTopRightImage;
	CssBorderBottomLeftImage cssBorderBottomLeftImage;
	CssBorderBottomRightImage cssBorderBottomRightImage;
	CssBorderCornerImage cssBorderCornerImage;
	CssBorderTopFitLength cssBorderTopFitLength;
	CssBorderBottomFitLength cssBorderBottomFitLength;
	CssBorderLeftFitLength cssBorderLeftFitLength;
	CssBorderRightFitLength cssBorderRightFitLength;
	CssBorderTopFitWidth cssBorderTopFitWidth;
	CssBorderBottomFitWidth cssBorderBottomFitWidth;
	CssBorderLeftFitWidth cssBorderLeftFitWidth;
	CssBorderRightFitWidth cssBorderRightFitWidth;
	CssBorderTopLeftFitLength cssBorderTopLeftFitLength;
	CssBorderTopRightFitLength cssBorderTopRightFitLength;
	CssBorderBottomRightFitLength cssBorderBottomRightFitLength;
	CssBorderBottomLeftFitLength cssBorderBottomLeftFitLength;
	CssBorderTopLeftFitWidth cssBorderTopLeftFitWidth;
	CssBorderTopRightFitWidth cssBorderTopRightFitWidth;
	CssBorderBottomLeftFitWidth cssBorderBottomLeftFitWidth;
	CssBorderBottomRightFitWidth cssBorderBottomRightFitWidth;
	CssBorderFitLength cssBorderFitLength;
	CssBorderFitWidth cssBorderFitWidth;
	CssBorderFit cssBorderFit;
	CssBorderCornerFit cssBorderCornerFit;
	CssBorderImageTransform cssBorderImageTransform;
	CssBorderBreak cssBorderBreak;
	CssBoxShadow cssBoxShadow;
	CssTextIndentCSS3 cssTextIndentCSS3;
	CssBlockProgression cssBlockProgression;

	//media features for media queries

	MediaWidth mediaWidth;
	MediaMaxWidth mediaMaxWidth;
	MediaMinWidth mediaMinWidth;
	MediaHeight mediaHeight;
	MediaMinHeight mediaMinHeight;
	MediaMaxHeight mediaMaxHeight;
	MediaDeviceWidth mediaDeviceWidth;
	MediaMinDeviceWidth mediaMinDeviceWidth;
	MediaMaxDeviceWidth mediaMaxDeviceWidth;
	MediaDeviceHeight mediaDeviceHeight;
	MediaMinDeviceHeight mediaMinDeviceHeight;
	MediaMaxDeviceHeight mediaMaxDeviceHeight;
	MediaDeviceAspectRatio mediaDeviceAspectRatio;
	MediaMaxDeviceAspectRatio mediaMaxDeviceAspectRatio;
	MediaMinDeviceAspectRatio mediaMinDeviceAspectRatio;
	MediaColor mediaColor;
	MediaMinColor mediaMinColor;
	MediaMaxColor mediaMaxColor;
	MediaColorIndex mediaColorIndex;
	MediaMinColorIndex mediaMinColorIndex;
	MediaMaxColorIndex mediaMaxColorIndex;
	MediaMonochrome mediaMonochrome;
	MediaMinMonochrome mediaMinMonochrome;
	MediaMaxMonochrome mediaMaxMonochrome;
	MediaResolution mediaResolution;
	MediaMinResolution mediaMinResolution;
	MediaMaxResolution mediaMaxResolution;
	MediaScan mediaScan;
	MediaGrid mediaGrid;

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

	public CssAllSpaceTreatment getAllSpaceTreatment() {
		if (cssAllSpaceTreatment == null) {
			cssAllSpaceTreatment =
				(CssAllSpaceTreatment) style.CascadingOrder(
						new CssAllSpaceTreatment(), style, selector);
		}
		return cssAllSpaceTreatment;
	}

	public CssHangingPunctuation getHangingPunctuation() {
		if (cssHangingPunctuation == null) {
			cssHangingPunctuation =
				(CssHangingPunctuation) style.CascadingOrder(
						new CssHangingPunctuation(), style, selector);
		}
		return cssHangingPunctuation;
	}

	public CssLineGrid getLineGrid() {
		if (cssLineGrid == null) {
			cssLineGrid =
				(CssLineGrid) style.CascadingOrder(
						new CssLineGrid(), style, selector);
		}
		return cssLineGrid;
	}

	public CssLineGridMode getLineGridMode() {
		if (cssLineGridMode == null) {
			cssLineGridMode =
				(CssLineGridMode) style.CascadingOrder(
						new CssLineGridMode(), style, selector);
		}
		return cssLineGridMode;
	}

	public CssLineGridProgression getLineGridProgression() {
		if (cssLineGridProgression == null) {
			cssLineGridProgression =
				(CssLineGridProgression) style.CascadingOrder(
						new CssLineGridProgression(), style, selector);
		}
		return cssLineGridProgression;
	}

	public CssLinefeedTreatment getLinefeedTreatment() {
		if (cssLinefeedTreatment == null) {
			cssLinefeedTreatment =
				(CssLinefeedTreatment) style.CascadingOrder(
						new CssLinefeedTreatment(), style, selector);
		}
		return cssLinefeedTreatment;
	}

	public CssScript getScript() {
		if (cssScript == null) {
			cssScript =
				(CssScript) style.CascadingOrder(
						new CssScript(), style, selector);
		}
		return cssScript;
	}

	public CssTextOverflow getTextOverflow() {
		if (cssTextOverflow == null) {
			cssTextOverflow =
				(CssTextOverflow) style.CascadingOrder(
						new CssTextOverflow(), style, selector);
		}
		return cssTextOverflow;
	}

	public CssTextOverflowEllipsis getTextOverflowEllipsis() {
		if (cssTextOverflowEllipsis == null) {
			cssTextOverflowEllipsis =
				(CssTextOverflowEllipsis) style.CascadingOrder(
						new CssTextOverflowEllipsis(), style, selector);
		}
		return cssTextOverflowEllipsis;
	}

	public CssTextOverflowMode getTextOverflowMode() {
		if (cssTextOverflowMode == null) {
			cssTextOverflowMode =
				(CssTextOverflowMode) style.CascadingOrder(
						new CssTextOverflowMode(), style, selector);
		}
		return cssTextOverflowMode;
	}

	public CssWhiteSpaceTreatment getWhiteSpaceTreatment() {
		if (cssWhiteSpaceTreatment == null) {
			cssWhiteSpaceTreatment =
				(CssWhiteSpaceTreatment) style.CascadingOrder(
						new CssWhiteSpaceTreatment(), style, selector);
		}
		return cssWhiteSpaceTreatment;
	}

	public CssWrapOption getWrapOption() {
		if (cssWrapOption == null) {
			cssWrapOption =
				(CssWrapOption) style.CascadingOrder(
						new CssWrapOption(), style, selector);
		}
		return cssWrapOption;
	}

	public CssColumnGap getColumnGap() {
		if (cssColumnGap == null) {
			cssColumnGap =
				(CssColumnGap) style.CascadingOrder(
						new CssColumnGap(), style, selector);
		}
		return cssColumnGap;
	}

	public CssColumnRuleColor getColumnRuleColor() {
		if (cssColumnRuleColor == null) {
			cssColumnRuleColor =
				(CssColumnRuleColor) style.CascadingOrder(
						new CssColumnRuleColor(), style, selector);
		}
		return cssColumnRuleColor;
	}

	public CssColumnRuleStyle getColumnRuleStyle() {
		if (cssColumnRuleStyle == null) {
			cssColumnRuleStyle =
				(CssColumnRuleStyle) style.CascadingOrder(
						new CssColumnRuleStyle(), style, selector);
		}
		return cssColumnRuleStyle;
	}

	public CssColumnRuleWidth getColumnRuleWidth() {
		if (cssColumnRuleWidth == null) {
			cssColumnRuleWidth =
				(CssColumnRuleWidth) style.CascadingOrder(
						new CssColumnRuleWidth(), style, selector);
		}
		return cssColumnRuleWidth;
	}

	public CssColumnRule getColumnRule() {
		if (cssColumnRule == null) {
			cssColumnRule =
				(CssColumnRule) style.CascadingOrder(
						new CssColumnRule(), style, selector);
		}
		return cssColumnRule;
	}

	public CssDropInitialAfterAdjust getDropInitialAfterAdjust() {
		if (cssDropInitialAfterAdjust == null) {
			cssDropInitialAfterAdjust =
				(CssDropInitialAfterAdjust) style.CascadingOrder(
						new CssDropInitialAfterAdjust(), style, selector);
		}
		return cssDropInitialAfterAdjust;
	}

	public CssDropInitialAfterAlign getDropInitialAfterAlign() {
		if (cssDropInitialAfterAlign == null) {
			cssDropInitialAfterAlign =
				(CssDropInitialAfterAlign) style.CascadingOrder(
						new CssDropInitialAfterAlign(), style, selector);
		}
		return cssDropInitialAfterAlign;
	}

	public CssDropInitialBeforeAdjust getDropInitialBeforeAdjust() {
		if (cssDropInitialBeforeAdjust == null) {
			cssDropInitialBeforeAdjust =
				(CssDropInitialBeforeAdjust) style.CascadingOrder(
						new CssDropInitialBeforeAdjust(), style, selector);
		}
		return cssDropInitialBeforeAdjust;
	}

	public CssDropInitialBeforeAlign getDropInitialBeforeAlign() {
		if (cssDropInitialBeforeAlign == null) {
			cssDropInitialBeforeAlign =
				(CssDropInitialBeforeAlign) style.CascadingOrder(
						new CssDropInitialBeforeAlign(), style, selector);
		}
		return cssDropInitialBeforeAlign;
	}

	public CssDropInitialSize getDropInitialSize() {
		if (cssDropInitialSize == null) {
			cssDropInitialSize =
				(CssDropInitialSize) style.CascadingOrder(
						new CssDropInitialSize(), style, selector);
		}
		return cssDropInitialSize;
	}

	public CssDropInitialValue getDropInitialValue() {
		if (cssDropInitialValue == null) {
			cssDropInitialValue =
				(CssDropInitialValue) style.CascadingOrder(
						new CssDropInitialValue(), style, selector);
		}
		return cssDropInitialValue;
	}

	public CssInlineBoxAlign getInlineBoxAlign() {
		if (cssInlineBoxAlign == null) {
			cssInlineBoxAlign =
				(CssInlineBoxAlign) style.CascadingOrder(
						new CssInlineBoxAlign(), style, selector);
		}
		return cssInlineBoxAlign;
	}

	public CssLineStacking getLineStacking() {
		if (cssLineStacking == null) {
			cssLineStacking =
				(CssLineStacking) style.CascadingOrder(
						new CssLineStacking(), style, selector);
		}
		return cssLineStacking;
	}

	public CssLineStackingRuby getLineStackingRuby() {
		if (cssLineStackingRuby == null) {
			cssLineStackingRuby =
				(CssLineStackingRuby) style.CascadingOrder(
						new CssLineStackingRuby(), style, selector);
		}
		return cssLineStackingRuby;
	}

	public CssLineStackingShift getLineStackingShift() {
		if (cssLineStackingShift == null) {
			cssLineStackingShift =
				(CssLineStackingShift) style.CascadingOrder(
						new CssLineStackingShift(), style, selector);
		}
		return cssLineStackingShift;
	}

	public CssLineStackingStrategy getLineStackingStrategy() {
		if (cssLineStackingStrategy == null) {
			cssLineStackingStrategy =
				(CssLineStackingStrategy) style.CascadingOrder(
						new CssLineStackingStrategy(), style, selector);
		}
		return cssLineStackingStrategy;
	}

	public CssTextHeight getTextHeight() {
		if (cssTextHeight == null) {
			cssTextHeight =
				(CssTextHeight) style.CascadingOrder(
						new CssTextHeight(), style, selector);
		}
		return cssTextHeight;
	}

	public CssAppearance getAppearance() {
		if (cssAppearance == null) {
			cssAppearance =
				(CssAppearance) style.CascadingOrder(
						new CssAppearance(), style, selector);
		}
		return cssAppearance;
	}

	public CssIcon getIcon() {
		if (cssIcon == null) {
			cssIcon =
				(CssIcon) style.CascadingOrder(
						new CssIcon(), style, selector);
		}
		return cssIcon;
	}

	public CssNavIndex getNavIndexCSS3() {
		if (cssNavIndex == null) {
			cssNavIndex =
				(CssNavIndex) style.CascadingOrder(
						new CssNavIndex(), style, selector);
		}
		return cssNavIndex;
	}

	public CssNavUp getNavUpCSS3() {
		if (cssNavUp == null) {
			cssNavUp =
				(CssNavUp) style.CascadingOrder(
						new CssNavUp(), style, selector);
		}
		return cssNavUp;
	}

	public CssNavRight getNavRightCSS3() {
		if (cssNavRight == null) {
			cssNavRight =
				(CssNavRight) style.CascadingOrder(
						new CssNavRight(), style, selector);
		}
		return cssNavRight;
	}

	public CssNavDown getNavDownCSS3() {
		if (cssNavDown == null) {
			cssNavDown =
				(CssNavDown) style.CascadingOrder(
						new CssNavDown(), style, selector);
		}
		return cssNavDown;
	}

	public CssNavLeft getNavLeftCSS3() {
		if (cssNavLeft == null) {
			cssNavLeft =
				(CssNavLeft) style.CascadingOrder(
						new CssNavLeft(), style, selector);
		}
		return cssNavLeft;
	}

	public CssOutlineOffset getOutlineOffset() {
		if (cssOutlineOffset == null) {
			cssOutlineOffset =
				(CssOutlineOffset) style.CascadingOrder(
						new CssOutlineOffset(), style, selector);
		}
		return cssOutlineOffset;
	}

	public CssOverflowX getOverflowX() {
		if (cssOverflowX == null) {
			cssOverflowX =
				(CssOverflowX) style.CascadingOrder(
						new CssOverflowX(), style, selector);
		}
		return cssOverflowX;
	}

	public CssOverflowY getOverflowY() {
		if (cssOverflowY == null) {
			cssOverflowY =
				(CssOverflowY) style.CascadingOrder(
						new CssOverflowY(), style, selector);
		}
		return cssOverflowY;
	}

	public CssRubySpan getRubySpan() {
		if (cssRubySpan == null) {
			cssRubySpan =
				(CssRubySpan) style.CascadingOrder(
						new CssRubySpan(), style, selector);
		}
		return cssRubySpan;
	}

	public CssTextBlink getTextBlink() {
		if (cssTextBlink == null) {
			cssTextBlink =
				(CssTextBlink) style.CascadingOrder(
						new CssTextBlink(), style, selector);
		}
		return cssTextBlink;
	}

	public CssClearAfter getClearAfter() {
		if (cssClearAfter == null) {
			cssClearAfter =
				(CssClearAfter) style.CascadingOrder(
						new CssClearAfter(), style, selector);
		}
		return cssClearAfter;
	}

	public CssCrop getCrop() {
		if (cssCrop == null) {
			cssCrop =
				(CssCrop) style.CascadingOrder(
						new CssCrop(), style, selector);
		}
		return cssCrop;
	}

	public CssFitPosition getFitPosition() {
		if (cssFitPosition == null) {
			cssFitPosition =
				(CssFitPosition) style.CascadingOrder(
						new CssFitPosition(), style, selector);
		}
		return cssFitPosition;
	}

	public CssFloatDisplace getFloatDisplace() {
		if (cssFloatDisplace == null) {
			cssFloatDisplace =
				(CssFloatDisplace) style.CascadingOrder(
						new CssFloatDisplace(), style, selector);
		}
		return cssFloatDisplace;
	}

	public CssIndentEdgeReset getIndentEdgeReset() {
		if (cssIndentEdgeReset == null) {
			cssIndentEdgeReset =
				(CssIndentEdgeReset) style.CascadingOrder(
						new CssIndentEdgeReset(), style, selector);
		}
		return cssIndentEdgeReset;
	}

	public CssVisibilityCSS3 getVisibilityCSS3() {
		if (cssVisibilityCSS3 == null) {
			cssVisibilityCSS3 =
				(CssVisibilityCSS3) style.CascadingOrder(
						new CssVisibilityCSS3(), style, selector);
		}
		return cssVisibilityCSS3;
	}

	public CssOverflowCSS3 getOverflowCSS3() {
		if (cssOverflowCSS3 == null) {
			cssOverflowCSS3 =
				(CssOverflowCSS3) style.CascadingOrder(
						new CssOverflowCSS3(), style, selector);
		}
		return cssOverflowCSS3;
	}

	public CssOverflowClip getOverflowClip() {
		if (cssOverflowClip == null) {
			cssOverflowClip =
				(CssOverflowClip) style.CascadingOrder(
						new CssOverflowClip(), style, selector);
		}
		return cssOverflowClip;
	}

	public CssMaxHeightCSS3 getMaxHeightCSS3() {
		if (cssMaxHeightCSS3 == null) {
			cssMaxHeightCSS3 =
				(CssMaxHeightCSS3) style.CascadingOrder(
						new CssMaxHeightCSS3(), style, selector);
		}
		return cssMaxHeightCSS3;
	}

	public CssMaxWidthCSS3 getMaxWidthCSS3() {
		if (cssMaxWidthCSS3 == null) {
			cssMaxWidthCSS3 =
				(CssMaxWidthCSS3) style.CascadingOrder(
						new CssMaxWidthCSS3(), style, selector);
		}
		return cssMaxWidthCSS3;
	}

	public CssMinHeightCSS3 getMinHeightCSS3() {
		if (cssMinHeightCSS3 == null) {
			cssMinHeightCSS3 =
				(CssMinHeightCSS3) style.CascadingOrder(
						new CssMinHeightCSS3(), style, selector);
		}
		return cssMinHeightCSS3;
	}

	public CssMinWidthCSS3 getMinWidthCSS3() {
		if (cssMinWidthCSS3 == null) {
			cssMinWidthCSS3 =
				(CssMinWidthCSS3) style.CascadingOrder(
						new CssMinWidthCSS3(), style, selector);
		}
		return cssMinWidthCSS3;
	}

	public CssPaddingCSS3 getPaddingCSS3() {
		if (cssPaddingCSS3 == null) {
			cssPaddingCSS3 =
				(CssPaddingCSS3) style.CascadingOrder(
						new CssPaddingCSS3(), style, selector);
		}
		return cssPaddingCSS3;
	}

	public CssPaddingTopCSS3 getPaddingTopCSS3() {
		if (cssPaddingTopCSS3 == null) {
			cssPaddingTopCSS3 =
				(CssPaddingTopCSS3) style.CascadingOrder(
						new CssPaddingTopCSS3(), style, selector);
		}
		return cssPaddingTopCSS3;
	}

	public CssPaddingBottomCSS3 getPaddingBottomCSS3() {
		if (cssPaddingBottomCSS3 == null) {
			cssPaddingBottomCSS3 =
				(CssPaddingBottomCSS3) style.CascadingOrder(
						new CssPaddingBottomCSS3(), style, selector);
		}
		return cssPaddingBottomCSS3;
	}

	public CssPaddingLeftCSS3 getPaddingLeftCSS3() {
		if (cssPaddingLeftCSS3 == null) {
			cssPaddingLeftCSS3 =
				(CssPaddingLeftCSS3) style.CascadingOrder(
						new CssPaddingLeftCSS3(), style, selector);
		}
		return cssPaddingLeftCSS3;
	}

	public CssPaddingRightCSS3 getPaddingRightCSS3() {
		if (cssPaddingRightCSS3 == null) {
			cssPaddingRightCSS3 =
				(CssPaddingRightCSS3) style.CascadingOrder(
						new CssPaddingRightCSS3(), style, selector);
		}
		return cssPaddingRightCSS3;
	}

	public CssHeightCSS3 getHeightCSS3() {
		if (cssHeightCSS3 == null) {
			cssHeightCSS3 =
				(CssHeightCSS3) style.CascadingOrder(
						new CssHeightCSS3(), style, selector);
		}
		return cssHeightCSS3;
	}

	public CssWidthCSS3 getWidthCSS3() {
		if (cssWidthCSS3 == null) {
			cssWidthCSS3 =
				(CssWidthCSS3) style.CascadingOrder(
						new CssWidthCSS3(), style, selector);
		}
		return cssWidthCSS3;
	}

	public CssMarquee getMarquee() {
		if (cssMarquee == null) {
			cssMarquee =
				(CssMarquee) style.CascadingOrder(
						new CssMarquee(), style, selector);
		}
		return cssMarquee;
	}

	public CssMarqueeDirection getMarqueeDirection() {
		if (cssMarqueeDirection == null) {
			cssMarqueeDirection =
				(CssMarqueeDirection) style.CascadingOrder(
						new CssMarqueeDirection(), style, selector);
		}
		return cssMarqueeDirection;
	}

	public CssMarqueeRepetition getMarqueeRepetition() {
		if (cssMarqueeRepetition == null) {
			cssMarqueeRepetition =
				(CssMarqueeRepetition) style.CascadingOrder(
						new CssMarqueeRepetition(), style, selector);
		}
		return cssMarqueeRepetition;
	}

	public CssMarqueeSpeed getMarqueeSpeed() {
		if (cssMarqueeSpeed == null) {
			cssMarqueeSpeed =
				(CssMarqueeSpeed) style.CascadingOrder(
						new CssMarqueeSpeed(), style, selector);
		}
		return cssMarqueeSpeed;
	}

	public CssMarqueeStyle getMarqueeStyle() {
		if (cssMarqueeStyle == null) {
			cssMarqueeStyle =
				(CssMarqueeStyle) style.CascadingOrder(
						new CssMarqueeStyle(), style, selector);
		}
		return cssMarqueeStyle;
	}

	public CssBorderTopRightRadius getBorderTopRightRadius() {
		if (cssBorderTopRightRadius == null) {
			cssBorderTopRightRadius =
				(CssBorderTopRightRadius) style.CascadingOrder(
						new CssBorderTopRightRadius(), style, selector);
		}
		return cssBorderTopRightRadius;
	}

	public CssBorderBottomRightRadius getBorderBottomRightRadius() {
		if (cssBorderBottomRightRadius == null) {
			cssBorderBottomRightRadius =
				(CssBorderBottomRightRadius) style.CascadingOrder(
						new CssBorderBottomRightRadius(), style, selector);
		}
		return cssBorderBottomRightRadius;
	}

	public CssBorderBottomLeftRadius getBorderBottomLeftRadius() {
		if (cssBorderBottomLeftRadius == null) {
			cssBorderBottomLeftRadius =
				(CssBorderBottomLeftRadius) style.CascadingOrder(
						new CssBorderBottomLeftRadius(), style, selector);
		}
		return cssBorderBottomLeftRadius;
	}

	public CssBorderTopLeftRadius getBorderTopLeftRadius() {
		if (cssBorderTopLeftRadius == null) {
			cssBorderTopLeftRadius =
				(CssBorderTopLeftRadius) style.CascadingOrder(
						new CssBorderTopLeftRadius(), style, selector);
		}
		return cssBorderTopLeftRadius;
	}

	public CssBorderRadius getBorderRadius() {
		if (cssBorderRadius == null) {
			cssBorderRadius =
				(CssBorderRadius) style.CascadingOrder(
						new CssBorderRadius(), style, selector);
		}
		return cssBorderRadius;
	}

	public CssBorderTopImage getBorderTopImage() {
		if (cssBorderTopImage == null) {
			cssBorderTopImage =
				(CssBorderTopImage) style.CascadingOrder(
						new CssBorderTopImage(), style, selector);
		}
		return cssBorderTopImage;
	}

	public CssBorderBottomImage getBorderBottomImage() {
		if (cssBorderBottomImage == null) {
			cssBorderBottomImage =
				(CssBorderBottomImage) style.CascadingOrder(
						new CssBorderBottomImage(), style, selector);
		}
		return cssBorderBottomImage;
	}

	public CssBorderLeftImage getBorderLeftImage() {
		if (cssBorderLeftImage == null) {
			cssBorderLeftImage =
				(CssBorderLeftImage) style.CascadingOrder(
						new CssBorderLeftImage(), style, selector);
		}
		return cssBorderLeftImage;
	}

	public CssBorderRightImage getBorderRightImage() {
		if (cssBorderRightImage == null) {
			cssBorderRightImage =
				(CssBorderRightImage) style.CascadingOrder(
						new CssBorderRightImage(), style, selector);
		}
		return cssBorderRightImage;
	}

	public CssBorderImage getBorderImage() {
		if (cssBorderImage == null) {
			cssBorderImage =
				(CssBorderImage) style.CascadingOrder(
						new CssBorderImage(), style, selector);
		}
		return cssBorderImage;
	}

	public CssBorderTopLeftImage getBorderTopLeftImage() {
		if (cssBorderTopLeftImage == null) {
			cssBorderTopLeftImage =
				(CssBorderTopLeftImage) style.CascadingOrder(
						new CssBorderTopLeftImage(), style, selector);
		}
		return cssBorderTopLeftImage;
	}

	public CssBorderTopRightImage getBorderTopRightImage() {
		if (cssBorderTopRightImage == null) {
			cssBorderTopRightImage =
				(CssBorderTopRightImage) style.CascadingOrder(
						new CssBorderTopRightImage(), style, selector);
		}
		return cssBorderTopRightImage;
	}

	public CssBorderBottomLeftImage getBorderBottomLeftImage() {
		if (cssBorderBottomLeftImage == null) {
			cssBorderBottomLeftImage =
				(CssBorderBottomLeftImage) style.CascadingOrder(
						new CssBorderBottomLeftImage(), style, selector);
		}
		return cssBorderBottomLeftImage;
	}

	public CssBorderBottomRightImage getBorderBottomRightImage() {
		if (cssBorderBottomRightImage == null) {
			cssBorderBottomRightImage =
				(CssBorderBottomRightImage) style.CascadingOrder(
						new CssBorderBottomRightImage(), style, selector);
		}
		return cssBorderBottomRightImage;
	}

	public CssBorderCornerImage getBorderCornerImage() {
		if (cssBorderCornerImage == null) {
			cssBorderCornerImage =
				(CssBorderCornerImage) style.CascadingOrder(
						new CssBorderCornerImage(), style, selector);
		}
		return cssBorderCornerImage;
	}

	public CssBorderTopFitLength getBorderTopFitLength() {
		if (cssBorderTopFitLength == null) {
			cssBorderTopFitLength =
				(CssBorderTopFitLength) style.CascadingOrder(
						new CssBorderTopFitLength(), style, selector);
		}
		return cssBorderTopFitLength;
	}

	public CssBorderBottomFitLength getBorderBottomFitLength() {
		if (cssBorderBottomFitLength == null) {
			cssBorderBottomFitLength =
				(CssBorderBottomFitLength) style.CascadingOrder(
						new CssBorderBottomFitLength(), style, selector);
		}
		return cssBorderBottomFitLength;
	}

	public CssBorderLeftFitLength getBorderLeftFitLength() {
		if (cssBorderLeftFitLength == null) {
			cssBorderLeftFitLength =
				(CssBorderLeftFitLength) style.CascadingOrder(
						new CssBorderLeftFitLength(), style, selector);
		}
		return cssBorderLeftFitLength;
	}

	public CssBorderRightFitLength getBorderRightFitLength() {
		if (cssBorderRightFitLength == null) {
			cssBorderRightFitLength =
				(CssBorderRightFitLength) style.CascadingOrder(
						new CssBorderRightFitLength(), style, selector);
		}
		return cssBorderRightFitLength;
	}

	public CssBorderTopFitWidth getBorderTopFitWidth() {
		if (cssBorderTopFitWidth == null) {
			cssBorderTopFitWidth =
				(CssBorderTopFitWidth) style.CascadingOrder(
						new CssBorderTopFitWidth(), style, selector);
		}
		return cssBorderTopFitWidth;
	}

	public CssBorderBottomFitWidth getBorderBottomFitWidth() {
		if (cssBorderBottomFitWidth == null) {
			cssBorderBottomFitWidth =
				(CssBorderBottomFitWidth) style.CascadingOrder(
						new CssBorderBottomFitWidth(), style, selector);
		}
		return cssBorderBottomFitWidth;
	}

	public CssBorderLeftFitWidth getBorderLeftFitWidth() {
		if (cssBorderLeftFitWidth == null) {
			cssBorderLeftFitWidth =
				(CssBorderLeftFitWidth) style.CascadingOrder(
						new CssBorderLeftFitWidth(), style, selector);
		}
		return cssBorderLeftFitWidth;
	}

	public CssBorderRightFitWidth getBorderRightFitWidth() {
		if (cssBorderRightFitWidth == null) {
			cssBorderRightFitWidth =
				(CssBorderRightFitWidth) style.CascadingOrder(
						new CssBorderRightFitWidth(), style, selector);
		}
		return cssBorderRightFitWidth;
	}

	public CssBorderTopLeftFitLength getBorderTopLeftFitLength() {
		if (cssBorderTopLeftFitLength == null) {
			cssBorderTopLeftFitLength =
				(CssBorderTopLeftFitLength) style.CascadingOrder(
						new CssBorderTopLeftFitLength(), style, selector);
		}
		return cssBorderTopLeftFitLength;
	}

	public CssBorderTopRightFitLength getBorderTopRightFitLength() {
		if (cssBorderTopRightFitLength == null) {
			cssBorderTopRightFitLength =
				(CssBorderTopRightFitLength) style.CascadingOrder(
						new CssBorderTopRightFitLength(), style, selector);
		}
		return cssBorderTopRightFitLength;
	}

	public CssBorderBottomLeftFitLength getBorderBottomLeftFitLength() {
		if (cssBorderBottomLeftFitLength == null) {
			cssBorderBottomLeftFitLength =
				(CssBorderBottomLeftFitLength) style.CascadingOrder(
						new CssBorderBottomLeftFitLength(), style, selector);
		}
		return cssBorderBottomLeftFitLength;
	}

	public CssBorderBottomRightFitLength getBorderBottomRightFitLength() {
		if (cssBorderBottomRightFitLength == null) {
			cssBorderBottomRightFitLength =
				(CssBorderBottomRightFitLength) style.CascadingOrder(
						new CssBorderBottomRightFitLength(), style, selector);
		}
		return cssBorderBottomRightFitLength;
	}

	public CssBorderTopLeftFitWidth getBorderTopLeftFitWidth() {
		if (cssBorderTopLeftFitWidth == null) {
			cssBorderTopLeftFitWidth =
				(CssBorderTopLeftFitWidth) style.CascadingOrder(
						new CssBorderTopLeftFitWidth(), style, selector);
		}
		return cssBorderTopLeftFitWidth;
	}

	public CssBorderTopRightFitWidth getBorderTopRightFitWidth() {
		if (cssBorderTopRightFitWidth == null) {
			cssBorderTopRightFitWidth =
				(CssBorderTopRightFitWidth) style.CascadingOrder(
						new CssBorderTopRightFitWidth(), style, selector);
		}
		return cssBorderTopRightFitWidth;
	}

	public CssBorderBottomLeftFitWidth getBorderBottomLeftFitWidth() {
		if (cssBorderBottomLeftFitWidth == null) {
			cssBorderBottomLeftFitWidth =
				(CssBorderBottomLeftFitWidth) style.CascadingOrder(
						new CssBorderBottomLeftFitWidth(), style, selector);
		}
		return cssBorderBottomLeftFitWidth;
	}

	public CssBorderBottomRightFitWidth getBorderBottomRightFitWidth() {
		if (cssBorderBottomRightFitWidth == null) {
			cssBorderBottomRightFitWidth =
				(CssBorderBottomRightFitWidth) style.CascadingOrder(
						new CssBorderBottomRightFitWidth(), style, selector);
		}
		return cssBorderBottomRightFitWidth;
	}

	public CssBorderFitLength getBorderFitLength() {
		if (cssBorderFitLength == null) {
			cssBorderFitLength =
				(CssBorderFitLength) style.CascadingOrder(
						new CssBorderFitLength(), style, selector);
		}
		return cssBorderFitLength;
	}

	public CssBorderFitWidth getBorderFitWidth() {
		if (cssBorderFitWidth == null) {
			cssBorderFitWidth =
				(CssBorderFitWidth) style.CascadingOrder(
						new CssBorderFitWidth(), style, selector);
		}
		return cssBorderFitWidth;
	}

	public CssBorderFit getBorderFit() {
		if (cssBorderFit == null) {
			cssBorderFit =
				(CssBorderFit) style.CascadingOrder(
						new CssBorderFit(), style, selector);
		}
		return cssBorderFit;
	}

	public CssBorderCornerFit getBorderCornerFit() {
		if (cssBorderCornerFit == null) {
			cssBorderCornerFit =
				(CssBorderCornerFit) style.CascadingOrder(
						new CssBorderCornerFit(), style, selector);
		}
		return cssBorderCornerFit;
	}

	public CssBorderImageTransform getBorderImageTransform() {
		if (cssBorderImageTransform == null) {
			cssBorderImageTransform =
				(CssBorderImageTransform) style.CascadingOrder(
						new CssBorderImageTransform(), style, selector);
		}
		return cssBorderImageTransform;
	}

	public CssBorderBreak getBorderBreak() {
		if (cssBorderBreak == null) {
			cssBorderBreak =
				(CssBorderBreak) style.CascadingOrder(
						new CssBorderBreak(), style, selector);
		}
		return cssBorderBreak;
	}

	public CssBoxShadow getBoxShadow() {
		if (cssBoxShadow == null) {
			cssBoxShadow =
				(CssBoxShadow) style.CascadingOrder(
						new CssBoxShadow(), style, selector);
		}
		return cssBoxShadow;
	}

	public CssTextIndentCSS3 getTextIndentCSS3() {
		if (cssTextIndentCSS3 == null) {
			cssTextIndentCSS3 =
				(CssTextIndentCSS3) style.CascadingOrder(
						new CssTextIndentCSS3(), style, selector);
		}
		return cssTextIndentCSS3;
	}

	public CssBlockProgression getBlockProgression() {
		if (cssBlockProgression == null) {
			cssBlockProgression =
				(CssBlockProgression) style.CascadingOrder(
						new CssBlockProgression(), style, selector);
		}
		return cssBlockProgression;
	}

// media features


	public MediaWidth getMediaWidth() {
		if (mediaWidth == null) {
			mediaWidth =
				(MediaWidth) style.CascadingOrder(
						new MediaWidth(), style, selector);
		}
		return mediaWidth;
	}

	public MediaMinWidth getMediaMinWidth() {
		if (mediaMinWidth == null) {
			mediaMinWidth =
				(MediaMinWidth) style.CascadingOrder(
						new MediaMinWidth(), style, selector);
		}
		return mediaMinWidth;
	}

	public MediaMaxWidth getMediaMaxWidth() {
		if (mediaMaxWidth == null) {
			mediaMaxWidth =
				(MediaMaxWidth) style.CascadingOrder(
						new MediaMaxWidth(), style, selector);
		}
		return mediaMaxWidth;
	}

	public MediaHeight getMediaHeight() {
		if (mediaHeight == null) {
			mediaHeight =
				(MediaHeight) style.CascadingOrder(
						new MediaHeight(), style, selector);
		}
		return mediaHeight;
	}

	public MediaMinHeight getMediaMinHeight() {
		if (mediaMinHeight == null) {
			mediaMinHeight =
				(MediaMinHeight) style.CascadingOrder(
						new MediaMinHeight(), style, selector);
		}
		return mediaMinHeight;
	}

	public MediaMaxHeight getMediaMaxHeight() {
		if (mediaMaxHeight == null) {
			mediaMaxHeight =
				(MediaMaxHeight) style.CascadingOrder(
						new MediaMaxHeight(), style, selector);
		}
		return mediaMaxHeight;
	}

	public MediaDeviceWidth getMediaDeviceWidth() {
		if (mediaDeviceWidth == null) {
			mediaDeviceWidth =
				(MediaDeviceWidth) style.CascadingOrder(
						new MediaDeviceWidth(), style, selector);
		}
		return mediaDeviceWidth;
	}

	public MediaMinDeviceWidth getMediaMinDeviceWidth() {
		if (mediaMinDeviceWidth == null) {
			mediaMinDeviceWidth =
				(MediaMinDeviceWidth) style.CascadingOrder(
						new MediaMinDeviceWidth(), style, selector);
		}
		return mediaMinDeviceWidth;
	}

	public MediaMaxDeviceWidth getMediaMaxDeviceWidth() {
		if (mediaMaxDeviceWidth == null) {
			mediaMaxDeviceWidth =
				(MediaMaxDeviceWidth) style.CascadingOrder(
						new MediaMaxDeviceWidth(), style, selector);
		}
		return mediaMaxDeviceWidth;
	}

	public MediaDeviceHeight getMediaDeviceHeight() {
		if (mediaDeviceHeight == null) {
			mediaDeviceHeight =
				(MediaDeviceHeight) style.CascadingOrder(
						new MediaDeviceHeight(), style, selector);
		}
		return mediaDeviceHeight;
	}

	public MediaMinDeviceHeight getMediaMinDeviceHeight() {
		if (mediaMinDeviceHeight == null) {
			mediaMinDeviceHeight =
				(MediaMinDeviceHeight) style.CascadingOrder(
						new MediaMinDeviceHeight(), style, selector);
		}
		return mediaMinDeviceHeight;
	}

	public MediaMaxDeviceHeight getMediaMaxDeviceHeight() {
		if (mediaMaxDeviceHeight == null) {
			mediaMaxDeviceHeight =
				(MediaMaxDeviceHeight) style.CascadingOrder(
						new MediaMaxDeviceHeight(), style, selector);
		}
		return mediaMaxDeviceHeight;
	}

	public MediaDeviceAspectRatio getMediaDeviceAspectRatio() {
		if (mediaDeviceAspectRatio == null) {
			mediaDeviceAspectRatio =
				(MediaDeviceAspectRatio) style.CascadingOrder(
						new MediaDeviceAspectRatio(), style, selector);
		}
		return mediaDeviceAspectRatio;
	}

	public MediaMinDeviceAspectRatio getMediaMinDeviceAspectRatio() {
		if (mediaMinDeviceAspectRatio == null) {
			mediaMinDeviceAspectRatio =
				(MediaMinDeviceAspectRatio) style.CascadingOrder(
						new MediaMinDeviceAspectRatio(), style, selector);
		}
		return mediaMinDeviceAspectRatio;
	}

	public MediaMaxDeviceAspectRatio getMediaMaxDeviceAspectRatio() {
		if (mediaMaxDeviceAspectRatio == null) {
			mediaMaxDeviceAspectRatio =
				(MediaMaxDeviceAspectRatio) style.CascadingOrder(
						new MediaMaxDeviceAspectRatio(), style, selector);
		}
		return mediaMaxDeviceAspectRatio;
	}

	public MediaColor getMediaColor() {
		if (mediaColor == null) {
			mediaColor =
				(MediaColor) style.CascadingOrder(
						new MediaColor(), style, selector);
		}
		return mediaColor;
	}

	public MediaMinColor getMediaMinColor() {
		if (mediaMinColor == null) {
			mediaMinColor =
				(MediaMinColor) style.CascadingOrder(
						new MediaMinColor(), style, selector);
		}
		return mediaMinColor;
	}

	public MediaMaxColor getMediaMaxColor() {
		if (mediaMaxColor == null) {
			mediaMaxColor =
				(MediaMaxColor) style.CascadingOrder(
						new MediaMaxColor(), style, selector);
		}
		return mediaMaxColor;
	}

	public MediaColorIndex getMediaColorIndex() {
		if (mediaColorIndex == null) {
			mediaColorIndex =
				(MediaColorIndex) style.CascadingOrder(
						new MediaColorIndex(), style, selector);
		}
		return mediaColorIndex;
	}

	public MediaMinColorIndex getMediaMinColorIndex() {
		if (mediaMinColorIndex == null) {
			mediaMinColorIndex =
				(MediaMinColorIndex) style.CascadingOrder(
						new MediaMinColorIndex(), style, selector);
		}
		return mediaMinColorIndex;
	}

	public MediaMaxColorIndex getMediaMaxColorIndex() {
		if (mediaMaxColorIndex == null) {
			mediaMaxColorIndex =
				(MediaMaxColorIndex) style.CascadingOrder(
						new MediaMaxColorIndex(), style, selector);
		}
		return mediaMaxColorIndex;
	}

	public MediaMonochrome getMediaMonochrome() {
		if (mediaMonochrome == null) {
			mediaMonochrome =
				(MediaMonochrome) style.CascadingOrder(
						new MediaMonochrome(), style, selector);
		}
		return mediaMonochrome;
	}

	public MediaMinMonochrome getMediaMinMonochrome() {
		if (mediaMinMonochrome == null) {
			mediaMinMonochrome =
				(MediaMinMonochrome) style.CascadingOrder(
						new MediaMinMonochrome(), style, selector);
		}
		return mediaMinMonochrome;
	}

	public MediaMaxMonochrome getMediaMaxMonochrome() {
		if (mediaMaxMonochrome == null) {
			mediaMaxMonochrome =
				(MediaMaxMonochrome) style.CascadingOrder(
						new MediaMaxMonochrome(), style, selector);
		}
		return mediaMaxMonochrome;
	}

	public MediaResolution getMediaResolution() {
		if (mediaResolution == null) {
			mediaResolution =
				(MediaResolution) style.CascadingOrder(
						new MediaResolution(), style, selector);
		}
		return mediaResolution;
	}

	public MediaMinResolution getMediaMinResolution() {
		if (mediaMinResolution == null) {
			mediaMinResolution =
				(MediaMinResolution) style.CascadingOrder(
						new MediaMinResolution(), style, selector);
		}
		return mediaMinResolution;
	}

	public MediaMaxResolution getMediaMaxResolution() {
		if (mediaMaxResolution == null) {
			mediaMaxResolution =
				(MediaMaxResolution) style.CascadingOrder(
						new MediaMaxResolution(), style, selector);
		}
		return mediaMaxResolution;
	}

	public MediaScan getMediaScan() {
		if (mediaScan == null) {
			mediaScan =
				(MediaScan) style.CascadingOrder(
						new MediaScan(), style, selector);
		}
		return mediaScan;
	}

	public MediaGrid getMediaGrid() {
		if (mediaGrid == null) {
			mediaGrid =
				(MediaGrid) style.CascadingOrder(
						new MediaGrid(), style, selector);
		}
		return mediaGrid;
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
	if (cssColumnSpan != null) {
	    cssColumnSpan.print(printer);
	}
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
	if (cssAllSpaceTreatment != null) {
		cssAllSpaceTreatment.print(printer);
	}
	if (cssHangingPunctuation != null) {
		cssHangingPunctuation.print(printer);
	}
	if (cssLineGrid != null) {
		cssLineGrid.print(printer);
	}
	if (cssLineGridMode != null) {
		cssLineGridMode.print(printer);
	}
	if (cssLineGridProgression != null) {
		cssLineGridProgression.print(printer);
	}
	if (cssLinefeedTreatment != null) {
		cssLinefeedTreatment.print(printer);
	}
	if (cssScript != null) {
		cssScript.print(printer);
	}
	if (cssTextOverflow != null) {
		cssTextOverflow.print(printer);
	}
	if (cssTextOverflowEllipsis != null) {
		cssTextOverflowEllipsis.print(printer);
	}
	if (cssTextOverflowMode != null) {
		cssTextOverflowMode.print(printer);
	}
	if (cssWhiteSpaceTreatment != null) {
		cssWhiteSpaceTreatment.print(printer);
	}
	if (cssWrapOption != null) {
		cssWrapOption.print(printer);
	}
	if (cssColumnGap != null) {
		cssColumnGap.print(printer);
	}
	if (cssColumnRuleColor != null) {
		cssColumnRuleColor.print(printer);
	}
	if (cssColumnRuleStyle != null) {
		cssColumnRuleStyle.print(printer);
	}
	if (cssColumnRuleWidth != null) {
		cssColumnRuleWidth.print(printer);
	}
	if (cssColumnRule != null) {
		cssColumnRule.print(printer);
	}
	if (cssDropInitialAfterAdjust != null) {
		cssDropInitialAfterAdjust.print(printer);
	}
	if (cssDropInitialAfterAlign != null) {
		cssDropInitialAfterAlign.print(printer);
	}
	if (cssDropInitialBeforeAdjust != null) {
		cssDropInitialBeforeAdjust.print(printer);
	}
	if (cssDropInitialBeforeAlign != null) {
		cssDropInitialBeforeAlign.print(printer);
	}
	if (cssDropInitialSize != null) {
		cssDropInitialSize.print(printer);
	}
	if (cssDropInitialValue != null) {
		cssDropInitialValue.print(printer);
	}
	if (cssInlineBoxAlign != null) {
		cssInlineBoxAlign.print(printer);
	}
	if (cssLineStacking != null) {
		cssLineStacking.print(printer);
	}
	if (cssLineStackingRuby != null) {
		cssLineStackingRuby.print(printer);
	}
	if (cssLineStackingShift != null) {
		cssLineStackingShift.print(printer);
	}
	if (cssLineStackingStrategy != null) {
		cssLineStackingStrategy.print(printer);
	}
	if (cssTextHeight != null) {
		cssTextHeight.print(printer);
	}
	if (cssAppearance != null) {
		cssAppearance.print(printer);
	}
	if (cssIcon != null) {
		cssIcon.print(printer);
	}
	if (cssNavIndex != null) {
		cssNavIndex.print(printer);
	}
	if (cssNavUp != null) {
		cssNavUp.print(printer);
	}
	if (cssNavRight != null) {
		cssNavRight.print(printer);
	}
	if (cssNavDown != null) {
		cssNavDown.print(printer);
	}
	if (cssNavLeft != null) {
		cssNavLeft.print(printer);
	}
	if (cssOutlineOffset != null) {
		cssOutlineOffset.print(printer);
	}
	if (cssOverflowX != null) {
		cssOverflowX.print(printer);
	}
	if (cssOverflowY != null) {
		cssOverflowY.print(printer);
	}
	if (cssRubySpan != null) {
		cssRubySpan.print(printer);
	}
	if (cssTextBlink != null) {
		cssTextBlink.print(printer);
	}
	if (cssClearAfter != null) {
		cssClearAfter.print(printer);
	}
	if (cssCrop != null) {
		cssCrop.print(printer);
	}
	if (cssFitPosition != null) {
		cssFitPosition.print(printer);
	}
	if (cssFloatDisplace != null) {
		cssFloatDisplace.print(printer);
	}
	if (cssIndentEdgeReset != null) {
		cssIndentEdgeReset.print(printer);
	}
	if (cssVisibilityCSS3 != null) {
		cssVisibilityCSS3.print(printer);
	}
	if (cssOverflowCSS3 != null) {
		cssOverflowCSS3.print(printer);
	}
	if (cssOverflowClip != null) {
		cssOverflowClip.print(printer);
	}
	if (cssMaxWidthCSS3 != null) {
		cssMaxWidthCSS3.print(printer);
	}
	if (cssMaxHeightCSS3 != null) {
		cssMaxHeightCSS3.print(printer);
	}
	if (cssMinWidthCSS3 != null) {
		cssMinWidthCSS3.print(printer);
	}
	if (cssMinHeightCSS3 != null) {
		cssMinHeightCSS3.print(printer);
	}
	if (cssPaddingCSS3 != null) {
		cssPaddingCSS3.print(printer);
	}
	if (cssPaddingTopCSS3 != null) {
		cssPaddingTopCSS3.print(printer);
	}
	if (cssPaddingBottomCSS3 != null) {
		cssPaddingBottomCSS3.print(printer);
	}
	if (cssPaddingLeftCSS3 != null) {
		cssPaddingLeftCSS3.print(printer);
	}
	if (cssPaddingRightCSS3 != null) {
		cssPaddingRightCSS3.print(printer);
	}
	if (cssWidthCSS3 != null){
		cssWidthCSS3.print(printer);
	}
	if (cssHeightCSS3 != null) {
		cssHeightCSS3.print(printer);
	}
	if (cssMarquee != null) {
		cssMarquee.print(printer);
	}
	if (cssMarqueeDirection != null) {
		cssMarqueeDirection.print(printer);
	}
	if (cssMarqueeRepetition != null) {
		cssMarqueeRepetition.print(printer);
	}
	if (cssMarqueeSpeed != null) {
		cssMarqueeSpeed.print(printer);
	}
	if (cssMarqueeStyle != null) {
		cssMarqueeStyle.print(printer);
	}
	if (cssBorderTopRightRadius  != null) {
		cssBorderTopRightRadius.print(printer);
	}
	if (cssBorderBottomRightRadius != null) {
		cssBorderBottomRightRadius.print(printer);
	}
	if (cssBorderBottomLeftRadius != null) {
		cssBorderBottomLeftRadius.print(printer);
	}
	if (cssBorderTopLeftRadius != null) {
		cssBorderTopLeftRadius.print(printer);
	}
	if (cssBorderRadius != null) {
		cssBorderRadius.print(printer);
	}
	if (cssBorderTopImage != null) {
		cssBorderTopImage.print(printer);
	}
	if (cssBorderBottomImage != null) {
		cssBorderBottomImage.print(printer);
	}
	if (cssBorderLeftImage != null) {
		cssBorderLeftImage.print(printer);
	}
	if (cssBorderRightImage != null) {
		cssBorderRightImage.print(printer);
	}
	if (cssBorderImage != null) {
		cssBorderImage.print(printer);
	}
	if (cssBorderTopLeftImage != null) {
		cssBorderTopLeftImage.print(printer);
	}
	if (cssBorderTopRightImage != null) {
		cssBorderTopRightImage.print(printer);
	}
	if (cssBorderBottomLeftImage != null) {
		cssBorderBottomLeftImage.print(printer);
	}
	if (cssBorderBottomRightImage != null) {
		cssBorderBottomRightImage.print(printer);
	}
	if (cssBorderCornerImage != null) {
		cssBorderCornerImage.print(printer);
	}
	if (cssBorderTopFitLength != null) {
		cssBorderTopFitLength.print(printer);
	}
	if (cssBorderBottomFitLength != null) {
		cssBorderBottomFitLength.print(printer);
	}
	if (cssBorderLeftFitLength != null) {
		cssBorderLeftFitLength.print(printer);
	}
	if (cssBorderRightFitLength != null) {
		cssBorderRightFitLength.print(printer);
	}
	if (cssBorderTopFitWidth != null) {
		cssBorderTopFitWidth.print(printer);
	}
	if (cssBorderBottomFitWidth != null) {
		cssBorderBottomFitWidth.print(printer);
	}
	if (cssBorderLeftFitWidth != null) {
		cssBorderLeftFitWidth.print(printer);
	}
	if (cssBorderRightFitWidth != null) {
		cssBorderRightFitWidth.print(printer);
	}
	if (cssBorderTopLeftFitLength != null) {
		cssBorderTopLeftFitLength.print(printer);
	}
	if (cssBorderTopRightFitLength != null) {
		cssBorderTopRightFitLength.print(printer);
	}
	if (cssBorderBottomLeftFitLength != null) {
		cssBorderBottomLeftFitLength.print(printer);
	}
	if (cssBorderBottomRightFitLength != null) {
		cssBorderBottomRightFitLength.print(printer);
	}
	if (cssBorderTopLeftFitWidth != null) {
		cssBorderTopLeftFitWidth.print(printer);
	}
	if (cssBorderTopRightFitWidth != null) {
		cssBorderTopRightFitWidth.print(printer);
	}
	if (cssBorderBottomLeftFitWidth != null) {
		cssBorderBottomLeftFitWidth.print(printer);
	}
	if (cssBorderBottomRightFitWidth != null) {
		cssBorderBottomRightFitWidth.print(printer);
	}
	if (cssBorderFitLength != null) {
		cssBorderFitLength.print(printer);
	}
	if (cssBorderFitWidth != null) {
		cssBorderFitWidth.print(printer);
	}
	if (cssBorderFit != null) {
		cssBorderFit.print(printer);
	}
	if (cssBorderCornerFit != null) {
		cssBorderCornerFit.print(printer);
	}
	if (cssBorderImageTransform != null) {
		cssBorderImageTransform.print(printer);
	}
	if (cssBorderBreak != null) {
		cssBorderBreak.print(printer);
	}
	if (cssBoxShadow != null) {
		cssBoxShadow.print(printer);
	}
	if (cssTextIndentCSS3 != null) {
		cssTextIndentCSS3.print(printer);
	}


	// media features


	if (mediaWidth != null) {
		mediaWidth.print(printer);
	}
	if (mediaMinWidth != null) {
		mediaMinWidth.print(printer);
	}
	if (mediaMaxWidth != null) {
		mediaMaxWidth.print(printer);
	}
	if (mediaHeight != null) {
		mediaHeight.print(printer);
	}
	if (mediaMinHeight != null) {
		mediaMinHeight.print(printer);
	}
	if (mediaMaxHeight != null) {
		mediaMaxHeight.print(printer);
	}
	if (mediaDeviceWidth != null) {
		mediaDeviceWidth.print(printer);
	}
	if (mediaMinDeviceWidth != null) {
		mediaMinDeviceWidth.print(printer);
	}
	if (mediaMaxDeviceWidth != null) {
		mediaMaxDeviceWidth.print(printer);
	}
	if (mediaDeviceHeight != null) {
		mediaDeviceHeight.print(printer);
	}
	if (mediaMinDeviceHeight != null) {
		mediaMinDeviceHeight.print(printer);
	}
	if (mediaMaxDeviceHeight != null) {
		mediaMaxDeviceHeight.print(printer);
	}
	if (mediaDeviceAspectRatio != null) {
		mediaDeviceAspectRatio.print(printer);
	}
	if (mediaMinDeviceAspectRatio != null) {
		mediaMinDeviceAspectRatio.print(printer);
	}
	if (mediaMaxDeviceAspectRatio != null) {
		mediaMaxDeviceAspectRatio.print(printer);
	}
	if (mediaColor != null) {
		mediaColor.print(printer);
	}
	if (mediaMinColor != null) {
		mediaMinColor.print(printer);
	}
	if (mediaMaxColor != null) {
		mediaMaxColor.print(printer);
	}
	if (mediaColorIndex != null) {
		mediaColorIndex.print(printer);
	}
	if (mediaMinColorIndex != null) {
		mediaMinColorIndex.print(printer);
	}
	if (mediaMaxColorIndex != null) {
		mediaMaxColorIndex.print(printer);
	}
	if (mediaMonochrome != null) {
		mediaMonochrome.print(printer);
	}
	if (mediaMinMonochrome != null) {
		mediaMinMonochrome.print(printer);
	}
	if (mediaMaxMonochrome != null) {
		mediaMaxMonochrome.print(printer);
	}
	if (mediaResolution != null) {
		mediaResolution.print(printer);
	}
	if (mediaMinResolution != null) {
		mediaMinResolution.print(printer);
	}
	if (mediaMaxResolution != null) {
		mediaMaxResolution.print(printer);
	}
	if (mediaScan != null) {
		mediaScan.print(printer);
	}
	if (mediaGrid != null) {
		mediaGrid.print(printer);
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

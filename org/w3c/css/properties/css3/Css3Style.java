//

// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssSelectors;
import org.w3c.css.properties.atsc.ATSCStyle;
import org.w3c.css.properties.css.CssAlignContent;
import org.w3c.css.properties.css.CssAlignItems;
import org.w3c.css.properties.css.CssAlignSelf;
import org.w3c.css.properties.css.CssAlignmentBaseline;
import org.w3c.css.properties.css.CssAnimation;
import org.w3c.css.properties.css.CssAnimationDelay;
import org.w3c.css.properties.css.CssAnimationDirection;
import org.w3c.css.properties.css.CssAnimationDuration;
import org.w3c.css.properties.css.CssAnimationFillMode;
import org.w3c.css.properties.css.CssAnimationIterationCount;
import org.w3c.css.properties.css.CssAnimationName;
import org.w3c.css.properties.css.CssAnimationPlayState;
import org.w3c.css.properties.css.CssAnimationTimingFunction;
import org.w3c.css.properties.css.CssAppearance;
import org.w3c.css.properties.css.CssBackfaceVisibility;
import org.w3c.css.properties.css.CssBackgroundBlendMode;
import org.w3c.css.properties.css.CssBackgroundClip;
import org.w3c.css.properties.css.CssBackgroundOrigin;
import org.w3c.css.properties.css.CssBackgroundSize;
import org.w3c.css.properties.css.CssBaselineShift;
import org.w3c.css.properties.css.CssBorderImageSource;
import org.w3c.css.properties.css.CssBoxDecorationBreak;
import org.w3c.css.properties.css.CssBoxShadow;
import org.w3c.css.properties.css.CssBoxSizing;
import org.w3c.css.properties.css.CssBoxSuppress;
import org.w3c.css.properties.css.CssBreakAfter;
import org.w3c.css.properties.css.CssBreakBefore;
import org.w3c.css.properties.css.CssBreakInside;
import org.w3c.css.properties.css.CssCaretColor;
import org.w3c.css.properties.css.CssColorInterpolationFilters;
import org.w3c.css.properties.css.CssColumnCount;
import org.w3c.css.properties.css.CssColumnFill;
import org.w3c.css.properties.css.CssColumnGap;
import org.w3c.css.properties.css.CssColumnRule;
import org.w3c.css.properties.css.CssColumnRuleColor;
import org.w3c.css.properties.css.CssColumnRuleStyle;
import org.w3c.css.properties.css.CssColumnRuleWidth;
import org.w3c.css.properties.css.CssColumnSpan;
import org.w3c.css.properties.css.CssColumnWidth;
import org.w3c.css.properties.css.CssColumns;
import org.w3c.css.properties.css.CssContain;
import org.w3c.css.properties.css.CssCounterSet;
import org.w3c.css.properties.css.CssDominantBaseline;
import org.w3c.css.properties.css.CssFilter;
import org.w3c.css.properties.css.CssFlex;
import org.w3c.css.properties.css.CssFlexBasis;
import org.w3c.css.properties.css.CssFlexDirection;
import org.w3c.css.properties.css.CssFlexFlow;
import org.w3c.css.properties.css.CssFlexGrow;
import org.w3c.css.properties.css.CssFlexShrink;
import org.w3c.css.properties.css.CssFlexWrap;
import org.w3c.css.properties.css.CssFloatDefer;
import org.w3c.css.properties.css.CssFloatOffset;
import org.w3c.css.properties.css.CssFloatReference;
import org.w3c.css.properties.css.CssFloodColor;
import org.w3c.css.properties.css.CssFloodOpacity;
import org.w3c.css.properties.css.CssFontFeatureSettings;
import org.w3c.css.properties.css.CssFontKerning;
import org.w3c.css.properties.css.CssFontLanguageOverride;
import org.w3c.css.properties.css.CssFontSynthesis;
import org.w3c.css.properties.css.CssFontVariantAlternates;
import org.w3c.css.properties.css.CssFontVariantCaps;
import org.w3c.css.properties.css.CssFontVariantEastAsian;
import org.w3c.css.properties.css.CssFontVariantLigatures;
import org.w3c.css.properties.css.CssFontVariantNumeric;
import org.w3c.css.properties.css.CssFontVariantPosition;
import org.w3c.css.properties.css.CssGrid;
import org.w3c.css.properties.css.CssGridArea;
import org.w3c.css.properties.css.CssGridAutoColumns;
import org.w3c.css.properties.css.CssGridAutoFlow;
import org.w3c.css.properties.css.CssGridAutoRows;
import org.w3c.css.properties.css.CssGridColumn;
import org.w3c.css.properties.css.CssGridColumnEnd;
import org.w3c.css.properties.css.CssGridColumnGap;
import org.w3c.css.properties.css.CssGridColumnStart;
import org.w3c.css.properties.css.CssGridGap;
import org.w3c.css.properties.css.CssGridRow;
import org.w3c.css.properties.css.CssGridRowEnd;
import org.w3c.css.properties.css.CssGridRowGap;
import org.w3c.css.properties.css.CssGridRowStart;
import org.w3c.css.properties.css.CssGridTemplate;
import org.w3c.css.properties.css.CssGridTemplateAreas;
import org.w3c.css.properties.css.CssGridTemplateColumns;
import org.w3c.css.properties.css.CssGridTemplateRows;
import org.w3c.css.properties.css.CssHangingPunctuation;
import org.w3c.css.properties.css.CssHyphens;
import org.w3c.css.properties.css.CssIcon;
import org.w3c.css.properties.css.CssImageOrientation;
import org.w3c.css.properties.css.CssImageResolution;
import org.w3c.css.properties.css.CssImeMode;
import org.w3c.css.properties.css.CssInitialLetter;
import org.w3c.css.properties.css.CssInitialLetterAlign;
import org.w3c.css.properties.css.CssInitialLetterWrap;
import org.w3c.css.properties.css.CssIsolation;
import org.w3c.css.properties.css.CssJustifyContent;
import org.w3c.css.properties.css.CssLightingColor;
import org.w3c.css.properties.css.CssLineBreak;
import org.w3c.css.properties.css.CssMarkerSide;
import org.w3c.css.properties.css.CssMarqueeDirection;
import org.w3c.css.properties.css.CssMarqueePlayCount;
import org.w3c.css.properties.css.CssMarqueeSpeed;
import org.w3c.css.properties.css.CssMarqueeStyle;
import org.w3c.css.properties.css.CssMixBlendMode;
import org.w3c.css.properties.css.CssNavDown;
import org.w3c.css.properties.css.CssNavLeft;
import org.w3c.css.properties.css.CssNavRight;
import org.w3c.css.properties.css.CssNavUp;
import org.w3c.css.properties.css.CssObjectFit;
import org.w3c.css.properties.css.CssObjectPosition;
import org.w3c.css.properties.css.CssOpacity;
import org.w3c.css.properties.css.CssOrder;
import org.w3c.css.properties.css.CssOutlineOffset;
import org.w3c.css.properties.css.CssOverflowStyle;
import org.w3c.css.properties.css.CssOverflowWrap;
import org.w3c.css.properties.css.CssOverflowX;
import org.w3c.css.properties.css.CssOverflowY;
import org.w3c.css.properties.css.CssPerspective;
import org.w3c.css.properties.css.CssPerspectiveOrigin;
import org.w3c.css.properties.css.CssResize;
import org.w3c.css.properties.css.CssRest;
import org.w3c.css.properties.css.CssRestAfter;
import org.w3c.css.properties.css.CssRestBefore;
import org.w3c.css.properties.css.CssScrollPadding;
import org.w3c.css.properties.css.CssScrollPaddingBlock;
import org.w3c.css.properties.css.CssScrollPaddingBlockEnd;
import org.w3c.css.properties.css.CssScrollPaddingBlockStart;
import org.w3c.css.properties.css.CssScrollPaddingBottom;
import org.w3c.css.properties.css.CssScrollPaddingInline;
import org.w3c.css.properties.css.CssScrollPaddingInlineEnd;
import org.w3c.css.properties.css.CssScrollPaddingInlineStart;
import org.w3c.css.properties.css.CssScrollPaddingLeft;
import org.w3c.css.properties.css.CssScrollPaddingRight;
import org.w3c.css.properties.css.CssScrollPaddingTop;
import org.w3c.css.properties.css.CssScrollSnapAlign;
import org.w3c.css.properties.css.CssScrollSnapMargin;
import org.w3c.css.properties.css.CssScrollSnapMarginBlock;
import org.w3c.css.properties.css.CssScrollSnapMarginBlockEnd;
import org.w3c.css.properties.css.CssScrollSnapMarginBlockStart;
import org.w3c.css.properties.css.CssScrollSnapMarginBottom;
import org.w3c.css.properties.css.CssScrollSnapMarginInline;
import org.w3c.css.properties.css.CssScrollSnapMarginInlineEnd;
import org.w3c.css.properties.css.CssScrollSnapMarginInlineStart;
import org.w3c.css.properties.css.CssScrollSnapMarginLeft;
import org.w3c.css.properties.css.CssScrollSnapMarginRight;
import org.w3c.css.properties.css.CssScrollSnapMarginTop;
import org.w3c.css.properties.css.CssScrollSnapStop;
import org.w3c.css.properties.css.CssScrollSnapType;
import org.w3c.css.properties.css.CssSpeakAs;
import org.w3c.css.properties.css.CssTabSize;
import org.w3c.css.properties.css.CssTextAlignLast;
import org.w3c.css.properties.css.CssTextDecorationColor;
import org.w3c.css.properties.css.CssTextDecorationLine;
import org.w3c.css.properties.css.CssTextDecorationSkip;
import org.w3c.css.properties.css.CssTextDecorationStyle;
import org.w3c.css.properties.css.CssTextEmphasis;
import org.w3c.css.properties.css.CssTextEmphasisColor;
import org.w3c.css.properties.css.CssTextEmphasisPosition;
import org.w3c.css.properties.css.CssTextEmphasisStyle;
import org.w3c.css.properties.css.CssTextJustify;
import org.w3c.css.properties.css.CssTextOverflow;
import org.w3c.css.properties.css.CssTextUnderlinePosition;
import org.w3c.css.properties.css.CssTouchAction;
import org.w3c.css.properties.css.CssTransform;
import org.w3c.css.properties.css.CssTransformOrigin;
import org.w3c.css.properties.css.CssTransformStyle;
import org.w3c.css.properties.css.CssTransition;
import org.w3c.css.properties.css.CssTransitionDelay;
import org.w3c.css.properties.css.CssTransitionDuration;
import org.w3c.css.properties.css.CssTransitionProperty;
import org.w3c.css.properties.css.CssTransitionTimingFunction;
import org.w3c.css.properties.css.CssVoiceBalance;
import org.w3c.css.properties.css.CssVoiceDuration;
import org.w3c.css.properties.css.CssVoicePitch;
import org.w3c.css.properties.css.CssVoiceRange;
import org.w3c.css.properties.css.CssVoiceRate;
import org.w3c.css.properties.css.CssVoiceStress;
import org.w3c.css.properties.css.CssVoiceVolume;
import org.w3c.css.properties.css.CssWillChange;
import org.w3c.css.properties.css.CssWritingMode;
import org.w3c.css.properties.css.CssWordBreak;
import org.w3c.css.properties.css.counterstyle.CssAdditiveSymbols;
import org.w3c.css.properties.css.counterstyle.CssFallback;
import org.w3c.css.properties.css.counterstyle.CssNegative;
import org.w3c.css.properties.css.counterstyle.CssPad;
import org.w3c.css.properties.css.counterstyle.CssPrefix;
import org.w3c.css.properties.css.counterstyle.CssRange;
import org.w3c.css.properties.css.counterstyle.CssSuffix;
import org.w3c.css.properties.css.counterstyle.CssSymbols;
import org.w3c.css.properties.css.counterstyle.CssSystem;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Util;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;

public class Css3Style extends ATSCStyle {

	public org.w3c.css.properties.css.counterstyle.CssSpeakAs counterStyleCssSpeakAs;
	public CssSystem counterStyleCssSystem;
	public CssFallback counterStyleCssFallback;
	public CssSymbols counterStyleCssSymbols;
	public CssPrefix counterStyleCssPrefix;
	public CssSuffix counterStyleCssSuffix;
	public CssRange counterStyleCssRange;
	public CssPad counterStyleCssPad;
	public CssAdditiveSymbols counterStyleCssAdditiveSymbols;
	public CssNegative counterStyleCssNegative;

	public CssWritingMode cssWritingMode;
	public CssTouchAction cssTouchAction;
	public CssAppearance cssAppearance;

	public CssScrollSnapMarginBlockStart cssScrollSnapMarginBlockStart;
	public CssScrollSnapMarginBlockEnd cssScrollSnapMarginBlockEnd;
	public CssScrollSnapMarginInlineStart cssScrollSnapMarginInlineStart;
	public CssScrollSnapMarginInlineEnd cssScrollSnapMarginInlineEnd;
	public CssScrollSnapMarginBlock cssScrollSnapMarginBlock;
	public CssScrollSnapMarginInline cssScrollSnapMarginInline;
	public CssScrollSnapMarginBottom cssScrollSnapMarginBottom;
	public CssScrollSnapMarginLeft cssScrollSnapMarginLeft;
	public CssScrollSnapMarginRight cssScrollSnapMarginRight;
	public CssScrollSnapMarginTop cssScrollSnapMarginTop;
	public CssScrollSnapMargin cssScrollSnapMargin;
	public CssScrollPaddingInlineStart cssScrollPaddingInlineStart;
	public CssScrollPaddingInlineEnd cssScrollPaddingInlineEnd;
	public CssScrollPaddingInline cssScrollPaddingInline;
	public CssScrollPaddingBlockStart cssScrollPaddingBlockStart;
	public CssScrollPaddingBlockEnd cssScrollPaddingBlockEnd;
	public CssScrollPaddingBlock cssScrollPaddingBlock;
	public CssScrollPadding cssScrollPadding;
	public CssScrollPaddingBottom cssScrollPaddingBottom;
	public CssScrollPaddingLeft cssScrollPaddingLeft;
	public CssScrollPaddingRight cssScrollPaddingRight;
	public CssScrollPaddingTop cssScrollPaddingTop;
	public CssScrollSnapStop cssScrollSnapStop;
	public CssScrollSnapAlign cssScrollSnapAlign;
	public CssScrollSnapType cssScrollSnapType;

	public CssMarkerSide cssMarkerSide;
	public CssCounterSet cssCounterSet;

	public CssGridRowGap cssGridRowGap;
	public CssGridColumnGap cssGridColumnGap;
	public CssGridGap cssGridGap;
	public CssGridRowStart cssGridRowStart;
	public CssGridRowEnd cssGridRowEnd;
	public CssGridColumnStart cssGridColumnStart;
	public CssGridColumnEnd cssGridColumnEnd;
	public CssGridColumn cssGridColumn;
	public CssGridRow cssGridRow;
	public CssGridArea cssGridArea;
	public CssGridAutoFlow cssGridAutoFlow;
	public CssGridTemplateAreas cssGridTemplateAreas;
	public CssGridAutoRows cssGridAutoRows;
	public CssGridAutoColumns cssGridAutoColumns;
	public CssGridTemplateRows cssGridTemplateRows;
	public CssGridTemplateColumns cssGridTemplateColumns;
	public CssGridTemplate cssGridTemplate;
	public CssGrid cssGrid;

	public CssWillChange cssWillChange;
	
	public CssContain cssContain;

	public CssMixBlendMode cssMixBlendMode;
	public CssIsolation cssIsolation;
	public CssBackgroundBlendMode cssBackgroundBlendMode;

	public CssVoiceBalance cssVoiceBalance;
	public CssVoiceDuration cssVoiceDuration;
	public CssVoiceRate cssVoiceRate;
	public CssVoiceStress cssVoiceStress;
	public CssVoiceVolume cssVoiceVolume;
	public CssRestAfter cssRestAfter;
	public CssRestBefore cssRestBefore;
	public CssRest cssRest;
	public CssSpeakAs cssSpeakAs;
	public CssVoicePitch cssVoicePitch;
	public CssVoiceRange cssVoiceRange;

	public CssBoxSuppress cssBoxSuppress;

	CssRubyPosition cssRubyPosition;
	CssRubyAlign cssRubyAlign;
	CssRubyOverhang cssRubyOverhang;
	public CssAlignmentBaseline cssAlignmentBaseline;
	CssAlignmentAdjust cssAlignmentAdjust;
	public CssBaselineShift cssBaselineShift;
	public CssDominantBaseline cssDominantBaseline;
	CssFit cssFit;
	public CssInitialLetter cssInitialLetter;
	public CssInitialLetterAlign cssInitialLetterAlign;
	public CssInitialLetterWrap cssInitialLetterWrap;

	public CssOpacity cssOpacity;
	public CssBackgroundClip cssBackgroundClip;
	public CssBackgroundSize cssBackgroundSize;
	public CssBackgroundOrigin cssBackgroundOrigin;
	public CssColumns cssColumns;
	public CssColumnCount cssColumnCount;
	public CssColumnFill cssColumnFill;
	public CssColumnGap cssColumnGap;
	public CssColumnRule cssColumnRule;
	public CssColumnRuleColor cssColumnRuleColor;
	public CssColumnRuleStyle cssColumnRuleStyle;
	public CssColumnRuleWidth cssColumnRuleWidth;
	public CssColumnSpan cssColumnSpan;
	public CssColumnWidth cssColumnWidth;
	public CssBreakAfter cssBreakAfter;
	public CssBreakBefore cssBreakBefore;
	public CssBreakInside cssBreakInside;
	public CssBoxShadow cssBoxShadow;
	public CssBoxDecorationBreak cssBoxDecorationBreak;
	public CssFontKerning cssFontKerning;
	public CssFontLanguageOverride cssFontLanguageOverride;
	public CssFontVariantCaps cssFontVariantCaps;
	public CssFontVariantPosition cssFontVariantPosition;
	public CssFontSynthesis cssFontSynthesis;
	public CssFontVariantEastAsian cssFontVariantEastAsian;
	public CssFontVariantLigatures cssFontVariantLigatures;
	public CssFontVariantNumeric cssFontVariantNumeric;
	public CssFontFeatureSettings cssFontFeatureSettings;
	public CssFontVariantAlternates cssFontVariantAlternates;

	public CssOverflowWrap cssOverflowWrap;
	public CssWordBreak cssWordBreak;
	public CssHyphens cssHyphens;
	public CssLineBreak cssLineBreak;
	public CssTextAlignLast cssTextAlignLast;
	public CssTextJustify cssTextJustify;
	public CssTextDecorationColor cssTextDecorationColor;
	public CssTextDecorationLine cssTextDecorationLine;
	public CssTextDecorationSkip cssTextDecorationSkip;
	public CssTextDecorationStyle cssTextDecorationStyle;
	public CssTextEmphasis cssTextEmphasis;
	public CssTextEmphasisColor cssTextEmphasisColor;
	public CssTextEmphasisPosition cssTextEmphasisPosition;
	public CssTextEmphasisStyle cssTextEmphasisStyle;
	public CssTextUnderlinePosition cssTextUnderlinePosition;
	public CssHangingPunctuation cssHangingPunctuation;
	public CssTabSize cssTabSize;

	public CssMarqueeDirection cssMarqueeDirection;
	public CssMarqueeSpeed cssMarqueeSpeed;
	public CssMarqueeStyle cssMarqueeStyle;
	public CssMarqueePlayCount cssMarqueePlayCount;
	public CssOverflowStyle cssOverflowStyle;

	public CssTransition cssTransition;
	public CssTransitionDelay cssTransitionDelay;
	public CssTransitionDuration cssTransitionDuration;
	public CssTransitionProperty cssTransitionProperty;
	public CssTransitionTimingFunction cssTransitionTimingFunction;

	public CssAnimation cssAnimation;
	public CssAnimationDelay cssAnimationDelay;
	public CssAnimationDirection cssAnimationDirection;
	public CssAnimationDuration cssAnimationDuration;
	public CssAnimationFillMode cssAnimationFillMode;
	public CssAnimationIterationCount cssAnimationIterationCount;
	public CssAnimationName cssAnimationName;
	public CssAnimationPlayState cssAnimationPlayState;
	public CssAnimationTimingFunction cssAnimationTimingFunction;

	public CssAlignContent cssAlignContent;
	public CssAlignItems cssAlignItems;
	public CssAlignSelf cssAlignSelf;
	public CssFlex cssFlex;
	public CssFlexBasis cssFlexBasis;
	public CssFlexDirection cssFlexDirection;
	public CssFlexWrap cssFlexWrap;
	public CssFlexFlow cssFlexFlow;
	public CssFlexGrow cssFlexGrow;
	public CssFlexShrink cssFlexShrink;
	public CssJustifyContent cssJustifyContent;
	public CssOrder cssOrder;

	public CssTransformStyle cssTransformStyle;
	public CssBackfaceVisibility cssBackfaceVisibility;
	public CssPerspective cssPerspective;
	public CssPerspectiveOrigin cssPerspectiveOrigin;
	public CssTransformOrigin cssTransformOrigin;
	public CssTransform cssTransform;

	public CssBoxSizing cssBoxSizing;
	public CssResize cssResize;
	public CssOutlineOffset cssOutlineOffset;
	public CssImeMode cssImeMode;
	public CssNavUp cssNavUp;
	public CssNavRight cssNavRight;
	public CssNavDown cssNavDown;
	public CssNavLeft cssNavLeft;
	public CssTextOverflow cssTextOverflow;
	public CssIcon cssIcon;
	public CssCaretColor cssCaretColor;

	public CssOverflowX cssOverflowX;
	public CssOverflowY cssOverflowY;

	public CssObjectFit cssObjectFit;
	public CssObjectPosition cssObjectPosition;
	public CssImageOrientation cssImageOrientation;
	public CssImageResolution cssImageResolution;

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
	CssRubySpan cssRubySpan;
	CssCrop cssCrop;
	CssFitPosition cssFitPosition;

	public CssFilter cssFilter;
	public CssColorInterpolationFilters cssColorInterpolationFilters;
	public CssFloodColor cssFloodColor;
	public CssFloodOpacity cssFloodOpacity;
	public CssLightingColor cssLightingColor;

	public CssFloatReference cssFloatReference;
	public CssFloatOffset cssFloatOffset;
	public CssFloatDefer cssFloatDefer;

	public CssWritingMode getWritingMode() {
		if (cssWritingMode == null) {
			cssWritingMode =
					(CssWritingMode) style.CascadingOrder(new CssWritingMode(),
							style, selector);
		}
		return cssWritingMode;
	}

	public CssTouchAction getTouchAction() {
		if (cssTouchAction == null) {
			cssTouchAction =
					(CssTouchAction) style.CascadingOrder(new CssTouchAction(),
							style, selector);
		}
		return cssTouchAction;
	}

	public CssAppearance getAppearance() {
		if (cssAppearance == null) {
			cssAppearance =
					(CssAppearance) style.CascadingOrder(new CssAppearance(),
							style, selector);
		}
		return cssAppearance;
	}

	public org.w3c.css.properties.css.counterstyle.CssSpeakAs getCounterStyleCssSpeakAs() {
		if (counterStyleCssSpeakAs == null) {
			counterStyleCssSpeakAs = (org.w3c.css.properties.css.counterstyle.CssSpeakAs) style.CascadingOrder(new org.w3c.css.properties.css.counterstyle.CssSpeakAs(), style, selector);
		}
		return counterStyleCssSpeakAs;
	}

	public org.w3c.css.properties.css.counterstyle.CssSystem getCounterStyleCssSystem() {
		if (counterStyleCssSystem == null) {
			counterStyleCssSystem = (org.w3c.css.properties.css.counterstyle.CssSystem) style.CascadingOrder(new org.w3c.css.properties.css.counterstyle.CssSystem(), style, selector);
		}
		return counterStyleCssSystem;
	}

	public org.w3c.css.properties.css.counterstyle.CssFallback getCounterStyleCssFallback() {
		if (counterStyleCssFallback == null) {
			counterStyleCssFallback = (org.w3c.css.properties.css.counterstyle.CssFallback) style.CascadingOrder(new org.w3c.css.properties.css.counterstyle.CssFallback(), style, selector);
		}
		return counterStyleCssFallback;
	}

	public org.w3c.css.properties.css.counterstyle.CssSymbols getCounterStyleCssSymbols() {
		if (counterStyleCssSymbols == null) {
			counterStyleCssSymbols = (org.w3c.css.properties.css.counterstyle.CssSymbols) style.CascadingOrder(new org.w3c.css.properties.css.counterstyle.CssSymbols(), style, selector);
		}
		return counterStyleCssSymbols;
	}

	public org.w3c.css.properties.css.counterstyle.CssPrefix getCounterStyleCssPrefix() {
		if (counterStyleCssPrefix == null) {
			counterStyleCssPrefix = (org.w3c.css.properties.css.counterstyle.CssPrefix) style.CascadingOrder(new org.w3c.css.properties.css.counterstyle.CssPrefix(), style, selector);
		}
		return counterStyleCssPrefix;
	}

	public org.w3c.css.properties.css.counterstyle.CssSuffix getCounterStyleCssSuffix() {
		if (counterStyleCssSuffix == null) {
			counterStyleCssSuffix = (org.w3c.css.properties.css.counterstyle.CssSuffix) style.CascadingOrder(new org.w3c.css.properties.css.counterstyle.CssSuffix(), style, selector);
		}
		return counterStyleCssSuffix;
	}

	public org.w3c.css.properties.css.counterstyle.CssRange getCounterStyleCssRange() {
		if (counterStyleCssRange == null) {
			counterStyleCssRange = (org.w3c.css.properties.css.counterstyle.CssRange) style.CascadingOrder(new org.w3c.css.properties.css.counterstyle.CssRange(), style, selector);
		}
		return counterStyleCssRange;
	}

	public org.w3c.css.properties.css.counterstyle.CssPad getCounterStyleCssPad() {
		if (counterStyleCssPad == null) {
			counterStyleCssPad = (org.w3c.css.properties.css.counterstyle.CssPad) style.CascadingOrder(new org.w3c.css.properties.css.counterstyle.CssPad(), style, selector);
		}
		return counterStyleCssPad;
	}

	public org.w3c.css.properties.css.counterstyle.CssAdditiveSymbols getCounterStyleCssAdditiveSymbols() {
		if (counterStyleCssAdditiveSymbols == null) {
			counterStyleCssAdditiveSymbols = (org.w3c.css.properties.css.counterstyle.CssAdditiveSymbols) style.CascadingOrder(new org.w3c.css.properties.css.counterstyle.CssAdditiveSymbols(), style, selector);
		}
		return counterStyleCssAdditiveSymbols;
	}

	public org.w3c.css.properties.css.counterstyle.CssNegative getCounterStyleCssNegative() {
		if (counterStyleCssNegative == null) {
			counterStyleCssNegative = (org.w3c.css.properties.css.counterstyle.CssNegative) style.CascadingOrder(new org.w3c.css.properties.css.counterstyle.CssNegative(), style, selector);
		}
		return counterStyleCssNegative;
	}

	public CssBorderImageSource getBorderImageSource() {
		if (cssBorder.borderImage.source == null) {
			cssBorder.borderImage.source = (CssBorderImageSource) style.CascadingOrder(new CssBorderImageSource(), style, selector);
		}
		return cssBorder.borderImage.source;

	}

	public CssMarkerSide getMarkerSide() {
		if (cssMarkerSide == null) {
			cssMarkerSide =
					(CssMarkerSide) style.CascadingOrder(new CssMarkerSide(),
							style, selector);
		}
		return cssMarkerSide;
	}

	public CssCounterSet getCounterSet() {
		if (cssCounterSet == null) {
			cssCounterSet =
					(CssCounterSet) style.CascadingOrder(new CssCounterSet(),
							style, selector);
		}
		return cssCounterSet;
	}

	public CssGridColumnGap getGridColumnGap() {
		if (cssGridColumnGap == null) {
			cssGridColumnGap =
					(CssGridColumnGap) style.CascadingOrder(new CssGridColumnGap(),
							style, selector);
		}
		return cssGridColumnGap;
	}

	public CssGridGap getGridGap() {
		if (cssGridGap == null) {
			cssGridGap =
					(CssGridGap) style.CascadingOrder(new CssGridGap(),
							style, selector);
		}
		return cssGridGap;
	}

	public CssGridRowGap getGridRowGap() {
		if (cssGridRowGap == null) {
			cssGridRowGap =
					(CssGridRowGap) style.CascadingOrder(new CssGridRowGap(),
							style, selector);
		}
		return cssGridRowGap;
	}

	public CssGridRowStart getGridRowStart() {
		if (cssGridRowStart == null) {
			cssGridRowStart =
					(CssGridRowStart) style.CascadingOrder(new CssGridRowStart(),
							style, selector);
		}
		return cssGridRowStart;
	}

	public CssGridRowEnd getGridRowEnd() {
		if (cssGridRowEnd == null) {
			cssGridRowEnd =
					(CssGridRowEnd) style.CascadingOrder(new CssGridRowEnd(),
							style, selector);
		}
		return cssGridRowEnd;
	}

	public CssGridColumnStart getGridColumnStart() {
		if (cssGridColumnStart == null) {
			cssGridColumnStart =
					(CssGridColumnStart) style.CascadingOrder(new CssGridColumnStart(),
							style, selector);
		}
		return cssGridColumnStart;
	}

	public CssGridColumnEnd getGridColumnEnd() {
		if (cssGridColumnEnd == null) {
			cssGridColumnEnd =
					(CssGridColumnEnd) style.CascadingOrder(new CssGridColumnEnd(),
							style, selector);
		}
		return cssGridColumnEnd;
	}

	public CssGridColumn getGridColumn() {
		if (cssGridColumn == null) {
			cssGridColumn =
					(CssGridColumn) style.CascadingOrder(new CssGridColumn(),
							style, selector);
		}
		return cssGridColumn;
	}

	public CssGridRow getGridRow() {
		if (cssGridRow == null) {
			cssGridRow =
					(CssGridRow) style.CascadingOrder(new CssGridRow(),
							style, selector);
		}
		return cssGridRow;
	}

	public CssGridArea getGridArea() {
		if (cssGridArea == null) {
			cssGridArea =
					(CssGridArea) style.CascadingOrder(new CssGridArea(),
							style, selector);
		}
		return cssGridArea;
	}

	public CssGridTemplateAreas getGridTemplateAreas() {
		if (cssGridTemplateAreas == null) {
			cssGridTemplateAreas =
					(CssGridTemplateAreas) style.CascadingOrder(new CssGridTemplateAreas(),
							style, selector);
		}
		return cssGridTemplateAreas;
	}

	public CssGridAutoFlow getGridAutoFlow() {
		if (cssGridAutoFlow == null) {
			cssGridAutoFlow =
					(CssGridAutoFlow) style.CascadingOrder(new CssGridAutoFlow(),
							style, selector);
		}
		return cssGridAutoFlow;
	}

	public CssGridAutoRows getGridAutoRows() {
		if (cssGridAutoRows == null) {
			cssGridAutoRows =
					(CssGridAutoRows) style.CascadingOrder(new CssGridAutoRows(),
							style, selector);
		}
		return cssGridAutoRows;
	}

	public CssGridAutoColumns getGridAutoColumns() {
		if (cssGridAutoColumns == null) {
			cssGridAutoColumns =
					(CssGridAutoColumns) style.CascadingOrder(new CssGridAutoColumns(),
							style, selector);
		}
		return cssGridAutoColumns;
	}

	public CssGridTemplateRows getGridTemplateRows() {
		if (cssGridTemplateRows == null) {
			cssGridTemplateRows =
					(CssGridTemplateRows) style.CascadingOrder(new CssGridTemplateRows(),
							style, selector);
		}
		return cssGridTemplateRows;
	}

	public CssGridTemplateColumns getGridTemplateColumns() {
		if (cssGridTemplateColumns == null) {
			cssGridTemplateColumns =
					(CssGridTemplateColumns) style.CascadingOrder(new CssGridTemplateColumns(),
							style, selector);
		}
		return cssGridTemplateColumns;
	}

	public CssGridTemplate getGridTemplate() {
		if (cssGridTemplate == null) {
			cssGridTemplate =
					(CssGridTemplate) style.CascadingOrder(new CssGridTemplate(),
							style, selector);
		}
		return cssGridTemplate;
	}

	public CssGrid getGrid() {
		if (cssGrid == null) {
			cssGrid =
					(CssGrid) style.CascadingOrder(new CssGrid(),
							style, selector);
		}
		return cssGrid;
	}

	public CssScrollSnapMarginBlockStart getScrollSnapMarginBlockStart() {
		if (cssScrollSnapMarginBlockStart == null) {
			cssScrollSnapMarginBlockStart =
					(CssScrollSnapMarginBlockStart) style.CascadingOrder(new CssScrollSnapMarginBlockStart(),
							style, selector);
		}
		return cssScrollSnapMarginBlockStart;
	}

	public CssScrollSnapMarginBlockEnd getScrollSnapMarginBlockEnd() {
		if (cssScrollSnapMarginBlockEnd == null) {
			cssScrollSnapMarginBlockEnd =
					(CssScrollSnapMarginBlockEnd) style.CascadingOrder(new CssScrollSnapMarginBlockEnd(),
							style, selector);
		}
		return cssScrollSnapMarginBlockEnd;
	}

	public CssScrollSnapMarginInlineStart getScrollSnapMarginInlineStart() {
		if (cssScrollSnapMarginInlineStart == null) {
			cssScrollSnapMarginInlineStart =
					(CssScrollSnapMarginInlineStart) style.CascadingOrder(new CssScrollSnapMarginInlineStart(),
							style, selector);
		}
		return cssScrollSnapMarginInlineStart;
	}

	public CssScrollSnapMarginInlineEnd getScrollSnapMarginInlineEnd() {
		if (cssScrollSnapMarginInlineEnd == null) {
			cssScrollSnapMarginInlineEnd =
					(CssScrollSnapMarginInlineEnd) style.CascadingOrder(new CssScrollSnapMarginInlineEnd(),
							style, selector);
		}
		return cssScrollSnapMarginInlineEnd;
	}

	public CssScrollSnapMarginBlock getScrollSnapMarginBlock() {
		if (cssScrollSnapMarginBlock == null) {
			cssScrollSnapMarginBlock =
					(CssScrollSnapMarginBlock) style.CascadingOrder(new CssScrollSnapMarginBlock(),
							style, selector);
		}
		return cssScrollSnapMarginBlock;
	}

	public CssScrollSnapMarginInline getScrollSnapMarginInline() {
		if (cssScrollSnapMarginInline == null) {
			cssScrollSnapMarginInline =
					(CssScrollSnapMarginInline) style.CascadingOrder(new CssScrollSnapMarginInline(),
							style, selector);
		}
		return cssScrollSnapMarginInline;
	}

	public CssScrollSnapMarginBottom getScrollSnapMarginBottom() {
		if (cssScrollSnapMarginBottom == null) {
			cssScrollSnapMarginBottom =
					(CssScrollSnapMarginBottom) style.CascadingOrder(new CssScrollSnapMarginBottom(),
							style, selector);
		}
		return cssScrollSnapMarginBottom;
	}

	public CssScrollSnapMarginLeft getScrollSnapMarginLeft() {
		if (cssScrollSnapMarginLeft == null) {
			cssScrollSnapMarginLeft =
					(CssScrollSnapMarginLeft) style.CascadingOrder(new CssScrollSnapMarginLeft(),
							style, selector);
		}
		return cssScrollSnapMarginLeft;
	}

	public CssScrollSnapMarginRight getScrollSnapMarginRight() {
		if (cssScrollSnapMarginRight == null) {
			cssScrollSnapMarginRight =
					(CssScrollSnapMarginRight) style.CascadingOrder(new CssScrollSnapMarginRight(),
							style, selector);
		}
		return cssScrollSnapMarginRight;
	}

	public CssScrollSnapMarginTop getScrollSnapMarginTop() {
		if (cssScrollSnapMarginTop == null) {
			cssScrollSnapMarginTop =
					(CssScrollSnapMarginTop) style.CascadingOrder(new CssScrollSnapMarginTop(),
							style, selector);
		}
		return cssScrollSnapMarginTop;
	}

	public CssScrollSnapMargin getScrollSnapMargin() {
		if (cssScrollSnapMargin == null) {
			cssScrollSnapMargin =
					(CssScrollSnapMargin) style.CascadingOrder(new CssScrollSnapMargin(),
							style, selector);
		}
		return cssScrollSnapMargin;
	}

	public CssScrollPaddingInlineStart getScrollPaddingInlineStart() {
		if (cssScrollPaddingInlineStart == null) {
			cssScrollPaddingInlineStart =
					(CssScrollPaddingInlineStart) style.CascadingOrder(new CssScrollPaddingInlineStart(),
							style, selector);
		}
		return cssScrollPaddingInlineStart;
	}

	public CssScrollPaddingInlineEnd getScrollPaddingInlineEnd() {
		if (cssScrollPaddingInlineEnd == null) {
			cssScrollPaddingInlineEnd =
					(CssScrollPaddingInlineEnd) style.CascadingOrder(new CssScrollPaddingInlineEnd(),
							style, selector);
		}
		return cssScrollPaddingInlineEnd;
	}

	public CssScrollPaddingInline getScrollPaddingInline() {
		if (cssScrollPaddingInline == null) {
			cssScrollPaddingInline =
					(CssScrollPaddingInline) style.CascadingOrder(new CssScrollPaddingInline(),
							style, selector);
		}
		return cssScrollPaddingInline;
	}

	public CssScrollPaddingBlockStart getScrollPaddingBlockStart() {
		if (cssScrollPaddingBlockStart == null) {
			cssScrollPaddingBlockStart =
					(CssScrollPaddingBlockStart) style.CascadingOrder(new CssScrollPaddingBlockStart(),
							style, selector);
		}
		return cssScrollPaddingBlockStart;
	}

	public CssScrollPaddingBlockEnd getScrollPaddingBlockEnd() {
		if (cssScrollPaddingBlockEnd == null) {
			cssScrollPaddingBlockEnd =
					(CssScrollPaddingBlockEnd) style.CascadingOrder(new CssScrollPaddingBlockEnd(),
							style, selector);
		}
		return cssScrollPaddingBlockEnd;
	}

	public CssScrollPaddingBlock getScrollPaddingBlock() {
		if (cssScrollPaddingBlock == null) {
			cssScrollPaddingBlock =
					(CssScrollPaddingBlock) style.CascadingOrder(new CssScrollPaddingBlock(),
							style, selector);
		}
		return cssScrollPaddingBlock;
	}

	public CssScrollPadding getScrollPadding() {
		if (cssScrollPadding == null) {
			cssScrollPadding =
					(CssScrollPadding) style.CascadingOrder(new CssScrollPadding(),
							style, selector);
		}
		return cssScrollPadding;
	}

	public CssScrollPaddingBottom getScrollPaddingBottom() {
		if (cssScrollPaddingBottom == null) {
			cssScrollPaddingBottom =
					(CssScrollPaddingBottom) style.CascadingOrder(new CssScrollPaddingBottom(),
							style, selector);
		}
		return cssScrollPaddingBottom;
	}

	public CssScrollPaddingLeft getScrollPaddingLeft() {
		if (cssScrollPaddingLeft == null) {
			cssScrollPaddingLeft =
					(CssScrollPaddingLeft) style.CascadingOrder(new CssScrollPaddingLeft(),
							style, selector);
		}
		return cssScrollPaddingLeft;
	}

	public CssScrollPaddingRight getScrollPaddingRight() {
		if (cssScrollPaddingRight == null) {
			cssScrollPaddingRight =
					(CssScrollPaddingRight) style.CascadingOrder(new CssScrollPaddingRight(),
							style, selector);
		}
		return cssScrollPaddingRight;
	}

	public CssScrollPaddingTop getScrollPaddingTop() {
		if (cssScrollPaddingTop == null) {
			cssScrollPaddingTop =
					(CssScrollPaddingTop) style.CascadingOrder(new CssScrollPaddingTop(),
							style, selector);
		}
		return cssScrollPaddingTop;
	}

	public CssScrollSnapStop getScrollSnapStop() {
		if (cssScrollSnapStop == null) {
			cssScrollSnapStop =
					(CssScrollSnapStop) style.CascadingOrder(new CssScrollSnapStop(),
							style, selector);
		}
		return cssScrollSnapStop;
	}

	public CssScrollSnapAlign getScrollSnapAlign() {
		if (cssScrollSnapAlign == null) {
			cssScrollSnapAlign =
					(CssScrollSnapAlign) style.CascadingOrder(new CssScrollSnapAlign(),
							style, selector);
		}
		return cssScrollSnapAlign;
	}

	public CssScrollSnapType getScrollSnapType() {
		if (cssScrollSnapType == null) {
			cssScrollSnapType =
					(CssScrollSnapType) style.CascadingOrder(new CssScrollSnapType(),
							style, selector);
		}
		return cssScrollSnapType;
	}
	
	public CssWillChange getWillChange() {
		if (cssWillChange == null) {
			cssWillChange =
					(CssWillChange) style.CascadingOrder(new CssWillChange(),
							style, selector);
		}
		return cssWillChange;
	}

	public CssContain getContain() {
		if (cssContain == null) {
			cssContain =
					(CssContain) style.CascadingOrder(new CssContain(),
							style, selector);
		}
		return cssContain;
	}

	public CssMixBlendMode getMixBlendMode() {
		if (cssMixBlendMode == null) {
			cssMixBlendMode =
					(CssMixBlendMode) style.CascadingOrder(new CssMixBlendMode(),
							style, selector);
		}
		return cssMixBlendMode;
	}

	public CssIsolation getIsolation() {
		if (cssIsolation == null) {
			cssIsolation =
					(CssIsolation) style.CascadingOrder(new CssIsolation(),
							style, selector);
		}
		return cssIsolation;
	}

	public CssBackgroundBlendMode getBackgroundBlendMode() {
		if (cssBackgroundBlendMode == null) {
			cssBackgroundBlendMode =
					(CssBackgroundBlendMode) style.CascadingOrder(new CssBackgroundBlendMode(),
							style, selector);
		}
		return cssBackgroundBlendMode;
	}

	public CssOpacity getOpacity() {
		if (cssOpacity == null) {
			cssOpacity =
					(CssOpacity) style.CascadingOrder(new CssOpacity(),
							style, selector);
		}
		return cssOpacity;
	}

	public CssRubyPosition getRubyPosition() {
		if (cssRubyPosition == null) {
			cssRubyPosition =
					(CssRubyPosition) style.CascadingOrder(
							new CssRubyPosition(), style, selector);
		}
		return cssRubyPosition;
	}

	public CssRubyAlign getRubyAlign() {
		if (cssRubyAlign == null) {
			cssRubyAlign =
					(CssRubyAlign) style.CascadingOrder(
							new CssRubyAlign(), style, selector);
		}
		return cssRubyAlign;
	}

	public CssRubyOverhang getRubyOverhang() {
		if (cssRubyOverhang == null) {
			cssRubyOverhang =
					(CssRubyOverhang) style.CascadingOrder(
							new CssRubyOverhang(), style, selector);
		}
		return cssRubyOverhang;
	}

	public CssBoxSuppress getBoxSuppress() {
		if (cssBoxSuppress == null) {
			cssBoxSuppress =
					(CssBoxSuppress) style.CascadingOrder(
							new CssBoxSuppress(), style, selector);
		}
		return cssBoxSuppress;
	}

	public CssBoxSizing getBoxSizing() {
		if (cssBoxSizing == null) {
			cssBoxSizing =
					(CssBoxSizing) style.CascadingOrder(
							new CssBoxSizing(), style, selector);
		}
		return cssBoxSizing;
	}

	public CssResize getResize() {
		if (cssResize == null) {
			cssResize =
					(CssResize) style.CascadingOrder(
							new CssResize(), style, selector);
		}
		return cssResize;
	}

	public CssTextJustify getTextJustify() {
		if (cssTextJustify == null) {
			cssTextJustify =
					(CssTextJustify) style.CascadingOrder(
							new CssTextJustify(), style, selector);
		}
		return cssTextJustify;
	}

	public CssTextAlignLast getTextAlignLast() {
		if (cssTextAlignLast == null) {
			cssTextAlignLast =
					(CssTextAlignLast) style.CascadingOrder(
							new CssTextAlignLast(), style, selector);
		}
		return cssTextAlignLast;
	}

	public CssDominantBaseline getDominantBaseline() {
		if (cssDominantBaseline == null) {
			cssDominantBaseline =
					(CssDominantBaseline) style.CascadingOrder(
							new CssDominantBaseline(), style, selector);
		}
		return cssDominantBaseline;
	}

	public CssAlignmentBaseline getAlignmentBaseline() {
		if (cssAlignmentBaseline == null) {
			cssAlignmentBaseline =
					(CssAlignmentBaseline) style.CascadingOrder(
							new CssAlignmentBaseline(), style, selector);
		}
		return cssAlignmentBaseline;
	}

	public CssAlignmentAdjust getAlignmentAdjust() {
		if (cssAlignmentAdjust == null) {
			cssAlignmentAdjust =
					(CssAlignmentAdjust) style.CascadingOrder(
							new CssAlignmentAdjust(), style, selector);
		}
		return cssAlignmentAdjust;
	}

	public CssBaselineShift getBaselineShift() {
		if (cssBaselineShift == null) {
			cssBaselineShift =
					(CssBaselineShift) style.CascadingOrder(
							new CssBaselineShift(), style, selector);
		}
		return cssBaselineShift;
	}

	public CssInitialLetter getInitialLetter() {
		if (cssInitialLetter == null) {
			cssInitialLetter =
					(CssInitialLetter) style.CascadingOrder(
							new CssInitialLetter(), style, selector);
		}
		return cssInitialLetter;
	}

	public CssInitialLetterAlign getInitialLetterAlign() {
		if (cssInitialLetterAlign == null) {
			cssInitialLetterAlign =
					(CssInitialLetterAlign) style.CascadingOrder(
							new CssInitialLetterAlign(), style, selector);
		}
		return cssInitialLetterAlign;
	}

	public CssInitialLetterWrap getInitialLetterWrap() {
		if (cssInitialLetterWrap == null) {
			cssInitialLetterWrap =
					(CssInitialLetterWrap) style.CascadingOrder(
							new CssInitialLetterWrap(), style, selector);
		}
		return cssInitialLetterWrap;
	}

	public CssLineBreak getLineBreak() {
		if (cssLineBreak == null) {
			cssLineBreak =
					(CssLineBreak) style.CascadingOrder(
							new CssLineBreak(), style, selector);
		}
		return cssLineBreak;
	}

	public CssWordBreak getWordBreak() {
		if (cssWordBreak == null) {
			cssWordBreak =
					(CssWordBreak) style.CascadingOrder(
							new CssWordBreak(), style, selector);
		}
		return cssWordBreak;
	}

	public CssFit getFit() {
		if (cssFit == null) {
			cssFit =
					(CssFit) style.CascadingOrder(
							new CssFit(), style, selector);
		}
		return cssFit;
	}

	public CssColumns getColumns() {
		if (cssColumns == null) {
			cssColumns =
					(CssColumns) style.CascadingOrder(
							new CssColumns(), style, selector);
		}
		return cssColumns;
	}

	public CssColumnCount getColumnCount() {
		if (cssColumnCount == null) {
			cssColumnCount =
					(CssColumnCount) style.CascadingOrder(
							new CssColumnCount(), style, selector);
		}
		return cssColumnCount;
	}

	public CssColumnSpan getColumnSpan() {
		if (cssColumnSpan == null) {
			cssColumnSpan =
					(CssColumnSpan) style.CascadingOrder(
							new CssColumnSpan(), style, selector);
		}
		return cssColumnSpan;
	}

	public CssColumnWidth getColumnWidth() {
		if (cssColumnWidth == null) {
			cssColumnWidth =
					(CssColumnWidth) style.CascadingOrder(
							new CssColumnWidth(), style, selector);
		}
		return cssColumnWidth;
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

	public CssHangingPunctuation getHangingPunctuation() {
		if (cssHangingPunctuation == null) {
			cssHangingPunctuation =
					(CssHangingPunctuation) style.CascadingOrder(
							new CssHangingPunctuation(), style, selector);
		}
		return cssHangingPunctuation;
	}

	public CssColumnGap getColumnGap() {
		if (cssColumnGap == null) {
			cssColumnGap =
					(CssColumnGap) style.CascadingOrder(
							new CssColumnGap(), style, selector);
		}
		return cssColumnGap;
	}

	public CssBreakBefore getBreakBefore() {
		if (cssBreakBefore == null) {
			cssBreakBefore =
					(CssBreakBefore) style.CascadingOrder(
							new CssBreakBefore(), style, selector);
		}
		return cssBreakBefore;
	}

	public CssBreakAfter getBreakAfter() {
		if (cssBreakAfter == null) {
			cssBreakAfter =
					(CssBreakAfter) style.CascadingOrder(
							new CssBreakAfter(), style, selector);
		}
		return cssBreakAfter;
	}

	public CssBreakInside getBreakInside() {
		if (cssBreakInside == null) {
			cssBreakInside =
					(CssBreakInside) style.CascadingOrder(
							new CssBreakInside(), style, selector);
		}
		return cssBreakInside;
	}

	public CssColumnFill getColumnFill() {
		if (cssColumnFill == null) {
			cssColumnFill =
					(CssColumnFill) style.CascadingOrder(
							new CssColumnFill(), style, selector);
		}
		return cssColumnFill;
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

	public CssIcon getIcon() {
		if (cssIcon == null) {
			cssIcon =
					(CssIcon) style.CascadingOrder(
							new CssIcon(), style, selector);
		}
		return cssIcon;
	}

	// TODO FIXME should be getNavUp... fix ATSC for that.
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

	public CssCaretColor getCaretColor() {
		if (cssCaretColor == null) {
			cssCaretColor =
					(CssCaretColor) style.CascadingOrder(
							new CssCaretColor(), style, selector);
		}
		return cssCaretColor;
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

	public CssMarqueeDirection getMarqueeDirection() {
		if (cssMarqueeDirection == null) {
			cssMarqueeDirection =
					(CssMarqueeDirection) style.CascadingOrder(
							new CssMarqueeDirection(), style, selector);
		}
		return cssMarqueeDirection;
	}

	public CssMarqueePlayCount getMarqueePlayCount() {
		if (cssMarqueePlayCount == null) {
			cssMarqueePlayCount =
					(CssMarqueePlayCount) style.CascadingOrder(
							new CssMarqueePlayCount(), style, selector);
		}
		return cssMarqueePlayCount;
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

	public CssOverflowStyle getOverflowStyle() {
		if (cssOverflowStyle == null) {
			cssOverflowStyle =
					(CssOverflowStyle) style.CascadingOrder(
							new CssOverflowStyle(), style, selector);
		}
		return cssOverflowStyle;
	}

	public org.w3c.css.properties.css.CssBorderTopRightRadius getBorderTopRightRadius() {
		if (cssBorder.borderRadius.topRight == null) {
			cssBorder.borderRadius.topRight =
					(org.w3c.css.properties.css.CssBorderTopRightRadius) style.CascadingOrder(
							new CssBorderTopRightRadius(), style, selector);
		}
		return cssBorder.borderRadius.topRight;
	}

	public org.w3c.css.properties.css.CssBorderBottomRightRadius getBorderBottomRightRadius() {
		if (cssBorder.borderRadius.bottomRight == null) {
			cssBorder.borderRadius.bottomRight =
					(org.w3c.css.properties.css.CssBorderBottomRightRadius) style.CascadingOrder(
							new CssBorderBottomRightRadius(), style, selector);
		}
		return cssBorder.borderRadius.bottomRight;
	}

	public org.w3c.css.properties.css.CssBorderBottomLeftRadius getBorderBottomLeftRadius() {
		if (cssBorder.borderRadius.bottomLeft == null) {
			cssBorder.borderRadius.bottomLeft =
					(org.w3c.css.properties.css.CssBorderBottomLeftRadius) style.CascadingOrder(
							new org.w3c.css.properties.css.CssBorderBottomLeftRadius(), style, selector);
		}
		return cssBorder.borderRadius.bottomLeft;
	}

	public org.w3c.css.properties.css.CssBorderTopLeftRadius getBorderTopLeftRadius() {
		if (cssBorder.borderRadius.topLeft == null) {
			cssBorder.borderRadius.topLeft =
					(org.w3c.css.properties.css.CssBorderTopLeftRadius) style.CascadingOrder(
							new org.w3c.css.properties.css.CssBorderTopLeftRadius(), style, selector);
		}
		return cssBorder.borderRadius.topLeft;
	}

	public org.w3c.css.properties.css.CssBorderRadius getBorderRadius() {
		if (cssBorder.borderRadius == null) {
			cssBorder.borderRadius =
					(org.w3c.css.properties.css.CssBorderRadius) style.CascadingOrder(
							new org.w3c.css.properties.css.CssBorderRadius(), style, selector);
		}
		return cssBorder.borderRadius;
	}

	public CssBoxShadow getBoxShadow() {
		if (cssBoxShadow == null) {
			cssBoxShadow =
					(CssBoxShadow) style.CascadingOrder(
							new CssBoxShadow(), style, selector);
		}
		return cssBoxShadow;
	}

	public CssBoxDecorationBreak getBoxDecorationBreak() {
		if (cssBoxDecorationBreak == null) {
			cssBoxDecorationBreak =
					(CssBoxDecorationBreak) style.CascadingOrder(
							new CssBoxDecorationBreak(), style, selector);
		}
		return cssBoxDecorationBreak;
	}

	public CssFontKerning getFontKerning() {
		if (cssFontKerning == null) {
			cssFontKerning =
					(CssFontKerning) style.CascadingOrder(
							new CssFontKerning(), style, selector);
		}
		return cssFontKerning;
	}

	public CssFontLanguageOverride getFontLanguageOverride() {
		if (cssFontLanguageOverride == null) {
			cssFontLanguageOverride =
					(CssFontLanguageOverride) style.CascadingOrder(
							new CssFontLanguageOverride(), style, selector);
		}
		return cssFontLanguageOverride;
	}

	public CssFontVariantCaps getFontVariantCaps() {
		if (cssFontVariantCaps == null) {
			cssFontVariantCaps =
					(CssFontVariantCaps) style.CascadingOrder(
							new CssFontVariantCaps(), style, selector);
		}
		return cssFontVariantCaps;
	}

	public CssFontVariantPosition getFontVariantPosition() {
		if (cssFontVariantCaps == null) {
			cssFontVariantCaps =
					(CssFontVariantCaps) style.CascadingOrder(
							new CssFontVariantCaps(), style, selector);
		}
		return cssFontVariantPosition;
	}

	public CssFontSynthesis getFontSynthesis() {
		if (cssFontSynthesis == null) {
			cssFontSynthesis =
					(CssFontSynthesis) style.CascadingOrder(
							new CssFontSynthesis(), style, selector);
		}
		return cssFontSynthesis;
	}

	public CssFontVariantEastAsian getFontVariantEastAsian() {
		if (cssFontVariantEastAsian == null) {
			cssFontVariantEastAsian =
					(CssFontVariantEastAsian) style.CascadingOrder(
							new CssFontVariantEastAsian(), style, selector);
		}
		return cssFontVariantEastAsian;
	}

	public CssFontVariantLigatures getFontVariantLigatures() {
		if (cssFontVariantLigatures == null) {
			cssFontVariantLigatures =
					(CssFontVariantLigatures) style.CascadingOrder(
							new CssFontVariantLigatures(), style, selector);
		}
		return cssFontVariantLigatures;
	}

	public CssFontVariantNumeric getFontVariantNumeric() {
		if (cssFontVariantNumeric == null) {
			cssFontVariantNumeric =
					(CssFontVariantNumeric) style.CascadingOrder(
							new CssFontVariantNumeric(), style, selector);
		}
		return cssFontVariantNumeric;
	}

	public CssFontFeatureSettings getFontFeatureSettings() {
		if (cssFontFeatureSettings == null) {
			cssFontFeatureSettings =
					(CssFontFeatureSettings) style.CascadingOrder(
							new CssFontFeatureSettings(), style, selector);
		}
		return cssFontFeatureSettings;
	}

	public CssFontVariantAlternates getFontVariantAlternates() {
		if (cssFontVariantAlternates == null) {
			cssFontVariantAlternates =
					(CssFontVariantAlternates) style.CascadingOrder(
							new CssFontVariantAlternates(), style, selector);
		}
		return cssFontVariantAlternates;
	}

	public CssOverflowWrap getOverflowWrap() {
		if (cssOverflowWrap == null) {
			cssOverflowWrap =
					(CssOverflowWrap) style.CascadingOrder(
							new CssOverflowWrap(), style, selector);
		}
		return cssOverflowWrap;
	}

	public CssHyphens getHyphens() {
		if (cssHyphens == null) {
			cssHyphens =
					(CssHyphens) style.CascadingOrder(
							new CssHyphens(), style, selector);
		}
		return cssHyphens;
	}

	public CssTextDecorationStyle getTextDecorationStyle() {
		if (cssTextDecorationStyle == null) {
			cssTextDecorationStyle =
					(CssTextDecorationStyle) style.CascadingOrder(
							new CssTextDecorationStyle(), style, selector);
		}
		return cssTextDecorationStyle;
	}

	public CssTextDecorationColor getTextDecorationColor() {
		if (cssTextDecorationColor == null) {
			cssTextDecorationColor =
					(CssTextDecorationColor) style.CascadingOrder(
							new CssTextDecorationColor(), style, selector);
		}
		return cssTextDecorationColor;
	}

	public CssTextDecorationLine getTextDecorationLine() {
		if (cssTextDecorationLine == null) {
			cssTextDecorationLine =
					(CssTextDecorationLine) style.CascadingOrder(
							new CssTextDecorationLine(), style, selector);
		}
		return cssTextDecorationLine;
	}

	public CssTextDecorationSkip getTextDecorationSkip() {
		if (cssTextDecorationSkip == null) {
			cssTextDecorationSkip =
					(CssTextDecorationSkip) style.CascadingOrder(
							new CssTextDecorationSkip(), style, selector);
		}
		return cssTextDecorationSkip;
	}

	public CssTextEmphasis getTextEmphasis() {
		if (cssTextEmphasis == null) {
			cssTextEmphasis =
					(CssTextEmphasis) style.CascadingOrder(
							new CssTextEmphasis(), style, selector);
		}
		return cssTextEmphasis;
	}

	public CssTextEmphasisColor getTextEmphasisColor() {
		if (cssTextEmphasisColor == null) {
			cssTextEmphasisColor =
					(CssTextEmphasisColor) style.CascadingOrder(
							new CssTextEmphasisColor(), style, selector);
		}
		return cssTextEmphasisColor;
	}

	public CssTextEmphasisPosition getTextEmphasisPosition() {
		if (cssTextEmphasisPosition == null) {
			cssTextEmphasisPosition =
					(CssTextEmphasisPosition) style.CascadingOrder(
							new CssTextEmphasisPosition(), style, selector);
		}
		return cssTextEmphasisPosition;
	}

	public CssTextEmphasisStyle getTextEmphasisStyle() {
		if (cssTextEmphasisStyle == null) {
			cssTextEmphasisStyle =
					(CssTextEmphasisStyle) style.CascadingOrder(
							new CssTextEmphasisStyle(), style, selector);
		}
		return cssTextEmphasisStyle;
	}

	public CssTextUnderlinePosition getTextUnderlinePosition() {
		if (cssTextUnderlinePosition == null) {
			cssTextUnderlinePosition =
					(CssTextUnderlinePosition) style.CascadingOrder(
							new CssTextUnderlinePosition(), style, selector);
		}
		return cssTextUnderlinePosition;
	}

	public CssTabSize getTabSize() {
		if (cssTabSize == null) {
			cssTabSize =
					(CssTabSize) style.CascadingOrder(
							new CssTabSize(), style, selector);
		}
		return cssTabSize;
	}

	public CssAnimation getAnimation() {
		if (cssAnimation == null) {
			cssAnimation =
					(CssAnimation) style.CascadingOrder(
							new CssAnimation(), style, selector);
		}
		return cssAnimation;
	}

	public CssAnimationDelay getAnimationDelay() {
		if (cssAnimationDelay == null) {
			cssAnimationDelay =
					(CssAnimationDelay) style.CascadingOrder(
							new CssAnimationDelay(), style, selector);
		}
		return cssAnimationDelay;
	}

	public CssAnimationDirection getAnimationDirection() {
		if (cssAnimationDirection == null) {
			cssAnimationDirection =
					(CssAnimationDirection) style.CascadingOrder(
							new CssAnimationDirection(), style, selector);
		}
		return cssAnimationDirection;
	}

	public CssAnimationDuration getAnimationDuration() {
		if (cssAnimationDuration == null) {
			cssAnimationDuration =
					(CssAnimationDuration) style.CascadingOrder(
							new CssAnimationDuration(), style, selector);
		}
		return cssAnimationDuration;
	}

	public CssAnimationIterationCount getAnimationIterationCount() {
		if (cssAnimationIterationCount == null) {
			cssAnimationIterationCount =
					(CssAnimationIterationCount) style.CascadingOrder(
							new CssAnimationIterationCount(), style, selector);
		}
		return cssAnimationIterationCount;
	}

	public CssAnimationName getAnimationName() {
		if (cssAnimationName == null) {
			cssAnimationName =
					(CssAnimationName) style.CascadingOrder(
							new CssAnimationName(), style, selector);
		}
		return cssAnimationName;
	}

	public CssAnimationPlayState getAnimationPlayState() {
		if (cssAnimationPlayState == null) {
			cssAnimationPlayState =
					(CssAnimationPlayState) style.CascadingOrder(
							new CssAnimationPlayState(), style, selector);
		}
		return cssAnimationPlayState;
	}

	public CssAnimationFillMode getAnimationFillMode() {
		if (cssAnimationFillMode == null) {
			cssAnimationFillMode =
					(CssAnimationFillMode) style.CascadingOrder(
							new CssAnimationFillMode(), style, selector);
		}
		return cssAnimationFillMode;
	}

	public CssAnimationTimingFunction getAnimationTimingFunction() {
		if (cssAnimationTimingFunction == null) {
			cssAnimationTimingFunction =
					(CssAnimationTimingFunction) style.CascadingOrder(
							new CssAnimationTimingFunction(), style, selector);
		}
		return cssAnimationTimingFunction;
	}

	public CssTransitionDelay getTransitionDelay() {
		if (cssTransitionDelay == null) {
			cssTransitionDelay =
					(CssTransitionDelay) style.CascadingOrder(
							new CssTransitionDelay(), style, selector);
		}
		return cssTransitionDelay;
	}

	public CssTransitionDuration getTransitionDuration() {
		if (cssTransitionDuration == null) {
			cssTransitionDuration =
					(CssTransitionDuration) style.CascadingOrder(
							new CssTransitionDuration(), style, selector);
		}
		return cssTransitionDuration;
	}

	public CssTransitionProperty getTransitionProperty() {
		if (cssTransitionProperty == null) {
			cssTransitionProperty =
					(CssTransitionProperty) style.CascadingOrder(
							new CssTransitionProperty(), style, selector);
		}
		return cssTransitionProperty;
	}

	public CssTransitionTimingFunction getTransitionTimingFunction() {
		if (cssTransitionTimingFunction == null) {
			cssTransitionTimingFunction =
					(CssTransitionTimingFunction) style.CascadingOrder(
							new CssTransitionTimingFunction(), style, selector);
		}
		return cssTransitionTimingFunction;
	}


	public CssTransition getTransition() {
		if (cssTransition == null) {
			cssTransition =
					(CssTransition) style.CascadingOrder(
							new CssTransition(), style, selector);
		}
		return cssTransition;
	}

	public CssAlignContent getAlignContent() {
		if (cssAlignContent == null) {
			cssAlignContent =
					(CssAlignContent) style.CascadingOrder(
							new CssAlignContent(), style, selector);
		}
		return cssAlignContent;
	}

	public CssAlignItems getAlignItems() {
		if (cssAlignItems == null) {
			cssAlignItems =
					(CssAlignItems) style.CascadingOrder(
							new CssAlignItems(), style, selector);
		}
		return cssAlignItems;
	}

	public CssAlignSelf getAlignSelf() {
		if (cssAlignSelf == null) {
			cssAlignSelf =
					(CssAlignSelf) style.CascadingOrder(
							new CssAlignSelf(), style, selector);
		}
		return cssAlignSelf;
	}

	public CssFlex getFlex() {
		if (cssFlex == null) {
			cssFlex =
					(CssFlex) style.CascadingOrder(
							new CssFlex(), style, selector);
		}
		return cssFlex;
	}

	public CssFlexBasis getFlexBasis() {
		if (cssFlexBasis == null) {
			cssFlexBasis =
					(CssFlexBasis) style.CascadingOrder(
							new CssFlexBasis(), style, selector);
		}
		return cssFlexBasis;
	}

	public CssFlexDirection getFlexDirection() {
		if (cssFlexDirection == null) {
			cssFlexDirection =
					(CssFlexDirection) style.CascadingOrder(
							new CssFlexDirection(), style, selector);
		}
		return cssFlexDirection;
	}

	public CssFlexWrap getFlexWrap() {
		if (cssFlexWrap == null) {
			cssFlexWrap =
					(CssFlexWrap) style.CascadingOrder(
							new CssFlexWrap(), style, selector);
		}
		return cssFlexWrap;
	}

	public CssFlexFlow getFlexFlow() {
		if (cssFlexFlow == null) {
			cssFlexFlow =
					(CssFlexFlow) style.CascadingOrder(
							new CssFlexFlow(), style, selector);
		}
		return cssFlexFlow;
	}

	public CssFlexGrow getFlexGrow() {
		if (cssFlexGrow == null) {
			cssFlexGrow =
					(CssFlexGrow) style.CascadingOrder(
							new CssFlexGrow(), style, selector);
		}
		return cssFlexGrow;
	}

	public CssFlexShrink getFlexShrink() {
		if (cssFlexShrink == null) {
			cssFlexShrink =
					(CssFlexShrink) style.CascadingOrder(
							new CssFlexShrink(), style, selector);
		}
		return cssFlexShrink;
	}

	public CssJustifyContent getJustifyContent() {
		if (cssJustifyContent == null) {
			cssJustifyContent =
					(CssJustifyContent) style.CascadingOrder(
							new CssJustifyContent(), style, selector);
		}
		return cssJustifyContent;
	}

	public CssOrder getOrder() {
		if (cssOrder == null) {
			cssOrder =
					(CssOrder) style.CascadingOrder(
							new CssOrder(), style, selector);
		}
		return cssOrder;
	}

	public CssTransformStyle getTransformStyle() {
		if (cssTransformStyle == null) {
			cssTransformStyle =
					(CssTransformStyle) style.CascadingOrder(
							new CssTransformStyle(), style, selector);
		}
		return cssTransformStyle;
	}

	public CssBackfaceVisibility getBackfaceVisibility() {
		if (cssBackfaceVisibility == null) {
			cssBackfaceVisibility =
					(CssBackfaceVisibility) style.CascadingOrder(
							new CssBackfaceVisibility(), style, selector);
		}
		return cssBackfaceVisibility;
	}

	public CssPerspective getPerspective() {
		if (cssPerspective == null) {
			cssPerspective =
					(CssPerspective) style.CascadingOrder(
							new CssPerspective(), style, selector);
		}
		return cssPerspective;
	}

	public CssPerspectiveOrigin getPerspectiveOrigin() {
		if (cssPerspectiveOrigin == null) {
			cssPerspectiveOrigin =
					(CssPerspectiveOrigin) style.CascadingOrder(
							new CssPerspectiveOrigin(), style, selector);
		}
		return cssPerspectiveOrigin;
	}

	public CssTransformOrigin getTransformOrigin() {
		if (cssTransformOrigin == null) {
			cssTransformOrigin =
					(CssTransformOrigin) style.CascadingOrder(
							new CssTransformOrigin(), style, selector);
		}
		return cssTransformOrigin;
	}

	public CssTransform getTransform() {
		if (cssTransform == null) {
			cssTransform =
					(CssTransform) style.CascadingOrder(
							new CssTransform(), style, selector);
		}
		return cssTransform;
	}

	public CssImeMode getImeMode() {
		if (cssImeMode == null) {
			cssImeMode =
					(CssImeMode) style.CascadingOrder(
							new CssImeMode(), style, selector);
		}
		return cssImeMode;
	}

	public CssTextOverflow getTextOverflow() {
		if (cssTextOverflow == null) {
			cssTextOverflow =
					(CssTextOverflow) style.CascadingOrder(
							new CssTextOverflow(), style, selector);
		}
		return cssTextOverflow;
	}

	public CssObjectFit getObjectFit() {
		if (cssObjectFit == null) {
			cssObjectFit =
					(CssObjectFit) style.CascadingOrder(
							new CssObjectFit(), style, selector);
		}
		return cssObjectFit;
	}

	public CssObjectPosition getObjectPosition() {
		if (cssObjectPosition == null) {
			cssObjectPosition =
					(CssObjectPosition) style.CascadingOrder(
							new CssObjectPosition(), style, selector);
		}
		return cssObjectPosition;
	}

	public CssImageOrientation getImageOrientation() {
		if (cssImageOrientation == null) {
			cssImageOrientation =
					(CssImageOrientation) style.CascadingOrder(
							new CssImageOrientation(), style, selector);
		}
		return cssImageOrientation;
	}

	public CssImageResolution getImageResolution() {
		if (cssImageResolution == null) {
			cssImageResolution =
					(CssImageResolution) style.CascadingOrder(
							new CssImageResolution(), style, selector);
		}
		return cssImageResolution;
	}

	public final CssVoiceBalance getVoiceBalance() {
		if (cssVoiceBalance == null) {
			cssVoiceBalance =
					(CssVoiceBalance) style.CascadingOrder(new CssVoiceBalance(),
							style, selector);
		}
		return cssVoiceBalance;
	}

	public final CssVoiceStress getVoiceStress() {
		if (cssVoiceStress == null) {
			cssVoiceStress =
					(CssVoiceStress) style.CascadingOrder(new CssVoiceStress(),
							style, selector);
		}
		return cssVoiceStress;
	}

	public final CssVoiceDuration getVoiceDuration() {
		if (cssVoiceDuration == null) {
			cssVoiceDuration =
					(CssVoiceDuration) style.CascadingOrder(new CssVoiceDuration(),
							style, selector);
		}
		return cssVoiceDuration;
	}

	public final CssVoiceRate getVoiceRate() {
		if (cssVoiceRate == null) {
			cssVoiceRate =
					(CssVoiceRate) style.CascadingOrder(new CssVoiceRate(),
							style, selector);
		}
		return cssVoiceRate;
	}

	public final CssVoiceVolume getVoiceVolume() {
		if (cssVoiceVolume == null) {
			cssVoiceVolume =
					(CssVoiceVolume) style.CascadingOrder(new CssVoiceVolume(),
							style, selector);
		}
		return cssVoiceVolume;
	}

	public final CssRestAfter getRestAfter() {
		if (cssRestAfter == null) {
			cssRestAfter =
					(CssRestAfter) style.CascadingOrder(new CssRestAfter(),
							style, selector);
		}
		return cssRestAfter;
	}

	public final CssRestBefore getRestBefore() {
		if (cssRestBefore == null) {
			cssRestBefore =
					(CssRestBefore) style.CascadingOrder(new CssRestBefore(),
							style, selector);
		}
		return cssRestBefore;
	}

	public final CssRest getRest() {
		if (cssRest == null) {
			cssRest =
					(CssRest) style.CascadingOrder(new CssRest(),
							style, selector);
		}
		return cssRest;
	}

	public final CssSpeakAs getSpeakAs() {
		if (cssSpeakAs == null) {
			cssSpeakAs = (CssSpeakAs) style.CascadingOrder(new CssSpeakAs(), style, selector);
		}
		return cssSpeakAs;
	}

	public final CssVoicePitch getVoicePitch() {
		if (cssVoicePitch == null) {
			cssVoicePitch = (CssVoicePitch) style.CascadingOrder(new CssVoicePitch(), style, selector);
		}
		return cssVoicePitch;
	}

	public final CssVoiceRange getVoiceRange() {
		if (cssVoiceRange == null) {
			cssVoiceRange = (CssVoiceRange) style.CascadingOrder(new CssVoiceRange(), style, selector);
		}
		return cssVoiceRange;
	}

	public final CssFilter getFilter() {
		if (cssFilter == null) {
			cssFilter = (CssFilter) style.CascadingOrder(new CssFilter(), style, selector);
		}
		return cssFilter;
	}

	public final CssColorInterpolationFilters getColorInterpolationFilters() {
		if (cssColorInterpolationFilters == null) {
			cssColorInterpolationFilters = (CssColorInterpolationFilters) style.CascadingOrder(
					new CssColorInterpolationFilters(), style, selector);
		}
		return cssColorInterpolationFilters;
	}

	public final CssFloodColor getFloodColor() {
		if (cssFloodColor == null) {
			cssFloodColor = (CssFloodColor) style.CascadingOrder(new CssFloodColor(), style, selector);
		}
		return cssFloodColor;
	}

	public final CssFloodOpacity getFloodOpacity() {
		if (cssFloodOpacity == null) {
			cssFloodOpacity = (CssFloodOpacity) style.CascadingOrder(new CssFloodOpacity(), style, selector);
		}
		return cssFloodOpacity;
	}

	public final CssLightingColor getLightingColor() {
		if (cssLightingColor == null) {
			cssLightingColor = (CssLightingColor) style.CascadingOrder(new CssLightingColor(), style, selector);
		}
		return cssLightingColor;
	}

	public final CssFloatReference getFloatReference() {
		if (cssFloatReference == null) {
			cssFloatReference = (CssFloatReference) style.CascadingOrder(new CssFloatReference(), style, selector);
		}
		return cssFloatReference;
	}

	public final CssFloatDefer getFloatDefer() {
		if (cssFloatDefer == null) {
			cssFloatDefer = (CssFloatDefer) style.CascadingOrder(new CssFloatDefer(), style, selector);
		}
		return cssFloatDefer;
	}

	public final CssFloatOffset getFloatOffset() {
		if (cssFloatOffset == null) {
			cssFloatOffset = (CssFloatOffset) style.CascadingOrder(new CssFloatOffset(), style, selector);
		}
		return cssFloatOffset;
	}
	///

	/**
	 * Returns the name of the actual selector
	 */
	public String getSelector() {
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

	/**
	 * Find conflicts in this Style
	 * For the float elements
	 *
	 * @param warnings     For warnings reports.
	 * @param allSelectors All contexts is the entire style sheet.
	 */
	private void findConflictsBlockElements(ApplContext ac, Warnings warnings,
											CssSelectors selector,
											CssSelectors[] allSelectors) {
		if (Util.fromHTMLFile) {
			if ((selector != null) &&
					(!selector.isBlockLevelElement())) {
				if (cssColumnCount != null) {
					warnings.addWarning(new Warning(cssColumnCount,
							"block-level", 1, ac));
				}
				if (cssColumnGap != null) {
					warnings.addWarning(new Warning(cssColumnGap,
							"block-level", 1, ac));
				}
				if (cssColumnSpan != null) {
					warnings.addWarning(new Warning(cssColumnSpan,
							"block-level", 1, ac));
				}
				if (cssColumnWidth != null) {
					warnings.addWarning(new Warning(cssColumnWidth,
							"block-level", 1, ac));
				}
			}
		}
	}

	/**
	 * Find conflicts in this Style
	 *
	 * @param warnings     For warnings reports.
	 * @param allSelectors All contexts is the entire style sheet.
	 */
	public void findConflicts(ApplContext ac, Warnings warnings,
							  CssSelectors selector, CssSelectors[] allSelectors) {
		findConflictsBlockElements(ac, warnings, selector, allSelectors);
		super.findConflicts(ac, warnings, selector, allSelectors);
	}
}

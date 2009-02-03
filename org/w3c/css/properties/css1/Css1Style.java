//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css1;

import java.util.Enumeration;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Util;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 * The Css1Style main class.
 */
public class Css1Style extends CssStyle {

    /** Font properties */
    protected CssFont cssFont = new CssFont();
    protected CssFontCSS2 cssFontCSS2 = new CssFontCSS2();
    protected CssFontCSS1 cssFontCSS1 = new CssFontCSS1();
    protected CssFontStretch cssFontStretch;
    protected CssFontStretchCSS2 cssFontStretchCSS2;
    protected CssFontStretchCSS1 cssFontStretchCSS1;
    protected CssFontSizeAdjust cssFontSizeAdjust;
    protected CssFontSizeAdjustCSS2 cssFontSizeAdjustCSS2;

    /* Color and Background properties */
    /** Color property */
    protected CssColor cssColor;
    protected CssColorCSS2 cssColorCSS2;
    protected CssColorCSS1 cssColorCSS1;
    /** background properties */
    protected CssBackground cssBackground = new CssBackground();
    protected CssBackgroundCSS2 cssBackgroundCSS2 = new CssBackgroundCSS2();
    protected CssBackgroundCSS1 cssBackgroundCSS1 = new CssBackgroundCSS1();
    protected CssBackgroundMob cssBackgroundMob = new CssBackgroundMob();

    /* Text properties */
    /** word-spacing property */
    protected CssWordSpacing cssWordSpacing;
    /** letter-spacing property */
    protected CssLetterSpacing cssLetterSpacing;
    /** text-decoration property */
    protected CssTextDecoration cssTextDecoration;
    protected CssTextDecorationMob cssTextDecorationMob;
    /** vertical-align property */
    protected CssVerticalAlign cssVerticalAlign;
    protected CssVerticalAlignCSS1 cssVerticalAlignCSS1;
    protected CssVerticalAlignMob cssVerticalAlignMob;
    /** text-transform property */
    protected CssTextTransform cssTextTransform;
    /** text-align property */
    protected CssTextAlign cssTextAlign;
    protected CssTextAlignMob cssTextAlignMob;
    /** text-ident property */
    protected CssTextIndent cssTextIndent;
    protected CssTextIndentMob cssTextIndentMob;
    /** text-shadow property */
    protected CssTextShadow cssTextShadow;
    protected CssTextShadowATSC cssTextShadowATSC;
    // line-heigth : see cssFont

    /* Box properties */
    /** margin properties */
    protected CssMargin cssMargin = new CssMargin();
    /** padding properties */
    protected CssPadding cssPadding = new CssPadding();
    /** border properties */
    protected CssBorder cssBorder = new CssBorder();
    protected CssBorderCSS2 cssBorderCSS2 = new CssBorderCSS2();
    protected CssBorderCSS1 cssBorderCSS1 = new CssBorderCSS1();
    /** width property */
    protected CssWidth cssWidth;
    protected CssWidthMob cssWidthMob;

    /** min-width property */
    protected CssMinWidth cssMinWidth;
    protected CssMinWidthATSC cssMinWidthATSC;
    /** max-width property */
    protected CssMaxWidth cssMaxWidth;
    protected CssMaxWidthATSC cssMaxWidthATSC;

    /** min-height property */
    protected CssMinHeight cssMinHeight;
    protected CssMinHeightATSC cssMinHeightATSC;
    /** max-height property */
    protected CssMaxHeight cssMaxHeight;
    protected CssMaxHeightATSC cssMaxHeightATSC;

    /** height property */
    protected CssHeight cssHeight;
    protected CssHeightMob cssHeightMob;
    /** float property */
    protected CssFloat cssFloat;
    /** clear property */
    protected CssClear cssClear;


    /* Classification properties */
    /** display property */
    protected CssDisplay cssDisplay;
    protected CssDisplayCSS2 cssDisplayCSS2;
    protected CssDisplayCSS1 cssDisplayCSS1;
    /** position property */
    protected CssPosition cssPosition;

    /** top property */
    protected CssTop cssTop;
    /** left property */
    protected CssLeft cssLeft;
    /** right property */
    protected CssRight cssRight;
    /** bottom property */
    protected CssBottom cssBottom;

    /** z-index property */
    protected CssZIndex cssZIndex;

    /** direction property */
    protected CssDirection cssDirection;
    protected CssDirectionATSC cssDirectionATSC;

    /** unicode-bidi property */
    protected CssUnicodeBidi cssUnicodeBidi;
    protected CssUnicodeBidiATSC cssUnicodeBidiATSC;

    /** white-space property */
    protected CssWhiteSpace cssWhiteSpace;
    /** list-style properties */
    protected CssListStyle cssListStyle = new CssListStyle();
    protected CssListStyleCSS2 cssListStyleCSS2 = new CssListStyleCSS2();
    protected CssListStyleCSS1 cssListStyleCSS1 = new CssListStyleCSS1();

    /** overflow property */
    protected CssOverflow cssOverflow;

    /** clip property */
    protected CssClip cssClip;

    /** visibility property */
    protected CssVisibility cssVisibility;

    /** content property */
    protected CssContentCSS2 cssContentCSS2;
    protected CssContent cssContent;
    /** quotes property */
    protected CssQuotes cssQuotes;
    protected CssQuotesATSC cssQuotesATSC;
    /** counter-reset property */
    protected CssCounterReset cssCounterReset;
    /** counter-increment property */
    protected CssCounterIncrement cssCounterIncrement;
    /** marker-offset property */
    protected CssMarkerOffset cssMarkerOffset;
    protected CssMarkerOffsetATSC cssMarkerOffsetATSC;

    /**TV property */
    protected CssListStyleTypeTV cssListStyleTypeTV;
    /**TV property */
    protected CssListStyleTV cssListStyleTV;
    /**TV property */
    protected CssPositionTV cssPositionTV;
    /**TV property */
    protected CssTextAlignTV cssTextAlignTV;
    /**TV property */
    protected CssTextDecorationTV cssTextDecorationTV;
    /**TV property */
    protected CssVerticalAlignTV cssVerticalAlignTV;

    /*
     * Font Properties
     */

    /**
     * Get the font-style property
     */
    public final CssFontStyle getFontStyle() {
	if (cssFont.fontStyle == null) {
	    cssFont.fontStyle =
		(CssFontStyle) style.CascadingOrder(new CssFontStyle(),
			style, selector);
	}
	return cssFont.fontStyle;
    }

    public final CssFontStyleCSS2 getFontStyleCSS2() {
	if (cssFontCSS2.fontStyle == null) {
	    cssFontCSS2.fontStyle =
		(CssFontStyleCSS2) style.CascadingOrder(new CssFontStyleCSS2(),
			style, selector);
	}
	return cssFontCSS2.fontStyle;
    }

    public final CssFontStyleCSS1 getFontStyleCSS1() {
	if (cssFontCSS1.fontStyle == null) {
	    cssFontCSS1.fontStyle =
		(CssFontStyleCSS1) style.CascadingOrder(new CssFontStyleCSS1(),
			style, selector);
	}
	return cssFontCSS1.fontStyle;
    }

    /**
     * Get the font-variant property
     */
    public final CssFontVariant getFontVariant() {
	if (cssFont.fontVariant == null) {
	    cssFont.fontVariant =
		(CssFontVariant) style.CascadingOrder(new CssFontVariant(),
			style, selector);
	}
	return cssFont.fontVariant;
    }

    public final CssFontVariantCSS2 getFontVariantCSS2() {
	if (cssFontCSS2.fontVariant == null) {
	    cssFontCSS2.fontVariant =
		(CssFontVariantCSS2) style.CascadingOrder(new CssFontVariantCSS2(),
			style, selector);
	}
	return cssFontCSS2.fontVariant;
    }

    public final CssFontVariantCSS1 getFontVariantCSS1() {
	if (cssFontCSS1.fontVariant == null) {
	    cssFontCSS1.fontVariant =
		(CssFontVariantCSS1) style.CascadingOrder(new CssFontVariantCSS1(),
			style, selector);
	}
	return cssFontCSS1.fontVariant;
    }

    /**
     * Get the font-weight property
     */
    public final CssFontWeightCSS2 getFontWeightCSS2() {
	if (cssFontCSS2.fontWeight == null) {
	    cssFontCSS2.fontWeight =
		(CssFontWeightCSS2) style.CascadingOrder(new CssFontWeightCSS2(),
			style, selector);
	}
	return cssFontCSS2.fontWeight;
    }

    public final CssFontWeight getFontWeight() {
	if (cssFont.fontWeight == null) {
	    cssFont.fontWeight =
		(CssFontWeight) style.CascadingOrder(new CssFontWeight(),
			style, selector);
	}
	return cssFont.fontWeight;
    }

    public final CssFontWeightCSS1 getFontWeightCSS1() {
	if (cssFontCSS1.fontWeight == null) {
	    cssFontCSS1.fontWeight =
		(CssFontWeightCSS1) style.CascadingOrder(new CssFontWeightCSS1(),
			style, selector);
	}
	return cssFontCSS1.fontWeight;
    }

    /**
     * Get the font-stretch property
     */
    public final CssFontStretch getFontStretch() {
	if (cssFontStretch == null) {
	    cssFontStretch =
		(CssFontStretch) style.CascadingOrder(new CssFontStretch(),
			style, selector);
	}
	return cssFontStretch;
    }

    public final CssFontStretchCSS2 getFontStretchCSS2() {
	if (cssFontStretchCSS2 == null) {
	    cssFontStretchCSS2 =
		(CssFontStretchCSS2) style.CascadingOrder(new CssFontStretchCSS2(),
			style, selector);
	}
	return cssFontStretchCSS2;
    }

    public final CssFontStretchCSS1 getFontStretchCSS1() {
	if (cssFontStretchCSS1 == null) {
	    cssFontStretchCSS1 =
		(CssFontStretchCSS1) style.CascadingOrder(new CssFontStretchCSS1(),
			style, selector);
	}
	return cssFontStretchCSS1;
    }

    /**
     * Get the font-size property
     */
    public final CssFontSizeCSS2 getFontSizeCSS2() {
	if (cssFontCSS2.fontSize == null) {
	    cssFontCSS2.fontSize =
		(CssFontSizeCSS2) style.CascadingOrder(new CssFontSizeCSS2(),
			style, selector);
	}
	return cssFontCSS2.fontSize;
    }

    public final CssFontSize getFontSize() {
	if (cssFont.fontSize == null) {
	    cssFont.fontSize =
		(CssFontSize) style.CascadingOrder(new CssFontSize(),
			style, selector);
	}
	return cssFont.fontSize;
    }

    public final CssFontSizeCSS1 getFontSizeCSS1() {
	if (cssFontCSS1.fontSize == null) {
	    cssFontCSS1.fontSize =
		(CssFontSizeCSS1) style.CascadingOrder(new CssFontSizeCSS1(),
			style, selector);
	}
	return cssFontCSS1.fontSize;
    }

    /**
     * Get the font-size-adjust property
     */
    public final CssFontSizeAdjustCSS2 getFontSizeAdjustCSS2() {
	if (cssFontSizeAdjustCSS2 == null) {
	    cssFontSizeAdjustCSS2 =
		(CssFontSizeAdjustCSS2) style.CascadingOrder(
			new CssFontSizeAdjustCSS2(),
			style, selector);
	}
	return cssFontSizeAdjustCSS2;
    }

    public final CssFontSizeAdjust getFontSizeAdjust() {
	if (cssFontSizeAdjust == null) {
	    cssFontSizeAdjust =
		(CssFontSizeAdjust) style.CascadingOrder(
			new CssFontSizeAdjust(),
			style, selector);
	}
	return cssFontSizeAdjust;
    }

    /**
     * Get the font-family property
     */
    public final CssFontFamily getFontFamily() {
	if (cssFont.fontFamily == null) {
	    cssFont.fontFamily =
		(CssFontFamily) style.CascadingOrder(new CssFontFamily(),
			style, selector);
	}
	return cssFont.fontFamily;
    }

    public final CssFontFamilyCSS2 getFontFamilyCSS2() {
	if (cssFontCSS2.fontFamily == null) {
	    cssFontCSS2.fontFamily =
		(CssFontFamilyCSS2) style.CascadingOrder(new CssFontFamilyCSS2(),
			style, selector);
	}
	return cssFontCSS2.fontFamily;
    }

    public final CssFontFamilyCSS1 getFontFamilyCSS1() {
	if (cssFontCSS1.fontFamily == null) {
	    cssFontCSS1.fontFamily =
		(CssFontFamilyCSS1) style.CascadingOrder(new CssFontFamilyCSS1(),
			style, selector);
	}
	return cssFontCSS1.fontFamily;
    }

    /**
     * Get the font property
     */
    public final CssFont getFont() {
	if (cssFont.value != null) {
	    // nothing
	} else {
	    if (cssFont.fontStyle == null) {
		cssFont.fontStyle = getFontStyle();
	    }
	    if (cssFont.fontVariant == null) {
		cssFont.fontVariant = getFontVariant();
	    }
	    if (cssFont.fontWeight == null) {
		cssFont.fontWeight = getFontWeight();
	    }
	    if (cssFont.fontSize == null) {
		cssFont.fontSize = getFontSize();
	    }
	    if (cssFont.lineHeight == null) {
		cssFont.lineHeight = getLineHeight();
	    }
	    if (cssFont.fontFamily == null) {
		cssFont.fontFamily = getFontFamily();
	    }
	}
	return cssFont;
    }

    public final CssFontCSS2 getFontCSS2() {
	if (cssFontCSS2.value != null) {
	    // nothing
	} else {
	    if (cssFontCSS2.fontStyle == null) {
		cssFontCSS2.fontStyle = getFontStyleCSS2();
	    }
	    if (cssFontCSS2.fontVariant == null) {
		cssFontCSS2.fontVariant = getFontVariantCSS2();
	    }
	    if (cssFontCSS2.fontWeight == null) {
		cssFontCSS2.fontWeight = getFontWeightCSS2();
	    }
	    if (cssFontCSS2.fontSize == null) {
		cssFontCSS2.fontSize = getFontSizeCSS2();
	    }
	    if (cssFontCSS2.lineHeight == null) {
		cssFontCSS2.lineHeight = getLineHeightCSS2();
	    }
	    if (cssFontCSS2.fontFamily == null) {
		cssFontCSS2.fontFamily = getFontFamilyCSS2();
	    }
	}
	return cssFontCSS2;
    }

    public final CssFontCSS1 getFontCSS1() {
	if (cssFontCSS1.value != null) {
	    // nothing
	} else {
	    if (cssFontCSS1.fontStyle == null) {
		cssFontCSS1.fontStyle = getFontStyleCSS1();
	    }
	    if (cssFontCSS1.fontVariant == null) {
		cssFontCSS1.fontVariant = getFontVariantCSS1();
	    }
	    if (cssFontCSS1.fontWeight == null) {
		cssFontCSS1.fontWeight = getFontWeightCSS1();
	    }
	    if (cssFontCSS1.fontSize == null) {
		cssFontCSS1.fontSize = getFontSizeCSS1();
	    }
	    if (cssFontCSS1.lineHeight == null) {
		cssFontCSS1.lineHeight = getLineHeightCSS1();
	    }
	    if (cssFontCSS1.fontFamily == null) {
		cssFontCSS1.fontFamily = getFontFamilyCSS1();
	    }
	}
	return cssFontCSS1;
    }

    /*
     * Color and Background properties
     */

    /**
     * Get the color property
     */
    public final CssColor getColor() {
	if (cssColor == null) {
	    cssColor = (CssColor)
	    style.CascadingOrder(new CssColor(), style, selector);
	}
	return cssColor;
    }

    public final CssColorCSS2 getColorCSS2() {
	if (cssColorCSS2 == null) {
	    cssColorCSS2 = (CssColorCSS2)
	    style.CascadingOrder(new CssColorCSS2(), style, selector);
	}
	return cssColorCSS2;
    }

    public final CssColorCSS1 getColorCSS1() {
	if (cssColorCSS1 == null) {
	    cssColorCSS1 = (CssColorCSS1)
	    style.CascadingOrder(new CssColorCSS1(), style, selector);
	}
	return cssColorCSS1;
    }

    /**
     * Get the background-color property
     */
    public final CssBackgroundColor getBackgroundColor() {
	if (cssBackground.color == null) {
	    cssBackground.color =
		(CssBackgroundColor) style.CascadingOrder(new CssBackgroundColor(),
			style, selector);
	}
	return cssBackground.color;
    }

    public final CssBackgroundColorCSS2 getBackgroundColorCSS2() {
	if (cssBackgroundCSS2.color == null) {
	    cssBackgroundCSS2.color =
		(CssBackgroundColorCSS2) style.CascadingOrder(
			new CssBackgroundColorCSS2(),
			style, selector);
	}
	return cssBackgroundCSS2.color;
    }

    public final CssBackgroundColorCSS1 getBackgroundColorCSS1() {
	if (cssBackgroundCSS1.color == null) {
	    cssBackgroundCSS1.color =
		(CssBackgroundColorCSS1) style.CascadingOrder(
			new CssBackgroundColorCSS1(),
			style, selector);
	}
	return cssBackgroundCSS1.color;
    }

    public final CssBackgroundColorMob getBackgroundColorMob() {
	if (cssBackgroundMob.color == null) {
	    cssBackgroundMob.color =
		(CssBackgroundColorMob) style.CascadingOrder(
			new CssBackgroundColorMob(),
			style, selector);
	}
	return cssBackgroundMob.color;
    }

    /**
     * Get the background-image property
     */
    public final CssBackgroundImage getBackgroundImage() {
	if (cssBackground.image == null) {
	    cssBackground.image =
		(CssBackgroundImage) style.CascadingOrder(new CssBackgroundImage(),
			style, selector);
	}
	return cssBackground.image;
    }

    public final CssBackgroundImageCSS2 getBackgroundImageCSS2() {
	if (cssBackgroundCSS2.image == null) {
	    cssBackgroundCSS2.image =
		(CssBackgroundImageCSS2) style.CascadingOrder(new CssBackgroundImageCSS2(),
			style, selector);
	}
	return cssBackgroundCSS2.image;
    }

    public final CssBackgroundImageCSS1 getBackgroundImageCSS1() {
	if (cssBackgroundCSS1.image == null) {
	    cssBackgroundCSS1.image =
		(CssBackgroundImageCSS1) style.CascadingOrder(new CssBackgroundImageCSS1(),
			style, selector);
	}
	return cssBackgroundCSS1.image;
    }

    public final CssBackgroundImageMob getBackgroundImageMob() {
	if (cssBackgroundMob.image == null) {
	    cssBackgroundMob.image =
		(CssBackgroundImageMob) style.CascadingOrder(new CssBackgroundImageMob(),
			style, selector);
	}
	return cssBackgroundMob.image;
    }

    /**
     * Get the background-repeat property
     */
    public final CssBackgroundRepeat getBackgroundRepeat() {
	if (cssBackground.repeat == null) {
	    cssBackground.repeat =
		(CssBackgroundRepeat) style.CascadingOrder(new CssBackgroundRepeat(),
			style, selector);
	}
	return cssBackground.repeat;
    }

    public final CssBackgroundRepeatCSS2 getBackgroundRepeatCSS2() {
	if (cssBackgroundCSS2.repeat == null) {
	    cssBackgroundCSS2.repeat =
		(CssBackgroundRepeatCSS2) style.CascadingOrder(new CssBackgroundRepeatCSS2(),
			style, selector);
	}
	return cssBackgroundCSS2.repeat;
    }

    public final CssBackgroundRepeatCSS1 getBackgroundRepeatCSS1() {
	if (cssBackgroundCSS1.repeat == null) {
	    cssBackgroundCSS1.repeat =
		(CssBackgroundRepeatCSS1) style.CascadingOrder(new CssBackgroundRepeatCSS1(),
			style, selector);
	}
	return cssBackgroundCSS1.repeat;
    }

    public final CssBackgroundRepeatMob getBackgroundRepeatMob() {
	if (cssBackgroundMob.repeat == null) {
	    cssBackgroundMob.repeat =
		(CssBackgroundRepeatMob) style.CascadingOrder(new CssBackgroundRepeatMob(),
			style, selector);
	}
	return cssBackgroundMob.repeat;
    }

    /**
     * Get the background-attachment property
     */
    public final CssBackgroundAttachment getBackgroundAttachment() {
	if (cssBackground.attachment == null) {
	    cssBackground.attachment =
		(CssBackgroundAttachment) style.CascadingOrder(new CssBackgroundAttachment(),
			style, selector);
	}
	return cssBackground.attachment;
    }

    public final CssBackgroundAttachmentCSS2 getBackgroundAttachmentCSS2() {
	if (cssBackgroundCSS2.attachment == null) {
	    cssBackgroundCSS2.attachment =
		(CssBackgroundAttachmentCSS2) style.CascadingOrder(new CssBackgroundAttachmentCSS2(),
			style, selector);
	}
	return cssBackgroundCSS2.attachment;
    }

    public final CssBackgroundAttachmentCSS1 getBackgroundAttachmentCSS1() {
	if (cssBackgroundCSS1.attachment == null) {
	    cssBackgroundCSS1.attachment =
		(CssBackgroundAttachmentCSS1) style.CascadingOrder(new CssBackgroundAttachmentCSS1(),
			style, selector);
	}
	return cssBackgroundCSS1.attachment;
    }

    public final CssBackgroundAttachmentMob getBackgroundAttachmentMob() {
	if (cssBackgroundMob.attachment == null) {
	    cssBackgroundMob.attachment =
		(CssBackgroundAttachmentMob) style.CascadingOrder(new CssBackgroundAttachmentMob(),
			style, selector);
	}
	return cssBackgroundMob.attachment;
    }

    /**
     * Get the background-position property
     */
    public final CssBackgroundPosition getBackgroundPosition() {
	if (cssBackground.position == null) {
	    cssBackground.position =
		(CssBackgroundPosition) style.CascadingOrder(new CssBackgroundPosition(),
			style, selector);
	}
	return cssBackground.position;
    }

    public final CssBackgroundPositionCSS2 getBackgroundPositionCSS2() {
	if (cssBackgroundCSS2.position == null) {
	    cssBackgroundCSS2.position =
		(CssBackgroundPositionCSS2) style.CascadingOrder(new CssBackgroundPositionCSS2(),
			style, selector);
	}
	return cssBackgroundCSS2.position;
    }

    public final CssBackgroundPositionCSS1 getBackgroundPositionCSS1() {
	if (cssBackgroundCSS1.position == null) {
	    cssBackgroundCSS1.position =
		(CssBackgroundPositionCSS1) style.CascadingOrder(new CssBackgroundPositionCSS1(),
			style, selector);
	}
	return cssBackgroundCSS1.position;
    }

    public final CssBackgroundPositionMob getBackgroundPositionMob() {
	if (cssBackgroundMob.position == null) {
	    cssBackgroundMob.position =
		(CssBackgroundPositionMob) style.CascadingOrder(new CssBackgroundPositionMob(),
			style, selector);
	}
	return cssBackgroundMob.position;
    }

    /**
     * Get the background property
     */
    public final CssBackground getBackground() {
	if (cssBackground.getColor() == null) {
	    cssBackground.color = getBackgroundColor();
	}
	if (cssBackground.image == null) {
	    cssBackground.image = getBackgroundImage();
	}
	if (cssBackground.repeat == null) {
	    cssBackground.repeat = getBackgroundRepeat();
	}
	if (cssBackground.attachment == null) {
	    cssBackground.attachment = getBackgroundAttachment();
	}
	if (cssBackground.position == null) {
	    cssBackground.position = getBackgroundPosition();
	}
	return cssBackground;
    }

    public final CssBackgroundCSS2 getBackgroundCSS2() {
	if (cssBackgroundCSS2.getColor() == null) {
	    cssBackgroundCSS2.color = getBackgroundColorCSS2();
	}
	if (cssBackgroundCSS2.image == null) {
	    cssBackgroundCSS2.image = getBackgroundImageCSS2();
	}
	if (cssBackgroundCSS2.repeat == null) {
	    cssBackgroundCSS2.repeat = getBackgroundRepeatCSS2();
	}
	if (cssBackgroundCSS2.attachment == null) {
	    cssBackgroundCSS2.attachment = getBackgroundAttachmentCSS2();
	}
	if (cssBackgroundCSS2.position == null) {
	    cssBackgroundCSS2.position = getBackgroundPositionCSS2();
	}
	return cssBackgroundCSS2;
    }

    public final CssBackgroundCSS1 getBackgroundCSS1() {
	if (cssBackgroundCSS1.getColor() == null) {
	    cssBackgroundCSS1.color = getBackgroundColorCSS1();
	}
	if (cssBackgroundCSS1.image == null) {
	    cssBackgroundCSS1.image = getBackgroundImageCSS1();
	}
	if (cssBackgroundCSS1.repeat == null) {
	    cssBackgroundCSS1.repeat = getBackgroundRepeatCSS1();
	}
	if (cssBackgroundCSS1.attachment == null) {
	    cssBackgroundCSS1.attachment = getBackgroundAttachmentCSS1();
	}
	if (cssBackgroundCSS1.position == null) {
	    cssBackgroundCSS1.position = getBackgroundPositionCSS1();
	}
	return cssBackgroundCSS1;
    }

    public final CssBackgroundMob getBackgroundMob() {
	if (cssBackgroundMob.getColor() == null) {
	    cssBackgroundMob.color = getBackgroundColorMob();
	}
	if (cssBackgroundMob.image == null) {
	    cssBackgroundMob.image = getBackgroundImageMob();
	}
	if (cssBackgroundMob.repeat == null) {
	    cssBackgroundMob.repeat = getBackgroundRepeatMob();
	}
	if (cssBackgroundMob.attachment == null) {
	    cssBackgroundMob.attachment = getBackgroundAttachmentMob();
	}
	if (cssBackgroundMob.position == null) {
	    cssBackgroundMob.position = getBackgroundPositionMob();
	}
	return cssBackgroundMob;
    }

    /*
     * Text properties
     */

    /**
     * Get the word-spacing property
     */
    public final CssWordSpacing getWordSpacing() {
	if (cssWordSpacing == null) {
	    cssWordSpacing =
		(CssWordSpacing) style.CascadingOrder(new CssWordSpacing(),
			style, selector);
	}
	return cssWordSpacing;
    }

    /**
     * Get the letter-spacing property
     */
    public final CssLetterSpacing getLetterSpacing() {
	if (cssLetterSpacing == null) {
	    cssLetterSpacing =
		(CssLetterSpacing) style.CascadingOrder(new CssLetterSpacing(),
			style, selector);
	}
	return cssLetterSpacing;
    }

    /**
     * Get the text-decoration property
     */
    public final CssTextDecoration getTextDecoration() {
	if (cssTextDecoration == null) {
	    cssTextDecoration =
		(CssTextDecoration) style.CascadingOrder(new CssTextDecoration(),
			style, selector);
	}
	return cssTextDecoration;
    }

    public final CssTextDecorationMob getTextDecorationMob() {
	if (cssTextDecorationMob == null) {
	    cssTextDecorationMob =
		(CssTextDecorationMob) style.CascadingOrder(new CssTextDecorationMob(),
			style, selector);
	}
	return cssTextDecorationMob;
    }

    /**
     * Get the vertical-align property
     */
    public final CssVerticalAlign getVerticalAlign() {
	if (cssVerticalAlign == null) {
	    cssVerticalAlign =
		(CssVerticalAlign) style.CascadingOrder(new CssVerticalAlign(),
			style, selector);
	}
	return cssVerticalAlign;
    }

    public final CssVerticalAlignMob getVerticalAlignMob() {
	if (cssVerticalAlignMob == null) {
	    cssVerticalAlignMob =
		(CssVerticalAlignMob) style.CascadingOrder(new CssVerticalAlignMob(),
			style, selector);
	}
	return cssVerticalAlignMob;
    }

    public final CssVerticalAlignCSS1 getVerticalAlignCSS1() {
	if (cssVerticalAlignCSS1 == null) {
	    cssVerticalAlignCSS1 =
		(CssVerticalAlignCSS1) style.CascadingOrder(new CssVerticalAlignCSS1(),
			style, selector);
	}
	return cssVerticalAlignCSS1;
    }

    /**
     * Get the text-transform property
     */
    public final CssTextTransform getTextTransform() {
	if (cssTextTransform == null) {
	    cssTextTransform =
		(CssTextTransform) style.CascadingOrder(new CssTextTransform(),
			style, selector);
	}
	return cssTextTransform;
    }

    /**
     * Get the text-align property
     */
    public final CssTextAlign getTextAlign() {
	if (cssTextAlign == null) {
	    cssTextAlign =
		(CssTextAlign) style.CascadingOrder(new CssTextAlign(),
			style, selector);
	}
	return cssTextAlign;
    }

    public final CssTextAlignMob getTextAlignMob() {
	if (cssTextAlignMob == null) {
	    cssTextAlignMob =
		(CssTextAlignMob) style.CascadingOrder(new CssTextAlignMob(),
			style, selector);
	}
	return cssTextAlignMob;
    }

    /**
     * Get the text-indent property
     */
    public final CssTextIndent getTextIndent() {
	if (cssTextIndent == null) {
	    cssTextIndent =
		(CssTextIndent) style.CascadingOrder(new CssTextIndent(),
			style, selector);
	}
	return cssTextIndent;
    }

    public final CssTextIndentMob getTextIndentMob() {
	if (cssTextIndentMob == null) {
	    cssTextIndentMob =
		(CssTextIndentMob) style.CascadingOrder(new CssTextIndentMob(),
			style, selector);
	}
	return cssTextIndentMob;
    }

    /**
     * Get the text-shadow property
     */
    public final CssTextShadow getTextShadow() {
	if (cssTextShadow == null) {
	    cssTextShadow =
		(CssTextShadow) style.CascadingOrder(new CssTextShadow(),
			style, selector);
	}
	return cssTextShadow;
    }

    public final CssTextShadowATSC getTextShadowATSC() {
	if (cssTextShadowATSC == null) {
	    cssTextShadowATSC =
		(CssTextShadowATSC) style.CascadingOrder(new CssTextShadowATSC(),
			style, selector);
	}
	return cssTextShadowATSC;
    }

    /**
     * Get the line-height property
     */
    public final CssLineHeight getLineHeight() {
	if (cssFont.lineHeight == null) {
	    cssFont.lineHeight =
		(CssLineHeight) style.CascadingOrder(new CssLineHeight(),
			style, selector);
	}
	return cssFont.lineHeight;
    }

    public final CssLineHeightCSS2 getLineHeightCSS2() {
	if (cssFontCSS2.lineHeight == null) {
	    cssFontCSS2.lineHeight =
		(CssLineHeightCSS2) style.CascadingOrder(new CssLineHeightCSS2(),
			style, selector);
	}
	return cssFontCSS2.lineHeight;
    }

    public final CssLineHeightCSS1 getLineHeightCSS1() {
	if (cssFontCSS1.lineHeight == null) {
	    cssFontCSS1.lineHeight =
		(CssLineHeightCSS1) style.CascadingOrder(new CssLineHeightCSS1(),
			style, selector);
	}
	return cssFontCSS1.lineHeight;
    }

    /*
     * Box properties
     */

    /**
     * Get the margin-top property
     */
    public final CssMarginTop getMarginTop() {
	if (cssMargin.top == null) {
	    cssMargin.top =
		(CssMarginTop) style.CascadingOrder(new CssMarginTop(),
			style, selector);
	}
	return cssMargin.top;
    }

    /**
     * Get the margin-right property
     */
    public final CssMarginRight getMarginRight() {
	if (cssMargin.right == null) {
	    cssMargin.right =
		(CssMarginRight) style.CascadingOrder(new CssMarginRight(),
			style, selector);
	}
	return cssMargin.right;
    }

    /**
     * Get the margin-bottom property
     */
    public final CssMarginBottom getMarginBottom() {
	if (cssMargin.bottom == null) {
	    cssMargin.bottom =
		(CssMarginBottom) style.CascadingOrder(new CssMarginBottom(),
			style, selector);
	}
	return cssMargin.bottom;
    }

    /**
     * Get the margin-left property
     */
    public final CssMarginLeft getMarginLeft() {
	if (cssMargin.left == null) {
	    cssMargin.left =
		(CssMarginLeft) style.CascadingOrder(new CssMarginLeft(),
			style, selector);
	}
	return cssMargin.left;
    }

    /**
     * Get the margin property
     */
    public final CssMargin getMargin() {
	if (cssMargin.top == null)
	    cssMargin.top = getMarginTop();
	if (cssMargin.right == null)
	    cssMargin.right = getMarginRight();
	if (cssMargin.bottom == null)
	    cssMargin.bottom = getMarginBottom();
	if (cssMargin.left == null)
	    cssMargin.left = getMarginLeft();
	return cssMargin;
    }

    /**
     * Get the padding-top property
     */
    public final CssPaddingTop getPaddingTop() {
	if (cssPadding.top == null) {
	    cssPadding.top =
		(CssPaddingTop) style.CascadingOrder(new CssPaddingTop(),
			style, selector);
	}
	return cssPadding.top;
    }

    /**
     * Get the padding-right property
     */
    public final CssPaddingRight getPaddingRight() {
	if (cssPadding.right == null) {
	    cssPadding.right =
		(CssPaddingRight) style.CascadingOrder(new CssPaddingRight(),
			style, selector);
	}
	return cssPadding.right;
    }

    /**
     * Get the padding-bottom property
     */
    public final CssPaddingBottom getPaddingBottom() {
	if (cssPadding.bottom == null) {
	    cssPadding.bottom =
		(CssPaddingBottom) style.CascadingOrder(new CssPaddingBottom(),
			style, selector);
	}
	return cssPadding.bottom;
    }

    /**
     * Get the padding-left property
     */
    public final CssPaddingLeft getPaddingLeft() {
	if (cssPadding.left == null) {
	    cssPadding.left =
		(CssPaddingLeft) style.CascadingOrder(new CssPaddingLeft(),
			style, selector);
	}
	return cssPadding.left;
    }

    /**
     * Get the padding property
     */
    public final CssPadding getPadding() {
	if (cssPadding.top == null)
	    cssPadding.top = getPaddingTop();
	if (cssPadding.right == null)
	    cssPadding.right = getPaddingRight();
	if (cssPadding.bottom == null)
	    cssPadding.bottom = getPaddingBottom();
	if (cssPadding.left == null)
	    cssPadding.left = getPaddingLeft();
	return cssPadding;
    }

    /**
     * Get the border-top-width property
     */
    public final CssBorderTopWidth getBorderTopWidth() {
	if (cssBorder.getTop().getWidth() == null) {
	    cssBorder.getTop().width =
		(CssBorderTopWidth) style.CascadingOrder(new CssBorderTopWidth(),
			style, selector);
	}
	return cssBorder.getTop().width;
    }

    public final CssBorderTopWidthCSS2 getBorderTopWidthCSS2() {
	if (cssBorderCSS2.getTop().getWidth() == null) {
	    cssBorderCSS2.getTop().width =
		(CssBorderTopWidthCSS2) style.CascadingOrder(new CssBorderTopWidthCSS2(),
			style, selector);
	}
	return cssBorderCSS2.getTop().width;
    }

    public final CssBorderTopWidthCSS1 getBorderTopWidthCSS1() {
	if (cssBorderCSS1.getTop().getWidth() == null) {
	    cssBorderCSS1.getTop().width =
		(CssBorderTopWidthCSS1) style.CascadingOrder(new CssBorderTopWidthCSS1(),
			style, selector);
	}
	return cssBorderCSS1.getTop().width;
    }

    /**
     * Get the border-top-style property
     */
    public final CssBorderTopStyle getBorderTopStyle() {
	if (cssBorder.getTop().getStyle() == null) {
	    cssBorder.getTop().style =
		(CssBorderTopStyle) style.CascadingOrder(new CssBorderTopStyle(),
			style, selector);
	}
	return cssBorder.getTop().style;
    }

    public final CssBorderTopStyleCSS2 getBorderTopStyleCSS2() {
	if (cssBorderCSS2.getTop().getStyle() == null) {
	    cssBorderCSS2.getTop().style =
		(CssBorderTopStyleCSS2) style.CascadingOrder(new CssBorderTopStyleCSS2(),
			style, selector);
	}
	return cssBorderCSS2.getTop().style;
    }

    public final CssBorderTopStyleCSS1 getBorderTopStyleCSS1() {
	if (cssBorderCSS1.getTop().getStyle() == null) {
	    cssBorderCSS1.getTop().style =
		(CssBorderTopStyleCSS1) style.CascadingOrder(new CssBorderTopStyleCSS1(),
			style, selector);
	}
	return cssBorderCSS1.getTop().style;
    }

    /**
     * Get the border-top-color property
     */
    public final CssBorderTopColorCSS2 getBorderTopColorCSS2() {
	if (cssBorderCSS2.getTop().getColor() == null) {
	    cssBorderCSS2.getTop().color =
		(CssBorderTopColorCSS2) style.CascadingOrder(new CssBorderTopColorCSS2(),
			style, selector);
	}
	return cssBorderCSS2.getTop().color;
    }

    public final CssBorderTopColor getBorderTopColor() {
	if (cssBorder.getTop().getColor() == null) {
	    cssBorder.getTop().color =
		(CssBorderTopColor) style.CascadingOrder(new CssBorderTopColor(),
			style, selector);
	}
	return cssBorder.getTop().color;
    }

    public final CssBorderTopColorCSS1 getBorderTopColorCSS1() {
	if (cssBorderCSS1.getTop().getColor() == null) {
	    cssBorderCSS1.getTop().color =
		(CssBorderTopColorCSS1) style.CascadingOrder(new CssBorderTopColorCSS1(),
			style, selector);
	}
	return cssBorderCSS1.getTop().color;
    }

    /**
     * Get the border-right-width property
     */
    public final CssBorderRightWidth getBorderRightWidth() {
	if (cssBorder.getRight().getWidth() == null) {
	    cssBorder.getRight().width =
		(CssBorderRightWidth) style.CascadingOrder(new CssBorderRightWidth(),
			style, selector);
	}
	return cssBorder.getRight().width;
    }

    public final CssBorderRightWidthCSS2 getBorderRightWidthCSS2() {
	if (cssBorderCSS2.getRight().getWidth() == null) {
	    cssBorderCSS2.getRight().width =
		(CssBorderRightWidthCSS2) style.CascadingOrder(new CssBorderRightWidthCSS2(),
			style, selector);
	}
	return cssBorderCSS2.getRight().width;
    }

    public final CssBorderRightWidthCSS1 getBorderRightWidthCSS1() {
	if (cssBorderCSS1.getRight().getWidth() == null) {
	    cssBorderCSS1.getRight().width =
		(CssBorderRightWidthCSS1) style.CascadingOrder(new CssBorderRightWidthCSS1(),
			style, selector);
	}
	return cssBorderCSS1.getRight().width;
    }

    /**
     * Get the border-right-style property
     */
    public final CssBorderRightStyle getBorderRightStyle() {
	if (cssBorder.getRight().getStyle() == null) {
	    cssBorder.getRight().style =
		(CssBorderRightStyle) style.CascadingOrder(new CssBorderRightStyle(),
			style, selector);
	}
	return cssBorder.getRight().style;
    }

    public final CssBorderRightStyleCSS2 getBorderRightStyleCSS2() {
	if (cssBorderCSS2.getRight().getStyle() == null) {
	    cssBorderCSS2.getRight().style =
		(CssBorderRightStyleCSS2) style.CascadingOrder(new CssBorderRightStyleCSS2(),
			style, selector);
	}
	return cssBorderCSS2.getRight().style;
    }

    public final CssBorderRightStyleCSS1 getBorderRightStyleCSS1() {
	if (cssBorderCSS1.getRight().getStyle() == null) {
	    cssBorderCSS1.getRight().style =
		(CssBorderRightStyleCSS1) style.CascadingOrder(new CssBorderRightStyleCSS1(),
			style, selector);
	}
	return cssBorderCSS1.getRight().style;
    }

    /**
     * Get the border-right-color property
     */
    public final CssBorderRightColor getBorderRightColor() {
	if (cssBorder.getRight().getColor() == null) {
	    cssBorder.getRight().color =
		(CssBorderRightColor) style.CascadingOrder(new CssBorderRightColor(),
			style, selector);
	}
	return cssBorder.getRight().color;
    }

    public final CssBorderRightColorCSS2 getBorderRightColorCSS2() {
	if (cssBorderCSS2.getRight().getColor() == null) {
	    cssBorderCSS2.getRight().color =
		(CssBorderRightColorCSS2) style.CascadingOrder(new CssBorderRightColorCSS2(),
			style, selector);
	}
	return cssBorderCSS2.getRight().color;
    }

    public final CssBorderRightColorCSS1 getBorderRightColorCSS1() {
	if (cssBorderCSS1.getRight().getColor() == null) {
	    cssBorderCSS1.getRight().color =
		(CssBorderRightColorCSS1) style.CascadingOrder(new CssBorderRightColorCSS1(),
			style, selector);
	}
	return cssBorderCSS1.getRight().color;
    }

    /**
     * Get the border-bottom-width property
     */
    public final CssBorderBottomWidth getBorderBottomWidth() {
	if (cssBorder.getBottom().getWidth() == null) {
	    cssBorder.getBottom().width =
		(CssBorderBottomWidth) style.CascadingOrder(new CssBorderBottomWidth(),
			style, selector);
	}
	return cssBorder.getBottom().width;
    }

    public final CssBorderBottomWidthCSS2 getBorderBottomWidthCSS2() {
	if (cssBorderCSS2.getBottom().getWidth() == null) {
	    cssBorderCSS2.getBottom().width =
		(CssBorderBottomWidthCSS2) style.CascadingOrder(new CssBorderBottomWidthCSS2(),
			style, selector);
	}
	return cssBorderCSS2.getBottom().width;
    }

    public final CssBorderBottomWidthCSS1 getBorderBottomWidthCSS1() {
	if (cssBorderCSS1.getBottom().getWidth() == null) {
	    cssBorderCSS1.getBottom().width =
		(CssBorderBottomWidthCSS1) style.CascadingOrder(new CssBorderBottomWidthCSS1(),
			style, selector);
	}
	return cssBorderCSS1.getBottom().width;
    }

    /**
     * Get the border-bottom-style property
     */
    public final CssBorderBottomStyle getBorderBottomStyle() {
	if (cssBorder.getBottom().getStyle() == null) {
	    cssBorder.getBottom().style =
		(CssBorderBottomStyle) style.CascadingOrder(new CssBorderBottomStyle(),
			style, selector);
	}
	return cssBorder.getBottom().style;
    }

    public final CssBorderBottomStyleCSS2 getBorderBottomStyleCSS2() {
	if (cssBorderCSS2.getBottom().getStyle() == null) {
	    cssBorderCSS2.getBottom().style =
		(CssBorderBottomStyleCSS2) style.CascadingOrder(new CssBorderBottomStyleCSS2(),
			style, selector);
	}
	return cssBorderCSS2.getBottom().style;
    }

    public final CssBorderBottomStyleCSS1 getBorderBottomStyleCSS1() {
	if (cssBorderCSS1.getBottom().getStyle() == null) {
	    cssBorderCSS1.getBottom().style =
		(CssBorderBottomStyleCSS1) style.CascadingOrder(new CssBorderBottomStyleCSS1(),
			style, selector);
	}
	return cssBorderCSS1.getBottom().style;
    }

    /**
     * Get the border-bottom-color property
     */
    public final CssBorderBottomColor getBorderBottomColor() {
	if (cssBorder.getBottom().getColor() == null) {
	    cssBorder.getBottom().color =
		(CssBorderBottomColor) style.CascadingOrder(new CssBorderBottomColor(),
			style, selector);
	}
	return cssBorder.getBottom().color;
    }

    public final CssBorderBottomColorCSS2 getBorderBottomColorCSS2() {
	if (cssBorderCSS2.getBottom().getColor() == null) {
	    cssBorderCSS2.getBottom().color =
		(CssBorderBottomColorCSS2) style.CascadingOrder(new CssBorderBottomColorCSS2(),
			style, selector);
	}
	return cssBorderCSS2.getBottom().color;
    }

    public final CssBorderBottomColorCSS1 getBorderBottomColorCSS1() {
	if (cssBorderCSS1.getBottom().getColor() == null) {
	    cssBorderCSS1.getBottom().color =
		(CssBorderBottomColorCSS1) style.CascadingOrder(new CssBorderBottomColorCSS1(),
			style, selector);
	}
	return cssBorderCSS1.getBottom().color;
    }

    /**
     * Get the border-left-width property
     */
    public final CssBorderLeftWidth getBorderLeftWidth() {
	if (cssBorder.getLeft().getWidth() == null) {
	    cssBorder.getLeft().width =
		(CssBorderLeftWidth) style.CascadingOrder(new CssBorderLeftWidth(),
			style, selector);
	}
	return cssBorder.getLeft().width;
    }

    public final CssBorderLeftWidthCSS2 getBorderLeftWidthCSS2() {
	if (cssBorderCSS2.getLeft().getWidth() == null) {
	    cssBorderCSS2.getLeft().width =
		(CssBorderLeftWidthCSS2) style.CascadingOrder(new CssBorderLeftWidthCSS2(),
			style, selector);
	}
	return cssBorderCSS2.getLeft().width;
    }

    public final CssBorderLeftWidthCSS1 getBorderLeftWidthCSS1() {
	if (cssBorderCSS1.getLeft().getWidth() == null) {
	    cssBorderCSS1.getLeft().width =
		(CssBorderLeftWidthCSS1) style.CascadingOrder(new CssBorderLeftWidthCSS1(),
			style, selector);
	}
	return cssBorderCSS1.getLeft().width;
    }

    /**
     * Get the border-left-style property
     */
    public final CssBorderLeftStyle getBorderLeftStyle() {
	if (cssBorder.getLeft().getStyle() == null) {
	    cssBorder.getLeft().style =
		(CssBorderLeftStyle) style.CascadingOrder(new CssBorderLeftStyle(),
			style, selector);
	}
	return cssBorder.getLeft().style;
    }

    public final CssBorderLeftStyleCSS2 getBorderLeftStyleCSS2() {
	if (cssBorderCSS2.getLeft().getStyle() == null) {
	    cssBorderCSS2.getLeft().style =
		(CssBorderLeftStyleCSS2) style.CascadingOrder(new CssBorderLeftStyleCSS2(),
			style, selector);
	}
	return cssBorderCSS2.getLeft().style;
    }

    public final CssBorderLeftStyleCSS1 getBorderLeftStyleCSS1() {
	if (cssBorderCSS1.getLeft().getStyle() == null) {
	    cssBorderCSS1.getLeft().style =
		(CssBorderLeftStyleCSS1) style.CascadingOrder(new CssBorderLeftStyleCSS1(),
			style, selector);
	}
	return cssBorderCSS1.getLeft().style;
    }

    /**
     * Get the border-left-color property
     */
    public final CssBorderLeftColor getBorderLeftColor() {
	if (cssBorder.getLeft().getColor() == null) {
	    cssBorder.getLeft().color =
		(CssBorderLeftColor) style.CascadingOrder(new CssBorderLeftColor(),
			style, selector);
	}
	return cssBorder.getLeft().color;
    }

    public final CssBorderLeftColorCSS2 getBorderLeftColorCSS2() {
	if (cssBorderCSS2.getLeft().getColor() == null) {
	    cssBorderCSS2.getLeft().color =
		(CssBorderLeftColorCSS2) style.CascadingOrder(new CssBorderLeftColorCSS2(),
			style, selector);
	}
	return cssBorderCSS2.getLeft().color;
    }

    public final CssBorderLeftColorCSS1 getBorderLeftColorCSS1() {
	if (cssBorderCSS1.getLeft().getColor() == null) {
	    cssBorderCSS1.getLeft().color =
		(CssBorderLeftColorCSS1) style.CascadingOrder(new CssBorderLeftColorCSS1(),
			style, selector);
	}
	return cssBorderCSS1.getLeft().color;
    }

    /**
     * Get the border-top property
     */
    public final CssBorderTop getBorderTop() {
	if (cssBorder.getTop().getWidth() == null) {
	    cssBorder.getTop().width = getBorderTopWidth();
	}
	if (cssBorder.getTop().getStyle() == null) {
	    cssBorder.getTop().style = getBorderTopStyle();
	}
	if (cssBorder.getTop().getColor() == null) {
	    cssBorder.getTop().color = getBorderTopColor();
	}
	return cssBorder.getTop();
    }

    public final CssBorderTopCSS2 getBorderTopCSS2() {
	if (cssBorderCSS2.getTop().getWidth() == null) {
	    cssBorderCSS2.getTop().width = getBorderTopWidthCSS2();
	}
	if (cssBorderCSS2.getTop().getStyle() == null) {
	    cssBorderCSS2.getTop().style = getBorderTopStyleCSS2();
	}
	if (cssBorderCSS2.getTop().getColor() == null) {
	    cssBorderCSS2.getTop().color = getBorderTopColorCSS2();
	}
	return cssBorderCSS2.getTop();
    }

    public final CssBorderTopCSS1 getBorderTopCSS1() {
	if (cssBorderCSS1.getTop().getWidth() == null) {
	    cssBorderCSS1.getTop().width = getBorderTopWidthCSS1();
	}
	if (cssBorderCSS1.getTop().getStyle() == null) {
	    cssBorderCSS1.getTop().style = getBorderTopStyleCSS1();
	}
	if (cssBorderCSS1.getTop().getColor() == null) {
	    cssBorderCSS1.getTop().color = getBorderTopColorCSS1();
	}
	return cssBorderCSS1.getTop();
    }

    /**
     * Get the border-right property
     */
    public final CssBorderRight getBorderRight() {
	if (cssBorder.getRight().getWidth() == null) {
	    cssBorder.getRight().width = getBorderRightWidth();
	}
	if (cssBorder.getRight().getStyle() == null) {
	    cssBorder.getRight().style = getBorderRightStyle();
	}
	if (cssBorder.getRight().getColor() == null) {
	    cssBorder.getRight().color = getBorderRightColor();
	}
	return cssBorder.getRight();
    }

    public final CssBorderRightCSS2 getBorderRightCSS2() {
	if (cssBorderCSS2.getRight().getWidth() == null) {
	    cssBorderCSS2.getRight().width = getBorderRightWidthCSS2();
	}
	if (cssBorderCSS2.getRight().getStyle() == null) {
	    cssBorderCSS2.getRight().style = getBorderRightStyleCSS2();
	}
	if (cssBorderCSS2.getRight().getColor() == null) {
	    cssBorderCSS2.getRight().color = getBorderRightColorCSS2();
	}
	return cssBorderCSS2.getRight();
    }

    public final CssBorderRightCSS1 getBorderRightCSS1() {
	if (cssBorderCSS1.getRight().getWidth() == null) {
	    cssBorderCSS1.getRight().width = getBorderRightWidthCSS1();
	}
	if (cssBorderCSS1.getRight().getStyle() == null) {
	    cssBorderCSS1.getRight().style = getBorderRightStyleCSS1();
	}
	if (cssBorderCSS1.getRight().getColor() == null) {
	    cssBorderCSS1.getRight().color = getBorderRightColorCSS1();
	}
	return cssBorderCSS1.getRight();
    }

    /**
     * Get the border-bottom property
     */
    public final CssBorderBottom getBorderBottom() {
	if (cssBorder.getBottom().getWidth() == null) {
	    cssBorder.getBottom().width = getBorderBottomWidth();
	}
	if (cssBorder.getBottom().getStyle() == null) {
	    cssBorder.getBottom().style = getBorderBottomStyle();
	}
	if (cssBorder.getBottom().getColor() == null) {
	    cssBorder.getBottom().color = getBorderBottomColor();
	}
	return cssBorder.getBottom();
    }

    public final CssBorderBottomCSS2 getBorderBottomCSS2() {
	if (cssBorderCSS2.getBottom().getWidth() == null) {
	    cssBorderCSS2.getBottom().width = getBorderBottomWidthCSS2();
	}
	if (cssBorderCSS2.getBottom().getStyle() == null) {
	    cssBorderCSS2.getBottom().style = getBorderBottomStyleCSS2();
	}
	if (cssBorderCSS2.getBottom().getColor() == null) {
	    cssBorderCSS2.getBottom().color = getBorderBottomColorCSS2();
	}
	return cssBorderCSS2.getBottom();
    }

    public final CssBorderBottomCSS1 getBorderBottomCSS1() {
	if (cssBorderCSS1.getBottom().getWidth() == null) {
	    cssBorderCSS1.getBottom().width = getBorderBottomWidthCSS1();
	}
	if (cssBorderCSS1.getBottom().getStyle() == null) {
	    cssBorderCSS1.getBottom().style = getBorderBottomStyleCSS1();
	}
	if (cssBorderCSS1.getBottom().getColor() == null) {
	    cssBorderCSS1.getBottom().color = getBorderBottomColorCSS1();
	}
	return cssBorderCSS1.getBottom();
    }

    /**
     * Get the border-left property
     */
    public final CssBorderLeft getBorderLeft() {
	if (cssBorder.getLeft().getWidth() == null) {
	    cssBorder.getLeft().width = getBorderLeftWidth();
	}
	if (cssBorder.getLeft().getStyle() == null) {
	    cssBorder.getLeft().style = getBorderLeftStyle();
	}
	if (cssBorder.getLeft().getColor() == null) {
	    cssBorder.getLeft().color = getBorderLeftColor();
	}
	return cssBorder.getLeft();
    }

    public final CssBorderLeftCSS2 getBorderLeftCSS2() {
	if (cssBorderCSS2.getLeft().getWidth() == null) {
	    cssBorderCSS2.getLeft().width = getBorderLeftWidthCSS2();
	}
	if (cssBorderCSS2.getLeft().getStyle() == null) {
	    cssBorderCSS2.getLeft().style = getBorderLeftStyleCSS2();
	}
	if (cssBorderCSS2.getLeft().getColor() == null) {
	    cssBorderCSS2.getLeft().color = getBorderLeftColorCSS2();
	}
	return cssBorderCSS2.getLeft();
    }

    public final CssBorderLeftCSS1 getBorderLeftCSS1() {
	if (cssBorderCSS1.getLeft().getWidth() == null) {
	    cssBorderCSS1.getLeft().width = getBorderLeftWidthCSS1();
	}
	if (cssBorderCSS1.getLeft().getStyle() == null) {
	    cssBorderCSS1.getLeft().style = getBorderLeftStyleCSS1();
	}
	if (cssBorderCSS1.getLeft().getColor() == null) {
	    cssBorderCSS1.getLeft().color = getBorderLeftColorCSS1();
	}
	return cssBorderCSS1.getLeft();
    }

    /**
     * Get the border property
     */
    public final CssBorder getBorder() {
	getBorderTop();
	getBorderRight();
	getBorderBottom();
	getBorderLeft();
	return cssBorder;
    }

    public final CssBorderCSS2 getBorderCSS2() {
	getBorderTopCSS2();
	getBorderRightCSS2();
	getBorderBottomCSS2();
	getBorderLeftCSS2();
	return cssBorderCSS2;
    }

    public final CssBorderCSS1 getBorderCSS1() {
	getBorderTopCSS1();
	getBorderRightCSS1();
	getBorderBottomCSS1();
	getBorderLeftCSS1();
	return cssBorderCSS1;
    }

    /**
     * Get the border-width property
     */
    public final CssBorderWidth getBorderWidth() {
	// WARNING invalid fields in this property ....
	return new CssBorderWidth(getBorderTopWidth(),
		getBorderBottomWidth(),
		getBorderRightWidth(),
		getBorderLeftWidth());
    }

    public final CssBorderWidthCSS2 getBorderWidthCSS2() {
	// WARNING invalid fields in this property ....
	return new CssBorderWidthCSS2(getBorderTopWidthCSS2(),
		getBorderBottomWidthCSS2(),
		getBorderRightWidthCSS2(),
		getBorderLeftWidthCSS2());
    }

    public final CssBorderWidthCSS1 getBorderWidthCSS1() {
	// WARNING invalid fields in this property ....
	return new CssBorderWidthCSS1(getBorderTopWidthCSS1(),
		getBorderBottomWidthCSS1(),
		getBorderRightWidthCSS1(),
		getBorderLeftWidthCSS1());
    }

    /**
     * Get the border-style property
     */
    public final CssBorderStyle getBorderStyle() {
	// WARNING invalid fields in this property ....
	return new CssBorderStyle(getBorderTopStyle(),
		getBorderBottomStyle(),
		getBorderRightStyle(),
		getBorderLeftStyle());
    }

    public final CssBorderStyleCSS2 getBorderStyleCSS2() {
	// WARNING invalid fields in this property ....
	return new CssBorderStyleCSS2(getBorderTopStyleCSS2(),
		getBorderBottomStyleCSS2(),
		getBorderRightStyleCSS2(),
		getBorderLeftStyleCSS2());
    }

    public final CssBorderStyleCSS1 getBorderStyleCSS1() {
	// WARNING invalid fields in this property ....
	return new CssBorderStyleCSS1(getBorderTopStyleCSS1(),
		getBorderBottomStyleCSS1(),
		getBorderRightStyleCSS1(),
		getBorderLeftStyleCSS1());
    }

    /**
     * Get the border-color property
     */
    public final CssBorderColor getBorderColor() {
	// WARNING invalid fields in this porperty ....
	return new CssBorderColor(getBorderTopColor(),
		getBorderBottomColor(),
		getBorderRightColor(),
		getBorderLeftColor());
    }

    public final CssBorderColorCSS2 getBorderColorCSS2() {
	// WARNING invalid fields in this porperty ....
	return new CssBorderColorCSS2(getBorderTopColorCSS2(),
		getBorderBottomColorCSS2(),
		getBorderRightColorCSS2(),
		getBorderLeftColorCSS2());
    }

    public final CssBorderColorCSS1 getBorderColorCSS1() {
	// WARNING invalid fields in this porperty ....
	return new CssBorderColorCSS1(getBorderTopColorCSS1(),
		getBorderBottomColorCSS1(),
		getBorderRightColorCSS1(),
		getBorderLeftColorCSS1());
    }

    /**
     * Get the width property
     */
    public final CssWidth getWidth() {
	if (cssWidth == null) {
	    cssWidth =
		(CssWidth) style.CascadingOrder(new CssWidth(), style, selector);
	}
	return cssWidth;
    }

    public final CssWidthMob getWidthMob() {
	if (cssWidthMob == null) {
	    cssWidthMob =
		(CssWidthMob) style.CascadingOrder(new CssWidthMob(), style, selector);
	}
	return cssWidthMob;
    }

    /**
     * Get the min-width property
     */
    public final CssMinWidth getMinWidth() {
	if (cssMinWidth == null) {
	    cssMinWidth =
		(CssMinWidth) style.CascadingOrder(new CssMinWidth(), style, selector);
	}
	return cssMinWidth;
    }

    public final CssMinWidthATSC getMinWidthATSC() {
	if (cssMinWidthATSC == null) {
	    cssMinWidthATSC =
		(CssMinWidthATSC) style.CascadingOrder(new CssMinWidthATSC(), style, selector);
	}
	return cssMinWidthATSC;
    }

    /**
     * Get the max-width property
     */
    public final CssMaxWidth getMaxWidth() {
	if (cssMaxWidth == null) {
	    cssMaxWidth =
		(CssMaxWidth) style.CascadingOrder(new CssMaxWidth(), style, selector);
	}
	return cssMaxWidth;
    }

    public final CssMaxWidthATSC getMaxWidthATSC() {
	if (cssMaxWidthATSC == null) {
	    cssMaxWidthATSC =
		(CssMaxWidthATSC) style.CascadingOrder(new CssMaxWidthATSC(), style, selector);
	}
	return cssMaxWidthATSC;
    }

    /**
     * Get the min-height property
     */
    public final CssMinHeight getMinHeight() {
	if (cssMinHeight == null) {
	    cssMinHeight =
		(CssMinHeight) style.CascadingOrder(new CssMinHeight(), style, selector);
	}
	return cssMinHeight;
    }

    public final CssMinHeightATSC getMinHeightATSC() {
	if (cssMinHeightATSC == null) {
	    cssMinHeightATSC =
		(CssMinHeightATSC) style.CascadingOrder(new CssMinHeightATSC(), style, selector);
	}
	return cssMinHeightATSC;
    }

    /**
     * Get the max-height property
     */
    public final CssMaxHeight getMaxHeight() {
	if (cssMaxHeight == null) {
	    cssMaxHeight =
		(CssMaxHeight) style.CascadingOrder(new CssMaxHeight(), style, selector);
	}
	return cssMaxHeight;
    }

    public final CssMaxHeightATSC getMaxHeightATSC() {
	if (cssMaxHeightATSC == null) {
	    cssMaxHeightATSC =
		(CssMaxHeightATSC) style.CascadingOrder(new CssMaxHeightATSC(), style, selector);
	}
	return cssMaxHeightATSC;
    }

    /**
     * Get the height property
     */
    public final CssHeight getHeight() {
	if (cssHeight == null) {
	    cssHeight =
		(CssHeight) style.CascadingOrder(new CssHeight(), style, selector);
	}
	return cssHeight;
    }

    public final CssHeightMob getHeightMob() {
	if (cssHeightMob == null) {
	    cssHeightMob =
		(CssHeightMob) style.CascadingOrder(new CssHeightMob(), style, selector);
	}
	return cssHeightMob;
    }

    /**
     * Get the float property
     */
    public final CssFloat getFloat() {
	if (cssFloat == null) {
	    cssFloat =
		(CssFloat) style.CascadingOrder(new CssFloat(), style, selector);
	}
	return cssFloat;
    }

    /**
     * Get the clear property
     */
    public final CssClear getClear() {
	if (cssClear == null) {
	    cssClear =
		(CssClear) style.CascadingOrder(new CssClear(), style, selector);
	}
	return cssClear;
    }

    /**
     * Get the top property
     */
    public final CssTop getTop() {
	if (cssTop == null) {
	    cssTop =
		(CssTop) style.CascadingOrder(new CssTop(), style, selector);
	}
	return cssTop;
    }

    /**
     * Get the left property
     */
    public final CssLeft getLeft() {
	if (cssLeft == null) {
	    cssLeft =
		(CssLeft) style.CascadingOrder(new CssLeft(), style, selector);
	}
	return cssLeft;
    }

    /**
     * Get the right property
     */
    public final CssRight getRight() {
	if (cssRight == null) {
	    cssRight =
		(CssRight) style.CascadingOrder(new CssRight(), style, selector);
	}
	return cssRight;
    }

    /**
     * Get the bottom property
     */
    public final CssBottom getBottom() {
	if (cssBottom == null) {
	    cssBottom =
		(CssBottom) style.CascadingOrder(new CssBottom(), style, selector);
	}
	return cssBottom;
    }

    /*
     * Classification properties
     */

    /**
     * Get the display property
     */
    public final CssDisplay getDisplay() {
	if (cssDisplay == null) {
	    cssDisplay =
		(CssDisplay) style.CascadingOrder(new CssDisplay(), style, selector);
	}
	return cssDisplay;
    }

    public final CssDisplayCSS2 getDisplayCSS2() {
	if (cssDisplayCSS2 == null) {
	    cssDisplayCSS2 =
		(CssDisplayCSS2) style.CascadingOrder(new CssDisplayCSS2(), style, selector);
	}
	return cssDisplayCSS2;
    }

    public final CssDisplayCSS1 getDisplayCSS1() {
	if (cssDisplayCSS1 == null) {
	    cssDisplayCSS1 =
		(CssDisplayCSS1) style.CascadingOrder(new CssDisplayCSS1(), style, selector);
	}
	return cssDisplayCSS1;
    }


    /**
     * Get the position property
     */
    public final CssPosition getPosition() {
	if (cssPosition == null) {
	    cssPosition =
		(CssPosition) style.CascadingOrder(new CssPosition(), style, selector);
	}
	return cssPosition;
    }

    /**
     * Get the z-index property
     */
    public final CssZIndex getZIndex() {
	if (cssZIndex == null) {
	    cssZIndex =
		(CssZIndex) style.CascadingOrder(new CssZIndex(),
			style, selector);
	}
	return cssZIndex;
    }

    /**
     * Get the direction property
     */
    public final CssDirection getDirection() {
	if (cssDirection == null) {
	    cssDirection =
		(CssDirection) style.CascadingOrder(new CssDirection(),
			style, selector);
	}
	return cssDirection;
    }

    public final CssDirectionATSC getDirectionATSC() {
	if (cssDirectionATSC == null) {
	    cssDirectionATSC =
		(CssDirectionATSC) style.CascadingOrder(new CssDirectionATSC(),
			style, selector);
	}
	return cssDirectionATSC;
    }

    /**
     * Get the overflow property
     */
    public final CssOverflow getOverflow() {
	if (cssOverflow == null) {
	    cssOverflow =
		(CssOverflow) style.CascadingOrder(new CssOverflow(),
			style, selector);
	}
	return cssOverflow;
    }

    /**
     * Get the clip property
     */
    public final CssClip getClip() {
	if (cssClip == null) {
	    cssClip =
		(CssClip) style.CascadingOrder(new CssClip(),
			style, selector);
	}
	return cssClip;
    }

    /**
     * Get the visibility property
     */
    public final CssVisibility getVisibility() {
	if (cssVisibility == null) {
	    cssVisibility =
		(CssVisibility) style.CascadingOrder(new CssVisibility(),
			style, selector);
	}
	return cssVisibility;
    }

    /**
     * Get the unicode-bidi property
     */
    public final CssUnicodeBidi getUnicodeBidi() {
	if (cssUnicodeBidi == null) {
	    cssUnicodeBidi =
		(CssUnicodeBidi) style.CascadingOrder(new CssUnicodeBidi(),
			style, selector);
	}
	return cssUnicodeBidi;
    }

    public final CssUnicodeBidiATSC getUnicodeBidiATSC() {
	if (cssUnicodeBidiATSC == null) {
	    cssUnicodeBidiATSC =
		(CssUnicodeBidiATSC) style.CascadingOrder(new CssUnicodeBidiATSC(),
			style, selector);
	}
	return cssUnicodeBidiATSC;
    }

    /**
     * Get the white-space property
     */
    public final CssWhiteSpace getWhiteSpace() {
	if (cssWhiteSpace == null) {
	    cssWhiteSpace =
		(CssWhiteSpace) style.CascadingOrder(new CssWhiteSpace(),
			style, selector);
	}
	return cssWhiteSpace;
    }

    /**
     * Get the list-style-type property
     */
    public final CssListStyleType getListStyleType() {
	if (cssListStyle.listStyleType == null) {
	    cssListStyle.listStyleType =
		(CssListStyleType) style.CascadingOrder(new CssListStyleType(),
			style, selector);
	}
	return cssListStyle.listStyleType;
    }

    public final CssListStyleTypeCSS2 getListStyleTypeCSS2() {
	if (cssListStyleCSS2.listStyleType == null) {
	    cssListStyleCSS2.listStyleType =
		(CssListStyleTypeCSS2) style.CascadingOrder(new CssListStyleTypeCSS2(),
			style, selector);
	}
	return cssListStyleCSS2.listStyleType;
    }

    public final CssListStyleTypeCSS1 getListStyleTypeCSS1() {
	if (cssListStyleCSS1.listStyleType == null) {
	    cssListStyleCSS1.listStyleType =
		(CssListStyleTypeCSS1) style.CascadingOrder(new CssListStyleTypeCSS1(),
			style, selector);
	}
	return cssListStyleCSS1.listStyleType;
    }

    /**
     * Get the list-style-image property
     */
    public final CssListStyleImage getListStyleImage() {
	if (cssListStyle.listStyleImage == null) {
	    cssListStyle.listStyleImage =
		(CssListStyleImage) style.CascadingOrder(new CssListStyleImage(),
			style, selector);
	}
	return cssListStyle.listStyleImage;
    }

    public final CssListStyleImageCSS2 getListStyleImageCSS2() {
	if (cssListStyleCSS2.listStyleImage == null) {
	    cssListStyleCSS2.listStyleImage =
		(CssListStyleImageCSS2) style.CascadingOrder(new CssListStyleImageCSS2(),
			style, selector);
	}
	return cssListStyleCSS2.listStyleImage;
    }

    public final CssListStyleImageCSS1 getListStyleImageCSS1() {
	if (cssListStyleCSS1.listStyleImage == null) {
	    cssListStyleCSS1.listStyleImage =
		(CssListStyleImageCSS1) style.CascadingOrder(new CssListStyleImageCSS1(),
			style, selector);
	}
	return cssListStyleCSS1.listStyleImage;
    }

    /**
     * Get the list-style-position property
     */
    public final CssListStylePosition getListStylePosition() {
	if (cssListStyle.listStylePosition == null) {
	    cssListStyle.listStylePosition =
		(CssListStylePosition)
		style.CascadingOrder(new CssListStylePosition(),
			style, selector);
	}
	return cssListStyle.listStylePosition;
    }

    public final CssListStylePositionCSS2 getListStylePositionCSS2() {
	if (cssListStyleCSS2.listStylePosition == null) {
	    cssListStyleCSS2.listStylePosition =
		(CssListStylePositionCSS2)
		style.CascadingOrder(new CssListStylePositionCSS2(),
			style, selector);
	}
	return cssListStyleCSS2.listStylePosition;
    }

    public final CssListStylePositionCSS1 getListStylePositionCSS1() {
	if (cssListStyleCSS1.listStylePosition == null) {
	    cssListStyleCSS1.listStylePosition =
		(CssListStylePositionCSS1)
		style.CascadingOrder(new CssListStylePositionCSS1(),
			style, selector);
	}
	return cssListStyleCSS1.listStylePosition;
    }

    /**
     * Get the list-style property
     */
    public final CssListStyle getListStyle() {
	if (cssListStyle.listStyleType == null)
	    cssListStyle.listStyleType = getListStyleType();
	if (cssListStyle.listStyleImage == null)
	    cssListStyle.listStyleImage = getListStyleImage();
	if (cssListStyle.listStylePosition == null)
	    cssListStyle.listStylePosition = getListStylePosition();
	return cssListStyle;
    }

    public final CssListStyleCSS2 getListStyleCSS2() {
	if (cssListStyleCSS2.listStyleType == null)
	    cssListStyleCSS2.listStyleType = getListStyleTypeCSS2();
	if (cssListStyleCSS2.listStyleImage == null)
	    cssListStyleCSS2.listStyleImage = getListStyleImageCSS2();
	if (cssListStyleCSS2.listStylePosition == null)
	    cssListStyleCSS2.listStylePosition = getListStylePositionCSS2();
	return cssListStyleCSS2;
    }

    public final CssListStyleCSS1 getListStyleCSS1() {
	if (cssListStyleCSS1.listStyleType == null)
	    cssListStyleCSS1.listStyleType = getListStyleTypeCSS1();
	if (cssListStyleCSS1.listStyleImage == null)
	    cssListStyleCSS1.listStyleImage = getListStyleImageCSS1();
	if (cssListStyleCSS1.listStylePosition == null)
	    cssListStyleCSS1.listStylePosition = getListStylePositionCSS1();
	return cssListStyleCSS1;
    }

    /**
     * Get the content property
     */
    public final CssContent getContent() {
	if (cssContent == null) {
	    cssContent =
		(CssContent) style.CascadingOrder(new CssContent(),
			style, selector);
	}
	return cssContent;
    }

    public final CssContentCSS2 getContentCSS2() {
	if (cssContentCSS2 == null) {
	    cssContentCSS2 =
		(CssContentCSS2) style.CascadingOrder(new CssContentCSS2(),
			style, selector);
	}
	return cssContentCSS2;
    }

    /**
     * Get the quotes property
     */
    public final CssQuotes getQuotes() {
	if (cssQuotes == null) {
	    cssQuotes =
		(CssQuotes) style.CascadingOrder(new CssQuotes(),
			style, selector);
	}
	return cssQuotes;
    }

    public final CssQuotesATSC getQuotesATSC() {
	if (cssQuotesATSC == null) {
	    cssQuotesATSC =
		(CssQuotesATSC) style.CascadingOrder(new CssQuotesATSC(),
			style, selector);
	}
	return cssQuotesATSC;
    }

    /**
     * Get the counter-reset property
     */
    public final CssCounterReset getCounterReset() {
	if (cssCounterReset == null) {
	    cssCounterReset =
		(CssCounterReset) style.CascadingOrder(new CssCounterReset(),
			style, selector);
	}
	return cssCounterReset;
    }

    /**
     * Get the counter-increment property
     */
    public final CssCounterIncrement getCounterIncrement() {
	if (cssCounterIncrement == null) {
	    cssCounterIncrement =
		(CssCounterIncrement) style.CascadingOrder(new CssCounterIncrement(),
			style, selector);
	}
	return cssCounterIncrement;
    }

    /**
     * Get the marker-offset property
     */
    public final CssMarkerOffset getMarkerOffset() {
	if (cssMarkerOffset == null) {
	    cssMarkerOffset =
		(CssMarkerOffset) style.CascadingOrder(new CssMarkerOffset(),
			style, selector);
	}
	return cssMarkerOffset;
    }

    public final CssMarkerOffsetATSC getMarkerOffsetATSC() {
	if (cssMarkerOffsetATSC == null) {
	    cssMarkerOffsetATSC =
		(CssMarkerOffsetATSC) style.CascadingOrder(new CssMarkerOffsetATSC(),
			style, selector);
	}
	return cssMarkerOffsetATSC;
    }

    public final CssListStyleTypeTV getListStyleTypeTV() {
	if (cssListStyleTypeTV == null) {
	    cssListStyleTypeTV =
		(CssListStyleTypeTV) style.CascadingOrder(new CssListStyleTypeTV(),
			style, selector);
	}

	return cssListStyleTypeTV;
    }

    public final CssListStyleTV getListStyleTV() {
	if (cssListStyleTV == null) {
	    cssListStyleTV =
		(CssListStyleTV) style.CascadingOrder(new CssListStyleTV(),
			style, selector);
	}

	return cssListStyleTV;
    }

    public final CssPositionTV getPositionTV() {
	if (cssPositionTV == null) {
	    cssPositionTV =
		(CssPositionTV) style.CascadingOrder(new CssPositionTV(),
			style, selector);
	}

	return cssPositionTV;
    }

    public final CssTextAlignTV getTextAlignTV() {
	if (cssTextAlignTV == null) {
	    cssTextAlignTV =
		(CssTextAlignTV) style.CascadingOrder(new CssTextAlignTV(),
			style, selector);
	}

	return cssTextAlignTV;
    }

    public final CssTextDecorationTV getTextDecorationTV() {
	if (cssTextDecorationTV == null) {
	    cssTextDecorationTV =
		(CssTextDecorationTV) style.CascadingOrder(new CssTextDecorationTV(),
			style, selector);
	}

	return cssTextDecorationTV;
    }

    public final CssVerticalAlignTV getVerticalAlignTV() {
	if (cssVerticalAlignTV == null) {
	    cssVerticalAlignTV =
		(CssVerticalAlignTV) style.CascadingOrder(new CssVerticalAlignTV(),
			style, selector);
	}

	return cssVerticalAlignTV;
    }


    /**
     * Print this style.
     *
     * @param printer The printer interface.
     */
    public void print(CssPrinterStyle printer) {

	// Note : macro are never null
	cssFont.print(printer);
	if (cssFontStretch != null) {
	    cssFontStretch.print(printer);
	}
	if (cssFontStretchCSS2 != null) {
	    cssFontStretchCSS2.print(printer);
	}
	if (cssFontStretchCSS1 != null) {
	    cssFontStretchCSS1.print(printer);
	}
	if (cssFontSizeAdjust != null) {
	    cssFontSizeAdjust.print(printer);
	}
	if (cssColorCSS2 != null)
	    cssColorCSS2.print(printer);
	if (cssColorCSS1 != null)
	    cssColorCSS1.print(printer);
	if (cssFontSizeAdjustCSS2 != null) {
	    cssFontSizeAdjustCSS2.print(printer);
	}
	if (cssColor != null)
	    cssColor.print(printer);
	cssBackground.print(printer);
	if (cssBackgroundCSS2 != null) {
	    cssBackgroundCSS2.print(printer);
	}
	if (cssBackgroundCSS1 != null) {
	    cssBackgroundCSS1.print(printer);
	}
	if (cssBackgroundMob != null) {
	    cssBackgroundMob.print(printer);
	}
	if (cssWordSpacing != null)
	    cssWordSpacing.print(printer);
	if (cssLetterSpacing != null)
	    cssLetterSpacing.print(printer);
	if (cssTextDecoration != null)
	    cssTextDecoration.print(printer);
	if (cssTextDecorationMob != null) {
	    cssTextDecorationMob.print(printer);
	}
	if (cssVerticalAlign != null)
	    cssVerticalAlign.print(printer);
	if (cssVerticalAlignCSS1 != null) {
	    cssVerticalAlignCSS1.print(printer);
	}
	if (cssVerticalAlignMob != null) {
	    cssVerticalAlignMob.print(printer);
	}
	if (cssTextTransform != null)
	    cssTextTransform.print(printer);
	if (cssTextAlign != null)
	    cssTextAlign.print(printer);
	if (cssTextAlignMob != null) {
	    cssTextAlignMob.print(printer);
	}
	if (cssTextIndent != null)
	    cssTextIndent.print(printer);
	if (cssTextIndentMob != null) {
	    cssTextIndentMob.print(printer);
	}
	if (cssTextShadow != null)
	    cssTextShadow.print(printer);
	if (cssTextShadowATSC != null) {
	    cssTextShadowATSC.print(printer);
	}
	cssMargin.print(printer);
	cssPadding.print(printer);
	cssBorder.print(printer);
	if (cssWidth != null)
	    cssWidth.print(printer);
	if (cssWidthMob != null) {
	    cssWidth.print(printer);
	}
	if (cssMinWidth != null)
	    cssMinWidth.print(printer);
	if (cssMinWidthATSC != null) {
	    cssMinWidthATSC.print(printer);
	}
	if (cssMaxWidth != null)
	    cssMaxWidth.print(printer);
	if (cssMaxWidthATSC != null) {
	    cssMaxWidthATSC.print(printer);
	}
	if (cssMinHeight != null)
	    cssMinHeight.print(printer);
	if (cssMinHeightATSC != null) {
	    cssMinHeightATSC.print(printer);
	}
	if (cssMaxHeight != null)
	    cssMaxHeight.print(printer);
	if (cssMaxHeightATSC != null) {
	    cssMaxHeightATSC.print(printer);
	}
	if (cssHeight != null)
	    cssHeight.print(printer);
	if (cssHeightMob != null) {
	    cssHeightMob.print(printer);
	}
	if (cssFloat != null)
	    cssFloat.print(printer);
	if (cssClear != null)
	    cssClear.print(printer);
	if (cssTop != null)
	    cssTop.print(printer);
	if (cssLeft != null)
	    cssLeft.print(printer);
	if (cssRight != null)
	    cssRight.print(printer);
	if (cssBottom != null)
	    cssBottom.print(printer);
	if (cssDisplay != null)
	    cssDisplay.print(printer);
	if (cssDisplayCSS2 != null) {
	    cssDisplayCSS2.print(printer);
	}
	if (cssDisplayCSS1 != null) {
	    cssDisplayCSS1.print(printer);
	}
	if (cssPosition != null)
	    cssPosition.print(printer);
	if (cssZIndex != null)
	    cssZIndex.print(printer);
	if (cssDirection != null)
	    cssDirection.print(printer);
	if (cssDirectionATSC != null) {
	    cssDirectionATSC.print(printer);
	}
	if (cssUnicodeBidi != null)
	    cssUnicodeBidi.print(printer);
	if (cssUnicodeBidiATSC != null) {
	    cssUnicodeBidiATSC.print(printer);
	}
	if (cssWhiteSpace != null)
	    cssWhiteSpace.print(printer);
	cssListStyle.print(printer);
	if (cssListStyleCSS2 != null) {
	    cssListStyleCSS2.print(printer);
	}
	if (cssListStyleCSS1 != null) {
	    cssListStyleCSS1.print(printer);
	}
	if (cssOverflow != null)
	    cssOverflow.print(printer);
	if (cssClip != null)
	    cssClip.print(printer);
	if (cssVisibility != null)
	    cssVisibility.print(printer);
	if (cssContent != null)
	    cssContent.print(printer);
	if (cssContentCSS2 != null) {
	    cssContentCSS2.print(printer);
	}
	if (cssQuotes != null)
	    cssQuotes.print(printer);
	if (cssQuotesATSC != null) {
	    cssQuotesATSC.print(printer);
	}
	if (cssCounterReset != null)
	    cssCounterReset.print(printer);
	if (cssCounterIncrement != null)
	    cssCounterIncrement.print(printer);
	if (cssMarkerOffset != null)
	    cssMarkerOffset.print(printer);
	if (cssMarkerOffsetATSC != null) {
	    cssMarkerOffsetATSC.print(printer);
	}
	if (cssListStyleTV != null) {
	    cssListStyleTV.print(printer);
	}
	if (cssListStyleTypeTV != null) {
	    cssListStyleTypeTV.print(printer);
	}
	if (cssPositionTV != null) {
	    cssPositionTV.print(printer);
	}
	if (cssTextAlignTV != null) {
	    cssTextAlignTV.print(printer);
	}
	if (cssTextDecorationTV != null) {
	    cssTextDecorationTV.print(printer);
	}
	if (cssVerticalAlignTV != null) {
	    cssVerticalAlignTV.print(printer);
	}
    }



    /**
     * Find conflicts in this Style
     *
     * @param warnings For warnings reports.
     * @param allSelectors All contexts is the entire style sheet.
     */
    public void findConflicts(ApplContext ac, Warnings warnings, 
	    CssSelectors selector, CssSelectors[] allSelectors) {
	// if border-style == none, border-width should be 0
	// @@ this is a horrible place to do this ...
	cssBorder.check();

	if (cssFont.fontFamily != null) {
	    if (!cssFont.fontFamily.containsGenericFamily()) {
		warnings.addWarning(new Warning(cssFont.fontFamily,
			"no-generic-family", 2, ac));
	    }
	    if (cssFont.fontFamily.withSpace) {
		warnings.addWarning(new Warning(cssFont.fontFamily,
			"with-space", 1, ac));
	    }
	}
	else if (cssFontCSS1.fontFamily != null) {
	    if (!cssFontCSS1.fontFamily.containsGenericFamily()) {
		warnings.addWarning(new Warning(cssFontCSS1.fontFamily,
			"no-generic-family", 2, ac));
	    }
	    if (cssFontCSS1.fontFamily.withSpace) {
		warnings.addWarning(new Warning(cssFontCSS1.fontFamily,
			"with-space", 1, ac));
	    }
	}
	else if (cssFontCSS2.fontFamily != null) {
	    if (!cssFontCSS2.fontFamily.containsGenericFamily()) {
		warnings.addWarning(new Warning(cssFontCSS2.fontFamily,
			"no-generic-family", 2, ac));
	    }
	    if (cssFontCSS2.fontFamily.withSpace) {
		warnings.addWarning(new Warning(cssFontCSS2.fontFamily,
			"with-space", 1, ac));
	    }
	}

    if (cssFloat != null) {
        if(cssWidth == null ) {
            // TODO do NOT send warning if element in context
            // is html, img, input, textarea, select, or object
		    warnings.addWarning(new Warning(cssFloat, "float-no-width", 2, ac));
        }
    }

	if (cssBackground.getColor() != null) {
	    CssColor colorCSS3 = cssColor;
	    // we need to look if there is the same selector elsewhere
	    // containing a color definition
	    for (int i=0;(colorCSS3 == null) && (i<allSelectors.length); i++) {
		CssSelectors sel = allSelectors[i];	
		if(sel.toString().equals(selector.toString())) {
		    colorCSS3 = ((Css1Style) sel.getStyle()).cssColor;
		}
	    }
	    if (colorCSS3 != null) {
		if (cssBackground.getColor().equals(colorCSS3.getColor())) {
		    // background and color can't have the same color
		    warnings.addWarning(new Warning(cssBackground.color,
			    "same-colors", 1, colorCSS3, ac));
		}
		else if(cssBackground.getColor().equals(
			CssProperty.transparent)) {
//		  It's better to have a background color with a color
		    warnings.addWarning(new Warning(
			    colorCSS3, "no-background-color", 2, ac));
		}
	    } else {
		CssValue color = cssBackground.getColor();

		if (!color.equals(CssBackgroundColor.transparent)) {
		    // It's better to have a color when a background is defined.
		    warnings.addWarning(new Warning(cssBackground.color,
			    "no-color", 2, ac));
		}
	    }

	    // Note : For borders, I don't look for inherited value.
	    //        So I can't find same colors in two differents contexts.

	    if (cssBorder.getTop().getColor() != null) {
		CssValue color = cssBorder.getTop().getColor();
		if (color != CssProperty.inherit
			&& cssBackground.getColor().equals(color)) {
		    // background and border-color can't have the same color
		    warnings.addWarning(new Warning(cssBackground.color,
			    "same-colors", 1,
			    cssBorder.getTop().color, ac));
		}
	    }
	    if (cssBorder.getRight().getColor() != null) {
		CssValue color = cssBorder.getRight().getColor();
		if (color != CssProperty.inherit
			&& cssBackground.getColor().equals(color)) {
		    // background and border-color can't have the same color
		    warnings.addWarning(new Warning(cssBackground.color,
			    "same-colors", 1,
			    cssBorder.getRight().color, ac));
		}
	    }
	    if (cssBorder.getBottom().getColor() != null) {
		CssValue color = cssBorder.getBottom().getColor();
		if (color != CssProperty.inherit
			&& cssBackground.getColor().equals(color)) {
		    // background and border-color can't have the same color
		    warnings.addWarning(new Warning(cssBackground.color,
			    "same-colors", 1,
			    cssBorder.getBottom().color, ac));
		}
	    }
	    if (cssBorder.getLeft().getColor() != null) {
		CssValue color = cssBorder.getLeft().getColor();
		if (color != CssProperty.inherit
			&& cssBackground.getColor().equals(color)) {
		    // background and border-color can't have the same color
		    warnings.addWarning(new Warning(cssBackground.color,
			    "same-colors", 1,
			    cssBorder.getLeft().color, ac));
		}
	    }
	    /* suppressed 03-09-98
	     if ((cssPadding.top == null) ||
	     (cssPadding.right == null) ||
	     (cssPadding.bottom == null) ||
	     (cssPadding.left == null)) {
	     // It's better to have a padding with a background color.
	      warnings.addWarning(new Warning(cssBackground.color, "no-padding", 2));
	      }
	      */
	} else if (cssBackgroundCSS1.getColor() != null) {
	    CssColorCSS1 colorCSS1 = cssColorCSS1;
	    // we need to look if there is the same selector elsewhere
	    // containing a color definition
	    for (int i=0; (colorCSS1 == null) && i < allSelectors.length; i++) {
		CssSelectors sel = allSelectors[i];		
		if(sel.toString().equals(selector.toString())) {
		    colorCSS1 = ((Css1Style) sel.getStyle()).cssColorCSS1;
		}
	    }
	    if (colorCSS1 != null) {
		if (cssBackgroundCSS1.getColor().equals(colorCSS1.getColor())) {
		    // background and color can't have the same color
		    warnings.addWarning(new Warning(cssBackgroundCSS1.color,
			    "same-colors", 1, colorCSS1, ac));
		}
		else if(cssBackgroundCSS1.getColor().equals(
			CssProperty.transparent)) {
//		  It's better to have a background color with a color
		    warnings.addWarning(new Warning(
			    colorCSS1, "no-background-color", 2, ac));
		}
	    } else {
		CssValue color = cssBackgroundCSS1.getColor();

		if (!color.equals(CssBackgroundColorCSS1.transparent)) {
		    // It's better to have a color when a background is defined.
		    warnings.addWarning(new Warning(cssBackgroundCSS1.color,
			    "no-color", 2, ac));
		}
	    }

	    // Note : For borders, I don't look for inherited value.
	    //        So I can't find same colors in two differents contexts.

	    if (cssBorderCSS1.getTop().getColor() != null) {
		CssValue color = cssBorderCSS1.getTop().getColor();
		if (cssBackgroundCSS1.getColor().equals(color)) {
		    // background and border-color can't have the same color
		    warnings.addWarning(new Warning(cssBackgroundCSS1.color,
			    "same-colors", 1,
			    cssBorderCSS1.getTop().color, ac));
		}
	    }
	    if (cssBorderCSS1.getRight().getColor() != null) {
		CssValue color = cssBorderCSS1.getRight().getColor();
		if (cssBackgroundCSS1.getColor().equals(color)) {
		    // background and border-color can't have the same color
		    warnings.addWarning(new Warning(cssBackgroundCSS1.color,
			    "same-colors", 1,
			    cssBorderCSS1.getRight().color, ac));
		}
	    }
	    if (cssBorderCSS1.getBottom().getColor() != null) {
		CssValue color = cssBorderCSS1.getBottom().getColor();
		if (cssBackgroundCSS1.getColor().equals(color)) {
		    // background and border-color can't have the same color
		    warnings.addWarning(new Warning(cssBackgroundCSS1.color,
			    "same-colors", 1,
			    cssBorderCSS1.getBottom().color, ac));
		}
	    }
	    if (cssBorderCSS1.getLeft().getColor() != null) {
		CssValue color = cssBorderCSS1.getLeft().getColor();
		if (cssBackgroundCSS1.getColor().equals(color)) {
		    // background and border-color can't have the same color
		    warnings.addWarning(new Warning(cssBackgroundCSS1.color,
			    "same-colors", 1,
			    cssBorderCSS1.getLeft().color, ac));
		}
	    }
	}
	else if (cssBackgroundCSS2.getColor() != null) {
	    CssColorCSS2 colorCSS2 = cssColorCSS2;
	    // we need to look if there is the same selector elsewhere
	    // containing a color definition
	    for (int i=0; (colorCSS2 == null) && (i<allSelectors.length); i++) {
		CssSelectors sel = allSelectors[i];		
		if(sel.toString().equals(selector.toString())) {
		    colorCSS2 = ((Css1Style) sel.getStyle()).cssColorCSS2;
		}
	    }
	    if (colorCSS2 != null) {
		if (cssBackgroundCSS2.getColor().equals(colorCSS2.getColor())) {
		    // background and color can't have the same color
		    warnings.addWarning(new Warning(cssBackgroundCSS2.color,
			    "same-colors", 1, colorCSS2, ac));
		}
		else if(cssBackgroundCSS2.getColor().equals(
			CssProperty.transparent)) {
		    // It's better to have a background color with a color
		    warnings.addWarning(new Warning(
			    colorCSS2, "no-background-color", 2, ac));
		}
	    } else {
		CssValue color = cssBackgroundCSS2.getColor();

		if (!color.equals(CssBackgroundColorCSS2.transparent)) {
		    // It's better to have a color when a background is defined.
		    warnings.addWarning(new Warning(cssBackgroundCSS2.color,
			    "no-color", 2, ac));
		}
	    }

	    // Note : For borders, I don't look for inherited value.
	    //        So I can't find same colors in two differents contexts.

	    if (cssBorderCSS2.getTop().getColor() != null) {
		CssValue color = cssBorderCSS2.getTop().getColor();
		if (color != CssProperty.inherit
			&& cssBackgroundCSS2.getColor().equals(color)) {
		    // background and border-color can't have the same color
		    warnings.addWarning(new Warning(cssBackgroundCSS2.color,
			    "same-colors", 1,
			    cssBorderCSS2.getTop().color, ac));
		}
	    }
	    if (cssBorderCSS2.getRight().getColor() != null) {
		CssValue color = cssBorderCSS2.getRight().getColor();
		if (color != CssProperty.inherit
			&& cssBackgroundCSS2.getColor().equals(color)) {
		    // background and border-color can't have the same color
		    warnings.addWarning(new Warning(cssBackgroundCSS2.color,
			    "same-colors", 1,
			    cssBorderCSS2.getRight().color, ac));
		}
	    }
	    if (cssBorderCSS2.getBottom().getColor() != null) {
		CssValue color = cssBorder.getBottom().getColor();
		if (color != CssProperty.inherit
			&& cssBackgroundCSS2.getColor().equals(color)) {
		    // background and border-color can't have the same color
		    warnings.addWarning(new Warning(cssBackgroundCSS2.color,
			    "same-colors", 1,
			    cssBorderCSS2.getBottom().color, ac));
		}
	    }
	    if (cssBorderCSS2.getLeft().getColor() != null) {
		CssValue color = cssBorderCSS2.getLeft().getColor();
		if (color != CssProperty.inherit
			&& cssBackgroundCSS2.getColor().equals(color)) {
		    // background and border-color can't have the same color
		    warnings.addWarning(new Warning(cssBackgroundCSS2.color,
			    "same-colors", 1,
			    cssBorderCSS2.getLeft().color, ac));
		}
	    }

	}
	else if (cssColor != null) {
	    CssValue backgroundColor = null;
	    // we need to look if there is the same selector elsewhere
	    // containing a color definition
	    for (int i=0; i<allSelectors.length; i++) {
		CssSelectors sel = allSelectors[i];
		Css1Style style =
		    (Css1Style) sel.getStyle();
		if(backgroundColor == null &&
		   sel.toString().equals(selector.toString())) {
		    backgroundColor = ((Css1Style) sel.getStyle()).
		    	cssBackground.getColor();
		}
		if (style.cssBackground.getColor() != null) {
		    if (style.cssBackground.getColor().equals(cssColor.getColor())) {
			warnings.addWarning(new Warning(cssColor, "same-colors2", 1,
				new String[] { style.cssBackground.color.getSelectors().toString(),
					       cssColor.getSelectors().toString() }, ac));
		    }
		}
	    }
	    if(backgroundColor == null) {
		// It's better to have a background color with a color
		warnings.addWarning(new Warning(cssColor, 
			"no-background-color", 2, ac));
	    }
	}
	else if (cssColorCSS1 != null) {
	    CssValue backgroundColor = null;
	    // we need to look if there is the same selector elsewhere
	    // containing a color definition
	    for (int i=0; i<allSelectors.length; i++) {
		CssSelectors sel = allSelectors[i];
		Css1Style style = (Css1Style) sel.getStyle();
		if(backgroundColor == null &&
			sel.toString().equals(selector.toString())) {
		    backgroundColor = ((Css1Style) sel.getStyle()).
		    	cssBackgroundCSS1.getColor();
		}
		if (style.cssBackgroundCSS1.getColor() != null) {
		    if (style.cssBackgroundCSS1.getColor().equals(cssColorCSS1.getColor())) {
			warnings.addWarning(new Warning(cssColorCSS1, "same-colors2", 1,
					new String[] { style.cssBackgroundCSS1.color.getSelectors().toString(),
				cssColorCSS1.getSelectors().toString() }, ac));
		    }
		}
	    }
	    if(backgroundColor == null) {
		// It's better to have a background color with a color
		warnings.addWarning(new Warning(cssColorCSS1, 
			"no-background-color", 2, ac));
	    }
	}
	else if (cssColorCSS2 != null) {
	    CssValue backgroundColor = null;
	    // we need to look if there is the same selector elsewhere
	    // containing a color definition
	    for (int i=0; i<allSelectors.length; i++) {
		CssSelectors sel = allSelectors[i];
		Css1Style style = (Css1Style) sel.getStyle();
		if(backgroundColor == null && 
			sel.toString().equals(selector.toString())) {
		    backgroundColor = ((Css1Style) sel.getStyle()).
		    	cssBackgroundCSS2.getColor();
		}
		if (style.cssBackgroundCSS2.getColor() != null) {
		    if (style.cssBackgroundCSS2.getColor().equals(cssColorCSS2.getColor())) {
			warnings.addWarning(new Warning(cssColorCSS2, "same-colors2", 1,
					new String[] { style.cssBackgroundCSS2.color.getSelectors().toString(),
				cssColorCSS2.getSelectors().toString() }, ac));
		    }
		}
	    }
	    if(backgroundColor == null) {
		// It's better to have a background color with a color
		warnings.addWarning(new Warning(cssColorCSS2, 
			"no-background-color", 2, ac));
	    }
	}

	// now testing for % and length in padding and marging
	// @@FIXME I don't be carreful with the value zero ...

	RelativeAndAbsolute checker = new RelativeAndAbsolute();
	CssProperty info = null;

	if (cssMargin.getTop() != null) {
	    info = cssMargin.getTop();
	    checker.compute(cssMargin.getTop().getValue());
	}
	if (cssMargin.getBottom() != null) {
	    info = cssMargin.getBottom();
	    checker.compute(cssMargin.getBottom().getValue());
	}
	if (checker.isNotRobust()) {
	    warnings.addWarning(new Warning(info.getSourceFile(),
		    info.getLine(),
		    "relative-absolute", 2,
		    new String[] { "margin" }, ac));
	}
	checker.reset();

	if (cssMargin.getRight() != null) {
	    info = cssMargin.getRight();
	    checker.compute(cssMargin.getRight().getValue());
	}
	if (cssMargin.getLeft() != null) {
	    info = cssMargin.getLeft();
	    checker.compute(cssMargin.getLeft().getValue());
	}
	if (checker.isNotRobust()) {
	    warnings.addWarning(new Warning(info.getSourceFile(),
		    info.getLine(),
		    "relative-absolute", 2,
		    new String[] { "margin" }, ac));
	}
	checker.reset();

	if (cssPadding.getTop() != null) {
	    info = cssPadding.getTop();
	    checker.compute(cssPadding.getTop().getValue());
	}
	if (cssPadding.getBottom() != null) {
	    info = cssPadding.getBottom();
	    checker.compute(cssPadding.getBottom().getValue());
	}
	if (checker.isNotRobust()) {
	    warnings.addWarning(new Warning(info.getSourceFile(),
		    info.getLine(),
		    "relative-absolute", 2,
		    new String[] { "padding" }, ac));
	}
	checker.reset();

	if (cssPadding.getRight() != null) {
	    info = cssPadding.getRight();
	    checker.compute(cssPadding.getRight().getValue());
	}
	if (cssPadding.getLeft() != null) {
	    info = cssPadding.getLeft();
	    checker.compute(cssPadding.getLeft().getValue());
	}

	if (checker.isNotRobust()) {
	    warnings.addWarning(new Warning(info.getSourceFile(),
		    info.getLine(),
		    "relative-absolute", 2,
		    new String[] { "padding" }, ac));
	}

	if (Util.fromHTMLFile) {
	    if ((cssTextIndent != null)
		    && (selector != null)
		    && (!selector.isBlockLevelElement())) {
		warnings.addWarning(new Warning(cssTextIndent,
			"block-level", 1, ac));
	    }
	    else if ((cssTextIndentMob != null)
		    && (selector != null)
		    && (!selector.isBlockLevelElement())) {
		warnings.addWarning(new Warning(cssTextAlignMob,
			"block-level", 1, ac));
	    }
	    if ((cssTextAlign != null)
		    && (selector != null)
		    && (!selector.isBlockLevelElement())) {
		warnings.addWarning(new Warning(cssTextAlign,
			"block-level", 1, ac));
	    }
	    else if ((cssTextAlignMob != null)
		    && (selector != null)
		    && (!selector.isBlockLevelElement())) {
		warnings.addWarning(new Warning(cssTextAlignMob,
			"block-level", 1, ac));
	    }
	    else if ((cssTextAlignTV != null)
		    && (selector != null)
		    && (!selector.isBlockLevelElement())) {
		warnings.addWarning(new Warning(cssTextAlignTV,
			"block-level", 1, ac));
	    }
	    if ((cssWhiteSpace != null)
		    && (selector != null)
		    && (!selector.isBlockLevelElement())) {
		warnings.addWarning(new Warning(cssWhiteSpace,
			"block-level", 1, ac));
	    }
	}

	if (cssMarkerOffset != null) {
	    //@@ beurk you know what ? 5 is horrible.
	    if ((cssDisplay == null)
		    || (cssDisplay.value != 5)) {
		warnings.addWarning(new Warning(cssMarkerOffset,
			"marker", 1, ac));
	    }
	}
    }
}

class RelativeAndAbsolute {
    boolean relative = false;
    boolean absolute = false;
    final void reset() {
	relative = false;
	absolute = false;
    }
    final boolean isNotRobust() {
	return relative && absolute;
    }
    final void compute(CssValue value) {
	if (value instanceof CssPercentage) {
	    relative |= true;
	} else if (value instanceof CssLength) {
	    CssLength length = (CssLength) value;
	    if (!length.getUnit().equals("ex")
		    || !length.getUnit().equals("em")) {
		absolute |= true;
	    } else {
		relative |= true;
	    }
	}
    }

}

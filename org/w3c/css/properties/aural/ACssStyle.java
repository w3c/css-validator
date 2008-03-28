//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.aural;

import java.util.Enumeration;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.properties.css1.Css1Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;
import org.w3c.css.values.CssPercentage;

/**
 * @version $Revision$
 */
public class ACssStyle extends Css1Style {

  ACssVolume acssVolume;
  ACssPause  acssPause = new ACssPause(); // it's a macro
  ACssCue  acssCue = new ACssCue(); // it's a macro
  ACssAzimuth acssAzimuth;
  ACssElevation acssElevation;
  ACssSpeechRate acssSpeechRate;
  ACssVoiceFamily acssVoiceFamily;
  ACssPitch acssPitch;
  ACssPitchRange acssPitchRange;
  ACssPlayDuring acssPlayDuring;

  ACssStress acssStress;
  ACssRichness acssRichness;

  ACssSpeakPunctuation acssSpeakPunctuation;
  ACssSpeakDate    acssSpeakDate;
  ACssSpeakNumeral acssSpeakNumeral;
  ACssSpeakTime    acssSpeakTime;
  ACssSpeak        acssSpeak;

  ACssSpeakCSS3    acssSpeakCSS3;
  ACssVoiceVolume  acssVoiceVolume;
  ACssVoiceBalance acssVoiceBalance;
  ACssVoiceFamilyCSS3 acssVoiceFamilyCSS3;
  ACssVoiceRate    acssVoiceRate;
  ACssVoicePitchRange acssVoicePitchRange;
  ACssVoiceStress  acssVoiceStress;
  ACssVoiceDuration acssVoiceDuration;
  ACssInterpretAs  acssInterpretAs;
  ACssPhonemes 	   acssPhonemes;

  /**
   * Get the volume
   */
  public ACssVolume getVolume() {
    if (acssVolume == null) {
      acssVolume = (ACssVolume) style.CascadingOrder(new ACssVolume(), style, selector);
    }
    return acssVolume;
  }

  /**
   * Get the pause after
   */
  public ACssPauseAfter getPauseAfter() {
    if (acssPause.pauseAfter == null) {
      acssPause.pauseAfter = (ACssPauseAfter) style.CascadingOrder(new ACssPauseAfter(), style, selector);
    }
    return acssPause.pauseAfter;
  }

  /**
   * Get the pause before
   */
  public ACssPauseBefore getPauseBefore() {
    if (acssPause.pauseBefore == null) {
      acssPause.pauseBefore = (ACssPauseBefore) style.CascadingOrder(new ACssPauseBefore(), style, selector);
    }
    return acssPause.pauseBefore;
  }

  /**
   * Get the pause
   */
  public ACssPause getPause() {
    if (acssPause.pauseAfter == null) {
      acssPause.pauseAfter = getPauseAfter();
    }
    if (acssPause.pauseBefore == null) {
      acssPause.pauseBefore = getPauseBefore();
    }
    return acssPause;
  }

  /**
   * Get the azimuth
   */
  public ACssAzimuth getAzimuth() {
    if (acssAzimuth == null) {
      acssAzimuth =
	(ACssAzimuth) style.CascadingOrder(new ACssAzimuth(),
					   style, selector);
    }
    return acssAzimuth;
  }

  /**
   * Get the elevation
   */
  public ACssElevation getElevation() {
    if (acssElevation == null) {
      acssElevation =
	(ACssElevation) style.CascadingOrder(new ACssElevation(),
					     style, selector);
    }
    return acssElevation;
  }

  /**
   * Get the speech rate
   */
  public ACssSpeechRate getSpeechRate() {
    if (acssSpeechRate == null) {
      acssSpeechRate = (ACssSpeechRate) style.CascadingOrder(new ACssSpeechRate(), style, selector);
    }
    return acssSpeechRate;
  }

  /**
   * Get the voice family
   */
  public ACssVoiceFamily getVoiceFamily() {
    if (acssVoiceFamily == null) {
      acssVoiceFamily = (ACssVoiceFamily) style.CascadingOrder(new ACssVoiceFamily(), style, selector);
    }
    return acssVoiceFamily;
  }

  /**
   * Get the pitch
   */
  public ACssPitch getPitch() {
    if (acssPitch == null) {
      acssPitch = (ACssPitch) style.CascadingOrder(new ACssPitch(), style, selector);
    }
    return acssPitch;
  }

  /**
   * Get the pitch
   */
  public ACssPitchRange getPitchRange() {
    if (acssPitchRange == null) {
      acssPitchRange = (ACssPitchRange) style.CascadingOrder(new ACssPitchRange(), style, selector);
    }
    return acssPitchRange;
  }

  /**
   * Get the cue after
   */
  public ACssCueAfter getCueAfter() {
    if (acssCue.cueAfter == null) {
      acssCue.cueAfter = (ACssCueAfter) style.CascadingOrder(new ACssCueAfter(), style, selector);
    }
    return acssCue.cueAfter;
  }

  /**
   * Get the cue before
   */
  public ACssCueBefore getCueBefore() {
    if (acssCue.cueBefore == null) {
      acssCue.cueBefore = (ACssCueBefore) style.CascadingOrder(new ACssCueBefore(), style, selector);
    }
    return acssCue.cueBefore;
  }

  /**
   * Get the cue
   */
  public ACssCue getCue() {
    if (acssCue.cueAfter == null) {
      acssCue.cueAfter = getCueAfter();
    }
    if (acssCue.cueBefore == null) {
      acssCue.cueBefore = getCueBefore();
    }
    return acssCue;
  }

  /**
   * Get the play during
   */
  public ACssPlayDuring getPlayDuring() {
    if (acssPlayDuring == null) {
      acssPlayDuring =
	(ACssPlayDuring) style.CascadingOrder(new ACssPlayDuring(),
					      style, selector);
    }
    return acssPlayDuring;
  }

  /**
   * Get the stress
   */
  public ACssStress getStress() {
    if (acssStress == null) {
      acssStress =
	(ACssStress) style.CascadingOrder(new ACssStress(),
					    style, selector);
    }
    return acssStress;
  }

  /**
   * Get the richness
   */
  public ACssRichness getRichness() {
    if (acssRichness == null) {
      acssRichness =
	(ACssRichness) style.CascadingOrder(new ACssRichness(),
					    style, selector);
    }
    return acssRichness;
  }

  /**
   * Get the speak punctuation
   */
  public ACssSpeakPunctuation getSpeakPunctuation() {
    if (acssSpeakPunctuation == null) {
      acssSpeakPunctuation =
	(ACssSpeakPunctuation) style.CascadingOrder(new ACssSpeakPunctuation(),
						    style, selector);
    }
    return acssSpeakPunctuation;
  }

  /**
   * Get the speak date
   */
  public ACssSpeakDate getSpeakDate() {
    if (acssSpeakDate == null) {
      acssSpeakDate =
	(ACssSpeakDate) style.CascadingOrder(new ACssSpeakDate(), style, selector);
    }
    return acssSpeakDate;
  }

  /**
   * Get the speak numeral
   */
  public ACssSpeakNumeral getSpeakNumeral() {
    if (acssSpeakNumeral == null) {
      acssSpeakNumeral =
	(ACssSpeakNumeral) style.CascadingOrder(new ACssSpeakNumeral(), style, selector);
    }
    return acssSpeakNumeral;
  }

  /**
   * Get the speak time
   */
  public ACssSpeakTime getSpeakTime() {
    if (acssSpeakTime == null) {
      acssSpeakTime =
	(ACssSpeakTime) style.CascadingOrder(new ACssSpeakTime(), style, selector);
    }
    return acssSpeakTime;
  }

  /**
   * Get the speak
   */
  public ACssSpeak getSpeak() {
    if (acssSpeak == null) {
      acssSpeak =
	(ACssSpeak) style.CascadingOrder(new ACssSpeak(), style, selector);
    }
    return acssSpeak;
  }

  public ACssSpeakCSS3 getSpeakCSS3() {
    if (acssSpeakCSS3 == null) {
      acssSpeakCSS3 =
	(ACssSpeakCSS3) style.CascadingOrder(new ACssSpeakCSS3(), style, selector);
    }
    return acssSpeakCSS3;
  }

  public ACssVoiceVolume getVoiceVolume() {
    if (acssVoiceVolume == null) {
      acssVoiceVolume =
	(ACssVoiceVolume) style.CascadingOrder(new ACssVoiceVolume(), style, selector);
    }
    return acssVoiceVolume;
  }

  public ACssVoiceBalance getVoiceBalance() {
    if (acssVoiceBalance == null) {
      acssVoiceBalance =
	(ACssVoiceBalance) style.CascadingOrder(new ACssVoiceBalance(), style, selector);
    }
    return acssVoiceBalance;
  }

  public ACssVoiceFamilyCSS3 getVoiceFamilyCSS3() {
    if (acssVoiceFamilyCSS3 == null) {
      acssVoiceFamilyCSS3 =
	(ACssVoiceFamilyCSS3) style.CascadingOrder(new ACssVoiceFamilyCSS3(), style, selector);
    }
    return acssVoiceFamilyCSS3;
  }

  public ACssVoiceRate getVoiceRate() {
    if (acssVoiceRate == null) {
      acssVoiceRate =
	(ACssVoiceRate) style.CascadingOrder(new ACssVoiceRate(), style, selector);
    }
    return acssVoiceRate;
  }

  public ACssVoicePitchRange getVoicePitchRange() {
    if (acssVoicePitchRange == null) {
      acssVoicePitchRange =
	(ACssVoicePitchRange) style.CascadingOrder(new ACssVoicePitchRange(), style, selector);
    }
    return acssVoicePitchRange;
  }

  public ACssVoiceStress getVoiceStress() {
    if (acssVoiceStress == null) {
      acssVoiceStress =
	(ACssVoiceStress) style.CascadingOrder(new ACssVoiceStress(), style, selector);
    }
    return acssVoiceStress;
  }

  public ACssVoiceDuration getVoiceDuration() {
    if (acssVoiceDuration == null) {
      acssVoiceDuration =
	(ACssVoiceDuration) style.CascadingOrder(new ACssVoiceDuration(), style, selector);
    }
    return acssVoiceDuration;
  }

  public ACssInterpretAs getInterpretAs() {
    if (acssInterpretAs == null) {
      acssInterpretAs =
	(ACssInterpretAs) style.CascadingOrder(new ACssInterpretAs(), style, selector);
    }
    return acssInterpretAs;
  }

  public ACssPhonemes getPhonemes() {
    if (acssPhonemes == null) {
      acssPhonemes =
	(ACssPhonemes) style.CascadingOrder(new ACssPhonemes(), style, selector);
    }
    return acssPhonemes;
  }

  public void print(CssPrinterStyle printer) {
    super.print(printer);
    if (acssVolume != null)
      acssVolume.print(printer);
    if (acssPlayDuring != null)
      acssPlayDuring.print(printer);
    acssPause.print(printer); // don't test null: it's a macro.
    if (acssAzimuth != null)
      acssAzimuth.print(printer);
    if (acssElevation != null)
      acssElevation.print(printer);
    if (acssSpeechRate != null)
      acssSpeechRate.print(printer);
    if (acssVoiceFamily != null)
      acssVoiceFamily.print(printer);
    if (acssPitch != null)
      acssPitch.print(printer);
    if (acssPitchRange != null)
      acssPitchRange.print(printer);
    acssCue.print(printer); // don't test null: it's a macro.
    if (acssStress != null)
      acssStress.print(printer);
    if (acssRichness != null)
      acssRichness.print(printer);
    if (acssSpeakPunctuation != null)
      acssSpeakPunctuation.print(printer);
    if (acssSpeakDate != null)
      acssSpeakDate.print(printer);
    if (acssSpeakNumeral != null)
      acssSpeakNumeral.print(printer);
    if (acssSpeakTime != null)
      acssSpeakTime.print(printer);
    if (acssSpeak != null)
      acssSpeak.print(printer);
    if (acssSpeakCSS3 != null)
	  acssSpeakCSS3.print(printer);
	if (acssVoiceVolume != null)
	  acssVoiceVolume.print(printer);
	if (acssVoiceBalance != null)
	  acssVoiceBalance.print(printer);
	if (acssVoiceFamilyCSS3 != null)
	  acssVoiceFamilyCSS3.print(printer);
	if (acssVoiceRate != null)
	  acssVoiceRate.print(printer);
	if (acssVoicePitchRange != null)
	  acssVoicePitchRange.print(printer);
	if (acssVoiceStress != null)
	  acssVoiceStress.print(printer);
	if (acssVoiceDuration != null)
	  acssVoiceDuration.print(printer);
	if (acssInterpretAs != null)
	  acssInterpretAs.print(printer);
	if (acssPhonemes != null)
	  acssPhonemes.print(printer);

  }

  /**
   * Find conflicts in this Style
   *
   * @param warnings For warnings reports.
   * @param allSelectors All contexts is the entire style sheet.
   */
  public void findConflicts(ApplContext ac, Warnings warnings,
	  CssSelectors selector, CssSelectors[] allSelectors) {
    super.findConflicts(ac, warnings, selector, allSelectors);
    if (acssVoiceFamily != null) {
	if (!acssVoiceFamily.containsGenericFamily()) {
	    warnings.addWarning(new Warning(acssVoiceFamily,
		    "no-generic-family", 2, ac));
	}
	if (acssVoiceFamily.withSpace) {
	    warnings.addWarning(new Warning(acssVoiceFamily,
		    "with-space", 1, ac));
	}
    }

    if ((acssPause.getBefore() != null) &&
	!acssPause.getBefore().isSoftlyInherited() &&
	!(acssPause.getBefore().get() instanceof CssPercentage)) {
      // Using relative units gives more robust stylesheets
	warnings.addWarning(new Warning(acssPause.getBefore(),
		"relative", 2, ac));
    }
    if ((acssPause.getAfter() != null) &&
	!acssPause.getAfter().isSoftlyInherited() &&
	!(acssPause.getAfter().get() instanceof CssPercentage)) {
	// Using relative units gives more robust stylesheets
	warnings.addWarning(new Warning(acssPause.getAfter(),
		"relative", 2, ac));
    }


  }

}

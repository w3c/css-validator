//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.aural;

import org.w3c.css.parser.CssSelectors;
import org.w3c.css.properties.css1.Css1Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;

/**
 * @version $Revision$
 */
public class ACssStyle extends Css1Style {

	ACssVoiceFamily acssVoiceFamily;
	ACssPlayDuring acssPlayDuring;

	ACssSpeakDate acssSpeakDate;
	ACssSpeakTime acssSpeakTime;

	ACssVoiceVolume acssVoiceVolume;
	ACssVoiceBalance acssVoiceBalance;
	ACssVoiceFamilyCSS3 acssVoiceFamilyCSS3;
	ACssVoiceRate acssVoiceRate;
	ACssVoicePitchRange acssVoicePitchRange;
	ACssVoiceDuration acssVoiceDuration;

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
	 * Get the speak time
	 */
	public ACssSpeakTime getSpeakTime() {
		if (acssSpeakTime == null) {
			acssSpeakTime =
					(ACssSpeakTime) style.CascadingOrder(new ACssSpeakTime(), style, selector);
		}
		return acssSpeakTime;
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

	public ACssVoiceDuration getVoiceDuration() {
		if (acssVoiceDuration == null) {
			acssVoiceDuration =
					(ACssVoiceDuration) style.CascadingOrder(new ACssVoiceDuration(), style, selector);
		}
		return acssVoiceDuration;
	}

	/**
	 * Find conflicts in this Style
	 *
	 * @param warnings     For warnings reports.
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
	  /* TODO move to CSS2

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

      */
	}

}

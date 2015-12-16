package de.schmitzhermes.simpleclitool.question;

import java.util.Arrays;
import java.util.List;

import de.schmitzhermes.simpleclitool.configuration.Configuration;

public abstract class CLYesNoQuestion extends CLDecisionQuestion {
	public static final String YES = "y";

	public static final String NO = "n";

	public CLYesNoQuestion(Configuration config, String question) {
		super(config, question);
	}

	@Override
	public void makeDecisionBasedOnAnswer(String answer) {
		if (answer.equalsIgnoreCase(YES)) {
			yesAction();
		} else {
			noAction();
		}
	}

	@Override
	public List<String> getPossibleAnswers() {
		return Arrays.asList(YES, NO);
	}
}

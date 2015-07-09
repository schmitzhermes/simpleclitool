package de.schmitzhermes.simpleclitool.question;

import java.io.IOException;
import java.util.List;

import de.schmitzhermes.simpleclitool.configuration.Configuration;
import de.schmitzhermes.simpleclitool.util.Util;

public abstract class CLDecisionQuestion {
    protected String question;

    protected List<String> answers;

    protected static final String ANSWER_SEPARATOR = "/";

    protected Configuration config;

    public CLDecisionQuestion(Configuration config, String question) {
	this.config = config;
	this.question = question;
	answers = getPossibleAnswers();
    }

    public void ask() throws IOException {
	String answer;
	do {
	    System.out.println(question + " (" + Util.join(answers, ANSWER_SEPARATOR) + ")");
	    answer = config.getConsoleReader().readLine();
	    if (checkIfAnswerIsSuitable(answer)) {
		makeDecisionBasedOnAnswer(answer);
	    }
	} while (!checkIfAnswerIsSuitable(answer));

    }
    
    public boolean checkIfAnswerIsSuitable(String answer) {
	for (String possibleAnswer : answers) {
	    if (answer.equalsIgnoreCase(possibleAnswer)) {
		return true;
	    }
	}

	return false;
    }

    public void noAction() {
	// intentionally left blank; just needful if no action is available
    }

    public abstract void yesAction();
    
    public abstract List<String> getPossibleAnswers();
    
    public abstract void makeDecisionBasedOnAnswer(String answer);
}

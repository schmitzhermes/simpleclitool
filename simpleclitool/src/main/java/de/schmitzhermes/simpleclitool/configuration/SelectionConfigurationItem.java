package de.schmitzhermes.simpleclitool.configuration;

import java.io.IOException;

public class SelectionConfigurationItem<T> extends ConfigurationItem<T> {
	private SelectionContent<T> content;

	public SelectionConfigurationItem(Integer argNumber, String optionName, String question,
			Class<?> itemClass, T defaultValue, SelectionContent<T> content, Configuration config) {
		super(argNumber, optionName, question, itemClass, defaultValue, config);
		this.content = content;
	}

	private String getValidOptions() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < content.getItems().size(); i++) {
			sb.append(i);
			sb.append(", ");
		}

		return sb.toString().substring(0, sb.length() - 2);
	}

	private boolean inputIsValid(String input) {
		int choiceAsNumber = -1;
		try {
			choiceAsNumber = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("Dies war keine valide Auswahl. Möglich ist: " + getValidOptions());
			return false;
		}
		if (choiceAsNumber >= content.getItems().size()) {
			System.out.println("Dies war keine valide Auswahl. Möglich ist: " + getValidOptions());
			return false;
		}

		return true;
	}

	@Override
	public void start() throws IOException {
		String choice = null;
		do {
			System.out.println(question);

			for (int i = 0; i < content.getItems().size(); i++) {
				System.out.println("[" + i + "] " + content.getItems().get(i).toString());
			}

			choice = config.getConsoleReader().readLine();
			item = content.getItems().get(Integer.parseInt(choice));
		} while (!inputIsValid(choice));

	}
}

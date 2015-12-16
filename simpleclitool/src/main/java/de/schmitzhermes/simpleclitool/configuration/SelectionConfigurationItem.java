package de.schmitzhermes.simpleclitool.configuration;

import java.io.IOException;

public class SelectionConfigurationItem<T> extends ConfigurationItem<T> {
	private SelectionContent<T> content;

	public SelectionConfigurationItem(Integer argNumber, String optionName, String question,
			Class<?> itemClass, T defaultValue, SelectionContent<T> content, Configuration config) {
		super(argNumber, optionName, question, itemClass, defaultValue, config);
		this.content = content;
	}

	@Override
	public void start() throws IOException {
		System.out.println(question);

		for (int i = 0; i < content.getItems().size(); i++) {
			System.out.println("[" + i + "] " + content.getItems().get(i).toString());
		}
		String choice = config.getConsoleReader().readLine();
		int choiceAsNumber = -1;
		try {
			choiceAsNumber = Integer.parseInt(choice);
		} catch (NumberFormatException e) {
			System.out.println("Dies war keine valide Auswahl. Möglich ist: " + content.toString());
			start();
		}
		if (choiceAsNumber >= content.getItems().size()) {
			System.out.println("Dies war keine valide Auswahl. Möglich ist: " + content.toString());
			start();
		}

		item = content.getItems().get(Integer.parseInt(choice));

	}
}

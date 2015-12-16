package de.schmitzhermes.simpleclitool.configuration;

import java.io.Console;
import java.io.IOException;

public abstract class ConfigurationItem<T> {

	protected Integer argNumber;

	protected String optionName;

	protected T item;

	protected String question;

	protected T defaultValue;

	protected Class<?> itemClass;

	protected Configuration config;

	protected boolean hideInput;

	public ConfigurationItem(Integer argNumber, String optionName, String question,
			Class<?> itemClass, T defaultValue, Configuration config) {
		this.argNumber = argNumber;
		this.optionName = optionName;
		this.defaultValue = defaultValue;
		this.question = question;
		this.itemClass = itemClass;
		this.config = config;
	}

	public ConfigurationItem(Integer argNumber, String optionName, String question,
			Class<?> itemClass, Configuration config) {
		this.argNumber = argNumber;
		this.optionName = optionName;
		this.defaultValue = null;
		this.question = question;
		this.itemClass = itemClass;
		this.config = config;
	}

	public ConfigurationItem(Integer argNumber, String optionName, String question,
			Class<?> itemClass, boolean hideInput, Configuration config) {
		this.argNumber = argNumber;
		this.optionName = optionName;
		this.defaultValue = null;
		this.question = question;
		this.itemClass = itemClass;
		this.hideInput = hideInput;
		this.config = config;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public void start() throws IOException {
		if (defaultValue != null) {
			System.out.println(question + " (Standardwert: " + defaultValue.toString() + ")");
		} else {
			System.out.println(question);
		}

		if (hideInput) {
			Console console = System.console();
			convertUserInput(new String(console.readPassword()));
		} else {
			convertUserInput(config.getConsoleReader().readLine());
		}
	}

	public boolean convertUserInput(String userInput) {
		if (userInput.isEmpty()) {
			if (defaultValue == null) {
				throw new IllegalArgumentException(
						"Der Standardwert ist nicht gesetzt und es wurde kein expliziter Wert gesetzt. Das Programm kann nicht ausgeführt werden.");
			}
			item = defaultValue;
			return true; // dann abbrechen
		}

		return false;
	}
}

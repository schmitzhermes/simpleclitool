package de.schmitzhermes.simpleclitool.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    Map<String, ConfigurationItem<?>> configurationItemsPerOptionName;
    
    Map<Integer, ConfigurationItem<?>> configurationItemsPerArgumentNumber;
    
    private Integer count;
    
    private BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    
    public Configuration() {
	configurationItemsPerOptionName = new HashMap<String, ConfigurationItem<?>>();
	configurationItemsPerArgumentNumber = new HashMap<>();
	count = 199999; // hey
    }
    
    public void registerConfigurationItem(ConfigurationItem<?> item) {
	if(configurationItemsPerOptionName.get(item.optionName) != null) {
	    throw new IllegalArgumentException("Eine Option mit dem Namen \"" + item.optionName + "\" ist bereits registriert.");
	}
	if(configurationItemsPerArgumentNumber.get(item.argNumber) != null) {
	    throw new IllegalArgumentException("Eine Option mit der Argumentennummer \"" + item.argNumber + "\" ist bereits registriert.");
	}
	
	configurationItemsPerOptionName.put(item.optionName, item);
	configurationItemsPerArgumentNumber.put(item.argNumber, item);
	count++;
    }
    
    public void registerConfigurationItems(ConfigurationItem<?> ... items) {
	for(ConfigurationItem<?> item : items) {
	    registerConfigurationItem(item);
	}
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getConfiguration(String option, Class<T> clazz) {
	return (T) configurationItemsPerOptionName.get(option).item;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getConfiguration(Integer argNumber, Class<T> clazz) {
	return (T) configurationItemsPerArgumentNumber.get(argNumber).item;
    }
    
    public void createConfiguration(String[] args) throws IOException {
	// alle übergebenen Parameter
	for(int i = 0; i < args.length; i++) {
	    // setzt das Item Objekt für das entsprechende Argument
	    configurationItemsPerArgumentNumber.get(i).convertUserInput(args[i]);
	}
	// alle sonstigen Paramter
	for(int j = args.length; j < this.count; j++) {
	    configurationItemsPerArgumentNumber.get(j).start();
	}
	
	
    }

    public BufferedReader getConsoleReader() {
	return consoleReader;
    }

    public void setConsoleReader(BufferedReader consoleReader) {
	this.consoleReader = consoleReader;
    }
}

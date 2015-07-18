# simpleclitool
Simple CLI-Tool for prototyping your stuff.

The code should be pretty much self-explaining. 
The comments at the end of each line should explain the constructor-arguments.

## Configuration
You create configuration objects and register them in the configuration.
The user either enters them at a command line level, like this:
```
java -jar someprogram.jar firstArg secondArg thirdArg
```

Or simpleclitool will ask the user for the configuration.

Until now, it is only possible to pass arguments in the specified order. This means you are not able to pass argument 0 and argument 2 via CLI, but argument 1 via question. Making this possible is part of future work.

The code below will show examples how you use the configuration.

```java
public static void main(String[] args) throws IOException, ParserConfigurationException {
  // initialize the configuration
	Configuration config = new Configuration();
	
	// a normal configuration item
	// the generic type is the class of the object the string should be converted to
	ConfigurationItem<File> path = new ConfigurationItem<File>(0,// the argument number (the position you can pass the argument at command line level)
		"path",  // optionname
		"Bitte geben Sie den Pfad ein, in dem die zu analysierenden Dokumente liegen.", // question to the user
		File.class, // class of the object the string should be converted to
		new File("."), // the default value
		config) { // the configuration initialized above

      // This function should be overwritten in order 
      // to convert the String user input into the desired format
	    @Override
	    public boolean convertUserInput(String userInput) {
    		if(!super.convertUserInput(userInput)) { // this is necessary (default value ....)
    		    item = new File(userInput); // 
    		}
		
    		return true;
    	 }
	};
	
	// a selection configuration item
	// the user can select from a list of entries 
	SelectionConfigurationItem<Double> alpha = new SelectionConfigurationItem<Double>(1, // see above
		"alpha", // see above
		"Bitte wählen Sie das gewünschte Signfikanzniveau:", // see above
		Double.class, // see above
		new Double(0.05), // see above
		new SelectionContent<Double>() { // the SelectionContent
        // this object determines, which objects can be selected from the user
		    @Override
		    public List<Double> possibleSelectionItems() {
		      // in this special case the user can choose between 0.01, 0.05 and 0.10
		    	return Arrays.asList(0.01, 0.05, 0.10);
		    }
		}, 
		config) {
	    
	    // see above
	    @Override
	    public boolean convertUserInput(String userInput) {
    		if(!super.convertUserInput(userInput)) {
    		    item = Double.valueOf(userInput);
    		}
    		return true;
    	}
	};
	
	// at the end, you register your created configuration items
	config.registerConfigurationItems(path, alpha, ...);
	// and create your configuration
	config.createConfiguration(args);
	
	// I passed the configuration to my controller, but it may be useful to modify the code and make it a thread-safe singleton
	// it depends on what you want to with it
```

## Get the user-configurated values
```java

// I passed the configuration to my controller, but it may be useful to modify the code and make it a thread-safe singleton
// it depends on what you want to with it

// You can either use the specified name and class object to retrieve the configuration
config.getConfiguration("alpha", Double.class))

// Or you can use the argument number
config.getConfiguration(1, Double.class))
```

## Question to the User
During the execution you may have a question to the user. Here is how you ask him/her.
```java
// This is a yes no question where you pass the config (see above) and the actual question as a String
CLYesNoQuestion print = new CLYesNoQuestion(config, "Möchten Sie die Ergebnisse der Analyse in einer XML-Datei speichern?") {
	    // you have to override the yes Action
	    @Override
	    public void yesAction() {
    	  try {
    		    controller.outputToXMLFile(testResults);
    		} catch (ParserConfigurationException e) {
    		    e.printStackTrace();
    		}
	    }
	    
	    // You MAY override the no Action, but this is not necessary
	};
```

There is even a way to ask arbitrary questions. You can figure out how this works if you take a look at the code.
The question will be asked to the user until he enters a VALID answer (in the yes no case this is "y" or "n"). 
In order to avoid confusion, the valid answers are presented slash-separated in brackets after the question.

If you have any question feel free to contact me via github.

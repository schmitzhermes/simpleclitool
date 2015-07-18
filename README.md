# simpleclitool
Simple CLI-Tool for prototyping your stuff.

# How can you use it?

public static void main(String[] args) throws IOException, ParserConfigurationException {
	Configuration config = new Configuration();
	ConfigurationItem<File> path = new ConfigurationItem<File>(0,
		"path", 
		"Bitte geben Sie den Pfad ein, in dem die zu analysierenden Dokumente liegen.",
		File.class, 
		new File("."),
		config) {

	    @Override
	    public boolean convertUserInput(String userInput) {
		if(!super.convertUserInput(userInput)) {
		    item = new File(userInput);
		}
		
		return true;
	    }
	};

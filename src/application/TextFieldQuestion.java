package application;

/** Represents a textfield question.
 * @author Jaimie Marchuk
 * @author Peter Tran 
 * @author Julii Ruta 
 * 
 *Creates a question that takes an input from a textfield GUI. 
 *Best used for a name or age input. 
 *Calculates the matching percentage of a user's name to the celebrities 
 * names, as well as verifies if the inputed name is valid. 
 *Also verifies if an inputed age is valid. 
 *
*/
public class TextFieldQuestion extends Question{
	// instance variables
	private String justinBieber = "justinbieber";
	private String pitbull = "pitbullperez";		
	private String jenniferLopez = "jenniferlopez";
	private String taylorSwift = "taylorswift";
	
	private int justinBieberCount;
	private int pitbullCount;
	private int jenniferLopezCount;
	private int taylorSwiftCount;

	// getter methods to access encapsulated instance variables
	
	/** Gets the name of this celeb.
	 * @return justinBieber A string containing the
	 * name of this celeb.
	*/
	public String getJustinBieber() {
		return justinBieber;
	}
	
	/** Gets the name of this celeb.
	 * @return pitbull A string containing the
	 * name of this celeb.
	*/
	public String getPitbull() {
		return pitbull;
	}
	
	/** Gets the name of this celeb.
	 * @return justinBieber A string containing the
	 * name of this celeb.
	*/
	public String getJenniferLopez() {
		return jenniferLopez;
	}
	
	/** Gets the name of this celeb.
	 * @return taylorSwift A string containing the
	 * name of this celeb.
	*/
	public String getTaylorSwift() {
		return taylorSwift;
	}

	/** Gets the matching percentage of this celeb.
	 * @return justinBieberCount An int containing the matching
	 * percentage of this celeb.
	*/
	public int getJustinBieberCount() {
		return justinBieberCount;
	}	

	/** Gets the matching percentage of this celeb.
	 * @return pitbullCount An int containing the matching
	 * percentage of this celeb.
	*/
	public int getPitBullCount() {
		return pitbullCount;
	}
	
	
	/** Gets the matching percentage of this celeb.
	 * @return jenniferLopezCount An int containing matching
	 * percentage of this celeb.
	*/
	public int getJenniferLopezCount() {
		return jenniferLopezCount;
	}
	
	/** Gets the matching percentage of this celeb.
	 * @return taylorSwiftCount An int containing matching
	 * percentage of this celeb.
	*/
	public int getTaylorSwiftCount() {
		return taylorSwiftCount;
	}
	

	//constructors 
	
	/** Creates a textfield question with the specified
	 *  users input and the answer to the question. 
	 * @param id  Users input/answer to the question.
	 * @param answer  The answer to specified question.
	*/
	TextFieldQuestion(String id, String answer) {
		super(id, answer);
	}
	
	/** Creates a textfield question with the specified
	 *  answer to the question. 
	 * @param answer The answer to specified question.
	*/
	TextFieldQuestion(String answer){
		super(answer);
	}
	
	/** Creates a textfield question with the total weight of the question.
	 * @param weight Specified total weight of question towards total weight.
	*/
	 TextFieldQuestion(double weight) {
		super(weight);
	}
	 
	 
	 /**
	  * A method that verifies a user's name input. If any invalid 
	  *  characters are found, throws an exception. 
	  *  @throws InvalidNameException
	  */
	public void verifyNames() throws InvalidNameException {
		// initialization of variables
		boolean validName = true;
		char invalidChar = Character.MIN_VALUE;
	
		
		for (char c : super.getAnswer().toCharArray()) {
			// if the current character c has an ASCII table value of the following, do this
   	  		if  ((c >= '!' && c <= '@') || (c >= '[' && c <= '`') || (c >= '{' && c <= '~') ) {
   	  			validName = false;
   	  			invalidChar = c;
   	  		} 
   	  		if (!(validName)) {
   	  		    throw new InvalidNameException(String.format("Do not use %c in your name. Make sure to enter a real name.", invalidChar));
   	  		} 
	    }
		if (!validName) {
			super.setAnswer("");
		}
	}
	

	/**
     * A method that verifies a user's age input. If any invalid 
     *  characters are found, or the age does not fall into the 
     *  proper range, throws an exception. 
     *  @throws InvalidAgeException 
     */
	public void verifyAge() throws InvalidAgeException {
		// initialization of variables
		boolean validAge = true;
		char invalidChar = Character.MIN_VALUE;
		
		for (char c : super.getAnswer().toCharArray()) {
			// testing if inputed age is actually a number
	   	  	if  (!(c >= '0' && c <= '9')) {
	   	  		validAge = false;
	   	  		invalidChar = c;
	   	  	} 
	   	  	if (!(validAge)) {
	   	  		throw new InvalidAgeException(String.format("Do not use %c in your Age. Please enter a valid Age.", invalidChar));
	   	 	   
	   	  	} 
		}
		if (validAge) {
			Double doubleAge = Double.parseDouble(super.getAnswer());	
			if (doubleAge < 16) { // must be 16+
				super.setAnswer("");
				throw new InvalidAgeException("Sorry, must be 16+. Please try again.");	
			} else if (doubleAge > 60) { //must be 60-
				super.setAnswer("");
				throw new InvalidAgeException("Sorry, must be 60-. Please try again.");
			} 
		}
	}
	
	
	/**
	 * A method that calculates the matching percentage of a user's name to 
	 *  all of the celebrities names, as well as set's the matching percentage
	 *  for each celebrity. 
	 */
	public void calculateNames() {
		//first call removeDuplicateLettersHelper to remove duplicate letters
		String nameAfterChanges = removeDuplicateLettersHelper(super.getAnswer());
		
		//now call a count method for each celebrity
		justinBieberCount = characterMatchCount(nameAfterChanges, getJustinBieber());
		pitbullCount = characterMatchCount(nameAfterChanges, getPitbull());
		jenniferLopezCount = characterMatchCount(nameAfterChanges, getJenniferLopez());
		taylorSwiftCount = characterMatchCount(nameAfterChanges, getTaylorSwift());
	}
	
	
	/**
	 * A method that removes any duplicate
	 *  letters in a user's inputted name. 
	 * @return name A string with any duplicate letters removed. 
	 */
	private String removeDuplicateLettersHelper(String name) {
		// to test matching, all characters should be in lowercase and removing white spaces
		name = name.toLowerCase();
		name = name.replaceAll("\\s", "");
		
		// loop for removing duplicate letters in order to test similar letters
		for (int i = 0; i < name.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (name.charAt(i) == name.charAt(j)) {
					char[] chars = name.toCharArray();
					chars[j] = '*';
					name = String.valueOf(chars);
				}
			}
		}
		return name;
	}
	
	
	/**
	 * A method that counts the matching characters between the user's
	 *  name and a celebrities name. 
	 *  @return count An int containing the number of matching characters. 
	 */
	private int characterMatchCount(String nameAfterChanges, String celebrityName) {
		//initialization of our count
		int count = 0;
		
		//loop for every character in name by index i
		for (int i = 0; i < nameAfterChanges.length(); i++) { 
			//nested loop for every character in celebrity name by index j
			for (int p = 0; p < celebrityName.length(); p++) {
				//if name at index i is the same as celebrity name at index j, add to count
				if (nameAfterChanges.charAt(i) == celebrityName.charAt(p)) {
					count++; 
				}	
			}
		}
		return count;
	}
}

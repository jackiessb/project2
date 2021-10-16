package twitterGUI;

//will test an instance of a collection
public class Test {
	private String permanentPath;
	Collection c;
	
	public Test (String p1) {
		permanentPath = p1;
		
		c = new Collection(permanentPath);
	}
	
	//The whole deal. Includes a test remove, a test add, a tweet search by ID and username,
	//a list of all the IDs in the system, and a tweet prediciton example.
	public void unitTest() {
		Tweet dummy = new Tweet();
		
		//WORKS!
		System.out.println("TEST REMOVE\n");
		c.deleteTweet("3");
		
		//WORKS!
		System.out.println("\nTEST ADD\n");
		System.out.println("Succesfully added tweet with ID " + c.addTweet(permanentPath,"2,60001,JackieSSB,this is a test tweet! not cool."));
		
		//WORKS!
		System.out.println("\nTEST SEARCH BY ID\n");
		dummy = c.getTweetByID(2562);
		System.out.println(dummy.toString());
		
		//WORKS!
		System.out.println("\nTEST SEARCH BY USERNAME\n");
		dummy = c.getTweetByUser("JackieSSB");
		System.out.println(dummy.toString());
		
		//WORKS!
		System.out.println("\nTEST GET ALL IDS\n");
		c.printIDs();
		
		//WORKS!
		System.out.println("\nTEST PREDICTION AND AVERAGE\n");
		System.out.println(c.predicitionAverage(5)); //I want to predict five tweets and then get their average.
	}
	
	// (would) be used to train for analysis
	// no training or machine learning this time around. prediction is all examples.
	public void train(Collection c) {
		
	}

}

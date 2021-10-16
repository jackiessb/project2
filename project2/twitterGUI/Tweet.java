package twitterGUI;

public class Tweet {
	private String id;
	private int polarity;
	private String user;
	private String text;
	
	public Tweet(int polarity, String id, String user, String text) {
		this.polarity = polarity;
		this.id = id;
		this.user = user;
		this.text = text;
	}
	
	public Tweet() {
		polarity = 0;
		id = "";
		user = "";
		text = "";
	}
	
	public void setID(String i) {
		id = i;
	}
	
	public void setPolarity(int i) {
		polarity = i;
	}
	
	public void setUser(String s) {
		user = s;
	}
	
	public void setTweetText(String s) {
		text = s;
	}
	
	public String getID() {
		return id;
	}
	
	public int getPolarity() {
		return polarity;
	}
	
	public String getText() {
		return text;
	}
	
	public String getUser() {
		return user;
	}
	
	//Turns single tweet into a string
	public String toString() {
		return this.polarity + "," + this.id + "," + this.user + "," + this.text;
	}
}

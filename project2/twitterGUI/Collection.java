package twitterGUI;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Collection {
	private Map<String, Tweet> tweetMap = new HashMap<>();
	
	public Collection(String path) {
		fromFile(path);
	}
	
	public void printIDs() {
		for (Map.Entry<String, Tweet> m : tweetMap.entrySet()) {
			System.out.println(m.getKey());
		}
	}
	
	//Creates an array of IDs based on the position of Tweets in the original array 
	public int[] getIDs() {
		int[] ids = new int[tweetMap.size()];
		int place = 0;
		
		for (Map.Entry<String, Tweet> m : tweetMap.entrySet()) {
			int id = Integer.parseInt(m.getKey());
			ids[place] = id;
			place++;
		}
		
		return ids;
	}
	
	public Tweet getTweetByID(int ids) {
		return tweetMap.get(String.valueOf(ids));
	}
	
	public Tweet getTweetByUser(String user) {
		Tweet temp = new Tweet();
		
		for(Map.Entry<String, Tweet> m : tweetMap.entrySet()) {
			if (m.getValue().getUser().equals(user)) {
				temp = m.getValue();
			}
		}
		
		return temp;
	}
	
	public void deleteTweet(String id) {
		if (tweetMap.remove(id) != null)
			System.out.println("Key is valid. Tweet deleted.");
		else
			System.out.println("Tweet not found.");
	}
	
	public String addTweet(String perm, String tweet) {
		String i = toFile(perm, tweet);
		return i;
	}
	
	//Implement Weka next time around...
	//No training or machine learning. prediction is all examples.
	//https://affectivetweets.cms.waikato.ac.nz/
	public int tweetPrediciton(Tweet t) {
		return t.getPolarity();
	}
	
	//Returns an average of each tweet prediction based on how many tweets you wanna predict
	public double predicitionAverage(int count) {
		Iterator<Entry<String, Tweet>> iterator = tweetMap.entrySet().iterator();
		int limit = 1, sum = 0;
		double rate = 0;
		
		while (iterator.hasNext() && limit != count) {
			sum += tweetPrediciton(iterator.next().getValue());
			limit++;
		}
		
		return rate = sum / limit;
	}
	
	//Turns entire object into string
	public String toString() {
		for (Map.Entry<String, Tweet> m : tweetMap.entrySet()) {
			String s = m.getValue().getPolarity() + "," + m.getValue().getID() + ","
					+ m.getValue().getUser() + "," + m.getValue().getText();
			
			System.out.println(s);
		}
		return null;
	}
	
	public String[] getTweetsInStringForm() {
		String[] strings = new String[tweetMap.size()];
		int place = 0;
		
		for (Map.Entry<String, Tweet> m : tweetMap.entrySet()) {
			String s = m.getValue().getPolarity() + "," + m.getValue().getID() + ","
					+ m.getValue().getUser() + "," + m.getValue().getText();
			
			strings[place] = s;
			place++;
		}
		
		return strings;
	}
	
	public Tweet parser(String s) {
		//Tear string apart (should just be four segments)
		String[] segment = s.split(",");
		
		//Set segment values
		Tweet toPut = new Tweet(Integer.parseInt(segment[0]), segment[1], 
				segment[2], segment[3]);
		
		return toPut;
	}
	
	public Map<String, Tweet> getMap() {
		return tweetMap;
	}
	
	public void fromFile(String path) {
		BufferedReader lineReader = null;
		String[] segment = new String[4];
		
		try {
			FileReader fr = new FileReader(path);
			lineReader = new BufferedReader(fr);
			String line = "";
			while ((line = lineReader.readLine()) != null) {
				//Use parser function
				Tweet temp = parser(line);
				
				//Set values into a new value in the map
				tweetMap.put(temp.getID(), temp);
				}
			}
		catch (Exception e) {
			System.out.println("The file reader ran into an error. Trying again...");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(path.substring(1))));
				String line = null;
				while ((line = lineReader.readLine()) != null) {
					//Tear string apart (should just be four segments)
					segment = line.split(",");
					
					//Set segment values
					Tweet toPut = new Tweet(Integer.parseInt(segment[0]), segment[1], 
							segment[2], segment[3]);
					
					//Set values into a new value in the map
					tweetMap.put(toPut.getID(), toPut);
			} } catch (Exception e2) {
				System.out.println("There was a problem with the file reader. Check your format.");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader...");
					}
			}			
		}
		finally {
			if (lineReader != null) {
				try {
					lineReader.close();
				} catch (IOException e2) {
					System.err.println("could not close BufferedReader...");
				}
			}
		}
	}
	
	public String toFile(String perm, String tweet) {
		String i = parser(tweet).getID();
		
		try {
			//For writing
			FileWriter fw = new FileWriter(perm, true);
			BufferedWriter myOutfile = new BufferedWriter(fw);
			
			//Add to file
			myOutfile.write("\n" + tweet);
			myOutfile.flush();
			myOutfile.close();
			
			//Add to data structure
			tweetMap.put(i, parser(tweet));
		}
		catch (IOException e) {
			System.out.println("Tweet not saved. Try again.");
		}
		return i;
	}
}


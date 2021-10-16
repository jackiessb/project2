package twitterGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

public class tweetPanel extends JPanel {
	private Test t;
	private Collection temp;
	private String[] strings;
	private int[] ids;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	private JDialog dialog;
	private JOptionPane popup;
	private JList list;
	private JScrollPane scrollPane;
	private Frame forPopup;
	private DefaultListModel model;
	
	public tweetPanel(String path) {
		setLayout(null);
	
		//Getting tweet data
		temp = getCollection();
		strings = temp.getTweetsInStringForm(); //Array of tweets in string form
		ids = temp.getIDs(); //Array of tweet IDs in array form as they are ordered in the original Collection
		
		DefaultListModel model = new DefaultListModel(); //This allows us to edit the information
		for (int i = 0; i < ids.length; i++) {
			model.addElement(strings[i]);
		}
		
		//ListModel information
		int modelSize = model.getSize();
				
		//Creating JList list type
		list = new JList(model);
		list.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		//Scrollpane
		scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 50, 997, 192);
		list.setVisibleRowCount(5);
		scrollPane.setViewportView(list);
		add(scrollPane);
		
		//Take in what to add from the user
		JButton btnAdd = new JButton("Add...");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdd.setBounds(777, 254, 123, 37);
		add(btnAdd);
		
		//Add a tweet
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				forPopup = new Frame();
				popup = new JOptionPane();
				
				String toPut2 = popup.showInputDialog(forPopup, "Enter the tweet to be added:");
				temp.addTweet(path, toPut2); //Add to local colleciton
				model.addElement(toPut2); //Adds to model
				scrollPane.repaint(); //Refresh
			}
		});
		
		//Delete by ID
		JButton btnDelete = new JButton("Delete...");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.setBounds(910, 254, 123, 37);
		add(btnDelete);
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				forPopup = new Frame();
				popup = new JOptionPane();
				
				String toPut2 = popup.showInputDialog(forPopup, "Enter the tweet ID to be deleted:");
				temp.deleteTweet(toPut2); //Delete to local colleciton
				model.removeElement(toPut2); //Removes from model
				scrollPane.repaint(); //Refresh
			}
			
		});
		
		//Search bar
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(581, 254, 186, 36);
		add(textField);
		textField.setColumns(10);
		
		//Search options
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Search By ID");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(447, 250, 128, 23);
		add(rdbtnNewRadioButton);
				
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Search By Username");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(447, 276, 128, 23);
		add(rdbtnNewRadioButton_1);
		
		//Search bar functionality
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				//If enter is pressed, perform the search in the list, 
				//then display it if found
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				forPopup = new Frame();
				dialog = new JDialog(forPopup, "Result");
				dialog.setSize(new Dimension(400, 100));
		
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (textField.getText() == "") {
						//NEED TO GET THIS WORKING
						JLabel text = new JLabel("Your message was empty. Try again!");
						dialog.getContentPane().add(text);
						dialog.setVisible(true);
					}
					else {
						String query = textField.getText();
						
						if (rdbtnNewRadioButton.isSelected()) {
							//MAKE LOGIC FOR IF QUERY IS THE WRONG TYPE
							if (getPositionInListINT(Integer.parseInt(query), modelSize) == -1) {
								JLabel text = new JLabel("Your ID was incorrect.");
								dialog.getContentPane().add(text);
								dialog.setVisible(true);
							}
							else {
								int i = getPositionInListINT(Integer.parseInt(query), modelSize);
								String tweet = (String)model.getElementAt(i);
								
								JLabel text = new JLabel("Your tweet was found! " + tweet);
								dialog.getContentPane().add(text);
								dialog.setVisible(true);
							}
						}
						else {
							//HAVING ERRORS WITH THIS
							if (getPositionInListUSER(query, modelSize) == -1) {
								JLabel text = new JLabel("Your username does not exist.");
								dialog.getContentPane().add(text);
								dialog.setVisible(true);
							}
							else {
								int i = getPositionInListUSER(query, modelSize);
								String user = (String)model.getElementAt(i);
								
								JLabel text = new JLabel("Your username was found! " + user);
								dialog.getContentPane().add(text);
								JLabel tweet2 = new JLabel(user);
								dialog.setVisible(true);
						}
					}
				}
			}
		}});
	}
	
	public Collection getCollection() {
		String path = "C:\\Users\\memjk\\OOP\\project2\\twitterGUI\\testProcessed.txt";
		t = new Test(path);
		
		return t.c; 
	}
	
	public void testCollection() {
		//Tests the functionality of the tweets.
		t.unitTest();
	}
	
	public int getPositionInListINT(int query, int modelSize) {
		//Find the position of the tweet ID in the form of an array index.
		for (int i = 0; i < ids.length; i++) {
			if (query == ids[i] && ids.length == modelSize) {
				return i;
			}
		}
		return -1;
	}
	
	public int getPositionInListUSER(String query, int modelSize) {
		//Find the position of the tweet ID in the form of an array index.
		String compareTo;
		
		for (int i = 0; i < ids.length; i++) {
			 compareTo = temp.getTweetByID(ids[i]).getUser();
			
			if (query.equals(compareTo)) {
				return i;
			}
		}
		return -1;
	}
}

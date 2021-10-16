package twitterGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class mainPanel extends JPanel {
	private tweetPanel tweets;
	private BufferedImage logo;
	private JOptionPane popup;
	private Frame forPopup;
	
	public mainPanel() {
		setLayout(null);
		
		//Twitter logo
		try {
			logo = ImageIO.read(new File("C:\\Users\\memjk\\OOP\\project2\\twitterGUI\\twitter logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel_1 = new JLabel(new ImageIcon(logo));
		lblNewLabel_1.setBounds(950, 35, 82, 55);
		add(lblNewLabel_1);
		
		//Title
		JLabel lblNewLabel = new JLabel("TWEET PREDICITIONS!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setBounds(35, 35, 419, 34);
		add(lblNewLabel);
		
		//Adds a tweetPanel, which displays all our tweets!
		tweets = new tweetPanel("C:\\Users\\memjk\\OOP\\project2\\twitterGUI\\twitter logo.png");
		tweets.setSize(1100, 595);
		tweets.setLocation(0, 50);
		tweets.setPreferredSize(null);
		add(tweets);
	}
}

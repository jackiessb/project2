//Jackson Torregrossa
//OOP Dr. Doderer, Project 2

package twitterGUI;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import javax.swing.JScrollPane;

public class twitterMain {

	public static void main(String[] args) {
		//EVERYTHING RELATED TO THE WINDOW!
		//Creates the initial frame
		JFrame frame = new JFrame("TEST!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1100, 595));
		
		mainPanel panel = new mainPanel();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}

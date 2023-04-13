/*
 * David Jimenez
 * Dylan Williams
 * Jay Coughlin
 * Mason Richardson
 * 
 * CSC445: Computer Algorithms
 * Final Project: Hex
 */

package main;

import javax.swing.JFrame;
import main.GraphicalComponents.*;

public class Main {
    public static void main(String[] args) {
		GUI frame = new GUI();
		frame.setTitle("CSC445 Hex Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLocation(200, 150);		
		
		frame.setVisible(true);
	}
}

/*
 * TODO's:
 * 	- Edit starting GUI
 * 	- Implement winning paths
 * 	- Color Hex tiles when selected with legality
 * 	- Allow Replay/Reset
 * 	- Add CPU for Computer-VS-Human Mode
 * 	- Track turns
 * 	- Make it *pretty*
 */

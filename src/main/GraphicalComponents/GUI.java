package main.GraphicalComponents;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.Boards.*;

public class GUI extends JFrame implements ActionListener {
	private JPanel gameSettings;
	private HexBoard activeGameBoard;
	private GameRunner runner;
	private JButton startButton = new JButton("Start Game");
	
	public GUI() {
		gameSettings = new JPanel();
		gameSettings.setPreferredSize(new Dimension(500, 500));
		gameSettings.add(startButton);
		
		startButton.addActionListener(this);	
		
		this.add(gameSettings);
	}
	
	public void startGame() {
		runner = new GameRunner();
		
		activeGameBoard = new HexBoard();
		activeGameBoard.setSize(1600, 800);		
		activeGameBoard.setLayout(new BorderLayout());
		
		this.getContentPane().remove(gameSettings);
		this.setSize(1600, 800);
		this.setLocation(200, 150);
		this.getContentPane().add(activeGameBoard);
		this.validate();
		this.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
            startGame();
        }
	}
}
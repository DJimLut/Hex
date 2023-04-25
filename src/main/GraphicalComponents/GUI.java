package main.GraphicalComponents;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.Boards.*;
import main.LogicalComponents.SFX;

public class GUI extends JFrame implements ActionListener {
	private JPanel gameSettings;
	private HexBoard activeGameBoard;
	private JButton startButton = new JButton("Start Game");
	private SFX sfx = new SFX();
	
	public GUI() {
		gameSettings = new JPanel();
		gameSettings.setPreferredSize(new Dimension(500, 500));
		gameSettings.add(startButton);
		
		startButton.addActionListener(this);	
		
		this.add(gameSettings);
	}
	
	public void startGame() {
		activeGameBoard = new HexBoard();
		activeGameBoard.setSize(800, 400);		
		activeGameBoard.setLayout(new BorderLayout());
		
		this.getContentPane().remove(gameSettings);
		this.setSize(800, 400);
		this.setLocation(200, 150);
		this.getContentPane().add(activeGameBoard);
		this.validate();
		this.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			sfx.playButtonClick();
            startGame();
        }
	}
}
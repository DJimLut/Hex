package main.GraphicalComponents;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.Boards.*;

public class GUI extends JFrame implements ActionListener {
	private JPanel gameSettings;
	private JPanel startPanel;
	private JPanel titlePanel;
	private HexBoard activeGameBoard;
	private GameRunner runner;
	private JButton startButton = new JButton("Start Game");
	private ImageIcon gif = new ImageIcon("lib/GIVv.gif");
	private JLabel gifLabel = new JLabel(gif);
	private JLabel title = new JLabel("Hex Game", SwingConstants.CENTER);
	private JLabel subtitle1 = new JLabel("David Jimenez, Dylan Williams, Jay Coughlon, Mason Richardson", SwingConstants.CENTER);
	private JLabel subtitle2 = new JLabel("CSC445 Game Project, Spring 2023", SwingConstants.CENTER);
	
	public GUI() {
		gameSettings = new JPanel(new BorderLayout());
		gameSettings.setPreferredSize(new Dimension(500, 500));
		startPanel = new JPanel(new FlowLayout());
		titlePanel = new JPanel(new BorderLayout());
		title.setFont(new Font("Courier New", Font.BOLD, 24));
		subtitle1.setFont(new Font("Courier New", Font.BOLD, 12));
		subtitle2.setFont(new Font("Courier New", Font.BOLD, 12));
		startPanel.add(startButton);
		
		titlePanel.add(title, BorderLayout.NORTH);
		titlePanel.add(subtitle1, BorderLayout.CENTER);
		titlePanel.add(subtitle2, BorderLayout.SOUTH);
		
		gameSettings.add(titlePanel, BorderLayout.NORTH);
		gameSettings.add(gifLabel, BorderLayout.CENTER);
		gameSettings.add(startPanel, BorderLayout.SOUTH);
		
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

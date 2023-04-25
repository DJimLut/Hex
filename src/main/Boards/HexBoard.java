package main.Boards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Random;
import javax.naming.OperationNotSupportedException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.GraphicalComponents.Mouse;
import main.GraphicalComponents.Node;
import main.LogicalComponents.SFX;
import main.Players.Player;

public class HexBoard extends JPanel implements IBoard {
	private int boardSize = 11;
	private double sm;
	private double lg;
	private int[][] board;
	private Polygon[][] hexagons;
	private final Color[] colors={Color.WHITE, Color.RED, Color.BLUE};
	private String[] playAgain={"Yes", "No"};
	private String[] colorOpts={"Red", "Blue"};
	private String[] playerOpts={"Friend","AI"};
	private int play = 2;
	private int turn;
	private int playerTurn;
	private int p2Turn;
	private int color;
	private int numPlayers = 1;
	private Random rand = new Random();	
	private BoardData data;
	private SFX sfx = new SFX();
	
	public HexBoard() {
		addMouseListener(new Mouse(this));
		board=new int[11][11];
		hexagons = new Polygon[11][11];
		data = new BoardData();
		gameMode();		
	}
	
	public void gameMode(){
		turn = 1;
		color = 0;
		playerTurn = 1;
		p2Turn = 2;
		numPlayers = JOptionPane.showOptionDialog(null, "Would you like to play against a Friend or an AI?", "Choose players", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, playerOpts, playerOpts[0]);
		if(numPlayers == 1){
			color=JOptionPane.showOptionDialog(null, "Which color would you like to play as?", "Choose Color", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, colorOpts, colorOpts[0]);
		}
	
		// Set player Color and Turn correctly
		if(color == 1){
			playerTurn = 2;
		}
		if(numPlayers ==1){
			p2Turn = -1;
		}
		if(numPlayers == 1 && playerTurn == 2){
			playAt(rand.nextInt(11), rand.nextInt(11));
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		double panelWidth = this.getSize().width;
	    double panelHeight = this.getSize().height;
	    double dim1 = panelWidth / (6 * boardSize + 2);
	    double dim2 = panelHeight / (4 + 3 * (boardSize - 1));
	    this.sm = Math.min(dim1, dim2);
	    this.lg = 2 * sm;
	    
		drawBorders(g2d);
		
		g2d.setColor(Color.BLACK);
		
		for (int y = 0; y < boardSize; y++) {
			for (int x = 0; x < boardSize; x++) {
				hexagons[x][y] = calcHexPoly(x, y);
				drawHex(g2d, x, y);
			}
		}		
	}
	
	public void drawHex(Graphics2D g, int column, int row) {
		Polygon polygon = calcHexPoly(column, row);
	    g.setPaint(getFillColor(column, row));
	    g.fill(polygon);
	    g.setPaint(Color.BLACK);
	    g.draw(polygon);
	}
	
	public Polygon calcHexPoly(int column, int row) {
	    double xl_double = (2 * lg * column) + (lg * row) + lg;
	    int xl = (int) (xl_double);
	    int xm = (int) (xl_double + lg);
	    int xr = (int) (xl_double + lg * 2);
	    double y1_double = row * (lg + sm);
	    int y1 = (int) y1_double;
	    int y2 = (int) (y1_double + sm);
	    int y3 = (int) (y1_double + sm + lg);
	    int y4 = (int) (y1_double + sm + lg + sm);
	    int[] x = {xm, xr, xr, xm, xl, xl};
	    int[] y = {y1, y2, y3, y4, y3, y2};

	    return new Polygon(x, y, 6);
	}
	
	public void drawBorders(Graphics2D g) {
		int xLoRight = (int) (((boardSize * 3) + 1) * lg);
	    int xHiRight = (int) (2 * boardSize * lg + (5 * sm) / 4);
	    int xLoLeft = (int) (boardSize * lg + (3 * sm) / 4);
	    int xMid = xLoRight / 2;

	    int yLoPoint = (int) (boardSize * (lg + sm) + sm);
	    int yMidPoint = yLoPoint / 2;

	    int[] xLeft = {0, xMid, xLoLeft};
	    int[] yLeft = {0, yMidPoint, yLoPoint};

	    int[] xTop = {0, xHiRight, xMid};
	    int[] yTop = {0, 0, yMidPoint};
	    int[] xBottom = {xMid, xLoRight, xLoLeft};
	    int[] yBottom = {yMidPoint, yLoPoint, yLoPoint};
	    int[] xRight = {xHiRight, xLoRight, xMid};
	    int[] yRight = {0, yLoPoint, yMidPoint};

	    Polygon left = new Polygon(xLeft, yLeft, 3);
	    Polygon right = new Polygon(xRight, yRight, 3);
	    Polygon top = new Polygon(xTop, yTop, 3);
	    Polygon bottom = new Polygon(xBottom, yBottom, 3);

	    g.setPaint(Color.RED);
	    g.fill(top);
	    g.fill(bottom);

	    g.setPaint(Color.BLUE);
	    g.fill(left);
	    g.fill(right);

	    g.setPaint(Color.BLACK);
	    g.draw(top);
	    g.draw(bottom);
	    g.draw(left);
	    g.draw(right);
	}
	
	public Color getFillColor(int x, int y) {
		return colors[board[y][x]];
	}

	public void playAt(int x, int y){
		if (!isLegalPlay(x, y) && turn == playerTurn || !isLegalPlay(x, y) && turn == p2Turn ) {
			JOptionPane.showMessageDialog(null, "Illegal Move! Play at another Location", "Invalid move!", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		// AI picks a hex space already occupied
		while(board[y][x] != 0){
			x = rand.nextInt(11);
			y = rand.nextInt(11);
		}
		
		board[y][x]=turn;
		repaint();


		if (turn==2) {
			data.set(x, y, Node.BLUE);
			turn--;
			repaint();
		} else {
			data.set(x, y, Node.RED);
			turn++;
			repaint();
		}

		try {
			if(data.checkWin(Player.BLUE)){
				sfx.playWin();
				play = JOptionPane.showOptionDialog(null, "Blue Wins! Would you like to play again?", "Game Over!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, playAgain, playAgain[0]); 
			}
			if(data.checkWin(Player.RED)){
				sfx.playWin();
				play = JOptionPane.showOptionDialog(null, "Red Wins! Would you like to play again?", "Game Over!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, playAgain, playAgain[0]); 
			}

			if(play == 0){
				play = 2;
				board = new int[11][11];
				data = new BoardData();
				gameMode();
				repaint();
			}
			if(play == 1){
				System.exit(0);
			}

		} catch (OperationNotSupportedException e) {
			e.printStackTrace();
		}

	}

	public void update(int x, int y) {
		for (int column = 0; column < hexagons.length; column++) {
			for (int row = 0; row < hexagons[column].length; row++) {
				if (hexagons[column][row].contains(x, y)) {
					sfx.playTileClick();
					playAt(column, row);
				}
			}
		}

		// If playing against AI and isn't player turn
		if(numPlayers == 1 && turn != playerTurn){
			playAt(rand.nextInt(11), rand.nextInt(11));
		}

	}

	// Checks if space is already taken
	private boolean isLegalPlay(int x, int y) {
		return board[y][x]==0;
	}
}

package main.Boards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

public class HexBoard extends JPanel {
	private int boardSize = 11;
	private double sm;
	private double lg;
	
	private BoardData data;
	
	public HexBoard() {
		
	}
	
	public HexBoard(BoardData d) {
		data = d;
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
				drawHex(g2d, x, y, 1);
			}
		}
	}
	
	public void drawHex(Graphics2D g, int column, int row, int color) {
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
	    Color defaultColor = Color.WHITE;
	    return defaultColor;
	}

}

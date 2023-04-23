package main.Boards;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public interface IBoard {
    public void drawHex(Graphics2D g, int column, int row);
    public Polygon calcHexPoly(int column, int row);
    public void drawBorders(Graphics2D g);
    public Color getFillColor(int x, int y);
    public void playAt(int x, int y);
    public void update(int x, int y);
}

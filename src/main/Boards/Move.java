package main.Boards;

public class Move {
    private int color;
    private int x;
    private int y;

    public Move(int color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public int getColor() {
        return this.color;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

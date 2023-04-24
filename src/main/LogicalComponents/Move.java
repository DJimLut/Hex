package main.LogicalComponents;

import main.Players.*;

public class Move {
    private Player player;
    private int x;
    private int y;

    public Move(Player player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

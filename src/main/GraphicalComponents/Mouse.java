package main.GraphicalComponents;

import java.awt.event.*;
import main.Boards.IBoard;

public class Mouse extends MouseAdapter {
    private final IBoard board;

    public Mouse(IBoard board) {
        this.board = board;
    }

    public void mousePressed(MouseEvent e) {
        board.update(e.getX(), e.getY());
    }
}

//This class performs adjacency calculations to determine the first player to complete a
//path of hex tiles (represented as nodes) across the board. It combines the GameBoard and 
//BoardData classes from the Trevorah project. Based on the Trevorah project, it should be 
//connected to graphical components through the GameRunner class.

package main.Boards;
import main.GraphicalComponents.*;
import main.LogicalComponents.Move;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class BoardData {
	private final int RED_BORDER1 = 0;
	private final int BLUE_BORDER1 = 1;
	private final int RED_BORDER2 = 2;
	private final int BLUE_BORDER2 = 3;
	private final int SIZE = 11;
	
	private Point selected;
	
	private HexNode[][] board;
	private AdjMatrix redAdjMatrix;
	private AdjMatrix blueAdjMatrix;
	
	public BoardData() {
		this.board = new HexNode[SIZE][SIZE];
		this.redAdjMatrix = new AdjMatrix((int) Math.pow(SIZE, 2) + 4);
	    this.blueAdjMatrix = new AdjMatrix((int) Math.pow(SIZE, 2) + 4);
	    
	    initBoard();
	    initAdjMatrix();
	}
	
	public BoardData(HexNode[][] board, 
			AdjMatrix redAdjMatrix, AdjMatrix blueAdjMatrix) {	    
	    this.board = board;
	    this.redAdjMatrix = redAdjMatrix;
	    this.blueAdjMatrix = blueAdjMatrix;
	  }
	
	public void initBoard() {
		for (int row = 0; row < SIZE; row++) {
			for (int column = 0; column < SIZE; column++) {
				board[column][row].setColor(Node.WHITE);
			}
		}
	}
	
	public void initAdjMatrix() {
		
		for (int row = 0; row < SIZE; row++) {
			for (int column = 0; column < SIZE; column++) {
				int node = board[column][row].getNodeID();
				ArrayList<Integer> neighbors = getBoardNeighbors(column, row);				
				for (int n : neighbors) {
					blueAdjMatrix.write(node,  n,  AdjMatrix.LINK);
					redAdjMatrix.write(node,  n,  AdjMatrix.LINK);
				}
			}
		}
		
		for (int row = 0; row < SIZE; row++) {
			int leftNeighbor = board[0][row].getNodeID();
			int rightNeighbor = board[SIZE - 1][row].getNodeID();
			blueAdjMatrix.write(BLUE_BORDER1,  leftNeighbor,  AdjMatrix.LINK);
			blueAdjMatrix.write(BLUE_BORDER2,  rightNeighbor,  AdjMatrix.LINK);
		}
		
		for (int column = 0; column < SIZE; column++) {
			int topNeighbor = board[column][0].getNodeID();
			int bottomNeighbor = board[column][SIZE - 1].getNodeID();
			redAdjMatrix.write(RED_BORDER1,  topNeighbor,  AdjMatrix.LINK);
			redAdjMatrix.write(RED_BORDER2,  bottomNeighbor,  AdjMatrix.LINK);
		}
		
		for (int i = 0; i < redAdjMatrix.size(); i++) {
			redAdjMatrix.write(i,  i,  AdjMatrix.LINK);
			blueAdjMatrix.write(i,  i,  AdjMatrix.LINK);
		}
	}
	
	private ArrayList<Integer> getBoardNeighbors(int x, int y) {
		ArrayList<Point> temp = new ArrayList<Point>();
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		
		temp.add(new Point(x - 1, y));
		
		temp.add(new Point(x + 1, y - 1));
		
		temp.add(new Point(x, y - 1));
		
		temp.add(new Point(x + 1, y));
		
		temp.add(new Point(x - 1, y + 1));
		
		temp.add(new Point(x, y + 1));
		
		for (Point n : temp) {
			if (n.x > -1 && n.y > -1 && n.x < SIZE && n.y < SIZE) {
				int nodeIndex = board[n.x][n.y].getNodeID();
				neighbors.add(nodeIndex);
			}
		}
		
		return neighbors;
	}
	
	private ArrayList<Integer> getAdjMatrixNeighbors(int node, int color) {
	    ArrayList<Integer> neighbours = new ArrayList<Integer>();
	    AdjMatrix adjMatrix = null;
	    switch (color) {
	    	case 1:
	    		adjMatrix = redAdjMatrix;
	    		break;
	    	case 2:
	    		adjMatrix = blueAdjMatrix;
	    		break;
	    }

	    for (int i = 0; i < adjMatrix.size(); i++)
	    	if (i != node && adjMatrix.read(node, i) == AdjMatrix.LINK)
	    		neighbours.add(i);
	    return neighbours;

	}
	
	public AdjMatrix getAdjMatrix(int color) {
	    AdjMatrix returnVal;
	    switch (color) {
	      case 1:
	        returnVal = redAdjMatrix.clone();
	        break;
	      case 2:
	        returnVal = blueAdjMatrix.clone();
	        break;
	      default:
	        System.err.println("incorrect color");
	        returnVal = null;
	        break;
	    }
	    return returnVal;
	  }
	
	public boolean makeMove(Move move) {
		//TODO: Write method for a valid move on the board.
		return false;
	}
	
	public ArrayList<Integer> getPath(int start, int target, int color) {
		ArrayList<Integer> tree = new ArrayList<Integer>();
		ArrayList<Integer> parentIndex = new ArrayList<Integer>();
		boolean done = false;
		tree.add(start);
		parentIndex.add(-1);
		
		for (int i = 0; i < tree.size() && !done; i++) {
			int node = tree.get(i);
			if (node == target)
				done = true;
			else {
				ArrayList<Integer> newChildren = getAdjMatrixNeighbors(node, color);
				Collections.shuffle(newChildren);
				for (int child : newChildren) {
					if (!tree.contains(child)) {
						tree.add(child);
						parentIndex.add(i);
					}
				}
			}
		}
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		if (done) {
			int treeIndex = tree.indexOf(target);
			path.add(tree.get(treeIndex));
			int parent = parentIndex.get(treeIndex);
			
			while (parent != -1) {
				path.add(tree.get(parent));
				parent = parentIndex.get(parent);
			}
		}
		
		return path;
	}
	
	public ArrayList<Point> getWinningPath(int color) {
		ArrayList<Integer> nodePath;
		ArrayList<Point> winningPath = new ArrayList<Point>();
		int borderA = 0;
		int borderB = 0;
		
		switch (color) {
		case 1:
			borderA = RED_BORDER1;
			borderB = RED_BORDER2;
			break;
		case 2:
			borderA = BLUE_BORDER1;
			borderB = BLUE_BORDER2;
			break;
		}
		
		nodePath = getPath(borderA, borderB, color);
		
		for (int node : nodePath) {
			Point xy = getXYpos(node);
			if (xy != null)
				winningPath.add(xy);
		}
		
		return winningPath;
	}
	
	public Point getXYpos (int node) {
		if (node < 4)
			return null;
		else {
			node = node - 4;
			int y = node / SIZE;
			int x = node % SIZE;
			return new Point(x,y);
		}
	}
	
	public boolean checkWin(int player) {
		boolean returnVal = false;
		switch (player) {
		case 1:
			if (redAdjMatrix.read(RED_BORDER1,  RED_BORDER2) == AdjMatrix.LINK)
				returnVal = true;
			break;
		case 2:
			if (blueAdjMatrix.read(BLUE_BORDER1,  BLUE_BORDER2) == AdjMatrix.LINK)
				returnVal = true;
			break;
		default:
			System.err.println("incorrect color");
			break;
		}
		return returnVal;
	}
	
	public HexNode getNode(int x, int y) {
		return board[x][y];
	}
	
	public void set(int x, int y, Node color) {
		int node = board[x][y].getNodeID();
		board[x][y].setColor(color);
		
		switch (color) {
		case RED:
			redAdjMatrix.bypassAndRemoveNode(node);
			blueAdjMatrix.wipeNode(node);
			break;
		case BLUE:
			redAdjMatrix.wipeNode(node);
			blueAdjMatrix.bypassAndRemoveNode(node);
			break;
		default:
			System.err.println("incorrect color");
			break;
		}
	}	
	
	public int get(int x, int y) {
		return -1;
	}
	
	public Point getSelected() {
		return selected;
	}
	
	public void setSelected(Point selected) {
		this.selected = selected;
	}
	
	public void setSelected(int x, int y) {
		this.setSelected(new Point(x, y));
	}

}


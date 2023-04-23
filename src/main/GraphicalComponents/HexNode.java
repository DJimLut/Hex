//This class represents hex tiles as nodes with information about 
//tile status (occupied by red, blue, etc.) and an ID which helps
//locate a specific tile in the graph. It's based on the HexLocation
//class of the Trevorah project.

package main.GraphicalComponents;

public class HexNode {	
	private int nodeID;
	private Node color = Node.WHITE;
	
	public HexNode(int nodeID) {
		this.nodeID = nodeID;
	}
	
	public HexNode(int nodeID, Node color) {
		this.nodeID = nodeID;
		this.color = color;
	}
	
	public int getNodeID() {
		return nodeID;
	}
	
	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}
	
	public Node getColor() {
		return color;
	}
	
	public void setColor(Node color) {
		this.color = color;
	}	

}

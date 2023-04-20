//This class represents hex tiles as nodes with information about 
//tile status (occupied by red, blue, etc.) and an ID which helps
//locate a specific tile in the graph. It's based on the HexLocation
//class of the Trevorah project.

package main.Boards;

public class HexNode {
	
	private int nodeID;
	private int value;
	//For red player, node value = 1; Blue player = 2.
	
	public HexNode(int nodeID) {
		this.nodeID = nodeID;
		this.value = 0;
	}
	
	public HexNode(int nodeID, int value) {
		this.nodeID = nodeID;
		this.value = value;
	}
	
	public int getNodeID() {
		return nodeID;
	}
	
	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}	

}

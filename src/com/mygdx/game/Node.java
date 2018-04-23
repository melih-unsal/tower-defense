package com.mygdx.game;

import java.util.ArrayList;

public class Node {
	Position pos;
	Node parent;
	ArrayList<Node> nb;
	double fCost;
	double gCost;
	double hCost;
	
	public Node(Position pos, Node parent, double g, double h ) {
		this.pos = pos;
		this.parent = parent;
		gCost = g;
		hCost = h;
		fCost = g + h;
		nb = new ArrayList<Node>();
	}
	public ArrayList<Node> getNb(){
		return nb;
	}
	
    public void calculateCost(Position pos){
    	int diffX = this.pos.getX() - pos.getX() ;
    	int diffY = this.pos.getY() - pos.getY();
    	hCost = (double) Math.sqrt(diffX * diffX + diffY * diffY);
    	fCost = hCost + gCost;
    }
    public String toString() {
    	return pos.toString();
    }

	
}

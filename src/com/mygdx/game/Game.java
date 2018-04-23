package com.mygdx.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Game {
	Comparator<Node> sorter = new Comparator<Node>() {
		@Override
		public int compare(Node arg0, Node arg1) {
			if(arg0.fCost > arg1.fCost) {
				return 1;
			}
			if(arg0.fCost < arg1.fCost) {
				return -1;
			}
			return 0;
		}
	};
		public Game() {
			
		}
		
		public ArrayList<Node> pathFinder(Position start, Position end, rectGraph rectangle){
		ArrayList<Node> open = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();
		Node current = rectangle.getNode(start.getX(), start.getY());
		open.add(current);
		while(open.size() > 0) {
			Collections.sort(open, sorter);
			current = open.get(0);
			if(current.pos.getX() == end.getX() && current.pos.getY() == end.getY()) {
				ArrayList<Node> shortpath = new ArrayList<Node>();
				while(current.parent != null) {
					shortpath.add(current);
					current = current.parent;
				}
				open.clear();
				closed.clear();
				return shortpath;
			}
			open.remove(current);
			closed.add(current);
			for(Node n : current.nb) {
				open.add(n);
			}
			Collections.sort(open, sorter);
			open.get(0).parent = current;
			
			
		}
		return null;
	}
	public double euclid1(Position current, Position target){
	    	int diffX = current.getX() - target.getX();
	    	int diffY = current.getY() - target.getY();
	    	return  Math.sqrt(diffX * diffX + diffY * diffY);		
	    }
    /*public void findPath() {
    	Game game = new Game();
    	Position start = new Position(0,0);
    	Position target = new Position(12,12);
    	rectGraph rectangle = new rectGraph(50,50);
    	rectangle.neighbor();
    	rectangle.euclid(target);
    	//System.out.print(game.pathFinder(start, target, rectangle));
    }*/
    
}
	
package com.mygdx.game;

public class Position {
    public int X;
    public int Y;
    
    public Position(int x, int y) {
    	X = x;
    	Y = y;
    }
    public Position(Position pos) {
    	X = pos.getX();
    	Y = pos.getY();
    }
    
    
    public int getX(){
    	return X;
    }

    public int getY(){
    	return Y;
    }
    
    public void setX(int x){
    	X=x;
    }
    
    public void setY(int y){
    	Y=y;	
    }
    
    public String toString() {
    	String str = "(" + X +"," + Y + ")";
    	return str;
    }

}

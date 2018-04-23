package com.mygdx.game;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class enemy extends AnimatedActor {
	boolean azalt=true;
	boolean slowed=false;
	public TextureRegion region;
	public Rectangle boundary;
	ArrayList<Node> path;
	public float hp=100;
	public float fixhp=100;
	Node nextNode;
	int ww=40,hh=40;
	Square[][] squares;
	int coordinateX,coordinateY,tempCoorX,tempCoorY;
	int[] ivalues={-1,-1,-1,-1};
	ArrayList<ArrayList<Node>> paths=PlayGame.paths;
	int pathnumber=0;
	int i=ivalues[0];
	int length=paths.get(pathnumber).size()-1;
	boolean visible=true;
	double constant=1.0;
	boolean writescore=true;
	public enemy(ArrayList<ArrayList<Node>> paths) {
		// TODO Auto-generated constructor stub
		super();
		region=new TextureRegion();
		boundary=new Rectangle();
		velocityX=20;
		velocityY=0;
		hp=100;
		this.paths=paths;
		squares=new Square[16][12];
		seperateSquares();
		
	}

	

	public void draw(Batch b,float parentAlpha){
		super.draw(b, parentAlpha);
	}
	public void seperateSquares(){
		for(int i=0;i<squares.length;i++)
			for(int j=0;j<squares[0].length;j++)
				squares[i][j]=new Square(i, j, 40, 40);
	}
	public void act(float dt){
		if (length - i < paths.get(pathnumber).size()) {
		if(i<length){
		if(paths.get(pathnumber).get(length-i).pos.getX()<paths.get(pathnumber).get(length-i-1).pos.getX()){
			velocityY=-(int)(40*constant);
			velocityX=0;
			//System.out.println("d1");
		}		
		 if(paths.get(pathnumber).get(length-i).pos.getY()>paths.get(pathnumber).get(length-i-1).pos.getY()){
			velocityX=-(int)(40*constant);
			velocityY=0;
			//System.out.println("d4");
		}
		 if(paths.get(pathnumber).get(length-i).pos.getX()>paths.get(pathnumber).get(length-i-1).pos.getX()){
			velocityY=(int)(40*constant);
			velocityX=0;
			//System.out.println("d2");
		}
		 if(paths.get(pathnumber).get(length-i).pos.getY()<paths.get(pathnumber).get(length-i-1).pos.getY()){
			velocityX=(int)(40*constant);
			velocityY=0;
			//System.out.println("d3");
		}

		super.act(dt);
		}
		}
		boundary=new Rectangle(getX(), getY(), getWidth(), getHeight());
		tempCoorX=coordinateX;
		tempCoorY=coordinateY;
		determineSquare();
		if(i<=length)
		if(tempCoorX!=coordinateX || tempCoorY!=coordinateY)
			i++;
		if(i>=length && pathnumber<3){
			pathnumber++;
			i=0;
			length=paths.get(pathnumber).size()-1;
		}
		if(pathnumber==3 && i==length){
			if(azalt)
			PlayGame.lives--;
			azalt=false;
			this.setVisible(false);
			visible=false;
		}
	}
	public void determineSquare(){
		
		for(int i=0;i<squares.length;i++){
			for(int j=0;j<squares[0].length;j++){
				Rectangle r=squares[i][j].getBoundingRectangle();
				Rectangle s=boundary;
				if(cont(r,s))
				{
					coordinateX=i;
					coordinateY=j;
				
				}
			}
		}

	}
	public boolean cont(Rectangle r,Rectangle s){
	
		if(r.getX()<=s.getX() && r.getX()+r.getWidth()>=s.getX()+s.getWidth())
			if((r.getY()<=s.getY()) && (r.getY()+r.getHeight()>=s.getY()+s.getHeight()))
				return true;
		
		return false;
	}
	
	
	
}

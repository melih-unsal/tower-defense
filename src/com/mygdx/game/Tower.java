package com.mygdx.game;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Tower extends GameActor{
	ArrayList<enemy> enemyArray;
	enemy hedef;
	float menzil;
	int damage;
	boolean vurulacak=false;
	float harm=5;
	boolean beaten=false;
	boolean setpos=true;
	public Tower(ArrayList<enemy> enemyArray,float menzil) {
		// TODO Auto-generated constructor stub
		this.enemyArray=enemyArray;
		this.menzil=menzil;
	}

	public void draw(Batch b,float parentAlpha){
		 super.draw(b, parentAlpha);

		
	}
	public void controllenemy(){
		for(int i=0;i<enemyArray.size();i++){
			if(getDistance(enemyArray.get(i))<=menzil && enemyArray.get(i).hp>0.5f){
				hedef=enemyArray.get(i);
				 /*PlayGame.shape.begin(ShapeType.Line);
				 PlayGame.shape.setColor(Color.RED);
				 PlayGame.shape.line(getX()+getWidth()/2, getY()+getHeight()/2,
						 hedef.getX()+hedef.getWidth()/2, hedef.getY()+hedef.getHeight()/2);
				 PlayGame.shape.end();*/
				
				 //if(hedef.hp>0.5f)
				 //hedef.hp-=harm;
				 return;
			}
				
		}
		hedef=null;
	}
	
	
	
	public boolean equals(Object obj){
		if(obj instanceof Tower){
			Tower t=(Tower)obj;
			if(t.getX()==getX() && t.getY()==getY())
				return true;
		
		}
		return false;
	}
}

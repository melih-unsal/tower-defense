package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class Square {
	float x,y;
	float w,h;
	public Square(float x,float y,float w,float h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	public Rectangle getBoundingRectangle(){
		return new Rectangle(x*w, y*h, w, h);
	}
}

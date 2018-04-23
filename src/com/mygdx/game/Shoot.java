package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Shoot extends PhysicsActor {
	public float speed;
	float time=1;
	boolean exit;
	float tan_x;
	float tan_y;
	boolean vurulacak;
	boolean controlshouted;
	enemy en;
	Tower t;
	public Shoot(float speed,String file) {
		super();
		this.speed=speed;
		exit=false;
		vurulacak=true;
		controlshouted=true;
		en=null;
		t=null;
		region=new TextureRegion();
		Texture t=new Texture(file);
		setWidth(t.getWidth());
		setHeight(t.getHeight());
		region.setTexture(t);
	}
	public boolean isShouted( enemy hedef){
		if(hedef!=null){
			if(getDistance(hedef)<2)
				return true;
			
		}
		return false;
	}
	public boolean intersect(enemy e){
		int xpay=9;
		int ypay=9;
		float x1,y1,x2,y2;
		float a1,b1,a2,b2;
		x1=e.getX()+e.getOriginX()+xpay;
		x2=x1+e.getWidth()-2*xpay;
		y1=e.getY()+e.getOriginY()+ypay;
		y2=y1+e.getHeight()-2*ypay;
		
		a1=getX()+getOriginX();
		a2=a1+getWidth();
		b1=getY()+getOriginY();
		b2=b1+getHeight();
		
		if(a1<x1 && x1<a2)
			if(b1<y1 && y1<b2)
				return true;
		
		if(a1<x1 && x1<a2)
			if(b1<y2 && y2<b2)
				return true;
		
		if(a1<x2 && x2<a2)
			if(b1<y1 && y1<b2)
				return true;
		if(a1<x2 && x2<a2)
			if(b1<y2 && y2<b2)
				return true;
		return false;
		
		
		
	}
	
	

}

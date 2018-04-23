package com.mygdx.game;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;

import java.util.ArrayList;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameActor extends Actor   {
	public TextureRegion region;
	private ArrayList<? extends GameActor> parentList;
	Rectangle boundary;
	public GameActor() {
		// TODO Auto-generated constructor stub
		super();
		region=new TextureRegion();
		boundary=null;
		parentList=null;
		
	}
	public void setParentList(ArrayList<? extends GameActor> p1){
		parentList=p1;
	}
	
	public void destroy(){
		remove();
		if(parentList!=null)
			parentList.remove(this);
	}
	public void setTexture(Texture t){
		int w=t.getWidth();
		int h=t.getHeight();
		setWidth(w);
		setHeight(h);
		region.setRegion(t);
		region=new TextureRegion(t);
	}
	/*public void setRectangleBoundary(){
		float w=getWidth();
		float h=getHeight();
		float[] vertices={0,0,w,0,w,h,0,h};
		boundingPolygon=new Polygon(vertices);
		boundingPolygon.setOrigin(getOriginX(), getOriginY());
	}
	public void setEllipseBoundary(){
		int n=8;
		float w=getWidth();
		float h=getHeight();
		float[] vertices=new float[2*n];
		for(int i=0;i<n;i++){
			float t=i*6.28f;
			vertices[2*i]=w/2*MathUtils.cos(t)+w/2;
			vertices[2*i+1]=h/2*MathUtils.sin(t)+h/2;
			
		}
		
		
		boundingPolygon=new Polygon(vertices);
		boundingPolygon.setOrigin(getOriginX(), getOriginY());
	}
	public Polygon getBoundingPolygon(){
		boundingPolygon.setPosition(getX(), getY());
		boundingPolygon.setRotation(getRotation());
		System.out.println(boundingPolygon.getX()+",  "+boundingPolygon.getY());
		return boundingPolygon;
		
		
	}
	*/
	public Rectangle getBoundingRectangle(){
		if(boundary!=null)
		boundary.set(getX(), getY(), getWidth(), getHeight());
		return boundary;
	}
	public void act(float dt){
		super.act(dt);
		//moveBy(velocityX*dt,velocityY*dt);
	}
		public void draw(Batch b,float parentAlpha){
			Color c=getColor();
			b.setColor(c.r,c.g,c.b,c.a);
			if(region.getRegionWidth()!=0)
			if(isVisible())
				b.draw(region,getX(),getY(), getOriginX(), getOriginY(),
						getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			
		}
		public float getDistance(enemy e){
			return (float) Math.sqrt(Math.pow(getDistanceX(e),2)+Math.pow(getDistanceY(e),2));
		}
		public float getDistanceX(enemy e){
			return e.getX()+ (e.getWidth()/2)-(getX()+getWidth()/2);
		}
		public float getDistanceY(enemy e){
			return e.getY()+ (e.getHeight()/2)-(getY()+getHeight()/2);
		}public void copy(GameActor original){
			this.region=new TextureRegion(original.region);
			
			setPosition(original.getX(), original.getY());
			setOriginX(original.getOriginX());
			setOriginY(original.getOriginY());
			setWidth(original.getWidth());
			setHeight(original.getHeight());
			setColor(original.getColor());
			setVisible(original.isVisible());
		}
		public GameActor clone(){
			GameActor newbie=new GameActor();
			newbie.copy(this);
			return newbie;
		}
		
		
	}
	




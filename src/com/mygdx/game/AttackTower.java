package com.mygdx.game;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;

public abstract class AttackTower extends Tower {
	private boolean move;
	boolean controlshouted=true;
	int renderer=50,n=0;
	public AttackTower(ArrayList<enemy> enemyArray, float menzil) {
		super(enemyArray, menzil);
		move=false;
	}
	
	public abstract void shoot(ArrayList<enemy> arr);
	public void draw(Batch b , float parentAlpha){
		super.draw(b, parentAlpha);
			
		}
			
			
				
	}
	//(getDistanceX(hedef)-hedef.velocityX*shoot.time)/shoot.time, getDistanceY(hedef)/shoot.time



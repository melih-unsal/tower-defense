package com.mygdx.game;
import java.util.ArrayList;

public abstract class AuraTower extends Tower {

	public AuraTower(ArrayList<enemy> enemyArray, float menzil) {
		super(enemyArray, menzil);
		// TODO Auto-generated constructor stub
		damage=0;
	}
	abstract void applyAura();
	public void controllenemy(){
		
	}
	
	
}

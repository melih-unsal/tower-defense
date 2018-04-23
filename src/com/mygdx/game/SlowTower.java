package com.mygdx.game;
import java.util.ArrayList;

public class SlowTower extends AuraTower {

	public SlowTower(ArrayList<enemy> enemyArray, float menzil) {
		super(enemyArray, menzil);
		// TODO Auto-generated constructor stub
	}

	@Override
	void applyAura() {
		// TODO Auto-generated method stub
		for(enemy e:enemyArray){
			if(getDistance(e)<menzil && (!e.slowed)){
				System.out.println("asfdzsgçmgd");
				e.constant=0.4;
				e.slowed=true;
			}
			else if(e.slowed &&getDistance(e)>menzil){
				e.constant=1;
				e.slowed=false;
			
	}

}
	}
}

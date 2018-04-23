package com.mygdx.game;

import java.util.ArrayList;
import java.util.Arrays;

public class rectGraph {
	int width;
	int height;
	Node[][] graph;
	ArrayList<int[]> coordinates=new ArrayList<int[]>();
    public rectGraph(int w, int h,ArrayList<Tower> towers) {
    	for(Tower t:towers){
    		int[] s={w-1-(int)(t.getY()/40),(int)(t.getX()/40)};
    		coordinates.add(s);
    		}
    	width = w;
    	height = h;
    	graph = new Node[w][h];
    	for(int i = 0; i < w; i++){
    		for(int j = 0; j < h; j++){
    			Position pos = new Position(i,j);
    			graph[i][j] = new Node(pos, null, 0, 0);
    			
    		}
 		}
    }
    public boolean control(int[] arr,int i,int j){
    	if(i==arr[0] && j== arr[1])
    		return true;
    	else
    		return false;
    }
    public boolean controlArrayList(ArrayList<int[]> list,int i,int j){
    	for(int[] a :list)
    		if(control(a,i,j))
    			return false;
    	return true;
    }
    public int getWidth(){
    	return width;
    }
    
    public int getHeigth(){
    	return height;
    }
    
    public Node[][] getGraph(){
    	return graph;
    }
    
    public Node getNode(int x, int y) {
    	return graph[x][y];
    }
    public void setNodeNull(int x,int y){
    	graph[x][y].parent=null;
    }
    public void neighbor(int w, int h,ArrayList<Tower> towers){
    	for(Tower t:towers){
    		int[] s={w-1-(int)(t.getY()/40),(int)(t.getX()/40)};
    		coordinates.add(s);
    		}
		for(int i = 0; i < getWidth(); i++){
			for(int j = 0; j < getHeigth(); j++){
    			
				if(i == 0 && j == 0){
					if(controlArrayList(coordinates,i,j+1))
					graph[i][j].getNb().add(graph[i][j + 1]);
					if(controlArrayList(coordinates,i+1,j))
					graph[i][j].getNb().add(graph[i + 1][j]);
				}
				else if(i == 0 && j == getHeigth() - 1){
					if(controlArrayList(coordinates,i,j-1))
					graph[i][j].getNb().add(graph[i][j - 1]);
					if(controlArrayList(coordinates,i+1,j))
					graph[i][j].getNb().add(graph[i + 1][j]);
				}
			
				else if(i == getWidth() - 1 && j == getHeigth() - 1){
					if(controlArrayList(coordinates,i-1,j))
					graph[i][j].getNb().add(graph[i - 1][j]);
					if(controlArrayList(coordinates,i,j-1))
					graph[i][j].getNb().add(graph[i][j - 1]);
				}
				else if(i == getWidth() - 1 && j == 0){
					if(controlArrayList(coordinates,i-1,j))
					graph[i][j].getNb().add(graph[i - 1][j]);
					if(controlArrayList(coordinates,i,j+1))
					graph[i][j].getNb().add(graph[i][j + 1]);
				}
				else if(i == 0 && (j > 0 && j < getHeigth() - 1)){
					if(controlArrayList(coordinates,i+1,j))
					graph[i][j].getNb().add(graph[i + 1][j]);
					if(controlArrayList(coordinates,i,j-1))
					graph[i][j].getNb().add(graph[i][j - 1]);
					if(controlArrayList(coordinates,i,j+1))
					graph[i][j].getNb().add(graph[i][j + 1]);
				}
				else if((i > 0 && i < getWidth() - 1) && j == getHeigth() - 1){
					if(controlArrayList(coordinates,i-1,j))
					graph[i][j].getNb().add(graph[i - 1][j]);
					if(controlArrayList(coordinates,i+1,j))
					graph[i][j].getNb().add(graph[i + 1][j]);
					if(controlArrayList(coordinates,i,j-1))
					graph[i][j].getNb().add(graph[i][j - 1]);
				}
				else if(i == getWidth() - 1 && (j > 0 && j < getHeigth() - 1)){
					if(controlArrayList(coordinates,i,j-1))
					graph[i][j].getNb().add(graph[i][j - 1]);
					if(controlArrayList(coordinates,i,j+1))
					graph[i][j].getNb().add(graph[i][j + 1]);
					if(controlArrayList(coordinates,i-1,j))
					graph[i][j].getNb().add(graph[i - 1][j]);
				}
				else if(i > 0 && i < getWidth() - 1 && j == 0 ){
					if(controlArrayList(coordinates,i-1,j))
					graph[i][j].getNb().add(graph[i - 1][j]);
					if(controlArrayList(coordinates,i+1,j))
					graph[i][j].getNb().add(graph[i + 1][j]);
					if(controlArrayList(coordinates,i,j+1))
					graph[i][j].getNb().add(graph[i][j + 1]);
				}
				else{
					if(controlArrayList(coordinates,i+1,j))
					graph[i][j].getNb().add(graph[i + 1][j]);
					if(controlArrayList(coordinates,i-1,j))
					graph[i][j].getNb().add(graph[i - 1][j]);
					if(controlArrayList(coordinates,i,j+1))
					graph[i][j].getNb().add(graph[i][j + 1]);
					if(controlArrayList(coordinates,i,j-1))
					graph[i][j].getNb().add(graph[i][j - 1]);
				
				}
			}
		}
    }
    public void euclid(Position target){
    	for(int i = 0; i < graph.length; i++) {
    		for(int j = 0; j < graph[i].length; j++) {
    			if(graph[i][j]!=null)
    			graph[i][j].calculateCost(target);
    		}
    	}
    }
}

package graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;

import graphUtil.Vector2d;
import graphUtil.nodes.GridNode;
import map.TileType;
import map.generation.mazes.RecursiveMaze;
import solvers.AStar;

public class Base extends JFrame implements Runnable  {	
	
	private Thread mainGame;
	private int tileSize, width,height;
	private Surface surface;
	private Thread test;
	RecursiveMaze level;
	ArrayList<Vector2d> path;
	
    public Base(int tileSize,int width, int height,RecursiveMaze maze) {
    	this.tileSize=tileSize;
    	this.width=width;
    	this.height=height;
    	this.level = maze;
        initUI(tileSize,width,height,maze);
       
    }

    private void initUI(final int tileSize,int width, int height,RecursiveMaze maze) {
    	surface = new Surface(tileSize,width,height,maze.getNodeList());    	
    	
    	surface.addMouseMotionListener(new MouseAdapter() {
    		 public void mouseMoved(MouseEvent e) {    			 
    			 	setTitle("X : " + (e.getX()/tileSize) + " Y : " + e.getY()/tileSize);    		      
    		    }
    		 
    	});
    	
        surface.addMouseListener(new MouseAdapter() {
   		 public void mouseClicked(MouseEvent e) {    			 
			 if(e.getButton()== MouseEvent.BUTTON3) {
				 Vector2d pos = new Vector2d(e.getX()/tileSize,e.getY()/tileSize);
				 for(GridNode node: level.getNodeList()){
					 if(node.postion.sameVector(pos)){
						 if( node.tile== TileType.WALL) {
							 node.tile= TileType.CLEAR; 
						 }
						 else {
							 node.tile= TileType.WALL;
						 }						 
						 path = AStar.aStarSearch(level.getStart(),level.getEnd());						 
						 if(null != path){							 
    	    				 surface.clearPath(); 
    	    				 surface.updateTile(node.postion,node.tile.color);
    	    				 surface.addPath(path);    	    				 
    	    			 }
						 else{
							 node.tile= node.tile== TileType.WALL ? TileType.CLEAR : TileType.WALL;							 
						 }
						
    					 break;
					 }
					 
				 }
			 }
			 if(e.getButton()== MouseEvent.BUTTON1) {
				 Vector2d newEnd = new Vector2d(e.getX()/tileSize,e.getY()/tileSize);
    			 Vector2d oldEnd = new Vector2d(level.end.postion);    			
    			 
    			 for(GridNode node: level.getNodeList()){
    				 if(node.postion.sameVector(newEnd)){
    					 if(node.tile != TileType.WALL){
	    	    			 path = AStar.aStarSearch(level.getStart(),node);
	    	    			 if(null != path){
	    	    				 surface.clearPath();
	    	    				 level.end.tile=TileType.CLEAR;
		    					 node.tile=TileType.END;
		    					 level.end=node;
		    					 surface.moveEnd(oldEnd, newEnd);	    	    				
	    	    				 surface.addPath(path);
	    	    			 }	    	    			
	    					 break;
    					 }
    				 }
    			 }
			 }	
   		 }
        });    	
    	add(surface);    	
    }
    
    public void addPath(ArrayList<Vector2d> path){
    	this.path = path;
    	Collections.reverse(path);
    	test = new Thread(this,"Display");
    	test.start();
    }

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			surface.addPath(path);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	
		
	
}

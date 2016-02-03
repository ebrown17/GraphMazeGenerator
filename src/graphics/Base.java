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
    			 surface.clearPath();    			 
    			 Vector2d newEnd = new Vector2d(e.getX()/tileSize,e.getY()/tileSize);
    			 Vector2d oldEnd = new Vector2d(level.end.postion);
    			 level.end.tile=TileType.CLEAR;
    			 for(GridNode node: level.getNodeList()){
    				 if(node.postion.sameVector(newEnd)){
    					 node.tile=TileType.END;
    					 level.end=node;
    					 
    					 break;
    				 }
    			 }
    			 surface.moveEnd(oldEnd, newEnd);
    			 path = AStar.aStarSearch(level.getStart(),level.getEnd());
    			 
    			 surface.addPath(path);
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

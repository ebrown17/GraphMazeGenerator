package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import graphUtil.Vector2d;
import graphUtil.nodes.GridNode;

public class Surface extends JPanel {
	
	private int tileSize,width, height;
	private ArrayList<GridNode> maze;
	private ArrayList<Vector2d> path = new  ArrayList<Vector2d>();
	
	public Surface(int tileSize,int width, int height,ArrayList<GridNode> maze){		
		this.tileSize=tileSize;
		this.width=width;
		this.height=height;
		this.maze = maze;		
	}
	
	
	private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;      
        
        for(GridNode node : maze){
        	g2d.setColor(node.tile.color);
    		g2d.fillRect((node.postion.x*tileSize), (node.postion.y*tileSize),tileSize,tileSize);    			
        }
    }

	public void addPath(ArrayList<Vector2d> path){
		this.path= path;
		drawPath();
	}
	
	public void updateTile(Vector2d point, Color color){
		Graphics2D g2d =(Graphics2D) getGraphics();
		g2d.setColor(color);
		g2d.fillRect((point.x*tileSize), (point.y*tileSize),tileSize,tileSize);
		
	}
	private void drawPath(){
		Graphics2D g2d =(Graphics2D) getGraphics();
		
		 for(Vector2d point : path){
	        	g2d.setColor(Color.cyan);
				g2d.fillRect((point.x*tileSize), (point.y*tileSize),tileSize,tileSize);
	     }
	}
	public void clearPath(){
		clearPath(getGraphics());
	}
	public void moveEnd(Vector2d old, Vector2d newEnd){
		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect((old.x*tileSize), (old.y*tileSize),tileSize,tileSize);
		
		g2d.setColor(Color.RED);
		g2d.fillRect((newEnd.x*tileSize), (newEnd.y*tileSize),tileSize,tileSize);
	}
	private void clearPath(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		for(Vector2d point : path){
        	g2d.setColor(Color.white);
			g2d.fillRect((point.x*tileSize), (point.y*tileSize),tileSize,tileSize);
        }
		
		 g2d.dispose();
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        doDrawing(g);
    }
	
}

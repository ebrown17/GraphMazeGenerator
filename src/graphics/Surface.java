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
		//System.out.println(maze.size());
	}
	
	
	private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;      
        
        for(GridNode node : maze){
        	
        	
        	
        	//if(node.getState().equals("S")){
				g2d.setColor(node.tile.color);
    			g2d.fillRect((node.postion.x*tileSize), (node.postion.y*tileSize),tileSize,tileSize);
    			/*g2d.setColor(Color.black);
    			g2d.drawRect(node.x*tileSize, node.y*tileSize, tileSize, tileSize);*/
        }		
			/*}
        	else if(node.getState().equals("X")){
        		g2d.setColor(Color.RED);
    			g2d.fillRect((node.x*tileSize), (node.y*tileSize),tileSize,tileSize);    			
    			g2d.setColor(Color.black);
    			g2d.drawRect(node.x*tileSize, node.y*tileSize, tileSize, tileSize);
        	}
        	else if(node.getState().equals("I")){
        		g2d.setColor(Color.BLACK);
    			g2d.fillRect((node.x*tileSize), (node.y*tileSize),tileSize,tileSize);
        	}
        	else if(node.getState().equals(" ")){
        		g2d.setColor(Color.WHITE);
    			g2d.fillRect((node.x*tileSize), (node.y*tileSize),tileSize,tileSize);
        	}
        	else if(node.getState().equals("o")){
        		g2d.setColor(Color.CYAN);
    			g2d.fillRect((node.x*tileSize), (node.y*tileSize),tileSize,tileSize);
        	}*/
        	
        //	g2d.setColor(Color.black);
			//g2d.drawRect(node.x*tileSize, node.y*tileSize, tileSize, tileSize);
			//g2d.drawString(""+(node.x)+","+(node.y), node.x*tileSize+(tileSize/4), node.y*tileSize+(tileSize/2)+(tileSize/6));
        	
        	/*else{
				g2d.setColor(Color.black);
    			g2d.fillRect((x*tileSize), (y*tileSize),tileSize,tileSize);
			}*/
        
        
      /*  for(int x=0;x<width/tileSize;x++){
			for(int y=0;y<height/tileSize;y++){
				
				if(test[x][y]==1){
					g2d.setColor(Color.white);
	    			g2d.fillRect((x*tileSize), (y*tileSize),tileSize,tileSize);
				}else{
					g2d.setColor(Color.black);
	    			g2d.fillRect((x*tileSize), (y*tileSize),tileSize,tileSize);
				}				
			}
		}*/
       
        //g2d.dispose();
    }

	public void addToPath(Vector2d point){
		this.path.add(point);
	}
	
	public void addPath(ArrayList<Vector2d> path){
		this.path= path;
		drawPath();
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

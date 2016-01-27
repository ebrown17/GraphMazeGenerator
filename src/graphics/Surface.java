package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import graphUtil.GraphNode;

public class Surface extends JPanel {
	
	private int tileSize,width, height;
	private ArrayList<GraphNode> maze;
	
	public Surface(int tileSize,int width, int height,ArrayList<GraphNode> maze){
		
		this.tileSize=tileSize;
		this.width=width;
		this.height=height;
		this.maze = maze;
		
	}
	
	
	private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        int wSpacer = (width%tileSize)/2;
        int hSpacer = (height%tileSize)/2;
        
       
        
        for(GraphNode node : maze){
        	
        	if(node.getState().equals("S")){
				g2d.setColor(Color.GREEN);
    			g2d.fillRect((node.x*tileSize), (node.y*tileSize),tileSize,tileSize);
    			g2d.setColor(Color.black);
    			g2d.drawRect(node.x*tileSize, node.y*tileSize, tileSize, tileSize);
    			
			}
        	else if(node.getState().equals("X")){
        		g2d.setColor(Color.RED);
    			g2d.fillRect((node.x*tileSize), (node.y*tileSize),tileSize,tileSize);
    			System.out.println(node.x + " " + node.y);
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
        	}
        	
        //	g2d.setColor(Color.black);
			//g2d.drawRect(node.x*tileSize, node.y*tileSize, tileSize, tileSize);
			//g2d.drawString(""+(node.x)+","+(node.y), node.x*tileSize+(tileSize/4), node.y*tileSize+(tileSize/2)+(tileSize/6));
        	
        	/*else{
				g2d.setColor(Color.black);
    			g2d.fillRect((x*tileSize), (y*tileSize),tileSize,tileSize);
			}*/
        }
        
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
        
      
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        doDrawing(g);
    }
	
}

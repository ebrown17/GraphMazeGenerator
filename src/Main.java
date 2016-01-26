import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import graphUtil.GraphNode;
import maze.RecursiveMaze;
import solvers.AStar;

public class Main {
	
	private static final int TILESIZE = 10;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	
	static int row = WIDTH/TILESIZE;
	static int column = HEIGHT/TILESIZE;
	static ArrayList<GraphNode> maze;
	
	public static void main(String args[]){	
		
		//System.out.println(row + " " + column);
		
		maze = new AStar(new RecursiveMaze(row,column).getMaze()).getMaze();		
		printLevel(maze);		
		
		 SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Base base = new Base(TILESIZE,WIDTH,HEIGHT,maze);
					base.setVisible(true);
				}
			});	        
	    
	
	}
	
	public static void printLevel(ArrayList<GraphNode> maze){
		int count =1;
		for(GraphNode node: maze){			
			System.out.print(node.getState() + "");
			if(count % row == 0) System.out.println("");
			count++;
		}	
	}
}

class Base extends JFrame {	
	
    public Base(int tileSize,int width, int height,ArrayList<GraphNode> maze) {

        initUI(tileSize,width,height,maze);
    }

    private void initUI(final int tileSize,int width, int height,ArrayList<GraphNode> maze) {
    	Surface test = new Surface(tileSize,width,height,maze);
    	
    	test.addMouseMotionListener(new MouseAdapter() {
    		 public void mouseMoved(MouseEvent e) {    			 
    			 	setTitle("X : " + (e.getX()/tileSize) + " Y : " + e.getY()/tileSize);    		      
    		    }
    	});
    	
        add(test);
        
        pack();               
        getContentPane().setPreferredSize(new Dimension(width,height));
        setTitle("Practice");
        pack();        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
    }
}

class Surface extends JPanel {
	
	private int tileSize,width, height;
	private ArrayList<GraphNode> maze;
	private int[][] test;
	public Surface(int tileSize,int width, int height,ArrayList<GraphNode> maze){
		
		this.tileSize=tileSize;
		this.width=width;
		this.height=height;
		this.maze = maze;
		this.test = new int[width/tileSize][height/tileSize];
		
		for(int x=0;x<width/tileSize;x++){
			for(int y=0;y<height/tileSize;y++){
				test[x][y]=(int)(Math.random()*2);
				//System.out.print(test[x][y]);
			}
		}
	}
	
	
	private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        int wSpacer = (width%tileSize)/2;
        int hSpacer = (height%tileSize)/2;
        
       
        
        for(GraphNode node : maze){
        	
        	if(node.getState()=="S"){
				g2d.setColor(Color.GREEN);
    			g2d.fillRect((node.x*tileSize), (node.y*tileSize),tileSize,tileSize);
    			System.out.println(node.x + " " + node.y);
			}
        	else if(node.getState()=="X"){
        		g2d.setColor(Color.RED);
    			g2d.fillRect((node.x*tileSize), (node.y*tileSize),tileSize,tileSize);
        	}
        	else if(node.getState()=="I"){
        		g2d.setColor(Color.BLACK);
    			g2d.fillRect((node.x*tileSize), (node.y*tileSize),tileSize,tileSize);
        	}
        	else if(node.getState()==" "){
        		g2d.setColor(Color.WHITE);
    			g2d.fillRect((node.x*tileSize), (node.y*tileSize),tileSize,tileSize);
        	}
        	else if(node.getState()=="o"){
        		g2d.setColor(Color.CYAN);
    			g2d.fillRect((node.x*tileSize), (node.y*tileSize),tileSize,tileSize);
        	}
        	
        	g2d.setColor(Color.black);
			g2d.drawRect(node.x*tileSize, node.y*tileSize, tileSize, tileSize);
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





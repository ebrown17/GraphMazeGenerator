import java.util.ArrayList;

import javax.swing.SwingUtilities;

import graphUtil.GraphNode;
import graphics.Base;
import maze.Maze;
import maze.RecursiveMaze;
import solvers.AStar;

public class Main {
	
	private static final int TILESIZE = 2;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	
	static int row = WIDTH/TILESIZE;
	static int column = HEIGHT/TILESIZE;
	static ArrayList<GraphNode> maze;
	static Maze test;
	static AStar path;
	public static void main(String args[]){
		
		 test = new RecursiveMaze(row,column);
		 path = new AStar(test.getNodeList());
		 
		//maze = new AStar(new RecursiveMaze(row,column).getNodeList()).getMaze();		
		//printLevel(maze);		
		
		 SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Base base = new Base(TILESIZE,WIDTH,HEIGHT,test.getNodeList());
					base = new Base(TILESIZE,WIDTH,HEIGHT,path.getPath());
					
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







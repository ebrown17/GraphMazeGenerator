import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import graphUtil.Vector2d;
import graphUtil.nodes.GridNode;
import graphics.Base;
import map.generation.mazes.RecursiveMaze;
import solvers.AStar;

public class Main {
	
	private static final int TILESIZE = 5;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	
	static int row = WIDTH/TILESIZE;
	static int column = HEIGHT/TILESIZE;	
	static RecursiveMaze test;
	static ArrayList<Vector2d> path;
	static Base base;
	public static void main(String args[]){
		
		/*while(true){
			path =null;*/
			while(null == path){ 
				test = new RecursiveMaze(row,column); 
				path = AStar.aStarSearch(test.getStart(),test.getEnd());		 
			}
		 
		 SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					base = new Base(TILESIZE,WIDTH,HEIGHT,test);
					
					base.pack();               
					base.getContentPane().setPreferredSize(new Dimension(WIDTH,HEIGHT));
					base.setTitle("Practice");
					base.pack();        
					base.setLocationRelativeTo(null);
					base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					base.setResizable(true);
					base.setVisible(true);
					base.addPath(path);
					//base.addPath(path.getPath());
					//base = new Base(TILESIZE,WIDTH,HEIGHT,path.getPath());
					
				}
			});	
		 
		
		/* try {
			Thread.sleep(2000);
			base.dispose();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}*/
	}
	
	public static void printLevel(ArrayList<GridNode> maze){
		int count =1;
		for(GridNode node: maze){			
			System.out.print(node.tile.chr + "");
			if(count % row == 0) System.out.println("");
			count++;
		}	
	}
}







import java.util.ArrayList;
import graphUtil.GraphNode;
import maze.RecursiveMaze;
import solvers.AStar;

public class Main {
	
	static int row = 15;
	static int column = 30;
	static ArrayList<GraphNode> maze;
	
	public static void main(String args[]){		
		
		maze = new AStar(new RecursiveMaze(row,column).getMaze()).getMaze();		
		printLevel(maze);
		maze = new AStar(new RecursiveMaze(row,column).getMaze()).getMaze();		
		printLevel(maze);
		maze = new AStar(new RecursiveMaze(row,column).getMaze()).getMaze();		
		printLevel(maze);
	
	}
	
	public static void printLevel(ArrayList<GraphNode> maze){
		int count =1;
		for(GraphNode node: maze){			
			System.out.print(node.getState() + "");
			if(count % column == 0) System.out.println("");
			count++;
		}	
	}
}





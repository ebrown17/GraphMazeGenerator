import java.util.ArrayList;
import java.util.Random;

public class Main {
	
	static int row = 15;
	static int column = 30;
	static ArrayList<Node> nodes;
	
	public static void main(String args[]){		
		
		nodes = GeneratorUtils.generateNodeList(row, column);		
		
		GeneratorUtils.setEdgeNodes(row,column,nodes);
		
		//System.out.println((int)(Math.random()*nodes.size()));
		GeneratorUtils.generateRecursive(nodes,row,column);
		Node start=null,end=null;
		for(Node node: nodes){
			if(node.getState().equals("1"))node.cost=1000;
			if(node.getState().equals("S"))start=node;
			if(node.getState().equals("E"))end=node;
			
		}
		long startTime = System.nanoTime();
		
		GeneratorUtils.AStarSearch(start,end);		
		//GeneratorUtils.resetPriority(nodes);
	//	GeneratorUtils.AStarSearchFast(start,end);
		
		long endTime = System.nanoTime();		
		long totalTime = (endTime-startTime)/1000;
		System.out.println((totalTime) + " ms to solve");
		
		
		printLevel();
	}
	
	public static void printLevel(){
		int count =1;
		for(Node node: nodes){			
			System.out.print(node.getState() + "");
			if(count % column == 0) System.out.println("");
			count++;
		}
	
	}

}





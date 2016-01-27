package maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import graphUtil.GraphNode;



public class RecursiveMaze extends Maze{		
	
	/**
	 * 
	 * @param rows number of rows a maze will have
	 * @param columns number of columns a maze will have
	 */
	
	private GraphNode start,end;
	
	public RecursiveMaze(int rows, int columns){
		super(rows,columns);
		generateRecursiveMaze();
	}	
	
	private void generateRecursiveMaze(){
		Stack<GraphNode> stack = new Stack<GraphNode>();
		ArrayList<GraphNode> unVisited = new ArrayList<GraphNode>();
		
		for(GraphNode node: nodeList){
			unVisited.add(node);
		}
		
		ArrayList<GraphNode> neighbors;
		
		GraphNode current = unVisited.remove(COLUMNS+1);
		current.start=true;	
		start=current;
		current.cost=0;
		current.visited=true;
		while(unVisited.size()>0){
			neighbors = new ArrayList<GraphNode>();
			for(GraphNode neighbor:current.getEdges()){
				if(neighbor.visited)continue;
				if(neighbor.start)continue;
				if(neighbor.x == 0 && neighbor.y == 0 && (neighbor.x > ROWS || neighbor.y > COLUMNS))continue;				
				
				int count =0;
				for(GraphNode nextNeghbor: neighbor.getEdges()){
					if(nextNeghbor.filled)count++;
				}
				if(count>=3){
					if(neighbor.x > 0 && neighbor.y > 0 && neighbor.x < ROWS-1 && neighbor.y < COLUMNS-1){
						neighbors.add(neighbor);
					}
				}
			}
			if(neighbors.size()>0){
				int size = neighbors.size();				
				stack.push(current);
				current = neighbors.remove((int)(Math.random()*size));			
				current.filled=false;				
				current.cost=0;
				current.visited=true;
			}
			else if (stack.size()!=0){
				current = stack.pop();
			}
			else {
				current = unVisited.remove((int)(Math.random()*unVisited.size()));
				current.visited=true;
			}			
			if(unVisited.size()<=0){				
				current.end=true;
				current.cost=0;
				current.visited=true;
				
				end=current;
			}
		}
	}
	
	public GraphNode getStart(){
		return start;
	}
	
	public GraphNode endStart(){
		return end;
	} 

}

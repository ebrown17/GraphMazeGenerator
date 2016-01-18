package maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import graphUtil.GraphNode;



public class RecursiveMaze {
	
	private static final int[][] DIRS = {{1,0},{0,1},{-1,0},{0,-1}};
	private final int ROWS,COLUMNS;
	
	private ArrayList<GraphNode> nodeList  = new ArrayList<GraphNode>();	
	
	/**
	 * 
	 * @param rows number of rows a maze will have
	 * @param columns number of columns a maze will have
	 */
	
	public RecursiveMaze(int rows, int columns){
		this.ROWS=rows;
		this.COLUMNS=columns;
		generateNodeList();
		setEdgeNodes();
		generateRecursiveMaze();
	}
	
	public ArrayList<GraphNode> getMaze(){
		return nodeList;
	}
	
	private void generateNodeList(){		
		for(int x=0;x<ROWS;x++){
			for(int y=0;y<COLUMNS;y++){
				int index = (x*COLUMNS)+y;
				nodeList.add(new GraphNode(x,y,index));								
			}
		}		
	}
	
	private void setEdgeNodes(){
		for(GraphNode node: nodeList){		
			for(int[] dir: DIRS){			
				int nX = node.x + dir[0];
				int nY =node.y + dir[1];
				int index = (nX*COLUMNS)+nY;
				if(nX < 0 || nY <0 || nX >= ROWS || nY >= COLUMNS )continue;			
				node.addEdge(nodeList.get(index));			
			}
		}
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
			}
		}
	}

}

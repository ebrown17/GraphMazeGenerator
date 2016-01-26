package solvers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

import graphUtil.GraphNode;

public class AStar {
	
	private ArrayList<GraphNode> maze;	
	private GraphNode start,end;
	
	public AStar(ArrayList<GraphNode> maze){
		this.maze=maze;
		setStartEndNodes();
		aStarSearch();
	}

	
	private void setStartEndNodes(){
		for(GraphNode node: maze){
			if(node.start){start=node;}
			if(node.end){end=node;}
		}
	}
	
	private void aStarSearch(){
		
		Queue<GraphNode> frontier = new PriorityQueue<GraphNode>();
		HashMap<GraphNode,GraphNode> cameFrom = new HashMap<GraphNode,GraphNode>();
		HashMap<GraphNode,Integer> costSoFar = new HashMap<GraphNode,Integer>();		
		ArrayList<GraphNode> path = new ArrayList<GraphNode>();
		
		frontier.add(start);		
		cameFrom.put(start, start);		
		costSoFar.put(start, 0);
		
		GraphNode current = null;		
		Integer cost =0;
		
		while(!frontier.isEmpty()){			
			
			current = frontier.poll();		
			if(current == end)break;			
			for(GraphNode next: current.getEdges()){				
				cost = 1+ costSoFar.get(current);
				if(!costSoFar.containsKey(next) || cost < costSoFar.get(next)){
					costSoFar.put(next, cost);
					next.priority= cost + heuristic(next,end)+next.cost;
					frontier.add(next);
					cameFrom.put(next, current);
				}				
			}				
		}		
		
		while(current != start) {			
			current = cameFrom.get(current);		
			path.add(current);			
		}
		int count=0;
		for(GraphNode node: path ){
			if(node == start || node == end)continue;
			node.path=true;
			count++;
		}	
		System.out.println(count + " moves to solve");
	}
	
	private Integer heuristic(GraphNode a, GraphNode b){
		return (Math.abs(a.x - b.x) + Math.abs(a.y-b.y));
	}
	
	public ArrayList<GraphNode> getMaze(){
		return maze;
	}
	
}

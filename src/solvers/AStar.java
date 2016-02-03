package solvers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

import graphUtil.Vector2d;
import graphUtil.nodes.GridNode;
import map.TileType;

public class AStar {
	
	private ArrayList<GridNode> nodeList;
	private ArrayList<GridNode> path = new ArrayList<GridNode>();
	private ArrayList<Vector2d> vectorPath = new ArrayList<Vector2d>();
	private GridNode start,end;
	
	public AStar(){	}	
	public static ArrayList<Vector2d> aStarSearch(GridNode start,GridNode end){		
		
		Queue<GridNode> frontier = new PriorityQueue<GridNode>();
		HashMap<GridNode,GridNode> cameFrom = new HashMap<GridNode,GridNode>();
		HashMap<GridNode,Integer> costSoFar = new HashMap<GridNode,Integer>();		
		ArrayList<GridNode> path = new ArrayList<GridNode>();
		ArrayList<Vector2d> vectorPath = new ArrayList<Vector2d>();
		
		frontier.add(start);		
		cameFrom.put(start, start);		// maybe start not null
		costSoFar.put(start, 0);
		
		GridNode current = null;		
		Integer cost =0;
		
		while(!frontier.isEmpty()){			
			
			current = frontier.poll();
			if(current == end)break;			
			for(GridNode next: current.getEdges()){
				if(next.tile==TileType.WALL)continue;
				cost = 1+ costSoFar.get(current);
				if(!costSoFar.containsKey(next) || cost < costSoFar.get(next)){
					costSoFar.put(next, cost);
					next.priority= cost + heuristic(next.postion,end.postion)+next.cost;
					frontier.add(next);
					cameFrom.put(next, current);
					
				}				
			}				
		}
		if(current != end){
			return null;
		}
		path.add(current);
		while(current != start) {			
			current = cameFrom.get(current);		
			path.add(current);			
		}
		int count=0;
		for(GridNode node: path ){
			if(node.postion.sameVector(start.postion) || node.postion.sameVector(end.postion))continue;
			//node.tile = TileType.PATH;
			vectorPath.add(node.postion);
			count++;
		}	
		
		//System.out.println(count + " moves to solve");
		return vectorPath;
	}
	
	private static Integer heuristic(Vector2d a, Vector2d b){
		return (Math.abs(a.x - b.x) + Math.abs(a.y-b.y));
	}	
	
}

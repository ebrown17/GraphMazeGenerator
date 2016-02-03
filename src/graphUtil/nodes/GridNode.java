package graphUtil.nodes;

import java.util.ArrayList;


import graphUtil.Vector2d;
import map.TileType;

public class GridNode implements Comparable<GridNode>{
	
	public Vector2d postion;
	public boolean visited=false;
	public Integer cost =0, priority=0;
	public TileType tile = TileType.WALL;
	private ArrayList<GridNode> edges = new ArrayList<GridNode>();	
	
	public GridNode(Vector2d postion){
		this.postion=postion;
	}

	
	public void addEdge(GridNode node){
		edges.add(node);
	}
	
	public ArrayList<GridNode> getEdges(){		
		return edges.size()>0 ? edges : null;
	}
	
	@Override
	public int compareTo(GridNode o) {
		if(priority < o.priority) return -1;
		if(priority> o.priority) return 1;
		else return 0;
	}

}

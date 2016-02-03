package graphUtil.graphs;

import java.util.ArrayList;

import graphUtil.Vector2d;
import graphUtil.nodes.GridNode;

public abstract class Grid {
	
	protected static final int[][] DIRS = {{1,0},{0,1},{-1,0},{0,-1}};
	protected final int ROWS,COLUMNS;
	
	protected ArrayList<GridNode> nodeList  = new ArrayList<GridNode>();	
	
	/**
	 * @param rows number of rows a grid will have
	 * @param columns number of columns a grid will have
	 */
	
	public Grid(int rows, int columns){
		this.ROWS=rows;
		this.COLUMNS=columns;
		generateNodeList();
		setEdgeNodes();
		
	}
	
	/**
	 * @return returns list of nodes with edges connected by XY Cartesian coordinates
	 */
	
	public ArrayList<GridNode> getNodeList(){
		return nodeList;
	}
	
	private void generateNodeList(){		
		for(int x=0;x<ROWS;x++){
			for(int y=0;y<COLUMNS;y++){
				//int index = (x*COLUMNS)+y; // 2d cord translated to 1d cord				
				nodeList.add(new GridNode(new Vector2d(x,y)));								
			}
		}		
	}
	
	private void setEdgeNodes(){
		for(GridNode node: nodeList){
			Vector2d vector = node.postion;
			for(int[] dir: DIRS){				
				int nX = vector.x + dir[0];
				int nY =vector.y + dir[1];
				int index = (nX*COLUMNS)+nY;
				if(nX < 0 || nY <0 || nX >= ROWS || nY >= COLUMNS )continue; // prevents nodes from going out of bounds		
				node.addEdge(nodeList.get(index));			
			}
		}
	}

}


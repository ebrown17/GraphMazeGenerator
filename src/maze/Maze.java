package maze;

import java.util.ArrayList;

import graphUtil.GraphNode;

public class Maze {
	protected static final int[][] DIRS = {{1,0},{0,1},{-1,0},{0,-1}};
	protected final int ROWS,COLUMNS;	
	protected ArrayList<GraphNode> nodeList  = new ArrayList<GraphNode>();	
	
	/**
	 * 
	 * @param rows number of rows a maze will have
	 * @param columns number of columns a maze will have
	 */
	
	public Maze(int rows, int columns){
		this.ROWS=rows;
		this.COLUMNS=columns;
		generateNodeList();
		setEdgeNodes();
		
	}
	
	public ArrayList<GraphNode> getNodeList(){
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

}

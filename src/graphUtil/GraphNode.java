package graphUtil;

import java.util.ArrayList;

public class GraphNode implements Comparable<GraphNode> {
	static final String FILL= "I";
	static final String PATH= "o";
	static final String CLEAR= " ";
	static final String START= "S";
	static final String END= "X";	
	
	final public int x, y;
	final public int index;
	public boolean filled=false,visited=false,start=false,end=false,path=false;
	public int cost =1000, priority=0;	
	private ArrayList<GraphNode> edges = new ArrayList<GraphNode>();	
	
	public GraphNode(int x, int y, int index){
		this.x=x;
		this.y=y;
		this.index = index;
		this.filled=true;
		this.visited=false;
	}
	
	public String toString(){
		return x + " " + y;
	}	
	
	public String getState(){
		if(start){return START;}
		else if (end){return END;}
		else if (filled){return FILL;}
		else if (path){return PATH;}
		else {return CLEAR;}
		
	}	
	
	public void addEdge(GraphNode node){
		edges.add(node);
	}
	
	public ArrayList<GraphNode> getEdges(){		
		return edges.size()>0 ? edges : null;
	}
	
	@Override
	public int compareTo(GraphNode o) {
		if(priority < o.priority) return -1;
		if(priority> o.priority) return 1;
		else return 0;
	}	
}

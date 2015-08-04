import java.util.ArrayList;

public class Node implements Comparable<Node> {
	final public int x, y;
	final public int index;
	public boolean filled,visited;
	public int cost =0, priority=0;
	private String state = " ";
	private ArrayList<Node> edges = new ArrayList<Node>();
	
	
	public Node(int x, int y, int index){
		this.x=x;
		this.y=y;
		this.index = index;
		this.filled=true;
		this.visited=false;
	}
	
	public String toString(){
		return x + " " + y;
	}
	
	public void setState(String c){
		if(!c.equals("1")); filled = false;
		state = c;
	}
	
	public String getState(){
		
		return filled ? "1" : state;
	}
	
	public void addEdge(Node node){
		edges.add(node);
	}
	
	public ArrayList<Node> getEdges(){		
		return edges.size()>0 ? edges : null;
	}
	
	@Override
	public int compareTo(Node o) {
		if(priority < o.priority) return -1;
		if(priority> o.priority) return 1;
		else return 0;
	}
	
	
	
}

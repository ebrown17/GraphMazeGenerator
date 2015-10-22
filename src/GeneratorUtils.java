import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class GeneratorUtils {
	
	static int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
	
	public static ArrayList<Node> generateNodeList(int row, int column){
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		for(int x=0;x<row;x++){
			for(int y=0;y<column;y++){
				int index = (x*column)+y;
				nodes.add(new Node(x,y,index));								
			}
		}		
		return nodes;
	}
	
	public static void setEdgeNodes(int row,int column,ArrayList<Node> nodes){		
		for(Node node: nodes){		
			for(int[] dir: dirs){			
				int nX = node.x + dir[0];
				int nY =node.y + dir[1];
				int index = (nX*column)+nY;
				if(nX < 0 || nY <0 || nX >= row || nY >= column )continue;			
				node.addEdge(nodes.get(index));			
			}
		}
	}
	
	public static void resetPriority(ArrayList<Node> nodes){
		for(Node node: nodes){
			node.priority=0;
		}
	}
	public static void generateRecursive(ArrayList<Node> nodes,int rows, int columns){
		Stack<Node> stack = new Stack<Node>();
		ArrayList<Node> unVisited = new ArrayList<Node>();
		for(Node node: nodes){
			unVisited.add(node);
		}
		
		ArrayList<Node> neighbors;
		
		Node current = unVisited.remove((int)(Math.random()*unVisited.size()));
		current.setState("S");
		current.visited=true;
		while(unVisited.size()>0){
			neighbors = new ArrayList<Node>();
			for(Node neighbor:current.getEdges()){
				if(neighbor.visited==true)continue;
				if(neighbor.getState().equals("S"))continue;
				if(neighbor.x == 0 && neighbor.y == 0 && (neighbor.x > rows || neighbor.y > columns))continue;
				
				
				int count =0;
				for(Node nextNeghbor: neighbor.getEdges()){
					if(nextNeghbor.filled==true )count++;
				}
				if(count>=3){
					if(neighbor.x > 0 && neighbor.y > 0 && neighbor.x < rows-1 && neighbor.y < columns-1){
						neighbors.add(neighbor);
					}
				}
			}
			if(neighbors.size()>0){
				int size = neighbors.size();
				Random r = new Random();
				stack.push(current);
				current = neighbors.remove((neighbors.size()>3) ? 3 :    (int)(Math.random()*size));			
				current.setState(" ");				
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
				current.setState("E");
				current.visited=true;
			}
		}
	}
	
	
	public static void AStarSearchFast(Node start,Node end){
		
		Queue<Node> frontier = new PriorityQueue<Node>();
		HashMap<Node,Node> cameFrom = new HashMap<Node,Node>();		
		ArrayList<Node> path = new ArrayList<Node>();		
		frontier.add(start);		
		cameFrom.put(start, start);		
		
		Node current = null;
		int cost, seeIfCloser;
		Node closest;
		
		while(!frontier.isEmpty()){
			closest=null;
			cost =0;
			current = frontier.poll();
			
			if(current == end)break;			
			
			for(Node next: current.getEdges()){
				
				if(closest == null){
					closest = next;
					cost = heuristic(closest,end) + closest.cost+closest.priority;					
				} else {
					 seeIfCloser = heuristic(next,end) + next.cost+next.priority;
					if(seeIfCloser < cost){
						closest = next;
						cost = seeIfCloser;
					}
				}				
			}			
			closest.priority=cost;						
			frontier.add(closest);
			cameFrom.put(closest, current);			
		}		
		
		while(current != start) {			
			current = cameFrom.get(current);		
			path.add(current);			
		}
		
		for(Node node: path ){
			if(node == start || node == end)continue;
			node.setState(".");		
		}		
	}
	
	public static void AStarSearch(Node start,Node end){
		
		Queue<Node> frontier = new PriorityQueue<Node>();
		HashMap<Node,Node> cameFrom = new HashMap<Node,Node>();
		HashMap<Node,Integer> costSoFar = new HashMap<Node,Integer>();		
		ArrayList<Node> path = new ArrayList<Node>();
		
		frontier.add(start);		
		cameFrom.put(start, start);		
		costSoFar.put(start, 0);
		
		Node current = null;		
		int cost =0;
		
		while(!frontier.isEmpty()){			
			
			current = frontier.poll();		
			if(current == end)break;			
			for(Node next: current.getEdges()){				
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
		for(Node node: path ){
			if(node == start || node == end)continue;
			node.setState(".");
			count++;
		}	
		System.out.println(count + " moves to solve");
	}
	
	public static int heuristic(Node a, Node b){
		return (Math.abs(a.x - b.x) + Math.abs(a.y-b.y));
	}
}

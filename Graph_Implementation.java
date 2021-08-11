package graphs;
import java.lang.Math;
import java.util.ArrayList;


class StackG {
	private int [] data;
	private int head = 0;
	public StackG(int size) {
		this.data = new int [size];
	}
	public void push(int ind) {
		data[head++] = ind;
	}
	public int pop() {
		return data[--head];
	}
	
	public int peek() {
		return data[head - 1];
	}
	public boolean isEmpty() {
		if(head == 0) {return true;}
		
		else {return false;}
	}
}

class Vertex{
	private int data;
	private boolean wasVisited;
	public Vertex(int data) {
		this.data = data;
		this.wasVisited = false;
	}
	public boolean wasVisited() {
		return wasVisited;
	}
	public void setStatus(boolean visited) {
		this.wasVisited = visited;
	}
	
	public void display() {
		System.out.println(this.data);
		
	}
}
class Graph{
	
	private final int maxVert = 20;
	private Vertex [] vertexArr;
	private int [] tracker;// 
	private int [][] adjMat;
	private int nVerts;
	private StackG theStack;
	
	public Graph() {
		vertexArr = new Vertex [maxVert];
		adjMat = new int[maxVert][maxVert];
		theStack = new StackG(maxVert);
		nVerts = 0;
		tracker = new int [maxVert];
		}
	
	public void addVert(int data) {
		vertexArr[nVerts++] = new Vertex(data);
	}
	
	public void addEdge(int start, int end) {
		adjMat[start][end] = 1;
		adjMat[end][start]=1;
	}
	public void displayVertex(int ind) {
		vertexArr[ind].display();
	}
	public int getAdjacentUnvisited(int v) {
		for(int i = 0; i < maxVert; i++) {
			if (adjMat[v][i] == 1) {
				if(!vertexArr[i].wasVisited()) {return i;}
			}
		}
		
		return -1;
		
	}
	public boolean isConnected() {
		int nodes = DFS(0);
		if(nodes < nVerts) {return false;}
		else {return true;}
	}
	
	public void displaySubgraphs() {
		
		DFS(0);
		
		for(int i = 0; i < nVerts; i++) {
			if(tracker[i] == 0) {
				DFS(i);
			}
		}
		for(int i = 0; i < nVerts; i++) {
			tracker[i] = 0;
		}
	}
	
	
	public int DFS(int rootInd) {
		tracker[rootInd] = 1;
		Vertex curr = vertexArr[rootInd];
		curr.display();
		curr.setStatus(true);
		int count = 1;
		theStack.push(rootInd);
		while(!theStack.isEmpty()) {
			int vertInd = getAdjacentUnvisited(theStack.peek());
			if(vertInd == -1) {
				theStack.pop();
			}
			else {
				count++;
				curr = vertexArr[vertInd];
				curr.setStatus(true);
				tracker[vertInd] = 1;
				curr.display();
				theStack.push(vertInd);
			}
		}
		for(int i = 0; i<this.nVerts; i ++) {
			vertexArr[i].setStatus(false);
		}
		
		return count;
		
	}
}



public class GaphTraversal {
	public static void main(String[] args) {
	Graph GraphyBoi = new Graph();
	GraphyBoi.addVert(5);
	GraphyBoi.addVert(7);
	GraphyBoi.addVert(9);
	GraphyBoi.addEdge(0,2);
	GraphyBoi.addEdge(1,2);
	System.out.println(GraphyBoi.isConnected());
	
	}
}

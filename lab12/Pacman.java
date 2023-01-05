package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class Pacman {

	/** representation of the graph as a 2D array */
	private Node[][] maze;
	/** input file name */
	private String inputFileName;
	/** output file name */
	private String outputFileName;
	/** height and width of the maze */
	private int height;
	private int width;
	/** starting (X,Y) position of Pacman */
	private int startX;
	private int startY;

	/** A Node is the building block for a Graph. */
	private static class Node {
		/** the content at this location */
	    private char content;
	    /** the row where this location occurs */
	    private int row;
	    /** the column where this location occurs */
	    private int col;
		private boolean visited;
		private Node parent;

	    public Node(int x, int y, char c) {
	        visited = false;
	        content = c;
	        parent =  null;
	        this.row = x;
	        this.col = y;
	    }

	    public boolean isWall() { return content == 'X'; }
	    public boolean isVisited() { return visited; }
	}

	/** constructor */
	public Pacman(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		buildGraph();
	}

	private boolean inMaze(int index, int bound){
		return index < bound && index >= 0;
	}

	/** helper method to construct the solution path from S to G
	 *  NOTE this path is built in reverse order, starting with the goal G.
	*/
	private void backtrack(Node end){
		// TODO for assignment12
		
			while(!(end.parent.content == 'S')) {
				end.parent.content = '.';
				end = end.parent;
			}
		
		
		//do nothing THE END
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			output.write(this.height + " " + this.width + "\n");
			for(int y = 0; y < this.height; y++) {
				for(int x = 0; x < this.width; x++) {
					output.write(this.maze[y][x].content + " ");
				}
				output.write("\n");
			}
			output.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		String s = "";
		s += height + " " + width + "\n";
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				s += maze[i][j].content + " ";
			}
			s += "\n";
		}
		return s;
	}
	/** helper method to construct a graph from a given input file;
	 *  all member variables (e.g. maze, startX, startY) should be
	 *  initialized by the end of this method
	 */
	private void buildGraph() {
		// TODO for lab12
		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			String line;
			String[] ans = new String[2];
			//read line
			//go line by line character by character
			line = input.readLine();
			ans = line.split(" ");
			this.height = Integer.parseInt(ans[0]);
			this.width = Integer.parseInt(ans[1]);
			this.maze = new Node[this.height][this.width];
			int ascii;
			int xpos = 0;
			int ypos = 0;
			while((ascii = input.read()) != -1) {
				if(ascii == 88) {
					//'X'
					Node x = new Node(xpos,ypos,'X');
					this.maze[ypos][xpos] = x;
				}else if(ascii == 83) {
					//'S'
					Node s = new Node(xpos,ypos,'S');
					this.startX = xpos;
					this.startY = ypos;
					this.maze[ypos][xpos] = s;
				}else if(ascii == 71) {
					//'G'
					Node g = new Node(xpos,ypos,'G');
					this.maze[ypos][xpos] = g;
				}else if(ascii == 32) {
					Node empty = new Node(xpos,ypos,' ');
					this.maze[ypos][xpos] = empty;
				}
				else if(ascii == 10) {
					ypos++;
					xpos = -1;
				}
				xpos++;
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/** obtains all neighboring nodes that have *not* been
	 *  visited yet from a given node when looking at the four
	 *  cardinal directions: North, South, West, East (IN THIS ORDER!)
	 *
	 * @param currentNode the given node
	 * @return an ArrayList of the neighbors (i.e., successors)
	 */
	public ArrayList<Node> getNeighbors(Node currentNode) {
		// TODO for assignment12
		Node north, south, east, west;
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbors = new ArrayList<Node>();
		// 1. Inspect the north neighbor
		
		if(inMaze(currentNode.col - 1, height) && !maze[currentNode.col - 1][currentNode.row].isWall()) {
			north = this.maze[currentNode.col - 1][currentNode.row];
			if(!north.isVisited()) {
				north.visited = true;
				north.parent = currentNode;
				neighbors.add(north);
			}
		}
		// 2. Inspect the south neighbor
		if(inMaze(currentNode.col + 1, height) && !maze[currentNode.col + 1][currentNode.row].isWall()) {
			south = this.maze[currentNode.col + 1][currentNode.row];
			if(!south.isVisited()) {
				south.visited = true;
				south.parent = currentNode;
				neighbors.add(south);
			}
		}
		// 3. Inspect the west neighbor
		if(inMaze(currentNode.row - 1, width) && !maze[currentNode.col][currentNode.row - 1].isWall()) {
			west = this.maze[currentNode.col][currentNode.row - 1];
			if(!west.isVisited()) {
				west.visited = true;
				west.parent = currentNode;
				neighbors.add(west);
			}
		}
		// 4. Inspect the east neighbor
		if(inMaze(currentNode.row + 1, width) && !maze[currentNode.col][currentNode.row + 1].isWall()) {
			east = this.maze[currentNode.col][currentNode.row + 1];
			if(!east.isVisited()) {
				east.visited = true;
			    east.parent = currentNode;
			    neighbors.add(east);
			}
		}

		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		Queue<Node> q = new LinkedList<Node>();
		Node start = this.maze[this.startY][this.startX];
		q.add(start);
		start.visited = true;
		Node current = null;
		
		while(!q.isEmpty()) {
			current = q.poll();
			if(current.content == 'G') {
				break;
			}
			ArrayList<Node> neighbors = getNeighbors(current);
			for(int i = 0; i < neighbors.size(); i++) {
				q.add(neighbors.get(i));
			}
		}
		if(current.content == 'G') {
			backtrack(current);
		}
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		Stack<Node> s = new Stack<Node>();
		Node start = this.maze[this.startY][this.startX];
		start.visited = true;
		s.push(start);
		Node current = null;
		while(!s.isEmpty()) {
			current = s.pop();
			current.visited = true;
			if(current.content == 'G') {
				break;
			}
			ArrayList<Node> neighbors = getNeighbors(current);
			for(int i = 0; i < neighbors.size(); i++) {
				s.push(neighbors.get(i));
			}
		}
		if(current.content == 'G') {
			backtrack(current);
		}
	}

}

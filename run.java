package chelmist_project_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class run 
{
	static int size = 0; // size of the graph growth to make sure to run properly.
	
	// sorting method to return topological sort if possible.
	public static List<Integer> Topologicalsorting(Graph graph, int n)
	{
		ArrayList<Integer> sorted = new ArrayList<>(); //empty list of sorted elements that will be pospulated on visiting nodes
		ArrayList<Integer> indegree = graph.indegree;  // list of the degree of the elements 
		Stack<Integer> stack = new Stack<>(); // set of all nodes with no incoming edges to make sure the idea.
		
		// so we add all the non incoming edges to the stack as they will have degree 0
		for(int i = 0; i < n ; i++)
		{
			if(indegree.get(i) == 0)
			{
				stack.add(i);
			}
		}
		
		// if the stack have some value then topological sorting is possible else not possible
		while(!stack.isEmpty())
		{
			// we remove node a node from stack now 
			int i = stack.pop();
			sorted.add(i); // add it to the sorted 
			// loop over all other temperary edges to the node i and add them to set
			for(int temp: graph.adj_list.get(i))
			{
				indegree.set(temp, indegree.get(temp)-1);
				//once the temp have no more incoming edges add it to set.
				if(indegree.get(temp) == 0)
				{
					stack.add(temp);
				}
			}
		}
		
		// if graph have more edges after this iteration of both loops there exits a cycle in the graph and topological sort isnt possible
		for(int i = 0; i < n; i++)
		{
			if(indegree.get(i) != 0)
			{
				return null;
			}
		}

		return sorted; // we return the sorted list in topological order.
		
	}
	
	// we get the edges of the graph from csv file
	// please add the file path in this method and make sure 
	// 1. the nodes are possible to parse to int
	// 2. the last element represent the max value of the node+1 to iterate through the graph
	public static ArrayList<Edge> getedges() throws Exception
	{
		//scanner object to read the file
		Scanner sc = new Scanner(new File("C:\\Users\\Owner\\OneDrive\\eclipse\\testing\\src\\testing\\data input.csv"));
		// obtained string array that we gonna fill from the csv file splitting by ,
		String[] obtained = null;
		sc.useDelimiter(","); // splitting by comma
		//when there exist a next line in sc
		while(sc.hasNext())
		{
			//we fill obtained by removing spaces if any
			obtained = sc.next().split("\\s+");
		}
		//a list of edges 
		ArrayList<Edge> edge = new ArrayList<>();
		// size of the graph that will be filled by the last element input of the csv file
		 size = Integer.parseInt(obtained[obtained.length-1]);
		obtained[obtained.length-1] = null; // we ignore the last in checking the edges.
		
		// loop over the obtained and fill the edges according to directions mentioend.
		for(int i = 0; i< obtained.length-1; i++)
		{
			int source = 0;
			int destination = 0;
			try {
			 source = Integer.parseInt(obtained[i].split(";")[0]); // when split by the ; the first is source
			}catch(Exception e) //throws exception if not numbr in node
			{
				System.out.println("entered isnt a parsable number, please check and retry");
			}
			 try {
			destination = Integer.parseInt(obtained[i].split(";")[1]); // second is destination 
			 }
			 catch(Exception e) //throws exception if it is no ta number in the node
			 {
				 System.out.println("entered isnt a parsable number, please check and retry");
			 }
			// we make a edge out of them and add to list, 
			edge.add(new Edge(source, destination)); 
		}
		sc.close();
		return edge; // we return the arraylist. 
	}
	
	// main method
	public static void main(String[] args) throws Exception
	{
		 
		ArrayList<Edge> edge = getedges(); //we get the edges and store in alist 
		Graph graph = new Graph(edge, size); // pass the edges and size to create graph
		List<Integer> result = Topologicalsorting(graph , size); // we take the result from topological sorting method
		if(result != null) // if it is not null given graph is DAG and gave a topological sort
		{
			System.out.println("topological order is: ");
			result.remove(result.size()-1);
			System.out.print(result);
		}
		else //else it is cyclic and not possible to return the topological graph
		{
			 System.out.println("Graph has cycle. " +
	                    "Topological sorting is not possible");
		}
		
	}
}

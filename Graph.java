package chelmist_project_2;

import java.util.ArrayList;
import java.util.Collections;

// we create the graph in here 
public class Graph 
{
	ArrayList<ArrayList<Integer>> adj_list = null; // a empty list to store the adjacent values 
	
	ArrayList<Integer> indegree = null; // we have a list of vertexes
	
	//constructor to create the graph
	Graph(ArrayList<Edge> edges, int n)
	{
		adj_list = new ArrayList<>(); // initialize the list 
		//initializes the list
		for(int i = 0; i< n; i++)
		{
			adj_list.add(new ArrayList<>()); 
		}
		//initialize the degree of all by 0
		indegree = new ArrayList<>(Collections.nCopies(n, 0));
		
		//add edges to the graph to make the graph
		for(Edge edge: edges)
		{
			int source = edge.source;
			int destination = edge.destination;
			adj_list.get(source).add(destination); // directed edge addition to graph
			indegree.set(destination, indegree.get(destination)+1); //increment of the degree to keep track of the graph.
		}
	}
}

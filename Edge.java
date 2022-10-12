package chelmist_project_2;

//class edge that makes an edge for the graph
public class Edge 
{
	int source; // source of the edge
	int destination; //destination of the edge making it easy to identify the direction
	
	//construtor
	public Edge(int source, int destination)
	{
		this.source = source;  //initializes the direction of the edge.
		this.destination = destination;
		
	}

}

package graph;

public class edge {
	
	node n1=null,n2=null;
	
	public edge(node n1,node n2)
	{
		this.n1=n1;
		this.n2=n2;
	}
	
	public node get_n1()
	{
		return n1;
	}
	
	public node get_n2()
	{
		return n2;
	}

}

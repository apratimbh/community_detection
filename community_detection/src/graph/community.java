package graph;

import java.util.ArrayList;

public class community {
	
	ArrayList<node> node_list=new ArrayList<node>();
	
	public void add_node(node n)
	{
		node_list.add(n);
	}
	
	public node get_node(int idx)
	{
		return node_list.get(idx);
	}

}

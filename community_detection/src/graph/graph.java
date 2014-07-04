package graph;

import java.util.ArrayList;
import java.util.HashMap;

public class graph {
	
	ArrayList<node> node_list=new ArrayList<node>();
	ArrayList<edge> edge_list=new ArrayList<edge>();
	
	HashMap<Integer,Integer> node_num_to_node_idx=new HashMap<Integer,Integer>();
	
	public void add_node(node n)
	{
		node_num_to_node_idx.put(n.num, node_list.size());
		this.node_list.add(n);
	}
	
	public boolean if_node_exists(int num)
	{
		return node_num_to_node_idx.containsKey(num);
	}
	
	public void add_edge(edge e)
	{
		this.edge_list.add(e);
	}
	
	
	
	public node get_node(int num)
	{
		try {
			return node_list.get(node_num_to_node_idx.get(num));
		}
		catch(Exception e)
		{
			System.out.print(num);
			System.exit(0);
		}
		return null;
	}
	
	public edge get_edge(int idx)
	{
		return edge_list.get(idx);
	}
	
	public int get_number_of_nodes()
	{
		return this.node_list.size();
	}
	
	public int get_number_of_edges()
	{
		return this.edge_list.size();
	}
	
}

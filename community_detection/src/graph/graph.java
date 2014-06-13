package graph;

import java.util.ArrayList;
import java.util.HashMap;

public class graph {
	
	ArrayList<node> node_list=new ArrayList<node>();
	ArrayList<edge> edge_list=new ArrayList<edge>();
	
	HashMap<Integer,Integer> idx_to_vertex_num=new HashMap<Integer,Integer>();
	
	public void add_node(node n)
	{
		idx_to_vertex_num.put(n.num, node_list.size());
		this.node_list.add(n);
	}
	
	public boolean if_exists(int num)
	{
		return idx_to_vertex_num.containsKey(num);
	}
	
	public void add_edge(edge e)
	{
		this.edge_list.add(e);
	}
	
	
	
	public node get_node(int num)
	{
		return node_list.get(idx_to_vertex_num.get(num));
	}
	
	public edge get_edge(int idx)
	{
		return edge_list.get(idx);
	}
	
}

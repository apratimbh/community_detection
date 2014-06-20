package graph;

import java.util.ArrayList;
import java.util.HashMap;

public class fgraph extends graph {
	
	ArrayList<ArrayList<feature>> node_feat_list=new ArrayList<ArrayList<feature>>();
	HashMap<Integer,Integer> feat_num_to_feat_idx=new HashMap<Integer,Integer>();
	ArrayList<feature> feat_list=new ArrayList<feature>();
	HashMap<node,ArrayList<edge>> adj_list=new HashMap<node,ArrayList<edge>>();
	
	public void add_node(node n)
	{
		node_num_to_node_idx.put(n.num, node_list.size());
		this.node_list.add(n);
		this.node_feat_list.add(new ArrayList<feature>());
		ArrayList<edge> le=new ArrayList<edge>();
		adj_list.put(n, le);
	}
	
	public ArrayList<edge> get_edge_list(node n)
	{
		return adj_list.get(n);
	}
	
	public boolean if_feature_exists(int num)
	{
		return feat_num_to_feat_idx.containsKey(num);
	}
	
	public void add_feature(feature f)
	{
		feat_num_to_feat_idx.put(f.num, feat_list.size());
		this.feat_list.add(f);
	}
	
	public feature get_feature(int num)
	{
		return feat_list.get(feat_num_to_feat_idx.get(num));
	}
	
	public node get_node_by_idx(int idx)
	{
		try {
			return node_list.get(idx);
		}
		catch(Exception e)
		{
			System.out.print(idx);
			System.exit(0);
		}
		return null;
	}
	
	public void add_feature_to_node(node node,feature feat)
	{
		this.node_feat_list.get(this.node_num_to_node_idx.get(node.num)).add(feat);
	}
	
	public int get_number_of_features()
	{
		return this.feat_list.size();
	}
	
	public ArrayList<feature> get_features_of_node(int node)
	{
		return this.node_feat_list.get(node);
	}
	

}

package community_analysis;

import graph.community;
import graph.edge;
import graph.fgraph;
import graph.node;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class degree_distribution {

	public void main(fgraph fg,ArrayList<community> g,ArrayList<community> p)
	{
		ArrayList<node> all_nodes=new ArrayList<node>();
		for(int i=0;i<fg.get_number_of_nodes();i++)
		{
			all_nodes.add(fg.get_node_by_idx(i));
		}
		ArrayList<bin> bins=get_degree_distribution(fg,all_nodes);
		double d=avg_distance(fg, all_nodes);
		System.out.println("Avearge distance of all nodes: "+d);
		try
		{
			normalize(bins);
			save_distribution_to_file(bins,"C:\\plots\\g\\0_all.txt");
			save_distribution_to_file(bins,"C:\\plots\\p\\0_all.txt");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		int gcnt=0;
		for(community tc:g)
		{
			bins.clear();
			all_nodes.clear();
			for(int i=0;i<tc.size();i++)
			{
				all_nodes.add(tc.get_node(i));
			}
			if(all_nodes.size()>5)
			{
				d=avg_distance(fg, all_nodes);
				System.out.println("Avearge distance of g-"+gcnt+": "+d);
			}
			bins=get_degree_distribution(fg,all_nodes);
			try
			{
				normalize(bins);
				save_distribution_to_file(bins,"C:\\plots\\g\\0_g_"+(gcnt++)+".txt");

			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}

		}
		gcnt=0;
		for(community tc:p)
		{
			bins.clear();
			all_nodes.clear();
			for(int i=0;i<tc.size();i++)
			{
				all_nodes.add(tc.get_node(i));
			}
			bins=get_degree_distribution(fg,all_nodes);
			if(all_nodes.size()>5)
			{
				d=avg_distance(fg, all_nodes);
				System.out.println("Avearge distance of p-"+gcnt+": "+d);
			}
			try
			{
				normalize(bins);
				save_distribution_to_file(bins,"C:\\plots\\p\\0_p_"+(gcnt++)+".txt");

			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}

		}
		gcnt=0;
		for(community tc:g)
		{
			bins.clear();
			all_nodes.clear();
			for(int i=0;i<tc.size();i++)
			{
				all_nodes.add(tc.get_node(i));
			}
			bins=get_in_degree_distribution(fg,all_nodes,tc);
			try
			{
				normalize(bins);
				save_distribution_to_file(bins,"C:\\plots\\g\\0_g_in_"+(gcnt++)+".txt");

			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}

		}
		gcnt=0;
		for(community tc:p)
		{
			bins.clear();
			all_nodes.clear();
			for(int i=0;i<tc.size();i++)
			{
				all_nodes.add(tc.get_node(i));
			}
			bins=get_in_degree_distribution(fg,all_nodes,tc);
			try
			{
				normalize(bins);
				save_distribution_to_file(bins,"C:\\plots\\p\\0_p_in_"+(gcnt++)+".txt");

			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}

		}

		gcnt=0;
		for(community tc:g)
		{
			bins.clear();
			all_nodes.clear();
			for(int i=0;i<tc.size();i++)
			{
				all_nodes.add(tc.get_node(i));
			}
			bins=get_out_degree_distribution(fg,all_nodes,tc);
			try
			{
				normalize(bins);
				save_distribution_to_file(bins,"C:\\plots\\g\\0_g_out_"+(gcnt++)+".txt");

			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}

		}
		gcnt=0;
		for(community tc:p)
		{
			bins.clear();
			all_nodes.clear();
			for(int i=0;i<tc.size();i++)
			{
				all_nodes.add(tc.get_node(i));
			}
			bins=get_out_degree_distribution(fg,all_nodes,tc);
			try
			{
				normalize(bins);
				save_distribution_to_file(bins,"C:\\plots\\p\\0_p_out_"+(gcnt++)+".txt");

			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}

		}

	}

	public void normalize(ArrayList<bin> bins)
	{
		int count=0;
		for(bin b:bins)
		{
			count+=b.frequency();
		}
		for(bin b:bins)
		{
			b.normalize(count);
		}

		double prev=0;
		for(bin b:bins)
		{
			b.add_cumulative_frequency(prev);
			prev=b.cumulative();
		}

	}

	public ArrayList<bin> get_in_degree_distribution(fgraph fg,ArrayList<node> node_list,community c)
	{
		ArrayList<bin> bins=new ArrayList<bin>();
		for(node n:node_list)
		{
			//System.out.println("Node: "+n.get_name()+" Edge List Size: "+fg.get_edge_list(n).size());

			int idx=bin_position(bins,count_nodes_in_community(fg.get_edge_list(n),c));  
			bins.get(idx).increment_frequency();
		}
		return bins;

	}

	public ArrayList<bin> get_out_degree_distribution(fgraph fg,ArrayList<node> node_list,community c)
	{
		ArrayList<bin> bins=new ArrayList<bin>();
		for(node n:node_list)
		{
			//System.out.println("Node: "+n.get_name()+" Edge List Size: "+fg.get_edge_list(n).size());

			int idx=bin_position(bins,fg.get_edge_list(n).size()-count_nodes_in_community(fg.get_edge_list(n),c));
			bins.get(idx).increment_frequency();
		}
		return bins;

	}

	public int count_nodes_in_community(ArrayList<edge> edge_list,community c)
	{
		int count=0;
		for(edge e:edge_list)
		{
			if(c.contains(e.get_n1())&&c.contains(e.get_n2()))
			{
				count++;
			}
		}
		return count;
	}

	public void save_distribution_to_file(ArrayList<bin> bins,String file_name) throws IOException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter(file_name));
		for(bin b:bins)
		{
			bw.write(b.index()+" "+b.cumulative()+"\n");
		}
		bw.close();
	}

	public ArrayList<bin> get_degree_distribution(fgraph fg,ArrayList<node> node_list)
	{
		ArrayList<bin> bins=new ArrayList<bin>();
		for(node n:node_list)
		{
			//System.out.println("Node: "+n.get_name()+" Edge List Size: "+fg.get_edge_list(n).size());
			int idx=bin_position(bins,fg.get_edge_list(n).size());  
			bins.get(idx).increment_frequency();
		}
		return bins;

	}

	public int bin_position(ArrayList<bin> bins,int new_idx)
	{
		int idx=greatestIndexNotExceeding(bins,new_idx);
		if(bins.get(idx).index()>new_idx)
		{
			bins.add(idx,new bin(new_idx));

		}
		else if(bins.get(idx).index()<new_idx)
		{
			bins.add(idx+1,new bin(new_idx));
		}
		return idx;
	}

	private  int greatestIndexNotExceeding(ArrayList<bin> bins, int new_idx) 
	{
		if(bins.size()>0)
		{

			for(int i=0;i<bins.size();i++)
			{
				if(bins.get(i).index()<new_idx)
				{
					if((i+1)>=bins.size())
					{
						return bins.size()-1;
					}
					else if(bins.get(i+1).index()>new_idx)
					{
						return i;
					}
				}
				else if(bins.get(i).index()==new_idx)
				{
					return i;
				}
				else
				{
					return i;
				}
			}
		}
		else
		{
			bins.add(new bin(new_idx));
		}

		return 0;
	}

	public double avg_distance(fgraph g,ArrayList<node> nodes)
	{
		int[][] path=new int[nodes.size()][nodes.size()];
		for(int i=0;i<nodes.size();i++)
		{
			for(int j=0;j<nodes.size();j++)
			{
				if(g.is_connected(nodes.get(i), nodes.get(j)))
				{
					path[i][j]=1;
					path[j][i]=1;
				}
				else
				{
					path[i][j]=10000;
					path[j][i]=10000;
				}
			}

		}

		floyd_warshall(path);
		
		double d=0;
		for(int i=0;i<nodes.size();i++)
		{
			for(int j=0;j<nodes.size();j++)
			{
				d+=path[i][j];
			}
		}

		return (double)d/(Math.pow(nodes.size(),2));
	}

	private void floyd_warshall(int[][]path)
	{
		for(int k=0;k<path.length;k++)
		{
			for(int i=0;i<path.length;i++)
			{
				for(int j=0;j<path.length;j++)
				{
					path[i][j]=Math.min(path[i][j],path[i][k]+path[k][j]);
				}
			}
		}
	}


}

class bin {

	private int index=0,frequency=0;
	private double normalized_frequency=0,cumulative=0;

	public bin(int index)
	{
		this.index=index;
	}

	public void add_cumulative_frequency(double add)
	{
		this.cumulative+=this.normalized_frequency+add;
	}

	public double cumulative()
	{
		return this.cumulative;
	}

	public void increment_frequency()
	{
		this.frequency++;
	}

	public int frequency()
	{
		return this.frequency;
	}

	public int index()
	{
		return this.index  ;
	}

	public void normalize(int count)
	{
		this.normalized_frequency=(double)(this.frequency)/count;

	}

	public double normalized_frequency()
	{
		return this.normalized_frequency;
	}





}
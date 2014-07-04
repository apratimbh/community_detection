package community_analysis;

import graph.community;
import graph.edge;
import graph.fgraph;
import graph.node;

import java.util.ArrayList;
import java.util.HashMap;

public class trp {

	public void main(fgraph fg,ArrayList<community> g,ArrayList<community> p)
	{
		int cnt=0;
		for(community c:g)
		{
			System.out.println("G community : "+cnt+" TRP: "+compute_trp(fg,c));
			cnt++;
		}
		
		cnt=0;
		
		for(community c:p)
		{
			System.out.println("P community : "+cnt+" TRP: "+compute_trp(fg,c));
			cnt++;
		}
	}

	public double compute_trp(fgraph fp,community c)
	{
		int cnt=0;
		node u,v,w;
		HashMap<node,Integer> in_triangle=new HashMap<node,Integer>();
		main_loop: for(int i=0;i<c.size();i++)
		{
			u=c.get_node(i);
			if(!in_triangle.containsKey(u))
			{
				ArrayList<edge> u_edges=fp.get_edge_list(u);
				for(edge e1:u_edges)
				{
					if((e1.get_n1()==u&&c.contains(e1.get_n2()))||(e1.get_n2()==u&&c.contains(e1.get_n1())))
					{
						if(e1.get_n1()==u&&c.contains(e1.get_n2()))
						{
							v=e1.get_n2();
						}
						else
						{
							v=e1.get_n1();
						}
						ArrayList<edge> v_edges=fp.get_edge_list(v);

						for(edge e2:v_edges)
						{
							if((e2.get_n1()==v&&c.contains(e2.get_n2())&&e2.get_n2()!=u)||(e2.get_n2()==v&&c.contains(e1.get_n1())&&e2.get_n1()!=u))
							{
								if(e2.get_n1()==v&&c.contains(e2.get_n2()))
								{
									w=e1.get_n2();
								}
								else
								{
									w=e1.get_n1();
								}

								if(fp.is_connected(u, w))
								{
									cnt++;
									in_triangle.put(u, 1);
									in_triangle.put(v, 1);
							 		in_triangle.put(w, 1);
									continue main_loop;
								}
							}
						}
					}
				}
			}
		}
		
		return (double)cnt/c.size();
	}

}

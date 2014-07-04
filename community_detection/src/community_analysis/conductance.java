package community_analysis;

import graph.community;
import graph.edge;
import graph.fgraph;

import java.util.ArrayList;

public class conductance {
	
	public void main(fgraph fg,ArrayList<community> g,ArrayList<community> p)
	{
		int cnt=0;
		
		ArrayList<vector>  g_v_list=new ArrayList<vector>();
		for(community c:g)
		{
			double d=compute_conductance(fg,c);
			vector vtmp=new vector(1);
			vtmp.initialize(d);
			g_v_list.add(vtmp);
			System.out.println("G community : "+cnt+" Conductance: "+d);
			cnt++;
		}
		
		System.out.println(" 1 ");
		mean m=new mean();
		System.out.println(" 2 ");
		starndard_deviation std=new starndard_deviation();
		System.out.println(" 3 ");
		
		vector mu=m.compute(g_v_list);
		mu.print("Mean");
		System.out.println(" 4 ");
		double sigma=std.compute(g_v_list, mu);
		System.out.println("Sigma: "+sigma);
		System.out.println(" 5 ");
		
		exp_simi es=new exp_simi();
		
		cnt=0;
		
		for(community c:p)
		{
			double d=compute_conductance(fg,c);
			vector vtmp=new vector(1);
			vtmp.initialize(d);
			System.out.println("P community : "+cnt+" Conductance: "+d);
			System.out.println("Similarity: "+es.compute(mu, sigma, vtmp));
			
			cnt++;
		}
	}
	
	private double compute_conductance(fgraph fg,community com)
	{
		double m=0,c=0;
		for(int i=0;i<fg.get_number_of_edges();i++)
		{
			edge e=fg.get_edge(i);
			if(com.contains(e.get_n1())&&com.contains(e.get_n2()))
			{
				m++;
			}
			else if((com.contains(e.get_n1())&&!com.contains(e.get_n2()))||(!com.contains(e.get_n1())&&com.contains(e.get_n2())))
			{
				c++;
			}
		}
		return (c/(m+c));
	}
	
	

}

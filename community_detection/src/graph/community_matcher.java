package graph;

import java.util.ArrayList;
import java.util.HashMap;

public class community_matcher {

	ArrayList<community_match_pair> match_list=new ArrayList<community_match_pair>();
	HashMap<community,Integer> is_matched=new HashMap<community,Integer>();
	ArrayList<community> g,p;

	public community_matcher(ArrayList<community> g,ArrayList<community> p)
	{
		this.g=g;
		this.p=p;
	}

	public void map_g_to_p(community a,community b)
	{
		this.match_list.add(new community_match_pair(a,b));
	}

	public void map_p_to_g(community a,community b)
	{
		this.match_list.add(new community_match_pair(b,a ));
	}

	public community get_match(community a)
	{
		if(is_matched.containsKey(a))
		{
			return match_list.get(is_matched.get(a)).ground_truth_community();
		}
		else
		{

			double max=0;
			int idx=0;
			int max_idx=0;
			for(community ct:g)
			{
				double curr_ratio=match_ratio(ct,a);
				if(curr_ratio>max)
				{
					max=curr_ratio;
					max_idx=idx;
				}
				idx++;
			}
			community_match_pair cmpt=new community_match_pair(g.get(max_idx),a);
			cmpt.set_match_ratio(max);
			match_list.add(cmpt);
			this.is_matched.put(a, match_list.size()-1);
			return g.get(max_idx);

		}
	}

	public int get_match_idx(community a)
	{
		community b=get_match(a);
		if(b!=null&&g.contains(b))
		{
			return g.indexOf(b);
		}
		else if(b!=null&&p.contains(b))
		{
			return p.indexOf(b);
		}
		else
		{
			return -1;
		}
	}

	public double match_ratio(community a,community b)
	{
		if(is_matched.containsKey(b))
		{
			return match_list.get(is_matched.get(b)).get_match_ratio();
		}
		else if(g.contains(a)&&p.contains(b))
		{
			return ((double)size_of_intersection(a,b)/size_of_union(a,b));
		}
		return 0;
	}

	public int size_of_intersection(community a,community b)
	{
		int c=0;
		for(int i=0;i<a.size();i++)
		{
			if(b.contains(a.get_node(i)))
			{
				c++;
			}
		}
		return c;
	}

	public int size_of_union(community a,community b)
	{
		int c=0;
		for(int i=0;i<a.size();i++)
		{
			if(!b.contains(a.get_node(i)))
			{
				c++;
			}
		}
		return (c+b.size());
	}

	public void print_communities(community a,community b)
	{
		for(int i=0;i<a.size();i++)
		{
			System.out.print(a.get_node(i).num+"\t");
		}
		for(int i=0;i<b.size();i++)
		{
			System.out.print(b.get_node(i).num+"\t");
		}
	}

	public boolean is_matched(community a)
	{
		for(community_match_pair cmpt: this.match_list )
		{
			if(cmpt.predicted_community()==a)
			{
				return true;
			}
		}
		return false;
	}

}

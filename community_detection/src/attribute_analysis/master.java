package attribute_analysis;

import graph.community;
import graph.create_communities;
import graph.create_fgraph;
import graph.feature;
import graph.fgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import corrrespondence_analysis.ca_table;

public class master {

	public static void main(String[] args)
	{
		master m=new master();
		m.main();
	}

	public void main()
	{
		String[] comm_names={"0","107"};
		for(int q=0;q<comm_names.length;q++)
		{
			create_fgraph cfgh=new create_fgraph();
			create_communities ccm=new create_communities();
			try {
				fgraph fg=cfgh.create("C:\\cygwin\\home\\cse\\CAA\\snap-master\\examples\\cesna\\0.edges","C:\\cygwin\\home\\cse\\CAA\\snap-master\\examples\\cesna\\"+comm_names[q]+".nodefeat");
				ArrayList<community> g=ccm.create_communities(fg, "C:\\cygwin\\home\\cse\\CAA\\snap-master\\examples\\cesna\\"+comm_names[q]+".circles");
				ArrayList<community> p=ccm.create_communities(fg, "C:\\cygwin\\home\\cse\\CAA\\snap-master\\examples\\cesna\\"+comm_names[q]+"cmtyvv.txt_n");
				//print_communities(g);
				//print_communities(p);
				System.out.println("Sizes: g: "+g.size()+" p: "+p.size());
				ca_table t1=new ca_table(g.size(),fg.get_number_of_features());
				ca_table t2=new ca_table(p.size(),fg.get_number_of_features());

				int c=0;
				for(community ct:g)
				{
					for(int i=0;i<ct.size();i++)
					{
						ArrayList<feature> tfeat=fg.get_features_of_node(i);
						for(feature tf:tfeat)
						{
							t1.increment(c, tf.index());
						}
					}
					c++;
				}
				c=0;
				for(community ct:p)
				{
					for(int i=0;i<ct.size();i++)
					{
						ArrayList<feature> tfeat=fg.get_features_of_node(i);
						for(feature tf:tfeat)
						{
							t2.increment(c, tf.index());
						}
					}
					c++;
				}

				ArrayList<Integer> top_feat=top_features_overall(10,t1,t2);

				String ca_ground_table="C:\\R\\0_ground.txt";
				String ca_predicted_table="C:\\R\\0_detected.txt";

				Random r=new Random();

				int rndm_file_n=r.nextInt();

				t1=reshape_ca_table(t1,top_feat);
				t2=reshape_ca_table(t2,top_feat);

				//create_arff_file carf=new create_arff_file();
				//carf.from_ca_table(t1, "C:\\R\\p.arff");

				profile_clustering pct=new profile_clustering();
				pct.main(t1);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}



	}

	public ArrayList<Integer> intersection(ArrayList<Integer> l1,ArrayList<Integer> l2)
	{

		ArrayList<Integer> l3=new ArrayList<Integer>();

		for(int tmp:l1)
		{
			if(l2.contains(tmp))
			{
				l3.add(tmp);
			}
		}

		return l3;
	}

	public ca_table reshape_ca_table(ca_table t,ArrayList<Integer> col)
	{
		ca_table tmp=new ca_table(t.rows(),col.size());
		for(int j=0;j<col.size();j++)
		{
			for(int i=0;i<t.rows();i++)
			{
				tmp.put(i, j, t.get(i, col.get(j)));
			}
		}
		return tmp;

	}

	public ArrayList<Integer> top_features_overall(int num,ca_table t1,ca_table t2)
	{
		int[] t1_totals=new int[t1.columns()];
		int[] t2_totals=new int[t1.columns()];
		int[] t1_totals_copy=new int[t1.columns()];
		int[] t2_totals_copy=new int[t1.columns()];

		for(int i=0;i<t1.columns();i++)
		{
			t1_totals[i]=t1.column_total(i);
			t1_totals_copy[i]=t1.column_total(i)*-1;
		}

		for(int i=0;i<t2.columns();i++)
		{
			t2_totals[i]=t2.column_total(i);
			t2_totals_copy[i]=t2.column_total(i)*-1;
		}

		Arrays.sort(t1_totals_copy);

		Arrays.sort(t2_totals_copy);

		HashMap<Integer,Integer> t1_map=new HashMap<Integer,Integer>();

		HashMap<Integer,Integer> t2_map=new HashMap<Integer,Integer>();

		for(int i=0;i<num;i++)
		{
			t1_map.put(t1_totals_copy[i]*-1, 1);
			t2_map.put(t2_totals_copy[i]*-1, 1);
		}

		ArrayList<Integer> t1_list=new ArrayList<Integer>();
		ArrayList<Integer> t2_list=new ArrayList<Integer>();

		int c=0;
		for(int i=0;i<t1_totals.length&&c<num;i++)
		{
			if(t1_map.containsKey(t1_totals[i]))
			{
				t1_list.add(i);
				c++;
			}
		}
		c=0;

		for(int i=0;i<t2_totals.length&&c<num;i++)
		{
			if(t2_map.containsKey(t2_totals[i]))
			{
				t2_list.add(i);
				c++;
			}
		}

		return intersection(t1_list,t2_list);
	}

}

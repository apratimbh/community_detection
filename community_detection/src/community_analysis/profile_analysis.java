package community_analysis;

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_2D;
import graph.community;
import graph.feature;
import graph.fgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import corrrespondence_analysis.ca_table;

public class profile_analysis {
	
	public void main(fgraph fg,ArrayList<community> g,ArrayList<community> p)
	{
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
		
		t1=reshape_ca_table(t1,top_feat);
		t2=reshape_ca_table(t2,top_feat);
		
		for(int i=0;i<t1.rows();i++)
		{
			System.out.println("-------------------------");
			double[] profile=t1.row_profile(i);
			print_dft_row(profile,"Profile: "+i+" ");
			DoubleFFT_1D ddft=new DoubleFFT_1D(profile.length); 
			double[] tmp_profile=new double[profile.length*2];
			for(int j=0;j<profile.length;j++)
			{
				tmp_profile[j]=profile[j];
			}
			ddft.realForwardFull(tmp_profile);
			System.out.println();
			print_dft_row(tmp_profile,"DFT: "+i+" ");
			System.out.println("-------------------------");
		}
		
		for(int i=0;i<t2.rows();i++)
		{
			System.out.println("-------------------------");
			double[] profile=t2.row_profile(i);
			/*System.out.println(t2.get_row_string(i));
			System.out.println(t2.row_total(i));
			print_sum(profile,i+" ");
			print_dft_row(profile,i+" ");*/
			print_dft_row(profile,"Profile: "+i+" ");
			DoubleFFT_1D ddft=new DoubleFFT_1D(profile.length); 
			double[] tmp_profile=new double[profile.length*2];
			for(int j=0;j<profile.length;j++)
			{
				tmp_profile[j]=profile[j];
			}
			System.out.println();
			ddft.realForwardFull(tmp_profile);
			print_dft_row(tmp_profile,"DFT: "+i+" ");
			System.out.println("-------------------------");
		}
		
	}
	
	public void print_sum(double[] dft_row,String title)
	{
		double t=0;
		System.out.println(title+"\n");
		for(int i=0;i<dft_row.length;i++)
		{
			t+=dft_row[i];
		}
		System.out.println("Sum: "+t+"\n");
	}
	
	public void print_dft_row(double[] dft_row,String title)
	{
		System.out.println(title+"\n");
		for(int i=0;i<dft_row.length;i++)
		{
			System.out.print(dft_row[i]+"\t");
		}
		System.out.println();
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

}

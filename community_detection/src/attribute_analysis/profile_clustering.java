package attribute_analysis;

import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.InstanceTools;
import corrrespondence_analysis.ca_table;

public class profile_clustering {
	
	public void main(ca_table ta)
	{
		Dataset data = new DefaultDataset();
		System.out.println("Number of communities: "+ta.rows());
		for(int i=0;i<ta.rows();i++)
		{
			Instance tist = new DenseInstance(ta.row_profile(i));
		    data.add(tist);
		}
		int len=ta.row_profile(0).length;
		for(int i=0;i<ta.rows();i++)
		{
			Instance tist = InstanceTools.randomInstance(len);
			data.add(tist);
		}
		
		Clusterer km = new KMeans();
		System.out.println("Clustering ..... ");
		Dataset[] clusters = km.cluster(data);
		System.out.println("Printing ..... ");
		for(Dataset dst:clusters)
		{
			
			int cnt=0;
			for(int j=0;j<17;j++)
			{
				if(dst.contains(data.get(j)))
				{
					cnt++;
				}
			}
			System.out.println("Size: "+dst.size()+" Contains: "+cnt);
			/*for(Instance ist:dst)
			{
				System.out.println(ist);
			}*/
		}
		
	}

}

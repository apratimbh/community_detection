package community_analysis;

import graph.community;
import graph.create_communities;
import graph.create_fgraph;
import graph.edge;
import graph.fgraph;

import java.util.ArrayList;

public class master {
	
	public static void main(String[] args)
	{
		create_fgraph cfgh=new create_fgraph();
		create_communities ccm=new create_communities();
		try {
			fgraph fg=cfgh.create("C:\\cygwin\\home\\cse\\CAA\\snap-master\\examples\\cesna\\0.edges","C:\\cygwin\\home\\cse\\CAA\\snap-master\\examples\\cesna\\0.nodefeat");
			ArrayList<community> g=ccm.create_communities(fg, "C:\\cygwin\\home\\cse\\CAA\\snap-master\\examples\\cesna\\0.circles");
			ArrayList<community> p=ccm.create_communities(fg, "C:\\cygwin\\home\\cse\\CAA\\snap-master\\examples\\cesna\\cmtyvv.txt_n");
			//degree_distribution dd=new degree_distribution();
			//dd.main(fg, g, p);
			//profile_analysis pd=new profile_analysis();
			//pd.main(fg, g, p);
			conductance cn=new conductance();
			cn.main(fg, g, p);
			//trp t=new trp();
			//t.main(fg, g, p);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}

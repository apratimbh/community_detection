package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class create_communities {
	
	public ArrayList<community> create_communities(graph g,String community_file) throws IOException
	{
		ArrayList<community> list=new ArrayList<community>();
		String line="";
		BufferedReader br=new BufferedReader(new FileReader(community_file));
		while((line=br.readLine())!=null)
		{
			if(!line.matches("^[0-9[ ]]+"))
			{
				throw new IOException("Error in file format");
			}
			
			String[] part=line.split("");
			community c=new community();
			
			for(String tmp:part)
			{
				c.add_node(g.get_node(Integer.parseInt(tmp)));
			}
			
			list.add(c);
			
		}
		br.close();
		return list;

	}

}

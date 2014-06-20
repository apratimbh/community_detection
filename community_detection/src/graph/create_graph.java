package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class create_graph {

	public graph create(String edge_file) throws IOException
	{
		graph g=new graph();
		String line="";
		BufferedReader br=new BufferedReader(new FileReader(edge_file));
		while((line=br.readLine())!=null)
		{

			if(!line.matches("^[0-9]+[\t][0-9]+"))
			{
				throw new IOException("Error in file format");
			}

			String[] part=line.split("\t");
			
			node n1=null,n2=null;
			
			if(!g.if_node_exists(Integer.parseInt(part[0])))
			{
				n1=new node(Integer.parseInt(part[0]));
				g.add_node(n1);
			}
			else
			{
				n1=g.get_node(Integer.parseInt(part[0]));
			}

			if(!g.if_node_exists(Integer.parseInt(part[1])))
			{
				n2=new node(Integer.parseInt(part[1]));
				g.add_node(n2);
			}
			else
			{
				n2=g.get_node(Integer.parseInt(part[1]));
			}
			
			edge e=new edge(n1,n2);
			
			g.add_edge(e);
			
		}
		br.close();
		return g;
	}

}

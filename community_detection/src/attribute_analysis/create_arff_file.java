package attribute_analysis;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import corrrespondence_analysis.ca_table;

public class create_arff_file {
	
	public void from_ca_table(ca_table ta,String file_name) throws IOException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter(file_name));
		/*bw.write("@RELATION 'community'\n");
		for(int i=0;i<ta.rows()+1;i++)
		{
			bw.write("@ATTRIBUTE attr"+i+" numeric\n");
		}
		bw.write("@DATA\n");*/
		for(int j=0;j<ta.columns();j++)
		{
			bw.write("attr"+j+",");
		}
		bw.write("attr"+(ta.columns())+"\n");
		for(int i=0;i<ta.rows();i++)
		{
			bw.write(ta.row_profile_string(i, ",")+"1\n");
		}
		Random r=new Random();
		for(int i=0;i<ta.rows();i++)
		{
			int t=0;
			int[] tmp=new int[ta.columns()];
			for(int j=0;j<ta.columns();j++)
			{
				tmp[j]=Math.abs(r.nextInt(1000));
				t+=tmp[j];
			}
			String stmp="";
			for(int j=0;j<ta.columns();j++)
			{
				stmp+=((double)tmp[j]/t)+",";
			}
			stmp+="0";
			bw.write(stmp+"\n");
		}
		bw.close();
	}

}

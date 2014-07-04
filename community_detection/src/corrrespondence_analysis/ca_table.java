package corrrespondence_analysis;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ca_table {
	
	private int[][] table=null;
	
	public ca_table(int h,int l)
	{
		table=new int[h][l];
	}

	public void put(int i,int j,int n)
	{
		table[i][j]=n;
	}
	
	public int get(int i,int j)
	{
		return table[i][j];
	}
	
	public void increment(int i,int j)
	{
		table[i][j]++;
	}
	
	public void write_to_file(String file_name) throws IOException
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter(file_name));
		for(int i=0;i<this.table.length;i++)
		{
			bw.write(get_row_string(i)+"\n");
		}
		bw.close();
		
	}
	
	public String get_row_string(int i)
	{
		String tmp="";
		for(int j=0;j<this.table[i].length;j++)
		{
			tmp+=this.table[i][j]+" ";
		}
		return tmp;
	}
	
	public int column_total(int n)
	{
		int t=0;
		for(int j=0;j<this.table.length;j++)
		{
			t+=this.table[j][n];
		}
		return t;
	}
	
	public int row_total(int m)
	{
		int t=0;
		for(int i=0;i<this.table[m].length;i++)
		{
			t+=this.table[m][i];
		}
		return t;
	}
	
	public double[] row_profile(int i)
	{
		int t=row_total(i);
		double[] tprofile=new double[this.table[i].length];
		for(int j=0;j<tprofile.length;j++)
		{
			tprofile[j]=(double)this.table[i][j]/t;
		}
		return tprofile;
	}
	
	public String row_profile_string(int i,String sep)
	{
		double[] arr=row_profile(i);
		
		String tmp="";
		for(int j=0;j<arr.length;j++)
		{
			tmp+=arr[j]+sep;
		}
		return tmp;
		
	}
	
	
	public int columns()
	{
		return this.table[0].length;
	}
	
	public int rows()
	{
		return this.table.length;
	}
	
}

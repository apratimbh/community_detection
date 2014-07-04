package corrrespondence_analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ca_coordinates {
	
	ArrayList<coordinate> row_coordinates=new ArrayList<coordinate>();
	ArrayList<coordinate> column_coordinates=new ArrayList<coordinate>();
	
	public ca_coordinates(double[][] row,double[][] col)
	{
		for(int i=0;i<row.length;i++)
		{
			row_coordinates.add(new coordinate(row[i][0],row[i][0]));
		}
		
		for(int i=0;i<col.length;i++)
		{
			column_coordinates.add(new coordinate(col[i][0],col[i][0]));
		}
		
	}
	
	public double[] distance_array(int x)
	{
		double[] dis=new double[column_coordinates.size()];
		
		for(int i=0;i<column_coordinates.size();i++)
		{
			dis[i]=c_distance(row_coordinates.get(x),column_coordinates.get(i));
		}
		return dis;		
	}
	
	public int[] rank_array(int x)
	{
		double[] dis=distance_array(x);
		int[] ranks=new int[dis.length];
		
		double[] dis_copy=new double[dis.length];
		
		for(int i=0;i<dis.length;i++)
		{
			dis_copy[i]=dis[i]*-1;
		}
		
		Arrays.sort(dis_copy);
		
		HashMap<Double,Integer> rank_map=new HashMap<Double,Integer>();
		
		for(int i=0;i<dis.length;i++)
		{
			rank_map.put(dis_copy[i]*-1, i+1);
		}
		
		for(int i=0;i<dis.length;i++)
		{
			ranks[i]=rank_map.get(dis[i]);
		}
		
		return ranks;
		
	}
	
	private double c_distance(coordinate a,coordinate b)
	{
		return Math.sqrt(Math.pow((a.x()-b.x()),2)+Math.pow((a.y()-b.y()),2));
	}

}

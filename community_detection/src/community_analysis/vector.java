package community_analysis;

import java.io.IOException;

public class vector {

	private int size=0;
	private double[] data;

	public int size()
	{
		return this.size;
	}
	
	public vector(int size)
	{
		this.size=size;
		data=new double[size];
	}

	public void initialize(double x) 
	{
		if(this.size==1)
		{
			this.data[0]=x;
		}
		else
		{
			System.out.println("Size mismatch 1");
			System.exit(0);
		}
	}

	public void initialize(int[] x) 
	{
		if(x.length==this.size)
		{
			for(int i=0;i<x.length;i++)
			{
				this.data[i]=x[i];
			}
		}
		else
		{
			System.out.println("Size mismatch 2");
			System.exit(0);
		}
	}

	public void initialize(double[] x) 
	{
		if(x.length==this.size)
		{
			for(int i=0;i<x.length;i++)
			{
				this.data[i]=x[i];
			}
		}
		else
		{
			System.out.println("Size mismatch 3");
			System.exit(0);
		}
	}
	
	public void copy(vector x) 
	{
		if(x.size()==this.size)
		{
			for(int i=0;i<x.size();i++)
			{
				this.data[i]=x.get(i);
			}
		}
		else
		{
			System.out.println("Size mismatch 4");
			System.exit(0);
		}
	}

	public double get(int i)
	{
		if(i<this.data.length)
		{
			return this.data[i];
		}
		else
		{
			System.exit(0);
		}
		return 0;
	}

	public void add(vector v)
	{
		if(v.size==this.size)
		{
			for(int i=0;i<v.size;i++)
			{
				this.data[i]+=v.get(i);
			}
		}
		else
		{
			System.out.println("Size mismatch 5");
			System.exit(0);
		}
	}

	public void substract(vector v)
	{
		if(v.size==this.size)
		{
			for(int i=0;i<v.size;i++)
			{
				this.data[i]-=v.get(i);
			}
		}
		else
		{
			System.out.println("Size mismatch 6");
			System.exit(0);
		}
	}

	public void divide(double d)
	{
		for(int i=0;i<this.size;i++)
		{
			this.data[i]/=d;
		}

	}

	public void square()
	{
		for(int i=0;i<this.size;i++)
		{
			this.data[i]=Math.pow(this.data[i], 2);
		}

	}
	
	public double mod()
	{
		double d=0;
		for(int i=0;i<this.size;i++)
		{
			d+=Math.pow(this.data[i],2);
		}
		return Math.sqrt(d);
	}
	
	public void print(String title)
	{
		System.out.println(" < "+title+" > ");
		for(int i=0;i<this.size;i++)
		{
			System.out.print(this.data[i]+"\t");
		}
		System.out.println();
	}

}

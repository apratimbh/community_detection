package dft;

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_2D;


public class dft {
public static void main(String[] args)
{
	double[][] mat={{1,2,3,4},
		{2,3,4,5}};
	DoubleFFT_2D ddft=new DoubleFFT_2D(mat.length,mat[0].length/2); 
	ddft.complexForward(mat);
	double[][] real=to_real(mat);
	for(int i=0;i<real.length;i++)
	{
		for(int j=0;j<real[0].length;j++)
		{
			System.out.print(real[i][j]+" ");
		}
		System.out.println();
	}
}
public static double[][] expand_matrix(double[][] mat)
{
	double[][] tmp=new double[mat.length][2*mat[0].length];
	for(int i=0;i<mat.length;i++)
	{
		for(int j=0;j<mat[0].length;j++)
		{
			tmp[i][j]=mat[i][j];
		}
	}
	return tmp;
}

public static double[][] get_contracted_matrix(double[][] mat)
{
	double[][] tmp=new double[mat.length][mat[0].length/2];
	return tmp;
}

public static double[][] to_real(double[][] mat)
{
	double[][] tmp=new double[mat.length][mat[0].length/2];
	for(int i=0;i<mat.length;i++)
	{
		for(int j=0;j<mat[0].length;j+=2)
		{
			try {
				tmp[i][j/2]=Math.sqrt(Math.pow(mat[i][j],2)+Math.pow(mat[i][j+1],2));
			}
			catch(Exception e)
			{
				System.out.println(i+" "+(j/2));
			}
		}
	}
	return tmp;
}
}

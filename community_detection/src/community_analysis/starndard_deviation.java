package community_analysis;

import java.util.ArrayList;

public class starndard_deviation {
	
	public double compute(ArrayList<vector> v_list,vector mu)
	{
		vector sigma=new vector(mu.size());
		for(vector v:v_list)
		{
			vector tmp=new vector(mu.size());
			tmp.copy(mu);
			tmp.substract(v);
			tmp.square();
			sigma.add(tmp);
		}
		sigma.divide(v_list.size());
		return sigma.mod();
	}

}

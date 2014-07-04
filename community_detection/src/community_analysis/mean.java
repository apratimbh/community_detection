package community_analysis;

import java.util.ArrayList;

public class mean {
	

	public vector compute(ArrayList<vector> v_list)
	{
		vector mu=new vector(v_list.get(0).size());
		for(vector v:v_list)
		{
			mu.add(v);
		}
		mu.divide(v_list.size());
		return mu;
	}
	
}

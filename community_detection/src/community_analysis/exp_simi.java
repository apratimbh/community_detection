package community_analysis;

public class exp_simi {
	
	public double compute(vector mu,double sigma,vector x)
	{
		vector tmp=new vector(mu.size());
		tmp.copy(mu);
		tmp.substract(x);
		tmp.square();
		
		return (Math.exp(-(Math.pow(tmp.mod(),2)/(1.5*Math.pow(sigma, 1)))));
	}

}

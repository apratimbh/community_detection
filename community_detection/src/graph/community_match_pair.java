package graph;

public class community_match_pair {
	
	private community cgt,cp;
	private double match_ratio=-1,attribute_similarity=-1,profile_similarity=-1;
	
	public community_match_pair(community g,community p)
	{
		this.cgt=g;
		this.cp=p;
	}
	
	public community ground_truth_community()
	{
		return cgt;
	}
	
	public community predicted_community()
	{
		return cp;
	}
	
	public void set_match_ratio(double d)
	{
		this.match_ratio=d;
	}
	
	public double get_match_ratio()
	{
		return this.match_ratio;
	}
	
	public void set_attribute_similarity(double d)
	{
		this.attribute_similarity=d;
	}
	
	public double get_attribute_similarity()
	{
		return this.attribute_similarity;
	}
	
	public void set_profile_similarity(double d)
	{
		this.profile_similarity=d;
	}
	
	public double get_profile_similarity()
	{
		return this.profile_similarity;
	}
	
}

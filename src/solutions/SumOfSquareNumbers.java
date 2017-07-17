// Leetcode 
// Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

class Solution{

  public boolean judgeSquareSum(int c) {
        
		double dc = (double)c;
		int limit = (int)Math.abs(Math.sqrt(dc));
		int i = 1;
		
		while(i<=limit)
		{
			int sqr = i*i;
			double rem = Math.sqrt(c - sqr);
			if(rem%1==0)
				return true;
			i++;
		}
		return false;
  }
}

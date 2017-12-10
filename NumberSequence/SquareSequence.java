public class SquareSequence implements NumberSequence
{
	int current;
	
	public SquareSequence()
	{
		current = 0;
	}
	
	public long next()
	{
		int tmp = current;
		current++;
		return (long)Math.pow(tmp, 2);
	}

	public double average()
	{
		return 0;
	}
}

public class SequenceOf implements NumberSequence
{
	private long[] s;
	private int ptr;
	
	public SequenceOf(long[] a)
	{
		s = a;
	}
	
	public long next()
	{
		if (hasNext())
		{
			ptr++;
			return s[ptr-1];
		}
		else
		{
			throw new IllegalStateException();
		}
	}
	
	public boolean hasNext()
	{
		try
		{
			long tmp = s[ptr];
			return true;
		}
		catch (Exception ex)
		{
			return false;
		}
	}

	public double average()
	{
		double sum = 0;
		for (int i = 0; i < s.length; i++)
		{
			sum = sum + s[i];
		}
		return sum/s.length;
	}

}

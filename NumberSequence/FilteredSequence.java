import java.util.function.LongPredicate;

public class FilteredSequence implements NumberSequence
{
	private NumberSequence s;
	private LongPredicate p;
	
	public FilteredSequence(NumberSequence s, LongPredicate p)
	{
		this.s = s;
		this.p = p;
	}
	
	public long next()
	{
		if (hasNext())
		{
			long tmp = s.next();
			while (!p.test(tmp)) tmp = s.next();
			return tmp;
		}
		else { throw new IllegalStateException(); }
	}
	

	public double average()
	{
		return s.average();
	}

}

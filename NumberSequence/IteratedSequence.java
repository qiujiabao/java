import java.util.function.LongUnaryOperator;

public class IteratedSequence implements NumberSequence
{
	private LongUnaryOperator f;
	private long ptr;
	
	public IteratedSequence(long seed, LongUnaryOperator f)
	{
		ptr = seed;
		this.f = f;
	}
	
	public long next()
	{
		long tmp = ptr;
		ptr = f.applyAsLong(ptr);
		return tmp;
	}
	
	public double average()
	{
		return 0;
	}

}

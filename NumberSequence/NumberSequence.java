import java.util.function.LongPredicate;
import java.util.function.LongUnaryOperator;

/**
 * An interface use to process a number sequence,
 * with your choice of sub types of the sequence.
 * It can calculate average number and filter numbers.
 */
public interface NumberSequence
{
	/**
	 * Return the next value in the sequence.
	 * @return the next value
	 */
   long next();
   
   /**
    * Calculate the average of all numbers in a sequence.
    * This method can only be used for a finite sequence.
    * @return the average number
    */
   double average();
   
   /**
    * Create a finite sequence with provided numbers.
    * @param args an array of longs to construct the sequence
    * @return the new sequence
    */
   static NumberSequence of(long... args)
   {
	   SequenceOf s = new SequenceOf(args);
	   return (NumberSequence)s;
   }
   
   /**
    * To calculate the average of the first n numbers of a sequence.
    * If the sequence is smaller than you enter, it will calculate
    * the average of all numbers.
    * @param n how many numbers you want from the most left
    * @return the average of the first n numbers
    */
   default double average(int n)
   {
	   long sum = 0;
	   int amt = n;
	   for (int i = 0; i < n; i++)
	   {
		   if (hasNext()) { sum = sum + next(); }
		   else
		   {
			   amt = i;
			   break;
		   }
	   }
	   return sum/amt;
   }
   
   /**
    * To yield an array of the first n numbers of a sequence.
    * If the sequence is smaller than you enter, it will construct
    * a smaller array than you entered, and add all numbers.
    * @param n how many numbers you want
    * @return the array of first n numbers
    */
   default long[] toArray(int n)
   {
	   long[] a = new long[n];
	   boolean shorter = false;
	   int size = 0;
	   for (int i = 0; i < n; i++)
	   {
		   if (hasNext())
		   {
			   a[i] = next();
			   size = i;
		   }
		   else
		   {
			   shorter = true;
			   size = i;
			   break;
		   }
	   }
	   if (shorter)
	   {
		   long[] b = new long[size];
		   for (int j = 0; j < size; j++)
		   { b[j] = a[j]; }
		   return b;
	   }
	   else
	   {
		   return a;
	   }
   }
   
   /**
    * Determine if the sequence have next value.
    * It is overridden by sub classes.
    * @return if the sequence have next value.
    */
   default boolean hasNext()
   {
	   return true;
   }
   
   /**
    * To filter an exist sequence.
    * @param p the long predicate expression of requirement
    * @return the filtered sequence.
    */
   default NumberSequence filter(LongPredicate p)
   {
	   FilteredSequence f = new FilteredSequence(this, p);
	   return (NumberSequence)f;
   }
   
   /**
    * To iterate a sequence with requirement.
    * @param seed the beginning value
    * @param f the requirement of growing of the numbers
    * @return the iterated sequence
    */
   static NumberSequence iterate(long seed, LongUnaryOperator f)
   {
	   IteratedSequence s = new IteratedSequence(seed, f);
	   return (NumberSequence)s;
   }
   
   /**
    * Iterate a sequence with random number.
    * @param seed the beginning number
    * @return the random sequence
    */
   static NumberSequence random(long seed)
   {
	   LongUnaryOperator f = x -> (x * 1103515245 + 12345) % (long)Math.pow(2, 31);
	   NumberSequence s = NumberSequence.iterate(seed, f);
	   return s;
   }
   
}
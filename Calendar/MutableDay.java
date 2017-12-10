/**
 * This class has an original form of book code day1/Day.java
 */
public class MutableDay
{
   /**
      Constructs a day with a given year, month, and day
      of the Julian/Gregorian calendar. The Julian calendar
      is used for all days before October 15, 1582
      @param aYear a year != 0
      @param aMonth a month between 1 and 12
      @param aDayOfMonth a date between 1 and 31
   */
   public MutableDay(int aYear, int aMonth, int aDayOfMonth)
   {
      if (aDayOfMonth > daysPerMonth(aYear, aMonth))
      { throw new IllegalStateException(); }
      else
      {
    	  year = aYear;
          month = aMonth;
          dayOfMonth = aDayOfMonth;
      }
   }

   /**
      Returns the year of this day
      @return the year
   */
   public int getYear()
   {
	  if (dayOfMonth <= daysPerMonth(year, month))
	  { return year; }
	  else
      { throw new IllegalStateException(); }
   }

   /**
      Returns the month of this day
      @return the month
   */
   public int getMonthValue()
   {
	  if (dayOfMonth <= daysPerMonth(year, month))
	  { return month; }
	  else
      { throw new IllegalStateException(); }
   }

   /**
      Returns the day of the month of this day
      @return the day of the month
   */
   public int getDayOfMonth()
   {
	  if (dayOfMonth <= daysPerMonth(year, month))
      { return dayOfMonth; }
	  else
	  { throw new IllegalStateException(); }
   }
   
   public void setYear(int year) 
   {
      this.year = year;
   }
   
   public void setMonthValue(int month) 
   {
      this.month = month;
   }
   
   public void setDayOfMonth(int dayOfMonth) 
   {
      this.dayOfMonth = dayOfMonth;
   }
   
   public String toString()
   {
	  if (dayOfMonth > daysPerMonth(year, month))
	  { throw new IllegalStateException(); }
	  else
	  { return String.format("%04d-%02d-%02d", year, month, dayOfMonth); }
   }

   /**
      Advances this day (or moves it backwards) by a given number of days.
      @param n the number of days, can be negative
   */
   public void plusDays(int n)
   {
	  if (dayOfMonth <= daysPerMonth(year, month))
	  { 
		  while (n > 0)
		  {
		     nextDay();
		     n--;
		  }
		  while (n < 0)
		  {
		     previousDay();
		     n++;
		  }
	  }
	  else
	  { throw new IllegalStateException(); }
      
   }

   /**
      Returns the number of days between this day and another
      day
      @param other the other day
      @return the number of days that this day is away from 
      the other (>0 if this day comes later)
   */
   public int daysFrom(MutableDay other)
   {
	  if (dayOfMonth <= daysPerMonth(year, month))
	  { 
		  int n = 0;
	      int y = year;
	      int m = month;
	      int day = dayOfMonth;
	      MutableDay d = new MutableDay(y, m, day);
	      while (d.compareTo(other) > 0)
	      {
	         d.previousDay();
	         n++;
	      }
	      while (d.compareTo(other) < 0)
	      {
	         d.nextDay();
	         n--;
	      }
	      return n; 
	  }
      else
	  { throw new IllegalStateException(); }   
   }

   /**
      Compares this day with another day.
      @param other the other day
      @return a positive number if this day comes after the
      other day, a negative number if this day comes before
      the other day, and zero if the days are the same
   */
   private int compareTo(MutableDay other)
   {
      if (year > other.year) return 1;
      if (year < other.year) return -1;
      if (month > other.month) return 1;
      if (month < other.month) return -1;
      return dayOfMonth - other.dayOfMonth;
   }

   /**
      Computes the next day.
      @return the day following this day
   */
   private void nextDay()
   {
      int y = year;
      int m = month;
      int d = dayOfMonth;

      if (y == GREGORIAN_START_YEAR
            && m == GREGORIAN_START_MONTH
            && d == JULIAN_END_DAY)
         d = GREGORIAN_START_DAY;
      else if (d < daysPerMonth(y, m))
         d++;
      else
      {
         d = 1;
         m++;
         if (m > DECEMBER) 
         { 
            m = JANUARY; 
            y++; 
            if (y == 0) y++;
         }
      }
//      return new MutableDay(y, m, d);
      year = y;
      month = m;
      dayOfMonth = d;
   }

   /**
      Computes the previous day.
      @return the day preceding this day
   */
   private void previousDay()
   {
      int y = year;
      int m = month;
      int d = dayOfMonth;

      if (y == GREGORIAN_START_YEAR
            && m == GREGORIAN_START_MONTH
            && d == GREGORIAN_START_DAY)
         d = JULIAN_END_DAY;
      else if (d > 1)
         d--;
      else
      {	
         m--;
         if (m < JANUARY) 
         {             
            m = DECEMBER; 
            y--; 
            if (y == 0) y--;
         }
         d = daysPerMonth(y, m);
      }
//      return new MutableDay(y, m, d);
      year = y;
      month = m;
      dayOfMonth = d;
   }

   /**
      Gets the days in a given month
      @param y the year
      @param m the month
      @return the last day in the given month
   */
   private static int daysPerMonth(int y, int m)
   {
      int days = DAYS_PER_MONTH[m - 1];
      if (m == FEBRUARY && isLeapYear(y)) 
         days++;
      return days;
   }

   /**
      Tests if a year is a leap year
      @param y the year
      @return true if y is a leap year
   */
   private static boolean isLeapYear(int y)
   {
      if (y % 4 != 0) return false;
      if (y < GREGORIAN_START_YEAR) return true;
      return (y % 100 != 0) || (y % 400 == 0);
   }

   private int year;
   private int month;
   private int dayOfMonth;

   private static final int[] DAYS_PER_MONTH 
         = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

   private static final int GREGORIAN_START_YEAR = 1582;
   private static final int GREGORIAN_START_MONTH = 10;
   private static final int GREGORIAN_START_DAY = 15;
   private static final int JULIAN_END_DAY = 4;

   private static final int JANUARY = 1;
   private static final int FEBRUARY = 2;
   private static final int DECEMBER = 12;
}






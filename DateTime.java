import java.util.*;

class DateTime 
{  
	private static long advance; 
	// keeps tracks of any time advance    
	private long time;    
	
	//constructor
	public DateTime()  
	{   
		time = System.currentTimeMillis() + advance;    
	}    
	
	
	public long getTime() 
	{
		return time; 
	} 
 
    // advances date/time by specified days, hours and mins for testing purpose    
	public static void setAdvance(int days, int hours, int mins)    
	{   
		advance = ((days * 24L + hours) * 60L) *60000L;    
	}    
	
	
	public String toString()     
	{  
		long l = getTime();       
		Date gct =  new Date(l);              
		return gct.toString();    
	}      
	
	
	//returns current date/time
	public static String getCurrentTime()  
	{       
		Date d = new Date(System.currentTimeMillis() + advance);       
		return d.toString();    
	}    
	
	
	// returns difference in days       
	public static int diffDays(DateTime d2, DateTime d1)    
	{       
		return (int) ( 1 + ( d2.getTime() - d1.getTime() )/(24L*60*60*1000));    
	}  
	
 
} 
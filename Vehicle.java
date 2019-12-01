public class Vehicle 
{
	protected String vehicleID;
	protected static String hirerID;
	protected String description;
	protected static char status = 'A';
	protected double dailyRate;
	protected int odometerReading;
	protected static DateTime time;
	
	//constructor
	public Vehicle (String vehicleID, String description, double dailyRate, int odometer)
	{
		this.vehicleID = vehicleID;
		this.description = description;
		this.dailyRate = dailyRate;
		this.odometerReading = odometer;
		//this.hirerID = null;
	}
	
	
	//mutator for setting status
	public void setStatus (char status)
	{
		this.status = status;
	}
	
	
	//mutator for setting hirerID
	public void setHirerID (String hID)
	{
		hirerID = hID;
	}
	
	
	//accessor for vehicleID
	public String getVehicleID ()
	{
		return vehicleID;
	}
	
	
	//accessor for hirerID
	public String getHirerID ()
	{
		return this.hirerID;
	}
	
	
	//set time
	public void setTime(DateTime time)
	{
		this.time= time;
	}
	
	
	//accessor for description
	public String getDescription ()
	{
		return description;
	}
	
	
	//accessor for status
	public char getStatus ()
	{
		return status;
	}
	
	
	//accessor for dailyRate
	public double getDailyRate ()
	{
		return dailyRate;
	}
	
	
	//accessor for odometerReading
	public int getOdometerReading ()
	{
		return odometerReading;
	}
	
	
	//accessor for time
	public DateTime getTime()
	{
		return time;
	}
	
	
	//called when car is hired
	public boolean hire (String hirerID) throws IdNotFoundException, VIDNotFoundException
	{
		
		if (getStatus() == 'S' || getStatus() == 'H')
		{
			return false;
		}			
		else
		{
			setStatus('H');
			setHirerID(hirerID);
			setTime(new DateTime());
			return true;
		}
			
	}
	
	
	//called when car is sent for service
	public boolean service()
	{
		if (getStatus() == 'S' || getStatus() == 'A')
		{
			setStatus('S');
			return true;
		}
		else
			return false;
	}
	
	
	//called when car is back from service
	public boolean serviceComplete(int odo)
	{
		if (getStatus() == 'S')
		{
			this.odometerReading = odo;
			setStatus('A');
			return true;			
		}
		return false;
	}
	
	
	//called when car hire is completed
	public double hireComplete(int odo) throws VIDNotFoundException, StatusException, OdometerException 
	{
		int iniOdo = getOdometerReading();
		int timeDiff;
		if (getStatus() == 'H' && odo > iniOdo)
		{
			timeDiff = DateTime.diffDays(time, new DateTime());
			setStatus('A');
			return (getDailyRate() * timeDiff);
		}
		else
		{
			return -1;
		}
		
	}
	
	
	//print ID of hirer and date/time of hire is printed if vehicle is currently on hire
	public void print()
	{
		DateTime dt = new DateTime();
		System.out.println(dt.toString());
		
		toString();
		
		if (getStatus() == 'H')
		{
			System.out.println("Hirer ID	:	" + getHirerID());
			System.out.println("Date and time of hire	:	" + getTime());
		}
		else
		{
			System.out.println("The vehicle is available for hire.!");
		}	
	}
	
    @Override
	public String toString()
	{
    	return getVehicleID() + " " + getDescription() + " " + getDailyRate() + " " + getStatus() + " " + getOdometerReading();
	}
}

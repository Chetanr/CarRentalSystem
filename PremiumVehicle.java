public class PremiumVehicle extends Vehicle
{
	public int freeMileage;
	public int serviceLength;
	public int lastServiceOdometerReading;
	
	//per km charge beyond free mileage
	private final double PER_KM_CHARGE = 0.10; 
	
	
	public PremiumVehicle(String vehicleID, String description, double dailyRate, int odometer, int freeMileage, int serviceLength) 
	{
		super(vehicleID, description, dailyRate, odometer);
		this.freeMileage = freeMileage;
		this.serviceLength = serviceLength;
	}
	
	
	//accessor to freeMileage variable
	public int getFreeMileage()
	{
		return freeMileage;
	}
	
	
	//accessor to serviceLength variable
	public int getServiceLength()
	{
		return serviceLength;
	}
	
	
	//accessor to lastServiceOdometerReading variable
	public int getLastServiceOdometerReading()
	{
		return lastServiceOdometerReading;
	}
	
	
	//send the car for service
	public boolean service()
	{
		if (getStatus() == 'A' && getStatus() == 'S')
		{
			setStatus('S');
			return true;
		}
		else
			return false;
	}
	
	
    //update the odometer reading
	public boolean serviceComplete (int odo)
	{
		if (getStatus() == 'S')
		{
			this.lastServiceOdometerReading = odo;
			setStatus('S');
			return true;
		}
		return false;
		
	}
	
	
	//calculate the charges
	public double hireComplete (int odo) throws VIDNotFoundException
	{
		//double baseCharge = freeMileage * dailyRate;
		int timeDiff = DateTime.diffDays(time, getTime());
		double baseCharge = timeDiff * getDailyRate();
		if ((odo - getOdometerReading()) > freeMileage)
		{
			return (baseCharge * (PER_KM_CHARGE * (odo - getOdometerReading())));
		}
		else
		{
			return baseCharge;
		}
		
	}
	
	
	//to verify if car is due for service
	public boolean hire (String hirerID) throws IdNotFoundException, VIDNotFoundException
	{
		setHirerID(hirerID);
		if (getStatus() == 'A')
		{
			if ((lastServiceOdometerReading - getOdometerReading()) > serviceLength)
			{
				return false;
			}
		}
		else if (getStatus() == 'S' || getStatus() == 'H')
		{
			return false;
		}
		return true;
	}
	
	
	//display all instance variables
	public void print()
	{
		System.out.println("Vehicle ID : " + getVehicleID());
		System.out.println("Description : " + getDescription());
		System.out.println("Daily rate : " + getDailyRate());
		System.out.println("Status : " + getStatus());
		System.out.println("Odometer reading : " + getOdometerReading());
		
		System.out.println("Mileage Allowance : " + getFreeMileage());
		System.out.println("Last Service : " + getLastServiceOdometerReading());
		System.out.println("Service Duration : " + getServiceLength());
	}
	
	
	public String toString()
	{
		return getVehicleID() + " " + getDescription() + " " + getDailyRate() + " " + getStatus() + " " + getOdometerReading() + " " + getFreeMileage() + " " + getServiceLength();
	}
}

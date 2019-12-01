import java.io.*;
import java.util.*;


public class ManageHiring {
	
	 static int size =0;
	 static int size2 = 0;
	
	 
	//read from the vehicle file
	public void readVehicle(String fileName) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File(fileName));
		
		String vID = null;
		String desc = null;
		double dailyRate = 0.0;
		String vehType = null;
		int freeMileage = 0;
		int serviceInterval= 0;
		int odoReading = 0;
		
		while (input.hasNext())
		{
			vID = input.next();
			desc = input.next();
			dailyRate = input.nextDouble();
			odoReading = input.nextInt();
			vehType = input.nextLine();
			if (vehType.equals("P"))
			{
				serviceInterval = input.nextInt();
				freeMileage = input.nextInt();
			}
			System.out.println("Vehicle ID: " + vID);
			System.out.println("Description: " + desc);
			System.out.println("DailyRate: " + dailyRate);
			System.out.println("Odometer Reading: " + odoReading);
			if(vehType.equals("P"))
			{
				System.out.println("Free Mileage: " + freeMileage);
				System.out.println("Service Interval: " + serviceInterval);
			}
			
			System.out.println("");
			
			size++;
		}
		
		
		input.close();
	}
	
	
	//read from Vehicle file and create array of Vehicle object
	public void vehRead(String fileName, Vehicle vehs[]) throws FileNotFoundException
	{
		int i = 0;
		Scanner input = new Scanner(new File(fileName));
			
		String vID = null;
		String desc = null;
		double dailyRate = 0.0;
		String vehType = null;
		int freeMileage = 0;
		int serviceInterval= 0;
		int odoReading = 0;
			
		while (input.hasNext())
		{
			vID = input.next();
			desc = input.next();
			dailyRate = input.nextDouble();
			odoReading = input.nextInt();
			vehType = input.nextLine();
			if (vehType.equals("P"))
			{
				serviceInterval = input.nextInt();
				freeMileage = input.nextInt();
				vehs[i++] = new PremiumVehicle(vID, desc, dailyRate, odoReading, freeMileage, serviceInterval);
			}
			else
			{
				vehs[i++] = new Vehicle(vID, desc, dailyRate, odoReading);
			}
		}
		input.close();
	}
	
	
	//read from the customer file
	public void readCustomer(String fileName) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File(fileName));
		
		String cType = null;
		String name = null;
		String ID = null;
		String phone = null;
		
		while (input.hasNextLine())
		{
			cType = input.next();
			name = input.next();
			ID = input.next();
			phone = input.next();
		}
		
		System.out.println("Customer Type: " + cType);
		System.out.println("Customer Name: " + name);
		System.out.println("Customer ID: " + ID);
		System.out.println("Phone: "+ phone);
		
		System.out.println("");
		input.close();
	}
	
	
	//read from customer file and create array of Customer object
	public void custRead (String fileName, Customer customer[]) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File(fileName));
		
		String cType = null;
		String name = null;
		String ID = null;
		String phone = null;
		int mileage = 0;
		int i=0;
		int rate;
		
		while (input.hasNextLine())
		{
			cType = input.next();
			name = input.next();
			ID = input.next();
			phone = input.next();
			mileage = input.nextInt();
			rate = input.nextInt();
			if (cType.equals("i"))
			{
				customer[i++] = new ICustomer (name, ID, phone, mileage);
			}
			else
			{
				customer[i++] = new CCustomer (ID, name, rate);
			}	
		}
		input.close();
	}
	
	
	//write to the vehicle file
	public void writeVehicle(String filename, String vID, String desc, double dailyRate, String vehType, 
			int freeMileage, int serviceInterval, int odoReading) throws IOException
	{
		FileWriter fw = new FileWriter(filename, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(vID + " " + desc + " " + dailyRate + " " + odoReading + " "+ vehType + " " + freeMileage + " " + serviceInterval);
		pw.close();
	}
	
	
	public static boolean findCust(String ID, Customer cus[])
	{
		//Customer[] cus = new Customer [200];
		
		for (int i=0; i < size2; i++)
		{
			if (cus[i].getID().equals(ID))
			{
				return true;
			}
		}
		return false;
	}
	
	
	//write to customer file
	public void writeCustomer(String filename, String cType, String ID, String name, String phone, int mileage, int rate) throws IOException
	{
		FileWriter fw = new FileWriter(filename);
		PrintWriter pw = new PrintWriter(fw);
		pw.print(cType + " " + ID + " " + name + " " + phone + " " + mileage + " " + rate);
		pw.close();
	}
	

	//main method
	public static void main(String args[]) throws IOException, VIDNotFoundException, IDException, NullPointerException
	{
		Customer[] customer = new Customer[200];
		
		Vehicle[] vehs = new Vehicle[200];
		
		int ch = 0;
		String vID;
		String desc;
		String hID;
		double dailyRate;
		String vehType;
		int freeMileage = 0;
		int serviceInterval = 0;
		int odoReading = 0;
		int flag = 1;
		int i, j;
		double cost = 0.0;
		int odo;
		int mileage = 0;
		ManageHiring mh = new ManageHiring();
		
				
		final String VEHICLE_FILE = "vehicle.text";
		final String CUSTOMER_FILE = "customer.txt";
		
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.println("*********");
			System.out.println("Vehicle Menu");
			System.out.println("Add New Vehicle			1");
			System.out.println("Add New Customer		2");
			System.out.println("Display Available Cars	3");
			System.out.println("Display Customers		4");
			System.out.println("Hire Vehicle			5");
			System.out.println("Complete Hire			6");
			System.out.println("Service Vehicle			7");
			System.out.println("Complete Service		8");
			System.out.println("Exit	9");
			System.out.println("************");
			System.out.println("Your Choice	:	");
			
			ch = sc.nextInt();
			
			if (ch == 1)
			{		
				try 
				{							
					System.out.println("Enter id for new vehicle: ");
					vID = sc.next();
					if (vID.length() != 6)
					{
						throw new IDException("Enter a 6 digit Vehicle ID:");
					}
					
					System.out.println("Enter vehicle description: ");
					desc = sc.next();
					System.out.println("Enter hire rate: ");
					dailyRate = sc.nextDouble();
					System.out.println("Enter vehicle type (V for Vehicle, P for Premium Vehicle: ");
					vehType = sc.next();
					System.out.println("Enter odometer reading: ");
					odoReading = sc.nextInt();
					if (vehType.equals("P"))
					{
						System.out.println("Enter free mileage allowance: ");
						freeMileage = sc.nextInt();
						System.out.println("Enter service interval: ");
						serviceInterval = sc.nextInt();
						System.out.println("Enter odometer reading: ");
						odoReading = sc.nextInt();
						
											
	
					}
					
					mh.writeVehicle(VEHICLE_FILE, vID, desc, dailyRate, vehType, freeMileage, serviceInterval, odoReading);					
				} 
				
				catch (FileNotFoundException e) {
					System.err.println("No such file. Please re-enter");
				}
			}
			else if (ch == 2)
			{
				try
				{
					String name;
					String ID;
					String phone;
					String cType;
					int rate = 0;
					System.out.println("Enter customer type (i for individual, c for corporate): ");
					cType = sc.next();
					System.out.println("Enter Customer ID: ");
					ID = sc.next();
					System.out.println("Enter customer name: ");
					name = sc.next();
					System.out.println("Enter phone: ");
					phone = sc.next();
					System.out.println("Enter mileage: ");
					mileage = sc.nextInt();
					if (cType.equals("c"))
					{
						System.out.println("Enter the rate: ");
						rate = sc.nextInt();
					}
					
					mh.writeCustomer(CUSTOMER_FILE, cType, ID, name, phone, mileage, rate);
				}
				catch (FileNotFoundException e) 
				{
					System.err.println("No such file. Please re-enter");
				}
			}
			else if(ch == 3)
			{
				mh.readVehicle(VEHICLE_FILE);
			}
			else if(ch == 4)
			{
				mh.readCustomer(CUSTOMER_FILE);
			}
			else if(ch == 5)
			{		
				mh.vehRead(VEHICLE_FILE, vehs);
				
				i=0;
				System.out.println("Enter the vehicleID to be hired :");
				vID = sc.next();
				for (i=0; i <= size; i++)
				{
					if(vehs[i].getVehicleID().equals(vID))
					{
						System.out.println("Enter the hirer ID: ");
						hID = sc.next();
						try
						{
							for (j = 0; j <= size; j++)
							{
								if (vehs[j].hire(hID) == true)
								{
									vehs[j].setHirerID(hID);
									vehs[j].setTime(new DateTime());
									System.out.println("Vehicle hired.!");
									flag = 0;
									break;
								}	
							}
							if (flag == 0)
								break;
						} 
						catch (IdNotFoundException e)
						{
							System.err.println("Invalid hirer ID.! Please enter the correct hirer ID");
						}								
					}
				} 
				if (flag == 1)
				{
					System.out.println("Vehicle already in hire/service");
				}
				if (i > vehs.length - 1)
				{
					System.out.println("Vehicle not found");
				}
					
			}	
			else if(ch == 6)
			{
				mh.vehRead(VEHICLE_FILE, vehs);
				mh.custRead(CUSTOMER_FILE, customer);
				
				System.out.println("Enter the vehicle ID : ");
				vID = sc.next();
				try
				{
					for (i = 0; i <= size; i++)
					{
						if (vehs[i].getVehicleID().equals(vID))
						{
							System.out.println("Enter the hirer ID : ");
							hID = sc.next();
							System.out.println("Enter the odometer reading : ");
							odo = sc.nextInt();
							cost = vehs[i].hireComplete(odo);				
							System.out.println("Car" + vID + "is returned by " + hID +
							"charges: " + cost);
							break;
					
						}
							flag = 0;
					}
					if (flag == 1 && i < (vehs.length - 1))
					{
						System.out.println("vehicle ID not found.!");
					}
					if (i > (vehs.length - 1))
					{
						System.out.println("Vehicle not found");
					}
				}
				catch (VIDNotFoundException e)
				{
					System.err.println("");
				}
				catch (OdometerException e)
				{
					System.err.println("Odometer reading Error.!");
				}
				catch (StatusException e)
				{
					System.err.println("Car status error.!");
				}
			}
			else if (ch == 7)
			{
				mh.vehRead(VEHICLE_FILE, vehs);
				
				System.out.println("Enter the vehicle ID : ");
				vID = sc.next();
				for (int k = 0 ; k < vehs.length ; k++)
				{
					if (vehs[k].getVehicleID().equals(vID))
					{
						if (vehs[k].service() == true)
						{
							System.out.println("Car " + vID + "is now sent for service");
						}
						else if(vehs[k].service() == false)
						{
							System.out.println("WARNING: Car" + vID + "cannot be sent for serice");
						}
					}
					break;
				}
			}
			else if (ch == 8)
			{
				mh.vehRead(VEHICLE_FILE, vehs);
				System.out.println("Enter the vehicle ID : ");
				vID = sc.next();
									
				for(int k = 0 ; k <= vehs.length ; k++)
				{
					if(vehs[k].getVehicleID().equals(vID))
					{
						System.out.println("Enter the current Odometer Reading : ");
						odo = sc.nextInt();
						if (vehs[k].serviceComplete(odo) == true)
						{
							System.out.println("Car " + vID + "is now back from service");
						}
						else if (vehs[k].serviceComplete(odo) == false)
						{
							System.out.println("WARNING: Car " + vID + "cannot be returned from service");
						}
					}
					else
					{
						System.out.println("Vehicle not found.");
					}
					break;
				}
			}	
		}while (ch != 9);
	sc.close();
	}
}

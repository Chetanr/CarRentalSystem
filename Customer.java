
public abstract class Customer {
	private String ID;
	private String name;
	private String phone;
	
	
	public Customer (String ID, String name, String phone)
	{
		this.ID = ID;
		this.name = name;
		this.phone = phone;
	}
	
	
	public Customer(String ID, String name) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		this.name = name;
	}

	
	public abstract double getDiscount(double amount);
	
	
	//accessor for customerID
	public String getID()
	{
		return ID;
	}
	
	
	//accessor for customer name
	public String name()
	{
		return name;
	}
	
	
	//accessor for customer phone
	public String getPhone()
	{
		return phone;
	}

}

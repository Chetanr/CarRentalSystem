
public class ICustomer extends Customer {
	private int mileage;
	
	
	public ICustomer(String ID, String name, String phone, int mileage)
	{
		super (ID, name, phone);
		this.mileage = mileage;
	}

	
	//finding the discount
	@Override
	public double getDiscount(double amount) {
	
		
		if (mileage >= 100000 && mileage < 200000)
			return (amount*0.1);
		else if (mileage >= 200000)
			return (amount*0.2);
		else
			return amount;
	}
}

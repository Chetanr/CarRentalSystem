
public class CCustomer extends Customer{
	
	private double rate;
	
	
	public CCustomer(String ID, String name, double rate) 
	{
		super(ID, name);
		// TODO Auto-generated constructor stub
		this.rate = rate;
	}
	
	
	public double getRate()
	{
		return rate;
	}
	
	
	public void setRate(double rate)
	{
		this.rate = rate;
	}
	
	
	@Override
	public double getDiscount(double amount) 
	{	
		return (amount * rate);
	}
}

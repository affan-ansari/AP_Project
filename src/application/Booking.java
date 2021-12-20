package application;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Plumber_1_Bookings")
public class Booking
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int booking_id;
	
	private String Date;
	private String Start_Time;
	private String End_Time;
	private String Payment_Method;
	private String Address;
	
	public void SetDate(String Date)
	{
		this.Date = Date;
	}
	public void SetStartTime(String StartTime)
	{
		this.Start_Time = StartTime;
	}
	public void SetEndTime(String EndTime)
	{
		this.End_Time = EndTime;
	}
	public void SetPaymentMethod(String PaymentMethod)
	{
		this.Payment_Method = PaymentMethod;
	}
	public void SetAddress(String Address)
	{
		this.Address = Address;
	}
	
	public String GetDate()
	{
		return this.Date;
	}
	public String GetStartTime()
	{
		return this.Start_Time;
	}
	public String GetEndTime()
	{
		return this.End_Time;
	}
	public String GetPaymentMethod()
	{
		return this.Payment_Method;
	}
	public String GetAddress()
	{
		return this.Address;
	}
}

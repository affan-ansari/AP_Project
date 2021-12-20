package application;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MySQLHandler extends DataHandler
{
	private Configuration con = new Configuration();
	private SessionFactory sf;
	MySQLHandler()
	{
		this.con = new Configuration();
		this.con.configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class);
		this.con.configure("hibernate.cfg.xml").addAnnotatedClass(User.class);
		this.con.configure("hibernate.cfg.xml").addAnnotatedClass(Booking.class);
		this.sf = this.con.buildSessionFactory();
	}
	@Override
	void save_user(User u) throws Exception {
//		Configuration con = new Configuration();
//		con.configure("hibernate.cfg.xml").addAnnotatedClass(User.class);
//		SessionFactory sf = con.buildSessionFactory();
		Session session = this.sf.openSession();
		Transaction trans = session.beginTransaction();
		session.save(u);
		trans.commit();
//		session.close();
//		sf.close();
	}

	@Override
	Vector<User> get_users() {
		Vector<User> users = new Vector<User>();
//		Configuration con = new Configuration();
//		con.configure("hibernate.cfg.xml").addAnnotatedClass(User.class);
//		SessionFactory sf = con.buildSessionFactory();
		Session session = this.sf.openSession();
		Transaction trans = session.beginTransaction();
		
		List users_list = session.createQuery("FROM User").list();
		for(Iterator iterator = users_list.iterator(); iterator.hasNext();)
		{
			User user = (User) iterator.next();
			users.add(user);
		}
		
		trans.commit();
//		session.close();
//		sf.close();
		return users;
	}

	@Override
	User get_user(String username) throws IOException, Exception 
	{
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml").addAnnotatedClass(User.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction trans = session.beginTransaction();

		User u = session.get(User.class, username);
		
		trans.commit();
//		session.close();
//		sf.close();
		return u;
	}
	
	@Override
	public boolean addEmployee(Employee emp) 
	{
		try
		{
			Session session = this.sf.openSession();
			Employee tempEmp = session.get(Employee.class, emp.getCNIC());
			if (tempEmp != null)
			{

//					throw new EmployeeAlreadyExistsException("The employee already exists!");
					return false;
			}
			else
			{
				Transaction trans = session.beginTransaction();
				session.save(emp);
				trans.commit();
				return true;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	@Override
	public boolean updateEmployee(Employee emp) 
	{
		try
		{
			Session session = this.sf.openSession();
			Employee tempEmp = session.get(Employee.class, emp.getCNIC());
//			List employees = session.createQuery("FROM employeeTable").list();
//			for (Iterator iterator = employees.iterator(); iterator.hasNext();)
//			{
//				tempEmp = (Employee) iterator.next();
//				if (tempEmp.getCNIC().compareTo(emp.getCNIC()) == 0)
//				{
//					break;
//				}
//			}
			if (tempEmp != null)
			{
				Transaction trans = session.beginTransaction();
				if (tempEmp.getAddress().length() > 0)
				{
					tempEmp.setAddress(emp.getAddress());
				}
				if (tempEmp.getContact().length() > 0)
				{
					tempEmp.setContact(emp.getContact());
				}
				if (tempEmp.getRate() > 0.0)
				{
					tempEmp.setRate(emp.getRate());
				}
				if (tempEmp.getService().length() > 0)
				{
					tempEmp.setService(emp.getService());
				}
				session.update(tempEmp);
				trans.commit(); //...
				return true;
			}
			else
			{
//				throw new EmployeeNotFoundException("The employee cannot be found!");
				return false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public boolean deleteEmployee(Employee emp) 
	{
		try
		{
			Employee tempEmp = null;
			Session session = this.sf.openSession();
			tempEmp = session.get(Employee.class, emp.getCNIC());
//			List employees = session.createQuery("FROM employeeTable").list();
//			for (Iterator iterator = employees.iterator(); iterator.hasNext();)
//			{
//				tempEmp = (Employee) iterator.next();
//				if (tempEmp.getCNIC().compareTo(emp.getCNIC()) == 0)
//				{
//					break;
//				}
//			}
			if (tempEmp != null)
			{
				Transaction trans = session.beginTransaction();
				session.delete(tempEmp);
				trans.commit(); //...
				return true;
			}
			else
			{
				//throw new EmployeeNotFoundException("The employee cannot be found!");
				return false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void SavePlumberBooking(String date, String starttime, String endtime, String paymentmethod, String address) 
	{
        // loads configuration and creates a session factory
//        Configuration con = new Configuration();
        
//        con.configure().addAnnotatedClass(Booking.class);
        
//        SessionFactory sf= con.buildSessionFactory();
        
        Session session= this.sf.openSession();
        
        Transaction trans= session.beginTransaction();

        Booking plumberbooking = new Booking();
        plumberbooking.SetDate(date);
        plumberbooking.SetStartTime(starttime);
        plumberbooking.SetEndTime(endtime);
        plumberbooking.SetPaymentMethod(paymentmethod);
        plumberbooking.SetAddress(address);
        
        session.save(plumberbooking);
        
        trans.commit();
	}

}

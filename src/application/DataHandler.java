package application;

import java.io.IOException;
import java.util.Vector;

public abstract class DataHandler {
	
	abstract void save_user(User u) throws IOException, Exception;
	abstract Vector<User> get_users() throws IOException, Exception;
	abstract User get_user(String username) throws IOException, Exception;
	public abstract boolean addEmployee(Employee emp);
	public abstract boolean updateEmployee(Employee emp);
	public abstract boolean deleteEmployee(Employee emp);
	public abstract void SavePlumberBooking(String date, String starttime, String endtime, String paymentmethod, String address);
}
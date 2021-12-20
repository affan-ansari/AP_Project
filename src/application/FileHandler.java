package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class FileHandler extends DataHandler{

	@Override
	void save_user(User u) throws IOException 
	{
		FileWriter pw = new FileWriter("users.csv",true);
		pw.append(u.getUser_name());
		pw.append(",");
		pw.append(u.getPass());
		pw.append(",");
		pw.append(u.getFull_name());
		pw.append(",");
		pw.append(u.getPhone_num());
		pw.append("\n");
		pw.close();
	}

	@Override
	Vector<User> get_users() throws IOException 
	{
		Vector<User> users = new Vector<User>();
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader("users.csv"));
		
		while ((line = br.readLine()) != null)  
		{  
			User u = new User();
			String[] user_data = line.split(",");
			u.setUser(user_data[0], user_data[1], user_data[2], user_data[3]);
			users.add(u);
		}
		
		br.close();
		return users;
	}

	@Override
	User get_user(String username) throws IOException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void SavePlumberBooking(String date, String starttime, String endtime, String paymentmethod,
			String address) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public boolean addEmployee(Employee emp) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean updateEmployee(Employee emp) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean deleteEmployee(Employee emp) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}

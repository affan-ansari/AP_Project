package application;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//import org.hibernate.cfg.Configuration;

public class Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	public DataHandler dh = new MySQLHandler();
	
	@FXML
	public TextField username_textbox;
	public TextField password_textbox;
	public TextField fullname_textbox;
	public TextField phonenumber_textbox;
	public TextField login_username_textbox;
	public TextField login_password_textbox;
	public Label user_reg_status_label;
	public Label user_fullname_label;
	public Label login_status_label;
	public Label login_username_label;
	
	@FXML
    private TextField ServiceRateTextBox;
	
	@FXML
    private TextField ServiceTextBox;
	
	@FXML
    private TextField RegisterEmployeeAddressTextBox;

    @FXML
    private TextField RegisterEmployeeCNICTextBox;

    @FXML
    private TextField RegisterEmployeeContactTextBox;

    @FXML
    private TextField RegisterEmployeeNameTextBox;
    
    @FXML
    private TextField UpdateEmployeeAddressTextBox;

    @FXML
    private TextField UpdateEmployeeContactTextBox;

    @FXML
    private Label UpdateEmployeeMessageLabel;

    @FXML
    private Label RegisterEmployeeMessageLabel;
    
    @FXML
    private Label ListEmployeeServiceMessageLabel;
	
    
    @FXML
	public TextField cnic_textbox;
	public TextField name_textbox;
	public TextField plumber_date;
	public TextField plumber_start_time;
	public TextField plumber_end_time;
	public TextField plumber_payment_method;
	public TextField plumber_booking_address;
    
	
	// ATHAR'S CONTROLLER FUNCTIONS
	
	@FXML
	public void bookplumber1(ActionEvent actionEvent)
	{
		try
		{
			AnchorPane pane_3 = FXMLLoader.load(getClass().getResource("plumber1bookingform.fxml"));
			
			Node node = (Node) actionEvent.getSource();
			Stage thisStage = (Stage) node.getScene().getWindow();
			
			thisStage.setTitle("Plumber Booking Form");
			
			thisStage.setScene(new Scene(pane_3));
		
			thisStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	public void plumber1submit(ActionEvent actionEvent)
	{
		try
		{
//			DBHandler db = new DBHandler();
			
			dh.SavePlumberBooking(plumber_date.getText(), plumber_start_time.getText(), plumber_end_time.getText(), plumber_payment_method.getText(), plumber_booking_address.getText());
		
		    try
		    {
		    	File myObj = new File("plumbers_booking.txt");
		        
		    	if (myObj.createNewFile())
		    	{
		    		String filename = "plumbers_booking.txt";
		    		
		    	    FileWriter fw = new FileWriter(filename, true); //the true will append the new data
		    	    fw.write("Date: " + plumber_date.getText() + "\n");   //appends the string to the file
		    	    fw.write("Start Time: " + plumber_start_time.getText() + "\n");
		    	    fw.write("End Time: " + plumber_end_time.getText() + "\n");
		    	    fw.write("Payment Method: " + plumber_payment_method.getText() + "\n");
		    	    fw.write("Address: " + plumber_booking_address.getText() + "\n");
		    	    fw.close();
		        }
		    	else
		    	{
		    		String filename = "plumbers_booking.txt";
		    		
		    	    FileWriter fw = new FileWriter(filename, true); //the true will append the new data
		    	    fw.write("\n");
		    	    fw.write("Date: " + plumber_date.getText() + "\n");   //appends the string to the file
		    	    fw.write("Start Time: " + plumber_start_time.getText() + "\n");
		    	    fw.write("End Time: " + plumber_end_time.getText() + "\n");
		    	    fw.write("Payment Method: " + plumber_payment_method.getText() + "\n");
		    	    fw.write("Address: " + plumber_booking_address.getText() + "\n");
		    	    fw.close();
		        }
		    }
		    catch (IOException e)
		    {
		        System.out.println("An error occurred.");
		        e.printStackTrace();
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
    // ALI'S CONTROLLER FUNCTIONS
    
    @FXML
	 void RegisterEmployeeSubmitButtonClicked(ActionEvent event) 
	 {
		 try
		 {
			 Employee newEmp = new Employee();
			 newEmp.setAddress(RegisterEmployeeAddressTextBox.getText());
			 newEmp.setCNIC(RegisterEmployeeCNICTextBox.getText());
			 newEmp.setContact(RegisterEmployeeContactTextBox.getText());
			 newEmp.setName(RegisterEmployeeNameTextBox.getText());
			 LoginSession.getInstance(RegisterEmployeeCNICTextBox.getText(), "Employee");
			 boolean success = dh.addEmployee(newEmp);
			 if (success == true)
			 {
				 RegisterEmployeeMessageLabel.setText("Employee added successfully!");
				 root = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
				 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				 scene = new Scene(root);
				 stage.setScene(scene);
				 stage.show();
			 }
			 else
			 {
				 RegisterEmployeeMessageLabel.setText("Failed to add new employee!");
				 throw new EmployeeAlreadyExistsException("Employee already exists!");
				 
			 }
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		 
	 }
	
	 // Update Employee Information
	 
	 @FXML
	 void UpdateEmployeeSubmitButtonClicked(ActionEvent event) 
	 {
		 try
		 {
			 Employee updateEmp = new Employee(); 
			 LoginSession mySession = LoginSession.getInstance(null, null);
			 System.out.println(mySession.getUsername());
			 updateEmp.setCNIC(mySession.getUsername());
			 updateEmp.setAddress(UpdateEmployeeAddressTextBox.getText());
			 updateEmp.setContact(UpdateEmployeeContactTextBox.getText());
			 boolean success = dh.updateEmployee(updateEmp);
			 
			 if (success == true)
			 {
				 UpdateEmployeeMessageLabel.setText("Employee updated successfully!");
//				 AnchorPane pane = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
//				 EmployeePane.getChildren().setAll(pane);
				 root = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
				 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				 scene = new Scene(root);
				 stage.setScene(scene);
				 stage.show();
			 }
			 else
			 {
				 UpdateEmployeeMessageLabel.setText("Failed to update");
			 }
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
			 try 
			 {
//				AnchorPane pane = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
//				EmployeePane.getChildren().setAll(pane);
				root = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			 } 
			 catch (IOException e1) 
			 {
				e1.printStackTrace();
			 }
		 }
		 
	 }
	 
	 @FXML
	 void UpdateEmployeeGoBackButtonClicked(ActionEvent event) 
	 {
		try
		{
//			AnchorPane pane = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
//	 		EmployeePane.getChildren().setAll(pane);
	 		root = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	 }
	 
	// Employee Dashboard
	
   @FXML
   public void EmployeeListAsServiceProviderButtonClicked(ActionEvent event) 
   {
		try 
		{
//			AnchorPane pane = FXMLLoader.load(getClass().getResource("ListEmployeeService.fxml"));
//			
//			EmployeePane.getChildren().setAll(pane);
			root = FXMLLoader.load(getClass().getResource("ListEmployeeService.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
   }

   @FXML
   public void EmployeeUpdateInformationButtonClicked(ActionEvent event) 
   {
	   	try
	   	{
//   		AnchorPane pane = FXMLLoader.load(getClass().getResource("UpdateEmployee.fxml"));
//   		EmployeePane.getChildren().setAll(pane);
	   		root = FXMLLoader.load(getClass().getResource("UpdateEmployee.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	   	}
	   	catch (Exception e)
	   	{
	   		e.printStackTrace();
	   	}
   	
   }	
   
   @FXML
   public void EmployeeRemoveListingButtonClicked(ActionEvent event) 
   {

   }
   
   @FXML
   public void EmployeeLogOutButtonClicked(ActionEvent event) 
   {
	   System.exit(0);
   }

   

   
   // Admin Dashboard

   @FXML
   void AdminRemovedServiceProviderButtonClicked(ActionEvent event) 
   {

   }
   
   @FXML
   void AdminLogOutButtonClicked(ActionEvent event) 
   {
	   System.exit(0);
   }
   
   
   // List Employee Service Page
   
   @FXML
   void SubmitServiceButtonClicked(ActionEvent event) 
   {
	   	try
	   	{
	       	float newRate = Float.parseFloat(ServiceRateTextBox.getText());
	       	String newService = ServiceTextBox.getText().toLowerCase();
	       	Employee newEmp = new Employee();
	       	LoginSession mySession = LoginSession.getInstance(null, null);
	       	System.out.println(mySession.getUsername());
	       	newEmp.setCNIC(mySession.getUsername());
	       	newEmp.setRate(newRate);
	       	newEmp.setService(newService);
	       	boolean success = dh.updateEmployee(newEmp);
	       	if (success == true)
	       	{
	       		ListEmployeeServiceMessageLabel.setText("Successfully updated employee!");
//       		AnchorPane pane = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
//				EmployeePane.getChildren().setAll(pane);
	       		root = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
	       	}
	       	else
	       	{
	       		ListEmployeeServiceMessageLabel.setText("Failed to update employee!");
	       		throw new EmployeeNotFoundException("Employee not found!");
	       	}
	   	}
	   	catch (Exception e)
	   	{
	   		e.printStackTrace();
			try 
			{
//				AnchorPane pane = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
//				EmployeePane.getChildren().setAll(pane);
				root = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
	
	   	}
   }
   
   @FXML
   void GoBackServiceButtonClicked(ActionEvent event) 
   {
	   	try
	   	{
//   		AnchorPane pane = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
//   		EmployeePane.getChildren().setAll(pane);
	   		root = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	   	}
	   	catch (Exception e)
	   	{
	   		e.printStackTrace();
	   	}
   	
   }
    
    ////////////////////////////////
    
    // AFFAN'S CONTROLLER FUNCTIONS
    
	@FXML
	public void login_as_user_button_clicked(ActionEvent actionEvent)
	{
		try {
			root = FXMLLoader.load(getClass().getResource("login.fxml"));
			stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void set_login_username_label(String username)
	{
		login_username_label.setText(username);
	}
	
	public void set_login_status_label(String status)
	{
		login_status_label.setText(status);
	}
	
	@FXML
	public void view_service_button_clicked(ActionEvent actionEvent)
	{
		
	}
	
	@FXML
	public void book_service_button_clicked(ActionEvent actionEvent)
	{
		
	}
	
	@FXML
	public void logout_button_clicked(ActionEvent actionEvent)
	{
		LoginSession mySession = LoginSession.getInstance(null, null);
		mySession.endSession();
		try {
			root = FXMLLoader.load(getClass().getResource("login.fxml"));
			stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void login_user_button_clicked(ActionEvent actionEvent)
	{
		String username = null;
		if (login_username_textbox.getText() == null || login_username_textbox.getText().trim().isEmpty())
		     username = "";
		else
			username = login_username_textbox.getText();
		
		String pass = null;
		if (login_password_textbox.getText() == null || login_password_textbox.getText().trim().isEmpty())
			pass = "";
		else
			pass = login_password_textbox.getText();
		try {
			FXMLLoader loader = null;
			if(username == "" || pass == "")
			{				
				loader = new FXMLLoader(getClass().getResource("login.fxml"));
				root = loader.load();
				Controller controller = loader.getController();
				controller.set_login_status_label("Invalid Credentials");
			}
			else
			{
				try{
					User user = dh.get_user(username);
					if(user.getPass().compareTo(pass) == 0)
					{
						LoginSession.getInstance(username, "customer");
						loader = new FXMLLoader(getClass().getResource("welcomeUser.fxml"));
						root = loader.load();
						Controller controller = loader.getController();
						controller.set_login_username_label("Welcome " + username);
					}
					else
					{
						loader = new FXMLLoader(getClass().getResource("login.fxml"));
						root = loader.load();
						Controller controller = loader.getController();
						controller.set_login_status_label("Invalid password");
					}
				}
				catch (Exception e) {
					e.printStackTrace();
					loader = new FXMLLoader(getClass().getResource("login.fxml"));
					root = loader.load();
					Controller controller = loader.getController();
					controller.set_login_status_label("Invalid Credentials");
				}
				
			}
			stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void home_button_clicked(ActionEvent actionEvent)
	{
		try
		{
			root = FXMLLoader.load(getClass().getResource("welcomeScreen.fxml"));
			stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	public void register_as_user_button_clicked(ActionEvent actionEvent)
	{
		try
		{
			root = FXMLLoader.load(getClass().getResource("RegisterUser.fxml"));
			stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	public void register_as_employee_button_clicked(ActionEvent actionEvent)
	{
		try 
		{
			root = FXMLLoader.load(getClass().getResource("RegisterEmployee.fxml"));
			stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void set_user_registration_status_label(String status)
	{
		user_reg_status_label.setText(status);
	}
	
	public void set_user_fullname_label(String fullname)
	{
		user_reg_status_label.setText(fullname);
	}
	
	public Boolean userExists(User u) throws Exception
	{
		Vector<User> users = dh.get_users();
		for(int i = 0; i < users.size(); i++)
			if(u.getUser_name().compareTo(users.get(i).getUser_name()) == 0)
				return true;
		return false;
	}
	
	@FXML
	public void register_user_button_clicked(ActionEvent actionEvent)
	{
		String username = "";
		if(username_textbox.getText().isEmpty() == false);
			username = username_textbox.getText();
		String pass = "";
		if(password_textbox.getText().isEmpty() == false);
			pass = password_textbox.getText();
		String fullname = "";
		if(fullname_textbox.getText().isEmpty() == false);
			fullname = fullname_textbox.getText();
		String phonenumber = "";
		if(phonenumber_textbox.getText().isEmpty() == false);
			phonenumber = phonenumber_textbox.getText();
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterUserStatus.fxml"));
			root = loader.load();
			
			Controller controller = loader.getController();
			if(username.compareTo("") == 0 || pass.compareTo("") == 0 || fullname.compareTo("") == 0 || phonenumber.compareTo("") == 0)
				controller.set_user_registration_status_label("Registration Status: Fail");
			else
			{
				User user = new User();
				user.setUser(username, pass, fullname, phonenumber);
				try {
					if(userExists(user) == false)
					{
						try {
							dh.save_user(user);
						} catch (Exception e) {
							e.printStackTrace();
						}
						controller.set_user_registration_status_label("Registration Status: Success");
					}
					else
						controller.set_user_registration_status_label("Registration Status: Fail");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package runner;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import dao.CustomerDaoImpl;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;


public class CustomerRunner {

	// check existing account details of a customer using SSN
	public void method1() throws SQLException {
		
		int cust_ssn = 0;

		cust_ssn = GetValidInput.getSSN();
		CustomerDaoImpl CustDaoimpl1 = new CustomerDaoImpl();
		CustDaoimpl1.CheckCustomer(cust_ssn);

	}

	// modify an existing account details of a customer using SSN
	public void method2() throws SQLException {
		int cust_ssn = 0, cust_phone = 0;
		
		String first_name, middle_name, last_name;
		String cust_email;
		String apt_no, street_name, cust_city, cust_state = "", cust_zip = "";
		
		// made cust_country a constant of "United States" because database is designed for US addresses only
		final String CUST_COUNTRY = "United States";	
		
		int menu=0;
		
		boolean valid = false;
		
		Scanner input = new Scanner(System.in);

		// get SSN
		cust_ssn = GetValidInput.getSSN();
			
		// show current values for cust_ssn
		CustomerDaoImpl CustDaoimpl1 = new CustomerDaoImpl();
		CustDaoimpl1.CheckCustomer(cust_ssn);
		
		// make menu selection 
		String selection = "\nSelect the number from the following menu you would like to modify. \n \n"
				+ "1. Name Fields \n" + "2. Address \n" + "3. Phone \n" + "4. Email \n" + "";
		valid = false;
		
		do {
			try {
				System.out.print(selection);
				menu = input.nextInt();
			
				while (menu < 1 || menu > 4) {
					throw new IllegalArgumentException();
				}				
				valid = true;
								
			} catch (InputMismatchException e) {
				System.out.print("*********************************************\n");
				System.out.print("*** You have entered an invalid selection ***\n");
				System.out.print("*********************************************\n");
				input.next();				
				valid = false;
			}  catch (IllegalArgumentException e) {
				System.out.print("*****************************************\n");
				System.out.print("**You have entered an invalid selection**\n");
				System.out.print("*******Selection must from 1 to 4********\n");
				System.out.print("*****************************************\n");
				valid = false;
			}
	
		} while (!valid);

		input.nextLine(); 

		switch (menu) {
		
		// modify name
		case 1:			
			// get and validate first_name contains only letters, spaces or periods
			System.out.print("Enter First Name: " + "");
			first_name = input.nextLine();			
			
			while (!isAlpha(first_name) ) {
				System.out.println();
				System.out.print("Enter First Name: " + "");
				first_name = input.nextLine();			
			}
			
			// get and validate middle_name contains only letters, spaces or periods	
			System.out.print("Enter Middle Name: " + "");
			middle_name = input.nextLine();	

			while (!isAlpha(middle_name) ) {
				System.out.println();
				System.out.print("Enter Middle Name: " + "");
				middle_name = input.nextLine();			
			}
			
			// get and validate last_name contains only letters, spaces or periods
			System.out.print("Enter Last Name: " + "");
			last_name = input.nextLine();
			
			while (!isAlpha(last_name) ) {
				System.out.println();
				System.out.print("Enter Last Name: " + "");
				last_name = input.nextLine();			
			}
			
			CustomerDaoImpl CustDaoimpl2a = new CustomerDaoImpl();
			CustDaoimpl2a.ModifyCustomerName(cust_ssn, first_name, middle_name, last_name);
		 	break;
		 	
		// modify address
		case 2:
			System.out.print("Enter Apartment NO: ");
			apt_no = input.nextLine();
			
			System.out.print("Enter Street Name: ");		
			street_name = input.nextLine();
			
			// get and validate city contains only letters, spaces or periods
			System.out.print("Enter City: ");
			cust_city = input.nextLine();
			
			while (!isAlpha(cust_city) ) {
				System.out.println();
				System.out.print("Enter City: ");
				cust_city = input.nextLine();			
			}
	
			cust_state = GetValidInput.getState();
			cust_zip = GetValidInput.getZip();
			
			CustomerDaoImpl CustDaoimpl2b = new CustomerDaoImpl();
		 	CustDaoimpl2b.ModifyCustomerAdd(cust_ssn, apt_no, street_name, cust_city, cust_state, CUST_COUNTRY, cust_zip);
		 	break;
		
		 // modify phone number
		case 3:			
			cust_phone = GetValidInput.getPhoneNumber();
			
			CustomerDaoImpl CustDaoimpl2c = new CustomerDaoImpl();
		 	CustDaoimpl2c.ModifyCustomerPhone(cust_ssn, cust_phone);
		 	break;

		// modify email address
		case 4:
			System.out.print("Enter Email address: ");
			cust_email = input.nextLine();
							
			while (!isEmail(cust_email) ) {
				System.out.println();
				System.out.print("Enter Email Address: ");
				cust_email = input.nextLine();			
			}

			CustomerDaoImpl CustDaoimpl2d = new CustomerDaoImpl();
		 	CustDaoimpl2d.ModifyCustomerEmail(cust_ssn, cust_email);
		 	break;
		 	
		default:
			System.out.println("It is not a correct #");
		}	
	
		input.close();

	}

	
	// Generate monthly bill for a credit card number 
	public void method3() throws SQLException {
		
		String creditCard;
		int month, year;

		creditCard = GetValidInput.getCardNum();
		month = GetValidInput.getMonth("");
		year = GetValidInput.getYear("");
		
		CustomerDaoImpl CustDaoimpl3 = new CustomerDaoImpl();
		CustDaoimpl3.GenerateBill(creditCard, month, year);

	}

	// Get transactions made by a customer between 2 dates
	public void method4() throws SQLException {
		int ssn;
		int fromYear, toYear, fromMonth, toMonth, fromDay, toDay;
		boolean valid = false;
		Date dFrom = new Date();
		Date dTo = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		ssn = GetValidInput.getSSN();
		
		do {
			fromMonth = GetValidInput.getMonth(" \"FROM\"");
			fromDay = GetValidInput.getDay(" \"FROM\"", fromMonth);
			fromYear = GetValidInput.getYear(" \"FROM\"");
			toMonth = GetValidInput.getMonth(" \"TO\"");
			toDay = GetValidInput.getDay(" \"TO\"", toMonth);
			toYear = GetValidInput.getYear(" \"TO\"");
			
			// validate toDate >= fromDate
			try {
				dFrom = sdf.parse(fromMonth + "/" + fromDay + "/" + fromYear );
				dTo = sdf.parse(toMonth + "/" + toDay + "/" + toYear );
				
				if (dFrom.compareTo(dTo) == 1) {
					throw new IllegalArgumentException();
				}
				
				valid = true;
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				valid = false;
			}
			catch (IllegalArgumentException e) {
				System.out.print("******************************************\n");
				System.out.print("**You have entered an invalid date range**\n");
				System.out.print("*******TO Date must be >= FROM Date*******\n");
				System.out.print("******************************************\n");
				valid = false;
			}		
			
		} while (!valid);
		
		CustomerDaoImpl CustDaoimpl4 = new CustomerDaoImpl();
		CustDaoimpl4.DisplayTrans(ssn, fromYear, toYear, fromMonth, toMonth, fromDay, toDay);

	}

	
	// method isAlpha checks if a String contains only letters, spaces, & periods
	public boolean isAlpha(String name) {

		try {
			if (name.matches("[a-zA-Z .]+")) {
				return true;
			}
			else {
				throw new IllegalArgumentException();				
			}
		} catch(IllegalArgumentException e) {
			System.out.print("*************************************\n");
			System.out.print("***You have entered invalid Name******\n");
			System.out.print("*************************************\n");
			System.out.println();
			
			return false;
		}
	}
	
	
	// method isEmail checks if email address is valid
	public boolean isEmail(String email) {
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
		
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
        
        // validate email address
        if (matcher.matches()) {
        	return true;
        }
        else {
          System.out.print("*************************************\n");
          System.out.print("**You have entered an invalid Email**\n");
          System.out.print("*************************************\n");
          System.out.println();
	      
          return false;
        }
	
	}
	
}

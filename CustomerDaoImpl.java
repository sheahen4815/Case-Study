package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {

	//private static final String FIRST_NAME = null;

	@Override
	// dms - changed ssn to dataype int to match database datatype
	public void CheckCustomer(int ssn) throws SQLException {

		Connection myConn = null;
		// Statement myStmt = null;
		PreparedStatement myStmt = null;

		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			// myStmt = myConn.createStatement();
			myStmt = myConn.prepareStatement(SqlFile.query4);

			// 2a. set the parameter;
			// dms - changed to setInt from setString 
			myStmt.setInt(1, ssn);

			// 3. Execute SQL query
			myRs = myStmt.executeQuery();

			if (myRs.next() == false) {
				System.out.println("***********************************");
				System.out.println("No information for your selection !");
				System.out.println("***********************************");
			} else {
				//dms - added missing columns from database table & corrected get statement datatypes
				System.out.println("The current information for custome with SSN of " + ssn + " is: ");
				System.out.println(myRs.getString("FIRST_NAME") + " " + myRs.getString("MIDDLE_NAME") + " "
						+ myRs.getString("LAST_NAME") + "\t" + myRs.getInt("SSN") + "\t"
						+ myRs.getString("CREDIT_CARD_NO") + "\t" + myRs.getString("APT_NO") + " "
						+ myRs.getString("STREET_NAME") + " " + myRs.getString("CUST_CITY") + " "
						+ myRs.getString("CUST_STATE") + " " + myRs.getString("CUST_COUNTRY") + " "
						+ myRs.getString("CUST_ZIP") + "\t" + myRs.getInt("CUST_PHONE") + "\t\t" 
						+ myRs.getString("CUST_EMAIL") //+ " " + myRs.getTimestamp("LAST_UPDATED")
						);
			}
			
			// 4. Process the result set
			//dms - added missing columns from database table * corrected get statement datatypes
			while (myRs.next()) { 
				System.out.println(myRs.getString("FIRST_NAME") + " " + myRs.getString("MIDDLE_NAME") + " "
						+ myRs.getString("LAST_NAME") + "\t" + myRs.getInt("SSN") + "\t"
						+ myRs.getString("CREDIT_CARD_NO") + "\t" + myRs.getString("APT_NO") + " "
								+ myRs.getString("STREET_NAME") + " " + myRs.getString("CUST_CITY") + " "
								+ myRs.getString("CUST_STATE") + " " + myRs.getString("CUST_COUNTRY") + " "
								+ myRs.getString("CUST_ZIP") + "\t" + myRs.getInt("CUST_PHONE") + "\t\t" 
								+ myRs.getString("CUST_EMAIL") //+ " " + myRs.getTimestamp("LAST_UPDATED")
						);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}

	}

	@Override  
	// dms - changed method signature to match interface
	// dms - changed datatypes of method parameters to match datatypes in DB
	public void ModifyCustomerName(int SSN, String FIRST_NAME,String MIDDLE_NAME, String LAST_NAME ) throws SQLException {
			                       
		Connection myConn = null;
		// Statement myStmt = null;
		PreparedStatement myStmt = null;

		//ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			myStmt = myConn.prepareStatement(SqlFile.query5a);
	
			// 2a. set the parameter;
			// dms - added set for parameter 2 (middle_name)
			myStmt.setString(1, FIRST_NAME);
			myStmt.setString(2, MIDDLE_NAME);
			myStmt.setString(3, LAST_NAME);
			myStmt.setInt(4, SSN);
			
									
			// 3. Execute SQL query
			int Rows = myStmt.executeUpdate();
		
			if (Rows == 0) {
				System.out.println("******************************************");
				System.out.println("There is no record for the SSN to update !");
				System.out.println("******************************************");
			} else {
				System.out.println(SSN + ": Updated");
			}
			
		}  finally {
			/*if (myRs != null) {
				myRs.close();
			} */

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}

	}

	
	@Override
	// dms - changed method signature to match interface
	// dms - changed datatypes of method parameters to match datatypes in DB
	public void ModifyCustomerAdd(int SSN, String APT_NO, String STREET_NAME, String CUST_CITY, String CUST_STATE,
		 	String CUST_COUNTRY, String CUST_ZIP) throws SQLException {
		Connection myConn = null;
		// Statement myStmt = null;
		PreparedStatement myStmt = null;

		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			myStmt = myConn.prepareStatement(SqlFile.query5b);
	
			// 2a. set the parameter;
			// dms - added missing sestString for CUST_CITY
			
			myStmt.setString(1, APT_NO);
			myStmt.setString(2, STREET_NAME);
			myStmt.setString(3, CUST_CITY);
			myStmt.setString(4, CUST_STATE);
			myStmt.setString(5, CUST_COUNTRY);
			myStmt.setString(6, CUST_ZIP);
			myStmt.setInt(7, SSN);
									
			// 3. Execute SQL query
			int Rows = myStmt.executeUpdate();
	
		
			if (Rows == 0) {
				System.out.println("******************************************");
				System.out.println("There is no record for the SSN to update !");
				System.out.println("******************************************");
			} else {
				System.out.println(SSN + ": Updated");
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}

	}

	
	@Override
	public void ModifyCustomerPhone(int SSN, int Phone) throws SQLException {
		Connection myConn = null;
		// Statement myStmt = null;
		PreparedStatement myStmt = null;

		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			myStmt = myConn.prepareStatement(SqlFile.query5c);
	
			// 2a. set the parameter;
			
			
			myStmt.setInt(1, Phone);
			myStmt.setInt(2, SSN);
									
			// 3. Execute SQL query
			int Rows = myStmt.executeUpdate();
	
		
			if (Rows == 0) {
				System.out.println("******************************************");
				System.out.println("There is no record for the SSN to update !");
				System.out.println("******************************************");
			} else {
				System.out.println(SSN + ": Updated");
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}

	}

	@Override
	public void ModifyCustomerEmail(int SSN, String Email) throws SQLException {
		Connection myConn = null;
		// Statement myStmt = null;
		PreparedStatement myStmt = null;

		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			myStmt = myConn.prepareStatement(SqlFile.query5d);
	
			// 2a. set the parameter;
			
			
			myStmt.setString(1, Email);
			myStmt.setInt(2, SSN);
									
			// 3. Execute SQL query
			int Rows = myStmt.executeUpdate();
	
		
			if (Rows == 0) {
				System.out.println("******************************************");
				System.out.println("There is no record for the SSN to update !");
				System.out.println("******************************************");
			} else {
				System.out.println(SSN + ": Updated");
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}

	}

	@Override
	public void GenerateBill(String creditCard, int month, int year) throws SQLException {

		Connection myConn = null;
		// Statement myStmt = null;
		PreparedStatement myStmt = null;

		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();


			// 2. Create a statement
			myStmt = myConn.prepareStatement(SqlFile.query6);

			// 2a. set the parameter;
			myStmt.setString(1, creditCard);
			myStmt.setInt(2, month);
			myStmt.setInt(3, year);

			// 3. Execute SQL query
			myRs = myStmt.executeQuery();

			if (myRs.next() == false) {
				System.out.println("***********************************");
				System.out.println("No information for your selection !");
				System.out.println("***********************************");
			} else {
				//System.out.println(myRs.getString("BILL"));
				System.out.println("Card Number " + creditCard + " owes $" + myRs.getDouble("BILL") + " for " 
							+ month + "/" + year );
			}
			// 4. Process the result set
			while (myRs.next()) {
				//System.out.println(myRs.getString("BILL"));
				System.out.println("Card Number " + creditCard + " owes $" + myRs.getDouble("BILL") + " for " 
						+ month + "/" + year );
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}

	}

	@Override
	// dms - changed method signature to match interface
	public void DisplayTrans(int ssn, int frYear, int toYear, int frMonth, int toMonth, int frDay, int toDay)
			throws SQLException {

		Connection myConn = null;
		// Statement myStmt = null;
		PreparedStatement myStmt = null;

		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			// myStmt = myConn.createStatement();
			myStmt = myConn.prepareStatement(SqlFile.query7);

			// 2a. set the parameter;
			myStmt.setInt(1, ssn);
			myStmt.setInt(2, frMonth);
			myStmt.setInt(3, frDay);
			myStmt.setInt(4, frYear);
			myStmt.setInt(5, toMonth);
			myStmt.setInt(6, toDay);
			myStmt.setInt(7, toYear);
			

			// 3. Execute SQL query
			// myRs = myStmt.executeQuery("select * from school_customer");
			myRs = myStmt.executeQuery();

			if (myRs.next() == false) {
				System.out.println("***********************************");
				System.out.println("No information for your selection !");
				System.out.println("***********************************");
			} else {
				System.out.println(myRs.getInt("TRANSACTION_ID")+ "\t" + myRs.getInt("MONTH") + "/"
				+ myRs.getInt("DAY") + "/" + myRs.getInt("YEAR") + "\t"  
				+ myRs.getString("CREDIT_CARD_NO") + "\t" + myRs.getInt("CUST_SSN") + "\t"
				/*+ myRs.getInt("BRANCH_CODE")*/  + myRs.getString("TRANSACTION_TYPE") + " "
				);
			}
			// 4. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getInt("TRANSACTION_ID")+ "\t" + myRs.getInt("MONTH") + "/"
				+ myRs.getInt("DAY") + "/" + myRs.getInt("YEAR") + "\t"  
				+ myRs.getString("CREDIT_CARD_NO") + "\t" + myRs.getInt("CUST_SSN") + "\t"
				/*+ myRs.getInt("BRANCH_CODE")*/ +  myRs.getString("TRANSACTION_TYPE") + " "
				);

			}
		} finally {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}
	}
}
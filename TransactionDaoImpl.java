package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDaoImpl implements TransactionDao {

	@Override
	public void getbyZipcode(String zip, int month, int year) throws SQLException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();
			
			// 2. Create a statement
			myStmt = myConn.prepareStatement(SqlFile.query1);

			// 2a. set the parameter;
			//dms - added code to set year parameter
			myStmt.setString(1, zip);
			myStmt.setInt(2, month);
			myStmt.setInt(3, year);

			// 3. Execute SQL query
			// dms - changed get statements to match column data types in database
			myRs = myStmt.executeQuery();

			if (myRs.next() == false) {
				System.out.println("************************************");
				System.out.println("*No information for your selection!*");
				System.out.println("************************************");
			} else {
				System.out.println("\nTransactions made by customers living in " + zip 
						+ " for " + month +"/" + year + " are: \n");
				System.out.println("DATE" + "\t\t" + "CARD NUMBER" + "\t\t" + "SSN" + "\t\t" 
						+ "AMOUNT" + "\t\t" + "TYPE");
				System.out.println(myRs.getInt("MONTH") + "/" + myRs.getInt("DAY") + "/" + myRs.getInt("YEAR") 
						+ "\t" + myRs.getString("CREDIT_CARD_NO") + "\t" + myRs.getInt("CUST_SSN")+ "\t"  + "$" 
						+ myRs.getDouble("TRANSACTION_VALUE") + "\t\t" + myRs.getString("TRANSACTION_TYPE") ); 		
			}
			// 4. Process the result set
			// dms - changed get statements to match column data types in database
			while (myRs.next()) {
				System.out.println(myRs.getInt("MONTH") + "/" + myRs.getInt("DAY") + "/" + myRs.getInt("YEAR") 
					+ "\t" + myRs.getString("CREDIT_CARD_NO") + "\t" + myRs.getInt("CUST_SSN")+ "\t"  + "$"
					+ myRs.getDouble("TRANSACTION_VALUE") + "\t\t" + myRs.getString("TRANSACTION_TYPE") ); 
			}
		} 
		finally {
			ConnectionManager.closeConnection (myRs, myStmt, myConn);
		}

	}

	@Override
	public void getbyType(String type) throws SQLException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			myStmt = myConn.prepareStatement(SqlFile.query2);

			// 2a. set the parameter;
			myStmt.setString(1, type);

			// 3. Execute SQL query
			// dms - changed get statements to match column data types in database
			myRs = myStmt.executeQuery();

			if (myRs.next() == false) {
				System.out.println("***********************************");
				System.out.println("No information for your selection !");
				System.out.println("***********************************");
			} else {
				System.out.println("\nTotal number of & total amount for " + type.toUpperCase() + " transactions" );
				System.out.println("\n# of Transactions" + "\t" + "TOTAL AMOUNT");
				System.out.println(myRs.getInt("# of Transaction") + "\t\t\t"  + "$" + myRs.getDouble("Transaction Amount"));
						
			}
			// 4. Process the result set
			// dms - changed get statements to match column data types in database
			while (myRs.next()) {
				System.out.println(myRs.getInt("# of Transaction") + "\t\t\t"  + "$" + myRs.getDouble("Transaction Amount"));				
			}
		} finally {		
			ConnectionManager.closeConnection (myRs, myStmt, myConn);			
		}

	}

	@Override
	public void getbyState(String state) throws SQLException {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to database
			myConn = ConnectionManager.getConnection();

			// 2. Create a statement
			myStmt = myConn.prepareStatement(SqlFile.query3);

			// 2a. set the parameter;
			myStmt.setString(1, state);

			// 3. Execute SQL query
			// dms - changed get statements to match column data types in database
			myRs = myStmt.executeQuery();

			if (myRs.next() == false) {
				System.out.println("***********************************");
				System.out.println("No information for your selection !");
				System.out.println("***********************************");
			} else {
				System.out.println("The branch code number and the total number of & transaction amounts for " + state.toUpperCase() + "\n");
				System.out.println("BC" + "\t" + "# of Transactions" + "\t" + "AMOUNT");
				System.out.println(myRs.getInt("BC") + "\t\t" + myRs.getInt("# of Transaction") + "\t\t" + "$" + myRs.getDouble("Transaction Amount"));
			}
			// 4. Process the result set
			// dms - changed get statements to match column data types in database
			while (myRs.next()) {
				System.out.println(myRs.getInt("BC") + "\t\t" + myRs.getInt("# of Transaction") + "\t\t" + "$" + myRs.getDouble("Transaction Amount"));
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			ConnectionManager.closeConnection (myRs, myStmt, myConn);			
		}

	}
}
package runner;

import dao.TransactionDaoImpl;
import java.sql.SQLException;

public class TransactionRunner {

	// display the transactions made by customers in a given zip
	// for a given month and year
	public void method1() throws SQLException {
		int month=0, year=0;
		String cust_zip="";	

		cust_zip = GetValidInput.getZip();
		month = GetValidInput.getMonth("");
		year = GetValidInput.getYear("");

		TransactionDaoImpl TXDaoimpl1 = new TransactionDaoImpl();
		TXDaoimpl1.getbyZipcode(cust_zip, month, year);		
	} 

	// display the number & total values of transactions for a give type
	public void method2() throws SQLException {
		 		
		String type; 
		
		type = GetValidInput.getTransactionType();
		TransactionDaoImpl TXDaoimpl2 = new TransactionDaoImpl();
		TXDaoimpl2.getbyType(type);

	}

	
	// display the number & total values of transactions for branches 
	// in a given state
	public void method3() throws SQLException {
		      
		String state = "";
		
		state = GetValidInput.getState();
		TransactionDaoImpl TXDaoimpl3 = new TransactionDaoImpl();
		TXDaoimpl3.getbyState(state);

	}
}
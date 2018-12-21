package dao;

import java.sql.SQLException;

public interface CustomerDao {

	// dms - changed ssn to datatype int to match database datatype
	public void CheckCustomer(int ssn) throws SQLException;

	public void ModifyCustomerName(int SSN, String FIRST_NAME, String MIDDLE_NAME, String LAST_NAME) throws SQLException;
	
	public void ModifyCustomerAdd(int SSN, String APT_NO, String STREET_NAME, String CUST_CITY, String CUST_STATE,
		 	String CUST_COUNTRY, String CUST_ZIP) throws SQLException;
	
	public void ModifyCustomerEmail(int SSN, String Email) throws SQLException;

	public void ModifyCustomerPhone(int SSN, int Phone) throws SQLException;
	
	public void GenerateBill(String creditCard, int month, int year) throws SQLException;

	public void DisplayTrans(int ssn, int frYear, int toYear, int frMonth, int toMonth, int frDay, int toDay)
			throws SQLException;
}
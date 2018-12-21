package dao;

import java.sql.SQLException;

public interface TransactionDao {
  	
	     public void getbyZipcode(String cust_zip,int month, int year) throws SQLException;
	     public void getbyType(String type) throws SQLException;
		 public void getbyState(String state) throws SQLException;
}

package dao;

import java.sql.*;

public class ConnectionManager {
	private static String url = "jdbc:mysql://localhost:3306/cdw_sapp";    
    private static String username = "root";   
    private static String password = "root";
    private static Connection con;

    public static Connection getConnection() {            
    	try {
    		con = DriverManager.getConnection(url, username, password);
    		System.out.println("\nDatabase connection successful!\n");
        } catch (SQLException ex) {                
                System.out.println("Failed to create the database connection."); 
        } 
    	
        return con;
    }
    
    public static void closeConnection(ResultSet myRs, Statement myStmt, Connection myConn) throws SQLException {
	    if (myRs != null) {
			myRs.close();
		}
	
		if (myStmt != null) {
			myStmt.close();
		}
	
		if (myConn != null) {
			myConn.close();
		}
		
		System.out.println("\nConnection closed.");
    }
}

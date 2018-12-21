package dao;

public class SqlFile {

	//dms - removed hardcoded cust_zip of 44070 from query and replaced with ?
	public static final String query1 = "select *" + "from cdw_sapp_creditcard cr "
			+ "JOIN cdw_sapp_customer cb ON cr.CREDIT_CARD_NO = cb.CREDIT_CARD_NO "
			+ "WHERE (cb.CUST_ZIP = ? AND cr.MONTH = ?  AND cr.YEAR = ?) " + "order by DAY desc";

	//dms - corrected query to SUM not COUNT the transaction values
	public static final String query2 = "select  TRANSACTION_TYPE 'Transaction Type', count(TRANSACTION_TYPE) '# of Transaction', \r\n"
			+ " sum(TRANSACTION_VALUE) 'Transaction Amount' " + "from cdw_sapp_creditcard cr "
			+ "WHERE cr.TRANSACTION_TYPE = ?";

	//dms - corrected to query to GROUP BY card.BRANCH_CODE not card.BRANCH_NAME
	public static final String query3 = "SELECT branch.BRANCH_CODE as BC, count(*) '# of Transaction', sum(TRANSACTION_VALUE)"
			+ " 'Transaction Amount' " + "FROM CDW_SAPP_CREDITCARD as card " + "JOIN CDW_SAPP_BRANCH as branch "
			+ "ON card.BRANCH_CODE= branch.BRANCH_CODE " + "WHERE branch.BRANCH_STATE = ?"
			+ "GROUP BY card.BRANCH_CODE";

	public static final String query4 = "SELECT *" + "FROM CDW_SAPP_CUSTOMER " + "WHERE SSN = ?";

	public static final String query5a = " update cdw_sapp_customer "
			+ " SET FIRST_NAME = ?, MIDDLE_NAME = ?, LAST_NAME = ? "
			+ "WHERE SSN = ?";
	
	public static final String query5b = " update cdw_sapp_customer "
			+ "	SET APT_NO = ?, STREET_NAME = ?, CUST_CITY = ?, CUST_STATE = ?, CUST_COUNTRY = ?,"
			+ " CUST_ZIP = ? " + "WHERE SSN = ?";
	
	public static final String query5c = " update cdw_sapp_customer "
			+ "SET CUST_PHONE = ? " + "WHERE SSN = ?";
	
	public static final String query5d = " update cdw_sapp_customer "
			+ " SET CUST_EMAIL = ? " + "WHERE SSN = ?";

	public static final String query6 = "SELECT SUM(TRANSACTION_VALUE) as BILL " + "FROM CDW_SAPP_CREDITCARD "
			+ "WHERE CREDIT_CARD_NO = ? AND " + "MONTH = ? AND " + "YEAR = ? ";
	
	// dms - corrected query 
	public static final String query7 = "select * "
				+ " from cdw_sapp_creditcard" + " where cust_ssn = ?"
				+ " and ( str_to_date( concat_ws('-', month, day, year) , '%m-%d-%Y') " 
				+ " between str_to_date( concat_ws('-', ?, ?, ?) , '%m-%d-%Y') "
				+ " and str_to_date( concat_ws('-', ?, ?, ?) , '%m-%d-%Y') ) "
				+ " order by year desc, month desc, day desc";
        

}

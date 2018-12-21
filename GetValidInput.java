package runner;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// This class provides methods to get and validate input from user.
// It also provides "one location" if any changes need to be done to the 
// custom error messages and user prompts.

public class GetValidInput {
	static Scanner scanner = new Scanner(System.in);
	boolean valid;
	
	
	// get zip and validate
	public static String getZip() {
		
		String zipSelection = "\nEnter Zip Code: \n" + "";
				
		String cust_zip = "";
		int zipInt;				// used to test for NumberFormatException
		boolean valid = false;
		
		do {			
			try {
				System.out.print(zipSelection);
				cust_zip = scanner.nextLine();

				// validates zip contains only numbers
				// throws NumberFormatException if string contains other than integers
				zipInt = Integer.parseInt(cust_zip);

				// validates length of zip code
				while (cust_zip.length() != 5) {
					throw new IllegalArgumentException();
				}
				valid = true;

			} catch (NumberFormatException e) {
				System.out.print("****************************************\n");
				System.out.print("**You have entered an invalid Zip Code**\n");
				System.out.print("***Zip Code must contain only numbers***\n");
				System.out.print("****************************************\n");
				System.out.println();
				valid = false;

			} catch (IllegalArgumentException e) {
				System.out.print("****************************************\n");
				System.out.print("**You have entered an invalid Zip Code**\n");
				System.out.print("*****Zip Code must contain 5 digits*****\n");
				System.out.print("****************************************\n");
				System.out.println();
				valid = false;
			} 
		} while (!valid);
		
		return cust_zip;
	}

	
	// get month and validate
	public static int getMonth(String monthDesc) {
		
		final String monthSelection = "\nEnter" + monthDesc + " Month as MM: \n" + "";
		
		int month = 0;
		boolean valid = false;
				
		do {
			try {				
				System.out.print(monthSelection);
				month = scanner.nextInt();
				
					while (month < 1 || month > 12) {
						throw new IllegalArgumentException();
					}
				valid = true;	
										
			} catch (InputMismatchException e) {
				System.out.print("*************************************\n");
				System.out.print("**You have entered an invalid Month**\n");
				System.out.print("********MM must be an integer********\n");
				System.out.print("*************************************\n");
	            scanner.next();
	            valid = false;
			}
			catch (IllegalArgumentException e) {
				System.out.print("***************************************\n");
				System.out.print("***You have entered an invalid Month***\n");
				System.out.print("******MM must be between 1 & 12********\n");
				System.out.print("***************************************\n");
				valid = false;
			}		
			
		} while (!valid);
		
		return month;
	}
	
	
	// get day and validate
	public static int getDay(String dayDesc, int month) {
		
		final String daySelection = "\nEnter" + dayDesc + " Day as DD: \n" + "";
		
		int day = 0, totDay = 0;
		boolean valid = false;
				
		do {
			try {				
				System.out.print(daySelection);
				day = scanner.nextInt();
				
					// ignore leap years and use 28 days for Feb
					switch (month) {
					case 1: 
						totDay = 31;
						while (day < 1 || day > 31) {					
							throw new IllegalArgumentException();
						}
						valid = true;
						break;
					case 2:
						totDay = 28;
						while (day < 1 || day > 28) {					// ignore leap years 
							throw new IllegalArgumentException();
						}
						valid = true;
						break;
					case 3:
						totDay = 31;
						while (day < 1 || day > 31) {					
							throw new IllegalArgumentException();
						}
						valid = true;
						break;
					case 4:
						totDay = 30;
						while (day < 1 || day > 30) {					
							throw new IllegalArgumentException();
						}
						valid = true;
						break;
					case 5:
						totDay = 31;
						while (day < 1 || day > 31) {					
							throw new IllegalArgumentException();
						}
						valid = true;
						break;
					case 6:
						totDay = 30;
						while (day < 1 || day > 30) {					
							throw new IllegalArgumentException();
						}
						valid = true;
						break;
					case 7:
						totDay = 31;
						while (day < 1 || day > 31) {					
							throw new IllegalArgumentException();
						}
						valid = true;
						break;
					case 8:
						totDay = 31;
						while (day < 1 || day > 31) {					
							throw new IllegalArgumentException();
						}
						valid = true;
						break;
					case 9:
						while (day < 1 || day > 30) {					
							throw new IllegalArgumentException();
						}
						valid = true;
						break;
					case 10:
						totDay = 31;
						while (day < 1 || day > 31) {					
							throw new IllegalArgumentException();
						}
						valid = true;
						break;
					case 11:
						totDay = 30;
						while (day < 1 || day > 30) {					
							throw new IllegalArgumentException();
						}
						valid = true;
						break;
					case 12:
						totDay = 31;
						while (day < 1 || day > 31) {					
							throw new IllegalArgumentException();
						}
						valid = true;
						break;
					default:	
						valid = true;	
						break;
					}
										
			} catch (InputMismatchException e) {
				System.out.print("*************************************\n");
				System.out.print("**You have entered an invalid Day**\n");
				System.out.print("********DD must be an integer********\n");
				System.out.print("*************************************\n");
	            scanner.next();
	            valid = false;
			}
			catch (IllegalArgumentException e) {
				if (totDay == 30) {
					System.out.print("***************************************\n");
					System.out.print("****You have entered an invalid Day****\n");
					System.out.print("******DD must be between 1 & 30********\n");
					System.out.print("***************************************\n");
		            valid = false;
				}
				else if (totDay == 31) {
					System.out.print("***************************************\n");
					System.out.print("****You have entered an invalid Day****\n");
					System.out.print("******DD must be between 1 & 31********\n");
					System.out.print("***************************************\n");
					valid = false;
				}
				else if (totDay == 28) {
					System.out.print("***************************************\n");
					System.out.print("****You have entered an invalid Day****\n");
					System.out.print("******DD must be between 1 & 28********\n");
					System.out.print("***************************************\n");
					valid = false;
				}
				
			}	
			
			
		} while (!valid);
		
		return day;
	}
	
	
	// get year and validate
	public static int getYear(String yearDesc) {
		
		final String yearSelection = "\nEnter" + yearDesc + " Year between 1950 to 2018 as YYYY  \n" + "";
		
		int year = 0;
		boolean valid = false;
		
		do {
			try {
				
				System.out.print(yearSelection);
				year = scanner.nextInt();
		
				while (year < 1950 || year > 2018) {	
					throw new IllegalArgumentException();		
				}			
					
				valid = true;
				
			} catch (InputMismatchException e) {
				System.out.print("************************************\n");
				System.out.print("**You have entered an invalid Year**\n");
				System.out.print("***YYYY must contain only numbers***\n");
				System.out.print("************************************\n");
		        scanner.next();
		        valid = false;
		        
			} catch (IllegalArgumentException e) {
				System.out.print("************************************\n");
				System.out.print("**You have entered an invalid Year**\n");
				System.out.print("**YYYY must be between 1950 & 2018**\n");
				System.out.print("************************************\n");
				valid = false;
			}
				
		} while (!valid);
		
		return year;
	
	}
	
	
	// get transaction type and validate
	public static String getTransactionType() {
		
		//final String transactionType = "education, entertainment, grocery, gas, bills, test, healthcare";
		final String transactionType = "education|entertainment|grocery|gas|bills|test|healthcare";

		
		final String typeSelection = "\nEnter the Transaction Type from the list below. \n" + "" 
                + "\n'Education', 'Entertainment', 'Grocery', 'Gas', 'Bills', 'Test', 'Healthcare'\n ";
		
		String type = ""; 
		Boolean valid = false;
		
		Pattern pattern = Pattern.compile(transactionType);
		Matcher matcher;

		do {
			try {
				System.out.print(typeSelection);
				type = scanner.next().toLowerCase();
			
				matcher = pattern.matcher(type);
			
				while (!matcher.matches()) {
					throw new IllegalArgumentException();
				}
				valid = true;
				
			} catch (IllegalArgumentException e) {
				System.out.print("***********************************************\n");
				System.out.print("***You have entered invalid Transaction Type***\n");
				System.out.print("***********************************************\n");
				valid = false;
			} 
		} while (!valid);
		
		return type;
		
	}

	
	// get state and validate
	public static String getState() {
		
		final String stateSelection = "\nEnter a 2 character State code \n";
		
		final String stateExpression = "AK|AL|AR|AZ|CA|CO|CT|DC|DE|FL|GA|HI|IA|ID|IL|IN|KS|KY|LA|MA|MD|ME|MI|MN|MO|MS|MT|NC|ND|NE|NH|NJ|"
				+ "NM|NV|NY|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VA|VT|WA|WI|WV|WY";

		String state = "";		
		boolean valid = false;
			
		Pattern pattern = Pattern.compile(stateExpression);
		Matcher matcher;
		
		do {
			try {
				System.out.print(stateSelection);
				state = scanner.next().toUpperCase();
			
				matcher = pattern.matcher(state);
			
				while (!matcher.matches()) {
					throw new IllegalArgumentException();
				}
				valid = true;
				
			} catch (IllegalArgumentException e) {
				System.out.print("******************************************\n");
				System.out.print("**You have entered an invalid State code**\n");
				System.out.print("******************************************\n");
				System.out.println();
				System.out.println("Valid State codes are:\n" + stateExpression);
				valid = false;
			} 
		} while (!valid);
		
		return state;
	}
	
	// get cust_ssn and validate
	public static int getSSN() {
		
		final String ssnSelection = "\nEnter Social Security Number (SSN): \n" + "";
		
		int cust_ssn = 0;
		boolean valid = false;
		
			do {
				try {
					
					System.out.print(ssnSelection);
					cust_ssn = scanner.nextInt();
					
					// valid cust_ssn must be of length = 9
					while (String.valueOf(cust_ssn).length() != 9) {		
						throw new IllegalArgumentException();
					}					
					valid = true;
					
				} catch (InputMismatchException e) {
					System.out.print("**************************************\n");
					System.out.print("***You have entered an invalid SSN****\n");
					System.out.print("****SSN must contain only numbers*****\n");
					System.out.print("**************************************\n");
					scanner.next();
					valid = false;
					
				} catch (IllegalArgumentException e) {
					System.out.print("**************************************\n");
					System.out.print("***You have entered an invalid SSN****\n");
					System.out.print("*****SSN must contain 9 digits********\n");
					System.out.print("**************************************\n");
					valid = false;
				}
			} while (!valid);
	
			return cust_ssn;
	}
	
	// get and validate Credit Card number
	public static String getCardNum() {
	
		final String creditCardSelection = "\nEnter Credit Card Number: \n" + "";

		String creditCardStr = "";
		Long creditCardNum;		// used to test for NumberFormatException
		boolean valid = false;
	
		do {
			try {
				System.out.print(creditCardSelection);
				creditCardStr = scanner.next();
				
				// validates Credit Card Number contains only numbers
				// throws NumberFormatException if string contains other than numbers
				creditCardNum = Long.parseLong(creditCardStr); 		
	
				while (creditCardStr.length() != 16) {
					throw new IllegalArgumentException();
				}
				valid = true;
				
			} catch (NumberFormatException e) {
				System.out.print("************************************************\n");
				System.out.print("*******You have entered an invalid Number*******\n");
				System.out.print("**Credit Card number must contain only numbers**\n");
				System.out.print("************************************************\n");
				valid = false;
				
			} catch (IllegalArgumentException e) {
				System.out.print("******************************************\n");
				System.out.print("****You have entered an invalid Number****\n");
				System.out.print("**Credit Card number must have 16 digits**\n");
				System.out.print("******************************************\n");
				valid = false;
			} 
		} while (!valid);
	
		return creditCardStr;
	
	}
	
	// get and validate phone number
	public static int getPhoneNumber() {
		
		final String phoneNumSelection = "\nEnter 7 digit Phone number: ";
		
		int cust_phone = 0;
		boolean valid = false;
		
		do {
			try {
				System.out.print(phoneNumSelection);
				cust_phone = scanner.nextInt();
				
				// valid cust_phone must be of length = 7
				while (String.valueOf(cust_phone).length() != 7) {		
					throw new IllegalArgumentException();
				}					
	
				valid = true;
				
			} catch (InputMismatchException e) {
				System.out.print("********************************************\n");
				System.out.print("**You have entered an invalid Phone number**\n");
				System.out.print("***Phone number must contain only numbers***\n");
				System.out.print("********************************************\n");
				scanner.next();
				valid = false;
				
			} catch (IllegalArgumentException e) {
				System.out.print("********************************************\n");
				System.out.print("**You have entered an invalid Phone number**\n");
				System.out.print("******Phone number must have 7 digits*******\n");
				System.out.print("********************************************\n");
				valid = false;
			}
		
		} while (!valid);
		
		return cust_phone;
		
	}
	
	
	
}

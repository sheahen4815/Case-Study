package runner;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class MainRunner {

//	private static final com.runner.TransactionRunner New = null;

	public static void main(String[] args)throws SQLException {

		Scanner input = new Scanner(System.in);
		int menu = 0;
		boolean valid1 = false, valid2 = false;
		String again;
		boolean run = true;
			
			while (run) {
				do {
					try {
						String selection = "\nPlease select a number from the following menu. \n \n"
								+ "Transaction Detail: \n"
								+ "1. Display the transactions made by customers living in a given zipcode for a given month and year. \n"
								+ "2. Display the number and total values of transactions for a given type. \n"
								+ "3. Display the number and total values of transactions for branches in a given state. \n\n"
								+ "Customer Detail: \n" + "4. Check the existing account details of a customer. \n"
								+ "5. Modify the existing account details of a customer. \n"
								+ "6. Generate monthly bill for a credit card number for a given month and year. \n"
								+ "7. Display the transactions made by a customer between two dates. \n" + "8. Exit \n"
								+ "";

						System.out.print(selection);
						menu = input.nextInt();
						
					
					while (menu < 1 || menu > 8) {
						throw new IllegalArgumentException();
					}
						valid1 = true;
					} catch (InputMismatchException e) {
						System.out.print("******************************************\n");
						System.out.print("*** You have entered invalid selection ***\n");
						System.out.print("******************************************\n");
						input.next();
						valid1 = false;
					} catch (IllegalArgumentException e) {
						System.out.print("******************************************\n");
						System.out.print("*** You have entered invalid selection ***\n");
						System.out.print("********Selection must from 1 to 8********\n");
						System.out.print("******************************************\n");
						valid1 = false;
					}
				} while (!valid1);
				
				switch (menu) {
				case 1:
					TransactionRunner Transaction1 = new TransactionRunner();
					Transaction1.method1();
					break;
				case 2:
					TransactionRunner Transaction2 = new TransactionRunner();
					Transaction2.method2();
					break;
				case 3:
					TransactionRunner Transaction3 = new TransactionRunner();
					Transaction3.method3();
					break;
				case 4:
					CustomerRunner Customer1 = new CustomerRunner();
					Customer1.method1();
					break;
				case 5:
					CustomerRunner Customer2 = new CustomerRunner();
					Customer2.method2();
					break;
				case 6:
					CustomerRunner Customer3 = new CustomerRunner();
					Customer3.method3();
					break;
				case 7:
					CustomerRunner Customer4 = new CustomerRunner();
					Customer4.method4();
					break;
				case 8:
					System.out.println();
					System.out.println("Thank you and goodbye!");
					run = false;
					valid2 = true;
					break;
				default:
					System.out.println("It is not a correct #");
				}
				
			
				 /*do {
					
					try {
						
						System.out.println();
						System.out.println("Would you like to run another transaction? (Y / N)");
						again = (input.next()).toUpperCase();

						if (again.equals("Y")) {
							run = true;
						} 
						else if (again.equals("N")){
							run = false;
							System.out.println();
							System.out.println("Thank you and goodbye!");
							input.close();
						}
						else {
							throw  new IllegalArgumentException();
						}
						
						valid2 = true;
						
					} catch (IllegalArgumentException e) {
						System.out.print("******************************************\n");
						System.out.print("*** You have entered invalid selection ***\n");
						System.out.print("**********Selection must Y or N***********\n");
						System.out.print("******************************************\n");
						valid2 = false;
					} 
				}  while (!valid2);*/
			}
		
		//input.close();

	}

}

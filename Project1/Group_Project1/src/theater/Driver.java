package theater;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		Scanner clientAns = new Scanner(System.in);
		int ans = 0;

		String theaterName = "Random Theater";
		int theaterCapacity = 100;

		Theater theater = new Theater(theaterName, theaterCapacity);

		showAllCommands();

		do {

			System.out.println("\nEnter selection:");

			try {
				ans = sc.nextInt();
			}catch (Exception ex){
				System.out.println("\nInvalid value please restart program and enter correct value");
				System.exit(1);
			}

			if(ans == 0) {
				theater.storeData();
				return;
			} else if(ans == 1) {

				String clientName;
				String clientAddress;
				String clientPhone;

				System.out.println("\nEnter client name:");
				clientName = clientAns.nextLine();
				System.out.println("\nEnter client address:");
				clientAddress = clientAns.nextLine();
				System.out.println("\nEnter client phone:");
				clientPhone = clientAns.nextLine();

				theater.addClient(clientName, clientAddress, clientPhone);

			} else if(ans == 2) {

				int clientID;

				System.out.println("\nEnter ID of client to be removed:");
				clientID = clientAns.nextInt();

				theater.removeClient(clientID);

			} else if(ans == 3){
				theater.listClients();
			} else if(ans == 4){

				System.out.println("\nEnter customer name:");
				String customerName = clientAns.nextLine();
				System.out.println("\nEnter customer address:");
				String customerAddress = clientAns.nextLine();
				System.out.println("\nEnter customer phone:");
				String customerPhone = clientAns.nextLine();
				System.out.println("\nEnter customer Credit Card number:");
				int creditCardNumber = clientAns.nextInt();
				clientAns.nextLine();//clear input buffer
				System.out.println("\nEnter customer Credit Card expiry date in the form MM/YY:");
				String expiryDate = clientAns.nextLine();

				theater.addCustomer(customerName, customerAddress, customerPhone, creditCardNumber, expiryDate);

			} else if(ans == 5) {

				int customerID;

				System.out.println("\nEnter ID of customer to be removed:");
				customerID = clientAns.nextInt();

				theater.removeCustomer(customerID);

			} else if(ans == 6){

				int customerID;
				int creditCardNumber;
				String expiryDate;

				System.out.println("\nEnter ID of customer:");
				customerID = clientAns.nextInt();
				System.out.println("\nEnter customer's Credit Card number:");
				creditCardNumber = clientAns.nextInt();
				clientAns.nextLine();//clear input buffer
				System.out.println("\nEnter Credit Card Number expiry date in the form MM/YY:");
				expiryDate = clientAns.nextLine();

				theater.addCustomerCreditCard(customerID, creditCardNumber, expiryDate);

			} else if(ans == 7){

				int creditCardNumber;
				System.out.println("\nEnter Credit Card number to be removed:");
				creditCardNumber = clientAns.nextInt();
				theater.removeCreditCard(creditCardNumber);

			} else if(ans == 8){
				theater.listCustomers();
			} else if(ans == 9){

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String showName;
				int clientID;
				String start;
				String end;

				System.out.println("\nEnter name of the show:");
				showName = clientAns.nextLine();
				System.out.println("\nEnter ID of client:");
				clientID = clientAns.nextInt();
				System.out.println("\nEnter show start date in the format \"yyyy-MM-dd\":");
				start = clientAns.nextLine();
				System.out.println("\nEnter show end date in the format \"yyyy-MM-dd\":");
				end = clientAns.nextLine();

				Date startDate = df.parse(start);
				Date endDate = df.parse(end);

				theater.addShow(showName, clientID, startDate, endDate);

			} else if(ans == 10){
				theater.listAllShows();
			} else if(ans == 11){
				theater.storeData();
			} else if(ans == 12){
				theater = theater.loadFromDisk();
			} else if (ans == 13){
				showAllCommands();
			} else{
				System.out.println("Input must be between 0-13, try again.");
			}

		} while (ans != 0);

	}//end main

	public static void showAllCommands(){
		System.out.println("\nWelcome to the Theater");
		System.out.println(" 0: Exit");
		System.out.println(" 1: Add a Client");
		System.out.println(" 2: Remove a Client");
		System.out.println(" 3: List all Clients");
		System.out.println(" 4: Add Customer");
		System.out.println(" 5: Remove Customer");
		System.out.println(" 6: Add Credit Card");
		System.out.println(" 7: Remove Credit Card");
		System.out.println(" 8: List all Customers");
		System.out.println(" 9: Add a Show");
		System.out.println("10: List all Shows");
		System.out.println("11: Store Data");
		System.out.println("12: Retrieve Data");
		System.out.println("13: Help");
	}//end show all commands

}//end all
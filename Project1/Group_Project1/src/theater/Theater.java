package theater;

import java.io.File;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Theater implements Serializable {

    String theaterName;
    int capacity;
    ArrayList<Client> clientList = new ArrayList<Client>();//list of all currently registered clients
    ArrayList<Customer> customerList = new ArrayList<Customer>();//list of all currently registered members
    ArrayList<Show> playList = new ArrayList<Show>(); // list of all the shows
    static String homeDirectory = System.getProperty("user.home"); // home directory
    static String fileSeparator = File.separator;
    static String theaterDirectory = "theater";
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	int numOfClients;//holds total number of clients ever had, using this number to ensure ID is never repeated
	                 //just using the size of the array wouldnt be accurate as clients can be removed and added and could have repeating numbers
	int numOfCustomers;//same as above except for customers

    public Theater(String theaterName, int capacity) {//Theater constructor
        this.theaterName = theaterName;
        this.capacity = capacity;
        this.numOfClients = 0;
        this.numOfCustomers = 0;
    }

    public void changeCapacity(int capacity){//method to change capacity
        this.capacity = capacity;
    }

    public void addClient(String clientName,
                          String clientAddress, String clientPhone){
        int clientID = ++numOfClients;//used to ensure id is always unique
        Client client = new Client(clientID, clientName, clientAddress, clientPhone, 0);
        clientList.add(client);
    }

	//loop that goes through all clients and
	// if it finds the ID will remove the corresponding client after making sure a play doesn't exist
    public void removeClient(int clientID){
        for(int i = 0; i < clientList.size(); i++){
            if(clientID == clientList.get(i).getClientID()){
            	for(int j = 0; j < playList.size(); j++){
            		if(clientID == playList.get(j).getClientId()){
            			Date todayDate = new Date();
            			if(!todayDate.before(playList.get(j).getPlayStartDate())){
            				System.out.println("Cannot remove Client; has show scheduled for future date.");
            				return;
						}else{
            				break;
						}
					}
				}
             	clientList.remove(i);
                return;
            }//end if
        }//end for
    }

    public void listClients(){//print all clients
        for(int i = 0; i < clientList.size(); i++){
            System.out.println(clientList.get(i));
        }
    }

    public void addCustomer(String customerName, String customerAddress, String customerPhone,
							int creditCardNumber, String expiryDate){
        int customerID = ++numOfCustomers;//used to ensure id is always unique
        Customer customer = new Customer(customerName, customerAddress, customerPhone, customerID);
        customer.addCreditCard(creditCardNumber, expiryDate);
        customerList.add(customer);
    }

    public void removeCustomer(int customerID){
        for(int i = 0; i < customerList.size(); i++){//loop that goes through all customers and
            // if it finds the ID will remove the corresponding customer
            if(customerID == customerList.get(i).customerID){
            	customerList.remove(i);
                return;
            }//end if
        }
    }

    
    //loops through all customers and if it find the customer with the corresponding ID
    //will add the credit card information as a new CreditCard object to its creditCardList
    public void addCustomerCreditCard(int customerID, int creditCardNumber, String expiryDate) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerID == customerList.get(i).customerID) {
                customerList.get(i).addCreditCard(creditCardNumber, expiryDate);
                return;
            }//end if
        }
    }

    //nested loop that loops through all customers in the customer list and their creditCard lists
    //until the credit card is found, once the credit card is found the credit card entry in the list
    //is deleted unless the credit card is the only one in the list
    public void removeCreditCard(int creditCardNumber){
        for (int i = 0; i < customerList.size(); i++) {
            for (int j = 0; j < customerList.get(i).creditCardList.size(); j++){
                if(customerList.get(i).creditCardList.get(j).creditCardNumber == creditCardNumber){
                    if(customerList.get(i).creditCardList.size() > 1)
                        customerList.get(i).creditCardList.remove(j);
                    return;
                }
            }
        }
    }

    public void listCustomers(){//print all customers
        for(int i = 0; i < customerList.size(); i++){
            System.out.println(customerList.get(i));
            System.out.println("\n");
        }
    }
    
    /**
     * Add a Show/Play. Add a new show for a client. The values accepted are the name of the show,
     * the client id, and the period for which the client wants the theater for this play. The entire range
     * of dates should be available, or the process fails
     * @param playName- name of show
     * @param startDate-start date of show
     * @param endDate- end date of show
     */
    public void addShow(String playName, int clientId, Date startDate, Date endDate) {
    	
    	//Check if the client exists. Is 0 a valid client id.
    	boolean isClientPresent = false;
    	for(Client client : clientList) {
    		if (client.getClientID() == clientId) {
    			isClientPresent = true;
    		}
    	}
    	
    	if (!isClientPresent) {
    		System.out.println("Client is not present in the client id list");
    		return;
    	}
    	
    	if (playName == null || playName.trim().length() == 0) {
    		System.out.println("Playname is required value");
    		return;
    	}
    	
    	if (startDate == null || endDate == null) {
    		System.out.println("Start and End dates are required");
    		return;
    	}
    	
    	Show show = new Show(playName, clientId, startDate, endDate);

    	if(playList.size() == 0 ) {
    		playList.add(show);
    		return;
    	}

    	boolean isDateAvailable = true;

    	for(int i =0; i < playList.size(); i++) {
    		Show currentPlay = playList.get(i);
    		Date currentPlayStartDate = currentPlay.getPlayStartDate();
    		Date currentPlayEndDate = currentPlay.getPlayEndDate();
    		
    		if( startDate.after(currentPlayStartDate) && startDate.before(currentPlayEndDate)) {
    			isDateAvailable = false;
    			break;
    		}
    		
    		if( endDate.after(currentPlayStartDate) && endDate.before(currentPlayEndDate)) {
    			isDateAvailable = false;
    			break;
    		}
    	}
    	
    	if (isDateAvailable) {
    		playList.add(show);
    	} else {
    		System.out.println("Date is already reserved. Unable to add to play list");
    	}
    	
    }
    
    public void listAllShows() {
		System.out.println("All shows available:");

    	for(int i=0; i < playList.size(); i++) {
    		System.out.println( playList.get(i));
    	}
    }
    
    public void storeData() {

    	try {
    		Files.deleteIfExists(Paths.get(homeDirectory + fileSeparator +theaterDirectory+ fileSeparator +"theaterNameCapacity.txt"));
    	} catch (Exception ex) {
    		System.out.println("Cleaned directory");
    	}
    	
    	try {
    		Files.deleteIfExists(Paths.get(homeDirectory + fileSeparator +theaterDirectory+ fileSeparator +"customerList.txt"));
    	} catch (Exception ex) {
    		System.out.println("Cleaned directory");
    	}
    	
    	try {
    		Files.delete(Paths.get(homeDirectory + fileSeparator +theaterDirectory+ fileSeparator +"clientList.txt"));
    	} catch (Exception ex) {
    		System.out.println("Cleaned directory");
    	}
    	
    	try {
    		Files.delete(Paths.get(homeDirectory + fileSeparator +theaterDirectory+ fileSeparator +"shows.txt"));
    	} catch (Exception ex) {
    		System.out.println("Cleaned directory");
    	}
    	
    	try {
    		Files.delete(Paths.get(homeDirectory + fileSeparator +theaterDirectory));
    	} catch (Exception ex) {
    		System.out.println("Cleaned directory");
    	}
    	
    	
    	try {
    		
    		Files.createDirectory(Paths.get(homeDirectory + fileSeparator +theaterDirectory));
    		
    		Path theaterCapacityPath = Files.createFile(Paths.get(homeDirectory + fileSeparator +theaterDirectory+ fileSeparator +"theaterNameCapacity.txt"));
    		StringBuilder theaterCapacityBuilder = new StringBuilder();

    		theaterCapacityBuilder.append(theaterName).append("|").append(capacity);
    		Files.write(theaterCapacityPath, theaterCapacityBuilder.toString().getBytes());

    		Path customerListPath = Files.createFile(Paths.get(homeDirectory + fileSeparator +theaterDirectory+ fileSeparator +"customerList.txt"));
    		StringBuilder customerListBuilder = new StringBuilder();
    		for (Customer customer : customerList) {
    			customerListBuilder
    			.append(customer.customerID).append("|")
    			.append(customer.customerAddress).append("|")
    			.append(customer.customerName)
    			.append(customer.customerPhone);
    			
    			customerListBuilder.append(":");
    			for (CreditCard creditCard : customer.creditCardList) {
    				customerListBuilder.append(creditCard.creditCardNumber)
    				.append("|").append(creditCard.expiryDate);
    			}
    			customerListBuilder.append("\n");
    			
    		}
    		Files.write(customerListPath, customerListBuilder.toString().getBytes());
    		
    		
    		Path clientListPath = Files.createFile(Paths.get(homeDirectory + fileSeparator +theaterDirectory+ fileSeparator +"clientList.txt"));
    		
    		StringBuilder clientListBuilder = new StringBuilder();
    		for (Client client : clientList) {
    			clientListBuilder
    			.append(client.getClientID())
    			.append("|")
    			.append(client.getClientName())
    			.append("|")
    			.append(client.getClientAddress())
    			.append("|")
    			.append(client.getClientPhone())
    			.append("|")
    			.append(client.getBalanceDue());
    			clientListBuilder.append("\n");
    		}
    		Files.write(clientListPath, clientListBuilder.toString().getBytes());
    		
    		
    		Path showPath = Files.createFile(Paths.get(homeDirectory + fileSeparator +theaterDirectory+ fileSeparator +"shows.txt"));
    		StringBuilder showListBuilder = new StringBuilder();
    		for (Show show : playList) {
    			showListBuilder
    			.append(show.getPlayName())
    			.append("|")
    			.append(show.getClientId())
    			.append("|")
    			.append(df.format(show.getPlayStartDate()))
    			.append("|")
    			.append(df.format(show.getPlayEndDate())).append("\n");
    		}
    		Files.write(showPath, showListBuilder.toString().getBytes());
    	} catch (Exception ex) {
    		System.out.println("Unable to create one or more files in " + homeDirectory + fileSeparator +theaterDirectory);
    	}
    }//end storeData
    
    /**
     * 
     */
    public static Theater loadFromDisk() {
    	
    	List<String> clientListContent = null;
    	List<String> customerListContent = null;
    	List<String> showListContent = null;
    	List<String> theaterCapacityContent = null;
    	Theater theater = null;

		try {
			theaterCapacityContent = Files.readAllLines(Paths.get(homeDirectory + fileSeparator +theaterDirectory+ fileSeparator +"theaterNameCapacity.txt"),Charset.defaultCharset());
			if(theaterCapacityContent != null && theaterCapacityContent.size() > 0) {
				String[] line = theaterCapacityContent.get(0).split("\\|");
				theater = new Theater(line[0], Integer.parseInt(line[1]));
			}
		} catch (Exception e) {
			System.err.println("Unable to find file");
		}
		
		try {
			clientListContent = Files.readAllLines(Paths.get(homeDirectory + fileSeparator +theaterDirectory+ fileSeparator +"clientList.txt"),Charset.defaultCharset());
			if(clientListContent != null && clientListContent.size() > 0) {
				for(String line : clientListContent) {
					String [] content = line.split("\\|");
					Client client = new Client(Integer.parseInt(content[0]), content[1], content[2], content[3], Double.parseDouble(content[4]));
					theater.clientList.add(client);
				}
			}
		} catch (Exception e) {
			System.err.println("Unable to find file");
		}
		
		try {
			customerListContent = Files.readAllLines(Paths.get(homeDirectory + fileSeparator +theaterDirectory+ fileSeparator +"customerList.txt"),Charset.defaultCharset());
			if(customerListContent != null && customerListContent.size() > 0) {
				for(String line : customerListContent) {
					String [] content = line.split("\\|");
					Customer customer = new Customer(content[2], content[1], content[3], Integer.parseInt(content[0]));
					String[] cLine = line.split(":");
					if (cLine != null && cLine.length > 1) {
						String[] creditCardLine = cLine[1].split("\\|");
						customer.addCreditCard(Integer.parseInt(creditCardLine[0]), creditCardLine[1]);
					}
					theater.customerList.add(customer);
				}
			}
		} catch (Exception e) {
			System.err.println("Unable to find file");
		}
		
		try {
			showListContent = Files.readAllLines(Paths.get(homeDirectory + fileSeparator +theaterDirectory+ fileSeparator +"shows.txt"),Charset.defaultCharset());
			if(showListContent != null && showListContent.size() > 0) {
				for(String line : showListContent) {
					String [] content = line.split("\\|");
					Show show = new Show(content[0], Integer.parseInt(content[1]), df.parse(content[2]), df.parse(content[3]));
					theater.playList.add(show);
				}
			}
		} catch (Exception e) {
			System.err.println("Unable to find file");
		}
		
		return theater;
    }//end loadFromDisk
    
}//end Theater
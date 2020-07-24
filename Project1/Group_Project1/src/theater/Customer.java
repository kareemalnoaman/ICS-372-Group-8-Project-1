package theater;
import java.util.ArrayList;

public class Customer {

    String customerName;
    String customerAddress;
    String customerPhone;
    int customerID;
    ArrayList<CreditCard> creditCardList;
    ArrayList<Ticket> ticketList;

    public Customer(String customerName, String customerAddress, String customerPhone, int customerID) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.creditCardList = new ArrayList<CreditCard>();
        this.customerID = customerID;
    }

    public void addCreditCard(String creditCardNumber, String expiryDate){
        creditCardList.add(new CreditCard(creditCardNumber, expiryDate));
    }

    @Override
    public String toString() {

        String creditCardList = "";

        for(int i = 0; i < this.creditCardList.size(); i++){
            creditCardList = creditCardList + this.creditCardList.get(i);
        }

        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerPhone=" + customerPhone +
                ", creditCardList=" + creditCardList +
                ", customerID=" + customerID +
                '}';
    }

}//end customer

class CreditCard {

    String creditCardNumber;
    String expiryDate;

    CreditCard(String creditCardNumber, String expiryDate){
        this.creditCardNumber = creditCardNumber;
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber=" + creditCardNumber +
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }

}//end CreditCard
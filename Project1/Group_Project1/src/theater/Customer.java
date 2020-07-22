package theater;
import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {

    String customerName;
    String customerAddress;
    String customerPhone;
    int customerID;
    ArrayList<CreditCard> creditCardList;

    public Customer(String customerName, String customerAddress, String customerPhone, int customerID) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.creditCardList = new ArrayList<CreditCard>();
        this.customerID = customerID;
    }

    public void addCreditCard(int creditCardNumber, String expiryDate){
        CreditCard card = new CreditCard(creditCardNumber, expiryDate);
        creditCardList.add(card);
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

    int creditCardNumber;
    String expiryDate;

    CreditCard(int creditCardNumber, String expiryDate){
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
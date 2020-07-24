package theater;

import java.util.Date;

public class Ticket {

    private double price;
    private int serialNum;
    private Date showDate;


    public Ticket(double price, int serialNum, Date showDate) {
        this.price = price;
        this.serialNum = serialNum;
        this.showDate = showDate;
    }

}

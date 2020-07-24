package theater;

import java.util.Date;

public class Ticket {

    private double price;
    private int serialNum;
    private Date showDate;

    public Ticket(double price, Date showDate, int serialNum) {
        this.price = price;
        this.showDate = showDate;
        this.serialNum = serialNum;
    }

    public Date getShowDate() {
        return showDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "price=" + price +
                ", serialNum=" + serialNum +
                ", showDate=" + showDate +
                '}';
    }

}

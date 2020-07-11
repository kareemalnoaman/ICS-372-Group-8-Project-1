package theater;

public class Client {

    private String clientName;
    private String clientAddress;
    private String clientPhone;
    private int clientID;
    private double balanceDue;

    public Client(int clientID, String clientName,
                  String clientAddress, String clientPhone, double balanceDue) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientPhone = clientPhone;
        this.balanceDue = balanceDue;
    }

    public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public double getBalanceDue() {
		return balanceDue;
	}

	public void setBalanceDue(double balanceDue) {
		this.balanceDue = balanceDue;
	}

	@Override
    public String toString() {
        return "Client{" +
                "clientName='" + clientName + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientPhone=" + clientPhone +
                ", clientID=" + clientID +
                ", balanceDue=" + balanceDue +
                
                '}';
    }

}
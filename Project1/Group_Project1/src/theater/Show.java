package theater;

import java.util.Date;

public class Show {
	
	private int clientID;
	private String playName;
	private Date playStartDate;
	private Date playEndDate;
	 
	 public Show(String playName, int clientId, Date startDate, Date endDate) {
		 this.playName = playName;
		 this.clientID = clientId;
		 playStartDate = startDate;
	     playEndDate = endDate;
	 }
	 
	public int getClientID() {
		return clientID;
	}
	
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	 
	public String getPlayName() {
		return playName;
	}

	public void setPlayName(String playName) {
		this.playName = playName;
	}

	public Date getPlayStartDate() {
		return playStartDate;
	}

	public void setPlayStartDate(Date playStartDate) {
		this.playStartDate = playStartDate;
	}

	public Date getPlayEndDate() {
		return playEndDate;
	}

	public void setPlayEndDate(Date playEndDate) {
		this.playEndDate = playEndDate;
	}

	@Override
	public String toString() {
		return "\n Client Id = \t" + clientID + ", Show Name =\t" + playName + ", Show StartDate =\t" + playStartDate
				+ ", Show End Date = \t" + playEndDate;
	}

}

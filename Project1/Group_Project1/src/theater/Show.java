package theater;

import java.util.Date;

public class Show {
	
	private int clientId;
	private String playName;
	private Date playStartDate;
	private Date playEndDate;
	 
	 public Show(String playName, int clientId, Date startDate, Date endDate) {
		 this.playName = playName;
		 this.clientId = clientId;
		 playStartDate = startDate;
	     playEndDate = endDate;
	 }
	 
	public int getClientId() {
		return clientId;
	}
	
	public void setClientId(int clientId) {
		this.clientId = clientId;
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

	public boolean playExists(){

	 	Date todayDate = new Date();

	 	if(playStartDate ==  null)
	 		return false;

	 	if(todayDate.before(playStartDate) || todayDate.after(playEndDate))
	 		return false;
	 	else
	 		return true;

	 }

	@Override
	public String toString() {
		return "\n Client Id = \t" + clientId + ", Show Name =\t" + playName + ", Show StartDate =\t" + playStartDate
				+ ", Show End Date = \t" + playEndDate;
	}

}

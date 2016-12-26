package by.bsu.extask.to;

import java.sql.Date;

public class RequestData {
	public UserData user;
	public RoomData room;
	public Date date_in;
	public Date date_out;
	public double total_price;
	
	public RequestData(UserData user, RoomData room, Date date_in, Date date_out, double total_price) {
		this.user = user;
		this.room = room;
		this.date_in = date_in;
		this.date_out = date_out;
		this.total_price = total_price;
	}

	
}

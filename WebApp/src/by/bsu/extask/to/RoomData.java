package by.bsu.extask.to;


public class RoomData {
	public int number_room;
	public int max_person;
	public double pricepernight;
	public String type;
		
	public RoomData(int number_room, int max_person, double pricepernight, String type) {
		this.number_room = number_room;
		this.max_person = max_person;
		this.type = type;
		this.pricepernight = pricepernight;
	}	
	
}

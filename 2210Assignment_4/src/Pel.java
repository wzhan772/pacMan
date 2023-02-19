/**
 * @author William Zhang - 251215208
 * this class represents the data items to be stored in the nodes of the binary search tree
 * this class has two instance variables
 */
public class Pel {
	Location loc;
	int Ocolor;
	//set location and color
	public Pel (Location p, int colour) {
		loc = p;
		Ocolor = colour;
	}
	//get location
	public Location getLocus() {
		return loc;
	}
	//get color
	public int getColor() {
		return Ocolor;
	}
}
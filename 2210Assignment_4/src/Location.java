/**
 * @author William Zhang - 251215208
 * this class represents the location (x,y) of a Pel
 */
public class Location {
	int objx;
	int objy;
	//set location
	public Location(int x, int y) {
		objx = x;
		objy = y;
	}
	//get x
	public int getx() {
		return objx;
	}
	//get y
	public int gety() {
		return objy;
	}
	//compare locations
	public int compareTo(Location p) {
		int value = 0;
		if (this.gety() > p.gety() || (this.gety() == p.gety() && this.getx() > p.getx())) {
			value = 1;
		} else if (this.getx() == p.getx() && this.gety() == p.gety()) {
			value = 0;
		} else if (this.gety() < p.gety() || (this.gety() == p.gety() && this.getx() < p.getx())) {
			value = -1;
		}
		return value;
	}

}
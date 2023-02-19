/**
 * @Author William Zhang - 251215208
 * The purpose of this class is to deal with the objects and positions of the mechanics in the game
 */

public class MyObject implements MyObjectADT {
	private int id;
	private int width;
	private int height;
	private String type;
	private Location pos;
	private BinarySearchTree BST;
	
public MyObject (int id, int width, int height, String type, Location pos) {
	this.id = id;
	this.width = width;
	this.height = height;
	this.type = type;
	this.pos = pos;
	this.BST = new BinarySearchTree();
}


//setter for the type
public void setType(String type) {
	this.type = type;
}


//getter for the width
public int getWidth() {
	return this.width;
}


//getter for the height
public int getHeight() {
	return this.height;
}
	

//getter for the type 
public String getType() {
	return this.type;
}


//getter for the ID
public int getId() {
	return this.id;
}


//getter for the location
public Location getLocus() {
	return this.pos;
}


//setter for the location
public void setLocus(Location value) {
	this.pos = value;
}


//inserts the Pel value into binary search tree associated with 'this' myObject
public void addPel(Pel pix) throws DuplicatedKeyException {
	DuplicatedKeyException dupKey = new DuplicatedKeyException("this key already exists.");
	try {
		this.BST.put(BST.getRoot(), pix);
	} catch(DuplicatedKeyException e) {
		throw dupKey;
	}
}

//returns true if 'this' myObject intersects the one specified in the parameter
//returns false otherwise
public boolean intersects (MyObject otherObject) {
	//boolean to check to see if object has intersected or not
	boolean check = true;
	//if else to check to see if object has intersected or not
	if(pos.gety() > otherObject.getLocus().gety() + otherObject.getHeight() || (pos.gety() + height < otherObject.getLocus().gety())) {
    	check = false;    
    }
    else {
    	//if else to check to see if object has intersected or not
        if(pos.getx() > otherObject.getLocus().getx() + otherObject.getWidth() || (pos.getx() + width < otherObject.getLocus().getx())) {
        	check = false;
        }
        else {
        	check = true;
        }
    }   
    if(check == true) {
    	//try catch to catch exceptions
        try { 
        	EmptyTreeException emptyTreeException = new EmptyTreeException("The tree is currently empty");
        for(Pel pos = BST.smallest(BST.getRoot()); pos != null; pos = BST.successor(BST.getRoot(), pos.getLocus())) {
        	//create new location object 
        	Location l = new Location(pos.getLocus().getx() + this.pos.getx() - otherObject.getLocus().getx(), pos.getLocus().gety() + this.pos.gety() - otherObject.getLocus().gety());
        	if(otherObject.BST.get(otherObject.BST.getRoot(),l) != null){
        	                return true;
        	            }
        	}
        } catch (EmptyTreeException e) {
        }
    }
    return false;
        
}
}
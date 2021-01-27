package de.softwaretechnik.model;

public class Node implements Comparable<Node>{
		
	private int _id;
	private String _name;
	private double _distance;
	
	public Node (int id, String name, double distance) {
		this._id = id;
		this._name = name;
		this._distance = distance;
	}
	
	public String toString() {
		return _name;			
	}
	
	public int getId() {
		return _id;
	}
	
	public double getDistance() {
		return _distance;
	}
	
	public void setDistance(double distance) {
		this._distance = distance;
	}

	public int compareTo(Node other) {
	    return this._name.compareTo(other._name);
	}
	
}

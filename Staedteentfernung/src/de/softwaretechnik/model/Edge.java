package de.softwaretechnik.model;


public class Edge implements Comparable<Edge>{
	private int _startPoint;
	private int _endPoint;
	private double _distance;

	public Edge(int startPoint, int endPoint, double distance) {
		this._startPoint = startPoint;
		this._endPoint = endPoint;
		this._distance = distance;
	}

	public int getStartPoint() {
		return _startPoint;
	}

	public int getEndPoint() {
		return _endPoint;
	}

	public double getDistance() {
		return _distance;
	}

	public boolean equals(Edge other) {
		if (this._startPoint == other._startPoint) {
			if (this._endPoint == other._endPoint) {
				return true;
			}
		}
		return false;
	}
   
	public int compareTo(Edge other) {
		return Double.compare(this._distance, other._distance);
	}

	public String toString() {
		return _startPoint + "-" + _endPoint + " (" + _distance + ")";
	}
}


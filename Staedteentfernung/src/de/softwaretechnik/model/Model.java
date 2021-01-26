package de.softwaretechnik.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class Model {
	
	private ArrayList<Node> _listNode;
	private ArrayList<Edge> _listEdge;
	
	private String _queryNode = "SELECT stadt_id, name FROM stadt ";
	private String _queryEdge = "SELECT von_id, nach_id, entfernung FROM entfernung";
	
	// set Default Algo
    private CalculationAlgo _calculationAlgo = new Dijkstra();
    
    
	public Model() {
		_listNode = new ArrayList<Node>();
		_listEdge = new ArrayList<Edge>();
		
	}
	
    public void setCalculationAlgo(CalculationAlgo calculationAlgo) {
    	this._calculationAlgo = calculationAlgo;
    }
	
	public double[] berechneAlgo(Graph g, int startVertex) {
		return _calculationAlgo.calculateDistance(g, startVertex);
	}
	
	public ArrayList<Node> getNodeList() {
		loadNode();
		Collections.sort(_listNode);
		return _listNode;
	}
	
	public ArrayList<Edge> getEdgeList() {
		loadEdge();
		return _listEdge;	
	}
	
	private void loadNode() {
		if (_listNode != null) {
			while(_listNode.size()>0) {
				_listNode.remove(0);
			}
		}
		try {	
		ResultSet rs = Datenbank.getInstance().getDBAusgabe(_queryNode);
			while (rs.next()) {
				_listNode.add(new Node(rs.getInt(1), rs.getString(2), 0.0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void loadEdge() {
		if (_listEdge != null) {
			while(_listEdge.size()>0) {
				_listEdge.remove(0);
			}
		}
		try {	
		ResultSet rs = Datenbank.getInstance().getDBAusgabe(_queryEdge);
			while (rs.next()) {
				_listEdge.add(new Edge(rs.getInt(1), rs.getInt(2), rs.getDouble(3)));
				_listEdge.add(new Edge(rs.getInt(2), rs.getInt(1), rs.getDouble(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}

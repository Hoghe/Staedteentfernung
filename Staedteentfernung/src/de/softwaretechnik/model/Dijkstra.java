
package de.softwaretechnik.model;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * The class represents the Dijkstra algorithm 
 * 
 * @author Dirk Hoghe
 * @version 1.0.1
 */



public class Dijkstra implements CalculationAlgo{
	
	/**
	 * find the shortest Path
	 * 
	 * @param g Graph is represented as an adjunct list
	 * @param startVertex the id from the start
	 * @return array with the distance from the start
	 *         to all other node
	 */
	
	public double[] calculateDistance(Graph g, int startVertex) {
		// for storing distances after removing vertex from Queue
		double[] distances = new double[g.getvCount()];
		// for storing father id's after removing vertex from Queue
		int[] predecessors = new int[g.getvCount()];
		for (int i = 0; i < g.getvCount(); i++) {
			predecessors[i] = -1;
	    }

	    // set up vertex queue
	    LinkedList<Vertex> L = new LinkedList<Vertex>();
	    for (int i = 0; i < g.getvCount(); i++) {
	    	if (i != startVertex) {
	    		L.add(new Vertex(i));
	        }
	    }

	    // add startVertex
	    Vertex node = new Vertex(startVertex);
	    node.setDistance(0);
	    L.add(0, node);	       
	       
	    // loop through all vertices
	    while (!L.isEmpty()) {
	    	// get vertex with shortest distance
	    	Vertex u = L.removeFirst();
	        distances[u.getId()] = u.getDistance();
	           
	        // iterate through all neighbours
	        Iterator<Edge> it = g.neighbours(u.getId()).iterator();
	        while (it.hasNext()) {
	        	Edge e = it.next();
	            Iterator<Vertex> it2 = L.iterator();
	            while (it2.hasNext()) {
	            	Vertex v = it2.next();
	                // check if vertex was visited already
	                if (e.getEndPoint() != v.getId()) {
	                	continue;
	                }
	                   
	                // check distance 
	                if (v.getDistance() > u.getDistance() + e.getDistance()) {
	                    v.setDistance(u.getDistance() + e.getDistance());
	                    v.setParent(u);
	                    predecessors[v.getId()] = v.getParent().getId();       
	                }
	            }
	        }
	        
	        // Sorts the list of Vertex by distance
	        Collections.sort(L);
	    }
	    
        /*
		* return the array with the distance
	    * from the start to all other node
        */
	    
	    return distances;
	}
	  
	public class Vertex implements Comparable<Vertex>{
		private int _id;
		private double _distance;
		private Vertex _parent;
		   
		public Vertex(){
			_distance = Double.MAX_VALUE; // "infinity"
		    _parent = null;  
		}
		   
		public Vertex(int id){
			this._id = id;
			_distance = Double.MAX_VALUE; // "infinity"
		    _parent = null;  
		}

		public String toString() {
			return "Vertex [id=" + _id + ", distance=" + _distance + "]";
		}

		public int getId() {
			return _id;
		}

		public void setId(int id) {
			this._id = id;
		}

		public double getDistance() {
			return _distance;
		}
		   
		public void setDistance(double distance) {
			this._distance = distance;
		}
		   
		public Vertex getParent() {
			return _parent;
		}
		   
		public void setParent(Vertex parent){
			this._parent = parent;
		}
		   
		public int compareTo(Vertex other) {
			return Double.compare(this._distance, other._distance);
		}
	}
}


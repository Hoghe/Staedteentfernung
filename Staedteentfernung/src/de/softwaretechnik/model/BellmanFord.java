package de.softwaretechnik.model;

import java.util.Iterator;

/**
 * The class represents the BellmanFord algorithm 
 * 
 * @author Dirk Hoghe
 * @version 1.0.1
 */

public class BellmanFord implements CalculationAlgo{
	
	public double[] calculateDistance(Graph g, int startVertex) {
		
		System.out.println("BellmanFord");
		
		// for storing distances
		double[] distances = new double[g.getvCount()];
		// for storing predecessors
		int[] predecessors = new int[g.getvCount()];
	 
		// initialize arrays
		for (int i = 0; i < distances.length; i++) {
			distances[i] = Double.MAX_VALUE; // "infinity"
			predecessors[i] = -1;
	    }
		distances[startVertex] = 0;
	 
		// relax all edges v - 1 times repeatedly
		for (int i = 1; i < g.getvCount() - 1; i++) {
			for (int j = 0; j < g.getvCount() - 1; j++) {
				Iterator<Edge> it = g.neighbours(j).iterator();
				while (it.hasNext()) {
					Edge e = it.next(); // edge (u, v)
					// if dist(u) + w(u, v) < dist(v) then
					// dist(v) = dist(u) + w(u, v)
					// pred(v) = u
					if (distances[e.getStartPoint()] + e.getDistance() < distances[e.getEndPoint()]) {
						distances[e.getEndPoint()] = distances[e.getStartPoint()] + e.getDistance();
						predecessors[e.getEndPoint()] = e.getStartPoint();
					}
				}
			}
		}
	 
		// check for negative-weight circles
		for (int i = 0; i < g.getvCount() - 1; i++) {
			Iterator<Edge> it = g.neighbours(i).iterator();
			while (it.hasNext()) {
				Edge e = it.next(); // edge (u, v)
				// if dist(u) + w(u, v) < dist(v) then
				// graph contains negative-weight circle
				if (distances[e.getStartPoint()] + e.getDistance() < distances[e.getEndPoint()]) {
					System.out.println("Graph contains negative-weight circle!");
					distances = new double[1];
					distances[0] = -1;
					return distances;
				}
			}
		}     
		return distances;
	}
}


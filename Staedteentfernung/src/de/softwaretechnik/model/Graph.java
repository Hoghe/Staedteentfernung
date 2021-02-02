package de.softwaretechnik.model;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.LinkedList;

/**
 * The class Graph merges the edge into a graph
 * 
 * @author Dirk Hoghe
 * @version 1.0.0
 */

public class Graph {
    private int _vCount;
    private LinkedList<Edge>[] _adj;


    @SuppressWarnings("unchecked")
	public Graph(int vCount) {
        this._vCount = vCount;
        // initialize adj
        _adj = new LinkedList[vCount];
        for (int i = 0; i < vCount; i++) {
            _adj[i] = new LinkedList<Edge>();
        }
    }
    
    public int getvCount() {
        return _vCount;
    }
 
    public void addEdge(int i, int j, double distance) {
        _adj[i].add(new Edge(i, j, distance));
    }
 
    public void removeEdge(int i, int j) {
        Iterator<Edge> it = _adj[i].iterator();
        Edge other = new Edge(i, j, 0);
        while (it.hasNext()) {
            if (it.next().equals(other)) {
                it.remove();
                return;
            }
        }
    }
 
    public boolean hasEdge(Edge e) {
        return _adj[e.getStartPoint()].contains(e);
    }
 
    public LinkedList<Edge> neighbours(int vertex) {   	
        return _adj[vertex];
    }
 
    public void printGraph() {
        for (int i = 0; i < _vCount; i++) {
            LinkedList<Edge> edges = neighbours(i);           
            Iterator<Edge> it = edges.iterator();
            System.out.print(i + ": ");
            for (int j = 0; j < edges.size(); j++) {
                System.out.print(it.next() + " ");
            }
            System.out.println();
        }
    }
}

package de.softwaretechnik.control;

import de.softwaretechnik.model.*;
import de.softwaretechnik.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class Controller extends WindowAdapter implements ActionListener{
	
	private Model _model;
	private ControlView _controlView;
	private LinkedList<Views> _views;
	private CloseListener aL_Close = new CloseListener();
	

	  public Controller(Model model, ControlView controlView) {	
		this._model = model;
		this._controlView = controlView;
		
		controlView.addButtonCloseListener(aL_Close);
		controlView.addButtonCalculateListener(this);
		controlView.setNodes(model.getNodeList());
		
		_views = new LinkedList<Views>();
	}
	
	public void addView(Views v) {
		_views.add(v);
	}

	public void delView(Views v) {
		int i = _views.indexOf(v);
		if (i >= 0) {
			_views.remove(i);
		}
	}
	
	public void actionPerformed(ActionEvent e) {

		int startNode = ((Node)_controlView.getStartPoint()).getId();

		Graph g = new Graph(_model.getNodeList().size());

		ArrayList<Edge> listEdge = _model.getEdgeList();
		for(Edge element : listEdge){ 
			g.addEdge(element.getStartPoint(), element.getEndPoint(), element.getDistance());;
			}
		
		double[] distances= _model.berechneAlgo(g, startNode);
		ArrayList<Node> listNodes = _model.getNodeList();
		for (int i=0; i<distances.length; i++) {
			for(Node element : listNodes) {
				if(element.getId() == i) {
					element.setDistance(distances[i]);
				}
			}
					
		}
        Iterator<Views> it = _views.iterator();
        while (it.hasNext()) {
        	Views v = it.next();
        	v.setDistance(((Node)_controlView.getEndPoint()), listNodes);
        }
	}
	
	public class CloseListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	}

}

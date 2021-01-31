package de.softwaretechnik.control;

import de.softwaretechnik.model.*;
import de.softwaretechnik.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;


public class Controller extends WindowAdapter implements ActionListener{
	
	private Model _model;
	private ControlView _controlView;
	private LinkedList<Views> _views;
	private AlgoListener aL_Dijkstra = new AlgoListener(new Dijkstra());
	private AlgoListener aL_BellmanFord = new AlgoListener(new BellmanFord());
	private CheckBoxListener cBL_Ziel = new CheckBoxListener(new ViewZiel());
	private CheckBoxListener cBL_Alle = new CheckBoxListener(new ViewAlle());	
	private CloseListener aL_Close = new CloseListener();
	

	  public Controller(Model model, ControlView controlView) {	
		this._model = model;
		this._controlView = controlView;
		
		_controlView.addRadioButtonDijkstraListener(aL_Dijkstra);
		_controlView.addRadioButtonBellmanFordListener(aL_BellmanFord);
		_controlView.addCheckBoxZielListener(cBL_Ziel);
		_controlView.addCheckBoxAllesListener(cBL_Alle);
		_controlView.addButtonCloseListener(aL_Close);
		_controlView.addButtonCalculateListener(this);
		_controlView.setNodes(model.getNodeList());
		
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
	
	public class AlgoListener implements ActionListener{
		
		private CalculationAlgo _calculationAlgo;
		
		public AlgoListener(CalculationAlgo calculationAlgo) {
			_calculationAlgo = calculationAlgo;
		}

		public void actionPerformed(ActionEvent e) {
			_model.setCalculationAlgo(_calculationAlgo);		
			
		}
	}
	
	public class CheckBoxListener implements ActionListener{
		
		private Views _view;
		
		public CheckBoxListener(Views view) {
			_view = view;
		}

	    public void actionPerformed(ActionEvent e) {
	        JCheckBox cb = (JCheckBox) e.getSource();
	        if (cb.isSelected()) {
	            addView(_view);
	            ((JFrame)_view).setVisible(true);
	        } else {
	            delView(_view);
	            ((JFrame)_view).setVisible(false);
	        }
	    }
	}

	
	public class CloseListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	}

}

package de.softwaretechnik.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.*;

import de.softwaretechnik.model.Node;

public class ViewAlle extends JFrame implements Views {

	private int _width = 300;
	private int _height = 470;
	
	private DefaultListModel<String> _model = new DefaultListModel<>();
	private JList<String> li_endPoints = new JList<>(_model);
	
	
	public ViewAlle() {
		setSize(_width, _height);
		setTitle("Softwaretechnik View-Alle Version 1.0.0");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		li_endPoints.setEnabled(false);
		li_endPoints.setForeground(Color.BLACK);
        
    add(li_endPoints);

	}


	public void setDistance(Node endPoint, ArrayList<Node> listNodes) {
		_model.clear();
		//sort listNode with the Distance
		listNodes.sort(Comparator.comparing(Node::getDistance));
		for(Node element : listNodes) {
			_model.addElement(element.toString()+" - "+ element.getDistance() + " km");
		}
	}

}

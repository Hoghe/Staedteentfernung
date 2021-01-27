package de.softwaretechnik.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import de.softwaretechnik.model.*;


public class ControlView extends JFrame {

	private int _width = 300;
	private int _height = 300;
	private JLabel lb_start = new JLabel("Start", JLabel.CENTER);
	private JComboBox<Node> cb_startPoint = new JComboBox<Node>();
	private JLabel lb_end = new JLabel("Ziel", JLabel.CENTER);
	private JComboBox<Node> cb_endPoint = new JComboBox<Node>();
	private JButton bt_calculate = new JButton("Berechnen");
	private JButton bt_exit = new JButton("Beenden");
	
	public ControlView() {
	  
		setSize(_width, _height);
		setTitle("Softwaretechnik ControlView Version 1.1.1");
		setLayout(new GridLayout(8,1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.add(bt_calculate);
		panel1.add(bt_exit);

		add(lb_start);
    add(cb_startPoint);
    add(lb_end);
    add(cb_endPoint);   
    add(panel1);

	}
	
	public Node getStartPoint() {
		return ((Node)cb_startPoint.getSelectedItem());
	}
	
	public Node getEndPoint() {
		return ((Node)cb_endPoint.getSelectedItem());
	}
	
	public void setNodes(ArrayList<Node> listStartPoints) {
		for(Node element : listStartPoints){ 
			cb_startPoint.addItem(element);
			cb_endPoint.addItem(element);
		}
	}
	
	public void addButtonCalculateListener(ActionListener listener) {
		bt_calculate.addActionListener(listener);
	}
	
	public void addButtonCloseListener(ActionListener listener) {
		bt_exit.addActionListener(listener);
	}
		
}

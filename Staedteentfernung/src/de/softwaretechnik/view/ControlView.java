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
	private ButtonGroup bg_Algos = new ButtonGroup();
	private JRadioButton rb_dijkstra = new JRadioButton("Dijkstra", true);
	private JRadioButton rb_BellmanFord = new JRadioButton("BellmanFord", false);
	private JCheckBox chb_viewZiel = new JCheckBox("Ziel", false);
	private JCheckBox chb_viewAlles = new JCheckBox("Alle", false);
	private JButton bt_calculate = new JButton("Berechnen");
	private JButton bt_exit = new JButton("Beenden");
	
	public ControlView() {
	  
		setSize(_width, _height);
		setTitle("Softwaretechnik ControlView Version 1.2.0");
		setLayout(new GridLayout(8,1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bg_Algos.add(rb_dijkstra);
		bg_Algos.add(rb_BellmanFord);
		
		JPanel panelAlgos = new JPanel();
		panelAlgos.setLayout(new FlowLayout());
		panelAlgos.add(rb_dijkstra);
		panelAlgos.add(rb_BellmanFord);
		
		JPanel panelViews = new JPanel();
		panelViews.setLayout(new FlowLayout());
		panelViews.add(chb_viewZiel);
		panelViews.add(chb_viewAlles);
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new FlowLayout());
		panelButton.add(bt_calculate);
		panelButton.add(bt_exit);

		add(lb_start);
		add(cb_startPoint);
		add(lb_end);
		add(cb_endPoint);
		add(panelAlgos);
		add(panelViews);
		add(panelButton);

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
	
	public void addRadioButtonDijkstraListener(ActionListener listener) {
		rb_dijkstra.addActionListener(listener);
	}
	
	public void addRadioButtonBellmanFordListener(ActionListener listener) {
		rb_BellmanFord.addActionListener(listener);
	}
	
	public void addCheckBoxZielListener(ActionListener listener) {
		chb_viewZiel.addActionListener(listener);
	}
	
	public void addCheckBoxAllesListener(ActionListener listener) {
		chb_viewAlles.addActionListener(listener);
	}
	
	public void addButtonCalculateListener(ActionListener listener) {
		bt_calculate.addActionListener(listener);
	}
	
	public void addButtonCloseListener(ActionListener listener) {
		bt_exit.addActionListener(listener);
	}
		
}

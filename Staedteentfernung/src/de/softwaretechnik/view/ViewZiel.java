package de.softwaretechnik.view;

import java.util.ArrayList;
import javax.swing.*;

import de.softwaretechnik.model.Node;

public class ViewZiel extends JFrame implements Views {
	
	private int _width = 300;
	private int _height = 100;
	private JLabel lb_distance = new JLabel( "Entfernung zum Ziel:", JLabel.CENTER );
	private JTextField  tf_distance = new JTextField(6);
	private JPanel _panel = new JPanel();
	
	public ViewZiel() {
		setSize( _width, _height );
		setTitle( "Softwaretechnik View-Ziel Version 1.0.0" );
		setLayout( new java.awt.FlowLayout() );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		
    _panel.add( lb_distance );
    _panel.add( tf_distance );
        
    add( _panel );
	} // Konstruktor

  public void setDistance( Node endPoint, ArrayList<Node> listNodes ) {
    for( Node element : listNodes ) {
	 	  if( element.getId() == endPoint.getId() ) {
		 	  tf_distance.setText( element.getDistance() + " km" );
		  }
	  }
  } // setDistance

} // ViewZiel

/**
 * Hochschule Harz 
 * Fachbereich Automatisierung und Informatik 
 * 
 * LV "Softwaretechnik" WiSe 2020/21
 * 
 * Staedteentfernung
 *  
 * "Berechne die kürzeste Entfernung"
 * 
 * 
 * @author Mike Rohloff u34825 und Dirk Hoghe u34823
 * @version 1.1.1
 * 
 */


package de.softwaretechnik.program;

import de.softwaretechnik.model.*;
import de.softwaretechnik.view.*;
import de.softwaretechnik.control.*;


public class Program {

	public static void main(String[] args) {
	       
	       Model model = new Model();
	       ControlView controlView = new ControlView();
	       Controller controller = new Controller(model, controlView);
	       
//	       model.setCalculationAlgo(new BellmanFord());
//	       model.setCalculationAlgo(new Dijkstra());
	       
	       ViewZiel viewZ = new ViewZiel();	
	       controller.addView(viewZ);
	       viewZ.setVisible(true);
	       
	       ViewAlle viewA = new ViewAlle();	
	       controller.addView(viewA);
	       viewA.setVisible(true);
	       
	       controlView.setVisible(true);
	       
	}

}

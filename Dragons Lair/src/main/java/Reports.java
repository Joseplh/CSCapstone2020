package main.java;
import java.awt.Color;

import javax.swing.JPanel;

public class Reports extends JPanel implements Tile {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller control;

	public Reports(Controller control) {
		this.control = control;
		setBackground(Color.LIGHT_GRAY);
		
		/* Report Data */
		String data[][] = control.getReports();

		/* Report Table Column Names */
		String column[] = { };
		
	}
}

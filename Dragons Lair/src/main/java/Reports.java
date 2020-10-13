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
		
		/* Reports Data */
		String data[][] = control.getReports();

		/* Reports Table Column Names */
		String column[] = { };
	}
}

package main.java;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Tools extends JPanel implements Tile {
	/**
	 * @author Joseph Maxwell
	 * Compilation table of all basic tools to interact with the database directly
	 * INSERT/MODIFY/DELETE
	 */
	private static final long serialVersionUID = 1L;
	Controller control;
	public Tools(Controller control) {
		this.control = control;
		toolBackground();//defines the bounds to the tool panel
		/* Button deceleration */
		JButton addTitleBtn = new JButton("Add New Title");
		addTitleBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addTitleBtn.setBounds(10, 11, 180, 41);
		add(addTitleBtn);
	}
	private void toolBackground() {
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		
		JPanel toolPanel = new JPanel();
		toolPanel.setBackground(Color.LIGHT_GRAY);
		toolPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Title Details", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		toolPanel.setBounds(505, 117, 459, 168);
		add(toolPanel);
		toolPanel.setLayout(null);
	}
}
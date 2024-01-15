package mvc;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import shapes.Shape;

public class View extends JPanel {
	
	private Model model;

	public void setModel(Model model) {
		this.model = model;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (model != null) {
			Iterator<Shape> it = model.getAll().iterator();
			while (it.hasNext()) {
				it.next().draw(g);
			}
		}
	}
}

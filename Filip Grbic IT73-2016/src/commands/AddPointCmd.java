package commands;

import mvc.Model;
import shapes.Point;

public class AddPointCmd implements Command {
	
	private Model model;
	private Point point;
	
	public AddPointCmd (Model model, Point point) {
		this.model = model;
		this.point = point;
	}

	@Override
	public void execute() {
		model.addShape(point);
	}

	@Override
	public void unexecute() {
		model.deleteShape(point);
	}
}

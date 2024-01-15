package commands;

import mvc.Model;
import shapes.Point;

public class DeletePointCmd implements Command {
	
	private Model model;
	private Point point;
	
	public DeletePointCmd(Model model, Point point) {
		this.model = model;
		this.point = point;
	}

	@Override
	public void execute() {
		model.deleteShape(point);
	}

	@Override
	public void unexecute() {
		model.addShape(point);
	}
}

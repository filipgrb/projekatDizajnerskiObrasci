package commands;

import mvc.Model;
import shapes.Circle;

public class DeleteCircleCmd implements Command {

	private Model model;
	private Circle circle;
	
	public DeleteCircleCmd(Model model, Circle circle) {
		this.model = model;
		this.circle = circle;
	}
	
	@Override
	public void execute() {
		model.deleteShape(circle);
	}

	@Override
	public void unexecute() {
		model.addShape(circle);
	}
}

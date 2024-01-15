package commands;

import mvc.Model;
import shapes.Circle;

public class AddCircleCmd implements Command {

	private Model model;
	private Circle circle;
	
	public AddCircleCmd(Model model, Circle circle) {
		this.model = model;
		this.circle = circle;
	}
	
	@Override
	public void execute() {
		model.addShape(circle);
	}

	@Override
	public void unexecute() {
		model.deleteShape(circle);
	}
}

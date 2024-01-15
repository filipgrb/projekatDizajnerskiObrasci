package commands;

import mvc.Model;
import shapes.CircleWithHole;

public class AddCircleWithHoleCmd implements Command {

	private Model model;
	private CircleWithHole circleWithHole;
	
	public AddCircleWithHoleCmd(Model model, CircleWithHole circleWithHole) {
		this.model = model;
		this.circleWithHole = circleWithHole;
	}
	
	@Override
	public void execute() {
		model.addShape(circleWithHole);
	}

	@Override
	public void unexecute() {
		model.deleteShape(circleWithHole);
	}
}

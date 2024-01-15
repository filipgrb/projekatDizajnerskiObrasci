package commands;

import mvc.Model;
import shapes.CircleWithHole;

public class DeleteCircleWithHoleCmd implements Command {

	private Model model;
	private CircleWithHole circleWithHole;
	
	public DeleteCircleWithHoleCmd(Model model, CircleWithHole circleWithHole) {
		this.model = model;
		this.circleWithHole = circleWithHole;
	}
	
	@Override
	public void execute() {
		model.deleteShape(circleWithHole);
	}

	@Override
	public void unexecute() {
		model.addShape(circleWithHole);
	}
}

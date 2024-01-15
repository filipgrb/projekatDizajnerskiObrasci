package commands;

import mvc.Model;
import shapes.Rectangle;

public class DeleteRectangleCmd implements Command {
	
	private Model model;
	private Rectangle rectangle;
	
	public DeleteRectangleCmd(Model model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;
	}

	@Override
	public void execute() {
		model.deleteShape(rectangle);
	}

	@Override
	public void unexecute() {
		model.addShape(rectangle);
	}
}

package commands;

import mvc.Model;
import shapes.Rectangle;

public class AddRectangleCmd implements Command {
	
	private Model model;
	private Rectangle rectangle;
	
	public AddRectangleCmd(Model model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;
	}

	@Override
	public void execute() {
		model.addShape(rectangle);
	}

	@Override
	public void unexecute() {
		model.deleteShape(rectangle);
	}
}

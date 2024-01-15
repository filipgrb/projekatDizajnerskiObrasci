package commands;

import mvc.Model;
import shapes.Line;

public class AddLineCmd implements Command {
	
	private Model model;
	private Line line;
	
	public AddLineCmd(Model model, Line line) {
		this.model = model;
		this.line = line;
	}

	@Override
	public void execute() {
		model.addShape(line);
	}

	@Override
	public void unexecute() {
		model.deleteShape(line);
	}
}

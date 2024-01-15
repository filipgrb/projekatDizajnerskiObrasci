package commands;

import mvc.Model;
import shapes.Line;

public class DeleteLineCmd implements Command {

	private Model model;
	private Line line;
	
	public DeleteLineCmd(Model model, Line line) {
		this.model = model;
		this.line = line;
	}
	
	@Override
	public void execute() {
		model.deleteShape(line);
	}

	@Override
	public void unexecute() {
		model.addShape(line);
	}
}

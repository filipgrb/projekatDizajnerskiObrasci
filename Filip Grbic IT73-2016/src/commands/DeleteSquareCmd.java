package commands;

import mvc.Model;
import shapes.Square;

public class DeleteSquareCmd implements Command {
	
	private Model model;
	private Square square;
	
	public DeleteSquareCmd(Model model, Square square) {
		this.model = model;
		this.square = square;
	}

	@Override
	public void execute() {
		model.deleteShape(square);
	}

	@Override
	public void unexecute() {
		model.addShape(square);
	}
}

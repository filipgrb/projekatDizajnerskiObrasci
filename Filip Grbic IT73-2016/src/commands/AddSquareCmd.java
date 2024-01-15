package commands;

import mvc.Model;
import shapes.Square;

public class AddSquareCmd implements Command {
	
	private Model model;
	private Square square;
	
	public AddSquareCmd(Model model, Square square) {
		this.model = model;
		this.square = square;
	}

	@Override
	public void execute() {
		model.addShape(square);
	}

	@Override
	public void unexecute() {
		model.deleteShape(square);
	}
}

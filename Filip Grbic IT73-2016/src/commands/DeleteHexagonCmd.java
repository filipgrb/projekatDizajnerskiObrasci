package commands;

import adapter.HexagonAdapter;
import mvc.Model;

public class DeleteHexagonCmd implements Command {
	
	private Model model;
	private HexagonAdapter hexagon;
	
	public DeleteHexagonCmd(Model model, HexagonAdapter hexagon) {
		this.model = model;
		this.hexagon = hexagon;
	}

	@Override
	public void execute() {
		model.deleteShape(hexagon);
	}

	@Override
	public void unexecute() {
		model.addShape(hexagon);
	}
}

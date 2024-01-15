package commands;

import adapter.HexagonAdapter;
import mvc.Model;

public class AddHexagonCmd implements Command {
	
	private Model model;
	private HexagonAdapter hexagon;
	
	public AddHexagonCmd(Model model, HexagonAdapter hexagon) {
		this.model = model;
		this.hexagon = hexagon;
	}

	@Override
	public void execute() {
		model.addShape(hexagon);
	}

	@Override
	public void unexecute() {
		model.deleteShape(hexagon);
	}
}

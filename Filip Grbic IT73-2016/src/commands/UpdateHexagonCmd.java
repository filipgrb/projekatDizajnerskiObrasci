package commands;

import adapter.HexagonAdapter;

public class UpdateHexagonCmd implements Command {
	
	private HexagonAdapter firstState, currentState, newState;
	
	public UpdateHexagonCmd(HexagonAdapter firstState, HexagonAdapter newState) {
		this.firstState = firstState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		currentState = (HexagonAdapter) firstState.clone();
		firstState.setInnerColor(newState.getInnerColor());
		firstState.setColor(newState.getColor());
		firstState.getHexagon().setX(newState.getHexagon().getX());
		firstState.getHexagon().setY(newState.getHexagon().getY());
		firstState.getHexagon().setR(newState.getHexagon().getR());
	}

	@Override
	public void unexecute() {
		firstState.setInnerColor(currentState.getInnerColor());
		firstState.setColor(currentState.getColor());
		firstState.getHexagon().setX(currentState.getHexagon().getX());
		firstState.getHexagon().setY(currentState.getHexagon().getY());
		firstState.getHexagon().setR(currentState.getHexagon().getR());
	}
}

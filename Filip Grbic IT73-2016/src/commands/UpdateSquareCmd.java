package commands;

import shapes.Square;

public class UpdateSquareCmd implements Command {

	private Square firstState, currentState, newState;

	public UpdateSquareCmd(Square firstState, Square newState) {
		this.firstState = firstState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		currentState = firstState.clone();
		firstState.setPointUpLeft(newState.getPointUpLeft());
		firstState.setSideLength(newState.getSideLength());
		firstState.setColor(newState.getColor());
		firstState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		firstState.setPointUpLeft(currentState.getPointUpLeft());
		firstState.setSideLength(currentState.getSideLength());
		firstState.setColor(currentState.getColor());
		firstState.setInnerColor(currentState.getInnerColor());
	}
}

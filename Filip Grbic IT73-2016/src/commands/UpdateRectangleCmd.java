package commands;

import shapes.Rectangle;

public class UpdateRectangleCmd implements Command {

	private Rectangle firstState, currentState, newState;

	public UpdateRectangleCmd(Rectangle firstState, Rectangle newState) {
		this.firstState = firstState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		currentState = firstState.clone();
		firstState.setPointUpLeft(newState.getPointUpLeft());
		firstState.setWidth(newState.getWidth());
		firstState.setSideLength(newState.getSideLength());
		firstState.setColor(newState.getColor());
		firstState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		firstState.setPointUpLeft(currentState.getPointUpLeft());
		firstState.setWidth(currentState.getWidth());
		firstState.setSideLength(currentState.getSideLength());
		firstState.setColor(currentState.getColor());
		firstState.setInnerColor(currentState.getInnerColor());
	}
}

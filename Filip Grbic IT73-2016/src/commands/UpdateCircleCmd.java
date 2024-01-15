package commands;

import shapes.Circle;

public class UpdateCircleCmd implements Command {
	
	private Circle firstState, currentState, newState;

	public UpdateCircleCmd(Circle firstState, Circle newState) {
		this.firstState = firstState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		currentState = firstState.clone();
		firstState.setCenter(newState.getCenter());
		firstState.setColor(newState.getColor());
		firstState.setInnerColor(newState.getInnerColor());
		firstState.setR(newState.getR());
	}

	@Override
	public void unexecute() {
		firstState.setCenter(currentState.getCenter());
		firstState.setColor(currentState.getColor());
		firstState.setInnerColor(currentState.getInnerColor());
		firstState.setR(currentState.getR());
	}
}

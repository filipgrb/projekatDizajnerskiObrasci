package commands;

import shapes.CircleWithHole;

public class UpdateCircleWithHoleCmd implements Command {
	
	private CircleWithHole firstState, currentState, newState;
	
	public UpdateCircleWithHoleCmd(CircleWithHole firstState, CircleWithHole newState) {
		this.firstState = firstState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		currentState = firstState.clone();
		firstState.setCenter(newState.getCenter());
		firstState.setR(newState.getR());
		firstState.setHoleRadius(newState.getHoleRadius());
		firstState.setColor(newState.getColor());
		firstState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		firstState.setCenter(currentState.getCenter());
		firstState.setR(currentState.getR());
		firstState.setHoleRadius(currentState.getHoleRadius());
		firstState.setColor(currentState.getColor());
		firstState.setInnerColor(currentState.getInnerColor());
	}
}

package commands;

import shapes.Point;

public class UpdatePointCmd implements Command {
	
	private Point firstState, currentState, newState;
	
	public UpdatePointCmd(Point firstState, Point newState) {
		this.firstState = firstState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		currentState = firstState.clone();
		firstState.moveTo(newState.getX(), newState.getY());
		firstState.setColor(newState.getColor());
		
	}

	@Override
	public void unexecute() {
		firstState.moveTo(currentState.getX(), currentState.getY());
		firstState.setColor(currentState.getColor());
	}
}

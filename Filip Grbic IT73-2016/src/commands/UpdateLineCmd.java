package commands;

import shapes.Line;

public class UpdateLineCmd implements Command {
	
	private Line firstState, currentState, newState;
	
	public UpdateLineCmd(Line firstState, Line newState) {
		this.firstState = firstState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		currentState = firstState.clone();
		firstState.setStart(newState.getStart());
		firstState.setEnd(newState.getEnd());
		firstState.setColor(newState.getColor());
	}

	@Override
	public void unexecute() {
		firstState.setStart(currentState.getStart());
		firstState.setEnd(currentState.getEnd());
		firstState.setColor(currentState.getColor());
	}
}

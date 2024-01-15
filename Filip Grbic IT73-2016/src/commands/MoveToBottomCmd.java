package commands;

import java.util.ArrayList;

import shapes.Shape;

public class MoveToBottomCmd implements Command {

	private ArrayList<Shape> list;
	private Shape shape;
	private int position;
	
	public MoveToBottomCmd(ArrayList<Shape> list, Shape shape) {
		this.list = list;
		this.shape = shape;
		this.position = list.indexOf(shape);
	}

	@Override
	public void execute() {
		shape = list.remove(position);
		list.add(0,shape);
	}

	@Override
	public void unexecute() {
		shape = list.remove(0);
		list.add(position,shape);
	}
}

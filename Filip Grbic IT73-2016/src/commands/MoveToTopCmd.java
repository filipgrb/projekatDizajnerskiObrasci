package commands;

import java.util.ArrayList;

import shapes.Shape;

public class MoveToTopCmd implements Command {
	
	private ArrayList<Shape> list;
	private Shape shape;
	private int position;
	
	public MoveToTopCmd(ArrayList<Shape> list, Shape shape) {
		this.list = list;
		this.shape = shape;
		this.position = list.indexOf(shape);
	}

	@Override
	public void execute() {
		shape = list.remove(position);
		list.add(list.size(), shape);
	}

	@Override
	public void unexecute() {
		shape = list.remove(list.size() - 1);
		list.add(position, shape);
	}
}

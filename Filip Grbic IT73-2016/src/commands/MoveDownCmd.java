package commands;

import java.util.ArrayList;

import shapes.Shape;

public class MoveDownCmd implements Command {
	
	private ArrayList<Shape> list;
	private Shape shape;
	
	public MoveDownCmd(ArrayList<Shape> list, Shape shape) {
		this.list = list;
		this.shape = shape;
	}

	@Override
	public void execute() {
		int position = list.indexOf(shape);

		if (position > 0) {
			Shape s = list.remove(position);
			list.add(position - 1, s);
		}
	}

	@Override
	public void unexecute() {
		int position = list.indexOf(shape);

		if (position < list.size() - 1) {
			Shape s = list.remove(position);
			list.add(position + 1, s);
		}
	}
}

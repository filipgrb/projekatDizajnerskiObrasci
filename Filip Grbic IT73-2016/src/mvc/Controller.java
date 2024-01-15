package mvc;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

import adapter.HexagonAdapter;
import commands.AddCircleCmd;
import commands.AddCircleWithHoleCmd;
import commands.AddHexagonCmd;
import commands.AddLineCmd;
import commands.AddPointCmd;
import commands.AddRectangleCmd;
import commands.AddSquareCmd;
import commands.Command;
import commands.DeleteCircleCmd;
import commands.DeleteCircleWithHoleCmd;
import commands.DeleteHexagonCmd;
import commands.DeleteLineCmd;
import commands.DeletePointCmd;
import commands.DeleteRectangleCmd;
import commands.DeleteSquareCmd;
import commands.DeselectShapeCmd;
import commands.MoveDownCmd;
import commands.MoveToBottomCmd;
import commands.MoveToTopCmd;
import commands.MoveUpCmd;
import commands.SelectShapeCmd;
import commands.UpdateCircleCmd;
import commands.UpdateCircleWithHoleCmd;
import commands.UpdateHexagonCmd;
import commands.UpdateLineCmd;
import commands.UpdatePointCmd;
import commands.UpdateRectangleCmd;
import commands.UpdateSquareCmd;
import dialogs.AddCircleDialog;
import dialogs.AddCircleWithHoleDialog;
import dialogs.AddHexagonDialog;
import dialogs.AddRectangleDialog;
import dialogs.AddSquareDialog;
import dialogs.ModifyCircleDialog;
import dialogs.ModifyCircleWithHoleDialog;
import dialogs.ModifyHexagonDialog;
import dialogs.ModifyLineDialog;
import dialogs.ModifyPointDialog;
import dialogs.ModifyRectangleDialog;
import dialogs.ModifySquareDialog;
import observer.ButtonEnabler;
import shapes.Circle;
import shapes.CircleWithHole;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;
import strategy.DrawingHandler;
import strategy.FileSaver;
import strategy.LogSaver;

public class Controller {

	private Model model;
	private Frame frame;
	
	private ButtonEnabler buttonEnabler;
	private Stack<Command> undoStack, redoStack;
	
	private int x, y, redoLogCounter, redoLogUpperBound;
	private boolean firstClick;
	private Point tempPoint;
	
	private ArrayList<String> listOfRedoCommands;
	
	private LogSaver logSaver;
	private DrawingHandler drawingHandler;
	
	public Controller(Model model, Frame frame) {
		this.model = model;
		this.frame = frame;
		
		buttonEnabler = new ButtonEnabler(frame.getRightFrame());
		model.addObserver(buttonEnabler);
		firstClick = false;
		
		undoStack = new Stack<Command>();
		redoStack = new Stack<Command>();
		
		logSaver = new LogSaver(this.frame.getLogFrame());
		drawingHandler = new DrawingHandler(this.model, this, this.frame);
	}
	
	public void mouseClicked(MouseEvent e) {
		model.notifyObservers();

		x = e.getX();
		y = e.getY();

		if (frame.getTopFrame().returnSelectedButton() == "point")
			createPoint(x, y);

		if (frame.getTopFrame().returnSelectedButton() == "line")
			createLine(x, y);

		if (frame.getTopFrame().returnSelectedButton() == "square")
			createSquare(x, y);

		if (frame.getTopFrame().returnSelectedButton() == "rectangle")
			createRect(x, y);

		if (frame.getTopFrame().returnSelectedButton() == "circle")
			createCircle(x, y);

		if (frame.getTopFrame().returnSelectedButton() == "circleWithHole") 
			createCircleWithHole(x, y);
		
		if (frame.getTopFrame().returnSelectedButton() == "hexagon")
			createHex(x, y);

		if (frame.getTopFrame().returnSelectedButton() == "select")
			selectShape(x, y);

		if (frame.getTopFrame().returnSelectedButton() == "multiple")
			multipleSelect(x, y);

		frame.getRightFrame().stackButtonEnabler(undoStack.size(), redoStack.size());
		frame.repaint();
	}
	
	public void createPoint(int x, int y) {
		firstClick = false;
		Point p = new Point(x, y, frame.getRightFrame().getColorButton().getBackground());
		AddPointCmd cmd = new AddPointCmd(model, p);
		cmd.execute();
		undoStack.push(cmd);
		frame.getLogFrame().getModel().addElement("Add," + p.toString());
	}
	
	public void createLine(int x, int y) {
		if (!firstClick) {
			tempPoint = new Point(x, y);
			firstClick = true;
		} else {
			Line l = new Line(tempPoint, new Point(x, y), frame.getRightFrame().getColorButton().getBackground());
			AddLineCmd cmd = new AddLineCmd(model, l);
			cmd.execute();
			undoStack.push(cmd);
			frame.getLogFrame().getModel().addElement("Add," + l.toString());
			firstClick = false;
		}
	}
	
	public void createCircle(int x, int y) {
		firstClick = false;

		AddCircleDialog dialog = new AddCircleDialog(x, y, frame);
		dialog.setVisible(true);

		if (dialog.isAdded()) {
			Circle circle = dialog.getCircle();
			AddCircleCmd cmd = new AddCircleCmd(model, circle);
			cmd.execute();
			undoStack.push(cmd);
			frame.getLogFrame().getModel().addElement("Add," + circle.toString());
		}
	}
	
	public void createCircleWithHole(int x, int y) {
		firstClick = false;

		AddCircleWithHoleDialog dialog = new AddCircleWithHoleDialog(x, y, frame);
		dialog.setVisible(true);

		if (dialog.isAdded()) {
			CircleWithHole circleWithHole = dialog.getCircleWithHole();
			AddCircleWithHoleCmd cmd = new AddCircleWithHoleCmd(model, circleWithHole);
			cmd.execute();
			undoStack.push(cmd);
			frame.getLogFrame().getModel().addElement("Add," + circleWithHole.toString());
		}
	}
	
	public void createSquare(int x, int y) {
		firstClick = false;

		AddSquareDialog dialog = new AddSquareDialog(x, y, frame);
		dialog.setVisible(true);

		if (dialog.isAdded()) {
			Square square = dialog.getSquare();
			AddSquareCmd cmd = new AddSquareCmd(model, square);
			cmd.execute();
			undoStack.push(cmd);
			frame.getLogFrame().getModel().addElement("Add," + square.toString());
		}
	}
	
	public void createRect(int x, int y) {
		firstClick = false;

		AddRectangleDialog dialog = new AddRectangleDialog(x, y, frame);
		dialog.setVisible(true);

		if (dialog.isAdded()) {
			Rectangle rectangle = dialog.getRectangle();
			AddRectangleCmd cmd = new AddRectangleCmd(model, rectangle);
			cmd.execute();
			undoStack.push(cmd);
			frame.getLogFrame().getModel().addElement("Add," + rectangle.toString());
		}
	}
	
	public void createHex(int x, int y) {
		firstClick = false;

		AddHexagonDialog dialog = new AddHexagonDialog(x, y, frame);
		dialog.setVisible(true);

		if (dialog.isAdded()) {
			HexagonAdapter hexagon = dialog.getHexagon();
			AddHexagonCmd cmd = new AddHexagonCmd(model, hexagon);
			cmd.execute();
			undoStack.push(cmd);
			frame.getLogFrame().getModel().addElement("Add," + hexagon.toString());
		}
	}
	
	public void selectShape(int x, int y) {
		firstClick = false;

		ArrayList<Shape> list = model.getAll();
		for (Shape s : list) {
			if (s.isSelected()) {
				DeselectShapeCmd cmdD = new DeselectShapeCmd(s);
				cmdD.execute();
				undoStack.push(cmdD);

				s.setSelected(false);
				frame.getLogFrame().getModel().addElement("Deselect," + s.toString());
			}
		}
		for (int i = model.getAll().size() - 1; i >= 0; i--) {
			Shape s = model.getShape(i);
			if (s.contains(x, y)) {
				SelectShapeCmd cmdS = new SelectShapeCmd(s);
				cmdS.execute();
				undoStack.push(cmdS);

				s.setSelected(true);
				frame.getLogFrame().getModel().addElement("Select," + s.toString());

				break;
			}
		}
		model.notifyObservers();
	}
	
	public void multipleSelect(int x, int y) {
		firstClick = false;

		for (int i = model.getAll().size() - 1; i >= 0; i--) {
			Shape s = model.getShape(i);
			if (s.contains(x, y)) {
				SelectShapeCmd cmd = new SelectShapeCmd(s);
				cmd.execute();
				undoStack.push(cmd);
				
				frame.getLogFrame().getModel().addElement("Select," + s.toString());
				s.setSelected(true);
				
				break;
			}
		}
		model.notifyObservers();
	}
	
	public void deleteShapes() {
		for (int i = 0; i < model.getAll().size(); i++) {
			if (model.getShape(i).isSelected()) {

				Shape s = model.getShape(i);

				if (s instanceof Rectangle) {
					Rectangle rectangle = (Rectangle) s;
					DeleteRectangleCmd cmd = new DeleteRectangleCmd(this.model, rectangle);
					cmd.execute();
					undoStack.push(cmd);
					frame.getLogFrame().getModel().addElement("Delete," + rectangle.toString());
					
				}  else if (s instanceof CircleWithHole) {
					CircleWithHole circleWithHole = (CircleWithHole) s;
					DeleteCircleWithHoleCmd cmd = new DeleteCircleWithHoleCmd(this.model, circleWithHole);
					cmd.execute();
					undoStack.push(cmd);
					frame.getLogFrame().getModel().addElement("Delete," + circleWithHole.toString());

				} else if (s instanceof Point) {
					Point point = (Point) s;
					DeletePointCmd cmd = new DeletePointCmd(this.model, point);
					cmd.execute();
					undoStack.push(cmd);
					frame.getLogFrame().getModel().addElement("Delete," + point.toString());

				} else if (s instanceof Line) {
					Line line = (Line) s;
					DeleteLineCmd cmd = new DeleteLineCmd(this.model, line);
					cmd.execute();
					undoStack.push(cmd);
					frame.getLogFrame().getModel().addElement("Delete," + line.toString());

				} else if (s instanceof Square) {
					Square square = (Square) s;
					DeleteSquareCmd cmd = new DeleteSquareCmd(this.model, square);
					cmd.execute();
					undoStack.push(cmd);
					frame.getLogFrame().getModel().addElement("Delete," + square.toString());
					
				} else if (s instanceof Circle) {
					Circle circle = (Circle) s;
					DeleteCircleCmd cmd = new DeleteCircleCmd(this.model, circle);
					cmd.execute();
					undoStack.push(cmd);
					frame.getLogFrame().getModel().addElement("Delete," + circle.toString());
					
				} else if (s instanceof HexagonAdapter) {
					HexagonAdapter hexagon = (HexagonAdapter) s;
					DeleteHexagonCmd cmd = new DeleteHexagonCmd(this.model, hexagon);
					cmd.execute();
					undoStack.push(cmd);
					frame.getLogFrame().getModel().addElement("Delete," + hexagon.toString());
				}
				i--;
			}
		}
		frame.getRightFrame().stackButtonEnabler(undoStack.size(), redoStack.size());
		frame.repaint();
	}
	
	public void modifyShape() {
		for (int i = 0; i < model.getAll().size(); i++) {
			if (model.getShape(i).isSelected()) {

				Shape tempShape = model.getShape(i);

				if (tempShape instanceof Rectangle) {
					Rectangle rectangle = (Rectangle) tempShape;
					Rectangle copy = rectangle.clone();
					Rectangle first = rectangle.clone();

					ModifyRectangleDialog dialogMR = new ModifyRectangleDialog(copy);
					dialogMR.setVisible(true);

					UpdateRectangleCmd cmd = new UpdateRectangleCmd(rectangle, copy);
					cmd.execute();
					frame.getLogFrame().getModel().addElement("Modify," + first.toString() + "," + rectangle.toString());
					undoStack.push(cmd);

				} else if (tempShape instanceof CircleWithHole) {
					CircleWithHole circleWithHole = (CircleWithHole) tempShape;
					CircleWithHole copy = circleWithHole.clone();
					CircleWithHole first = circleWithHole.clone();
					
					ModifyCircleWithHoleDialog dialogMCC = new ModifyCircleWithHoleDialog(copy);
					dialogMCC.setVisible(true);
					
					UpdateCircleWithHoleCmd cmd = new UpdateCircleWithHoleCmd(circleWithHole, copy);
					cmd.execute();
					frame.getLogFrame().getModel().addElement("Modify," + first.toString()+"," + circleWithHole.toString());
					undoStack.push(cmd);

				} else if (tempShape instanceof Circle) {
					Circle circle = (Circle) tempShape;
					Circle copy = circle.clone();
					Circle first = circle.clone();

					ModifyCircleDialog dialogMC = new ModifyCircleDialog(copy);
					dialogMC.setVisible(true);

					UpdateCircleCmd cmd = new UpdateCircleCmd(circle, copy);
					cmd.execute();
					frame.getLogFrame().getModel().addElement("Modify," + first.toString() + "," + circle.toString());
					undoStack.push(cmd);

				} else if (tempShape instanceof Square) {
					Square square = (Square) tempShape;
					Square copy = square.clone();
					Square first = square.clone();

					ModifySquareDialog dialogMS = new ModifySquareDialog(copy);
					dialogMS.setVisible(true);

					UpdateSquareCmd cmd = new UpdateSquareCmd(square, copy);
					cmd.execute();
					frame.getLogFrame().getModel().addElement("Modify," + first.toString() + "," + square.toString());
					undoStack.push(cmd);
					
				} else if (tempShape instanceof Point) {
					Point point = (Point) tempShape;
					Point copy = point.clone();
					Point first = point.clone();

					ModifyPointDialog dialogMP = new ModifyPointDialog(copy);
					dialogMP.setVisible(true);

					UpdatePointCmd cmd = new UpdatePointCmd(point, copy);
					cmd.execute();
					frame.getLogFrame().getModel().addElement("Modify," + first.toString() + "," + point.toString());
					undoStack.push(cmd);

				} else if (tempShape instanceof Line) {
					Line line = (Line) tempShape;
					Line copy = line.clone();
					Line first = line.clone();

					ModifyLineDialog dialogML = new ModifyLineDialog(copy);
					dialogML.setVisible(true);

					UpdateLineCmd cmd = new UpdateLineCmd(line, copy);
					cmd.execute();
					frame.getLogFrame().getModel().addElement("Modify," + first.toString() + "," + line.toString());
					undoStack.push(cmd);

				} else if (tempShape instanceof HexagonAdapter) {
					HexagonAdapter hexagon = (HexagonAdapter) tempShape;
					HexagonAdapter copy = hexagon.clone();
					HexagonAdapter first = hexagon.clone();

					ModifyHexagonDialog dialogMH = new ModifyHexagonDialog(copy);
					dialogMH.setVisible(true);

					UpdateHexagonCmd cmd = new UpdateHexagonCmd(hexagon, copy);
					cmd.execute();
					frame.getLogFrame().getModel().addElement("Modify," + first.toString() + "," + hexagon.toString());
					undoStack.push(cmd);
				}
			}
		}
		frame.getRightFrame().stackButtonEnabler(undoStack.size(), redoStack.size());
		frame.repaint();
	}
	
	public void undoClicked() {
		if (!undoStack.empty()) {
			//if provera u slucaju ako kliknemo undo a selektovano je vise oblika kod brisanja
			if(undoStack.get(undoStack.size() - 1).toString().contains("Delete")) {
				for(int i = undoStack.size() - 1; i >= 0; i--) {
					if(undoStack.get(i).toString().contains("Delete")) {
						Command cmd = undoStack.pop();
						cmd.unexecute();
						redoStack.push(cmd);
						continue;
					}
					break;
				}
			} else {
				Command cmd = undoStack.pop();
				cmd.unexecute();
				redoStack.push(cmd);
			}
		}

		frame.getLogFrame().getModel().addElement("Undo");
		frame.getRightFrame().stackButtonEnabler(undoStack.size(), redoStack.size());
		frame.repaint();
	}
	
	public void redoClicked() {
		if (!redoStack.empty()) {
			//isti razlog if provere kao i kod undo metode
			if(redoStack.get(redoStack.size() - 1).toString().contains("Delete")) {
				for(int i = redoStack.size() - 1; i >= 0; i--) {
					if(redoStack.get(i).toString().contains("Delete")) {
						Command cmd = redoStack.pop();
						cmd.execute();
						undoStack.push(cmd);
						continue;
					}
					break;
				}
			} else {
				Command cmd = redoStack.pop();
				cmd.execute();
				undoStack.push(cmd);
			}
		}
		
		frame.getLogFrame().getModel().addElement("Redo");
		frame.getRightFrame().stackButtonEnabler(undoStack.size(), redoStack.size());
		frame.repaint();
	}
	
	public void moveUp() {
		for (Shape s : model.getAll()) {
			if (s.isSelected()) {
				MoveUpCmd cmd = new MoveUpCmd(model.getAll(), s);
				cmd.execute();
				undoStack.push(cmd);
				frame.getLogFrame().getModel().addElement("MoveUp," + s.toString());
				break;
			}
		}
		frame.repaint();
	}
	
	public void moveDown() {
		for (Shape s : model.getAll()) {
			if (s.isSelected()) {
				MoveDownCmd cmd = new MoveDownCmd(model.getAll(), s);
				cmd.execute();
				undoStack.push(cmd);
				frame.getLogFrame().getModel().addElement("MoveDown," + s.toString());
				break;
			}
		}
		frame.repaint();
	}
	
	public void moveTop() {
		for (Shape s : model.getAll()) {
			if (s.isSelected()) {
				MoveToTopCmd cmd = new MoveToTopCmd(model.getAll(), s);
				cmd.execute();
				undoStack.push(cmd);
				frame.getLogFrame().getModel().addElement("MoveTop," + s.toString());
				break;
			}
		}
		frame.repaint();
	}

	public void moveBottom() {
		for (Shape s : model.getAll()) {
			if (s.isSelected()) {
				MoveToBottomCmd cmd = new MoveToBottomCmd(model.getAll(), s);
				cmd.execute();
				undoStack.push(cmd);
				frame.getLogFrame().getModel().addElement("MoveBottom," + s.toString());
				break;
			}
		}
		frame.repaint();
	}
	
	public void saveSerialization() {
		FileSaver is = new FileSaver(model);
		is.save();
	}
	
	public void loadSerialization() {
		FileSaver fs = new FileSaver(this.model);
		fs.read("");
		this.frame.repaint();
	}

	public void saveLog() {
		LogSaver ls = new LogSaver(frame.getLogFrame());
		ls.save();
	}

	public void loadLog() {
		listOfRedoCommands = drawingHandler.readFile();
		redoLogUpperBound = listOfRedoCommands.size();
		redoLogCounter = 0;
		System.out.println("Loaded Log File!!!");
		this.frame.repaint();
		this.frame.getRightFrame().getRedoLogButton().setEnabled(true);
	}

	public void redoLog() {
		this.drawingHandler.read(listOfRedoCommands.get(redoLogCounter));
		redoLogCounter++;

		if (redoLogCounter == redoLogUpperBound)
			this.frame.getRightFrame().getRedoLogButton().setEnabled(false);

		this.frame.repaint();
	}

	public Stack<Command> getUndoStack() {
		return undoStack;
	}
}

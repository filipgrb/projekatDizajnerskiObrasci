package strategy;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import adapter.HexagonAdapter;
import commands.AddCircleCmd;
import commands.AddCircleWithHoleCmd;
import commands.AddHexagonCmd;
import commands.AddLineCmd;
import commands.AddPointCmd;
import commands.AddRectangleCmd;
import commands.AddSquareCmd;
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
import hexagon.Hexagon;
import mvc.Controller;
import mvc.Frame;
import mvc.Model;
import shapes.Circle;
import shapes.CircleWithHole;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;

public class DrawingHandler implements FileHandler{

	private FileOutputStream fileOutputStream;
	private Model model;
	private Controller controller;
	private Frame frame;
	private ArrayList<String> commandStringList = new ArrayList<String>();

	public DrawingHandler(Model model, Controller controller, Frame frame) {
		this.model = model;
		this.controller = controller;
		this.frame = frame;
	}

	@Override
	public void save() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select a name and a folder");
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Serial files", "txt", "text");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showDialog(null, "Save");
		if (returnValue == JFileChooser.APPROVE_OPTION) {

			String path = jfc.getSelectedFile().getPath();
			System.out.println("Saving serializable file in: " + path + "...");

			File f = new File(path + ".ser");

			try {
				fileOutputStream = new FileOutputStream(f);
				ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
				out.writeObject(model.getAll());
				out.close();
				fileOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<String> readFile() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt", "text");
		jfc.setFileFilter(filter);
		
		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println("Reading from:" + selectedFile.getAbsolutePath() + "...");
			

			try (BufferedReader br = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()))) {

				String line = br.readLine();

				while (line != null) {
					commandStringList.add(line);
					line = br.readLine();
				}

			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<String>();
			}
		}

		return commandStringList;
	}

	@Override
	public void read(String line) {
		try {
			String[] command = line.split(",");

			switch (command[0]) {
			case "Add":
				if (command[1].equalsIgnoreCase("Point")) {

					Point p = new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
							new Color(Integer.parseInt(command[4])));
					AddPointCmd cmdP = new AddPointCmd(this.model, p);
					cmdP.execute();
					controller.getUndoStack().push(cmdP);
					frame.getLogFrame().getModel().addElement("Add," + p.toString());
				}
				if (command[1].equalsIgnoreCase("Square")) {

					Square s = new Square(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					AddSquareCmd cmdS = new AddSquareCmd(this.model, s);
					cmdS.execute();
					controller.getUndoStack().push(cmdS);
					frame.getLogFrame().getModel().addElement("Add," + s.toString());
				}
				if (command[1].equalsIgnoreCase("Line")) {
					
					Line l = new Line(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							new Point(Integer.parseInt(command[4]), Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					AddLineCmd cmdL = new AddLineCmd(this.model, l);
					cmdL.execute();
					controller.getUndoStack().push(cmdL);
					frame.getLogFrame().getModel().addElement("Add," + l.toString());
				}
				if (command[1].equalsIgnoreCase("Circle")) {

					Circle c = new Circle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					AddCircleCmd cmdC = new AddCircleCmd(this.model, c);
					cmdC.execute();
					controller.getUndoStack().push(cmdC);
					frame.getLogFrame().getModel().addElement("Add," + c.toString());
				}
				if (command[1].equalsIgnoreCase("CircleWithHole")) {

					CircleWithHole cwc = new CircleWithHole(
							new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					AddCircleWithHoleCmd cmdCC = new AddCircleWithHoleCmd(this.model, cwc);
					cmdCC.execute();
					controller.getUndoStack().push(cmdCC);
					frame.getLogFrame().getModel().addElement("Add," + cwc.toString());
				}
				if (command[1].equalsIgnoreCase("Rectangle")) {

					Rectangle r = new Rectangle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					AddRectangleCmd cmdR = new AddRectangleCmd(this.model, r);
					cmdR.execute();
					controller.getUndoStack().push(cmdR);
					frame.getLogFrame().getModel().addElement("Add," + r.toString());
				}
				if (command[1].equalsIgnoreCase("Hexagon")) {

					HexagonAdapter h = new HexagonAdapter(
							new Hexagon(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
									Integer.parseInt(command[4])),
							new Color(Integer.parseInt(command[5])), new Color(Integer.parseInt(command[6])));

					AddHexagonCmd cmd = new AddHexagonCmd(this.model, h);
					cmd.execute();
					controller.getUndoStack().push(cmd);
					frame.getLogFrame().getModel().addElement("Add," + h.toString());

				}

				break;

			case "Undo":
				controller.undoClicked();
				break;

			case "Redo":
				controller.redoClicked();
				break;

			case "Delete":

				int index;

				if (command[1].equalsIgnoreCase("Point")) {
					Point s = new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
							new Color(Integer.parseInt(command[4])));
					index = model.getAll().indexOf(s);
					DeletePointCmd cmdP = new DeletePointCmd(model, (Point) model.getShape(index));
					cmdP.execute();
					controller.getUndoStack().push(cmdP);
					frame.getLogFrame().getModel().addElement("Delete," + s.toString());

				} else if (command[1].equalsIgnoreCase("Line")) {

					Line s = new Line(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							new Point(Integer.parseInt(command[4]), Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index = model.getAll().indexOf(s);
					DeleteLineCmd cmd = new DeleteLineCmd(model, (Line) model.getShape(index));
					cmd.execute();
					controller.getUndoStack().push(cmd);
					frame.getLogFrame().getModel().addElement("Delete," + s.toString());

				} else if (command[1].equalsIgnoreCase("Square")) {

					Square s = new Square(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index = model.getAll().indexOf(s);
					DeleteSquareCmd cmd = new DeleteSquareCmd(model, (Square) model.getShape(index));
					cmd.execute();
					controller.getUndoStack().push(cmd);
					frame.getLogFrame().getModel().addElement("Delete," + s.toString());

				} else if (command[1].equalsIgnoreCase("Circle")) {

					Circle s = new Circle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index = model.getAll().indexOf(s);
					DeleteCircleCmd cmd = new DeleteCircleCmd(model, (Circle) model.getShape(index));
					cmd.execute();
					controller.getUndoStack().push(cmd);
					frame.getLogFrame().getModel().addElement("Delete," + s.toString());

				} else if (command[1].equalsIgnoreCase("CircleWithHole")) {

					CircleWithHole s = new CircleWithHole(
							new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					index = model.getAll().indexOf(s);
					DeleteCircleWithHoleCmd cmd = new DeleteCircleWithHoleCmd(model, (CircleWithHole) model.getShape(index));
					cmd.execute();
					controller.getUndoStack().push(cmd);
					frame.getLogFrame().getModel().addElement("Delete," + s.toString());

				} else if (command[1].equalsIgnoreCase("Rectangle")) {

					Rectangle s = new Rectangle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					index = model.getAll().indexOf(s);
					DeleteRectangleCmd cmd = new DeleteRectangleCmd(model, (Rectangle) model.getShape(index));
					cmd.execute();
					controller.getUndoStack().push(cmd);
					frame.getLogFrame().getModel().addElement("Delete," + s.toString());

				} else if (command[1].equalsIgnoreCase("Hexagon")) {

					HexagonAdapter s = new HexagonAdapter(
							new Hexagon(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
									Integer.parseInt(command[4])),
							new Color(Integer.parseInt(command[5])), new Color(Integer.parseInt(command[6])));
					index = model.getAll().indexOf(s);
					DeleteHexagonCmd cmdH = new DeleteHexagonCmd(model, (HexagonAdapter) model.getShape(index));
					cmdH.execute();
					controller.getUndoStack().push(cmdH);
					frame.getLogFrame().getModel().addElement("Delete," + s.toString());
				}

				break;

			case "Modify":

				int indexx;

				if (command[1].equalsIgnoreCase("Point")) {

					Point ss = new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
							new Color(Integer.parseInt(command[4])));
					indexx = model.getAll().indexOf(ss);
					Point pp = new Point(Integer.parseInt(command[6]), Integer.parseInt(command[7]),
							new Color(Integer.parseInt(command[8])));
					UpdatePointCmd cmdP = new UpdatePointCmd((Point) model.getShape(indexx), pp);
					cmdP.execute();
					controller.getUndoStack().push(cmdP);
					frame.getLogFrame().getModel().addElement("Modify," + ss.toString() + "," + pp.toString());

				} else if (command[1].equalsIgnoreCase("Line")) {

					Line ss = new Line(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							new Point(Integer.parseInt(command[4]), Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					indexx = model.getAll().indexOf(ss);
					Line pp = new Line(new Point(Integer.parseInt(command[8]), Integer.parseInt(command[9])),
							new Point(Integer.parseInt(command[10]), Integer.parseInt(command[11])),
							new Color(Integer.parseInt(command[12])));
					UpdateLineCmd cmd = new UpdateLineCmd((Line) model.getShape(indexx), pp);
					cmd.execute();
					controller.getUndoStack().push(cmd);
					frame.getLogFrame().getModel().addElement("Modify," + ss.toString() + pp.toString());

				} else if (command[1].equalsIgnoreCase("Square")) {

					Square ss = new Square(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					indexx = model.getAll().indexOf(ss);
					Square pp = new Square(new Point(Integer.parseInt(command[8]), Integer.parseInt(command[9])),
							Integer.parseInt(command[10]), new Color(Integer.parseInt(command[11])),
							new Color(Integer.parseInt(command[12])));
					UpdateSquareCmd cmd = new UpdateSquareCmd((Square) model.getShape(indexx), pp);
					cmd.execute();
					controller.getUndoStack().push(cmd);
					frame.getLogFrame().getModel().addElement("Modify," + ss.toString() + pp.toString());

				} else if (command[1].equalsIgnoreCase("Circle")) {

					Circle ss = new Circle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					indexx = model.getAll().indexOf(ss);
					Circle pp = new Circle(new Point(Integer.parseInt(command[8]), Integer.parseInt(command[9])),
							Integer.parseInt(command[10]), new Color(Integer.parseInt(command[11])),
							new Color(Integer.parseInt(command[12])));
					UpdateCircleCmd cmd = new UpdateCircleCmd((Circle) model.getShape(indexx), pp);
					cmd.execute();
					controller.getUndoStack().push(cmd);
					frame.getLogFrame().getModel().addElement("Modify," + ss.toString() + pp.toString());

				} else if (command[1].equalsIgnoreCase("CircleWithHole")) {

					CircleWithHole ss = new CircleWithHole(
							new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					indexx = model.getAll().indexOf(ss);
					CircleWithHole pp = new CircleWithHole(
							new Point(Integer.parseInt(command[9]), Integer.parseInt(command[10])),
							Integer.parseInt(command[11]), Integer.parseInt(command[12]),
							new Color(Integer.parseInt(command[13])), new Color(Integer.parseInt(command[14])));
					UpdateCircleWithHoleCmd cmd = new UpdateCircleWithHoleCmd((CircleWithHole) model.getShape(indexx), pp);
					cmd.execute();
					controller.getUndoStack().push(cmd);
					frame.getLogFrame().getModel().addElement("Modify," + ss.toString() + pp.toString());

				} else if (command[1].equalsIgnoreCase("Rectangle")) {

					Rectangle ss = new Rectangle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					indexx = model.getAll().indexOf(ss);
					Rectangle pp = new Rectangle(new Point(Integer.parseInt(command[9]), Integer.parseInt(command[10])),
							Integer.parseInt(command[11]), Integer.parseInt(command[12]),
							new Color(Integer.parseInt(command[13])), new Color(Integer.parseInt(command[14])));
					UpdateRectangleCmd cmd = new UpdateRectangleCmd((Rectangle) model.getShape(indexx), pp);
					cmd.execute();
					controller.getUndoStack().push(cmd);
					frame.getLogFrame().getModel().addElement("Modify," + ss.toString() + pp.toString());

				} else if (command[1].equalsIgnoreCase("Hexagon")) {

					HexagonAdapter ss = new HexagonAdapter(
							new Hexagon(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
									Integer.parseInt(command[4])),
							new Color(Integer.parseInt(command[5])), new Color(Integer.parseInt(command[6])));
					indexx = model.getAll().indexOf(ss);
					HexagonAdapter pp = new HexagonAdapter(
							new Hexagon(Integer.parseInt(command[8]), Integer.parseInt(command[9]),
									Integer.parseInt(command[10])),
							new Color(Integer.parseInt(command[11])), new Color(Integer.parseInt(command[12])));
					UpdateHexagonCmd cmd = new UpdateHexagonCmd((HexagonAdapter) model.getShape(indexx), pp);
					cmd.execute();
					controller.getUndoStack().push(cmd);
					frame.getLogFrame().getModel().addElement("Modify," + ss.toString() + pp.toString());

				}

				break;

			case "MoveUp":

				int index1;
				Shape ss;

				if (command[1].equalsIgnoreCase("Point")) {

					ss = new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
							new Color(Integer.parseInt(command[4])));
					index1 = model.getAll().indexOf(ss);
					MoveUpCmd cmdMD = new MoveUpCmd(model.getAll(), model.getShape(index1));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveUp," + ss.toString());

				} else if (command[1].equalsIgnoreCase("Square")) {
					ss = new Square(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index1 = model.getAll().indexOf(ss);
					MoveUpCmd cmdMU = new MoveUpCmd(model.getAll(), model.getShape(index1));
					cmdMU.execute();
					controller.getUndoStack().push(cmdMU);
					frame.getLogFrame().getModel().addElement("MoveUp," + ss.toString());

				} else if (command[1].equalsIgnoreCase("Line")) {
					ss = new Line(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							new Point(Integer.parseInt(command[4]), Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index1 = model.getAll().indexOf(ss);
					MoveUpCmd cmdMU = new MoveUpCmd(model.getAll(), model.getShape(index1));
					cmdMU.execute();
					controller.getUndoStack().push(cmdMU);
					frame.getLogFrame().getModel().addElement("MoveUp," + ss.toString());

				} else if (command[1].equalsIgnoreCase("Circle")) {
					ss = new Circle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index1 = model.getAll().indexOf(ss);
					MoveUpCmd cmdMU = new MoveUpCmd(model.getAll(), model.getShape(index1));
					cmdMU.execute();
					controller.getUndoStack().push(cmdMU);
					frame.getLogFrame().getModel().addElement("MoveUp," + ss.toString());

				} else if (command[1].equalsIgnoreCase("CircleWithHole")) {
					ss = new CircleWithHole(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					index1 = model.getAll().indexOf(ss);
					MoveUpCmd cmdMU = new MoveUpCmd(model.getAll(), model.getShape(index1));
					cmdMU.execute();
					controller.getUndoStack().push(cmdMU);
					frame.getLogFrame().getModel().addElement("MoveUp," + ss.toString());

				} else if (command[1].equalsIgnoreCase("Rectangle")) {
					ss = new Rectangle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					index1 = model.getAll().indexOf(ss);
					MoveUpCmd cmdMU = new MoveUpCmd(model.getAll(), model.getShape(index1));
					cmdMU.execute();
					controller.getUndoStack().push(cmdMU);
					frame.getLogFrame().getModel().addElement("MoveUp," + ss.toString());

				} else if (command[1].equalsIgnoreCase("Hexagon")) {
					ss = new HexagonAdapter(
							new Hexagon(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
									Integer.parseInt(command[4])),
							new Color(Integer.parseInt(command[5])), new Color(Integer.parseInt(command[6])));
					index1 = model.getAll().indexOf(ss);
					MoveUpCmd cmdMU = new MoveUpCmd(model.getAll(), model.getShape(index1));
					cmdMU.execute();
					controller.getUndoStack().push(cmdMU);
					frame.getLogFrame().getModel().addElement("MoveUp," + ss.toString());

				}

				break;

			case "MoveDown":

				int index2;
				Shape sh;

				if (command[1].equalsIgnoreCase("Point")) {

					sh = new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
							new Color(Integer.parseInt(command[4])));
					index2 = model.getAll().indexOf(sh);
					MoveDownCmd cmdMD = new MoveDownCmd(model.getAll(), model.getShape(index2));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveDown," + sh.toString());

				} else if (command[1].equalsIgnoreCase("Square")) {
					sh = new Square(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index2 = model.getAll().indexOf(sh);
					MoveDownCmd cmdMD = new MoveDownCmd(model.getAll(), model.getShape(index2));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveDown," + sh.toString());

				} else if (command[1].equalsIgnoreCase("Line")) {
					sh = new Line(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							new Point(Integer.parseInt(command[4]), Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index2 = model.getAll().indexOf(sh);
					MoveDownCmd cmdMD = new MoveDownCmd(model.getAll(), model.getShape(index2));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveDown," + sh.toString());

				} else if (command[1].equalsIgnoreCase("Circle")) {
					sh = new Circle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index2 = model.getAll().indexOf(sh);
					MoveDownCmd cmdMD = new MoveDownCmd(model.getAll(), model.getShape(index2));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveDown," + sh.toString());

				} else if (command[1].equalsIgnoreCase("CircleWithHole")) {
					sh = new CircleWithHole(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					index2 = model.getAll().indexOf(sh);
					MoveDownCmd cmdMD = new MoveDownCmd(model.getAll(), model.getShape(index2));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveDown," + sh.toString());

				} else if (command[1].equalsIgnoreCase("Rectangle")) {
					sh = new Rectangle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					index2 = model.getAll().indexOf(sh);
					MoveDownCmd cmdMD = new MoveDownCmd(model.getAll(), model.getShape(index2));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveDown," + sh.toString());

				} else if (command[1].equalsIgnoreCase("Hexagon")) {
					sh = new HexagonAdapter(
							new Hexagon(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
									Integer.parseInt(command[4])),
							new Color(Integer.parseInt(command[5])), new Color(Integer.parseInt(command[6])));
					index2 = model.getAll().indexOf(sh);
					MoveDownCmd cmdMD = new MoveDownCmd(model.getAll(), model.getShape(index2));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveDown," + sh.toString());

				}

				break;

			case "MoveBottom":

				int index3;
				Shape shape;

				if (command[1].equalsIgnoreCase("Point")) {

					shape = new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
							new Color(Integer.parseInt(command[4])));
					index3 = model.getAll().indexOf(shape);
					MoveToBottomCmd cmdMD = new MoveToBottomCmd(model.getAll(), model.getShape(index3));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveBottom," + shape.toString());

				} else if (command[1].equalsIgnoreCase("Square")) {
					shape = new Square(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index3 = model.getAll().indexOf(shape);
					MoveToBottomCmd cmdMD = new MoveToBottomCmd(model.getAll(), model.getShape(index3));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveBottom," + shape.toString());

				} else if (command[1].equalsIgnoreCase("Line")) {
					shape = new Line(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							new Point(Integer.parseInt(command[4]), Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index3 = model.getAll().indexOf(shape);
					MoveToBottomCmd cmdMD = new MoveToBottomCmd(model.getAll(), model.getShape(index3));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveBottom," + shape.toString());

				} else if (command[1].equalsIgnoreCase("Circle")) {
					shape = new Circle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index3 = model.getAll().indexOf(shape);
					MoveToBottomCmd cmdMD = new MoveToBottomCmd(model.getAll(), model.getShape(index3));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveBottom," + shape.toString());

				} else if (command[1].equalsIgnoreCase("CircleWithHole")) {
					shape = new CircleWithHole(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					index3 = model.getAll().indexOf(shape);
					MoveToBottomCmd cmdMD = new MoveToBottomCmd(model.getAll(), model.getShape(index3));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveBottom," + shape.toString());

				} else if (command[1].equalsIgnoreCase("Rectangle")) {
					shape = new Rectangle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					index3 = model.getAll().indexOf(shape);
					MoveToBottomCmd cmdMD = new MoveToBottomCmd(model.getAll(), model.getShape(index3));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveBottom," + shape.toString());

				} else if (command[1].equalsIgnoreCase("Hexagon")) {
					shape = new HexagonAdapter(
							new Hexagon(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
									Integer.parseInt(command[4])),
							new Color(Integer.parseInt(command[5])), new Color(Integer.parseInt(command[6])));
					index3 = model.getAll().indexOf(shape);
					MoveToBottomCmd cmdMD = new MoveToBottomCmd(model.getAll(), model.getShape(index3));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveBottom," + shape.toString());

				}

				break;

			case "Select":

				int indexSel;
				Shape shapeSel;

				if (command[1].equalsIgnoreCase("Point")) {

					shapeSel = new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
							new Color(Integer.parseInt(command[4])));
					indexSel = model.getAll().indexOf(shapeSel);
					SelectShapeCmd cmdSS = new SelectShapeCmd(model.getShape(indexSel));
					cmdSS.execute();
					controller.getUndoStack().push(cmdSS);
					frame.getLogFrame().getModel().addElement("Select," + shapeSel.toString());

				} else if (command[1].equalsIgnoreCase("Square")) {

					shapeSel = new Square(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					indexSel = model.getAll().indexOf(shapeSel);
					SelectShapeCmd cmdSS = new SelectShapeCmd(model.getShape(indexSel));
					cmdSS.execute();
					controller.getUndoStack().push(cmdSS);
					frame.getLogFrame().getModel().addElement("Select," + shapeSel.toString());

				} else if (command[1].equalsIgnoreCase("Line")) {

					shapeSel = new Line(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							new Point(Integer.parseInt(command[4]), Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));

					indexSel = model.getAll().indexOf(shapeSel);
					SelectShapeCmd cmdSS = new SelectShapeCmd(model.getShape(indexSel));
					cmdSS.execute();
					controller.getUndoStack().push(cmdSS);
					frame.getLogFrame().getModel().addElement("Select," + shapeSel.toString());

				} else if (command[1].equalsIgnoreCase("Circle")) {

					shapeSel = new Circle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					indexSel = model.getAll().indexOf(shapeSel);
					SelectShapeCmd cmdSS = new SelectShapeCmd(model.getShape(indexSel));
					cmdSS.execute();
					controller.getUndoStack().push(cmdSS);
					frame.getLogFrame().getModel().addElement("Select," + shapeSel.toString());

				} else if (command[1].equalsIgnoreCase("CircleWithHole")) {

					shapeSel = new CircleWithHole(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));

					indexSel = model.getAll().indexOf(shapeSel);
					SelectShapeCmd cmdSS = new SelectShapeCmd(model.getShape(indexSel));
					cmdSS.execute();
					controller.getUndoStack().push(cmdSS);
					frame.getLogFrame().getModel().addElement("Select," + shapeSel.toString());

				} else if (command[1].equalsIgnoreCase("Rectangle")) {

					shapeSel = new Rectangle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));

					indexSel = model.getAll().indexOf(shapeSel);
					SelectShapeCmd cmdSS = new SelectShapeCmd(model.getShape(indexSel));
					cmdSS.execute();
					controller.getUndoStack().push(cmdSS);
					frame.getLogFrame().getModel().addElement("Select," + shapeSel.toString());

				} else if (command[1].equalsIgnoreCase("Hexagon")) {

					shapeSel = new HexagonAdapter(
							new Hexagon(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
									Integer.parseInt(command[4])),
							new Color(Integer.parseInt(command[5])), new Color(Integer.parseInt(command[6])));
					indexSel = model.getAll().indexOf(shapeSel);
					SelectShapeCmd cmdSS = new SelectShapeCmd(model.getShape(indexSel));
					cmdSS.execute();
					controller.getUndoStack().push(cmdSS);
					frame.getLogFrame().getModel().addElement("Select," + shapeSel.toString());

				}

				break;

			case "Deselect":

				int indexDesel;
				Shape shapeDesel;

				if (command[1].equalsIgnoreCase("Point")) {

					shapeDesel = new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
							new Color(Integer.parseInt(command[4])));
					indexDesel = model.getAll().indexOf(shapeDesel);
					DeselectShapeCmd cmdDS = new DeselectShapeCmd(model.getShape(indexDesel));
					cmdDS.execute();
					controller.getUndoStack().push(cmdDS);
					frame.getLogFrame().getModel().addElement("Deselect," + shapeDesel.toString());

				} else if (command[1].equalsIgnoreCase("Square")) {

					shapeDesel = new Square(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					indexDesel = model.getAll().indexOf(shapeDesel);
					DeselectShapeCmd cmdDS = new DeselectShapeCmd(model.getShape(indexDesel));
					cmdDS.execute();
					controller.getUndoStack().push(cmdDS);
					frame.getLogFrame().getModel().addElement("Deselect," + shapeDesel.toString());

				} else if (command[1].equalsIgnoreCase("Line")) {

					shapeDesel = new Line(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							new Point(Integer.parseInt(command[4]), Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));

					indexDesel = model.getAll().indexOf(shapeDesel);
					DeselectShapeCmd cmdDS = new DeselectShapeCmd(model.getShape(indexDesel));
					cmdDS.execute();
					controller.getUndoStack().push(cmdDS);
					frame.getLogFrame().getModel().addElement("Deselect," + shapeDesel.toString());

				} else if (command[1].equalsIgnoreCase("Circle")) {

					shapeDesel = new Circle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));

					indexDesel = model.getAll().indexOf(shapeDesel);
					DeselectShapeCmd cmdDS = new DeselectShapeCmd(model.getShape(indexDesel));
					cmdDS.execute();
					controller.getUndoStack().push(cmdDS);
					frame.getLogFrame().getModel().addElement("Deselect," + shapeDesel.toString());

				} else if (command[1].equalsIgnoreCase("CircleWithHole")) {

					shapeDesel = new CircleWithHole(
							new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					indexDesel = model.getAll().indexOf(shapeDesel);
					DeselectShapeCmd cmdDS = new DeselectShapeCmd(model.getShape(indexDesel));
					cmdDS.execute();
					controller.getUndoStack().push(cmdDS);
					frame.getLogFrame().getModel().addElement("Deselect," + shapeDesel.toString());

				} else if (command[1].equalsIgnoreCase("Rectangle")) {

					shapeDesel = new Rectangle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));

					indexDesel = model.getAll().indexOf(shapeDesel);
					DeselectShapeCmd cmdDS = new DeselectShapeCmd(model.getShape(indexDesel));
					cmdDS.execute();
					controller.getUndoStack().push(cmdDS);
					frame.getLogFrame().getModel().addElement("Deselect," + shapeDesel.toString());

				} else if (command[1].equalsIgnoreCase("Hexagon")) {

					shapeDesel = new HexagonAdapter(
							new Hexagon(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
									Integer.parseInt(command[4])),
							new Color(Integer.parseInt(command[5])), new Color(Integer.parseInt(command[6])));
					indexDesel = model.getAll().indexOf(shapeDesel);
					DeselectShapeCmd cmdDS = new DeselectShapeCmd(model.getShape(indexDesel));
					cmdDS.execute();
					controller.getUndoStack().push(cmdDS);
					frame.getLogFrame().getModel().addElement("Deselect," + shapeDesel.toString());

				}

				break;

			case "MoveTop":

				int index4;
				Shape shape4;

				if (command[1].equalsIgnoreCase("Point")) {

					shape4 = new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
							new Color(Integer.parseInt(command[4])));
					index4 = model.getAll().indexOf(shape4);
					MoveToTopCmd cmdMD = new MoveToTopCmd(model.getAll(), model.getShape(index4));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveTop," + shape4.toString());

				} else if (command[1].equalsIgnoreCase("Square")) {
					shape4 = new Square(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index4 = model.getAll().indexOf(shape4);
					MoveToTopCmd cmdMD = new MoveToTopCmd(model.getAll(), model.getShape(index4));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveTop," + shape4.toString());

				} else if (command[1].equalsIgnoreCase("Line")) {
					shape4 = new Line(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							new Point(Integer.parseInt(command[4]), Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index4 = model.getAll().indexOf(shape4);
					MoveToTopCmd cmdMD = new MoveToTopCmd(model.getAll(), model.getShape(index4));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveTop," + shape4.toString());

				} else if (command[1].equalsIgnoreCase("Circle")) {
					shape4 = new Circle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), new Color(Integer.parseInt(command[5])),
							new Color(Integer.parseInt(command[6])));
					index4 = model.getAll().indexOf(shape4);
					MoveToTopCmd cmdMD = new MoveToTopCmd(model.getAll(), model.getShape(index4));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveTop," + shape4.toString());

				} else if (command[1].equalsIgnoreCase("CircleWithHole")) {
					shape4 = new CircleWithHole(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					index4 = model.getAll().indexOf(shape4);
					MoveToTopCmd cmdMD = new MoveToTopCmd(model.getAll(), model.getShape(index4));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveTop," + shape4.toString());

				} else if (command[1].equalsIgnoreCase("Rectangle")) {
					shape4 = new Rectangle(new Point(Integer.parseInt(command[2]), Integer.parseInt(command[3])),
							Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							new Color(Integer.parseInt(command[6])), new Color(Integer.parseInt(command[7])));
					index4 = model.getAll().indexOf(shape4);
					MoveToTopCmd cmdMD = new MoveToTopCmd(model.getAll(), model.getShape(index4));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveTop," + shape4.toString());

				} else if (command[1].equalsIgnoreCase("Hexagon")) {
					shape4 = new HexagonAdapter(
							new Hexagon(Integer.parseInt(command[2]), Integer.parseInt(command[3]),
									Integer.parseInt(command[4])),
							new Color(Integer.parseInt(command[5])), new Color(Integer.parseInt(command[6])));
					index4 = model.getAll().indexOf(shape4);
					MoveToTopCmd cmdMD = new MoveToTopCmd(model.getAll(), model.getShape(index4));
					cmdMD.execute();
					controller.getUndoStack().push(cmdMD);
					frame.getLogFrame().getModel().addElement("MoveTop," + shape4.toString());

				}

				break;

			default:
				break;
			}

			model.notifyObservers();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

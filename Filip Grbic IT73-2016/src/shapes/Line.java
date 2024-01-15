package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
	
	private Point start;
	private Point end;
	
	public Line() {}
	
	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	public Line(Point start, Point end, Color color) {
		super(color);
		this.start = start;
		this.end = end;
	}
	
	public Point middleLine() {
		int middleX = (start.getX() + end.getX()) / 2;
		int middleY = (start.getY() + end.getY()) / 2;
		return new Point(middleX, middleY);
	}
	
	public String toString() {
		return "Line," + this.start.getX() + "," + this.start.getY() + "," + this.end.getX() + ","
				+ this.end.getY() + "," + this.getColor().getRGB();
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line temp = (Line) obj;
			if (this.start.equals(temp.start) && this.end.equals(temp.end))
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public double length() {
		return start.distance(end);
	}
	
	public int compareTo(Object o) {
		if (o instanceof Line) {
			Line temp = (Line) o;
			return (int) this.length() - (int) temp.length();
		} else
			return 0;
	}
	
	public void moveFor(int x, int y) {
		start.setX(start.getX() + x);
		start.setY(start.getY() + y);
		end.setX(end.getX() + x);
		end.setY(end.getY() + y);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
		
		if (isSelected()) {
			selected(g);
		}		
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		start.selected(g);
		end.selected(g);
		middleLine().selected(g);
	}

	@Override
	public Line clone() {
		return new Line(this.start.clone(), this.end.clone(), this.getColor());
	}

	@Override
	public boolean contains(int x, int y) {
		Point clickPoint = new Point(x, y);
		if (clickPoint.distance(start) + clickPoint.distance(end) - this.length() <= 0.5)
			return true;
		else
			return false;
	}
	
	public Point getStart() {
		return start;
	}

	public Point getEnd() {
		return end;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public void setEnd(Point end) {
		this.end = end;
	}
}

package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape implements Movable{
	
	private int x;
	private int y;
	
	public Point() {}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		setColor(color);
	}
	
	public String toString() {
		return "Point," + this.x + "," + this.y + ","+ this.getColor().getRGB();
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point temp = (Point) obj;
			if (this.x == temp.getX() && this.y == temp.getY())
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public double distance(Point t2) {
		double dx = x - t2.getX();
		double dy = y - t2.y;
		double result = Math.sqrt(dx * dx + dy * dy);
		return result;
	}

	@Override
	public void moveTo(int x, int y) {
		setX(x);
		setY(y);
	}

	@Override
	public void moveFor(int x, int y) {
		this.x += x;
		this.y += y;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(x - 2, y, x + 2, y);
		g.drawLine(x, y - 2, x, y + 2);
		if (isSelected())
			selected(g);
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.blue);
		g.drawRect(x - 3, y - 3, 6, 6);
	}

	@Override
	public Point clone() {
		return new Point(this.getX(), this.getY(), this.getColor());
	}

	@Override
	public boolean contains(int x, int y) {
		Point clickPoint = new Point(x, y);
		if (clickPoint.distance(this) <= 2)
			return true;
		else
			return false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}

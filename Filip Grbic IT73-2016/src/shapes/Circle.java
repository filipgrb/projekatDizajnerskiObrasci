package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape implements Movable {
	
	protected Point center;
	protected int r;
	
	public Circle() {}
	
	public Circle(Point center, int r) {
		this.center = center;
		this.r = r;
	}
	
	public Circle(Point center, int r, Color color) {
		this(center, r);
		setColor(color);
	}
	
	public Circle(Point center, int r, Color color, Color innerColor) {
		this(center, r, color);
		setInnerColor(innerColor);
	}
	
	public String toString() {
		return "Circle," + this.center.getX()+ "," + this.center.getY() + "," + this.r + ","+ this.getColor().getRGB() + "," +
				Integer.toString(getInnerColor().getRGB());
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle temp = (Circle) obj;
			if (this.center.equals(temp.center) && this.r == temp.r)
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public double area() {
		return r * r * Math.PI;
	}

	public double volume() {
		return 2 * r * Math.PI;
	}

	public int compareTo(Object o) {
		if (o instanceof Circle) {
			Circle temp = (Circle) o;
			return this.r - temp.r;
		} else
			return 0;
	}

	@Override
	public void moveTo(int x, int y) {
		center.setX(x);
		center.setY(y);
	}

	@Override
	public void moveFor(int x, int y) {
		center.setX(center.getX() + x);
		center.setY(center.getY() + y);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(center.getX() - r + 1, center.getY() - r + 1, 2 * r - 2, r * 2 - 2);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(center.getX() - r, center.getY() - r, 2 * r, r * 2);
		fill(g);
		
		if (isSelected()) {
			selected(g);
		}		
	}

	@Override
	public void selected(Graphics g) {
		new Line(new Point(center.getX(), center.getY() - r), new Point(center.getX(), center.getY() + r))
		.selected(g);
		new Line(new Point(center.getX() - r, center.getY()), new Point(center.getX() + r, center.getY()))
		.selected(g);
	}

	@Override
	public Circle clone() {
		return new Circle(this.center.clone(), this.r, this.getColor(),this.getInnerColor());
	}

	@Override
	public boolean contains(int x, int y) {
		Point clickPoint = new Point(x, y);
		if (clickPoint.distance(center) <= r)
			return true;
		else
			return false;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}
}

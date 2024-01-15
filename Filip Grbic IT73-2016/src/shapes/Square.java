package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends SurfaceShape implements Movable{
	
	protected Point pointUpLeft;
	protected int sideLength;
	
	public Square() {}
	
	public Square(Point pointUpLeft, int sideLength) {
		this.pointUpLeft = pointUpLeft;
		this.sideLength = sideLength;
	}
	
	public Square(Point pointUpLeft, int sideLength, Color color) {
		this(pointUpLeft, sideLength);
		setColor(color);
	}
	
	public Square(Point pointUpLeft, int sideLength, Color color, Color innerColor) {
		this(pointUpLeft, sideLength, color);
		setInnerColor(innerColor);
	}
	
	public Line diagonal() {
		return new Line(pointUpLeft, new Point(pointUpLeft.getX() + sideLength, pointUpLeft.getY() + sideLength));
	}

	public Point center() {
		return diagonal().middleLine();
	}
	
	public int area() {
		return sideLength * sideLength;
	}
	
	public int volume() {
		return 4 * sideLength;
	}

	public String toString() {
		return "Square," + this.pointUpLeft.getX() + "," + this.pointUpLeft.getY() + "," + this.sideLength + ","
				+ getColor().getRGB() + "," + Integer.toString(getInnerColor().getRGB());
	}

	public boolean equals(Object obj) {
		if (obj instanceof Square) {
			Square temp = (Square) obj;
			if (this.pointUpLeft.equals(temp.pointUpLeft) && this.sideLength == temp.sideLength)
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public int compareTo(Object o) {
		if (o instanceof Square) {
			Square temp = (Square) o;
			return this.area() - temp.area();
		} else
			return 0;
	}
	
	@Override
	public void moveTo(int x, int y) {
		pointUpLeft.setX(x);
		pointUpLeft.setY(y);
	}
	
	@Override
	public void moveFor(int x, int y) {
		pointUpLeft.setX(pointUpLeft.getX() + x);
		pointUpLeft.setY(pointUpLeft.getY() + y);
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(pointUpLeft.getX() + 1, pointUpLeft.getY() + 1, sideLength - 1, sideLength - 1);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(pointUpLeft.getX(), pointUpLeft.getY(), sideLength, sideLength);
		fill(g);
		
		if (isSelected()) {
			selected(g);
		}
	}
	
	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		new Line(getPointUpLeft(), new Point(getPointUpLeft().getX() + sideLength, getPointUpLeft().getY())).selected(g);
		new Line(getPointUpLeft(), new Point(getPointUpLeft().getX(), getPointUpLeft().getY() + sideLength)).selected(g);
		new Line(new Point(getPointUpLeft().getX() + sideLength, getPointUpLeft().getY()), diagonal().getEnd()).selected(g);
		new Line(new Point(getPointUpLeft().getX(), getPointUpLeft().getY() + sideLength), diagonal().getEnd()).selected(g);
	}
	
	@Override
	public Square clone() {
		return new Square(getPointUpLeft().clone(), getSideLength(), getColor(), getInnerColor());
	}
	
	@Override
	public boolean contains(int x, int y) {
		if (this.getPointUpLeft().getX() <= x && x <= (this.getPointUpLeft().getX() + sideLength)
				&& this.getPointUpLeft().getY() <= y && y <= (this.getPointUpLeft().getY() + sideLength))
			return true;
		else
			return false;
	}

	public Point getPointUpLeft() {
		return pointUpLeft;
	}

	public void setPointUpLeft(Point pointUpLeft) {
		this.pointUpLeft = pointUpLeft;
	}

	public int getSideLength() {
		return sideLength;
	}

	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}
}

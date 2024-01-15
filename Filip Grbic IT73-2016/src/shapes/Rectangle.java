package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Square {

	private int width;
	
	public Rectangle() {}
	
	public Rectangle(Point pointUpLeft, int height, int width) {
		super(pointUpLeft, height);
		this.width = width;
	}
	
	public Rectangle(Point pointUpLeft, int height, int width, Color color) {
		this(pointUpLeft, height, width);
		setColor(color);
	}

	public Rectangle(Point pointUpLeft, int height, int width, Color color, Color innerColor) {
		this(pointUpLeft, height, width,color);
		setInnerColor(innerColor);
	}
	
	public Line diagonal() {
		return new Line(pointUpLeft, new Point(pointUpLeft.getX() + getSideLength(), pointUpLeft.getY() + width));
	}

	public Point center() {
		return diagonal().middleLine();
	}
	
	public int area() {
		return width * getSideLength();
	}
	
	public int volume() {
		return 2 * (width + getSideLength());
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle temp = (Rectangle) obj;
			if (this.pointUpLeft.equals(temp.pointUpLeft) && this.width == temp.width
					&& this.getSideLength() == temp.getSideLength())
				return true;
			else
				return false;
		} else
			return false;
	}

	public String toString() {
		return "Rectangle," + this.pointUpLeft.getX() + "," + this.pointUpLeft.getY() + "," +this.getSideLength() + ","
				+ this.width + "," + getColor().getRGB() + "," + Integer.toString(getInnerColor().getRGB());
	}
	
	public boolean contains(int x, int y) {
		if (this.getPointUpLeft().getX() <= x && x <= (this.getPointUpLeft().getX() + sideLength)
				&& this.getPointUpLeft().getY() <= y && y <= (this.getPointUpLeft().getY() + width))
			return true;
		else
			return false;
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(pointUpLeft.getX(), pointUpLeft.getY(), sideLength, width);
		fill(g);
		
		if (isSelected()) {
			selected(g);
		}
	}
	
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(pointUpLeft.getX() + 1, pointUpLeft.getY() + 1, sideLength - 1, width - 1);
	}
	
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		new Line(getPointUpLeft(), new Point(getPointUpLeft().getX() + sideLength, getPointUpLeft().getY())).selected(g);
		new Line(getPointUpLeft(), new Point(getPointUpLeft().getX(), getPointUpLeft().getY() + width)).selected(g);
		new Line(new Point(getPointUpLeft().getX() + sideLength, getPointUpLeft().getY()), diagonal().getEnd()).selected(g);
		new Line(new Point(getPointUpLeft().getX(), getPointUpLeft().getY() + width), diagonal().getEnd()).selected(g);
	}
	
	public Rectangle clone() {
		return new Rectangle(getPointUpLeft().clone(),getSideLength(),getWidth(), getColor(),getInnerColor());
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}

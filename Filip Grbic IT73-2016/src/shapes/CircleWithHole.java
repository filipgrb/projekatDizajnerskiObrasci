package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class CircleWithHole extends Circle implements Serializable {
	
	public int holeRadius;
	
	public CircleWithHole() {}
	
	public CircleWithHole(Point center, int r, int holeRadius) {
		super(center, r);
		this.holeRadius = holeRadius;
	}
	
	public CircleWithHole(Point center, int r, int holeRadius, Color color) {
		this(center, r, holeRadius);
		setColor(color);
	}
	
	public CircleWithHole(Point center, int r, int holeRadius, Color color, Color innerColor) {
		this(center, r, holeRadius, color);
		setInnerColor(innerColor);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof CircleWithHole) {
			CircleWithHole temp = (CircleWithHole) obj;
			if (this.center.equals(temp.center) && this.r == temp.r && this.getHoleRadius() == temp.getHoleRadius())
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public String toString() {
		return "CircleWithHole," + this.center.getX() + "," + this.center.getY() + "," + this.r + "," + this.holeRadius
				+ "," + this.getColor().getRGB() + "," + Integer.toString(getInnerColor().getRGB());
	}
	
	public double area() {
		return r * r * Math.PI;
	}

	public double volume() {
		return (2 * r * Math.PI) - (2 * holeRadius * Math.PI);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        java.awt.Shape outer = new Ellipse2D.Double(getCenter().getX() - getR(), getCenter().getY() - getR(), 2*getR(),2*getR());
        java.awt.Shape inner = new Ellipse2D.Double(getCenter().getX() - getHoleRadius(), getCenter().getY() - getHoleRadius(), 2*getHoleRadius(),2*getHoleRadius());

        Area cwc = new Area( outer );
        cwc.subtract( new Area(inner) );

        g2d.setColor(getInnerColor());
        g2d.fill(cwc);
        g2d.setColor(getColor());
        g2d.draw(cwc);

        g2d.dispose();
        
        if(isSelected()) {
        	selected(g);
        }
	}
	
	public void selected(Graphics g) {
		new Line(new Point(center.getX(), center.getY() - r), new Point(center.getX(), center.getY() + r)).selected(g);
		new Line(new Point(center.getX() - r, center.getY()), new Point(center.getX() + r, center.getY())).selected(g);
		new Line(new Point(center.getX(), center.getY() - holeRadius), new Point(center.getX(), center.getY() + holeRadius)).selected(g);
		new Line(new Point(center.getX() - holeRadius, center.getY()), new Point(center.getX() + holeRadius, center.getY())).selected(g);
	}
	
	public boolean contains(int x, int y) {
		Point clickPoint = new Point(x, y);
		if (clickPoint.distance(center) <= r && clickPoint.distance(center) >= holeRadius)
			return true;
		else
			return false;
	}
	
	public CircleWithHole clone() {
		return new CircleWithHole(getCenter().clone(), getR(), getHoleRadius(), getColor(), getInnerColor());
	}

	public int getHoleRadius() {
		return holeRadius;
	}

	public void setHoleRadius(int holeRadius) {
		this.holeRadius = holeRadius;
	}
}

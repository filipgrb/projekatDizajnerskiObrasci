package adapter;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;
import shapes.Movable;
import shapes.Shape;
import shapes.SurfaceShape;

public class HexagonAdapter extends SurfaceShape implements Movable {
	
	private Hexagon hexagon;
	
	public HexagonAdapter() {}
	
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	public HexagonAdapter(Hexagon hexagon, Color borderColor, Color areaColor) {
		this(hexagon);
		hexagon.setBorderColor(borderColor);
		hexagon.setAreaColor(areaColor);
	}
	
	@Override
	public String toString() {
		return "Hexagon," + this.hexagon.getX() + "," + this.hexagon.getY() + "," + this.hexagon.getR() + ","
				+ this.getColor().getRGB() + "," + this.getInnerColor().getRGB();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			Hexagon h = ((HexagonAdapter) obj).hexagon;
			return hexagon.getX() == h.getX() && hexagon.getY() == h.getY() && hexagon.getR() == h.getR();
		}
		return false;
	}

	@Override
	public void moveTo(int x, int y) {
		this.hexagon.setX(x);
		this.hexagon.setY(y);
	}

	@Override
	public void moveFor(int x, int y) {
		hexagon.setX(hexagon.getX() + x);
		hexagon.setY(hexagon.getY() + y);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(hexagon.getAreaColor());
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		hexagon.setSelected(isSelected());
	}

	@Override
	public void selected(Graphics g) {
	}
	
	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}
	
	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		hexagon.setSelected(selected);
	}

	@Override
	public HexagonAdapter clone() {
		return new HexagonAdapter(
				new Hexagon(hexagon.getX(), hexagon.getY(), hexagon.getR()), this.hexagon.getBorderColor(), this.hexagon.getAreaColor());
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}
	
	@Override
	public void setColor(Color color) {
		super.setColor(color);
		this.hexagon.setBorderColor(color);
	}

	@Override
	public Color getColor() {
		return this.hexagon.getBorderColor();
	}

	@Override
	public void setInnerColor(Color innerColor) {
		super.setInnerColor(innerColor);
		this.hexagon.setAreaColor(innerColor);
	}

	@Override
	public Color getInnerColor() {
		return this.hexagon.getAreaColor();
	}
	
	public void setX(int x) {
		this.hexagon.setX(x);
	}

	public int getX() {
		return this.hexagon.getX();
	}

	public void setY(int y) {
		this.hexagon.setY(y);
	}

	public int getY() {
		return this.hexagon.getY();
	}

	public void setR(int r) {
		this.hexagon.setR(r);
	}

	public int getR() {
		return this.hexagon.getR();
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
}

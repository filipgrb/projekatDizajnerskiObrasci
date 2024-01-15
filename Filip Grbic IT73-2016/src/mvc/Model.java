package mvc;

import java.util.ArrayList;

import observer.Observable;
import observer.Observer;
import shapes.Shape;

public class Model implements Observable {
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public ArrayList<Shape> getAll(){
		return shapes;
	}
	
	public Shape getShape(int i) {
		return shapes.get(i);
	}
	
	public void addShape(Shape s) {
		shapes.add(s);
	}
	
	public void deleteShape(Shape s) {
		shapes.remove(s);
	}
	
	public void addMultiple(ArrayList<Shape> shapes) {
		this.shapes.addAll(shapes);
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		int i=0;
		for(int j=0; j<shapes.size(); j++) {
			if (shapes.get(j).isSelected()) 
				i++;
		}
		for (Observer observer: observers) {
			observer.update(i);
		}
	}
}

package frames;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class TopFrame extends JPanel {
	
	private JToggleButton pointButton, lineButton, circleButton, circleWithHoleButton, squareButton, rectangleButton, hexagonButton,
			selectButton, selectMultipleButton;
	private ButtonGroup toggleButtonsGroup = new ButtonGroup();
	
	public TopFrame() {
		
		pointButton = new JToggleButton("Point");
		toggleButtonsGroup.add(pointButton);
		add(pointButton);
		
		lineButton = new JToggleButton("Line");
		toggleButtonsGroup.add(lineButton);
		add(lineButton);
		
		circleButton = new JToggleButton("Circle");
		toggleButtonsGroup.add(circleButton);
		add(circleButton);
		
		circleWithHoleButton = new JToggleButton("Circle With Hole");
		toggleButtonsGroup.add(circleWithHoleButton);
		add(circleWithHoleButton);
		
		squareButton = new JToggleButton("Square");
		toggleButtonsGroup.add(squareButton);
		add(squareButton);
		
		rectangleButton = new JToggleButton("Rectangle");
		toggleButtonsGroup.add(rectangleButton);
		add(rectangleButton);
		
		hexagonButton = new JToggleButton("Hexagon");
		toggleButtonsGroup.add(hexagonButton);
		add(hexagonButton);
		
		selectButton = new JToggleButton("Select");
		toggleButtonsGroup.add(selectButton);
		add(selectButton);
		
		selectMultipleButton = new JToggleButton("Select Multiple");
		toggleButtonsGroup.add(selectMultipleButton);
		add(selectMultipleButton);
	}
	
	public String returnSelectedButton() {

		if (pointButton.isSelected())
			return "point";

		if (lineButton.isSelected())
			return "line";

		if (squareButton.isSelected())
			return "square";

		if (circleButton.isSelected())
			return "circle";

		if (rectangleButton.isSelected())
			return "rectangle";

		if (hexagonButton.isSelected())
			return "hexagon";
		
		if(circleWithHoleButton.isSelected())
			return "circleWithHole";

		if (selectButton.isSelected())
			return "select";

		if (selectMultipleButton.isSelected())
			return "multiple";
		
		return null;
	}
}

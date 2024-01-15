package frames;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

public class RightFrame extends JPanel {
	
	private JButton undoButton, redoButton, moveUpButton, moveDownButton, moveToTopButton, moveToBottomButton,
			saveLogButton, loadButton, saveButton, loadSerButton, redoLogButton, colorButton, innerColorButton, 
			modifyButton, deleteButton;
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public RightFrame() {
		
		this.setLayout(new GridBagLayout());
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		undoButton = new JButton("UNDO");
		add(undoButton, gbc);
		undoButton.setEnabled(false);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		redoButton = new JButton("REDO");
		add(redoButton, gbc);
		redoButton.setEnabled(false);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 4;
		moveUpButton = new JButton("MOVE UP");
		add(moveUpButton, gbc);
		moveUpButton.setEnabled(false);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 6;
		moveDownButton = new JButton("MOVE DOWN");
		add(moveDownButton, gbc);
		moveDownButton.setEnabled(false);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 8;
		moveToTopButton = new JButton("TO TOP");
		add(moveToTopButton, gbc);
		moveToTopButton.setEnabled(false);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 10;
		moveToBottomButton = new JButton("TO BOTTOM");
		add(moveToBottomButton, gbc);
		moveToBottomButton.setEnabled(false);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 12;
		saveLogButton = new JButton("Save log");
		add(saveLogButton, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 14;
		loadButton = new JButton("Load log");
		add(loadButton, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 16;
		saveButton = new JButton("Save serialization");
		add(saveButton, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 18;
		loadSerButton = new JButton("Load Serialization");
		add(loadSerButton, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 20;
		redoLogButton = new JButton("Redo Log");
		add(redoLogButton, gbc);
		redoLogButton.setEnabled(false);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 22;
		colorButton = new JButton("Color");
		colorButton.setBackground(Color.blue);
		colorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colorButton.setBackground(chooseColor(colorButton.getBackground()));
			}
		});
		add(colorButton, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 24;
		innerColorButton = new JButton("Inner color");
		innerColorButton.setBackground(Color.WHITE);
		innerColorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				innerColorButton.setBackground(chooseColor(innerColorButton.getBackground()));
			}
		});
		add(innerColorButton, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 26;
		modifyButton = new JButton("Modify");
		add(modifyButton, gbc);
		modifyButton.setEnabled(false);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 28;
		deleteButton = new JButton("Delete");
		add(deleteButton, gbc);
		deleteButton.setEnabled(false);
	}
	
	public Color chooseColor(Color previous) {
		Color newC = JColorChooser.showDialog(null, "Choose color", previous);
		if (newC != null)
			return newC;
		else
			return previous;
	}
	
	public void stackButtonEnabler(int undoSize, int redoSize) {
		if (undoSize > 0) {
			undoButton.setEnabled(true);
		}else {
			undoButton.setEnabled(false);
		}
		
		if (redoSize>0) {
			redoButton.setEnabled(true);
		}else {
			redoButton.setEnabled(false);
		}
	}

	public JButton getUndoButton() {
		return undoButton;
	}

	public JButton getRedoButton() {
		return redoButton;
	}

	public JButton getMoveUpButton() {
		return moveUpButton;
	}

	public JButton getMoveDownButton() {
		return moveDownButton;
	}

	public JButton getMoveToTopButton() {
		return moveToTopButton;
	}

	public JButton getMoveToBottomButton() {
		return moveToBottomButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public JButton getLoadButton() {
		return loadButton;
	}

	public JButton getSaveLogButton() {
		return saveLogButton;
	}

	public JButton getRedoLogButton() {
		return redoLogButton;
	}

	public JButton getColorButton() {
		return colorButton;
	}

	public JButton getInnerColorButton() {
		return innerColorButton;
	}

	public JButton getModifyButton() {
		return modifyButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JButton getLoadSerButton() {
		return loadSerButton;
	}

	public GridBagConstraints getGbc() {
		return gbc;
	}
}

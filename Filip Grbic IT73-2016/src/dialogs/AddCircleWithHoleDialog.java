package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mvc.Frame;
import shapes.CircleWithHole;
import shapes.Point;

public class AddCircleWithHoleDialog extends JDialog {

	private JTextField txtRadius, txtRadius1;
	private JButton btnOk = new JButton("Ok");
	private JButton btnCancel = new JButton("Cancel");
	
	private boolean added = false;
	private CircleWithHole circleWithHole = new CircleWithHole(new Point(1, 1), 1, 1, Color.BLACK, Color.BLACK);
	
	public AddCircleWithHoleDialog(int x, int y, Frame frame) {
		setTitle("Circle With Hole Creation");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 375, 175);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblRadius = new JLabel("Enter outer radius:");
		lblRadius.setBounds(10, 15, 170, 25);
		panel.add(lblRadius);

		txtRadius = new JTextField();
		txtRadius.setBounds(120, 15, 224, 25);
		panel.add(txtRadius);
		txtRadius.setColumns(10);

		JLabel lblRadius1 = new JLabel("Enter inner radius:");
		lblRadius1.setBounds(10, 55, 170, 25);
		panel.add(lblRadius1);

		txtRadius1 = new JTextField();
		txtRadius1.setBounds(120, 55, 224, 25);
		panel.add(txtRadius1);
		txtRadius1.setColumns(10);
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((txtRadius.getText().trim().length() == 0) || (txtRadius1.getText().trim().length() == 0)) {
					JOptionPane.showMessageDialog(getContentPane(), "You have to enter radius!", "Error!",
							JOptionPane.WARNING_MESSAGE);
					return;

				} else {
					try {
						int radius = Integer.parseInt(txtRadius.getText());
						int radius1 = Integer.parseInt(txtRadius1.getText());

						if ((radius <= 0) || (radius1 <= 0)) {
							JOptionPane.showMessageDialog(getContentPane(), "Radius has to be larger than 0", "Warning",
									JOptionPane.WARNING_MESSAGE);
						} else if(radius <= radius1) {
							JOptionPane.showMessageDialog(getContentPane(), "Inner radius must be smaller than outer radius!", "Warning",
									JOptionPane.WARNING_MESSAGE);					
						}else {
							circleWithHole.setCenter(new Point(x, y));
							circleWithHole.setR(radius);
							circleWithHole.setHoleRadius(radius1);
							circleWithHole.setColor(frame.getRightFrame().getColorButton().getBackground());
							circleWithHole.setInnerColor(frame.getRightFrame().getInnerColorButton().getBackground());

							added = true;
							dispose();
						}

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(panel, "Radius has to be integer", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		btnOk.setBounds(35, 100, 89, 25);
		panel.add(btnOk);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		btnCancel.setBounds(235,100,89,25);
		panel.add(btnCancel);
	}
	
	public boolean isAdded() {
		return this.added;
	}
	
	public CircleWithHole getCircleWithHole() {
		return circleWithHole;
	}
}

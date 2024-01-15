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
import shapes.Point;
import shapes.Rectangle;

public class AddRectangleDialog extends JDialog {

	private JTextField txtWidth, txtHeight;
	private JButton btnOk = new JButton("Ok");
	private JButton btnCancel = new JButton("Cancel");
	
	private boolean added = false;
	private Rectangle rectangle = new Rectangle(new Point(1, 1), 1, 1, Color.black, Color.black);
	
	public AddRectangleDialog(int x, int y, Frame frame) {
		setTitle("Rectangle Creation");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 375, 200);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblWidth = new JLabel("Enter height:");
		lblWidth.setBounds(15, 15, 170, 25);
		panel.add(lblWidth);

		JLabel lblHeight = new JLabel("Enter width:");
		lblHeight.setBounds(15, 55, 170, 25);
		panel.add(lblHeight);

		txtWidth = new JTextField();
		txtWidth.setBounds(120, 15, 224, 25);
		panel.add(txtWidth);
		txtWidth.setColumns(10);

		txtHeight = new JTextField();
		txtHeight.setBounds(120, 55, 224, 25);
		panel.add(txtHeight);
		txtHeight.setColumns(10);
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtWidth.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "You have to enter width!", "Error!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (txtHeight.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "You have to enter height!", "Error!", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					try {
						int widthh = Integer.parseInt(txtWidth.getText().trim());
						int heightt = Integer.parseInt(txtHeight.getText().trim());

						if (widthh <= 0) {
							JOptionPane.showMessageDialog(getContentPane(), "Width has to be larger than 0", "Warning",
									JOptionPane.WARNING_MESSAGE);
						} else if (heightt <= 0) {
							JOptionPane.showMessageDialog(getContentPane(), "Height has to be larger than 0", "Warning",
									JOptionPane.WARNING_MESSAGE);
						} else {
							rectangle.setPointUpLeft(new Point(x, y));
							rectangle.setWidth(widthh);
							rectangle.setSideLength(heightt);
							rectangle.setColor(frame.getRightFrame().getColorButton().getBackground());
							rectangle.setInnerColor(frame.getRightFrame().getInnerColorButton().getBackground());

							added = true;
							dispose();
						}

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(panel, "Width and Height have to be integers", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		btnOk.setBounds(35, 95, 89, 25);
		panel.add(btnOk);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		btnCancel.setBounds(235, 95, 89, 25);
		panel.add(btnCancel);
	}
	
	public boolean isAdded() {
		return this.added;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
}

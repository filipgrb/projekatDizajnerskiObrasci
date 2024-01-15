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
import shapes.Square;

public class AddSquareDialog extends JDialog {

	private JTextField txtSideLength;
	private JButton btnOk = new JButton("Ok");
	private JButton btnCancel = new JButton("Cancel");
	
	private boolean added = false;
	private Square square = new Square(new Point(1, 1), 1, Color.BLACK, Color.BLACK);
	
	public AddSquareDialog(int x, int y, Frame frame) {
		setTitle("Square Creation");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 375, 130);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblSideLength = new JLabel("Enter side length:");
		lblSideLength.setBounds(15, 15, 170, 25);
		panel.add(lblSideLength);

		txtSideLength = new JTextField();
		txtSideLength.setBounds(120, 15, 224, 25);
		panel.add(txtSideLength);
		txtSideLength.setColumns(10);
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSideLength.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "You have to enter side length!", "Error!",
							JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					try {
						int side = Integer.parseInt(txtSideLength.getText());

						if (side <= 0) {
							JOptionPane.showMessageDialog(getContentPane(), "Side has to be larger than 0", "Warning",
									JOptionPane.WARNING_MESSAGE);
						} else {
							square.setPointUpLeft(new Point(x,y));
							square.setSideLength(side);
							square.setColor(frame.getRightFrame().getColorButton().getBackground());
							square.setInnerColor(frame.getRightFrame().getInnerColorButton().getBackground());

							added = true;
							dispose();
						}

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(panel, "Length has to be integer", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		btnOk.setBounds(35, 60, 89, 25);
		panel.add(btnOk);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		btnCancel.setBounds(235, 60, 89, 25);
		panel.add(btnCancel);
	}
	
	public boolean isAdded() {
		return this.added;
	}
	
	public Square getSquare() {
		return square;
	}
}

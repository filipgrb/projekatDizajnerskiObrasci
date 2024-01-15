package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import shapes.CircleWithHole;
import shapes.Point;

public class ModifyCircleWithHoleDialog extends JDialog {

	private JTextField txtX, txtY, txtR, txtHr;
	private JButton btnColor, btnInnerColor;
	private CircleWithHole circleWithHole;
	
	public ModifyCircleWithHoleDialog(CircleWithHole circleWithHole) {
		this.circleWithHole = circleWithHole;
		
		setTitle("Modify Circle With Hole");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 350);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblX = new JLabel("Enter x:");
		lblX.setBounds(10, 15, 170, 25);
		panel.add(lblX);

		JLabel lblY = new JLabel("Enter y:");
		lblY.setBounds(10, 55, 170, 25);
		panel.add(lblY);

		JLabel lblR = new JLabel("Enter r:");
		lblR.setBounds(10, 95, 170, 25);
		panel.add(lblR);

		JLabel lblHr = new JLabel("Enter hole radius:");
		lblHr.setBounds(10, 135, 170, 25);
		panel.add(lblHr);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(10, 175, 170, 25);
		panel.add(lblColor);

		JLabel lblInnerColor = new JLabel("Inner Color:");
		lblInnerColor.setBounds(10, 215, 170, 25);
		panel.add(lblInnerColor);

		txtX = new JTextField();
		txtX.setBounds(200, 15, 224, 25);
		txtX.setText(Integer.toString(circleWithHole.getCenter().getX()));
		panel.add(txtX);
		txtX.setColumns(10);

		txtY = new JTextField();
		txtY.setBounds(200, 55, 224, 25);
		txtY.setText(Integer.toString(circleWithHole.getCenter().getY()));
		panel.add(txtY);
		txtY.setColumns(10);

		txtR = new JTextField();
		txtR.setBounds(200, 95, 224, 25);
		txtR.setText(Integer.toString(circleWithHole.getR()));
		panel.add(txtR);
		txtR.setColumns(10);
		
		txtHr = new JTextField();
		txtHr.setBounds(200, 135, 224, 25);
		txtHr.setText(Integer.toString(circleWithHole.getHoleRadius()));
		panel.add(txtHr);
		txtHr.setColumns(10);
		

		btnColor = new JButton();
		btnColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setColor(btnColor);
			}
		});
		btnColor.setBounds(200, 175, 224, 25);
		btnColor.setBackground(circleWithHole.getColor());
		panel.add(btnColor);

		btnInnerColor = new JButton();
		btnInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setColor(btnInnerColor);
			}
		});
		btnInnerColor.setBounds(200, 215, 224, 25);
		btnInnerColor.setBackground(circleWithHole.getInnerColor());
		panel.add(btnInnerColor);

		JButton btnOk = new JButton("Ok");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (txtX.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "Enter X!", "Error!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (txtY.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "Enter Y!", "Error!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (txtR.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "Enter r!", "Error!", JOptionPane.WARNING_MESSAGE);
					return;
				} 
				else if (txtHr.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "Enter hole radius!", "Error!", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					try {
						int x1 = Integer.parseInt(txtX.getText().trim());
						int y1 = Integer.parseInt(txtY.getText().trim());
						int r1 = Integer.parseInt(txtR.getText().trim());
						int hR1 = Integer.parseInt(txtHr.getText().trim());

						if (x1 <= 0) {
							JOptionPane.showMessageDialog(getContentPane(), "X has to be larger than 0", "Error!",
									JOptionPane.WARNING_MESSAGE);
						} else if (y1 <= 0) {
							JOptionPane.showMessageDialog(getContentPane(), "Y  has to be larger than 0", "Error!",
									JOptionPane.WARNING_MESSAGE);
						} else if (r1 <= 0) {
							JOptionPane.showMessageDialog(getContentPane(), "r has to be larger than 0", "Error!",
									JOptionPane.WARNING_MESSAGE);
						} else if (hR1 <= 0) {
							JOptionPane.showMessageDialog(getContentPane(), "hole radius has to be larger than 0", "Error!",
									JOptionPane.WARNING_MESSAGE);
						} else {
							circleWithHole.setCenter(new Point(x1, y1));
							circleWithHole.setR(r1);
							circleWithHole.setHoleRadius(hR1);
							circleWithHole.setInnerColor(btnInnerColor.getBackground());
							circleWithHole.setColor(btnColor.getBackground());

							dispose();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(getContentPane(), "X, Y, r and hole radius have to be integers!", "Error!",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		btnOk.setBounds(35, 245, 89, 25);
		panel.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnCancel.setBounds(248, 245, 89, 25);
		panel.add(btnCancel);
	}
	
	public void setColor(JButton btnColor) {
		JColorChooser jCCh = new JColorChooser();
		Color col = jCCh.showDialog(null, "Choose Color!", Color.BLACK);
		if (col != null) {
			btnColor.setBackground(col);
		}
	}

	public CircleWithHole getCircleWithHole() {
		return circleWithHole;
	}
}

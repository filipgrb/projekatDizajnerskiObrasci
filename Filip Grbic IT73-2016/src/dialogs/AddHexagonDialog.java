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

import adapter.HexagonAdapter;
import hexagon.Hexagon;
import mvc.Frame;

public class AddHexagonDialog extends JDialog {
	
	private JTextField txtRadius;
	private JButton btnOk = new JButton("Ok");
	private JButton btnCancel = new JButton("Cancel");
	
	private boolean added = false;
	private HexagonAdapter hexagon = new HexagonAdapter(new Hexagon(1, 1, 1),Color.black,Color.BLACK);
	
	public AddHexagonDialog(int x, int y, Frame frame) {
		setTitle("Hexagon Creation");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 375, 150);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblRadius = new JLabel("Enter radius:");
		lblRadius.setBounds(15, 15, 170, 25);
		panel.add(lblRadius);

		txtRadius = new JTextField();
		txtRadius.setBounds(120, 15, 224, 25);
		panel.add(txtRadius);
		txtRadius.setColumns(10);
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtRadius.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "You have to enter radius!", "Error!", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					try {
						int radius = Integer.parseInt(txtRadius.getText());

						if (radius <= 0) {
							JOptionPane.showMessageDialog(getContentPane(), "Radius has to be larger than 0", "Warning",
									JOptionPane.WARNING_MESSAGE);
						} else {
							hexagon.setX(x);
							hexagon.setY(y);
							hexagon.setR(radius);
							hexagon.setColor(frame.getRightFrame().getColorButton().getBackground());
							hexagon.setInnerColor(frame.getRightFrame().getInnerColorButton().getBackground());

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
	
	public HexagonAdapter getHexagon() {
		return hexagon;
	}
}

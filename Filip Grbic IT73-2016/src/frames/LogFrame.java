package frames;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LogFrame extends JPanel {
	
	private JScrollPane scrollPane = new JScrollPane();
	private JList<String> logList;
	private DefaultListModel<String> model = new DefaultListModel<String>();

	public LogFrame() {

		setLayout(new CardLayout());

		logList = new JList<String>(model);
		scrollPane.setViewportView(logList);
		add(scrollPane, "log");
		logList.setBackground(Color.white);
		logList.setLayoutOrientation(JList.VERTICAL);
	}

	public DefaultListModel<String> getModel() {
		return model;
	}
}

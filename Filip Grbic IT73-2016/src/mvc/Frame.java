package mvc;

import javax.swing.JFrame;

import frames.LogFrame;
import frames.RightFrame;
import frames.TopFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame extends JFrame {
	
	private View view = new View();
	private Controller controller;
	
	private TopFrame topFrame = new TopFrame();
	private LogFrame logFrame = new LogFrame();
	private RightFrame rightFrame = new RightFrame();
	
	public Frame() {
		setTitle("Filip Grbic IT73-2016");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		view.setBackground(Color.WHITE);
		
		getContentPane().add(view, BorderLayout.CENTER);
		getContentPane().add(topFrame, BorderLayout.NORTH);
		getContentPane().add(logFrame, BorderLayout.SOUTH);
		getContentPane().add(rightFrame, BorderLayout.EAST);
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		
		rightFrame.getDeleteButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.deleteShapes();
			}
		});
		
		rightFrame.getModifyButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.modifyShape();
			}
		});
		
		rightFrame.getUndoButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.undoClicked();
			}
		});
		
		rightFrame.getRedoButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redoClicked();
			}
		});
		
		rightFrame.getMoveUpButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveUp();
			}
		});
		
		rightFrame.getMoveDownButton().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveDown();		
			}
		});
		
		rightFrame.getMoveToTopButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveTop();
			}
		});
		
		rightFrame.getMoveToBottomButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveBottom();
			}
		});
		
		rightFrame.getSaveButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveSerialization();
			}
		});
		
		rightFrame.getLoadSerButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadSerialization();
				
			}
		});
		
		rightFrame.getSaveLogButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
			}
		});
		
		rightFrame.getLoadButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadLog();
			}
		});
		
		rightFrame.getRedoLogButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redoLog();
			}
		});
	}

	public View getView() {
		return view;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public TopFrame getTopFrame() {
		return topFrame;
	}
	
	public LogFrame getLogFrame() {
		return logFrame;
	}
	
	public RightFrame getRightFrame() {
		return rightFrame;
	}
}

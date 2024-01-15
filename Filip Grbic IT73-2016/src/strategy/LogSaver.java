package strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import frames.LogFrame;
import mvc.Controller;

public class LogSaver implements FileHandler {

	private LogFrame logFrame;
	
	public LogSaver(LogFrame logFrame) {
		this.logFrame = logFrame;
	}
	
	@Override
	public void save() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select a name and a folder");
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt", "text");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showDialog(null, "Save");
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			String path = jfc.getSelectedFile().getPath();
			System.out.println("Saving log in: " + path + "...");

			File f = new File(path + ".txt");

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
				for (int i = 0; i < logFrame.getModel().getSize(); i++) {
					bw.write(logFrame.getModel().getElementAt(i) + "\n");
				}
				
				System.out.println("Log saved successfuly");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void read(String line) {}
}

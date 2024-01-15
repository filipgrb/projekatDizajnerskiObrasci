package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import mvc.Model;
import shapes.Shape;

public class FileSaver implements FileHandler {

	private FileOutputStream fileOutputStream;
	private FileInputStream fileInputStream;
	private Model model;
	
	public FileSaver(Model model) {
		this.model = model;
	}
	
	@Override
	public void save() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select a name and a folder");
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Serializable files", "ser");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showDialog(null, "Save");
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			String path = jfc.getSelectedFile().getPath();
			File f = new File(path + ".ser");

			try {
				fileOutputStream = new FileOutputStream(f);
				ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
				out.writeObject(model.getAll());
				out.close();
				fileOutputStream.close();
			} catch (Exception e) {
				System.out.println("Ne radi serijalizacija!");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void read(String line) {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Serializable files", "ser");
		jfc.setFileFilter(filter);
		
		int returnValue = jfc.showOpenDialog(null);	
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println("Reading from:" + selectedFile.getAbsolutePath() + "...");
		
			try {
				fileInputStream = new FileInputStream(selectedFile);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		        model.addMultiple((ArrayList<Shape>) objectInputStream.readObject());
		        objectInputStream.close();
		        fileInputStream.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}

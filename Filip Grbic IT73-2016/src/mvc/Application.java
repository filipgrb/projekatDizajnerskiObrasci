package mvc;

public class Application {

	public static void main(String[] args) {
		Model model = new Model();
		Frame frame = new Frame();
		frame.getView().setModel(model);
		Controller controller = new Controller(model, frame);
		frame.setController(controller);
		frame.setSize(1000,800);
	}

}

package observer;

import frames.RightFrame;

public class ButtonEnabler implements Observer {
	
	private RightFrame rightFrame;

	public ButtonEnabler(RightFrame rightFrame) {
		this.rightFrame = rightFrame;
	}

	@Override
	public void update(int i) {
		if (i == 0) {
			rightFrame.getDeleteButton().setEnabled(false);
			rightFrame.getMoveToTopButton().setEnabled(false);
			rightFrame.getMoveToBottomButton().setEnabled(false);
			rightFrame.getMoveDownButton().setEnabled(false);
			rightFrame.getMoveUpButton().setEnabled(false);
			rightFrame.getModifyButton().setEnabled(false);
		}
		if (i == 1) {
			rightFrame.getDeleteButton().setEnabled(true);
			rightFrame.getMoveToTopButton().setEnabled(true);
			rightFrame.getMoveToBottomButton().setEnabled(true);
			rightFrame.getMoveDownButton().setEnabled(true);
			rightFrame.getMoveUpButton().setEnabled(true);
			rightFrame.getModifyButton().setEnabled(true);
		}
		if (i > 1) {
			rightFrame.getDeleteButton().setEnabled(true);
			rightFrame.getMoveToTopButton().setEnabled(false);
			rightFrame.getMoveToBottomButton().setEnabled(false);
			rightFrame.getMoveDownButton().setEnabled(false);
			rightFrame.getMoveUpButton().setEnabled(false);
			rightFrame.getModifyButton().setEnabled(false);
		}
	}
}

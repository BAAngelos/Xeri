import javax.swing.JButton;

public class AlwaysLeftAi extends BasicAi implements Runnable {

	public AlwaysLeftAi(boolean first) {
		super(first);
	}

	@Override
	public void run() {
		while (!HelpMethods.matchOver()) {
			
			while (!HelpMethods.gameOver()) {
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {

				}
				if (this.turn) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}

					if (first) {
						((JButton) Board.getInstance().getPlayerHand().getComponent(0)).doClick();
					} else {
						((JButton) Board.getInstance().getOppHand().getComponent(0)).doClick();
					}
					this.turn = false;
				}
			}
			try {
				Board.getInstance().waitForNewGame();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	}

}

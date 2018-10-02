package TheGame;

public class AlwaysLeftAi extends Thread {
	boolean first;
	volatile boolean turn;
	String name;

	public AlwaysLeftAi(boolean goingFirst, String name) {
		if (goingFirst) {
			this.first = true;
			this.turn = false;
		} else {
			this.first = false;
			this.turn = false;
		}
		this.name = name;
		this.setName(name);
	}

	@Override
	public void run() {

		while (!HelpMethods.matchOver()) {

			if (this.turn) {
				Board.getInstance().doMoveForAlwayLeftKi(first, name);
			}

		}

	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

}

package TheGame;

import MonteCarloTreeSearch.*;

public class MonteCarloAI extends Thread {

	boolean first;
	volatile boolean turn;
	String name;
	TreeSearch mcts;

	public MonteCarloAI(boolean goingFirst, String name) {
		if (goingFirst) {
			this.first = true;
			this.turn = false;
		} else {
			this.first = false;
			this.turn = false;
		}
		this.name = name;
		this.setName(name);
		this.mcts = new TreeSearch();
	}

	@Override
	public void run() {

		while (!HelpMethods.matchOver()) {

			if (this.turn) {
				Board.getInstance().doMoveForMCTSKi(mcts.findNextMove("this Board", 1));
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

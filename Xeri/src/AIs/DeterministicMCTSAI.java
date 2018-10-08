package AIs;

import MonteCarloTreeSearch.*;
import TheGame.Board;
import TheGame.HelpMethods;

public class DeterministicMCTSAI extends Thread {

	boolean player;
	volatile boolean turn;
	String name;
	DeterministicTreeSearch mcts;

	public DeterministicMCTSAI(boolean goingFirst, String name) {
		if (goingFirst) {
			this.player = true;
			this.turn = false;
		} else {
			this.player = false;
			this.turn = false;
		}
		this.name = name;
		this.setName(name);
		this.mcts = new DeterministicTreeSearch(player);
	}

	@Override
	public void run() {

		while (!HelpMethods.matchOver()) {

			if (this.turn) {
				Board.getInstance().doMoveForKi(mcts.findNextMove("this Board", 1), player);
			}

		}

	}

	public boolean isPlayer1() {
		return player;
	}

	public void setPlayer1(boolean first) {
		this.player = first;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

}

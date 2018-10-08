package AIs;

import MonteCarloTreeSearch.*;
import TheGame.Board;
import TheGame.HelpMethods;

public class MonteCarloAI extends Thread {

	boolean player;
	volatile boolean turn;
	String name;
	TreeSearch mcts;

	public MonteCarloAI(boolean goingFirst, String name) {
		if (goingFirst) {
			this.player = true;
			this.turn = false;
		} else {
			this.player = false;
			this.turn = false;
		}
		this.name = name;
		this.setName(name);
		this.mcts = new TreeSearch(player);
	}

	@Override
	public void run() {

		while (!HelpMethods.matchOver()) {

			if (this.turn) {
				Board.getInstance().doMoveForKi(mcts.findNextMove("this Board", 1), player);
				this.turn = false;
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

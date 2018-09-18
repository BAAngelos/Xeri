package TheGame;

import MonteCarloTreeSearch.*;

public class MonteCarloAI extends Thread{

	boolean first;
	volatile boolean turn;
	String name;
	TreeSearch mcts;

	public MonteCarloAI(boolean goingFirst, String name) {
			if(goingFirst) {
				this.first = true;
				this.turn = true;
				}
			else {
				this.first = false;
				this.turn = false;
			}
			this.name = name;
			this.setName(name);
			this.mcts = new TreeSearch();
		}

	@Override
	public void run() {
		try {
			Board.getInstance().waitForNewGame();
			System.out.println(name + " Startet jetzt");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (!HelpMethods.matchOver()) {

			while (!HelpMethods.gameOver()) {

				while (!HelpMethods.roundOver()) {
					if (this.first && Board.getInstance().getPlayerHand().getComponentCount() == 0) {
						break;
					} else if (!this.first && Board.getInstance().getOppHand().getComponentCount() == 0) {
						break;
					}
					if (this.turn) {
						Board.getInstance().doMoveForMCTSKi(mcts.findNextMove(Board.getInstance(), 1));
					}

				}

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

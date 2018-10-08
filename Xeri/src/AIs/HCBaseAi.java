package AIs;

import HcBaseAgent.BaseAgent;
import TheGame.Board;
import TheGame.HelpMethods;

public class HCBaseAi extends Thread {

	boolean player;
	volatile boolean turn;
	String name;
	BaseAgent ba;

	public HCBaseAi(boolean player, String name) {
		if (player) {
			this.player = true;
			this.turn = false;
		} else {
			this.player = false;
			this.turn = false;
		}
		this.name = name;
		this.setName(name);
		this.ba = new BaseAgent(player);
	}

	@Override
	public void run() {

		while (!HelpMethods.matchOver()) {

			if (this.turn) {
				try {
					Thread.sleep(700);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Board.getInstance().doMoveForKi(ba.findNextMove(), player);
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

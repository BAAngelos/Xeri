package AIs;

import TheGame.Board;
import TheGame.Card;
import TheGame.HelpMethods;

public class RandomPlayAi extends Thread {
	boolean player;
	volatile boolean turn;
	String name;

	public RandomPlayAi(boolean player, String name) {
		if (player) {
			this.player = true;
			this.turn = false;
		} else {
			this.player = false;
			this.turn = false;
		}
		this.name = name;
		this.setName(name);
	}

	@Override
	public void run() {

		while (!HelpMethods.matchOver()) {

			if (this.turn) {
				Board.getInstance().doMoveForKi(getRandomCardToPlay(), player);
				this.turn = false;
			}

		}

	}

	
	public Card getRandomCardToPlay() {
		Card tmp = null;
		if (player) {
			tmp = (Card) Board.getInstance().getPlayerHand().getComponent((int) (Math.random()*Board.getInstance().getPlayerHand().getComponentCount()));
		}else {
			tmp = (Card) Board.getInstance().getOppHand().getComponent((int) (Math.random()*Board.getInstance().getOppHand().getComponentCount()));
		}
			
		return tmp;
	}
	
	public boolean isFirst() {
		return player;
	}

	public void setFirst(boolean first) {
		this.player = first;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	

}

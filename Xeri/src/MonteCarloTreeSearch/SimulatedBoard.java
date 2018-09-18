package MonteCarloTreeSearch;

import TheGame.*;

public class SimulatedBoard {

	CardPile p1Hand;
	int p2Handsize;

	CardPile p1Collect;
	CardPile p2Collect;

	CardPile field;
	CardPile deck;

	public static final int IN_PROGRESS = -1;
	public static final int DRAW = 0;
	public static final int P1 = 1;
	public static final int P2 = 2;

	public SimulatedBoard() {
		
	}

	public SimulatedBoard(Board board) {
		p1Hand = new CardPile();
		p2Handsize = board.getOppHand().getComponentCount();
		
		for (int i = 0; i < board.getPlayerHand().getComponentCount(); i++) {
			p1Hand.add((Card) board.getPlayerHand().getComponent(i));
		}

		
		p1Collect = board.getPlayPile();
		p2Collect = board.getOppPile();
		field = board.getBoardPile();
		deck = board.getDeck();
	}
	
	public SimulatedBoard(SimulatedBoard sBoard) {
		this.p1Collect = sBoard.getP1Collect();
		this.p2Collect = sBoard.getP2Collect();
		this.p1Hand = sBoard.getP1Hand();
		this.p2Handsize = sBoard.getP2Handsize();
		this.field = sBoard.getField();
		this.deck = sBoard.getDeck();
	}

	public void performMove(int player, Card c) {
		int lastCard = -1;
		if (field.size() > 0) {
			lastCard = field.get(field.size() - 1).getValue();
		}
		int playedCard = c.getValue();

		if (player == P1) {
			p1Hand.remove(c);
		} else if (player == P2) {
			deck.remove(c);
			p2Handsize--;
		}
		field.add(c);

		if (lastCard == playedCard) {
			
			if (player == P1) {
				if(field.size() == 2) {
					p1Collect.addXeri();
				}
				for (int i = 0; i < field.size(); i++) {
					p1Collect.add(field.get(0));
					field.remove(0);
				}
			} else if (player == P2) {
				if(field.size() == 2) {
					p2Collect.addXeri();
				}
				for (int i = 0; i < field.size(); i++) {
					p2Collect.add(field.get(0));
					field.remove(0);
				}
			}
		}
		
		if(playedCard == 11 && field.size() > 1) {
			if (player == P1) {
				for (int i = 0; i < field.size(); i++) {
					p1Collect.add(field.get(0));
					field.remove(0);
				}
			} else if (player == P2) {
				for (int i = 0; i < field.size(); i++) {
					p2Collect.add(field.get(0));
					field.remove(0);
				}
			}
		}

	}

	public int checkStatus() {
		if(p1Hand.size() > 0 || p2Handsize > 0) {
			return -1;
		}else if(p1Hand.size() == 0 && p2Handsize == 0) {
			if(p1Collect.countPoints() > p2Collect.countPoints()) {
				return 1;
			}else if(p1Collect.countPoints() < p2Collect.countPoints()) {
				return 2;
			}
		}
		
		return 0;
	}

	public CardPile getP1Hand() {
		return p1Hand;
	}

	public void setP1Hand(CardPile p1Hand) {
		this.p1Hand = p1Hand;
	}

	public int getP2Handsize() {
		return p2Handsize;
	}

	public void setP2Handsize(int p2Hand) {
		this.p2Handsize = p2Hand;
	}

	public CardPile getP1Collect() {
		return p1Collect;
	}

	public void setP1Collect(CardPile p1Collect) {
		this.p1Collect = p1Collect;
	}

	public CardPile getP2Collect() {
		return p2Collect;
	}

	public void setP2Collect(CardPile p2Collect) {
		this.p2Collect = p2Collect;
	}

	public CardPile getField() {
		return field;
	}

	public void setField(CardPile field) {
		this.field = field;
	}

	public CardPile getDeck() {
		return deck;
	}

	public void setDeck(CardPile deck) {
		this.deck = deck;
	}
	
	

}

package MonteCarloTreeSearch;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.commons.lang3.SerializationUtils;

import TheGame.*;

@SuppressWarnings("serial")
public class SimulatedBoard implements Serializable{

	CardPile p1Hand;
	CardPile p2Hand;
	int p2Handsize;

	CardPile p1Collect;
	CardPile p2Collect;

	CardPile field;
	
	
	
	public static final int IN_PROGRESS = -1;
	public static final int DRAW = 0;
	public static final int P1 = 1;
	public static final int P2 = 2;

	public SimulatedBoard() {
		
	}
/**
 * Constructor for the Deterministic Board; the Opp is given a random hand of 6 Cards	
 * @param player
 */
	public SimulatedBoard(boolean player) {
		if(player){
			p1Hand = new CardPile();
			p2Hand = new CardPile();
			CardPile realP2Hand = new CardPile();
			p2Handsize = Board.getInstance().getOppHand().getComponentCount();
			ArrayList<Integer> randomCards = new ArrayList<Integer>();
			CardPile tmpDeck = SerializationUtils.clone(Board.getInstance().getDeck());
			
			for (int i = 0; i < Board.getInstance().getPlayerHand().getComponentCount(); i++) {
				p1Hand.add((Card) Board.getInstance().getPlayerHand().getComponent(i));
			}
			
			for (int i = 0; i < Board.getInstance().getOppHand().getComponentCount(); i++) {
				realP2Hand.add((Card) Board.getInstance().getOppHand().getComponent(i));
			}
			tmpDeck.addAll(realP2Hand);
			
			for (int i = 0; i < p2Handsize; i++) {
				int randomCard = (int) (Math.random() * (tmpDeck.size()));
				while(randomCards.contains(randomCard)) {
					randomCard = (int) (Math.random() * (tmpDeck.size()));
				}
			randomCards.add(randomCard);
			p2Hand.add(tmpDeck.get(randomCard));
			}
			
			p1Collect = new CardPile();
			p2Collect = new CardPile();
			field = new CardPile(Board.getInstance().getBoardPile());
		}else {
			p1Hand = new CardPile();
			p2Hand = new CardPile();
			CardPile realP2Hand = new CardPile();
			p2Handsize = Board.getInstance().getOppHand().getComponentCount();
			ArrayList<Integer> randomCards = new ArrayList<Integer>();
			CardPile tmpDeck = SerializationUtils.clone(Board.getInstance().getDeck());
			
			for (int i = 0; i < Board.getInstance().getOppHand().getComponentCount(); i++) {
				p1Hand.add((Card) Board.getInstance().getOppHand().getComponent(i));
			}
			
			for (int i = 0; i < Board.getInstance().getPlayerHand().getComponentCount(); i++) {
				realP2Hand.add((Card) Board.getInstance().getPlayerHand().getComponent(i));
			}
			tmpDeck.addAll(realP2Hand);
			
			for (int i = 0; i < p2Handsize; i++) {
				int randomCard = (int) (Math.random() * (tmpDeck.size()));
				while(randomCards.contains(randomCard)) {
					randomCard = (int) (Math.random() * (tmpDeck.size()));
				}
			randomCards.add(randomCard);
			p2Hand.add(tmpDeck.get(randomCard));
			}
			
			p1Collect = new CardPile();
			p2Collect = new CardPile();
			field = new CardPile(Board.getInstance().getBoardPile());
		}
		
	}
	
	
/**This Constructor is for Creating a Non-Deterministic Board
 * where the Opponent could play everycard in the Deck at any time
 * 
 * @param s
 * @param player
 */
	public SimulatedBoard(String s, boolean player) {
		if(player){
			p1Hand = new CardPile();
			CardPile realP2Hand = new CardPile();
			p2Handsize = Board.getInstance().getOppHand().getComponentCount();
			
			for (int i = 0; i < Board.getInstance().getPlayerHand().getComponentCount(); i++) {
				p1Hand.add((Card) Board.getInstance().getPlayerHand().getComponent(i));
			}
			
			for (int i = 0; i < Board.getInstance().getOppHand().getComponentCount(); i++) {
				realP2Hand.add((Card) Board.getInstance().getOppHand().getComponent(i));
			}

			
			p1Collect = new CardPile();
			p2Collect = new CardPile();
			field = new CardPile(Board.getInstance().getBoardPile());
			p2Hand = new CardPile(Board.getInstance().getDeck());
			p2Hand.addAll(realP2Hand);
		}else {
			p1Hand = new CardPile();
			CardPile realP2Hand = new CardPile();
			p2Handsize = Board.getInstance().getPlayerHand().getComponentCount();
			
			for (int i = 0; i < Board.getInstance().getOppHand().getComponentCount(); i++) {
				p1Hand.add((Card) Board.getInstance().getOppHand().getComponent(i));
			}
			
			for (int i = 0; i < Board.getInstance().getPlayerHand().getComponentCount(); i++) {
				realP2Hand.add((Card) Board.getInstance().getPlayerHand().getComponent(i));
			}

			
			p1Collect = new CardPile();
			p2Collect = new CardPile();
			field = new CardPile(Board.getInstance().getBoardPile());
			p2Hand = new CardPile(Board.getInstance().getDeck());
			p2Hand.addAll(realP2Hand);
		}
		
	}
	
	public SimulatedBoard(SimulatedBoard sBoard) { 

		this(sBoard.getP1Collect(), sBoard.getP2Collect(), sBoard.getP1Hand(), sBoard.getP2Handsize(), sBoard.getField(), sBoard.getP2Hand());
	}
	
	public SimulatedBoard(CardPile p1c, CardPile p2c, CardPile p1h, int p2size, CardPile field, CardPile deck) {
		this.p1Collect = p1c;
		this.p2Collect = p2c;
		this.p2Hand = deck;
		this.field = field;
		this.p1Hand = p1h;
		this.p2Handsize = p2size;
	}

	public void performMove(int player, Card c) {
		int lastCard = -1;
		
		if (this.field.size() > 0) {
			lastCard = field.get(field.size() - 1).getValue();
		}
		int playedCard = c.getValue();

		if (player == P1) {
			p1Hand.remove(c);
		} else if (player == P2) {
			p2Hand.remove(c);
			p2Handsize--;
		}
		
		field.add(c);
		int fieldSize = this.field.size();

		if (lastCard == playedCard) {
			
			if (player == P1) {
				if(field.size() == 2) {
					p1Collect.addXeri();
				}
				for (int i = 0; i < fieldSize; i++) {
					p1Collect.add(field.get(0));
					field.remove(0);
				}
			} else if (player == P2) {
				if(field.size() == 2) {
					p2Collect.addXeri();
				}
				for (int i = 0; i < fieldSize; i++) {
					p2Collect.add(field.get(0));
					field.remove(0);
				}
			}
			return;
		}
		
		
		
		if(playedCard == 11 && fieldSize > 1) {
			if (player == P1) {
				for (int i = 0; i < fieldSize; i++) {
					p1Collect.add(field.get(0));
					field.remove(0);
				}
			} else if (player == P2) {
				for (int i = 0; i < fieldSize; i++) {
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
			if(p1Collect.countPointsForRound() > p2Collect.countPointsForRound()) {
				return 1;
			}else if(p1Collect.countPointsForRound() < p2Collect.countPointsForRound()) {
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

	public CardPile getP2Hand() {
		return p2Hand;
	}

	public void setP2Hand(CardPile deck) {
		this.p2Hand = deck;
	}
	
	public String toString() {
		String tmp = "Opp Hand:   ";
		
		for (int i = 0; i < p2Hand.size(); i++) {
			tmp += p2Hand.get(i).toString() + " ; ";
		}
		tmp += "\nField:   ";
		for (int i = 0; i < field.size(); i++) {
			tmp += field.get(i) +" ; ";
		}
		tmp += "\nPlayer Hand: ";
		for (int i = 0; i < p1Hand.size(); i++) {
			tmp += p1Hand.get(i).toString() + " ; ";
		}
		tmp += "\n";
		
		
		return tmp;
		
	}
	

}

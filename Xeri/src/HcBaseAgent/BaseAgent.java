package HcBaseAgent;

import TheGame.Board;
import TheGame.Card;
import TheGame.CardPile;
import TheGame.Suit;

public class BaseAgent {

	boolean player;
	CardPile p1Hand;
	CardPile knownCards;

	public BaseAgent() {

	}

	public BaseAgent(boolean player) {
		this.player = player;
	}
	
	public Card findNextMove() {
		p1Hand = new CardPile();
		CardPile cardsThatTakeTrick = new CardPile();
		
		
		if (player) {
			for (int i = 0; i < Board.getInstance().getPlayerHand().getComponentCount(); i++) {
				p1Hand.add((Card) Board.getInstance().getPlayerHand().getComponent(i));
			}

		} else {
			for (int i = 0; i < Board.getInstance().getOppHand().getComponentCount(); i++) {
				p1Hand.add((Card) Board.getInstance().getOppHand().getComponent(i));
			}
		}
		
		if(Board.getInstance().getBoardPile().size() > 0) {
			Card cardOnBoard = Board.getInstance().getBoardPile().get(Board.getInstance().getBoardPile().size()-1);
			for (Card card : p1Hand) {
				if(card.getValue() == cardOnBoard.getValue()) {
					if((card.getValue() == 10 && card.getSuit() == Suit.DIAMONDS) || (card.getValue() == 2 && card.getSuit() == Suit.CLUBS)) {
						return card;
					}
					cardsThatTakeTrick.add(card);
				}	
			}
		}

		
		if (cardsThatTakeTrick.size() != 0) {
			
			return cardsThatTakeTrick.get((int) (Math.random() * cardsThatTakeTrick.size()));
		}
		
		
		return findeBestCard();
	}

	public Card findeBestCard() {
		CardPile cardsWithLowestChance = new CardPile();
		int lowestChance = 1;


		
		for (Card card : p1Hand) {
			
			int tmpChance = percentOppToHaveCard(card);
			
			if(lowestChance > tmpChance) {
				cardsWithLowestChance.clear();
				cardsWithLowestChance.add(card);
				lowestChance = tmpChance;
			}else if(lowestChance == tmpChance) {
				cardsWithLowestChance.add(card);
			}
			
		}
		
		Card randomCard = cardsWithLowestChance.get((int) (Math.random()*cardsWithLowestChance.size()));

		return randomCard;
	}

	public int percentOppToHaveCard(Card c) {
		knownCards = new CardPile();
		int counter = 0;
		int percentage;

		for (Card card : p1Hand) {
			if(!card.equals(c)) {
				knownCards.add(card);
			}
		}
		
		if (player) {

			for (int i = 0; i < Board.getInstance().getPlayPile().size(); i++) {
				knownCards.add(Board.getInstance().getPlayPile().get(i));
			}
			for (int i = 0; i < Board.getInstance().getOppPile().size(); i++) {
				if (!Board.getInstance().getOppPile().get(i).isFirstThreeCards()) {
					knownCards.add(Board.getInstance().getOppPile().get(i));
				}
			}
		} else {
			for (int i = 0; i < Board.getInstance().getPlayPile().size(); i++) {
				if (!Board.getInstance().getPlayPile().get(i).isFirstThreeCards()) {
					knownCards.add(Board.getInstance().getPlayPile().get(i));
				}
			}
			for (int i = 0; i < Board.getInstance().getOppPile().size(); i++) {

				knownCards.add(Board.getInstance().getOppPile().get(i));

			}
		}
		
		for (int i = 0; i < Board.getInstance().getBoardPile().size(); i++) {
			knownCards.add(Board.getInstance().getBoardPile().get(i));
		}
		
		for (Card card : knownCards) {
			if(card.getValue() == c.getValue()) {
				counter++;
			}
		}
		
		percentage = 1 - (counter/3);
		

		return percentage;
	}

}

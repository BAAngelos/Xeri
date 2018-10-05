package TheGame;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CardPile extends ArrayList<Card> implements Serializable{

	
	
	public CardPile() {
		
	}

	public CardPile(CardPile cp) {
		for (int i = 0; i < cp.size(); i++) {
			Card c = cp.get(i);
			this.add(c);
		}
		
	}
	
	int numberOfXeri = 0;
	
	public void addXeri() {
		numberOfXeri++;
	}
	
	//counts th points there is in a CArdPile
	public int countPoints() {
		int points = 0;
		
		if(this.size() > 26) {
			points += 3;
		}
		
		for (Card card : this) {
			points += card.points;
		}
		return points+10*numberOfXeri;
	}
	
	//counts the points in a cardPile wheer there is a maximum of 12 cards in it
	public int countPointsForRound() {
		int points = 0;
		
		if(this.size() > 0) {
			points += 3;
		}
		
		for (Card card : this) {
			points += card.points;
		}
		return points+10*numberOfXeri;
		
	}
	
	public void resetPile() {
		this.clear();
		numberOfXeri = 0;
	}

	
	
	
	
	public int getNumberOfXeri() {
		return numberOfXeri;
	}

	public void setNumberOfXeri(int numberOfXeri) {
		this.numberOfXeri = numberOfXeri;
	}
	
	public String toString() {
		String tmp = "[";
		for (int i = 0; i < this.size(); i++) {
			tmp += this.get(i) +", ";
		}
		tmp += "]";
		return tmp;
	}
	
	
}

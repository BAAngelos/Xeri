package TheGame;
import java.util.Collections;


public class Deck extends CardPile{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Deck(){
		for (int i = 1; i < 14; i++) {
			this.add(new Card(i, Suit.CLUBS));
			this.add(new Card(i, Suit.DIAMONDS));
			this.add(new Card(i, Suit.SPADES));
			this.add(new Card(i, Suit.HEARTS));
			
		}
		Collections.shuffle(this);
	}
	

	public void firstFourCards() {
		for (int i = 0; i < 4; i++) {
			if(i != 3) {
				Board.getInstance().getDeck().get(0).setFirstThreeCards(true);
			}
			Board.getInstance().getTable().add(Board.getInstance().getDeck().get(0));
			Board.getInstance().getBoardPile().add(Board.getInstance().getDeck().get(0));
			Board.getInstance().getDeck().remove(0);
		}
	}
	
	public String toString() {
		String tmp = "Size of Deck: "+this.size() +" -> [";
		for (int i = 0; i < this.size(); i++) {
			tmp += this.get(i)+", ";
		}
		tmp += "]";
		
		return tmp;
	}
	
	
}

import java.util.Collections;

import javax.swing.JPanel;

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
	
	public void deal(JPanel panel) {
		for (int i = 0; i < 6; i++) {
			if(panel.equals(Board.getInstance().getPlayerHand())) {
				this.get(0).addActionListener(new PlayerHandActionListener());
			}else {
				this.get(0).addActionListener(new OppHandActionListener());
			}
			panel.add(this.get(0));
			this.remove(0);
		}
		
	}
	
	public void firstFourCards() {
		for (int i = 0; i < 4; i++) {
			Board.getInstance().getTable().add(this.get(0));
			Board.getInstance().getBoardPile().add(this.get(0));
			this.remove(0);
		}
	}
	
	
}

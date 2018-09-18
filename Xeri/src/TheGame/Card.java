package TheGame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Card extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Suit suit;
	int value;
	int points;

	Card(int value, Suit suit) {
		this.suit = suit;
		this.value = value;

		// setting the points of individual cards
		switch (value) {
		case 1:
		case 11:
			this.points = 1;
			break;
		case 2:
			if (this.suit.equals(Suit.CLUBS)) {
				this.points = 2;
			}
			break;
		case 10:
			if (this.suit.equals(Suit.DIAMONDS)) {
				this.points = 3;
			}
			break;
		default:
			this.points = 0;
			break;
		}

		// defining the forgroundcolor depending on suits
		switch (this.suit) {
		case CLUBS:
		case SPADES:
			this.setForeground(Color.BLACK);
			break;
		case DIAMONDS:
		case HEARTS:
			this.setForeground(Color.RED);
			break;
		}

		this.setText(this.value + " of " + this.suit);

		switch (value) {
		case 1:
			this.setText("Ace of " + this.suit);
			break;
		case 11:
			this.setText("J of " + this.suit);
			break;
		case 12:
			this.setText("Q of " + this.suit);
			break;
		case 13:
			this.setText("K of " + this.suit);
			break;
		}
		this.setPreferredSize(new Dimension(170, 200));
		this.setMaximumSize(new Dimension(170, 200));
		this.setMinimumSize(new Dimension(170, 200));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);

	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public static void main(String[] agrgs) {

	}

}

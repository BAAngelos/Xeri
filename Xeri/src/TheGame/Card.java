package TheGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.sun.javafx.tk.Toolkit;

public class Card extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Suit suit;
	int value;
	int points;
	boolean upSide;
	boolean firstThreeCards;

	Card(int value, Suit suit) {


		this.suit = suit;
		this.value = value;
		this.upSide = true;
		this.firstThreeCards = false;

		
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
		

//		this.setRigthImage();
		
		this.setPreferredSize(new Dimension(155, 200));
		this.setMaximumSize(new Dimension(155, 200));
		this.setMinimumSize(new Dimension(155, 200));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);



	}
	
	public ImageIcon getImage(String path) {
		Image tmpImage = null;
		try {
			URL imageURL = Card.class.getResource(path);
			tmpImage = ImageIO.read(imageURL);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		Image scaledImage = tmpImage.getScaledInstance(155, 200, java.awt.Image.SCALE_SMOOTH);
		ImageIcon tmpIcon = new ImageIcon(scaledImage);
		return tmpIcon;
	}
	
	public void setRigthImage() {
		switch (value) {
		case 1:
			this.setIcon(getImage("A"+this.suit.getShortCut()+".png"));
			break;
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			this.setIcon(getImage(value+this.suit.getShortCut()+".png"));
			break;
		case 11:
			this.setIcon(getImage("J"+this.suit.getShortCut()+".png"));
			break;
		case 12:
			this.setIcon(getImage("Q"+this.suit.getShortCut()+".png"));
			break;
		case 13:
			this.setIcon(getImage("K"+this.suit.getShortCut()+".png"));
			break;
		}
		
		this.setPreferredSize(new Dimension(155, 200));
		this.setMaximumSize(new Dimension(155, 200));
		this.setMinimumSize(new Dimension(155, 200));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);
	}
	
	public void turnOver() {
		if (upSide) {
//			this.setIcon(getImage("CardBack.png"));
			this.upSide = false;
		}else {
//			this.setRigthImage();
			this.upSide = true;
		}
		
	}
	
	public boolean isEquals(Card c) {
		
		if(c.getValue() == this.value && c.getSuit().equals(suit)) {
			return true;
		}
		return false;
	}

	
	public boolean isFirstThreeCards() {
		return firstThreeCards;
	}


	public void setFirstThreeCards(boolean firstThreeCards) {
		this.firstThreeCards = firstThreeCards;
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
	
	public String toString() {
		return this.getText();
	}


}

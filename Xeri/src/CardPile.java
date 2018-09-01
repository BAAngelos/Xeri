import java.util.ArrayList;

public class CardPile extends ArrayList<Card>{

	int numberOfXeri = 0;
	
	public void addXeri() {
		numberOfXeri++;
	}
	
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
	
	
}

package TheGame;

public enum Suit {

	CLUBS, SPADES, DIAMONDS, HEARTS;
	
	
	public String getShortCut() {
		switch (this) {
		case CLUBS:
			return "C";
		case SPADES:
			return "S";
		case DIAMONDS:
			return "D";
		case HEARTS: 
			return "H";
		default:
			System.out.println("da ist was falsh");
			break;
		}
		
		return null;
	}
}

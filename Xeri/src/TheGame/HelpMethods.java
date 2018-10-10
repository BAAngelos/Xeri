package TheGame;

public class HelpMethods {

	static boolean roundOver = false;
	static boolean gameOver = false;
	static boolean matchOver = false;

	public static boolean isHit() {
		return Board.getInstance().getBoardPile().get(Board.getInstance().getBoardPile().size() - 1).getValue() == Board
				.getInstance().getBoardPile().get(Board.getInstance().getBoardPile().size() - 2).getValue();
	}

	public static boolean playedJack() {
		boolean tmp = false;
		if((Board.getInstance().getBoardPile().size()>1) && Board.getInstance().getBoardPile().get(Board.getInstance().getBoardPile().size() - 1).getValue() == 11) {
			tmp = true;
		}
		return tmp;
	}

	// round Over when all the cards in the hands have been played but not all the
	// cards in the deck
	public static synchronized boolean roundOver() {

		return (Board.getInstance().getOppHand().getComponentCount() == 0
				&& Board.getInstance().getPlayerHand().getComponentCount() == 0);
	}

	// Game Over when all the Cards in the Deck has been played and the points have
	// to be counted
	public static synchronized boolean gameOver() {

		if (Board.getInstance().getOppHand().getComponentCount() == 0
				&& Board.getInstance().getPlayerHand().getComponentCount() == 0
				&& Board.getInstance().getDeck().isEmpty()) {
			gameOver = true;
		} else if (!Board.getInstance().getDeck().isEmpty()) {
			gameOver = false;
		}

		return gameOver;
	}

	// match Over when someone reaches the point goal after several games
	public static synchronized boolean matchOver() {
		return (Board.getInstance().getPlayerPoints() >= Game.getInstance().getGoal()
				|| Board.getInstance().getOppPoints() >= Game.getInstance().getGoal());
	}

	//
	public static synchronized void distributeRestCards() {
		int boardPileSize = Board.getInstance().getBoardPile().size();
		System.out.println(boardPileSize);
			for (int i = 0; i < boardPileSize; i++) {
				if (Game.getInstance().isLastTakenTrick()) {
					Board.getInstance().getPlayPile().add(Board.getInstance().getBoardPile().get(0));
				}else {
					Board.getInstance().getOppPile().add(Board.getInstance().getBoardPile().get(0));
				}
				System.out.println(Board.getInstance().getBoardPile().get(0)+" is removed");
				Board.getInstance().getBoardPile().remove(0);
				Board.getInstance().getTable().remove(0);
			}
	
		Board.getInstance().validate();
	}
}

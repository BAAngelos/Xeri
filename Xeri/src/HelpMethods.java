import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class HelpMethods {

	public static boolean isHit() {
		return 	Board.getInstance().getBoardPile().get(Board.getInstance().getBoardPile().size()-1).getValue() ==
				Board.getInstance().getBoardPile().get(Board.getInstance().getBoardPile().size()-2).getValue();	
	}
	
	public static boolean playedJack() {
		return 	Board.getInstance().getBoardPile().get(Board.getInstance().getBoardPile().size()-1).getValue() == 11;
	}
	
	//round Over when all the cards in the hands have been played but not all the cards in the deck
	public static synchronized boolean roundOver() {
		
		return (Board.getInstance().getOppHand().getComponentCount()== 0 && 
				Board.getInstance().getPlayerHand().getComponentCount() == 0 &&
				!Board.getInstance().getDeck().isEmpty());
	}
	
	//Game Over when all the Cards in the Deck has been played and the points have to be counted
	public static synchronized boolean gameOver() {
		
		return  (Board.getInstance().getOppHand().getComponentCount()== 0 && 
				Board.getInstance().getPlayerHand().getComponentCount() == 0 && 
				Board.getInstance().getDeck().isEmpty());
	}
	
	//match Over when someone reaches the point goal after several games
	public static synchronized boolean matchOver() {
		return (Board.getInstance().getPlayerPoints() >= Game.getInstance().getGoal() ||
				Board.getInstance().getOppPoints() >= Game.getInstance().getGoal());
	}
	

	public static synchronized void distributeRestCards() {
		if(Board.getInstance().getBoardPile().size() > 0 && (Board.getInstance().getBoardPile().size() % 2) == 0) {
			int tmp = Board.getInstance().getBoardPile().size();
			if(Game.getInstance().getKi().isFirst()) {
				for (int i = 0; i < tmp; i++) {
					Board.getInstance().getOppPile().add(Board.getInstance().getBoardPile().get(0));
					Board.getInstance().getBoardPile().remove(0);
					Board.getInstance().getTable().remove(0);
				}
			}else {
				for (int i = 0; i < tmp; i++) {
					Board.getInstance().getPlayPile().add(Board.getInstance().getBoardPile().get(0));
					Board.getInstance().getBoardPile().remove(0);
					Board.getInstance().getTable().remove(0);
				}
			}
		}else {
			int tmp = Board.getInstance().getBoardPile().size();
			if(!Game.getInstance().getKi().isFirst()) {
				for (int i = 0; i < tmp; i++) {
					Board.getInstance().getOppPile().add(Board.getInstance().getBoardPile().get(0));
					Board.getInstance().getBoardPile().remove(0);
					Board.getInstance().getTable().remove(0);
				}
			}else {
				for (int i = 0; i < tmp; i++) {
					Board.getInstance().getPlayPile().add(Board.getInstance().getBoardPile().get(0));
					Board.getInstance().getBoardPile().remove(0);
					Board.getInstance().getTable().remove(0);
				}
			}
		}
		Board.getInstance().validate();
	}
}

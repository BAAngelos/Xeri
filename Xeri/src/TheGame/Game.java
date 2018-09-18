package TheGame;

public class Game implements Runnable {

	public static Game game;
	AlwaysLeftAi ki;
	AlwaysLeftAi ki2;
	MonteCarloAI mctsKi;
	int goal;

	public Game() {
		mctsKi = new MonteCarloAI(true, "MonteCarlso");
		mctsKi.start();
//		ki = new AlwaysLeftAi(true, "player");
//		ki2 = new AlwaysLeftAi(false, "opponent");
//
//		ki2.start();
//		ki.start();
		
		
	}

	@Override
	public void run() {

		while (!HelpMethods.matchOver()) {

			Board.getInstance().resetBoard();
			Board.getInstance().startNewGame();

			while (!HelpMethods.gameOver()) {

				// System.out.println("player xeri -> "+
				// Board.getInstance().getPlayPile().getNumberOfXeri());
				// System.out.println("opp xeri -> "+
				// Board.getInstance().getOppPile().getNumberOfXeri());

				if (HelpMethods.roundOver()) {
					System.out.println("runde ist vorbei--------------------------------------");

					Board.getInstance().getDeck().deal(Board.getInstance().getOppHand());
					Board.getInstance().getDeck().deal(Board.getInstance().getPlayerHand());
					Board.getInstance().validate();
					Board.getInstance().startNewGame();
				}
				

			}

			HelpMethods.distributeRestCards();

			System.out.println("Player: xires -> " + Board.getInstance().getPlayPile().getNumberOfXeri()
					+ " + total Points " + Board.getInstance().getPlayPile().countPoints());
			System.out.println("opp: xires -> " + Board.getInstance().getOppPile().getNumberOfXeri()
					+ " + total Points " + Board.getInstance().getOppPile().countPoints());

			Board.getInstance().setPlayerPoints(
					Board.getInstance().getPlayerPoints() + Board.getInstance().getPlayPile().countPoints());
			Board.getInstance()
					.setOppPoints(Board.getInstance().getOppPoints() + Board.getInstance().getOppPile().countPoints());
			Board.getInstance().updatePoints();

		}

	}

	public static Game getInstance() {
		if (Game.game == null) {
			Game.game = new Game();
		}
		return Game.game;
	}

	public AlwaysLeftAi getKi() {
		return ki;
	}

	public void setKi(AlwaysLeftAi ki) {
		this.ki = ki;
	}

	public AlwaysLeftAi getKi2() {
		return ki2;
	}

	public void setKi2(AlwaysLeftAi ki2) {
		this.ki2 = ki2;
	}

	public int getGoal() {
		return this.goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public static void main(String[] args) {
		Game.getInstance().setGoal(51);
		new Thread(Game.getInstance()).start();
		
	}

}

package TheGame;

public class Game implements Runnable {

	public static Game game;
	AlwaysLeftAi ki;
	AlwaysLeftAi ki2;
	MonteCarloAI mctsKi;
	MonteCarloAI mctsKi2;
	DeterministicMCTSAI dMctski;
	int goal;
	boolean lastTakenTrick; //is true if the player on the bottom played the last Card else false

	/**if you want to change the Ki you have to make following additional changes
	 * -Game.getKi() method should return the rigth Ai
	 * -Player and Opp ActionListener should activate the rigth AI 
	*/
	public Game() {
		dMctski = new DeterministicMCTSAI(false, "test");
		
		mctsKi = new MonteCarloAI(true, "MonteCarlo");
//		mctsKi2 = new MonteCarloAI(false, "OppCarlo");
		mctsKi.start();
		dMctski.start();
//		mctsKi2.start();
		

//		ki = new AlwaysLeftAi(true, "sad");
//		ki2 = new AlwaysLeftAi(false, "asdasd");
		
//		ki.start();
//		ki2.start();
	}

	@Override
	public void run() {

		while (!HelpMethods.matchOver()) {

			Board.getInstance().resetBoard();
//			System.out.println(Board.getInstance().getDeck());
			Board.getInstance().initiateGame();
			Game.getInstance().getMonteCarloKi().setTurn(true);


			while (!HelpMethods.gameOver()) {

				if (HelpMethods.roundOver() && !HelpMethods.gameOver()) {
					System.out.println("runde ist vorbei--------------------------------------");					
					Board.getInstance().deal();
					Board.getInstance().validate();
					Game.getInstance().getMonteCarloKi().setTurn(true);

				}
				

			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
	
	

	public DeterministicMCTSAI getdMctski() {
		return dMctski;
	}


	public MonteCarloAI getMonteCarloKi() {
		return mctsKi;
	}
	
	public MonteCarloAI getMonteCarloKi2() {
		return mctsKi2;
	}
	
	public AlwaysLeftAi getAlwayLeftKi() {
		return this.ki;
	}
	
	public AlwaysLeftAi getAlwayLeftKi2() {
		return ki2;
	}
	
	public void setdMctski(DeterministicMCTSAI dMctski) {
		this.dMctski = dMctski;
	}

	public void setKi(AlwaysLeftAi ki) {
		this.ki = ki;
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
	
	

	public boolean isLastTakenTrick() {
		return lastTakenTrick;
	}

	public void setLastTakenTrick(boolean lastPlayedCard) {
		this.lastTakenTrick = lastPlayedCard;
	}

	public static void main(String[] args) {
		Game.getInstance().setGoal(51);
		new Thread(Game.getInstance()).start();
		
	}

}

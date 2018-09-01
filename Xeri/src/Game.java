
public class Game implements Runnable {

	public static Game game;
	AlwaysLeftAi ki;
	AlwaysLeftAi ki2;
	int goal;

	public Game() {
		System.out.println(Board.getInstance().getDeck().countPoints());
		
	}

	@Override
	public void run() {


		while (!HelpMethods.matchOver()) {

			Board.getInstance().resetBoard();
			Board.getInstance().startNewGame();

			ki = new AlwaysLeftAi(true);
			ki2 = new AlwaysLeftAi(false);
			new Thread(ki2).start();
			new Thread(ki).start();

			while (!HelpMethods.gameOver()) {
				

//				System.out.println("player xeri -> "+ Board.getInstance().getPlayPile().getNumberOfXeri());
//				System.out.println("opp xeri -> "+ Board.getInstance().getOppPile().getNumberOfXeri());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (HelpMethods.roundOver()) {
					Board.getInstance().getDeck().deal(Board.getInstance().getOppHand());
					Board.getInstance().getDeck().deal(Board.getInstance().getPlayerHand());
					Board.getInstance().validate();
				}

			}

			HelpMethods.distributeRestCards();

			System.out.println("Player: xires -> "+Board.getInstance().getPlayPile().getNumberOfXeri()+ " + total Points " + Board.getInstance().getPlayPile().countPoints());
			System.out.println("opp: xires -> "+Board.getInstance().getOppPile().getNumberOfXeri()+ " + total Points " + Board.getInstance().getOppPile().countPoints());

			Board.getInstance().setPlayerPoints( Board.getInstance().getPlayerPoints() + Board.getInstance().getPlayPile().countPoints() );
			Board.getInstance().setOppPoints( Board.getInstance().getOppPoints() + Board.getInstance().getOppPile().countPoints() );
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
		Game.getInstance();
		Game.getInstance().setGoal(21);
		new Thread(Game.getInstance()).start();

	}

}

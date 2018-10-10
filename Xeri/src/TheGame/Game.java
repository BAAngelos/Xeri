package TheGame;

import AIs.*;
import MonteCarloTreeSearch.DTreeSearchWithHeavyPlayout;

public class Game implements Runnable {

	public static Game game;
	RandomPlayAi ki;
	RandomPlayAi ki2;
	MonteCarloAI mctsKi;
	MonteCarloAI mctsKi2;
	DeterministicMCTSAI dMctski;
	DeterministicMCTSAI dMctski2;
	HCBaseAi hcBaseKi;
	HCBaseAi hcBaseKi2;
	DMctsWithHeavyPlayoutAI dMctsWithHPki;
	DMctsWithHeavyPlayoutAI dMctsWithHPki2;

	int goal;
	boolean lastTakenTrick; //is true if the player on the bottom played the last Card else false

	/**if you want to change the Ki you have to make following additional changes
	 * -Game.getKi() method should return the rigth Ai
	 * -Player and Opp ActionListener should activate the rigth AI 
	*/
	public Game() {
//		dMctski = new DeterministicMCTSAI(false, "test");
//		dMctski.start();
//		dMctski2 = new DeterministicMCTSAI(false, "DMCTS OPP");
//		dMctski2.start();
		
		hcBaseKi = new HCBaseAi(true, "HCBase");
		hcBaseKi.start();
//		hcBaseKi2 = new HCBaseAi(false, "HCBaseOpp");
//		hcBaseKi2.start();
		
		
//		mctsKi = new MonteCarloAI(true, "MonteCarlo");
//		mctsKi2 = new MonteCarloAI(false, "OppCarlo");
//		mctsKi.start();
//		mctsKi2.start();
		
//		dMctsWithHPki = new DMctsWithHeavyPlayoutAI(true, "mctsWitchHeavy");
//		dMctsWithHPki.start();
		dMctsWithHPki2 = new DMctsWithHeavyPlayoutAI(false, "Heavy Playout Opp");
		dMctsWithHPki2.start();
		
//		ki = new RandomPlayAi(true, "sad");
//		ki2 = new RandomPlayAi(false, "asdasd");
//		ki.start();
//		ki2.start();
	}

	@Override
	public void run() { 

		while (!HelpMethods.matchOver()) {

			Board.getInstance().resetBoard();
			Board.getInstance().initiateGame();
			Board.getInstance().validate();
//			Game.getInstance().getMonteCarloKi().setTurn(true);
//			Game.getInstance().getRandomPlayKi().setTurn(true);
			Game.getInstance().getHcBaseKi().setTurn(true);
//			Game.getInstance().getdMctsWithHPki().setTurn(true);
			

			while (!HelpMethods.gameOver()) {

				if (HelpMethods.roundOver() && !HelpMethods.gameOver()) {
					System.out.println("runde ist vorbei--------------------------------------");					
					Board.getInstance().deal();
					Board.getInstance().validate();
//					Game.getInstance().getMonteCarloKi().setTurn(true);
//					Game.getInstance().getRandomPlayKi().setTurn(true);
					Game.getInstance().getHcBaseKi().setTurn(true);
//					Game.getInstance().getdMctsWithHPki().setTurn(true);




				}
				

			}
			
			HelpMethods.distributeRestCards();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			

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
	
	
	//-----------------AI Getters-----------------

	public DeterministicMCTSAI getdMctski() {
		return dMctski;
	}
	public DeterministicMCTSAI getdMctski2() {
		
		return this.dMctski2;
	}

	public MonteCarloAI getMonteCarloKi() {
		return mctsKi;
	}
	
	public MonteCarloAI getMonteCarloKi2() {
		return mctsKi2;
	}
	
	public RandomPlayAi getRandomPlayKi() {
		return this.ki;
	}
	
	public RandomPlayAi getRandomPlayKi2() {
		return ki2;
	}
	
	public HCBaseAi getHcBaseKi() {
		return hcBaseKi;
	}
	
	public HCBaseAi getHcBaseKi2(){
		return hcBaseKi2;
	}
	
	public DMctsWithHeavyPlayoutAI getdMctsWithHPki() {
		return dMctsWithHPki;
	}

	public DMctsWithHeavyPlayoutAI getdMctsWithHPki2() {
		return dMctsWithHPki2;
	}

	
	
	//---------------------------------------------




	public void setHcBaseKi(HCBaseAi hcBaseKi) {
		this.hcBaseKi = hcBaseKi;
	}

	public void setdMctski(DeterministicMCTSAI dMctski) {
		this.dMctski = dMctski;
	}

	public void setKi(RandomPlayAi ki) {
		this.ki = ki;
	}



	public void setKi2(RandomPlayAi ki2) {
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

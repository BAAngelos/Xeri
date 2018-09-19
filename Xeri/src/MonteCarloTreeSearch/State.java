package MonteCarloTreeSearch;


import java.util.ArrayList;
import java.util.List;

import TheGame.Card;

public class State {

	SimulatedBoard simulatedBoard;
	int playerNo;
	int visitedCount;
	int winCount;

	public State() {
		simulatedBoard = new SimulatedBoard();
	}

	public State(State state) {
		this.simulatedBoard = new SimulatedBoard(state.getSimulatedBoard());
		this.playerNo = state.getPlayerNo();
		this.visitedCount = state.getVisitCount();
		this.winCount = state.getWinScore();
	}

	public State(SimulatedBoard board) {
		this.simulatedBoard = new SimulatedBoard(board);
	}

	SimulatedBoard getSimulatedBoard() {
		return simulatedBoard;
	}

	void setSimulatedBoard(SimulatedBoard board) {
		this.simulatedBoard = board;
	}

	int getPlayerNo() {
		return playerNo;
	}

	void setPlayerNo(int playerNo) {
		this.playerNo = playerNo;
	}

	int getOpponent() {
		return 3 - playerNo;
	}

	public int getVisitCount() {
		return visitedCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitedCount = visitCount;
	}

	int getWinScore() {
		return winCount;
	}

	void setWinScore(int winScore) {
		this.winCount = winScore;
	}

	void incrementVisit() {
		this.visitedCount++;
	}

	void addScore(double score) {
		if (this.winCount != Integer.MIN_VALUE) {
			this.winCount += score;
		}
	}



	void togglePlayer() {
		this.playerNo = 3 - this.playerNo;
	}

	public void randomPlay() {
		if(playerNo == 1) {
			int cardsInHand = this.simulatedBoard.getP1Hand().size();
//			System.out.println("CardsInHand = "+cardsInHand);// +"\n randomCard = "+randomCard);
//for (int i = 0; i < simulatedBoard.getP1Hand().size(); i++) {
//	System.out.println(simulatedBoard.getP1Hand().get(i));
//}
//System.out.println(simulatedBoard.getP1Hand().size());
			Card randomCard = simulatedBoard.getP1Hand().get((int) (Math.random() * cardsInHand));
			//System.out.println("CardsInHand = "+cardsInHand);// +"\n randomCard = "+randomCard);
			this.simulatedBoard.performMove(this.playerNo, randomCard);
		}else if(playerNo == 2) {
			int possibleCard = this.simulatedBoard.getDeck().size();
			Card randomCard = simulatedBoard.getDeck().get((int) (Math.random() * possibleCard));
			this.simulatedBoard.performMove(this.playerNo, randomCard);
		}
		
		
	}

	public List<State> getAllPossibleStates() {
		List<State> possibleStates = new ArrayList<>();
		if(playerNo == 1) {
			for (int i = 0; i < simulatedBoard.getDeck().size(); i++) {
				State newState = new State(simulatedBoard);
				newState.getSimulatedBoard().performMove(2, newState.getSimulatedBoard().getDeck().get(i));						//Syso
				System.out.println(newState.getSimulatedBoard().toString());
				possibleStates.add(newState);
			}
		}
		if(playerNo == 2) {
			for (int i = 0; i < simulatedBoard.getP1Hand().size(); i++) {
				System.out.println(simulatedBoard.getP1Hand().size());
				State newState = new State(simulatedBoard);
				newState.getSimulatedBoard().performMove(1, newState.getSimulatedBoard().getP1Hand().get(i));		//Syso
				System.out.println(simulatedBoard.toString());
				possibleStates.add(newState);
			}
		}
		
		return possibleStates;
	}
	
	public static void main(String[] args) {
//		Board.getInstace();
	}
}

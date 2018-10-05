package MonteCarloTreeSearch;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import TheGame.Card;

public class State {

	SimulatedBoard simulatedBoard;
	int playerNo;
	int visitedCount;
	int winCount;

	public State() {
		simulatedBoard = new SimulatedBoard();
		visitedCount = 0;
		winCount = 0;
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

	public State(SimulatedBoard board, int player) {
		this.playerNo = player;
		this.simulatedBoard = new SimulatedBoard(board);
	}
	SimulatedBoard getSimulatedBoard() {
		return simulatedBoard;
	}

	void setSimulatedBoard(SimulatedBoard board) {
		this.simulatedBoard = new SimulatedBoard(board);
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
			Card randomCard = this.simulatedBoard.getP1Hand().get((int) (Math.random() * cardsInHand));
			this.simulatedBoard.performMove(this.playerNo, randomCard);
			
		}else if(playerNo == 2) {
			int possibleCard = this.simulatedBoard.getP2Hand().size();
			Card randomCard = simulatedBoard.getP2Hand().get((int) (Math.random() * possibleCard));
			this.simulatedBoard.performMove(this.playerNo, randomCard);
		}
		
		
	}

	public List<State> getAllPossibleStates() {
		List<State> possibleStates = new ArrayList<>();
		if(playerNo == 1) {
			for (int i = 0; i < simulatedBoard.getP2Hand().size(); i++) {
				SimulatedBoard newSim = (SimulatedBoard) SerializationUtils.clone(simulatedBoard);
				State newState = new State(newSim);
				newState.getSimulatedBoard().performMove(2, newState.getSimulatedBoard().getP2Hand().get(i));						//Syso
				possibleStates.add(newState);
			}
		}
		if(playerNo == 2) {
			for (int i = 0; i < simulatedBoard.getP1Hand().size(); i++) {
				SimulatedBoard newSim = (SimulatedBoard) SerializationUtils.clone(simulatedBoard);
				State newState = new State(newSim);
				newState.getSimulatedBoard().performMove(1, newState.getSimulatedBoard().getP1Hand().get(i));
				possibleStates.add(newState);
			}
		}
		
		return possibleStates;
	}
	
	public void add(State s) {
		this.winCount += s.getWinScore();
		this.visitedCount += s.getVisitCount();
	}
	

	public State copyState(State s) {
		State newState = new State();
		newState.setPlayerNo(s.getPlayerNo());
		newState.setSimulatedBoard(s.getSimulatedBoard());
		newState.setVisitCount(s.getVisitCount());
		newState.setWinScore(s.getWinScore());
		
		return newState;
	}
	
	public String toString() {
		String tmp = "";
		tmp += this.getWinScore()+"/"+this.getVisitCount();
		
		return tmp;
	}
}

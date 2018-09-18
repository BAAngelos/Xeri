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
		if (this.winCount != Integer.MIN_VALUE)
			this.winCount += score;
	}

//	    void randomPlay() {
//	        List<Position> availablePositions = this.board.getEmptyPositions();
//	        int totalPossibilities = availablePositions.size();
//	        int selectRandom = (int) (Math.random() * totalPossibilities);
//	        this.board.performMove(this.playerNo, availablePositions.get(selectRandom));
//	    }

	void togglePlayer() {
		this.playerNo = 3 - this.playerNo;
	}

	public void randomPlay() {
		if(playerNo == 1) {
			int cardsInHand = this.simulatedBoard.getP1Hand().size();
			Card randomCard = simulatedBoard.getP1Hand().get((int) (Math.random() * cardsInHand));
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
				newState.getSimulatedBoard().performMove(2, simulatedBoard.getDeck().get(i));
				possibleStates.add(newState);
			}
		}
		if(playerNo == 2) {
			for (int i = 0; i < simulatedBoard.getP1Hand().size(); i++) {
				State newState = new State(simulatedBoard);
				newState.getSimulatedBoard().performMove(1, simulatedBoard.getP1Hand().get(i));
				possibleStates.add(newState);
			}
		}
		
		return possibleStates;
	}
	
	public static void main(String[] args) {
//		Board.getInstace();
	}
}

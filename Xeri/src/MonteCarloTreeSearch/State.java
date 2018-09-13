package MonteCarloTreeSearch;

import java.util.List;

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
		// TODO Auto-generated method stub
		
	}

	public List<State> getAllPossibleStates() {
		// TODO Auto-generated method stub
		return null;
	}
}

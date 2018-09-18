package MonteCarloTreeSearch;

import java.util.List;

import TheGame.Board;

public class TreeSearch {

	private static final int WIN_SCORE = 10;
	private int level;
	private int opponent;

	public TreeSearch() {
		this.level = 3;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	private int getMillisForCurrentLevel() {
		return 8 * (this.level - 1) + 1;
	}

	public   SimulatedBoard findNextMove(Board boardGui, int playerNo) {
		// TODO einstellung von der Zeit die ich geben werde
		SimulatedBoard board = new SimulatedBoard(boardGui);
		long start = System.currentTimeMillis();
		long end = start + 60 * getMillisForCurrentLevel();

		
		opponent = 3 - playerNo;
		Tree tree = new Tree();
		Node rootNode = tree.getRoot();
		rootNode.getState().setSimulatedBoard(board);
		rootNode.getState().setPlayerNo(opponent);

		while (System.currentTimeMillis() < end) {
			// Phase 1 - Selection
			Node promisingNode = selectPromisingNode(rootNode);
			// Phase 2 - Expansion
			if (promisingNode.getState().getSimulatedBoard().checkStatus() == SimulatedBoard.IN_PROGRESS) {
				expandNode(promisingNode);
			}

			// Phase 3 - Simulation
			Node nodeToExplore = promisingNode;
			if (promisingNode.getChildArray().size() > 0) {
				nodeToExplore = promisingNode.getRandomChildNode();
			}
			int playoutResult = simulateRandomPlayout(nodeToExplore);
			// Phase 4 - Update
			backPropogation(nodeToExplore, playoutResult);
		}

		Node winnerNode = rootNode.getChildWithMaxScore();

		return winnerNode.getState().getSimulatedBoard();
	}

	private Node selectPromisingNode(Node rootNode) {
		Node node = rootNode;
		while (node.getChildArray().size() != 0) {
			node = UCT.findBestNodeWithUCT(node);
		}
		return node;
	}

	private void expandNode(Node node) {
		List<State> possibleStates = node.getState().getAllPossibleStates();
		possibleStates.forEach(state -> {
			Node newNode = new Node(state);
			newNode.setParent(node);
			newNode.getState().setPlayerNo(node.getState().getOpponent());
			node.getChildArray().add(newNode);
		});
	}

	private void backPropogation(Node nodeToExplore, int playerNo) {
		Node tempNode = nodeToExplore;
		while (tempNode != null) {
			tempNode.getState().incrementVisit();
			if (tempNode.getState().getPlayerNo() == playerNo) {
				tempNode.getState().addScore(1);
			}
			tempNode = tempNode.getParent();
		}
	}

	private int simulateRandomPlayout(Node node) {
		Node tempNode = new Node(node);
		State tempState = tempNode.getState();
		int boardStatus = tempState.getSimulatedBoard().checkStatus();

		if (boardStatus == opponent) {
			tempNode.getParent().getState().setWinScore(Integer.MIN_VALUE);
			return boardStatus;
		}
		while (boardStatus == SimulatedBoard.IN_PROGRESS) {
			tempState.togglePlayer();
			tempState.randomPlay();
			boardStatus = tempState.getSimulatedBoard().checkStatus();
		}

		return boardStatus;
	}

}

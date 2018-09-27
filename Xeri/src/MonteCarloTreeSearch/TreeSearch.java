package MonteCarloTreeSearch;

import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import TheGame.Card;

public class TreeSearch {

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

//	private int getMillisForCurrentLevel() {
//		return 15 * (this.level - 1) + 1;
//	}

	public   Card findNextMove(String thisBoard, int playerNo) {
		// TODO einstellung von der Zeit die ich geben werde
		SimulatedBoard board = new SimulatedBoard("this Board");
		long start = System.currentTimeMillis();
		@SuppressWarnings("unused")
		long end = start + 4000;

		
		opponent = 3 - playerNo;
		Tree tree = new Tree();
		Node rootNode = tree.getRoot();
		rootNode.getState().setSimulatedBoard(board);
		rootNode.getState().setPlayerNo(opponent);
		

		while (System.currentTimeMillis() < end) { //end
			// Phase 1 - Selection
			Node promisingNode = selectPromisingNode(rootNode);

			// Phase 2 - Expansion
			if (promisingNode.getState().getSimulatedBoard().checkStatus() == SimulatedBoard.IN_PROGRESS) {
				expandNode(promisingNode);
			}

			System.out.println(promisingNode.getState().getSimulatedBoard().getP1Hand().size()+" in findeNExtMove");

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
		
		
		Card cardToPlay = null;
		
		for (int i = 0; i < winnerNode.getParent().getState().getSimulatedBoard().getP1Hand().size(); i++) {
			if(!winnerNode.getParent().getState().getSimulatedBoard().getP1Hand().contains(winnerNode.getState().getSimulatedBoard().getP1Hand().get(i))) {
				cardToPlay = winnerNode.getState().getSimulatedBoard().getP1Hand().get(i);
			}
		}		

		return cardToPlay;
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
	
		int player = node.getState().getPlayerNo();
		SimulatedBoard tmpSim = (SimulatedBoard) SerializationUtils.clone(node.getState().getSimulatedBoard());
		State tempState = new State(tmpSim, player);
		int boardStatus = tempState.getSimulatedBoard().checkStatus();

//		if (boardStatus == opponent) {
//			tempNode.getParent().getState().setWinScore(Integer.MIN_VALUE);
//			return boardStatus;
//		}
		while (boardStatus == SimulatedBoard.IN_PROGRESS) {
			tempState.togglePlayer();
			tempState.randomPlay();
			boardStatus = tempState.getSimulatedBoard().checkStatus();
		}

		return boardStatus;
	}

}

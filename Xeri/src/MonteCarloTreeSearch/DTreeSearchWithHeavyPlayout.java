package MonteCarloTreeSearch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import TheGame.Card;

public class DTreeSearchWithHeavyPlayout {

	private boolean player;
	// The player attribute determines for wich side th AI should play ->
	// true=bottom ; false=top
	private int opponent;

	public DTreeSearchWithHeavyPlayout(boolean player) {
		this.player = player;
	}

	public Card findNextMove(String thisBoard, int playerNo) {
		SimulatedBoard endBoard = new SimulatedBoard(player);
		Node endRootNode = new Node();
		endRootNode.getState().setSimulatedBoard(endBoard);
		endRootNode.getState().setPlayerNo(2);
		expandNode(endRootNode);
		

		for (int i = 0; i < 10; i++) {
			int countSimulations = 0;
			SimulatedBoard board = new SimulatedBoard(player);
			opponent = 3 - playerNo;
			Tree tree = new Tree();
			Node rootNode = tree.getRoot();
			rootNode.getState().setSimulatedBoard(board);
			rootNode.getState().setPlayerNo(opponent);
			
			
			while (countSimulations < 100) { 
				System.out.println("Heavy Playout");
				countSimulations++;
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
				int playoutResult = simulateHeavyPlayout(nodeToExplore);
				// Phase 4 - Update
				backPropogation(nodeToExplore, playoutResult);
			}

			for (int j = 0; j < rootNode.getChildArray().size(); j++) {
				endRootNode.getChildArray().get(j).getState().add(rootNode.getChildArray().get(j).getState());
			}
			
		}

		Node winnerNode = endRootNode.getChildWithMaxScore();

		Card cardToPlay = null;
		ArrayList<Card> parentList = new ArrayList<Card>(winnerNode.getParent().getState().getSimulatedBoard().getP1Hand());
		ArrayList<Card> winnerList = new ArrayList<Card>(winnerNode.getState().getSimulatedBoard().getP1Hand());

		for (Card card : winnerList) {
			for (Card cardJ : parentList) {
				if(card.isEquals(cardJ)) {
					parentList.remove(cardJ);
					break;
				}
			}
		}
		cardToPlay = parentList.get(0);
		
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

	private int simulateHeavyPlayout(Node node) {

		int player = node.getState().getPlayerNo();
		SimulatedBoard tmpSim = (SimulatedBoard) SerializationUtils.clone(node.getState().getSimulatedBoard());
		State tempState = new State(tmpSim, player);
		int boardStatus = tempState.getSimulatedBoard().checkStatus();

		 if (boardStatus == opponent) {
		 node.getParent().getState().setWinScore(Integer.MIN_VALUE);
		 return boardStatus;
		 }
		while (boardStatus == SimulatedBoard.IN_PROGRESS) {
			tempState.togglePlayer();
			tempState.cleverPlay();
			boardStatus = tempState.getSimulatedBoard().checkStatus();
		}

		return boardStatus;
	}

}

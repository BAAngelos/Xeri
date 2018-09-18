package MonteCarloTreeSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
	State state;
	Node parent;
	ArrayList<Node> childArray;

	public Node() {
		this.state = new State();
		childArray = new ArrayList<Node>();
	}

	public Node(State state) {
		this.state = state;
		childArray = new ArrayList<>();
	}

	public Node(State state, Node parent, ArrayList<Node> childArray) {
		this.state = state;
		this.parent = parent;
		this.childArray = childArray;
	}

	public Node(Node node) {
		this.childArray = new ArrayList<>();
		this.state = new State(node.getState());
		if (node.getParent() != null) {
			this.parent = node.getParent();
		}
		List<Node> childArray = node.getChildArray();
		for (Node child : childArray) {
			this.childArray.add(new Node(child));
		}
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public ArrayList<Node> getChildArray() {
		return this.childArray;
	}

	public void setChildArray(ArrayList<Node> childArray) {
		this.childArray = childArray;
	}

	public Node getRandomChildNode() {
		int noOfPossibleMoves = this.childArray.size();
		int selectRandom = (int) (Math.random() * noOfPossibleMoves);
		return this.childArray.get(selectRandom);
	}

	public Node getChildWithMaxScore() {
        return Collections.max(this.childArray, Comparator.comparing(c -> {
            return c.getState().getVisitCount();
        }));
}

}

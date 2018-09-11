
public class BasicAi extends Thread{
	boolean first;
	volatile boolean turn;
	
	public BasicAi(boolean goingFirst) {
		if(goingFirst) {
			this.first = true;
			this.turn = true;
			}
		else {
			this.first = false;
			this.turn = false;
		}
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	
	
}

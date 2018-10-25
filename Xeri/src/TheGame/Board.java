package TheGame;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class Board extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Board board;

	Deck deck;
	CardPile oppPile;
	CardPile playPile;
	CardPile boardPile;

	JPanel oppHand;
	JPanel playerHand;
	JPanel contentPane;
	JPanel pointDisplay;

	JLabel playerPointLabel;
	JLabel oppPointLabel;

	JScrollPane tableScrollPane;
	JPanel table;

	int playerPoints = 0;
	int oppPoints = 0;

	GridBagConstraints c = new GridBagConstraints();

	public Board() {

//		deck = new Deck();
//		oppPile = new CardPile();
//		playPile = new CardPile();
//		boardPile = new CardPile();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		oppHand = new JPanel();
		oppHand.setLayout(new BoxLayout(oppHand, BoxLayout.LINE_AXIS));
		// oppHand.setPreferredSize(new Dimension(1300, 300));
		oppHand.setMinimumSize(new Dimension(1300, 200));

		playerHand = new JPanel();
		playerHand.setLayout(new BoxLayout(playerHand, BoxLayout.LINE_AXIS));
		// playerHand.setPreferredSize(new Dimension(1300, 300));
		playerHand.setMinimumSize(new Dimension(1300, 200));

		table = new JPanel();
		table.setLayout(new BoxLayout(table, BoxLayout.LINE_AXIS));
		table.setPreferredSize(new Dimension(800, 200));
		// table.setMinimumSize(new Dimension(800, 200));

		tableScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tableScrollPane.getHorizontalScrollBar().setValue(tableScrollPane.getHorizontalScrollBar().getMaximum());
		tableScrollPane.setMaximumSize(new Dimension(170 * 4, 250));
		tableScrollPane.setMinimumSize(new Dimension(800, 250));
		tableScrollPane.setPreferredSize(new Dimension(170 * 4, 250));
		tableScrollPane.setVisible(true);

		playerPointLabel = new JLabel("You have: " + playerPoints + "   ");
		oppPointLabel = new JLabel("Opp has: " + oppPoints + "   ");

		pointDisplay = new JPanel();
		pointDisplay.setLayout(new BoxLayout(pointDisplay, BoxLayout.PAGE_AXIS));
		pointDisplay.add(playerPointLabel);
		pointDisplay.add(Box.createVerticalGlue());
		pointDisplay.add(oppPointLabel);

		// setting the frame
		contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		contentPane.setSize(new Dimension(1500, 800));
		contentPane.add(oppHand, c);
		c.gridy = 2;
		contentPane.add(playerHand, c);
		c.gridy = 1;
		contentPane.add(tableScrollPane, c);
		c.gridx = 1;
		contentPane.add(pointDisplay, c);
		contentPane.setVisible(true);

		this.setTitle("Kseri");
		this.setContentPane(contentPane);
		this.setSize(new Dimension(1500, 800));
		this.setLocation(20, 10);
		this.setVisible(true);

	}

	public static void checkTrick(String who) {
		int tmp = Board.getInstance().getBoardPile().size();

		if (tmp > 1) {
			if (HelpMethods.isHit() || HelpMethods.playedJack()) {

				if (who.equals("opp")) {
					if (tmp == 2 && !HelpMethods.playedJack()) {
						Board.getInstance().getOppPile().addXeri();
					}
					for (int i = 0; i < tmp; i++) {
						Board.getInstance().getOppPile().add(Board.getInstance().getBoardPile().get(0));
						Board.getInstance().getTable().remove(Board.getInstance().getBoardPile().get(0));
						Board.getInstance().getBoardPile().remove(0);

					}
					Board.getInstance().getTable().setPreferredSize(new Dimension(400, 200));
					Board.getInstance().getTableScrollPane().getHorizontalScrollBar().setValue(0);
					Game.getInstance().setLastTakenTrick(false);

				} else if (who.equals("player")) {
					if (tmp == 2 && !HelpMethods.playedJack()) {
						Board.getInstance().getPlayPile().addXeri();
					}
					for (int i = 0; i < tmp; i++) {
						Board.getInstance().getPlayPile().add(Board.getInstance().getBoardPile().get(0));
						Board.getInstance().getTable().remove(Board.getInstance().getBoardPile().get(0));
						Board.getInstance().getBoardPile().remove(0);

					}
					Board.getInstance().getTable().setPreferredSize(new Dimension(400, 200));
					Board.getInstance().getTableScrollPane().getHorizontalScrollBar().setValue(0);
					Game.getInstance().setLastTakenTrick(true);
				}
			}
		}
	}
	
	public void deal() {
		for (int i = 0; i < 6; i++) {
			Board.getInstance().getDeck().get(0).addActionListener(new PlayerHandActionListener());
			Board.getInstance().getPlayerHand().add(Board.getInstance().getDeck().get(0));
			Board.getInstance().getDeck().remove(0);
			
			Board.getInstance().getDeck().get(0).addActionListener(new OppHandActionListener());
			Board.getInstance().getDeck().get(0).turnOver();
			Board.getInstance().getOppHand().add(Board.getInstance().getDeck().get(0));
			Board.getInstance().getDeck().remove(0);
		}

		
	}

	public void initiateGame() {

		Board.getInstance().deal();
		Board.getInstance().getDeck().firstFourCards();

	}

	public static Board getInstance() {
		if (Board.board == null) {
			Board.board = new Board();
		}
		return Board.board;
	}

	public void updatePoints() {
		playerPointLabel.setText("You have: " + playerPoints + "   ");
		oppPointLabel.setText("Opp has: " + oppPoints + "   ");
		Board.getInstance().validate();
	}

	public void resetBoard() {
		Board.getInstance().setDeck(new Deck());
		Board.getInstance().setOppPile(new CardPile());
		Board.getInstance().setPlayPile(new CardPile());
		Board.getInstance().setBoardPile(new CardPile());

	}

	public synchronized void waitForNewGame() throws InterruptedException {

		System.out.println("Thread wartet");
		wait();
		System.out.println("Thread startet");
	}

	public synchronized void startNewGame() {
		System.out.println("ich starte Das spiel -Game");
		notifyAll();

	}


	
	public synchronized void doMoveForKi(Card c, boolean player) {
		if(player) {
			System.out.println(c);
			int CardsInHand = Board.getInstance().getPlayerHand().getComponentCount();
			for (int i = 0; i < CardsInHand; i++) {
				if(Board.getInstance().getPlayerHand().getComponent(i).equals(c)) {
					 ((AbstractButton) Board.getInstance().getPlayerHand().getComponent(i)).doClick();
					 try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(Board.getInstance().getPlayerHand().getComponentCount() == 0 && Board.getInstance().getOppHand().getComponentCount() == 0 && Board.getInstance().getDeck().size() == 0) {
							Game.switchSide = true;
					}
					 return;
				}
				
			}
		}else {
			int CardsInHand = Board.getInstance().getOppHand().getComponentCount();
			for (int i = 0; i < CardsInHand; i++) {
				if(Board.getInstance().getOppHand().getComponent(i).equals(c)) {
					 ((AbstractButton) Board.getInstance().getOppHand().getComponent(i)).doClick();
					 try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					 if(Board.getInstance().getPlayerHand().getComponentCount() == 0 && Board.getInstance().getOppHand().getComponentCount() == 0 && Board.getInstance().getDeck().size() == 0) {
							Game.switchSide = true;
					 }
					 return;
				}
			}
		}

	}
	
	public synchronized void notifyThread() {
		notify();
	}

	public synchronized Deck getDeck() {
		return Board.getInstance().deck;
	}

	public synchronized void setDeck(Deck deck) {
		Board.getInstance().deck = deck;
	}

	public synchronized CardPile getOppPile() {
		return oppPile;
	}

	public synchronized void setOppPile(CardPile oppPile) {
		this.oppPile = oppPile;
	}

	public synchronized CardPile getPlayPile() {
		return playPile;
	}

	public synchronized void setPlayPile(CardPile playPile) {
		this.playPile = playPile;
	}

	public synchronized CardPile getBoardPile() {
		return boardPile;
	}

	public synchronized void setBoardPile(CardPile boardPile) {
		this.boardPile = boardPile;
	}

	public synchronized JPanel getOppHand() {
		return oppHand;
	}

	public synchronized void setOppHand(JPanel oppHand) {
		this.oppHand = oppHand;
	}

	public synchronized JPanel getPlayerHand() {
		return playerHand;
	}

	public synchronized void setPlayerHand(JPanel playerHand) {
		this.playerHand = playerHand;
	}

	public synchronized JPanel getPointDisplay() {
		return pointDisplay;
	}

	public synchronized void setPointDisplay(JPanel pointDisplay) {
		this.pointDisplay = pointDisplay;
	}

	public synchronized JLabel getPlayerPointLabel() {
		return playerPointLabel;
	}

	public synchronized void setPlayerPointLabel(JLabel playerPointLabel) {
		this.playerPointLabel = playerPointLabel;
	}

	public synchronized JLabel getOppPointLabel() {
		return oppPointLabel;
	}

	public synchronized void setOppPointLabel(JLabel oppPointLabel) {
		this.oppPointLabel = oppPointLabel;
	}

	public synchronized JScrollPane getTableScrollPane() {
		return tableScrollPane;
	}

	public synchronized void setTableScrollPane(JScrollPane tableScrollPane) {
		this.tableScrollPane = tableScrollPane;
	}

	public synchronized JPanel getTable() {
		return table;
	}

	public synchronized void setTable(JPanel table) {
		this.table = table;
	}

	public synchronized int getPlayerPoints() {
		return playerPoints;
	}

	public synchronized void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;
	}

	public synchronized int getOppPoints() {
		return oppPoints;
	}

	public synchronized void setOppPoints(int oppPoints) {
		this.oppPoints = oppPoints;
	}


}

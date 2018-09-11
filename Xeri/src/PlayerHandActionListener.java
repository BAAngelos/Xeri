import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerHandActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		Board.getInstance().getTable().setPreferredSize(new Dimension(Board.getInstance().getTable().getWidth()+170, 200));
		Board.getInstance().getTable().add((Card)e.getSource());
		Board.getInstance().getTableScrollPane().getHorizontalScrollBar().setValue(Board.getInstance().getTableScrollPane().getHorizontalScrollBar().getMaximum());
		Board.getInstance().getPlayerHand().remove((Card)e.getSource());
		Board.getInstance().getBoardPile().add((Card) e.getSource());
		Board.checkTrick("player");
		
		
		
		
		Game.getInstance().getKi2().setTurn(true);
		((Card)e.getSource()).removeActionListener(this);
		Board.getInstance().validate();
		Board.getInstance().notifyThread();
		
		
	}

}

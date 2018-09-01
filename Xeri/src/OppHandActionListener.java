import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OppHandActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
	
		Board.getInstance().getTable().setPreferredSize(new Dimension((int) Board.getInstance().getTable().getPreferredSize().getWidth()+170,200));
		Board.getInstance().getTable().add((Card)e.getSource());
		Board.getInstance().getTableScrollPane().getHorizontalScrollBar().setValue(Board.getInstance().getTableScrollPane().getHorizontalScrollBar().getMaximum());
		Board.getInstance().getOppHand().remove((Card)e.getSource());
		Board.getInstance().getBoardPile().add((Card) e.getSource());
		Board.checkTrick("opp");
		
		
		Game.getInstance().getKi().setTurn(true);
		((Card)e.getSource()).removeActionListener(this);
		Board.getInstance().validate();

	}
	

}

import javax.swing.*;

@SuppressWarnings("serial")
public class Player extends Cell{

	public Player(String fileName){
		setIcon(new ImageIcon(fileName));
		
	}
	
	public void move(int dRow, int dCol) {
		
		setRow(getRow() + dRow);
		setCol(getCol() + dCol);
	}
}


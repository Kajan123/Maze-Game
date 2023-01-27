import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Cell extends JLabel {
	
	private int row;
	private int col;
	
	public Cell() {
		
	}

	public Cell(int row, int col){
		
		super();
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row){
		this.row = row;
		
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public String toString() {
		return "Cell [row=" + row + ", col=" + col + "]";
	}
	
}

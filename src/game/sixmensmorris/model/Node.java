package game.sixmensmorris.model;

public class Node {
	
	private static final String INVALID_SET = "Warning: token overflow.";
	private static final String INVALID_TOSS = "Warning: null-token delete.";

	private int row;
	private int column;
	private int radius;
	private Piece piece;

	public Node(char column, char row, int radius) {
		this.column = column;
		this.row = row;
		this.radius = radius;
	}

	public int getRow() 		{ return row; 	 }
	public int getColumn() 		{ return column; }
	public int getRadius()		{ return radius; }
	public Piece getPiece() 	{ return piece;  }
	public boolean isEmpty()	{ return piece == null; }
	public boolean remove() 	{ if (piece == null) return false; 
								  piece = null; return true; }
	

	public void setPiece(Piece t) {
		if (piece != null && t != null) { piece = t; pieceOverFlow(t, this); }
		else if (piece == null && t == null) nullPieceDelete(this);
		else piece = t;
	}
	
	public String toString() { 
		if (piece == null) return (char)column + "" + (int)row + "[ ]"; 
		return (char)column + "" + (int)row + "[" + piece.toString() + "]"; }
	
	private static void pieceOverFlow(Piece t, Node n) {
		System.out.println(INVALID_SET + " At: " + n.toString());
	}
	
	private static void nullPieceDelete(Node n) {
		System.out.println(INVALID_TOSS + " At: " + n.toString());
	}
}

package game.sixmensmorris.model;

public class Node {
	
	private static final String INVALID_SET = "Warning: token overflow.";
	private static final String INVALID_TOSS = "Warning: null-token delete.";

	private int row;
	private int column;
	private int radius;
	private Piece token;

	public Node(char column, char row, int radius) {
		this.column = column;
		this.row = row;
		this.radius = radius;
	}

	public int getRow() 		{ return row; 	 }
	public int getColumn() 		{ return column; }
	public int getRadius()		{ return radius; }
	public Piece getToken() 	{ return token;  }
	public boolean isEmpty()	{ return token == null; }
	public boolean remove() 	{ if (token == null) return false; 
								  token = null; return true; }
	

	public void setToken(Piece t) {
		if (token != null && t != null) { token = t; tokenOverFlow(t, this); }
		else if (token == null && t == null) nullTokenDelete(this);
		else token = t;
	}
	
	public String toString() { 
		if (token == null) return (char)column + "" + (int)row + "[ ]"; 
		return (char)column + "" + (int)row + "[" + token.toString() + "]"; }
	
	private static void tokenOverFlow(Piece t, Node n) {
		System.out.println(INVALID_SET + " At: " + n.toString());
	}
	
	private static void nullTokenDelete(Node n) {
		System.out.println(INVALID_TOSS + " At: " + n.toString());
	}
}

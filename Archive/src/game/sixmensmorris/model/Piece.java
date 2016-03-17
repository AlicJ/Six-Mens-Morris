package game.sixmensmorris.model;

public class Piece {
	
	private int id;
	
	public Piece(int id) {
		this.id = id;
	}
	
	public int id() { return id; }
	public String toString() { return Integer.toString(id); }

}

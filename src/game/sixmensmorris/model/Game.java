package game.sixmensmorris.model;

import java.util.ArrayList;

public class Game {
	
	private final int PLAYER_PIECES = 6;
	
	private GameField field;
	private Player redPlayer;
	private Player bluePlayer;
	
	public Game(int n) {
		field = new GameField(n);
		redPlayer = new Player(PLAYER_PIECES, 0);
		bluePlayer = new Player(PLAYER_PIECES, 1);
	}
	
	public boolean movePiece(char c1, char r1, char c2, char r2) {
		return field.move(c1, r1, c2, r2);
	}
	
	// Added movePiece() method that takes Nodes instead of coordinates
	public boolean movePiece(Node node1, Node node2) {
		char c1, r1, c2, r2;
		
		c1 = (char)node1.getColumn();
		r1 = (char)node1.getRow();
		c2 = (char)node2.getColumn();
		r2 = (char)node2.getRow();
		
		return field.move(c1, r1, c2, r2);
	}
	
	public boolean setPiece(char column, char row, Player player) {
		return field.assign(column, row, player.takePiece());
	}

	// Added setPiece() method that takes a Node instead of coordinates
	public boolean setPiece(Node node, Player player) {
		return field.assign(node, player.takePiece());
	}
	
	public boolean removePiece(char column, char row) {
		return field.assign(column, row, null);
	}
	
	public Player player(int id)	{
		if (id == 0)
			return this.redPlayer;
		else
			return this.bluePlayer;
	}
	
	// Abrogated. Though, still functional, use if necessary.
	public int checkPiece(char column, char row) {
		if (field.node(column, row) == null) 
			return -1;
		return field.node(column, row).getPiece().id();
	}
	
	// Use this to draw all nodes.
	public ArrayList<Node> state() {
		return field.nodes();
	}
	
	// Returns node at index i
	public Node getNode(int i)
	{
		return field.getNode(i);
	}
	
	public String toString() {
		return field.toString();
	}
}

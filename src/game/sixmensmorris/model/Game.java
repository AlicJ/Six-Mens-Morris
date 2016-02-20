package game.sixmensmorris.model;

import java.util.ArrayList;

public class Game {
	
	private final int PLAYER_PIECES = 6;
	
	private GameField field;
	private Player player;
	private Player computer;
	
	public Game(int n) {
		field = new GameField(n);
		player = new Player(PLAYER_PIECES, 0);
		computer = new Player(PLAYER_PIECES, 1);
	}
	
	public boolean movePiece(char c1, char r1, char c2, char r2) {
		return field.move(c1, r1, c2, r2);
	}
	
	public boolean setPiece(char column, char row, Player player) {
		return field.assign(column, row, player.takePawn());
	}
	
	public boolean removePiece(char column, char row) {
		return field.assign(column, row, null);
	}
	
	public Player player() {
		return this.player;
	}
	
	public Player computer() {
		return this.computer;
	}
	
	// Abrogated. Though, still functional, use if necessary.
	public int checkPiece(char column, char row) {
		if (field.node(column, row) == null) 
			return -1;
		return field.node(column, row).getToken().id();
	}
	
	// Use this to draw all nodes.
	public ArrayList<Node> state() {
		return field.nodes();
	}
	
	public String toString() {
		return field.toString();
	}
}

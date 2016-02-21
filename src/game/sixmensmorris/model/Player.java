package game.sixmensmorris.model;

import java.util.ArrayList;

public class Player {
	
	private static final String STACK_UNDERFLOW = "Warning: stack is empty.";
	
	private ArrayList<Piece> pawns;
	private int id;
	
	public Player(int numberOfPawns, int playerID) {
		
		pawns = new ArrayList<Piece>();
		
		for (int i = 0; i < numberOfPawns; i++) {
			Piece t = new Piece(playerID);
			pawns.add(t);
		}
	}
	
	public int ID () { return id; }
	
	public Piece takePawn() {
		if (pawns.get(0) != null) {
			Piece pawn = pawns.get(0); 
			pawns.remove(0);
			return pawn;
		}
		else {
			stackUnderFlowWarning(this);
		}
		return null;
	}
	
	public Piece pawnAt(int i) {
		if (pawns.get(i) != null) {
			return pawns.get(0); 
		}
		else {
			stackUnderFlowWarning(this);
		}
		return null;
	}
	
	private static void stackUnderFlowWarning(Player p) {
		System.out.println(STACK_UNDERFLOW + " Player: " + p.ID() );
		System.out.println("   >> stack returns a null Token" );
		System.out.println("   >> @move or @set placed a null Token on field.");
	}

}

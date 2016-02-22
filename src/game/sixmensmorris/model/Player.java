package game.sixmensmorris.model;

import java.util.ArrayList;

public class Player {
	
	private static final String STACK_UNDERFLOW = "Warning: stack is empty.";
	
	private ArrayList<Piece> pieces;
	private int id;
	
	public Player(int numberOfPieces, int playerID) {
		
		pieces = new ArrayList<Piece>();
		
		for (int i = 0; i < numberOfPieces; i++) {
			Piece t = new Piece(playerID);
			pieces.add(t);
		}
	}
	
	public int ID () { return id; }
	
	public Piece takePiece() {
		try {
			Piece piece = pieces.get(0); 
			pieces.remove(0);
			return piece;
		}	catch (IndexOutOfBoundsException e) {
			stackUnderFlowWarning(this);
		}
		return null;
	}
	
	// Returns a Piece from stack without removing it
	// Added by Hasan
	public Piece piece(int i) {
		try {
			return pieces.get(i); 
		}	catch (IndexOutOfBoundsException e) {
			stackUnderFlowWarning(this);
		}
		return null;
	}
	
	// Returns whether or not the player has any more bench pieces
	// Added by Hasan
	public boolean hasPiece()
	{
		try
		{
			pieces.get(0);
		}
		catch (IndexOutOfBoundsException e)
		{
			return false;
		}

		return true;
	}
	
	private static void stackUnderFlowWarning(Player p) {
		System.out.println(STACK_UNDERFLOW + " Player: " + p.ID() );
		System.out.println("   >> stack returns a null Piece" );
		System.out.println("   >> @move or @set placed a null Piece on field.");
	}

}

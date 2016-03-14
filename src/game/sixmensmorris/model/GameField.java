package game.sixmensmorris.model;

import java.util.ArrayList;

/**
 * Compilation: 	javac GameField.java
 * Execution:		java GameField
 * Dependencies:	none.
 * Data files:		none.
 * 
 * The GameField class handles all of the Token allocation, Token movement
 * logic and Token win/loose condition checks.
 *  
 * @author Danish
 *
 */
public class GameField {

	// Warning Labels
	private static final String NODE_NOT_FOUND = "Warning: node not found.";
	private static final String INVALID_MOVE = "Warning: move not registered.";
	
	// Enable to monitor console details of significant events.
	private static final boolean DEBUG_MODE = false;
	
	// MORRIS service number for this board.
	private int n; 
	
	private ArrayList<Node> field;

	/**
	 * Instantiates a new GameField.
	 * @param n - the MORRIS number from which the board and rules are created.
	 */
	public GameField(int n) {
	// Instantiates a new field for any given n. Note: win condition behave
	// unpredictably when n is not a valid MORRIS service number. In such cases,
	// a warning will is displayed.
		this.n = n;
		field = field(n);
		print(this.toString());
	}
	
	/**
	 * Moves a Piece from one Node N1 in GameField to another Piece N2. A warning
	 * label is raised if a move() results in a Piece being moved to N2 on which
	 * a Piece is already placed or one that is too far. Likewise, a warning will be
	 * raised if a move() is called but a Piece is not present to be moved in N1
	 * @param c1 - column number of N1.
	 * @param r1 - row number of N1.
	 * @param c2 - column number of N2.
	 * @param r2 - row number of N2.
	 * @return true if move was successful. False otherwise.
	 */
	public boolean move(char c1, char r1, char c2, char r2) {
	// Checks validity of move and performs it if valid, prints error if invalid
		
		// Calculate distance of given move, added by Hasan
		int moveDistance = Math.abs(c2 - c1) + Math.abs(r2 - r1);
		int validDistance = 1;
		
		// Calculate last column/row number
		int last = 2*n / 3;
		
		// If origin and destination are both on outer square, valid move distance is 2.
		if ((c1 == 'A' && c2 == 'A') || (c1 == 'A' + last && c2 == 'A' + last) ||
				(r1 == 0 && r2 == 0) || (r1 == last && r2 == last))
		{
			validDistance = 2;
		}
		
		try {
			if (!(node(c1, r1).isEmpty()) && node(c2, r2).isEmpty() && (moveDistance == validDistance)) {
				Piece token = node(c1, r1).getPiece();
				assign(c2, r2, token);
				assign(c1, r1, null);
				promptMove(c1, r1, c2, r2, true);
				return true;
			}
		} catch (NullPointerException e) {
			//e.printStackTrace();
		}
		
		promptMove(c1, r1, c2, r2, false); // custom error message.
		return false;
	}
	
	/**
	 * Assigns a Piece to a Node in GameField class.
	 * @param column - column number of the Node to assign the Piece to.
	 * @param row - row number of the Node to assign the Piece to.
	 * @param piece - the piece to be assigned.
	 * @return true if move was successful. False otherwise.
	 */
	public boolean assign(char column, char row, Piece piece) {
	// The following method assigns a token to a particular node given a column 
	// and row and returns true if the assignment was successful.
		for (Node n : field) {
			if (n.getColumn() == column && n.getRow() == row) {
				if (piece == null && n.isEmpty() == false) {
					n.setPiece(null);
					return true;
				} 
				else if (piece != null && n.isEmpty() == true) {
					n.setPiece(piece);
					return true;
				}
			}
		}
		promptNodeNotFound(column, row, "assign():boolean");
		return false;
	}
	
	/**
	 * Assigns a Piece to a Node in GameField class.
	 * @param node - the Node to assign the Piece to.
	 * @param player - the Player from which to take the Piece from.
	 * @return true if move was successful. False otherwise.
	 */
	public boolean assign(Node node, Piece piece) {
	// The following method assigns a token to a given node
	//  and returns true if the assignment was successful.
		if (piece != null && node.isEmpty() == true) {
			node.setPiece(piece);
			return true;
		}
		
		// Change error prompt?
		promptNodeNotFound((char)node.getColumn(), (char)node.getRow(), "assign():boolean");
		return false;
	}
	
	/**
	 * Returns a Node with the specified row and column number. Raises a 
	 * warning if the Node if the specified row and column do not result in a
	 * a Node being found in this GameField.
	 * @param column
	 * @param row
	 * @return
	 */
	public Node node(char column, char row) {
		for (Node n : field) {
			if (n.getColumn() == column && n.getRow() == row) {
				return n;
			}
		}
		promptNodeNotFound(column, row, "node():Node");
		return null;
	}
	
	/**
	 * Returns an array based on a any number N. If the N is not a valid MORRIS
	 * number, an incomplete field will be returned.
	 * @param n - the MORRIS number with which to label the array.
	 * @return ArrayList<Node> with appropriate labels too accommodate N-M.M.
	 */
	public ArrayList<Node> field(int n) {
	// The following method will create and generate a board for any valid 
	// service MORRIS number. A MORRIS number is valid if n >= 6 and n % 3 = 0. 
	// This method calculates and assigns Nodes (token carrier objects) a column 
	// tag and a row tag, based on the given MORRIS number.

		validateGameBoard(n);
		
		int middle = ((2*n / 3) + 1)/2; // refers to the middle column number
		int radius; 
		
		char column = '?'; 	// initialized to '?' for error detection
		char row = '?';		// initialized to '?' for error detection
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		// assigns row elements first, then assigns column elements as required
		for (int i = 0; i < (2 * n / 3) + 1; i++) {
			
			if(DEBUG_MODE) print("i=" + i + "\t"); // debug
			
			column = (char) (i + 'A');
			radius = Math.abs(middle - i);
			
			if (i != middle) {
				
				for (int k = 0; k < 3; k++) {
					
					row = (char)(radius*k + Math.abs(middle - radius) ); 
					
					 // debug
					if (DEBUG_MODE) {
						System.out.print((char)column + "" + (int)row);
					}
					
					Node node = new Node(column, row, radius);
					nodes.add(node);
					
				}
				
				if (DEBUG_MODE) print("\n"); // debug
				
			}
			
			else {
				
				for (int k = 0; k < (2 * n / 3) + 1; k++) {

					row = (char)(k);
					if (row != i) {
						
						// debug
						if (DEBUG_MODE) { 
							System.out.print((char)column + "" + (int)row); 
						}
						Node node = new Node(column, row, radius);
						nodes.add(node);
					}
					
				}
				if (DEBUG_MODE) print("\n"); // debug
				
			}
		}
		
		// check array, labels and assignments (debug purposes):
		if (DEBUG_MODE) {
			for(Node y : nodes) {
				System.out.println(y.toString()); 
			}
		}
		return nodes; // return generated field based on MORRIS number n
	}
	
//	/**
//	 * Returns the current state of this GameField in the form of an array of
//	 * Nodes.
//	 * @return Node array representing the current state of this GameField.
//	 */
//	public ArrayList<Node> nodes() {
//	// Returns the current state of this abstract data through a list of all
//	// nodes within this GameField object. Future Note: a sting array copy sent 
//	// instead of this is highly recommended.
//		return this.field;
//	}
	
	/**
	 * Returns the node at a specific position in the array of nodes
	 * @param i - index at which to take node from
	 * @return Node array representing the current state of this GameField.
	 */
	public Node getNode(int i) {
	// Returns node at i index in ArrayList
		return this.field.get(i);
	}
	
	/**
	 * Returns the string representation of this GameField.
	 * @return the string representation of this GameField.
	 */
	public String toString() {
	// Returns a string representation of the GameField Abstract Data Type.
		String board = "\n"+field.get(0).toString();
		
		for( int i = 1; i < field.size(); i++ ) {
			if (!( field.get(i).getColumn() == field.get(i-1).getColumn())) {
				board += "\n"; 
			}
			board += field.get(i).toString();
		}
		
		return board + "\n";
	}
	
	// Called when a particular location call results in a Node not being found.
	private void promptNodeNotFound(char column, char row, String caller) {
		print(NODE_NOT_FOUND + "\n");
		print("   >> called by: "  + caller + "\n");
		print("   >> searched for: " + (char)column + (int)row + "\n");
	}

	// Called when the move() function was used illegally.
	private void promptMove(char c1, char r1, char c2, char r2, boolean valid) {
		if (valid) print("Moved: " + c1 + (int) r1 + " to " + c2 + (int) r2 + "\n");
		else {
			print(INVALID_MOVE + "\n");
			print("   >> tried: " +c1 + (int)r1 + " to " + c2 + (int)r2 + "\n");
		}
	}

	// Warns user if an invalid MORRIS number board was generated. Fields
	// generated with an Invalid MORRIS number will lead to unpredictable
	// win condition behaviour or may result in an incomplete board.
	private void validateGameBoard(int n) {
		if (n < 6 || n % 3 != 0) 
			print("Warning: unconventional morris number...");
		print("Creating " + n + " men's morris field...\n");
	}

	// Aesthetic and coding optimization function. Does not affect program.
	private static void print(String s) {
		System.out.print(s);
	}
}

package game.sixmensmorris.model;

import java.util.ArrayList;

public class GameField {

	private static final String NODE_NOT_FOUND = "Warning: node not found.";
	private static final String INVALID_MOVE = "Warning: move not registered.";
	
	private static final boolean DEBUG_MODE = false;
	
	private int n; // MORRIS service number for this board.
	
	private ArrayList<Node> field;

	public GameField(int n) {
	// Instantiates a new field for any given n. Note: win condition behave
	// unpredictably when n is not a valid MORRIS service number. In such cases,
	// a warning will is displayed.
		this.n = n;
		field = field(n);
		print(this.toString());
	}
	
	public boolean move(char c1, char r1, char c2, char r2) {
		try {
			if (!(node(c1, r1).isEmpty()) && node(c2, r2).isEmpty()) {
				Piece token = node(c1, r1).getPiece();
				assign(c2, r2, token);
				assign(c1, r1, null);
				promptMove(c1, r1, c2, r2, true);
				return true;
			}
		} catch (NullPointerException e) {
			//e.printStackTrace();
			promptMove(c1, r1, c2, r2, false); // custom error message.
		}
		return false;
	}
	
	public boolean assign(char column, char row, Piece token) {
	// The following method assigns a token to a particular node given a column 
	// and row and returns true if the assignment was successful.
		for (Node n : field) {
			if (n.getColumn() == column && n.getRow() == row) {
				if (token == null && n.isEmpty() == false) {
					n.setPiece(null);
					return true;
				} 
				else if (token != null && n.isEmpty() == true) {
					n.setPiece(token);
					return true;
				}
			}
		}
		promptNodeNotFound(column, row, "assign():boolean");
		return false;
	}
	
	public Node node(char column, char row) {
		for (Node n : field) {
			if (n.getColumn() == column && n.getRow() == row) {
				return n;
			}
		}
		promptNodeNotFound(column, row, "node():Node");
		return null;
	}
	
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
	
	public ArrayList<Node> nodes() {
	// Returns the current state of this abstract data through a list of all
	// nodes within this GameField object. Future Note: a sting array copy sent 
	// instead of this is highly recommended.
		return this.field;
	}
	
	public Node getNode(int i) {
	// Returns node at i index in ArrayList
		return this.field.get(i);
	}
	
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
	
	private void promptNodeNotFound(char column, char row, String caller) {
	// Called when a particular location call results in a Node not being found.
		print(NODE_NOT_FOUND + "\n");
		print("   >> called by: "  + caller + "\n");
		print("   >> searched for: " + (char)column + (int)row + "\n");
	}
	
	private void promptMove(char c1, char r1, char c2, char r2, boolean valid) {
		if (valid) print("Moved: " + c1 + (int) r1 + " to " + c2 + (int) r2);
		else {
			print(INVALID_MOVE + "\n");
			print("   >> tried: " +c1 + (int)r1 + " to " + c2 + (int)r2 + "\n");
		}
	}
	
	private void validateGameBoard(int n) {
	// Warns user if an invalid MORRIS number board was generated. Fields
	// generated with an Invalid MORRIS number will lead to unpredictable
	// win condition behaviour or may result in an incomplete board.
		if (n < 6 || n % 3 != 0) 
			print("Warning: unconventional morris number...");
		print("Creating " + n + " men's morris field...\n");
	}
	
	private static void print(String s) {
	// Esthetic and coding optimization function. Does not affect program.
		System.out.print(s);
	}
	
//	public static void main (String[] args) {
//		GameField field = new GameField(9);
//		Piece p1 = new Piece(0);
//		Piece p2 = new Piece(0);
//		Piece p3 = new Piece(0);
//		Piece c1 = new Piece(1);
//		field.assign('A', (char)0, p1);
//		field.assign('B', (char)1, p2);
//		field.assign('C', (char)2, p3);
//		field.assign('D', (char)3, p1); // Error
//		field.assign('E', (char)4, c1);
//		field.assign('F', (char)5, c1);
//		field.assign('G', (char)6, c1);
//		field.assign('G', (char)9, c1); // Error
//		field.move('B', (char)1, 'E', (char) 22);
//		System.out.println(field);
//		System.out.println(field);
//		field.move('A', (char)0, 'D', (char) 6);
//		System.out.println(field);
//		field.move('A', (char)0, 'D', (char) 6);
//		System.out.println(field);
//		field.move('A', (char)0, 'D', (char) 6);
//		System.out.println(field);
//		field.move('D', (char)0, 'E', (char) 2);
//		System.out.println(field);
//	}
}

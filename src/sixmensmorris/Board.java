package sixmensmorris;
/**
 * This is an abstract representation of the game board. 
 * It keeps the state of each piece in a 1 dimensional array in order to reduce run time and space.
 * 
 * Note that by using modular arithmetic, we can represent the the Board as a 1 dimensional array. Pieces are adjacent to pieces
 * that are beside it in the array, and 8 spots ahead or behind it. This fact can be used in order to determine which pieces to check
 * to determine a victory condition in the future, as well as where a certain piece can move in the gameplay state.
 * 
 * @author Kelvin Lin, Jeremy Klotz
 * @version 1
 */
public class Board {

	private int N;								//Total number of pieces on the board
	private final int NUM_PIECES_PER_LAYER = 8;
	private int[] pieces;
	
	/**	 
	 * Constructs an array representation of the board.
	 * @param N Number of squares needed for the board.
	 * @param N determines if 6, 9, 12 Men's Morris is being played, and allows for easy change.
	 */
	public Board(int N){
		this.N = N;
		pieces = new int[N * NUM_PIECES_PER_LAYER];
	}
	
	/**
	 * Constructs an array representation of the board given a preset state.
	 * @param N Number of squares
	 * @param pieces is a pre-determined number of pieces (this also allows for simple change in game logic)
	 */
	public Board(int N, int[] pieces){
		this.N = N;
		this.pieces = pieces;
	}
	
	/**
	 * Initializes the pieces array.
	 * @param pieces is an integer array of positions to place pieces
	 * allow for access to number of squares, and piece array for custom functions
	 */
	public void setPieces(int[] pieces){
		this.pieces = pieces;
	}
	
	/**
	 * Returns the number of squares on the board.
	 * @return number of squares of the board
	 */
	public int getN(){
		return this.N;
	}
	
	/**
	 * Set the state of a piece on the board
	 * @param number will help us declare what state the board is in (0 = not started, 1 = play mode , 2 = debug mode)
	 * @param state will be the value we get as a result of calling this method
	 */
	public void setPieceState(int number, int state){
		this.pieces[number] = state;
	}
	
	/**
	 * Return the current state of the board
	 * @return the current state of the board 
	 */
	public int[] getBoardState(){
		return this.pieces;
	}
	
	/**
	 * Return the current state of the piece (black, red or blue)
	 * @param number is an index that will help determine the piece state
	 * @return the current state of the piece (black, red or blue)
	 */
	public int getPieceState(int number){
		return this.pieces[number];
	}
	
	public int[] millExists(int i){
		int[] mill = new int[3];
		int currentState = this.pieces[i];
		if(i % 2 == 0){ //Check even position
			if( (pieces[(i+1)%8 + ((i > 7)?8:0)] == currentState) && ((i%8 == 0)?(pieces[7 + ((i > 7)?8:0)] == currentState):(pieces[(i-1)%8 + ((i > 7)?8:0)] == currentState)) ){
				mill[0] = i;
				mill[1] = i+1;
				mill[2] = (i%8 == 0)?(7+ ((i > 7)?8:0) ):(i-1);
			} else{ //No mills exist
				mill[0] = -1;
				mill[1] = -1;
				mill[2] = -1;
			}
		} else{ //Odd position
			if(pieces[(i+1)%8 + ((i > 7)?8:0)] == currentState && pieces[(i+2)%8 + ((i > 7)?8:0)] == currentState){
				mill[0] = i;
				mill[1] = i+1;
				mill[2] = i+2;
			} else if((pieces[(i-1)%8 + ((i > 7)?8:0)] == currentState) && ((i%8 == 1)?(pieces[7 + ((i > 7)?8:0)] == currentState):(pieces[(i-2)%8 + ((i > 7)?8:0)] == currentState))){
				mill[0] = i;
				mill[1] = i-1;
				mill[2] = (i%8 == 1)? (7+ ((i > 7)?8:0)):(i-2);
			} else{ //No mill found
				mill[0] = -1;
				mill[1] = -1;
				mill[2] = -1;
			}
		}
		return mill;
	}
	
	public boolean onlyMillsLeft(int colour){
		boolean onlyMillsLeft = true;
		for(int i = 0; i < pieces.length; i++){
			if(pieces[i] == colour){
				int[] mill = millExists(i);
				onlyMillsLeft = onlyMillsLeft && mill[0] == -1;
			}
		}
		return onlyMillsLeft;
	}
	
}

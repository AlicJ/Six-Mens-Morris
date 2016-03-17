package sixmensmorris;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This is a controller for the board class. 
 * It acts as an intermediary between the Board model (Board.java), and the Board view (BoardView.java).
 * @author Kelvin Lin , Jeremy Klotz
 * @version 1
 */
public class BoardController extends JFrame {
	private JFrame jFrame;
	private BoardView boardView;
	private int turn; // 0 = blue, 1 = red
	private Player blue, red;

	private int state = 0; // 0 = place pieces, 1 = play game

	private final int NUMBER_OF_PIECES = 6;	// this can change to 9 if we are going to do 9 Men's Morris instead
	private final int BLUE_STATE = 1;
	private final int RED_STATE = 2;

	private final int FONT_SIZE = 25; // declaring a size for the font used in the application

	private final int DEFAULT_SCREEN_WIDTH = 500; // default width and height of screen (will scale if stretch/compress window)
	private final int DEFAULT_SCREEN_HEIGHT = 500;

	private JLabel blueLabel, blueCount, redLabel, redCount, cLabel, cCount; // some labels to properly update the view
	
	/**
	 * Constructs the screen needed to play the game, and adds all EventListeners needed to obtain input from the user.
	 * @param N is the number of squares
	 * @return framework for the game, manipulates the model to know how to update the view
	 */
	public BoardController(int N) {
		//Instantiate Random Turns
		Random random = new Random();
		turn = random.nextInt(2);

		// Instantiate Models
		blue = new Player(BLUE_STATE, NUMBER_OF_PIECES);
		red = new Player(RED_STATE, NUMBER_OF_PIECES);

		// Instantiate Views
		jFrame = new JFrame("Six Men's Morris");
		jFrame.setSize(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT); // (500,500)
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Box box = Box.createHorizontalBox(); // original box to contain all of the view's information
		Box blueVerticalBox = Box.createVerticalBox();
		Box redVerticalBox = Box.createVerticalBox();
		Font font = new Font(Font.MONOSPACED, Font.PLAIN,
				this.jFrame.getWidth() * FONT_SIZE / this.DEFAULT_SCREEN_WIDTH); // scale the font based on window size 
																				 // incase it is streched/compressed
		
		blueLabel = new JLabel("Blue:"); // use scalable font for all labels relevant to the blue player										 
		blueLabel.setFont(font);
		blueCount = new JLabel(String.valueOf(blue.getNumberOfUnplayedPieces())); // the label accesses the number of pieces left
																				  // and returns the integer value as a string for the label
		blueCount.setFont(font);		// use scalable font
		//create a sub box to be place in the window
		//add previously define labels to the sub box, and add this sub box to the original box
		blueVerticalBox.add(blueLabel);	
		blueVerticalBox.add(blueCount);
		box.add(blueVerticalBox);

		boardView = new BoardView(N); // call BoardView to display graphics
		box.add(boardView);		      // add graphics to the original box
		
		// creating the same labels needed for player blue, but this time for player red
		redLabel = new JLabel("Red:");
		redLabel.setFont(font);
		redCount = new JLabel(String.valueOf(red.getNumberOfUnplayedPieces()));
		redCount.setFont(font);
		
		redVerticalBox.add(redLabel);
		redVerticalBox.add(redCount);
		box.add(redVerticalBox);

		jFrame.add(box); // add the original box (everything) to the window
		jFrame.setVisible(true); // allows the window to display everything

		boardView.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				updateView();
				resizeText();
			}
		});

		boardView.addMouseListener(new MouseClickEventHandler());
		

	}
	
	/**
	 * Construct the screen needed to play the game given a certain state, and adds all EventListers needed to obtain input from the user.
	 * @author Kelvin Lin , Jeremy Klotz
	 * @param N is the number of squares 
	 * @param boardState this allows us to construct / update the board based on current state
	 */
	public BoardController(int N, int[] boardState) { 
		Random random = new Random();
		turn = random.nextInt(2);

		
		int bluePieces = 0, redPieces = 0;
		//this chunk of code adds up all of the pieces for each player
		//is applicable for any version of 6 Men's Morris, only the length of the board state should change
		for(int i = 0; i < boardState.length; i++){
			if(boardState[i] == BLUE_STATE){
				bluePieces++;
			} else if(boardState[i] == RED_STATE){
				redPieces++;
			}
		}
		
		// Instantiate Models by creating players with a state and number of pieces
		blue = new Player(BLUE_STATE, NUMBER_OF_PIECES-bluePieces);
		red = new Player(RED_STATE, NUMBER_OF_PIECES-redPieces);

		// Instantiate View by creating the gameplay window
		jFrame = new JFrame("Six Men's Morris");
		jFrame.setSize(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// every time a piece is added to the board, the window is updated, recalling the constructor
		
		// repeated code
		Box box = Box.createHorizontalBox();

		Font font = new Font(Font.MONOSPACED, Font.PLAIN,
				this.jFrame.getWidth() * FONT_SIZE / this.DEFAULT_SCREEN_WIDTH);

		blueLabel = new JLabel("Blue:");
		blueLabel.setFont(font);
		blueCount = new JLabel(String.valueOf(blue.getNumberOfUnplayedPieces()));
		blueCount.setFont(font);
		Box blueVerticalBox = Box.createVerticalBox();
		blueVerticalBox.add(blueLabel);
		blueVerticalBox.add(blueCount);
		box.add(blueVerticalBox);

		boardView = new BoardView(N);
		box.add(boardView);

		redLabel = new JLabel("Red:");
		redLabel.setFont(font);
		redCount = new JLabel(String.valueOf(red.getNumberOfUnplayedPieces()));
		redCount.setFont(font);
		Box redVerticalBox = Box.createVerticalBox();
		redVerticalBox.add(redLabel);
		redVerticalBox.add(redCount);
		box.add(redVerticalBox);

		jFrame.add(box);
		jFrame.setVisible(true);
	
		boardView.addComponentListener(new ComponentAdapter() { // add a component listener to the view 
			public void componentResized(ComponentEvent e) {	// so that it can continuously update and resize the text 
				updateView();
				resizeText();
			}
		});

		boardView.addMouseListener(new MouseClickEventHandler()); // add a mouselistener to the view
		for(int i = 0; i < boardState.length; i++){				  // so that when a click occurs, the board changes from one state to another
			boardView.setBoardState(i, boardState[i]);
		}
	}
	
	/**
	 * It is used to simply resize the text based on the dimensions of the window
	 * This allows for dynamic change, such that the user can play with any size of window
	 */
	private void resizeText() {
		Font font = new Font(Font.MONOSPACED, Font.PLAIN,
				this.jFrame.getWidth() * FONT_SIZE / this.DEFAULT_SCREEN_WIDTH);
		blueLabel.setFont(font);
		blueCount.setFont(font);
		redLabel.setFont(font);
		redCount.setFont(font);
	}
	
	/**
	 * This method will update the labels of each player involved.
	 */
	private void updateLabels() {
		blueCount.setText(String.valueOf(blue.getNumberOfUnplayedPieces()));
		redCount.setText(String.valueOf(red.getNumberOfUnplayedPieces()));
	}
	
	/**
	 * Controller takes information from the view and calls methods from the java.awt library on it
	 */
	private void updateView() {
		boardView.invalidate(); // invalidates the board
		boardView.repaint(); 	// repaints the visual component of the view.
	}
	
	/**
	 * @author Kelvin Lin
	 * @version 1:
	 * Controls what happens when a piece is clicked on the screen
	 */
	private class MouseClickEventHandler implements MouseListener {
		
		@Override
		/**
		 * This method allows for alternate colour pieces to be placed on the board after each click.
		 * @param e is the mouse being click
		 */
		public void mouseClicked(MouseEvent e) { 
			Point point = new Point(e.getPoint().getX(), e.getPoint().getY()); // get the coordinates of the click
			Circle[] circles = boardView.getCircles(); // create an array that holds all circles from the board
			// iterate through the circle array
			// if the mouse is clicked on a point and is not occupied by another coloured circle
			// place a coloured circle based on the current state
			for(int i = 0; i < circles.length; i++){   
				if(circles[i].isMouseOver(point)){
					if(state == 0){
						switch(turn%2){
						case 0:
							if(boardView.pieceNotTaken(i)){
								if(blue.getNumberOfUnplayedPieces() > 0){
									boardView.setBoardState(i, 1);
									blue.placePiece();
								}
								turn++; //Incrementing and decrementing turn at every subsequent turn ensures that there will never be overflow.
							}
							break;
						case 1:
							if(boardView.pieceNotTaken(i)){
								if(red.getNumberOfUnplayedPieces() > 0){
									boardView.setBoardState(i, 2);
									red.placePiece();
								}
								turn--;
							}
							break;
						}
						updateLabels();
						updateView();
						if(red.getNumberOfUnplayedPieces() == 0 && blue.getNumberOfUnplayedPieces() == 0){
							state = 1;
						}
					} else if(state == 1){
						//Play Game Logic Here
					}
				}
					
			}
		}
		// other methods that could be used if we decide to use different mouse events.
		// currently they are empty.
		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

	}

}
package sixmensmorris;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * Defines a view for the menu screen.
 * This is the first GUI that appears that the user can interact with.
 * @author Kelvin Lin, Jeremy Klotz
 * @version 1
 */
public class MenuView extends Screen{	
	
	private JLabel title;
	
	// User will click one of these two buttons to choose a state to enter.
	// Either debugging state, then go to play state, OR go straight to the play state.
	private JButton playGame;
	private JButton debug;
	private int state;
	private int defaultFontSize = 36;
	private int defaultScreenWidth = 500;
	private int N; //Total number of pieces on the board
	
	/**
	 * Method to construct the output that the controller will handle everything inside of it.
	 * @param N	The number of pieces on the board
	 */
	public MenuView(int N){
		
		this.N = N;
		
		title = new JLabel("Six Men's Morris"); // title 
		
		playGame = new JButton("Play Game");
		playGame.addMouseListener(new MouseAdapter(){
			/**
			 * Determine if the playGame button was pressed.
			 */
			public void mouseClicked(MouseEvent e){
				playGameMouseClicked(e);
			}
		});	 		
		debug = new JButton("Debug");
		
		/**
		 * Determine if the Debug button was pressed
		 */
		debug.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				debugMouseClicked(e);
			}
		});	 	
		
		/* The next chunk of code Formats the menu screen to look like
		 *   		Six Men's Morris title
		 *   
		 *   		Play Game button 
		 *   
		 *   		Debug button 
		 *   */
		Box box = Box.createVerticalBox();
		box.add(new JLabel(" "));
		box.add(new JLabel(" "));
		box.add(title);
		box.add(new JLabel(" "));
		box.add(new JLabel(" "));
		box.add(playGame);
		box.add(new JLabel(" "));
		box.add(new JLabel(" "));
		box.add(debug);
		this.add(box);
	}
	
	/**
	 * Return the state of the application
	 * @return The state of the application
	 */
	public int getState(){
		return this.state;
	}
	
	/**
	 * If the mouse was clicked on the play game button, call the board controller and display the regular game on screen.
	 * @param e The MouseEvent
	 */
	private void playGameMouseClicked(MouseEvent e){
		BoardController boardController = new BoardController(N);
		boardController.setVisible(true);
		SwingUtilities.getWindowAncestor(this).dispose();
	}
	
	/**
	 * If the mouse was click on the debug button, call the debug controller and display the debugging section on the screen.
	 * @param e The MouseEvent
	 */
	private void debugMouseClicked(MouseEvent e){
		DebugController debugController = new DebugController(N);
		debugController.setVisible(true);
		SwingUtilities.getWindowAncestor(this).dispose();
	}
	
	/**
	 * This method formats all of the required components to the menu screen.
	 * @param g The Graphics object 
	 */
	private void draw(Graphics g) {
		// declare the font and pass it through to each component
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, this.getWidth()*defaultFontSize/defaultScreenWidth);
		title.setFont(font);
		
		playGame.setFont(font);
		debug.setFont(font);
		//align components to center of the screen
		title.setAlignmentX(JLabel.CENTER_ALIGNMENT); 
		playGame.setAlignmentX(JButton.CENTER_ALIGNMENT);
		debug.setAlignmentX(JButton.CENTER_ALIGNMENT);
	}
	
	/**
	 * Updates the screen
	 */
	public void updateScreen(){
		this.invalidate();
		this.repaint();
	}
	
	@Override
	/**
	 * Draws the required components onto the screen.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.draw(g);
	}

}

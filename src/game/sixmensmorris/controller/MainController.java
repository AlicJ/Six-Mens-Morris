package game.sixmensmorris.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainController extends JFrame
{
	private static final long serialVersionUID = -1265967824330879837L;
	
	// Declare main game controller and UI
	private GameController board;
	private JPanel controls;
	private JButton newGameButton;

	public MainController()
	{
		initUI();
	}

	// Initialize User Interface
	private void initUI()
	{
		setTitle("Six Men's Morris");
		setSize(920, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Initialize game board
		board = new GameController();
		
		// Initialize button controls
		controls = new JPanel(new FlowLayout());
		newGameButton = new JButton("New Game");
		
		// Function for New Game button
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board = new GameController();
			}
		});
		
		// Add buttons to control panel
		controls.add(newGameButton);
		
		// Add control panel and game board to main window
		add(board, BorderLayout.CENTER);
		add(controls, BorderLayout.SOUTH);
		
		// Display game board
		board.setVisible(true);
	}

	// Create new game window and start game
	public static void main (String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame gameWindow = new MainController();
				gameWindow.setVisible(true);

				
				// Danish's test code for model below, put in MainController?
				
//				currentGame.setPiece('A', (char)0, currentGame.player(0));
//				currentGame.setPiece('A', (char)2, currentGame.player(1));
//				currentGame.setPiece('B', (char)3, currentGame.player(0));
//				currentGame.setPiece('B', (char)3, currentGame.player(0)); // returns false
//
//				print(currentGame.toString());
//
//				currentGame.movePiece('A', (char)0, 'E', (char)4);
//				print(currentGame.toString());
//				currentGame.movePiece('E', (char)4, 'A', (char)0);
//				print(currentGame.toString());
//				currentGame.movePiece('E', (char)2, 'E', (char)4); // error, although no indication...
//				print(currentGame.toString());
			}
		});
	}
}

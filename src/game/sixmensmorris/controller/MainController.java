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
	private JButton newGameButton, gameModeButton;

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
		gameModeButton = new JButton("Set Board State");
		
		// Function for New Game button
		newGameButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				board.newGame();
			}
		});
		
		// Function for Game Mode button
		gameModeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{				
				if (board.switchMode() == -1)
					gameModeButton.setText("Turn-Based Mode");
				else
					gameModeButton.setText("Set Board State");
				
				board.repaint();
			}
		});
		
		// Add buttons to control panel
		controls.add(newGameButton);
		controls.add(gameModeButton);
		
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
			}
		});
	}
}

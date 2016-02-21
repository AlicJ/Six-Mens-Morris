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
	
	private GameController board;
	private JPanel controls;
	private JButton newGameButton;
	private JButton changePlayerButton;

	public MainController()
	{
		initUI();
		
		board = new GameController();
		
		controls = new JPanel(new FlowLayout());
		newGameButton = new JButton("New Game");
		changePlayerButton = new JButton("Change Colour");
		
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board = new GameController();
			}
		});
		
		controls.add(newGameButton);
		controls.add(changePlayerButton);
		
		add(board, BorderLayout.CENTER);
		add(controls, BorderLayout.SOUTH);
		
		board.setVisible(true);
	}

	private void initUI()
	{
		setTitle("Simple example");
		setSize(920, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main (String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame gameWindow = new MainController();
				gameWindow.setVisible(true);

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

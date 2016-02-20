package game.sixmensmorris.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame
{
	private static final long serialVersionUID = -1265967824330879837L;
	
	private GameView game;
	private JPanel controls;
	private JButton newGameButton;

	public MainView()
	{
		initUI();
		
		game = new GameView();
		
		controls = new JPanel(new FlowLayout());
		newGameButton = new JButton("New Game");
		
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game = new GameView();
			}
		});
		
		controls.add(newGameButton);
		
		add(game, BorderLayout.CENTER);
		add(controls, BorderLayout.SOUTH);
		
		game.setVisible(true);
	}

	private void initUI()
	{
		setTitle("Simple example");
		setSize(700, 750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
//	public void paint(Graphics g)
//	{
//		super.paint(g);  // fixes the immediate problem.
//		Graphics2D g2 = (Graphics2D) g;
//		g2.setStroke(new BasicStroke(2));
//
//
//		int radius=15;
//		int p1=50;
//		int p2=650;
//
//
//		Line2D lin = new Line2D.Float(50, 50, p2, 50);
//		g2.draw(lin);
//
//
//		lin = new Line2D.Float(50, p2, p2, p2);
//		g2.draw(lin);
//
//
//		lin = new Line2D.Float(50, 50, 50, p2);
//		g2.draw(lin);
//		lin = new Line2D.Float(p2, 50, p2, p2);
//		g2.draw(lin);
//
//		lin = new Line2D.Float(50, 50+300, 50+150, 50+300);
//		g2.draw(lin);
//
//
//		lin = new Line2D.Float(p2, 50+300,p2-150, 50+300);
//		g2.draw(lin);
//
//
//
//		lin = new Line2D.Float(50+300, 50,50+300, 50+150);
//		g2.draw(lin);
//
//
//
//		lin = new Line2D.Float(50+300, p2-150,50+300, p2);
//		g2.draw(lin);
//
//
//
//
//
//
//		lin = new Line2D.Float(50+150, 50+150, p2-150, 50+150);
//		g2.draw(lin);
//
//		lin = new Line2D.Float(50+150, p2-150, p2-150, p2-150);
//		g2.draw(lin);
//
//
//
//
//		lin = new Line2D.Float(p2-150, 50+150, p2-150, p2-150);
//		g2.draw(lin);
//
//		lin = new Line2D.Float(50+150, 50+150, 50+150, p2-150);
//		g2.draw(lin);
//
//
//		Ellipse2D.Double circle = new Ellipse2D.Double(50-radius/2, 50-radius/2, radius, radius);
//		g2.fill(circle);        
//
//		circle = new Ellipse2D.Double(p2-radius/2, 50-radius/2, radius, radius);
//		g2.fill(circle);  
//
//		circle = new Ellipse2D.Double(p2-radius/2, p2-radius/2, radius, radius);
//		g2.fill(circle);  
//
//		circle = new Ellipse2D.Double(50-radius/2, p2-radius/2, radius, radius);
//		g2.fill(circle);  
//
//
//		circle = new Ellipse2D.Double(50-radius/2, 50+300-radius/2, radius, radius);
//		g2.fill(circle);  
//
//
//		circle = new Ellipse2D.Double(p2-radius/2, 50+300-radius/2, radius, radius);
//		g2.fill(circle); 
//
//
//
//		circle = new Ellipse2D.Double(50+300-radius/2, 50-radius/2, radius, radius);
//		g2.fill(circle); 
//
//
//		circle = new Ellipse2D.Double(50+300-radius/2, p2-radius/2, radius, radius);
//		g2.fill(circle); 
//
//		circle = new Ellipse2D.Double(50+150-radius/2, p2-150-radius/2, radius, radius);
//		g2.fill(circle); 
//
//
//		circle = new Ellipse2D.Double(50+150-radius/2, 50+150-radius/2, radius, radius);
//		g2.fill(circle); 
//
//
//		circle = new Ellipse2D.Double(p2-150-radius/2, 50+150-radius/2, radius, radius);
//		g2.fill(circle); 
//
//
//		circle = new Ellipse2D.Double(p2-150-radius/2, p2-150-radius/2, radius, radius);
//		g2.fill(circle); 
//
//		circle = new Ellipse2D.Double(p2-150-radius/2, p2-300-radius/2, radius, radius);
//		g2.fill(circle); 
//
//
//		circle = new Ellipse2D.Double(50+150-radius/2, p2-300-radius/2, radius, radius);
//		g2.fill(circle); 
//
//
//		circle = new Ellipse2D.Double(50+300-radius/2, p2-150-radius/2, radius, radius);
//		g2.fill(circle); 
//
//
//		circle = new Ellipse2D.Double(50+300-radius/2, 50+150-radius/2, radius, radius);
//		g2.fill(circle); 
//
//
//	}

	public static void main (String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame gameWindow = new MainView();
				gameWindow.setVisible(true);

//				currentGame.setPiece('A', (char)0, currentGame.player());
//				currentGame.setPiece('A', (char)2, currentGame.computer());
//				currentGame.setPiece('B', (char)3, currentGame.player());
//				currentGame.setPiece('B', (char)3, currentGame.player()); // returns false
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

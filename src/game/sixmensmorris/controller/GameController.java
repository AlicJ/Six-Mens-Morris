package game.sixmensmorris.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import game.sixmensmorris.model.Game;
import game.sixmensmorris.model.Node;
import game.sixmensmorris.model.Piece;
import game.sixmensmorris.model.Player;
import game.sixmensmorris.view.NodeView;
import game.sixmensmorris.view.PieceView;

public class GameController extends JPanel
{
	private static final long serialVersionUID = 5123686544982539956L;
	private static final int N_NODES = 16;
	private static final int N_PIECES = 6;
	private static final int RED_X = 50;
	private static final int BLUE_X = 750;
	private static final int BOARDSTART_X = 100;
	private static final int BOARDSTART_Y = 50;
	private static final int BOARDSIZE = 600;
	private static final int BOARDEND_X = BOARDSTART_X + BOARDSIZE;
	private static final int BOARDEND_Y = BOARDSTART_Y + BOARDSIZE;
	private static final int GRIDSIZE = BOARDSIZE / 4;
	
	private Game currentGame;
	private NodeView[] nodes;
	private PieceView[] redPieces;
	private PieceView[] bluePieces;
	private NodeView selectedNode;
	private Player currentPlayer;
	
	public GameController()
	{
		currentGame = new Game(N_PIECES);
		currentPlayer = currentGame.player();
		initNodes();
		initPieces();
		
		addMouseListener(new BoardController());
	}
	
	private void initNodes()
	{
		nodes = new NodeView[N_NODES];
		Node currentNode;
		int x, y;
		
		for (int i = 0; i < N_NODES; i++)
		{
			currentNode = currentGame.getNode(i);
			
			x = BOARDSTART_X + GRIDSIZE * (currentNode.getColumn() - 'A');
			y = BOARDEND_Y + GRIDSIZE * currentNode.getRow();
			
			nodes[i] = new NodeView(x, y, currentNode);
		}
		
		selectedNode = null;
	}
	
	private void initPieces()
	{
		redPieces = new PieceView[N_PIECES];
		bluePieces = new PieceView[N_PIECES];
		int y;
		
		for (int i = 0; i < N_PIECES; i++)
		{
			y = 50 + i * BOARDSIZE / 5;
			
			redPieces[i] = new PieceView(RED_X, y, currentGame.player().pawnAt(i));
			bluePieces[i] = new PieceView(BLUE_X, y, currentGame.computer().pawnAt(i));
		}
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawRect(BOARDSTART_X, BOARDSTART_Y, BOARDSIZE, BOARDSIZE);
		g2d.drawRect(BOARDSTART_X + GRIDSIZE, BOARDSTART_Y + GRIDSIZE, 2 * GRIDSIZE, 2 * GRIDSIZE);
		
		g2d.drawLine((BOARDSTART_X + BOARDEND_X) / 2, BOARDSTART_X, (BOARDSTART_X + BOARDEND_X) / 2, BOARDSTART_X + GRIDSIZE);
		g2d.drawLine((BOARDSTART_X + BOARDEND_X) / 2, BOARDEND_X, (BOARDSTART_X + BOARDEND_X) / 2, BOARDEND_X - GRIDSIZE);
		
		g2d.drawLine(BOARDSTART_X, (BOARDSTART_X + BOARDEND_X) / 2, BOARDSTART_X + GRIDSIZE, (BOARDSTART_X + BOARDEND_X) / 2);
		g2d.drawLine(BOARDEND_X, (BOARDSTART_X + BOARDEND_X) / 2, BOARDEND_X - GRIDSIZE, (BOARDSTART_X + BOARDEND_X) / 2);
		
		for (NodeView node : nodes)
		{			
			g2d.fill(node);
		}
		
		for (PieceView piece : redPieces)
		{
			g2d.setPaint(Color.RED);
			g2d.fill(piece);
		}
		
		for (PieceView piece : bluePieces)
		{
			g2d.setPaint(Color.BLUE);
			g2d.fill(piece);
		}
	}
	
	public class BoardController extends MouseAdapter
	{	
		public void mouseClicked (MouseEvent e)
		{
			Piece currentPiece;
			
			for (NodeView node : nodes)
			{
				if (node.contains(e.getPoint()))
				{
					currentPiece = node.getPiece();
					
					if (currentPiece == null)
					{
						System.out.println((char)node.getNode().getColumn()
								+ ", " + (int)node.getNode().getRow());
						
						currentGame.setPiece(node.getNode(), currentPlayer);
					}
					else
					{
						
					}
				}
			}
		}
	}
}


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
	
	// Change these three constants to adjust size
	private static final int BOARDSTART_X = 150;
	private static final int BOARDSTART_Y = 50;
	private static final int BOARDSIZE = 600;
	
	private static final int BOARDEND_X = BOARDSTART_X + BOARDSIZE;
	private static final int BOARDEND_Y = BOARDSTART_Y + BOARDSIZE;
	private static final int BOARDCENTER_X = (BOARDSTART_X + BOARDEND_X) / 2;
	private static final int BOARDCENTER_Y = (BOARDSTART_Y + BOARDEND_Y) / 2;
	private static final int REDBENCH_X = BOARDSTART_X - 100;
	private static final int BLUEBENCH_X = BOARDEND_X + 100;
	private static final int GRIDSIZE = BOARDSIZE / 4;
	
	private Game currentGame;
	private NodeView[] nodes;
	private PieceView[] redPieces;
	private PieceView[] bluePieces;
	private PieceView selectedPiece;
	private Player currentPlayer;
	
	public GameController()
	{
		currentGame = new Game(N_PIECES);
		currentPlayer = currentGame.player(0);
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
			y = BOARDSTART_Y + GRIDSIZE * currentNode.getRow();
			
			nodes[i] = new NodeView(x, y, currentNode);
		}
	}
	
	private void initPieces()
	{
		redPieces = new PieceView[N_PIECES];
		bluePieces = new PieceView[N_PIECES];
		int y;
		
		for (int i = 0; i < N_PIECES; i++)
		{
			y = 50 + i * BOARDSIZE / 5;
			
			redPieces[i] = new PieceView(REDBENCH_X, y, currentGame.player(0).pawnAt(i));
			bluePieces[i] = new PieceView(BLUEBENCH_X, y, currentGame.player(1).pawnAt(i));
		}
	}
	
	// Paints current state of board
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		// Draw outer and inner squares
		g2d.drawRect(BOARDSTART_X, BOARDSTART_Y, BOARDSIZE, BOARDSIZE);
		g2d.drawRect(BOARDSTART_X + GRIDSIZE, BOARDSTART_Y + GRIDSIZE, 2 * GRIDSIZE, 2 * GRIDSIZE);
		
		// Draw vertical center lines
		g2d.drawLine(BOARDCENTER_X, BOARDSTART_Y, BOARDCENTER_X, BOARDSTART_Y + GRIDSIZE);
		g2d.drawLine(BOARDCENTER_X, BOARDEND_Y, BOARDCENTER_X, BOARDEND_Y - GRIDSIZE);
		
		// Draw horizontal center lines
		g2d.drawLine(BOARDSTART_X, BOARDCENTER_Y, BOARDSTART_X + GRIDSIZE, BOARDCENTER_Y);
		g2d.drawLine(BOARDEND_X, BOARDCENTER_Y, BOARDEND_X - GRIDSIZE, BOARDCENTER_Y);
		
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
		
		if (selectedPiece != null)
		{
			g2d.setPaint(Color.BLACK);
			g2d.fill(selectedPiece);
		}
	}
	
	// Manages all mouse input
	public class BoardController extends MouseAdapter
	{	
		public void mouseClicked (MouseEvent e)
		{
			for (int i = 0; i < N_PIECES; i++)
			{
				if (redPieces[i].contains(e.getPoint()))
				{
					selectedPiece = redPieces[i];
				}
				
				if (bluePieces[i].contains(e.getPoint()))
				{
					selectedPiece = bluePieces[i];
				}
			}
			
			for (NodeView node : nodes)
			{
				if (node.contains(e.getPoint()))
				{
					System.out.println((char)node.getNode().getColumn()
							+ ", " + node.getNode().getRow());
					
					if (selectedPiece != null)
					{
						// If piece hasn't been placed yet
						if (selectedPiece.currentNode() == null)
						{
							Player selectedPlayer = currentGame.player(selectedPiece.getId());

							if (currentGame.setPiece(node.getNode(), selectedPlayer))
							{
								selectedPiece.moveToNode(node);
								selectedPiece = null;
							}
						}
						else
						{
							Node origin = selectedPiece.currentNode().getNode();
							Node destination = node.getNode();
							
							if (currentGame.movePiece(origin, destination))
							{
								selectedPiece.moveToNode(node);
								selectedPiece = null;
							}
						}
					}
				}
				else
				{

				}
				
			}
			
			repaint();
		}
	}
}


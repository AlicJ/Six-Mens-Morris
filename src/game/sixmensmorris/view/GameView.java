/*
 * GameView not currently implemented, code found in GameController instead
 */

package game.sixmensmorris.view;

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

public class GameView extends JPanel
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
	
	private NodeView[] nodes;
	
	public GameView(Game currentGame)
	{
		nodes = new NodeView[N_NODES];
		Node currentNode;
		int x, y;
		
		for (int i = 0; i < N_NODES; i++)
		{
			currentNode = currentGame.getNode(i);
			
//			x = START + GRIDSIZE * (currentNode.getColumn() - 'A');
//			y = START + GRIDSIZE * currentNode.getRow();
//			
//			nodes[i] = new NodeView(x, y, currentNode);
		}
	}
	
	public NodeView[] getNodes()
	{
		return nodes;
	}
	
	// Paints current state of board
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			// Cast Graphics to Graphics2D
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
			
			// Draw nodes
			for (NodeView node : nodes)
			{			
				g2d.fill(node);
			}
			
//			// Draw red pieces
//			for (PieceView piece : redPieces)
//			{
//				g2d.setPaint(Color.RED);
//				g2d.fill(piece);
//			}
//			
//			// Draw blue pieces
//			for (PieceView piece : bluePieces)
//			{
//				g2d.setPaint(Color.BLUE);
//				g2d.fill(piece);
//			}
//			
//			// Change color of selected piece
//			if (selectedPiece != null)
//			{
//				g2d.setPaint(Color.BLACK);
//				g2d.fill(selectedPiece);
//			}
		}
}


package game.sixmensmorris.view;

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
	private static final int START = 50;
	private static final int END = 650;
	private static final int GRIDSIZE = (END - START) / 4;
	
	private NodeView[] nodes;
	
	public GameView(Game currentGame)
	{
		nodes = new NodeView[N_NODES];
		Node currentNode;
		int x, y;
		
		for (int i = 0; i < N_NODES; i++)
		{
			currentNode = currentGame.getNode(i);
			
			x = START + GRIDSIZE * (currentNode.getColumn() - 'A');
			y = START + GRIDSIZE * currentNode.getRow();
			
			nodes[i] = new NodeView(x, y, currentNode);
		}
	}
	
	public NodeView[] getNodes()
	{
		return nodes;
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawRect(START, START, END - START, END - START);
		g2d.drawRect(START + GRIDSIZE, START + GRIDSIZE, 2 * GRIDSIZE, 2 * GRIDSIZE);
		
		g2d.drawLine((START + END) / 2, START, (START + END) / 2, START + GRIDSIZE);
		g2d.drawLine((START + END) / 2, END, (START + END) / 2, END - GRIDSIZE);
		
		g2d.drawLine(START, (START + END) / 2, START + GRIDSIZE, (START + END) / 2);
		g2d.drawLine(END, (START + END) / 2, END - GRIDSIZE, (START + END) / 2);
		
		for (NodeView node : nodes)
		{			
			g2d.fill(node);
		}
	}
}


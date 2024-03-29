package aufgaben.events.snake.v4.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import aufgaben.events.snake.v4.server.Board;
import aufgaben.events.snake.v4.server.Snake;

public class PanelSnake extends JPanel {

	private static final long serialVersionUID = -464331361619712251L;
	private final int DELAY = 140;
	private Timer timer;
	// Der Top-Level Container, von dem das Panel erzeugt wird
	private FrameSnake root;
	// Der Faktor, um den ein Schlangensegment ausgedehnt werden soll - 10 ist ein guter Richtwert 		
	private int stretch_factor = 10;
	
	
	private class TimerActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
//			root.getClient().sendGameStep();
			System.out.println(root.getClient().getBoard().getSnake(0).getX(0));
			
			repaint();
		}
	}

	private class TAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if ((key == KeyEvent.VK_LEFT)) {
				root.getClient().sendTurnLeft();
			}
			if ((key == KeyEvent.VK_RIGHT)) {
				root.getClient().sendTurnRight();
			}
			if ((key == KeyEvent.VK_UP)) {
				root.getClient().sendTurnUp();
			}
			if ((key == KeyEvent.VK_DOWN)) {
				root.getClient().sendTurnDown();				
			}
		}
	}

	
	/**
	 * Der Konstuktor f�r das Spielfeldpanel
	 * @param root Referenz auf den Top-Level-Container
	 * @param stretch_factor Der Faktor f�r die Ausdehnung des Feldes 
	 */
	public PanelSnake(FrameSnake root, int stretch_factor) {
		this.root = root;
		this.stretch_factor = stretch_factor;
		this.setPreferredSize(new Dimension(root.getClient().getBoard().getWidth() * stretch_factor, root.getClient().getBoard().getHeight() * stretch_factor));
		timer = new Timer(DELAY, new TimerActionListener());
		timer.start();
		addKeyListener(new TAdapter());		
		setBackground(Color.black);

		// ImageIcon iid = new
		// ImageIcon(this.getClass().getResource("dot.png"));
		// ball = iid.getImage();
		//
		// ImageIcon iia = new
		// ImageIcon(this.getClass().getResource("apple.png"));
		// apple = iia.getImage();
		//
		// ImageIcon iih = new
		// ImageIcon(this.getClass().getResource("head.png"));
		// head = iih.getImage();

		setFocusable(true);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Board b = root.getClient().getBoard();

		for (Snake snake : b.getSnakes()) {
		if (b.isInGame()) {
			
			g.setColor(Color.GREEN);
			g.fillOval(b.getAppleX() * stretch_factor, b.getAppleY() * stretch_factor, stretch_factor, stretch_factor);
			// g.drawImage(apple, apple_x, apple_y, this);

			for (int z = 0; z < snake.getSegmente(); z++) {

//				
				if (z == 0) {
					g.setColor(Color.RED);
					// g.drawImage(head, x[z], y[z], this);
				} else {
					g.setColor(Color.LIGHT_GRAY);								
//					 g.drawImage(ball, x[z], y[z], this);
				}
								
				g.fillOval(snake.getX(z) * stretch_factor, snake.getY(z) * stretch_factor, stretch_factor, stretch_factor);
			}

//			 Toolkit.getDefaultToolkit().sync();
//			 g.dispose();

		} else {
			gameOver(g);
		}
		}
	}

	public void gameOver(Graphics g) {
		//String msg = root.getModel().getSnake(0).getSegmente() + " Punkte - Game Over";
		String msg = "Game over";
		Font small = new Font("Helvetica", Font.BOLD, 24);
		FontMetrics metr = this.getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (this.getWidth() - metr.stringWidth(msg)) / 2,
				this.getHeight() / 2);
	}

}
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.server.UID;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.PanelUI;

public class GamePanel extends JPanel  implements ActionListener{
	final int SCREEN_HEIGHT = 600;
	final int SCREEN_WIDTH = 600;
	int snake_size = 25;
	int snakeBody = 4;
	int x[] = new int[SCREEN_HEIGHT*SCREEN_WIDTH/snake_size];
	int y[] = new int[SCREEN_HEIGHT*SCREEN_WIDTH/snake_size];
	boolean running = false;
    Timer timer;
    char direction = 'R';
    Random random = new Random();
    int applex = 0;
     int appley = 0;
     int score = 0;
     boolean start = false;
     int Delay = 100;
     Image headRight = new ImageIcon("headright.PNG").getImage();
     Image headLeft = new ImageIcon("headleft.PNG").getImage();
     Image headUp = new ImageIcon("headup.PNG").getImage();
     Image headDown = new ImageIcon("headdown.PNG").getImage();
     Image apple = new ImageIcon("apple.PNG").getImage();
     Image tailUp = new ImageIcon("tailup.PNG").getImage();
     Image tailDown = new ImageIcon("taildown.PNG").getImage();
     Image tailRight = new ImageIcon("tailright.PNG").getImage();
     Image tailLeft = new ImageIcon("tailleft.PNG").getImage();
     Image body = new ImageIcon("body.PNG").getImage();
     Image endBackRound = new ImageIcon("snake.jpg").getImage();
     
    static  JButton button ;
     
	public GamePanel() {
		
			this.setPreferredSize( new Dimension(SCREEN_HEIGHT, SCREEN_WIDTH));
			this.setBackground(Color.BLACK);
			this.setFocusable(true);
			this.addKeyListener(new Mykeyadapter());
			start();
			
			
		
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	
	}
	public void start() {
		running = true;
		timer = new Timer(Delay,this);
		timer.start();
		newApple();
		
	}
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		if(running) {
		
			for(int i=0; i<snakeBody;i++) {
				if(i == 0) {
//					g.setColor(Color.BLUE);
//					g.fillOval(x[i], y[i], snake_size, snake_size);
					if(direction == 'R') {
						g2.drawImage(headRight, x[i], y[i], snake_size, snake_size, null);
					}
					else if(direction == 'L') {
						g2.drawImage(headLeft, x[i], y[i], snake_size, snake_size, null);
					}
					else if(direction == 'U') {
						g2.drawImage(headUp, x[i], y[i], snake_size, snake_size, null);
					}
					else if(direction == 'D') {
						g2.drawImage(headDown, x[i], y[i], snake_size, snake_size, null);
					}
					
				}
				else if(i == snakeBody-1) {
					if(direction == 'R') {
						g2.drawImage(tailRight, x[i], y[i], snake_size, snake_size, null);
					}
					else if(direction == 'L') {
						g2.drawImage(tailLeft, x[i], y[i], snake_size, snake_size, null);
					}
					else if(direction == 'U') {
						g2.drawImage(tailUp, x[i], y[i], snake_size, snake_size, null);
					}
					else if(direction == 'D') {
						g2.drawImage(tailDown, x[i], y[i], snake_size, snake_size, null);
					}
					
				}
				
				else {
//					g.setColor(Color.green);
//					g.fillRect(x[i], y[i],snake_size,snake_size);
					g2.drawImage(body, x[i], y[i], snake_size, snake_size, null);
				}
					
				
			}
		
			g.setColor(Color.red);
//			g.fillOval(applex, appley, snake_size, snake_size);
			g2.drawImage(apple, applex, appley, snake_size, snake_size, null);
			g.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD, 25));
			g.drawString("Score: " + score, (SCREEN_HEIGHT/snake_size)/2, 40);
		}
		else 
			gameOver(g);	
	}
	
	public void move() {
		for(int i=snakeBody; i>0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'R':
			
			x[0] = x[0] + snake_size;
			break;
		case 'L':
			x[0] = x[0] - snake_size;
			break;
		case 'U':		
			y[0] = y[0] - snake_size;
			break;
		case 'D':			
			y[0] = y[0] + snake_size;
			break;
		}
		
		
		
		
	}


	
	public void newApple() {
		applex = random.nextInt((int)SCREEN_HEIGHT/snake_size)*snake_size;
		appley = random.nextInt((int)SCREEN_WIDTH/snake_size)*snake_size;
	}
	public void checkApple() {
		if(x[0]==applex&&y[0]==appley) {
			newApple();
			snakeBody++;
			score++;
			Delay--;
			timer.setDelay(Delay);
			
		}
	}
	
	public void checkCollision() {
		for (int i = snakeBody; i > 0; i--) {
			if((x[0] == x[i])&&(y[0] == y[i])) {
				running = false;
			}
			if(x[0] < 0) {
				x[0] = SCREEN_WIDTH;
			}
			
			if(x[0] > SCREEN_WIDTH) {
				x[0] = 0;
			}
			if(y[0] < 0) {
				y[0] = SCREEN_HEIGHT;
			}
			if(y[0] > SCREEN_HEIGHT ) {
				y[0] = 0;
			}
		}
	}
	public void gameOver(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(endBackRound, 0, 0, 600, 600,null);
		g.setColor(Color.RED);
		g.setFont(new Font(Font.MONOSPACED,Font.ITALIC, 100));
		g.drawString("Score: " + score, (SCREEN_HEIGHT/snake_size)/2, 150);
		g.drawString("Game Over", (SCREEN_HEIGHT/snake_size)/2, 300);
		timer.stop();
		button = new JButton();
		button.setText("Play again");
		button.setBackground(Color.LIGHT_GRAY);
		button.setBorder(BorderFactory.createEtchedBorder());
		button.setBounds(150,400, 300, 100);
		this.add(button);
		button.addActionListener(this);
		
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkCollision();
		}
		repaint();
		
		if(e.getSource() == button) {
			new GameFrame();
		}
		
		
	}
	
	
	
	public class  Mykeyadapter extends KeyAdapter {
		
		
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_UP:
					
					if(direction != 'D') {
						direction = 'U';
					}
					break;
				case KeyEvent.VK_RIGHT:
					
					if(direction != 'L') {
						direction = 'R';
					}
					break;
				case KeyEvent.VK_LEFT:
					if(direction != 'R') {
						direction='L';
					}
					break;
				case KeyEvent.VK_DOWN:
					if(direction != 'U') {
						direction='D';
					}
					break;
				}
				
		}
		
	}
	
	
}

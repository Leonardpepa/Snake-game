import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.BackingStoreException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window implements ActionListener {

	JFrame frame = new JFrame();
	JButton button;
	Image snake = new ImageIcon("./src/snake2.jpg").getImage();

	Window() {
		panel p = new panel();
		frame.add(p);
		frame.setContentPane(p);
		frame.pack();

		button = new JButton();
		frame.add(button);
		button.setBackground(Color.lightGray);
		button.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		button.setText("start game");
		button.setBounds(150, 200, 300, 200);
		button.addActionListener(this);
		JLabel label = new JLabel();
		label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
		// label.setVerticalAlignment(1);
		label.setHorizontalAlignment(10);
		label.setBounds(300, 50, 50, 50);
		frame.add(label);

		frame.setIconImage(snake);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("snake game");
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			frame.dispose();
			new GameFrame();

		}

	}

	public class panel extends JPanel {

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(snake, 0, 0, 600, 600, null);
			// g2.drawImage(snake, 0, 0, null);
		}

	}

}

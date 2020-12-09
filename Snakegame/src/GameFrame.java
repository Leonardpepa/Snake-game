import javax.swing.JFrame;

public class GameFrame extends JFrame {
	GameFrame(){
		
		
		//this.add(new GamePanel());
		this.setContentPane(new GamePanel());
		this.setTitle("snake game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
		
	}

	
	
}

	import java.awt.Color;
	import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.*;

	@SuppressWarnings("serial")
	public class Winner extends JFrame implements ActionListener {

			//Setting up GUI Variables
			JPanel introPanel = new JPanel();
			JLabel[] questionLabels = new JLabel[5];
			JButton startButton = new JButton("Play Again");
			ButtonGroup buttonGroup[] = new ButtonGroup[5];

			//Setting Font
			Font font = new Font("Comic Sans MS", Font.BOLD, 20);

		public Winner() {
			setupQuestionLabels();
			userRatingsPanelSetup();
			frameSetup();
		}
		
		
		//adding the font and bound of the label
		private void setupQuestionLabels() {
			questionLabels[0] = new JLabel("WINNER");

			questionLabels[0].setFont(new Font("Comic Sans MS", Font.BOLD , 35));
			
			questionLabels[0].setBounds(545, 200, 300, 55);

			introPanel.add(questionLabels[0]);

		}

		private void userRatingsPanelSetup() {
			introPanel.setBounds(50, 50, 1250, 500);
			introPanel.setLayout(null);

		}
		
		private void frameSetup() {

			//Sets Size
			getContentPane().setBackground(new Color(33, 150, 243));
			setSize(1366, 750);
			setTitle("Maze Race");
			setLayout(null);
			
			//Add JPanel
			add(introPanel);
			setVisible(true);
			
			//Setup Confirm Selection Button
			startButton.setBounds(575, 550, 200, 50);
			startButton.addActionListener(this);
			add(startButton);
		}
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == startButton) {
				setVisible(false);
				new MazeRaceGUI();
			}
			
		}
}

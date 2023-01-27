import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class SplashScreenMaze extends JFrame implements ActionListener {

		//Setting up GUI Variables
		JPanel introPanel = new JPanel();
		JLabel[] questionLabels = new JLabel[5];
		JButton startButton = new JButton("Confirm Selection");
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton[][] radioButton = new JRadioButton[1][2];

		//Setting Font
		Font font = new Font("Comic Sans MS", Font.BOLD, 20);
		public static String upimg, downimg, rightimg, leftimg = new String();

	public SplashScreenMaze() {
		setupQuestionLabels();
		setupRadioButtonsLabels();
		userRatingsPanelSetup();
		frameSetup();
	}
	
		// creating radio buttons to choose character and setting the bounds for the button
	private void setupRadioButtonsLabels() {
		radioButton[0][0] = new JRadioButton("Sonic");
		radioButton[0][1] = new JRadioButton("Mario");
		
		buttonGroup = new ButtonGroup();
		
		buttonGroup.add(radioButton[0][1]);
		radioButton[0][1].setBounds(300, 200, 200, 30);
		introPanel.add(radioButton[0][1]);
		
		radioButton[0][1].addActionListener(this);
		
		buttonGroup.add(radioButton[0][0]);
		radioButton[0][0].setBounds(300, 250, 200, 30);
		introPanel.add(radioButton[0][0]);
		
		radioButton[0][0].addActionListener(this);
		
	}

		// adding the labels and setting bounds
	private void setupQuestionLabels() {
		questionLabels[0] = new JLabel("Sega Maze");
		questionLabels[1] = new JLabel("Please select your character");
		
		questionLabels[0].setFont(new Font("Comic Sans MS", Font.BOLD , 45));
		
		questionLabels[0].setBounds(500, 50, 300, 75);
		questionLabels[1].setBounds(300, 150, 200, 30);
		
		introPanel.add(questionLabels[1]);
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
		startButton.setBounds(600, 550, 200, 50);
		startButton.addActionListener(this);
		add(startButton);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == startButton) {
			setVisible(false);
			new MazeRaceGUI();
		}
		if (event.getSource() == radioButton[0][1]) {
			upimg = "images/mario0.gif";
			rightimg = "images/mario1.gif";
			downimg = "images/mario2.gif";
			leftimg = "images/mario3.gif";
		}
		if (event.getSource() == radioButton[0][0]) {
			upimg = "images/sonic0.gif";
			rightimg = "images/sonic1.gif";
			downimg = "images/sonic2.gif";
			leftimg = "images/sonic3.gif";
		}
	}
}

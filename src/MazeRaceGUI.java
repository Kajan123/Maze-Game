import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

@SuppressWarnings("serial")

//GUI for the maze
public class MazeRaceGUI extends JFrame implements KeyListener, ActionListener {
	
	//Variables for Cells
	private final int CELL_SIZE = 25;
	private final int NUM_COINS = 9;
	private final int NUM_CELLS_WIDTH = 27;
	private final int NUM_CELLS_HEIGHT = 27;
	
	//Variables for maze
	private final ImageIcon WALL = new ImageIcon("images/red square.png");
	private final ImageIcon OUT_OF_BOUNDS = new ImageIcon("images/black square.png");
	private final ImageIcon PATH = new ImageIcon("images/grey square.png");
	private final ImageIcon COIN = new ImageIcon("images/gold coin.gif");
	
	//Variable for player
	private Player player = new Player(SplashScreenMaze.rightimg);
	private ImageIcon down = new ImageIcon(SplashScreenMaze.downimg);
	private ImageIcon up = new ImageIcon(SplashScreenMaze.upimg);
	private ImageIcon right = new ImageIcon(SplashScreenMaze.rightimg);
	private ImageIcon left = new ImageIcon(SplashScreenMaze.leftimg);
	//Variables for frame and timer
	private JPanel scoreboardPanel = new JPanel(null);
	private Timer gameTimer = new Timer(100, this);
	private JLabel scoreLabel = new JLabel("0");
	private JLabel timerLabel = new JLabel("0");
	
	private int score;
	private int highScore;
	
	private JPanel help = new JPanel();
	
	//Variables for panel
	private JPanel mazePanel = new JPanel(new GridBagLayout());
	private GridBagConstraints constraints = new GridBagConstraints();
	private Cell[][] maze = new Cell[NUM_CELLS_WIDTH][NUM_CELLS_HEIGHT];
	
	//Setting variable values
	private int numCoins = 0;
	public int time = 0;
	
	//Menu bar variables
		private JMenu [] menu = {
				new JMenu("File"),
		};

		//this method creates the menu items which appear on the menus
		private JMenuItem[] menuItems = {
				new JMenuItem("High scores")
		};
		
	//Calling methods
	public MazeRaceGUI() {
		
		scoreboardPanelSetup();
		mazePanelSetup();
		frameSetup();
		setupMenu();
		
	}
	
	private void setupMenu() {
		for(int i = 0; i < menuItems.length; i++) {
			menu[i % 10].add(menuItems[i]);
		}
		JMenuBar menubar = new JMenuBar();
		for(JMenu menus : menu)
			menubar.add(menus);
		setJMenuBar(menubar);

		for (int length = 0; length < menuItems.length; length++) {
			menuItems[length].addActionListener(this);
		}
	}
	
	private void scoreboardPanelSetup() {
		
		//Sets up Panel
		scoreboardPanel.setLayout(null);
		scoreboardPanel.setBounds(0,  0 , CELL_SIZE*NUM_CELLS_WIDTH, 50);
		
		
		//Sets up labels
		scoreLabel.setBounds(scoreboardPanel.getWidth()/2, 0, 100, 25);
		timerLabel.setBounds(scoreboardPanel.getWidth()/2, scoreboardPanel.getHeight()/2, 100, 25);
		
		
		//Adds labels to panel
		scoreboardPanel.add(scoreLabel);
		scoreboardPanel.add(timerLabel);
		
	
	}
	
	//Sets up maze panel
	private void mazePanelSetup() {
		
		//Positions the maze panel
		mazePanel.setBounds(0, 50, CELL_SIZE*NUM_CELLS_WIDTH, CELL_SIZE*NUM_CELLS_HEIGHT);
		
		//Calling methods
		loadMaze();
		placeCoins();
		placePlayer();
		
	}
	
	//Setting up the frame
	private void frameSetup() {
		
		//Sets frame
		setTitle("Kajan's Maze Race");
		setSize(mazePanel.getWidth(), scoreboardPanel.getHeight() + mazePanel.getHeight() + CELL_SIZE);
		setLayout(null);
		
		//Add panels
		add(scoreboardPanel);
		add(mazePanel);
		
		//Action listener
		addKeyListener(this);
		
		//Sets rules for frame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		//Starts game timer
		gameTimer.start();
	}
	
	//Places player on GUI
	private void placePlayer() {
		Cell cell = findEmptyCell(); //
		
		//Gets position
		player.setRow(cell.getRow());
		player.setCol(cell.getCol());
		
		//Sets icon in new position
		maze[player.getRow()][player.getCol()].setIcon(player.getIcon());
		
		
	}
	
	//
	private Cell findEmptyCell() {
		
		Cell cell = new Cell(0,0); //
		
		//Randomizes locations
		do {
			cell.setRow((int)(Math.random()*24) + 2);
			cell.setCol((int)(Math.random()*24) + 2);
			
		} while(maze[cell.getRow()][cell.getCol()].getIcon() != PATH);
		
		return cell;
	}
	
	//Places coins
	private void placeCoins() {
		
		//
		for(int coin = 0; coin <= NUM_COINS; coin++){
			
			Cell cell= findEmptyCell();
			maze[cell.getRow()][cell.getCol()].setIcon(COIN);
			
		}
		
	}
	
	//Loads maze
	private void loadMaze() {
		int row = 0; // Setting value
		char[] line; // Setting char value
		
		
		//Gets maze from files
		try {
			Scanner input = new Scanner(new File("maze.txt")); //
			
			//
			while (input.hasNext()) {
				
				line = input.nextLine().toCharArray();
				
				//
				for(int col = 0; col < line.length; col++)
					fillCell(line[col], row, col);
				
				row++;
			}
			
			input.close();
		}catch (FileNotFoundException error) {
			System.out.println("File error: " + error);
		}
		
	}
	
	//Fills maze
	private void fillCell(char shape, int row, int col) {
		
		//Positions Maze
		maze[row][col] = new Cell(row, col);
		
		//Sets icons for maze
		if (shape == 'W')
			maze[row][col].setIcon(WALL);
		else if (shape == 'X')
			maze[row][col].setIcon(OUT_OF_BOUNDS);
		else if (shape == '.')
			maze[row][col].setIcon(PATH);
		else if (shape == 'C')
			maze[row][col].setIcon(COIN);
		
		//Adds coordinates
		constraints.gridx = col;
		constraints.gridy = row;
		mazePanel.add(maze[row][col], constraints);
	}		
	
	//Changes timer
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource()==gameTimer){
			time++;
			timerLabel.setText(Integer.toString(time));
		}
		if (event.getSource() == menuItems[0]) {
			try {
				//reading from scores.txt file
				Scanner input = new Scanner(new File("scores.txt"));
				//converts input.next() to string
				int highScore = input.nextInt();
				//outputs the high score on a dialog box
				JOptionPane.showMessageDialog(help, "Your high score is currently:  "+highScore);
				//closes input
				input.close();
			} catch (FileNotFoundException error) {
				System.out.println("File not found");
			}               
		}
	}
	
	//Actions for each key press
	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode()==KeyEvent.VK_UP && maze[player.getRow()-1][player.getCol()+0].getIcon() !=WALL) {
			player.setIcon(up);
			movePlayer(-1,0);
		}
		else if (key.getKeyCode()==KeyEvent.VK_RIGHT && maze[player.getRow()+0][player.getCol()+1].getIcon() !=WALL){
			player.setIcon(right);
			movePlayer(0,1);
		}
		else if (key.getKeyCode()==KeyEvent.VK_LEFT && maze[player.getRow()+0][player.getCol()-1].getIcon() !=WALL){
			player.setIcon(left);
			movePlayer(0,-1);
		}
		else if (key.getKeyCode()==KeyEvent.VK_DOWN && maze[player.getRow()+1][player.getCol()+0].getIcon() !=WALL){
			player.setIcon(down);
			movePlayer(1, 0);
		}
	}
	
	//Moves players
	private void movePlayer(int dRow, int dCol) {
		maze[player.getRow()][player.getCol()].setIcon(PATH);
		
		//Counts the number of coins collected
		if (maze[player.getRow()+dRow][player.getCol()+dCol].getIcon() == COIN) {
			MusicPlayer musicObject = new MusicPlayer();
			musicObject.playMusic("Music/Coin.wav");
			score+=1;

			highScore = 0;
			//gets the current highest score
			try {
				//reading from scores.txt file
				Scanner input = new Scanner(new File("scores.txt"));
				highScore = input.nextInt();
				input.close();
			} catch (FileNotFoundException error) {
				System.out.println("File not found");
			}       

			//compares new score to current highest score, if its higher, 
			//save current highest score as the new highest score
			numCoins++;
			scoreLabel.setText(Integer.toString(numCoins));
			if (numCoins == 10) {
				gameTimer.stop();
				setVisible(false);
				if (time < highScore) {
					try{    
						FileWriter fw=new FileWriter("scores.txt");    
						fw.write(new Integer(time).toString());    
						fw.close();    
					} catch(Exception e) {
						System.out.println(e);
					}    
					System.out.println("Success saving");    
				}  
				new Winner();
			}
		}
		
		player.move(dRow, dCol);
		maze[player.getRow()][player.getCol()].setIcon(player.getIcon());
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	

}

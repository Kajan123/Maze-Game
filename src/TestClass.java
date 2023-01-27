import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class TestClass {
	public static void main(String[] args) {
	 //creates new JWindow
    JWindow window = new JWindow();
    
    //adds splash screen
    window.getContentPane().add(new JLabel("",new ImageIcon("images/load.gif"),SwingConstants.CENTER));
    
    //sets bounds
    window.setBounds(450,300,700,467);
    
    //makes it invisible
    window.setVisible(true);
    try {
            Thread.sleep(3000);
    } catch(InterruptedException Error) {}
    
    //deletes screen
    window.dispose();
   
	//Runs SplashScreen
		new SplashScreenMaze();

	}
}


package projekt;
import javax.swing.*;

public class StartFrame extends JFrame {

	int WEIGHT = 280;
	int HEIGHT = 180;
	
	JLabel title = new JLabel("Set parameters of the star");
	JLabel lname = new JLabel("Name: ");
	JLabel lmass = new JLabel("Mass: ");
	JLabel lradius = new JLabel("Radius: ");
	JTextField name = new JTextField();
	JTextField mass = new JTextField();
	JTextField radius = new JTextField();
	
	
	
	
	StartFrame(){
		this.setSize(WEIGHT, HEIGHT);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Millenium Falcon");
		
		
		
		
	}
	
	
}

package projekt;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ProjectMainFrame extends JFrame {
	
 // In loving memory of Stephen Hawking
	
	JMenuBar menubar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem save = new JMenuItem("Save Configuration");

	
	JPanel leftside = new JPanel(new GridLayout(10,1));
	JPanel anipanel = new JPanel();
	JPanel animel = new JPanel(new BorderLayout()); //Filip
	JPanel bottom = new JPanel();
	
	
	ArrayList<String> planets = new ArrayList<String>();
	
	
	JLabel lstarlabel = new JLabel("Star"); //l od left (dla Filipa)
	JLabel lstarplabel = new JLabel("tu bêd¹ parametry gwiazdki <333");// w ramce
	JLabel lplanetplabel = new JLabel("<html>tu <br>bêd¹ <br>parametry <br>planet <333</html>");// w ramce
	
	
	
	
	JComboBox<Object> planetList = new JComboBox<Object>(planets.toArray());
	
	
	JSlider sslider = new JSlider(JSlider.HORIZONTAL, 0, 10, 1);
	JLabel speed = new JLabel(String.format("Animation speed: %d", sslider.getValue()));
	
	
	JButton play = new JButton("Play");
	JButton pause = new JButton("Pause");
	JButton reverse = new JButton("Reverse");
	
	
	ProjectMainFrame() throws HeadlessException {
	  		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	  		this.setSize(800,600);
	  		this.setBackground(Color.black);
	  		
	  		this.setJMenuBar(menubar);
	  		menubar.add(file);
	  		file.add(save);
	  		
	  		this.add(leftside, BorderLayout.LINE_START);
	  		this.add(animel, BorderLayout.CENTER);
	  		animel.add(anipanel, BorderLayout.CENTER);
	  		animel.add(bottom, BorderLayout.PAGE_END);
	  		anipanel.setBackground(Color.black); 
	  		
	  		Border border = BorderFactory.createLineBorder(Color.yellow, 2);
	  		lstarplabel.setBorder(border);
	  		lplanetplabel.setBorder(border);
	  		
	  		sslider.addChangeListener(new SliderChangeListener());
	  		
	  		leftside.add(lstarlabel);
	  		leftside.add(lstarplabel);
	  		leftside.add(planetList);
	  		leftside.add(lplanetplabel);
	  		
	  		bottom.add(speed);
	  		bottom.add(sslider);
	  		bottom.add(play);
	  		bottom.add(pause);
	  		bottom.add(reverse);
	  		
	  		
	}
	
	public class SliderChangeListener implements ChangeListener{
		
		public void stateChanged(ChangeEvent arg0) {
			String value = String.format("Animation speed: %d", sslider.getValue());
			speed.setText(value);
		}
	}
	

}

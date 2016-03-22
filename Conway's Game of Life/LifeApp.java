package GameOfLife;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class LifeApp extends JFrame{

	private LifePanel lp = new LifePanel();
	private LifeControls cp = new LifeControls();
	
	public LifeApp(){
		this.setTitle("Conway's Game of Life - Life Sucks");
		this.setSize(500, 500);
		
		lp.setSize(this.getSize().width / 2, this.getSize().height);
		cp.setSize(this.getSize().width / 2, this.getSize().height);
		
		this.add(lp, BorderLayout.CENTER);
		this.add(cp, BorderLayout.EAST);
		
		URL imgURL = getClass().getResource("logo.png");//got alternate code from the internet
		BufferedImage bi = null;		
		//File inputfile = new File("./Logo.png");
		try {
			bi = ImageIO.read(imgURL);
		} catch (IOException e) {
			//TODO Auto-generated catch block
			System.out.println("error reading logo, exiting");
		}
		catch(IllegalArgumentException e){
			
		}
		// -- this is the call that actually changes the icon
		setIconImage(bi);
		

		                           
		this.setLocationRelativeTo(null);					
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true);
	}
	
	public static void main(String arg[]){
		LifeApp life = new LifeApp();
	}
}

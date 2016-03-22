package GameOfLife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LifePanel extends JPanel {
	
	private int width = 0;
	private int height = 0;
	private int cellWidth = 0;
	private int cellHeight = 0;
	private int delay = 50;
	
	private MouseListener anonymousClass;
	private Timer timer;
	
	private LifeGrid grid = new LifeGrid(20,20);
	private Conway conway = new Conway();
	private Custom custom = new Custom();
	
	private static ActionListener start;
	private static ActionListener stop;
	private static ActionListener step;
	private static ActionListener clear;
	private static ActionListener save;
	private static ActionListener load;
	private static ActionListener wrap;
	
	public LifePanel(){
		
		super();
		
		this.setBackground(new Color(0,0,0));
		setLayout(new BorderLayout(5,5));
				
		anonymousClass = new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Click!");
				
				int x = arg0.getX();
				int y = arg0.getY();
				int tempX = 0;
				int tempY = 0;
				
				System.out.println(x + ", " + y);
				
				for(int i = 1; i < grid.getWidth(); i++){
					if(cellWidth * (i - 1) <= x && x <= cellWidth * i){
						tempX = i;
						break;
					}
					
					else{
						tempX = grid.getWidth();
					}
				}
				
				for(int i = 1; i < grid.getHeight(); i++){
					if(cellHeight * (i - 1) <= y && y <= cellHeight * i){
						tempY = i;
						break;
					}
					
					else{
						tempY = grid.getHeight();
					}
				}
				
				tempX -= 1;
				tempY -= 1;
				
				System.out.println("Cell " + tempX + "," + tempY);
				System.out.println("Before = " + grid.getGridCell(tempX, tempY));

				if(grid.getGridCell(tempX, tempY) == 0){
					grid.setGridCell(tempX, tempY, 1);
				}
				
				else{
					grid.setGridCell(tempX, tempY, 0);
				}

				System.out.println("After = " + grid.getGridCell(tempX, tempY));

				
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		addMouseListener(anonymousClass);

		final ActionListener create = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Tick!");
				conway.ApplyRule(grid);
				repaint();
			}	
		};
		
		start = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timer = new Timer(delay, create);
				timer.start();
			}	
		};
		
		stop = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timer.stop();
			}	
		};
		
		step = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				conway.ApplyRule(grid);
				repaint();
			}	
		};
		
		clear = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				grid.clear();
				repaint();
			}	
		};
		
		save = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				savePattern();
			}	
		};
		
		load = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadPattern();
			}	
		};
		
		wrap = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(conway.getWrap() == false){
					conway.setWrap(true);
					System.out.println("true");
				}
				
				else{
					conway.setWrap(false);
					System.out.println("false");
				}
			}	
		};
	}
	
	public static ActionListener Start(){
		return start;
	}
	
	public static ActionListener Stop(){
		return stop;
	}
	
	public static ActionListener Step(){
		return step;
	}
	
	public static ActionListener Clear(){
		return clear;
	}
	
	public static ActionListener saveShape(){
		return save;
	}
	
	public static ActionListener loadShape(){
		return load;
	}
	
	public void savePattern(){
		JFrame saveFrame = new JFrame();
		JFileChooser saveChooser = new JFileChooser();
		saveChooser.setDialogTitle("Save");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("LIFE file", "lif");
		saveChooser.setFileFilter(filter);

		    
		int selection = saveChooser.showSaveDialog(saveFrame);
		
		if (selection == JFileChooser.APPROVE_OPTION) {
			File temp = saveChooser.getSelectedFile();
			
			try {
				FileWriter fw = new FileWriter(temp);
				fw.write("");
				grid.save(temp.getAbsolutePath());
				
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void loadPattern(){
		JFrame loadFrame = new JFrame();
		JFileChooser loadChooser = new JFileChooser();
		loadChooser.setDialogTitle("Load");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("LIFE file", "lif");
		loadChooser.setFileFilter(filter);
		
		int selection = loadChooser.showOpenDialog(loadFrame);
		
		if (selection == JFileChooser.APPROVE_OPTION) {	
			File temp = loadChooser.getSelectedFile();
			grid.load(temp.getAbsolutePath(), true);
			repaint();
		}
	}
	
	public void paint(Graphics g)
	{		
		super.paint(g);
		
		width = getWidth();
		height = getHeight();
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, width, height);
		
		g2d.setColor(Color.red);
		
		cellWidth = width / grid.getWidth();
		cellHeight = height / grid.getHeight();
		
		for(int i = 0; i < grid.getWidth(); i++){
			for(int j = 0; j < grid.getHeight(); j++){
				if(grid.getGridCell(i, j) == 1){
					g2d.fillRect(i  * cellWidth, j * cellHeight, cellWidth, cellHeight);
				}
			}
		}
		
		g2d.setColor(Color.black);
		
		for(int i = 0; i < grid.getWidth(); i++){
			g2d.drawLine(cellWidth * i, 0, cellWidth * i, cellHeight * grid.getHeight());
		}
		
		for(int i = 0; i < grid.getHeight(); i++){
			g2d.drawLine(0, cellHeight * i, grid.getWidth() * cellWidth, cellHeight * i);
		}
		
		g2d.drawLine(width - 1, 0, width - 1, height);
		g2d.drawLine(0, height - 1, width, height - 1);
	}
}
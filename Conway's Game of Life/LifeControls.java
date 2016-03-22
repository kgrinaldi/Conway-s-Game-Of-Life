package GameOfLife;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class LifeControls extends JPanel {
	
	JButton startButton;
	JButton stopButton;
	JButton stepButton;
	JButton clearButton;
	JButton saveShape;
	JButton loadShape;
	
	JCheckBox setWrap;
	
	public LifeControls(){	
		setLayout( new GridLayout(15,1,15,2));
		
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		stepButton = new JButton("Step");
		clearButton = new JButton("Clear");
		saveShape = new JButton("Save");
		loadShape = new JButton("Load");
		
		setWrap = new JCheckBox("Wrap", false);
		
		startButton.addActionListener(LifePanel.Start());
		stopButton.addActionListener(LifePanel.Stop());
		stepButton.addActionListener(LifePanel.Step());
		clearButton.addActionListener(LifePanel.Clear());
		saveShape.addActionListener(LifePanel.saveShape());
		loadShape.addActionListener(LifePanel.loadShape());
		
		add(startButton);
		add(stopButton);
		add(stepButton);
		add(clearButton);
		add(saveShape);
		add(loadShape);
		//add(setWrap);
	}
}

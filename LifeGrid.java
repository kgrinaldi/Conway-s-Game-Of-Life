package GameOfLife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class LifeGrid extends GridBase{

	
	public LifeGrid(int width, int height){
		this.grid = new int [width][height];
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void setGridCell(int x, int y, int value) {
		this.grid[x][y] = value;
	}

	@Override
	public int getGridCell(int x, int y) {
		return this.grid[x][y];
	}

	@Override
	public void clear() {
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid.length; j++){
				grid[i][j] = 0;
			}
		}	
	}

	@Override
	public void load(String filename, boolean clear) {
		
		if(clear == true){
			this.clear();
		}
		
		File temp = new File(filename);
	
		try {
			Scanner sc = new Scanner(temp);
			
			while (sc.hasNextLine()) {
	            String line = sc.nextLine();
	            String coords[] = line.split(", ");
	            this.setGridCell(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), 1);
	        }
			
			sc.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("File doesnt exist");
		}
		
		
	}
	
	@Override
	public void save(String filename) { //"file name" in this case is just the file path
		File temp = new File(filename);
		try {
			FileWriter fw = new	FileWriter(temp);
			
			for(int i = 0; i < this.getHeight(); i++){
				for(int j = 0; j < this.getWidth(); j++){
					if(this.getGridCell(i, j) != 0){
						String line = i + ", " + j + "\n";
						fw.write(line);
						System.out.println(line);	
					}
					
				}
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
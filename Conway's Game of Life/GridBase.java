package GameOfLife;

public abstract class GridBase {
	
	protected int width;
	protected int height;
	protected int grid[][];

	// -- gets the number of cells in the width of the grid
	public int getWidth() {
		return this.width;
	}

	// -- sets the number of cells in the width of the grid and resizes the grid
	//    keeping the currently state of cells. If cells are "resized out of the grid"
	//    they are lost
	public void setWidth(int width) {
		int newgrid[][] = new int[this.height][width];
		int w = Math.max(this.width, width);
		for (int i = 0; i < this.height; ++i) {
			for (int j = 0; j < w; ++j) {
				newgrid[i][j] = this.grid[i][j];
			}
		}
		this.width = width;
		this.grid = newgrid;
	}

	// -- gets the number of cells in the height of the grid
	public int getHeight() {
		return this.height;
	}

	// -- sets the number of cells in the height of the grid and resizes the grid
	//    keeping the currently state of cells. If cells are "resized out of the grid"
	//    they are lost
	public void setHeight(int height) {
		int newgrid[][] = new int[height][this.width];
		int h = Math.max(this.height, height);
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < this.width; ++j) {
				newgrid[i][j] = this.grid[i][j];
			}
		}
		this.height = height;
		this.grid = newgrid;
		
	}

	// -- sets a grid cell to value. If x or y is off the grid, does nothing
	public abstract void setGridCell(int x, int y, int value);

	// -- returns the grid cell value at x, y. If x or y is off the grid return 0
	public abstract int getGridCell(int x, int y);
	
	// -- clears all cells
	public abstract void clear();
	
	// -- load pattern file
	public abstract void load(String filename, boolean clear);
	
	// -- save pattern file
	public abstract void save(String filename);
}
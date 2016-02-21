package GameOfLife;

public class Conway implements RuleSetInterface{

	boolean wrapActive = false;
	
	@Override
	public void ApplyRule(GridBase grid) {
//		rules:
//		Any live cell with fewer than two live neighbours dies
//		Any live cell with two or three live neighbours lives 
//		Any live cell with more than three live neighbours dies
//		Any dead cell with exactly three live neighbours becomes a live cell
		
		int count[][] = new int [grid.width][grid.height];
		
		for(int i = 0; i < grid.getWidth(); i++){
			for(int j = 0; j < grid.getHeight(); j++){
				count[i][j] = checkSurrounding(i, j, grid);
			}
		}
		
		for(int i = 0; i < grid.getWidth(); i++){
			for(int j = 0; j < grid.getHeight(); j++){
				
//				if(wrapActive == true){
//					
//					count = checkSurrounding(i, j, grid, true);
//				}
//				
//				else{
//					count = checkSurrounding(i, j, grid); 
//				}
				
				if(grid.getGridCell(i, j) != 0){
					
					if(count[i][j] < 2){
						grid.setGridCell(i, j, 0);
						System.out.println("Rule 1");
					}
					
					else if(count[i][j] > 3){
						grid.setGridCell(i, j, 0);
						System.out.println("Rule 3");
					}
					
					else if(count[i][j] == 2 || count[i][j] == 3){
						grid.setGridCell(i, j, 1);
						System.out.println("Rule 2");
					}
					
				}
				
				if(grid.getGridCell(i, j) == 0){
					if(count[i][j] == 3){
						grid.setGridCell(i, j, 1);
						System.out.println("Rule 4");
					}
				}

				//System.out.println("count = " + count[i][j]);
			}
		}
		
	}
	
	public int checkSurrounding(int x, int y, GridBase grid){
		
		int count = 0;
		
		for(int i = x - 1; i <= x + 1; i++){
			for(int j = y - 1; j <= y + 1; j++){
				if(i != x && j != y){
					try{
						if(grid.getGridCell(i, j) != 0){
							count++;
							System.out.println("count " + count + " " + x + ", " + y);
						}
					}
					catch(ArrayIndexOutOfBoundsException e){
					
					}
				}
			}
		}
		
		return count;
	}
	
	public int checkSurrounding(int x, int y, GridBase grid, boolean active){
		
		int count = 0;
		
		int tempX = grid.getWidth() - 1;
		int tempY = grid.getHeight() - 1;
		
		for(int i = x - 1; i <= x + 1; i++){
			for(int j = y - 1; j <= y + 1; j++){
				try{
					if(grid.getGridCell(i, j) == 1){
						count++;
					}
				}
				catch(ArrayIndexOutOfBoundsException e){
					if(x < 0){
						if(y < 0){
							if(grid.getGridCell(tempX, tempY) == 1){
								count++;
							}		
						}
						
						else{
							if(grid.getGridCell(tempX, y) == 1){
								count++;
							}
						}
					}
					
					else if (y < 0){
						if(grid.getGridCell(i, tempY) == 1){
							count++;
						}
					}
				}
			}
		}
		
		
		return count;
		
	}

	@Override
	public void setWrap(boolean wrap) {
		wrapActive = wrap;
	}
	
	public boolean getWrap(){
		return wrapActive;
	}

}

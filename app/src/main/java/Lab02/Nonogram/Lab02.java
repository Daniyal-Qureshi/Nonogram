package Lab02.Nonogram;

public class Lab02 {

	
	
	static int[][] rows;
	 static int[][] columns;

	static boolean[][] puzzle;
	static private boolean check = false;

	//finding the solution
	public static void findSolution(int row, int col, int c) {
		if ( col >= columns.length) {	
				isFinished();
				return;
		}
		else if (row >= rows.length ) 
			return;
		
		int size=columns[col][c];
		if(size==0) {
			size=columns[col][c+1];
			c=c+1;
		}
		
		
		if (isCompatible(row, col, size)) { 
			add(row, col, size); 
			if (c == columns[col].length - 1)  
				findSolution(0, col + 1, 0);
			
			
			else 
				findSolution(row + 1 + size, col, c + 1);
				
			
			if (!check) 					
				remove(row, col, size);
			
		}
		
		if (!check) 
			findSolution(row + 1, col, c);
		
	}
	
	//remove from puzzle board
	private static void remove(int row, int col, int blockSize) {
		for (int i = puzzle.length - 1; i >= 0; i--) {
			if (puzzle[i][col]) {
				for (int j = i; j >= 0; j--) {
					if (!puzzle[j][col]) {
						return;
					} else {
					puzzle[j][col] = false;
					}
				}
			}

		}
		
	}
	
	//insert on puzzle board
	 static void add(int row, int col, int blockSize) {
		for (int i = 0; i < blockSize; i++) 
			puzzle[row + i][col] = true;
		
	}
	
	 //It checks wheteher puzzle is solved or not 
	public static void isFinished() {
		for (int i = 0; i < rows.length; i++) {
			String rowStr = "";
			for (int j = 0; j < columns.length; j++) {
				if (puzzle[i][j]) {
					rowStr += "x";
				} else {
					rowStr += " ";
				}
			} 
				String[] blockArray = rowStr.trim().split("((?<=x)[ ]+)");
				
				if (blockArray.length != rows[i].length) { 
					return;
				} else {
					for(int j = 0; j < blockArray.length && j < rows[i].length; j++) { 
						if (rows[i][j] != blockArray[j].length() ) {
							return;
						}
					}
				}	
		} 	 
		check=true;	
	}
	
	
	//It checks whether the given row ,col and size is compatible or not
	public static boolean isCompatible(int row, int col, int blockSize) { 
		
		if ( blockSize + row > rows.length) {
		
			return false;
		}
		if (row > 0 ) {   
			
                    if (puzzle[row - 1][col]) { 
			return false;
		    }
                }
		for (int i = row; i < puzzle.length; i++) {
		    String rowStr = "";
		    for (int j = 0; j < columns.length; j++) {
		        if (puzzle[i][j]) {
				rowStr += "x";
			    } else {
				rowStr += " ";
			    }
		} 

		String[] blocks = rowStr.trim().split("((?<=x)[ ]+)");
                if (blocks.length > rows[i].length) {
                	
                	return false;
                } 

                int length = 0;
                for (String tempString : blocks) {
                    length += tempString.length();
                }

                int sum = 0;
                for (int k = 0; k < rows[i].length; k++) {
                    sum += rows[i][k];
                }
                
                if (length > sum) {

                	return false;
                }


                for (int n = 0; n < blocks.length - 1; n++) {
                    if (blocks[n].length() != rows[i][n]) {
                        return false;
                    }
                
                }

               if (blocks[blocks.length - 1].length() < rows[i][blocks.length - 1]) {
                    int lastTrue = -1;
                    for (int s = 0; s < col; s++) {
                        if(puzzle[i][s]) {
                            lastTrue = s;
                        }
                    }
                
                    if (lastTrue != -1 && lastTrue + 1 != col) {
                    	return false;
                    }
                }  else if (blocks[blocks.length - 1].length() > rows[i][blocks.length - 1]) {
                        return false;
                    } 
                 }
               
	    return true;
	}


	
	
		//It prints the puzzle board true false
       private static void print(boolean[][] board ) {
                              

           for(int r = 0; r < board.length; r++) {
        
           for(int c = 0; c < board[r].length; c++)
                if (board[r][c]) { 
               	
                   System.out.print("true" + " ");
               } else {
                   System.out.print("false" + " ");
               }
           		System.out.println("");
           }
           
           
      
      
      
      }

	
	
	
	
	public static boolean[][] solveNonogram(int[][] columns,int[][] rows){
		puzzle = new boolean[rows.length][columns.length];		
		
		for (int i = 0; i < rows.length; i++) {
			if(rows[i][0]==0)
				rows[i]=new int[]{rows[i][1]};
			
		}	
		findSolution(0, 0, 0);
		print(puzzle);
		return puzzle;
	}
	
	public static void main(String[] args) {

		
		
		//First test case example 
//		columns= new int[][]{
//		 
//		 {0,1},
//		 {0,1},
//		 {0,1},
//		 {0,1},
//		 {0,1}
//		 
//		 
//		};
//		
//		
//		rows= new int[][]{
//			{0,5}	
//		};
//		
		
			//Second test case example
		
//		columns=new int[][] {
//			
//			{0,2},{0,2},{0,2},{0,1},{0,1},{0,1},{0,2},{0,2},{0,1}
//			
//		};
//		rows=new int[][] {
//			
//			{4,3},{3,4}
//			
//		};
//		
		
		
		//Third test case example 
		columns=new int[][] {
			
			{0,2},
			{2,1}
			,{0,4},
			{0,3},
			{0,1}
			
		};
		rows=new int[][] {
			
			{0,4},
			{0,4},
			{0,3},
			{0,1},
			{0,1}
			
		};
		
		//Just commment other examples so that you can individually check the desired one.
		solveNonogram(columns, rows);
		
	}
	
	
}

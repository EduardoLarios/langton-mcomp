public class LangtonAnt {
	public static void main(String[] args){
		int size = 100;
		boolean[][] plane = runAnt(size, size);
		for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
				System.out.print(plane[row][col]);
                System.out.print(plane[row][col] ? "#" : ".");
            }
            System.out.println();
        }
	}
	
	private static boolean[][] runAnt(int height, int width){
		boolean[][] plane = new boolean[height][width];
		int antX = width/2, antY = height/2;//start in the middle-ish
		int xChange = 0, yChange = -1; //start moving up
		while(antX < width && antY < height && antX >= 0 && antY >= 0){
			if(plane[antY][antX]){
				//turn left
				if(xChange == 0){ //if moving up or down
					xChange = yChange;
					yChange = 0;
				}else{ //if moving left or right
					yChange = -xChange;
					xChange = 0;
				}
			}else{
				//turn right
				if(xChange == 0){ //if moving up or down
					xChange = -yChange;
					yChange = 0;
				}else{ //if moving left or right
					yChange = xChange;
					xChange = 0;
				}
			}
			plane[antY][antX] = !plane[antY][antX];
			antX += xChange;
			antY += yChange;
		}
		return plane;
	}
}

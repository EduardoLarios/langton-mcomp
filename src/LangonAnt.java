@SuppressWarnings("ALL")
class LangtonAnt {
    public static void main(String[] args) {
        int size = 100;
        boolean[][] matrix = step(size, size);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(matrix[row][col] ? "#" : ".");
            }
            System.out.println();
        }
    }

    private static boolean[][] step(int height, int width) {
        //creates the matrix where the ant will move
        boolean[][] matrix = new boolean[height][width];
        //start in the middle of the matrix
        int ant_x = width / 2, ant_y = height / 2;
        //start moving up
        int x_change = 0, y_change = -1;

        while (ant_x < width && ant_y < height && ant_x >= 0 && ant_y >= 0) {
            //turn left
            if (matrix[ant_y][ant_x]) {
                //if moving up or down
                if (x_change == 0) {
                    x_change = y_change;
                    y_change = 0;
                }
                //if moving left or right
                else {
                    y_change = -x_change;
                    x_change = 0;
                }
            }

            //turn right
            else {
                //if moving up or down
                if (x_change == 0) {
                    x_change = -y_change;
                    y_change = 0;
                }
                //if moving left or right
                else {
                    y_change = x_change;
                    x_change = 0;
                }
            }
            matrix[ant_y][ant_x] = !matrix[ant_y][ant_x];
            ant_x += x_change;
            ant_y += y_change;
        }
        return matrix;
    }
}

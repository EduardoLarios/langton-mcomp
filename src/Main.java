import java.io.IOException;

enum Direction {
    North(0),
    South(1),
    West(2),
    East(3);

    private int numVal;

    Direction(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}

class Point {
    int X;
    int Y;

    Point(int x, int y) {
        this.X = x;
        this.Y = y;
    }
}

class Langton {
    private Point origin;
    private Point ant_position = new Point(0, 0);
    boolean out_of_bounds;
    final boolean[][] is_black;

    // Dirección inicial de la hormiga
    private Direction ant_direction = Direction.North;

    private final Direction[] left_turn = new Direction[]{Direction.West, Direction.North, Direction.South, Direction.East};
    private final Direction[] right_turn = new Direction[]{Direction.East, Direction.South, Direction.North, Direction.West};

    private final int[] x_inc = {0, 1, -1, 0};
    private final int[] y_inc = {-1, 0, 0, 1};

    public Langton(int width, int height, Point origin) {
        this.origin = origin;
        out_of_bounds = false;
        is_black = new boolean[width][height];
    }

    public Langton(int width, int height) {
        this(width, height, new Point(width / 2, height / 2));
    }

    private void move_ant() {
        //Alguien que sepa Java cambie los 0's por el valor del enum pls :P
        //ejemplo de C# arriba
        ant_position.X += x_inc[ant_direction.getNumVal()];
        ant_position.Y += y_inc[ant_direction.getNumVal()];
    }

    Point step() {
        if (out_of_bounds) {
            throw new IllegalStateException("Trying to step out of bounds.");
        }
        Point current_pnt = new Point(ant_position.X + origin.X, ant_position.Y + origin.Y);
        boolean lft_turn = is_black[current_pnt.X][current_pnt.Y];

        // Igual revisar aquí el valor del enum
        int dir = ant_direction.getNumVal();
        ant_direction = lft_turn ? left_turn[dir] : right_turn[dir];
        is_black[current_pnt.X][current_pnt.Y] = !is_black[current_pnt.X][current_pnt.Y];
        move_ant();

        current_pnt = new Point(ant_position.X + origin.X, ant_position.Y + origin.Y);
        out_of_bounds =
                current_pnt.X < 0 ||
                current_pnt.X >= is_black[0].length - 1 ||
                current_pnt.Y < 0 ||
                current_pnt.Y >= is_black[1].length - 1;

        return ant_position;
    }
}

public class Main {

    public static void main(String[] args) {

        Langton ant = new Langton(100, 100);
        while (!ant.out_of_bounds) ant.step();

        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
                System.out.print(ant.is_black[col][row] ? "#" : ".");
            }

            System.out.println();
        }

        try {
            char buffer = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

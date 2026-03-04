
class RatInMaze {

    static class Frame {
        int row;
        int col;
        int dir;

        Frame(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }

    public static void main(String[] args) {

        int[][] maze = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {0, 1, 0, 0},
            {1, 1, 1, 1}
        };

        if (!solveMaze(maze)) {
            System.out.println("No path found.");
        }
    }

    static boolean solveMaze(int[][] maze) {

        int N = maze.length;

        boolean[][] visited = new boolean[N][N];

        int[] dRow = {1, 0, -1, 0};   // Down, Right, Up, Left
        int[] dCol = {0, 1, 0, -1};

        Stack<Frame> stack = new Stack<>(1000);

        stack.push(new Frame(0, 0, 0));
        visited[0][0] = true;

        while (!stack.isEmpty()) {

            Frame current = stack.peek();

            int r = current.row;
            int c = current.col;

            // Destination reached
            if (r == N - 1 && c == N - 1) {
                printPath(stack);
                return true;
            }

            // All directions tried → backtrack
            if (current.dir == 4) {
                visited[r][c] = false;
                stack.pop();
                continue;
            }

            int nextRow = r + dRow[current.dir];
            int nextCol = c + dCol[current.dir];

            current.dir++;  // advance direction

            if (isValid(nextRow, nextCol, maze, visited)) {
                visited[nextRow][nextCol] = true;
                stack.push(new Frame(nextRow, nextCol, 0));
            }
        }

        return false;
    }

    static boolean isValid(int r, int c, int[][] maze, boolean[][] visited) {

        int N = maze.length;

        return r >= 0 && c >= 0 &&
               r < N && c < N &&
               maze[r][c] == 1 &&
               !visited[r][c];
    }

    static void printPath(Stack<Frame> stack) {

        Stack<Frame> path = new Stack<>(stack.size());

        while (!stack.isEmpty()) {
            path.push(stack.pop());
        }

        System.out.println("Path:");
        while (!path.isEmpty()) {
            Frame f = path.pop();
            System.out.println("(" + f.row + ", " + f.col + ")");
        }
    }
}
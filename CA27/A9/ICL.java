class ICL {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void labelImageComponents(int[][] pixel, int m) {

        CircularQueue<Point> q = new CircularQueue<>(m * m);

        int id = 1;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < m; c++) {

                if (pixel[r][c] == 1) {

                    id++;  
                    pixel[r][c] = id;

                    q.enqueue(new Point(r, c));

                    while (!q.isEmpty()) {

                        Point p = q.dequeue();
                        int row = p.x;
                        int col = p.y;

                        // Right neighbor
                        if (col < m - 1 && pixel[row][col + 1] == 1) {
                            pixel[row][col + 1] = id;
                            q.enqueue(new Point(row, col + 1));
                        }

                        // Down neighbor
                        if (row < m - 1 && pixel[row + 1][col] == 1) {
                            pixel[row + 1][col] = id;
                            q.enqueue(new Point(row + 1, col));
                        }

                        // Left neighbor
                        if (col > 0 && pixel[row][col - 1] == 1) {
                            pixel[row][col - 1] = id;
                            q.enqueue(new Point(row, col - 1));
                        }

                        // Up neighbor
                        if (row > 0 && pixel[row - 1][col] == 1) {
                            pixel[row - 1][col] = id;
                            q.enqueue(new Point(row - 1, col));
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        int[][] image = {
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 1, 1}
        };

        int m = image.length;

        labelImageComponents(image, m);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }
    }
}
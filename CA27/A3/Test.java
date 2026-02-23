class Test {
    public static void main(String[] args) {
        double matrix[][] = new double[][] {{10},
    {25, 4},
    {9, 31, 8}};

        LTM ltm = new LTM(matrix);

        LTM inv = ltm.inverse();

        ltm.display();
        inv.display();
    }
}
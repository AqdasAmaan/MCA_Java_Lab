class Tree {
    int h;
    char[] a ;

    public Tree (int h) {
        this.h = h;
        a = new char[(int)Math.pow(2, h) - 1];
    }

    public void build(int i) {

    }

    public boolean isEmpty() {
        return a[0] == '\0';
    }
}
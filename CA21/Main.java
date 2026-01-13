class Main {
    public static void main(String[] args) {
        Array a = new Array(10);

        for (int i=0; i<10; i++) {
            a.insert(i+1, i);
        }

        a.display();
    }
}
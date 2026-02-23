class Test {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        for (int i=0; i<10; i++) {
            list.insert(10-i, list.size);
        }
        list.display();
        list.sort("bucket");
        list.display();
    }
}
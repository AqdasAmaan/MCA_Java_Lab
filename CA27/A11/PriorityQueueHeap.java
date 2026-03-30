
class PriorityQueueHeap {

    private MaxHeapTree t;

    public PriorityQueueHeap(int capacity) {
        t = new MaxHeapTree(capacity);
    }

    public boolean isEmpty() {
        return t.isEmpty();
    }

    public void insert(int x) {
        t.insert(x);
    }

    public int del() {
        return t.delMax();
    }

    public void display() {
        t.display();
    }
}

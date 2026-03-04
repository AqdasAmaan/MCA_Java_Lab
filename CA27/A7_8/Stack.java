
class Stack<T> {

    private T[] a ;
    private final int capacity;
    private int size, top;

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        this.capacity = capacity;
        a = (T[]) new Object[capacity];
        size = 0;
        top = -1;
    } 

    public int top () {
        return top;
    }

    public void push(T item) {
        if (size == capacity) 
            throw new RuntimeException("Stack Overflow!");

        a[++top] = item;
        size++;
    }

    public T pop() {
        if (top == -1) 
            throw new RuntimeException("Stack Underflow!");

        size--;

        return a[top--];
    }

    public T peek() {
        if (top == -1) 
            throw new RuntimeException("Stack Underflow!");

        return a[top];
    }

    public int search(T element) {
        for (int i=0; i<a.length; i++) 
            if (a[i].equals(element)) return i; 
        
        return -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return size;
    }
}
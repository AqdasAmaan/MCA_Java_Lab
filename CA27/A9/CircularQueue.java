class CircularQueue<T> {
    private T[] arr;
    private int front;
    private int rear;
    private int size;

    @SuppressWarnings("unchecked")
    public CircularQueue(int size) {
        this.size = size;
        arr = (T[]) new Object[size];
        front = -1;
        rear = -1;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (rear + 1) % size == front;
    }

    public void enqueue(T data) {
        if (isFull()) {
            throw new RuntimeException("Queue is Full");
        }

        if (isEmpty()) {
            front = 0;
        }

        rear = (rear + 1) % size;
        arr[rear] = data;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }

        T value = arr[front];

        if (front == rear) {
            // Only one element
            front = rear = -1;
        } else {
            front = (front + 1) % size;
        }

        return value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }
        return arr[front];
    }

    public T getRear() {
        if (isEmpty())
            throw new RuntimeException("Queue is Empty");
        return arr[rear];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return;
        }

        int i = front;
        while (true) {
            System.out.print(arr[i] + " ");
            if (i == rear) break;
            i = (i + 1) % size;
        }
        System.out.println();
    }
}
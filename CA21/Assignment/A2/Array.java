class Array {

    private final int a[], length;
    private int size;

    public Array(int n) {
        if (n < 0)
            throw new RuntimeException("Size of Array cannot be negative! Array creation failed.");
        a = new int[n];
        length = n;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(int x, int index) {
        // if (size == length)
        //     throw new RuntimeException("Array Is Full! Operation Unsuccessful.");
        
        if (index < 0 || index > length)
            throw new RuntimeException("Invalid Index! Operation Unsuccessful.");

        for (int i = size - 1; i >= index; i--)
            a[i+1] = a[i];
        
        a[index] = x;
        size = size + 1;
    }

    public int del(int index) {
        if (size == 0)
            throw new RuntimeException("Array Is Empty! Operation Unsuccessful.");
        
        if (index < 0 || index > size)
            throw new RuntimeException("Invalid Index! Operation Unsuccessful.");

        int x = a[index];

        for (int i = index + 1; i < size; i++)
            a[i-1] = a[i];

        size = size - 1;
        
        return x;
    }

    public int indexOf(int x) {
        for (int i=0; i<size; i++) {
            if (a[i] == x)
                return i;
        }
        return -1;
    }

    public int get(int index) {
        if (index < size) 
            return a[index];
        else 
            return -1;
    }

    public void display() {
        if (length == 0) {
            System.out.print("[ ]");
            return;
        }

        System.out.print("[");

        for (int i=0; i<size; i++)
            System.out.print(a[i] + ", ");
        
        System.out.println("\b\b]");
    }
}
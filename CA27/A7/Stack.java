import java.util.*;

class Stack<T> {

    private final ArrayList<T> stack = new ArrayList<>();

    public void push(T item) {
        stack.add(item);
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.remove(stack.size() - 1);
    }

    public T peek() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.get(stack.size() - 1);
    }

    public int search(T element) {
        return stack.indexOf(element);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
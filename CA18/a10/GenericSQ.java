
import java.util.*;

class GenericStack<T> {

    private final List<T> stack = new ArrayList<>();

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

    public void reverse() {
        Collections.reverse(stack);
    }

    public void pushAll(Collection<? extends T> items) {
        for (T item : items) {
            push(item);
        }
    }

    public void popAll(Collection<? super T> target) {
        while (!isEmpty()) {
            target.add(pop());
        }
    }

    public static int getSize(GenericStack<?> st) {
        return st.stack.size();
    }

    public static boolean haveSameSize(GenericStack<?> a, GenericStack<?> b) {
        return a.stack.size() == b.stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public List<T> getInternalList() {
        return stack;
    }
}

class GenericQueue<T> {

    private final List<T> queue = new ArrayList<>();

    public void enqueue(T item) {
        queue.add(item);
    }

    public T dequeue() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException();
        }
        return queue.remove(0);
    }

    public T peek() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException();
        }
        return queue.get(0);
    }

    public boolean contains(T item) {
        return queue.contains(item);
    }

    public void enqueueAll(Collection<? extends T> src) {
        for (T item : src) {
            enqueue(item);
        }
    }

    public void dequeueAll(Collection<? super T> dest) {
        while (!queue.isEmpty()) {
            dest.add(dequeue());
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public List<T> getInternalList() {
        return queue;
    }
}

class GenericUtils {

    public static <T> void copyStack(GenericStack<? extends T> src,
            GenericStack<? super T> dest) {
        for (T item : src.getInternalList()) {
            dest.push(item);
        }
    }

    public static <T> List<T> filter(Collection<T> src, java.util.function.Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T item : src) {
            if (p.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static <T> GenericStack<T> merge(GenericStack<? extends T> a,
            GenericStack<? extends T> b) {
        GenericStack<T> merged = new GenericStack<>();
        merged.pushAll(a.getInternalList());
        merged.pushAll(b.getInternalList());
        return merged;
    }

    public static <T> GenericStack<T> reversed(GenericStack<T> st) {
        GenericStack<T> newStack = new GenericStack<>();
        List<T> copy = new ArrayList<>(st.getInternalList());
        Collections.reverse(copy);
        newStack.pushAll(copy);
        return newStack;
    }
}

public class GenericSQ {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter 1 for Auto Demo, 2 for User Demo, 0 to Exit: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                        runAllDemonstrations();
                    break;
                }

                case 2 -> {
                    System.out.println("User operated demo");

                    System.out.print("Enter the type (1 for Stack, 2 for Queue): ");
                    int type = sc.nextInt();
                    sc.nextLine(); // consume newline

                    System.out.print("Enter the data type (1 for Integer, 2 for Double, 3 for String): ");
                    int dataType = sc.nextInt();
                    sc.nextLine(); // consume newline

                    if (type == 1) { // Stack
                        switch (dataType) {
                            case 1:
                                GenericStack<Integer> intStack = new GenericStack<>();
                                System.out.println("Enter integers to push to stack (type 'done' to finish):");
                                while (true) {
                                    String input = sc.nextLine();
                                    if (input.equalsIgnoreCase("done")) {
                                        break;
                                    }
                                    try {
                                        Integer val = Integer.parseInt(input);
                                        intStack.push(val);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Please enter a valid integer or 'done'.");
                                    }
                                }
                                System.out.println("Final Stack: " + intStack.getInternalList());
                                break;
                            case 2:
                                GenericStack<Double> doubleStack = new GenericStack<>();
                                System.out.println("Enter doubles to push to stack (type 'done' to finish):");
                                while (true) {
                                    String input = sc.nextLine();
                                    if (input.equalsIgnoreCase("done")) {
                                        break;
                                    }
                                    try {
                                        Double val = Double.parseDouble(input);
                                        doubleStack.push(val);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Please enter a valid double or 'done'.");
                                    }
                                }
                                System.out.println("Final Stack: " + doubleStack.getInternalList());
                                break;

                            case 3:
                                GenericStack<String> stringStack = new GenericStack<>();
                                System.out.println("Enter strings to push to stack (type 'done' to finish):");
                                while (true) {
                                    String input = sc.nextLine();
                                    if (input.equalsIgnoreCase("done")) {
                                        break;
                                    }
                                    stringStack.push(input);
                                }
                                System.out.println("Final Stack: " + stringStack.getInternalList());
                                break;

                            default:
                                break;
                        }

                    } else if (type == 2) { // Queue
                        switch (dataType) {
                            case 1:
                                GenericQueue<Integer> intQueue = new GenericQueue<>();
                                System.out.println("Enter integers to enqueue to queue (type 'done' to finish):");
                                while (true) {
                                    String input = sc.nextLine();
                                    if (input.equalsIgnoreCase("done")) {
                                        break;
                                    }
                                    try {
                                        Integer val = Integer.parseInt(input);
                                        intQueue.enqueue(val);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Please enter a valid integer or 'done'.");
                                    }
                                }
                                System.out.println("Final Queue: " + intQueue.getInternalList());

                                break;

                            case 2:
                                GenericQueue<Double> doubleQueue = new GenericQueue<>();
                                System.out.println("Enter doubles to enqueue to queue (type 'done' to finish):");
                                while (true) {
                                    String input = sc.nextLine();
                                    if (input.equalsIgnoreCase("done")) {
                                        break;
                                    }
                                    try {
                                        Double val = Double.parseDouble(input);
                                        doubleQueue.enqueue(val);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Please enter a valid double or 'done'.");
                                    }
                                }
                                System.out.println("Final Queue: " + doubleQueue.getInternalList());

                                break;

                            case 3:
                                GenericQueue<String> stringQueue = new GenericQueue<>();
                                System.out.println("Enter strings to enqueue to queue (type 'done' to finish):");
                                while (true) {
                                    String input = sc.nextLine();
                                    if (input.equalsIgnoreCase("done")) {
                                        break;
                                    }
                                    stringQueue.enqueue(input);
                                }
                                System.out.println("Final Queue: " + stringQueue.getInternalList());

                                break;

                            default:
                                break;
                        }
                    }

                    break;

                }
                case 0 -> {
                    System.out.println("Exiting Generic Stack and Queue Demonstration.");
                    return;
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    public static void runAllDemonstrations() {
        System.out.println("========= GENERIC STACK & QUEUE DEMONSTRATION =========\n");
        GenericStack<Integer> s1 = new GenericStack<>();
        s1.push(10);
        s1.push(20);
        s1.push(30);

        System.out.println("Stack s1 peek: " + s1.peek());
        System.out.println("Stack s1 search(20): " + s1.search(20));

        List<Integer> nums = Arrays.asList(100, 200, 300);
        s1.pushAll(nums);

        List<Object> poppedList = new ArrayList<>();
        s1.popAll(poppedList);
        System.out.println("Popped into list: " + poppedList);

        System.out.println("Size: " + GenericStack.getSize(s1));

        GenericQueue<String> q = new GenericQueue<>();
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");

        System.out.println("Queue peek: " + q.peek());
        System.out.println("Queue contains B? " + q.contains("B"));

        List<String> more = Arrays.asList("X", "Y", "Z");
        q.enqueueAll(more);

        List<Object> drain = new ArrayList<>();
        q.dequeueAll(drain);
        System.out.println("Queue drained into: " + drain);

        GenericStack<Number> dest = new GenericStack<>();
        GenericUtils.copyStack(poppedListToStack(poppedList), dest);

        System.out.println("Copied via PECS (Number stack): " + dest.getInternalList());

        GenericStack<Integer> a = new GenericStack<>();
        a.push(1);
        a.push(2);
        a.push(3);

        GenericStack<Integer> b = new GenericStack<>();
        b.push(4);
        b.push(5);

        GenericStack<Integer> merged = GenericUtils.merge(a, b);
        System.out.println("Merged stack: " + merged.getInternalList());

        List<Integer> evens = GenericUtils.filter(merged.getInternalList(), x -> x % 2 == 0);
        System.out.println("Filtered evens: " + evens);

        GenericStack<Integer> reversed = GenericUtils.reversed(merged);
        System.out.println("Reversed stack: " + reversed.getInternalList());

        System.out.println("\n✔ All demonstrations completed.");
    }

    private static GenericStack<Number> poppedListToStack(List<Object> list) {
        GenericStack<Number> st = new GenericStack<>();
        for (Object obj : list) {
            if (obj instanceof Number number) {
                st.push(number);
            }
        }
        return st;
    }
}

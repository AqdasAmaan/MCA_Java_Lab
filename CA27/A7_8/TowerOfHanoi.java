// import java.util.Stack;

class TowerOfHanoi {

    static class Frame {
        int n;
        char from, aux, to;
        int stage;

        Frame(int n, char from, char aux, char to, int stage) {
            this.n = n;
            this.from = from;
            this.aux = aux;
            this.to = to;
            this.stage = stage;
        }
    }

    public static void hanoi(int n, char from, char aux, char to) {
        Stack<Frame> stack = new Stack<>(100);

        stack.push(new Frame(n, from, aux, to, 0));

        while (!stack.isEmpty()) {
            Frame current = stack.pop();

            if (current.n == 1) {
                System.out.println("Move disk 1 from " + current.from + " to " + current.to);
                continue;
            }

            if (current.stage == 0) {
                current.stage = 1;
                stack.push(current);
                stack.push(new Frame(current.n - 1, current.from, current.to, current.aux, 0));
            }
            else if (current.stage == 1) {
                System.out.println("Move disk " + current.n + " from " + current.from + " to " + current.to);

                stack.push(new Frame(current.n - 1, current.aux, current.from, current.to, 0));
            }
        }
    }

    public static void main(String[] args) {
        hanoi(3, 'A', 'B', 'C');
    }
}

class Fibonacci {

    static class Frame {
        int n;
        int stage;
        int fn_1;

        public Frame( int n, int stage, int fn_1) {
            this.n = n;
            this.stage = stage;
            this.fn_1 = fn_1;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("Fibonacci (" + n + "): " + fib(n));
        System.out.println("Fibonacci (" + n + "): " + fib2(n));
    }

    static int fib(int n) {
        Stack<Integer> stack = new Stack<>(3);

        if (n < 3) {
            if (n == 1) {
                return 0;
            }

            if (n == 2) {
                return 1;
            }
        }

        stack.push(0);
        stack.push(1);

        while (n-- != 2) {
            int f2 = stack.pop();
            int f1 = stack.pop();

            stack.push(f2);
            stack.push(f1 + f2);
        }

        return stack.pop();
    }

    static int fib2(int n) {
        Stack<Frame> stack = new Stack<>(1000) ;

        int lastResult = 0;

        stack.push(new Frame(n, 0, 0));

        while (!stack.isEmpty()) {
            Frame current = stack.pop();

            if (current.stage == 0) {

                if (current.n == 1) 
                    lastResult = 0;
                
                else if (current.n == 2)
                    lastResult = 1;

                else {
                    current.stage = 1;
                    stack.push(current);
                    stack.push(new Frame(current.n-1, 0, 0));
                } 
            }

            else if (current.stage == 1) {
                
                current.stage = 2;
                current.fn_1 = lastResult;
                stack.push(current);

                stack.push(new Frame(current.n-2, 0, 0));
            }

            else if (current.stage == 2) 
                lastResult += current.fn_1;

        }

        return lastResult;
    }
}

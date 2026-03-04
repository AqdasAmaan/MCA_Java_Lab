class GCD {
    static class Frame {
        int a, b;

        Frame (int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    public static void main (String[] args) {
        System.out.println("GCD:" + gcd(143, 297));
    }

    static int gcd(int a, int b) {
        Stack<Frame> stack = new Stack<>(1);

        stack.push(new Frame(a, b));

        while (!stack.isEmpty()) {
            Frame current = stack.pop();
            
            if (current.b == 0)
                return current.a;

            stack.push(new Frame(current.b, current.a % current.b));
        }

        return -1;
    }
}
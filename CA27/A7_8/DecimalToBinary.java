class DecimalToBinary {
    public static void main (String[] args) {
        int n = 1000;
        System.out.println("Binary Equivalent of " + n + ": " + toBinary(n));
    }

    static String toBinary(int n) {
        Stack<Integer> stack = new Stack<>(32);

        while (n > 0) {
            stack.push(n%2);
            n /= 2;
        }

        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()) 
            sb.append(stack.pop());

        return sb.toString();
    }
}
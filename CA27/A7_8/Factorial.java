class Factorial {
    public static int factorial (int n) {
        if (n < 0) 
            throw new RuntimeException("Error: Negative numbers don't have a fatorial.");

        Stack<Integer> stack = new Stack<>(n);
        
        while (n > 0) 
            stack.push(n--);
        
        int fact = 1;

        while (!stack.isEmpty())
            fact *= stack.pop();

        return fact;
    }

    public static void main(String[] args) {

        for (int i=0; i<10; i++)
            System.out.println("Factorial of " + i + ": " + factorial(i));

    }
}
import java.util.Scanner;

class StrOperation {

    String s;

    StrOperation(String s) {
        this.s = s.trim();
    }

    public void performOperations() {
        String lineBreak = "\n-------------------------------------------------";

        System.out.println("Original String: \n" + s + lineBreak);
        System.out.println("\nString With Each Word Reversed: \n" + reverseWords() + lineBreak);
        System.out.println("\nString In Sentence Case: \n" + sentenceCase() + lineBreak);
        System.out.println("\nString With Sentences Reversed: \n" + reverseSentence() + lineBreak);
    }

    public String sentenceCase() {

        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = true;

        for (char ch : s.toCharArray()) {
            if (capitalizeNext && Character.isLetter(ch)) {
                sb.append(Character.toUpperCase(ch));
                capitalizeNext = false;
            }
            else 
                sb.append(ch);

            if (ch == '.')
                capitalizeNext = true;

        }
        return sb.toString();
    }

    public String reverseWord(String word) {

        char[] c = word.trim().toCharArray();
        int n = c.length;
        for (int i=0; i<n / 2; i++) {
            char temp = c[i] ;
            c[i] = c[n-i-1];
            c[n-i-1] = temp;
        }

        return new String(c);
    }
    
    public String reverseWords() {
        StringBuilder sb = new StringBuilder();

        for (String x : s.split(" ")) {
            sb.append(reverseWord(x)).append(" ");
        }

        return sb.toString();
    }

    public String reverseSentence() {
        StringBuilder sb = new StringBuilder();
        String[] arr = s.split("");
        for (String x : arr) {
            
            if (x.endsWith("."))
                sb.append(reverseWord(x.substring(0,x.length()-1)));
            else
                sb.append(reverseWord(x));
        }
        sb.append(".");
        
        return sb.toString();
    }
}

class StringTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StrOperation sop = new StrOperation(input(sc));

        String menu = """
                    \n-------------Menu-----------
                    1. No. Of Words
                    2. No. Of Characters
                    3. No. Of Sentences
                    0. Exit

                    Your Choice: 
                    """;

        int ch;
        
        do { 
            System.out.print(menu);
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("No. Of Words: " + wordCount(sop.s));
                    break;

                case 2:
                    System.out.println("No. of Characters: " + characterCount(sop.s));
                    break;

                case 3:
                    System.out.println("No. of lines: " + lineCount(sop.s));
                    break;

                case 0:
                    System.out.print("Exit!");
                    break;

                default:
                    System.out.println("Invalid Choice!");

            }

            
        } while (ch!=0);
    }

    public static String input(Scanner sc) {
        StringBuilder s = new StringBuilder();
        String line ;

        System.out.println("Enter a string (Reading terminates on encountering the substring '#EOF'):");

        while (true) {
            line = sc.nextLine();

            if (line.contains("#EOF")) {
                s.append(line.substring(0, line.indexOf("#EOF")));
                break;
                
            }
            
            if (line.endsWith(".")) {
                s.append(line).append(" ");
                continue;
            }

            if (line.isEmpty())
                s.append("\n");

            else 
                s.append(line);

            s.append("\n");
        }

        return s.toString();
    }

    public static int wordCount(String s) {
        s = s.trim() + " ";
        int words = 0;

        for (char ch : s.toCharArray()) {
            if (ch == ' ') words++;
        }

        return words;
    }

    public static int characterCount(String s) {
        s = s.trim();
        int characters = 0;
        for (char c : s.toCharArray()) {
            if (!Character.isWhitespace(c)) characters++;
        }

        return characters;
    }

    public static int lineCount(String s) {
        s = s.trim();

        int lines = 0;

        for (char c: s.toCharArray()) {
            if (c == '\n') lines++ ;
        }

        return lines;
    }
}
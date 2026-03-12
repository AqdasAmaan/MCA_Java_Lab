
import java.util.Arrays;

class Main {
    public static void main(String[] args) {

        String[][] tests = {

            // Basic sorting
            {"dog", "cat", "bat", "ant"},

            // Already sorted
            {"ant", "bat", "cat", "dog"},

            // Reverse sorted
            {"zoo", "yak", "xenon", "wolf"},

            // Different lengths
            {"a", "abc", "ab", "abcd"},

            // Prefix case
            {"app", "apple", "ap"},

            // Duplicates
            {"dog", "cat", "dog", "bat"},

            // All duplicates
            {"face", "face", "face"},

            // Stability case
            {"dig", "fig", "big", "gig"},

            // Same suffix
            {"cage", "bage", "fage", "gage"},

            // Same last two characters
            {"abge", "bbge", "cbge"},

            // Empty string case
            {"", "a", "ab"},

            // Single element
            {"hello"}
        };


        String[][] expected = {

            {"ant","bat","cat","dog"},
            {"ant","bat","cat","dog"},
            {"wolf","xenon","yak","zoo"},
            {"a","ab","abc","abcd"},
            {"ap","app","apple"},
            {"bat","cat","dog","dog"},
            {"face","face","face"},
            {"big","dig","fig","gig"},
            {"bage","cage","fage","gage"},
            {"abge","bbge","cbge"},
            {"","a","ab"},
            {"hello"}
        };


        for (int i = 0; i < tests.length; i++) {

            String[] input = Arrays.copyOf(tests[i], tests[i].length);

            Main.sort(input);

            boolean pass = Arrays.equals(input, expected[i]);

            System.out.println("\nTest Case " + (i + 1));

            System.out.println("Input:    " + Arrays.toString(tests[i]));
            System.out.println("Expected: " + Arrays.toString(expected[i]));
            System.out.println("Actual:   " + Arrays.toString(input));

            System.out.println(pass ? "PASS" : "FAIL");
        }
    }

    public static void sort(String[] words) {
        int max_len = 0;

        for (String word : words)
            if (word.length() > max_len) max_len = word.length();

        LinkedList[] bucket = new LinkedList[27];

        for (int i = 0; i < bucket.length; i++)
            bucket[i] = new LinkedList();

        for (int pos = max_len - 1; pos >= 0; pos--) {

            for (String word : words) {
                char ch = (pos < word.length()) ? word.charAt(pos) : '\0';
                bucket[index(ch)].insert(word, bucket[index(ch)].size);
            }

            int j = 0;

            for (int i = 0; i < bucket.length; i++) {
                while (!bucket[i].isEmpty())
                    words[j++] = bucket[i].del(0);
            }
        }

        System.out.println("Sorted List of Words -->");
        System.out.println(Arrays.toString(words));
    }

    public static int index (char letter) {

        if (letter == '\0')
            return 0;
        return letter - 'a' + 1;
    }
}
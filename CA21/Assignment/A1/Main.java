
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        // String[] words = {
        //     "abide", "guide", "badge", "caged", "faced",
        //     "gaude", "obige", "afoge", "begad", "debag",
        //     "dice", "face", "cage", "fade", "aged",
        //     "bog", "dig", "fig", "big", "fed",
        //     "gab", "bag", "bed", "ice", "dog",
        //     "ab", "ac", "ad", "af", "ag",
        //     "ba", "be", "bi", "bo", "bu",
        //     "ego", "due", "obeid", "cafed", "gabed",
        //     "gaud", "foe", "aid", "obe", "fad"
        // };

        String[] words = {
            // Same last character 'e'
            "cage",
            "bage",
            "fage",
            "gage",

            // Same last two characters "ge"
            "abge",
            "bbge",
            "cbge",

            // Same last three characters "age"
            "bage",
            "cage",
            "gage",

            // Different lengths but same suffix
            "age",
            "ge",
            "e",

            // Same prefix but different suffix
            "abide",
            "abide",
            "abide",

            // Carefully ordered for stability check
            "dig",
            "fig",
            "big",
            "gig",

            // Same exact words in order (must remain in same order)
            "face",
            "face",
            "face"
        };

        sort(words);
    }

    public static void sort(String[] words) {
        int max_len = 0;

        for (String word : words)
            if (word.length() > max_len) max_len = word.length();

        System.out.println(max_len);

        LinkedList[] bucket = new LinkedList[11];

        for (int i=0; i<11; i++)
            bucket[i] = new LinkedList();
        

        int pos = 1;
        while (max_len-- > 0) {

            for (String word : words) {
                char ch = (word.length() >= pos) ? word.charAt(word.length() - pos) : '\0';

                bucket[index(ch)].insert(word, 0);
            }
            int j = 0;

            for (int i=0; i<11; i++) {
                while (!bucket[i].isEmpty())
                    words[j++] = bucket[i].del(bucket[i].size - 1);
            }

            pos++;
        }     

        System.out.println("Sorted List of Words -->");   
        System.out.print(Arrays.toString(words));
    }

    public static int index (char letter) {
        return switch(letter) {
            case '\0' -> 0;
            case 'a' -> 1;
            case 'b' -> 2;
            case 'c' -> 3;
            case 'd' -> 4;
            case 'e' -> 5;
            case 'f' -> 6;
            case 'g' -> 7;
            case 'i' -> 8;
            case 'o' -> 9;
            case 'u' -> 10;
            default -> -1;
        };
    }
}
public class LongestPalindromeUsingAPI {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;

        String longest = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {

                String sub = s.substring(i, j);
                
                // use Java API to reverse & check palindrome
                if (isPalindrome(sub) && sub.length() > longest.length()) {
                    longest = sub;
                }
            }
        }
        return longest;
    }

    private static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad")); // "bab" or "aba"
    }
}
/**
 * ****************************************************
 * LeetCode 5 — Longest Palindromic Substring
 * ****************************************************
 * Problem:
 *  Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 *  Input:  s = "babad"
 *  Output: "bab" ("aba" is also valid)
 *
 * Example 2:
 *  Input:  s = "cbbd"
 *  Output: "bb"
 *
 * Example 3:
 *  Input:  s = "a"
 *  Output: "a"
 *
 * Example 4:
 *  Input:  s = "ac"
 *  Output: "a" (or "c")
 *
 * Approach (Expand Around Center):
 *  - Every index (and gap) can be a center of a palindrome.
 *  - Expand outward while characters match to find longest for that center.
 *  - Track the best [start, end] window across all centers.
 *  - Time O(n^2) worst-case, space O(1).
 * ****************************************************
 */
public class LeetCode_5_Longest_Palindromic_Substring {
    // Returns the longest palindromic substring using center expansion.
    public static String longestPalindrome(String s) {
        int n = s.length();
        if (n <= 1) return s;

        int bestStart = 0, bestEnd = 0;

        for (int i = 0; i < n; i++) {
            int[] odd = expand(s, i, i);       // odd-length center at i
            int[] even = expand(s, i, i + 1);  // even-length center between i and i+1

            if (odd[1] - odd[0] > bestEnd - bestStart) {
                bestStart = odd[0];
                bestEnd = odd[1];
            }
            if (even[1] - even[0] > bestEnd - bestStart) {
                bestStart = even[0];
                bestEnd = even[1];
            }
        }

        return s.substring(bestStart, bestEnd + 1);
    }

    // Expand around center l,r and return the bounds of the longest palindrome.
    private static int[] expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return new int[]{l + 1, r - 1}; // last valid palindrome bounds
    }

    // Quick demo runner with sample inputs.
    public static void main(String[] args) {
        String[] samples = {
                "babad",
                "cbbd",
                "a",
                "ac",
                "forgeeksskeegfor",
                "abccccdd",
                "aaaa"
        };

        for (String s : samples) {
            System.out.printf("s='%s' -> longest palindrome='%s'%n", s, longestPalindrome(s));
        }
    }
}

/*
Walkthrough for s = "babad":
- Center at 0 ('b') => "b".
- Gap (0,1) => no even palindrome.
- Center at 1 ('a') => "bab" becomes best [0,2].
- Center at 2 ('b') => "aba" also length 3; keep first best.
Result "bab" ("aba" also valid).

Time Complexity: O(n^2) in worst case (e.g., all same chars).
Space Complexity: O(1) extra.
*/


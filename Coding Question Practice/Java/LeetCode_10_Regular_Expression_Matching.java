/**
 * ****************************************************
 * LeetCode 10 — Regular Expression Matching
 * ****************************************************
 * Problem:
 *  Implement regex matching with support for '.' and '*'.
 *  - '.' Matches any single character.
 *  - '*' Matches zero or more of the preceding element.
 *  Matching must cover the entire input string (not partial).
 *
 * Example 1:
 *  Input:  s = "aa", p = "a*"
 *  Output: true
 *
 * Example 2:
 *  Input:  s = "ab", p = ".*"
 *  Output: true
 *
 * Example 3:
 *  Input:  s = "aab", p = "c*a*b"
 *  Output: true
 *
 * Example 4:
 *  Input:  s = "mississippi", p = "mis*is*p*."
 *  Output: false
 *
 * Approach (Top-Down DP with Memoization):
 *  - Define dp(i, j): does s[i:] match p[j:]?
 *  - firstMatch when s[i] exists and (s[i] == p[j] or p[j] == '.').
 *  - If next pattern char is '*', we can skip pair (dp(i, j+2)) or, if
 *    firstMatch, consume one char (dp(i+1, j)).
 *  - Else, move both pointers when firstMatch holds (dp(i+1, j+1)).
 *  - Memoize (i, j) to achieve O(m*n) time and O(m*n) space.
 * ****************************************************
 */
public class LeetCode_10_Regular_Expression_Matching {
    // Returns true if s matches p (full match) with '.' and '*' support.
    public static boolean isMatch(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p, memo);
    }

    private static boolean dp(int i, int j, String s, String p, Boolean[][] memo) {
        if (memo[i][j] != null) return memo[i][j];

        boolean ans;
        if (j == p.length()) {
            ans = (i == s.length());
        } else {
            boolean firstMatch = (i < s.length()) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // Skip this char+* OR consume one if it matches
                ans = dp(i, j + 2, s, p, memo) || (firstMatch && dp(i + 1, j, s, p, memo));
            } else {
                ans = firstMatch && dp(i + 1, j + 1, s, p, memo);
            }
        }

        memo[i][j] = ans;
        return ans;
    }

    // Quick demo runner with sample inputs.
    public static void main(String[] args) {
        String[][] samples = {
                {"aa", "a"},
                {"aa", "a*"},
                {"ab", ".*"},
                {"aab", "c*a*b"},
                {"mississippi", "mis*is*p*.*"},
                {"", ".*"},
                {"", ""}
        };

        for (String[] pair : samples) {
            String s = pair[0];
            String p = pair[1];
            System.out.printf("s='%s', p='%s' -> %b%n", s, p, isMatch(s, p));
        }
    }
}

/*
Walkthrough for s = "aab", p = "c*a*b":
- dp(0,0): 'c*' can be skipped -> dp(0,2)
- dp(0,2): pattern 'a*'; firstMatch true. Options:
  * skip 'a*' -> dp(0,4)
  * consume one 'a' -> dp(1,2)
- dp(1,2): firstMatch true; consume again -> dp(2,2)
- dp(2,2): firstMatch false; skip 'a*' -> dp(2,4)
- dp(2,4): pattern 'b'; firstMatch true; move to end -> true.

Time Complexity: O(m * n) due to memo over (i, j).
Space Complexity: O(m * n) for memo; recursion depth O(m + n).
*/


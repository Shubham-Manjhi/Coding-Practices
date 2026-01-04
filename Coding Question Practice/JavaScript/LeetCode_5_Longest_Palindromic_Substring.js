/****************************************************
 * LeetCode 5 — Longest Palindromic Substring
 ****************************************************
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
 *  - Treat every index and gap as a palindrome center.
 *  - Expand outward while characters match to get longest for that center.
 *  - Track best start/end seen. O(n^2) time worst-case, O(1) space.
 ****************************************************/
function longestPalindrome(s) {
  if (s.length <= 1) return s;

  let bestStart = 0;
  let bestEnd = 0;

  const expand = (l, r) => {
    while (l >= 0 && r < s.length && s[l] === s[r]) {
      l -= 1;
      r += 1;
    }
    return [l + 1, r - 1]; // last valid palindrome bounds
  };

  for (let i = 0; i < s.length; i++) {
    const [l1, r1] = expand(i, i);     // odd center
    const [l2, r2] = expand(i, i + 1); // even center

    if (r1 - l1 > bestEnd - bestStart) {
      bestStart = l1;
      bestEnd = r1;
    }
    if (r2 - l2 > bestEnd - bestStart) {
      bestStart = l2;
      bestEnd = r2;
    }
  }

  return s.slice(bestStart, bestEnd + 1);
}

// Quick demo runner with sample inputs.
(() => {
  const samples = [
    "babad",
    "cbbd",
    "a",
    "ac",
    "forgeeksskeegfor",
    "abccccdd",
    "aaaa",
  ];

  for (const s of samples) {
    console.log(`s='${s}' -> longest palindrome='${longestPalindrome(s)}'`);
  }
})();

/*
Walkthrough for s = "babad":
- Center 0 => "b".
- Gap (0,1) => none.
- Center 1 => "bab" best so far.
- Center 2 => "aba" same length; keep first best.
Answer "bab" ("aba" valid too).

Time Complexity: O(n^2) worst case.
Space Complexity: O(1) extra.
*/


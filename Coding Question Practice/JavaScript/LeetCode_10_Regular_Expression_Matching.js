/****************************************************
 * LeetCode 10 — Regular Expression Matching
 ****************************************************
 * Problem:
 *  Implement regex matching with support for '.' and '*'.
 *  - '.' matches any single character.
 *  - '*' matches zero or more of the preceding element.
 *  Matching must cover the entire input string.
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
 *  - Define dp(i,j): does s[i:] match p[j:]?
 *  - firstMatch when s[i] exists and (s[i] === p[j] || p[j] === '.').
 *  - If p[j+1] is '*', either skip the pair (dp(i, j+2)) or, if firstMatch,
 *    consume one char (dp(i+1, j)).
 *  - Else move both forward when firstMatch holds (dp(i+1, j+1)).
 *  - Memoize (i,j) for O(m*n) time, O(m*n) space.
 ****************************************************/
function isMatch(s, p) {
  const memo = new Map(); // key: `${i},${j}` -> boolean

  const dp = (i, j) => {
    const key = `${i},${j}`;
    if (memo.has(key)) return memo.get(key);

    let ans;
    if (j === p.length) {
      ans = i === s.length;
    } else {
      const firstMatch = i < s.length && (s[i] === p[j] || p[j] === '.');

      if (j + 1 < p.length && p[j + 1] === '*') {
        ans = dp(i, j + 2) || (firstMatch && dp(i + 1, j));
      } else {
        ans = firstMatch && dp(i + 1, j + 1);
      }
    }

    memo.set(key, ans);
    return ans;
  };

  return dp(0, 0);
}

// Quick demo runner with sample inputs.
(() => {
  const samples = [
    ['aa', 'a'],
    ['aa', 'a*'],
    ['ab', '.*'],
    ['aab', 'c*a*b'],
    ['mississippi', 'mis*is*p*.*'],
    ['', '.*'],
    ['', ''],
  ];

  for (const [s, p] of samples) {
    console.log(`s='${s}', p='${p}' -> ${isMatch(s, p)}`);
  }
})();

/*
Walkthrough for s = "aab", p = "c*a*b":
- dp(0,0): 'c*' can be skipped -> dp(0,2)
- dp(0,2): 'a*'; firstMatch true. Options skip or consume.
- Consume twice then skip 'a*' yields dp(2,4) where 'b' matches -> true.

Time Complexity: O(m * n) with memoization.
Space Complexity: O(m * n) for memo; recursion depth O(m + n).
*/


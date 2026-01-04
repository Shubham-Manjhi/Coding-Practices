"""
****************************************************
LeetCode 10 — Regular Expression Matching
****************************************************
Problem:
  Implement regex matching with support for '.' and '*'.
  - '.' Matches any single character.
  - '*' Matches zero or more of the preceding element.
  The matching should cover the entire input string (not partial).

Example 1:
  Input:  s = "aa", p = "a*"
  Output: true

Example 2:
  Input:  s = "ab", p = ".*"
  Output: true

Example 3:
  Input:  s = "aab", p = "c*a*b"
  Output: true

Example 4:
  Input:  s = "mississippi", p = "mis*is*p*."
  Output: false

Approach (Top-Down DP with Memoization):
  - Define dp(i, j): does s[i:] match p[j:]?
  - First match when s[i] exists and (s[i] == p[j] or p[j] == '.').
  - If next pattern char is '*', we can skip this pair (dp(i, j+2)) or
    consume one char if first match holds (dp(i+1, j)).
  - Otherwise, move both pointers when first match holds (dp(i+1, j+1)).
  - Memoize (i, j) to avoid recomputation. Time O(m*n), space O(m*n).
****************************************************
"""
from functools import lru_cache


def is_match(s: str, p: str) -> bool:
    """Return True if s matches p where p supports '.' and '*' with full match."""

    @lru_cache(None)
    def dp(i: int, j: int) -> bool:
        if j == len(p):
            return i == len(s)

        first_match = i < len(s) and (s[i] == p[j] or p[j] == '.')

        # Check if next pattern char is '*'
        if j + 1 < len(p) and p[j + 1] == '*':
            # Option 1: skip the char and '*'; Option 2: consume one char if match
            return dp(i, j + 2) or (first_match and dp(i + 1, j))
        else:
            return first_match and dp(i + 1, j + 1)

    return dp(0, 0)


# ---------- Helpers and demo ----------
def _demo():
    samples = [
        ("aa", "a"),
        ("aa", "a*"),
        ("ab", ".*"),
        ("aab", "c*a*b"),
        ("mississippi", "mis*is*p*.*"),
        ("", ".*"),
        ("", ""),
    ]
    for s, p in samples:
        print(f"s='{s}', p='{p}' -> {is_match(s, p)}")


if __name__ == "__main__":
    _demo()

"""
Walkthrough for s = "aab", p = "c*a*b":
- dp(0,0): 'c*' can be skipped -> dp(0,2)
- dp(0,2): pattern 'a*'; first_match True ('a' vs 'a'). Options:
  * skip 'a*' -> dp(0,4)
  * consume one 'a' -> dp(1,2)
- dp(1,2): first_match True; consume again -> dp(2,2)
- dp(2,2): first_match False; skip 'a*' -> dp(2,4)
- dp(2,4): pattern 'b'; first_match True and ends -> True.

Time Complexity: O(m * n) with memoization over (i, j).
Space Complexity: O(m * n) for memo stack plus recursion depth O(m + n).
"""


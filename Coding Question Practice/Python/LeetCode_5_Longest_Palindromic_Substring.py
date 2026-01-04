"""
****************************************************
LeetCode 5 — Longest Palindromic Substring
****************************************************
Problem:
  Given a string s, return the longest palindromic substring in s.

Example 1:
  Input:  s = "babad"
  Output: "bab"  ("aba" is also valid)

Example 2:
  Input:  s = "cbbd"
  Output: "bb"

Example 3:
  Input:  s = "a"
  Output: "a"

Example 4:
  Input:  s = "ac"
  Output: "a" (or "c")

Approach (Expand Around Center):
  - A palindrome mirrors around its center. Every index (and gap between two
    indices) can be a center.
  - Expand outward while characters match to get the longest palindrome for
    that center.
  - Track the best start/end seen across all centers.
  - Time O(n^2) in worst case (e.g., all identical chars), space O(1).
****************************************************
"""
from typing import Tuple


def longest_palindrome(s: str) -> str:
    """Return the longest palindromic substring via center expansion."""
    if len(s) <= 1:
        return s

    start, end = 0, 0  # inclusive start, inclusive end for best window

    def expand(l: int, r: int) -> Tuple[int, int]:
        while l >= 0 and r < len(s) and s[l] == s[r]:
            l -= 1
            r += 1
        return l + 1, r - 1  # step back to last valid palindrome bounds

    for i in range(len(s)):
        l1, r1 = expand(i, i)       # odd-length center at i
        l2, r2 = expand(i, i + 1)   # even-length center between i and i+1

        if r1 - l1 > end - start:
            start, end = l1, r1
        if r2 - l2 > end - start:
            start, end = l2, r2

    return s[start : end + 1]


# ---------- Helpers and demo ----------
def _demo():
    samples = [
        "babad",
        "cbbd",
        "a",
        "ac",
        "forgeeksskeegfor",
        "abccccdd",
        "aaaa",
    ]
    for text in samples:
        print(f"s='{text}' -> longest palindrome='{longest_palindrome(text)}'")


if __name__ == "__main__":
    _demo()

"""
Walkthrough for s = "babad":
- Center at index 0 ('b'): expands to "b" (length 1).
- Center at gap (0,1) ('b' vs 'a'): no even palindrome.
- Center at index 1 ('a'): expands to "bab"; best becomes [0,2].
- Center at gap (1,2) ('a' vs 'b'): no even palindrome.
- Center at index 2 ('b'): expands to "aba"; same length 3, keep first best.
- Continue; final answer "bab" ("aba" also valid).

Time Complexity: O(n^2) — each expansion can traverse the string in worst case.
Space Complexity: O(1) — constant extra space.
"""


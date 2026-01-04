"""
****************************************************
LeetCode 4 — Median of Two Sorted Arrays
****************************************************
Problem:
  Given two sorted arrays nums1 and nums2 of size m and n, return the median
  of the two sorted arrays. The overall run time complexity should be
  O(log(m + n)).

Example 1:
  Input:  nums1 = [1,3], nums2 = [2]
  Output: 2.0

Example 2:
  Input:  nums1 = [1,2], nums2 = [3,4]
  Output: 2.5

Example 3:
  Input:  nums1 = [0,0], nums2 = [0,0]
  Output: 0.0

Example 4:
  Input:  nums1 = [2], nums2 = []
  Output: 2.0

Approach (Binary Search Partition on Shorter Array):
  - Always binary search on the shorter array to keep the search space small.
  - Choose a cut position i in nums1, derive j so that left partition has
    (m + n + 1) // 2 elements. Compute border values around the cut.
  - If maxLeft1 <= minRight2 and maxLeft2 <= minRight1, correct partition
    found; median is average of middle two when total length even, else max
    of lefts when odd.
  - If maxLeft1 > minRight2, move search left; else move right.
  - Runs in O(log(min(m, n))) time, O(1) extra space.
****************************************************
"""
from typing import List


def find_median_sorted_arrays(nums1: List[int], nums2: List[int]) -> float:
    """Return the median of two sorted arrays in O(log(min(m,n))) time."""
    # Ensure nums1 is the shorter array for minimal search range.
    if len(nums1) > len(nums2):
        nums1, nums2 = nums2, nums1

    m, n = len(nums1), len(nums2)
    total_left = (m + n + 1) // 2  # size of combined left partition

    low, high = 0, m
    while low <= high:
        i = (low + high) // 2  # cut in nums1
        j = total_left - i     # cut in nums2 derived from i

        # Border values (use +/-inf when cut is at array boundary)
        max_left1 = nums1[i - 1] if i > 0 else float("-inf")
        min_right1 = nums1[i] if i < m else float("inf")

        max_left2 = nums2[j - 1] if j > 0 else float("-inf")
        min_right2 = nums2[j] if j < n else float("inf")

        if max_left1 <= min_right2 and max_left2 <= min_right1:
            if (m + n) % 2 == 0:
                return (max(max_left1, max_left2) + min(min_right1, min_right2)) / 2.0
            return float(max(max_left1, max_left2))

        if max_left1 > min_right2:
            high = i - 1  # need smaller i
        else:
            low = i + 1   # need larger i

    raise ValueError("Input arrays are not sorted as required.")


# ---------- Helpers and demo ----------
def _demo():
    samples = [
        ([1, 3], [2]),
        ([1, 2], [3, 4]),
        ([0, 0], [0, 0]),
        ([2], []),
        ([1, 2, 3], [4, 5, 6, 7]),
        ([1, 2], [3]),
    ]
    for a, b in samples:
        median = find_median_sorted_arrays(a, b)
        print(f"nums1={a}, nums2={b} -> median={median}")


if __name__ == "__main__":
    _demo()

"""
Walkthrough for nums1 = [1,3], nums2 = [2]:
- nums1 shorter already. m=2, n=1, total_left=2.
- low=0, high=2 -> i=1, j=1.
  max_left1=1, min_right1=3, max_left2=2, min_right2=+inf.
  max_left1 <= min_right2 and max_left2 <= min_right1, partition correct.
  total length odd (3); median = max(max_left1, max_left2) = 2.

Time Complexity: O(log(min(m, n))) due to binary search on smaller array.
Space Complexity: O(1) extra; uses only constant additional variables.
"""


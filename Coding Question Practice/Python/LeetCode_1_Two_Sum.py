"""
****************************************************
LeetCode 1 — Two Sum
****************************************************
Problem:
 Given an integer array nums and an integer target, return the indices of the
 two numbers such that they add up to target. Each input has exactly one
 solution; you may not use the same element twice. Return indices in any order.

Example 1:
 Input:  nums = [2,7,11,15], target = 9
 Output: [0,1]  # nums[0] + nums[1] = 9

Example 2:
 Input:  nums = [3,2,4], target = 6
 Output: [1,2]

Example 3:
 Input:  nums = [3,3], target = 6
 Output: [0,1]

Approach (HashMap - One Pass):
 - Maintain a dictionary mapping value -> index while iterating.
 - For current nums[i], complement = target - nums[i].
 - If complement is already in the map, return [index_of_complement, i].
 - Otherwise store nums[i] with index i and continue.
 - Runs in O(n) time with O(n) space.
****************************************************
"""
from typing import List


def two_sum(nums: List[int], target: int) -> List[int]:
    """Return indices of the two numbers that add up to target using one-pass hash map."""
    seen = {}  # value -> index
    for i, num in enumerate(nums):
        complement = target - num
        if complement in seen:
            return [seen[complement], i]
        seen[num] = i
    return []  # fallback; LeetCode guarantees one solution


def _demo():
    samples = [
        ([2, 7, 11, 15], 9),
        ([3, 2, 4], 6),
        ([3, 3], 6),
        ([-1, -2, -3, -4, -5], -8),
        ([0, 4, 3, 0], 0),
    ]
    for nums, target in samples:
        ans = two_sum(nums, target)
        values = (nums[ans[0]], nums[ans[1]]) if len(ans) == 2 else None
        print(f"nums={nums}, target={target} -> indices={ans}, values={values}")


if __name__ == "__main__":
    _demo()

"""
Walkthrough on nums = [2,7,11,15], target = 9:
- i=0, num=2, complement=7; 7 not in seen; store 2->0.
- i=1, num=7, complement=2; 2 is in seen at index 0; return [0,1].

Time Complexity: O(n) — single pass through nums.
Space Complexity: O(n) — dict holds up to all elements.
"""


/****************************************************
 * LeetCode 1 — Two Sum
 ****************************************************
 * Problem:
 *  Given an integer array nums and an integer target, return indices of the two
 *  numbers such that they add up to target. Each input has exactly one
 *  solution; you may not use the same element twice. Return indices in any order.
 *
 * Example 1:
 *  Input:  nums = [2,7,11,15], target = 9
 *  Output: [0,1]
 *
 * Example 2:
 *  Input:  nums = [3,2,4], target = 6
 *  Output: [1,2]
 *
 * Example 3:
 *  Input:  nums = [3,3], target = 6
 *  Output: [0,1]
 *
 * Approach (HashMap - One Pass):
 *  - Traverse nums while keeping a map value -> index.
 *  - For each nums[i], complement = target - nums[i].
 *  - If complement exists in map, return [map[complement], i].
 *  - Else store nums[i] in map and continue. O(n) time, O(n) space.
 ****************************************************/
function twoSum(nums, target) {
  const seen = new Map(); // value -> index
  for (let i = 0; i < nums.length; i++) {
    const complement = target - nums[i];
    if (seen.has(complement)) {
      return [seen.get(complement), i];
    }
    seen.set(nums[i], i);
  }
  return []; // fallback; LeetCode guarantees a solution
}

// Quick demo runner with sample inputs.
(function demo() {
  const samples = [
    { nums: [2, 7, 11, 15], target: 9 },
    { nums: [3, 2, 4], target: 6 },
    { nums: [3, 3], target: 6 },
    { nums: [-1, -2, -3, -4, -5], target: -8 },
    { nums: [0, 4, 3, 0], target: 0 },
  ];

  for (const { nums, target } of samples) {
    const ans = twoSum(nums, target);
    const values = ans.length === 2 ? [nums[ans[0]], nums[ans[1]]] : null;
    console.log(`nums=${JSON.stringify(nums)}, target=${target} -> indices=${JSON.stringify(ans)}, values=${JSON.stringify(values)}`);
  }
})();

/*
Walkthrough on nums = [2,7,11,15], target = 9:
- i=0, nums[i]=2, complement=7; 7 not seen; store 2->0.
- i=1, nums[i]=7, complement=2; 2 is seen at index 0; return [0,1].

Time Complexity: O(n) — single pass through the array.
Space Complexity: O(n) — map can store up to all elements.
*/


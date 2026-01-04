/****************************************************
 * LeetCode 4 — Median of Two Sorted Arrays
 ****************************************************
 * Problem:
 *  Given two sorted arrays nums1 and nums2 of size m and n, return the median
 *  of the two sorted arrays. The run time must be O(log(m + n)).
 *
 * Example 1:
 *  Input:  nums1 = [1,3], nums2 = [2]
 *  Output: 2.0
 *
 * Example 2:
 *  Input:  nums1 = [1,2], nums2 = [3,4]
 *  Output: 2.5
 *
 * Example 3:
 *  Input:  nums1 = [0,0], nums2 = [0,0]
 *  Output: 0.0
 *
 * Example 4:
 *  Input:  nums1 = [2], nums2 = []
 *  Output: 2.0
 *
 * Approach (Binary Search Partition on Shorter Array):
 *  - Always binary search the shorter array to minimize search space.
 *  - Pick cut i in nums1, derive cut j so left side has (m+n+1)/2 elements.
 *  - Check borders: maxLeft1 <= minRight2 and maxLeft2 <= minRight1.
 *    If true, we found the correct partition; compute median accordingly.
 *  - If maxLeft1 > minRight2, move i left; else move i right.
 *  - O(log(min(m, n))) time, O(1) extra space.
 ****************************************************/
function findMedianSortedArrays(nums1, nums2) {
  if (nums1.length > nums2.length) {
    return findMedianSortedArrays(nums2, nums1); // ensure nums1 is shorter
  }

  const m = nums1.length;
  const n = nums2.length;
  const totalLeft = Math.floor((m + n + 1) / 2);

  let low = 0;
  let high = m;

  while (low <= high) {
    const i = Math.floor((low + high) / 2); // cut in nums1
    const j = totalLeft - i;                // cut in nums2 derived from i

    const maxLeft1 = i === 0 ? -Infinity : nums1[i - 1];
    const minRight1 = i === m ? Infinity : nums1[i];

    const maxLeft2 = j === 0 ? -Infinity : nums2[j - 1];
    const minRight2 = j === n ? Infinity : nums2[j];

    if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
      if ((m + n) % 2 === 0) {
        return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2;
      }
      return Math.max(maxLeft1, maxLeft2);
    }

    if (maxLeft1 > minRight2) {
      high = i - 1; // move left
    } else {
      low = i + 1;  // move right
    }
  }

  throw new Error("Input arrays must be sorted.");
}

// Quick demo runner with sample inputs.
(() => {
  const samples = [
    { a: [1, 3], b: [2] },
    { a: [1, 2], b: [3, 4] },
    { a: [0, 0], b: [0, 0] },
    { a: [2], b: [] },
    { a: [1, 2, 3], b: [4, 5, 6, 7] },
    { a: [1, 2], b: [3] },
  ];

  for (const { a, b } of samples) {
    const median = findMedianSortedArrays(a, b);
    console.log(`nums1=${JSON.stringify(a)}, nums2=${JSON.stringify(b)} -> median=${median}`);
  }
})();

/*
Walkthrough for nums1 = [1,3], nums2 = [2]:
- nums1 already shorter. m=2, n=1, totalLeft=2.
- low=0, high=2 -> i=1, j=1.
  maxLeft1=1, minRight1=3, maxLeft2=2, minRight2=+inf.
  Conditions hold; length odd (3); median=max(1,2)=2.

Time Complexity: O(log(min(m, n))) — binary search on shorter array.
Space Complexity: O(1) — constant extra space.
*/


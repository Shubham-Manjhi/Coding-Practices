import java.util.Arrays;

/**
 * ****************************************************
 * LeetCode 4 — Median of Two Sorted Arrays
 * ****************************************************
 * Problem:
 *  Given two sorted arrays nums1 and nums2 of size m and n respectively,
 *  return the median of the two sorted arrays. The overall run time
 *  complexity should be O(log(m + n)).
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
 *  - Always binary search on the shorter array to minimize search space.
 *  - Pick a cut i in nums1 and derive cut j in nums2 so left partitions hold
 *    (m + n + 1) / 2 elements. Compare border values around the cuts.
 *  - If maxLeft1 <= minRight2 and maxLeft2 <= minRight1, we found the correct
 *    partition. Median is max of lefts (odd length) or avg of middle two
 *    (even length).
 *  - If maxLeft1 > minRight2, move i left; else move i right.
 *  - Runs in O(log(min(m, n))) time with O(1) extra space.
 * ****************************************************
 */
public class LeetCode_4_Median_of_Two_Sorted_Arrays {
    // Returns median of two sorted arrays in O(log(min(m,n))) time.
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1); // ensure nums1 is shorter
        }

        int m = nums1.length, n = nums2.length;
        int totalLeft = (m + n + 1) / 2; // size of combined left partition

        int low = 0, high = m;
        while (low <= high) {
            int i = (low + high) / 2;    // cut in nums1
            int j = totalLeft - i;        // cut in nums2 derived from i

            int maxLeft1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRight1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int maxLeft2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRight2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((m + n) % 2 == 0) {
                    int leftMax = Math.max(maxLeft1, maxLeft2);
                    int rightMin = Math.min(minRight1, minRight2);
                    return (leftMax + rightMin) / 2.0;
                }
                return (double) Math.max(maxLeft1, maxLeft2);
            }

            if (maxLeft1 > minRight2) {
                high = i - 1; // move left
            } else {
                low = i + 1;  // move right
            }
        }

        throw new IllegalArgumentException("Input arrays must be sorted.");
    }

    // Quick demo runner with sample inputs.
    public static void main(String[] args) {
        int[][] aSamples = {
                {1, 3},
                {1, 2},
                {0, 0},
                {2},
                {1, 2, 3},
                {1, 2}
        };
        int[][] bSamples = {
                {2},
                {3, 4},
                {0, 0},
                {},
                {4, 5, 6, 7},
                {3}
        };

        for (int k = 0; k < aSamples.length; k++) {
            double median = findMedianSortedArrays(aSamples[k], bSamples[k]);
            System.out.printf("nums1=%s, nums2=%s -> median=%.2f%n",
                    Arrays.toString(aSamples[k]), Arrays.toString(bSamples[k]), median);
        }
    }
}

/*
Walkthrough for nums1 = [1,3], nums2 = [2]:
- nums1 already shorter. m=2, n=1, totalLeft=2.
- low=0, high=2 -> i=1, j=1.
  maxLeft1=1, minRight1=3, maxLeft2=2, minRight2=+inf.
  Both partition conditions hold; total length 3 (odd) so median=max(1,2)=2.

Time Complexity: O(log(min(m, n))) — binary search on shorter array.
Space Complexity: O(1) — constant extra space.
*/


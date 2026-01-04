import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ****************************************************
 * LeetCode 1 — Two Sum
 * ****************************************************
 * Problem:
 *  Given an integer array nums and an integer target, return the indices of the
 *  two numbers such that they add up to target. You may assume that each input
 *  would have exactly one solution, and you may not use the same element twice.
 *  You can return the answer in any order.
 *
 * Example 1:
 *  Input:  nums = [2,7,11,15], target = 9
 *  Output: [0,1] // nums[0] + nums[1] = 9
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
 *  - Keep a map from number -> its index as we iterate.
 *  - For each number nums[i], compute complement = target - nums[i].
 *  - If complement already exists in the map, we found the pair; return indices.
 *  - Otherwise store nums[i] with index i and continue.
 *  - This is O(n) time and O(n) space.
 * ****************************************************
 */
public class LeetCode_1_Two_Sum {
    // Returns indices of the two numbers that sum to target using one-pass hash map.
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>(); // value -> index
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }
            seen.put(nums[i], i);
        }
        // Problem guarantees a solution; this is a fallback for completeness.
        return new int[]{};
    }

    // Quick demo runner with a few sample inputs.
    public static void main(String[] args) {
        int[][] samples = {
                {2, 7, 11, 15},
                {3, 2, 4},
                {3, 3},
                {-1, -2, -3, -4, -5},
                {0, 4, 3, 0}
        };
        int[] targets = {9, 6, 6, -8, 0};

        for (int i = 0; i < samples.length; i++) {
            int[] nums = samples[i];
            int target = targets[i];
            int[] ans = twoSum(nums, target);
            System.out.printf("nums=%s, target=%d -> indices=%s, values=(%d,%d)%n",
                    Arrays.toString(nums),
                    target,
                    Arrays.toString(ans),
                    ans.length == 2 ? nums[ans[0]] : null,
                    ans.length == 2 ? nums[ans[1]] : null);
        }
    }
}

/*
Walkthrough on nums = [2,7,11,15], target = 9:
- i=0, nums[i]=2, complement=7; 7 not seen; store 2->0.
- i=1, nums[i]=7, complement=2; 2 is seen at index 0; return [0,1].

Time Complexity: O(n) — single pass through the array.
Space Complexity: O(n) — hash map may store every element in worst case.
*/


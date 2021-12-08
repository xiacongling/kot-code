package me.xiacongling.code.leet.k0.h6

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.FailReason
import me.xiacongling.code.leet.Frustrating
import me.xiacongling.code.leet.Solution

@Solution(
    id = 689, title = "三个无重叠子数组的最大和", difficulty = Difficulty.HARD, description = """
给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且
3 * k 项的和最大的子数组，并返回这三个子数组。

以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。
如果有多个结果，返回字典序最小的一个。

示例 1：
输入：nums = [1,2,1,2,6,7,5,1], k = 2
输出：[0,3,5]
解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。

示例 2：
输入：nums = [1,2,1,2,1,2,1,2,1], k = 2
输出：[0,2,4]

提示：
* 1 <= nums.length <= 2 * 10^4
* 1 <= nums[i] < 2^16
* 1 <= k <= floor(nums.length / 3)

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun maxSumOfThreeSubarrays(nums: IntArray, k: Int): IntArray {
    @Frustrating(reason = FailReason.TLE)
    fun badSolution(): IntArray {
        val sums: IntArray = nums.scan(0) { a, b -> a + b }.toIntArray()

        val partialResult: IntArray = intArrayOf(0, 0, 0)
        var partialSum = 0

        var result: IntArray = intArrayOf(0, 0, 0)
        var maxSum = 0

        fun find(n: Int, start: Int) {
            val need: Int = (3 - n) * k
            if (n == 3) {
                if (partialSum > maxSum) {
                    result = partialResult.clone()
                    maxSum = partialSum
                }
                return
            }
            for (i in start..(nums.size - need)) {
                partialResult[n] = i
                val sum: Int = sums[i + k] - sums[i]
                partialSum += sum
                find(n + 1, i + k)
                partialSum -= sum
            }
        }

        find(0, 0)
        return result
    }

    var result: IntArray = intArrayOf(0, 0, 0)

    var sums: IntArray = intArrayOf(0, 0, 0)
    val maxSums: IntArray = intArrayOf(0, 0, 0)
    val index: IntArray = intArrayOf(0, 0, 0)
    val maxIndex: IntArray = intArrayOf(0, 0, 0)

    for (i in k * 2 until nums.size) {
        sums = sums.mapIndexed { idx, v -> v + nums[i - (2 - idx) * k] }.toIntArray()
        if (i >= k * 3 - 1) {
            if (sums[0] > maxSums[0]) {
                maxSums[0] = sums[0]
                index[0] = i - k * 3 + 1
            }
            if (maxSums[0] + sums[1] > maxSums[1]) {
                maxSums[1] = maxSums[0] + sums[1]
                maxIndex[0] = index[0]
                maxIndex[1] = i - k * 2 + 1
            }
            if (maxSums[1] + sums[2] > maxSums[2]) {
                maxSums[2] = maxSums[1] + sums[2]
                maxIndex[2] = i - k + 1
                result = maxIndex.clone()
            }
            sums = sums.mapIndexed { idx, v -> v - nums[i - (3 - idx) * k + 1] }.toIntArray()
        }
    }
    return result
}
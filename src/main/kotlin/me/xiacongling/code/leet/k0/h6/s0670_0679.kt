package me.xiacongling.code.leet.k0.h6

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 673, title = "最长递增子序列的个数", difficulty = Difficulty.MEDIUM, description = """
给定一个未排序的整数数组，找到最长递增子序列的个数。

示例 1:
输入: [1,3,5,4,7]
输出: 2
解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。

示例 2:
输入: [2,2,2,2,2]
输出: 5
解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。

注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findNumberOfLIS(nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    var lengths: Array<Pair<Int, Int>> = arrayOf(Pair(1, 1))
    var globalMaxLen = 1

    for (i in 1 until nums.size) {
        var maxLen = 1
        var cnt = 1
        for (j in 0 until i) {
            if (nums[j] < nums[i]) {
                val len = lengths[j].first + 1
                if (len > maxLen) {
                    maxLen = len
                    cnt = lengths[j].second
                } else if (len == maxLen) {
                    cnt += lengths[j].second
                }
            }
        }
        lengths += Pair(maxLen, cnt)
        globalMaxLen = maxOf(globalMaxLen, maxLen)
    }
    return lengths
        .filter { it.first == globalMaxLen }
        .fold(0) { s, p -> s + p.second }
}

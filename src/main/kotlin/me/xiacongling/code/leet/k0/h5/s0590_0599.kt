package me.xiacongling.code.leet.k0.h5

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 594, title = "最长和谐子序列", difficulty = Difficulty.EASY, description = """
和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。

现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。

数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。

示例 1：
输入：nums = [1,3,2,2,5,2,3,7]
输出：5
解释：最长的和谐子序列是 [3,2,2,2,3]

示例 2：
输入：nums = [1,2,3,4]
输出：2

示例 3：
输入：nums = [1,1,1,1]
输出：0

提示：
* 1 <= nums.length <= 2 * 10^4
* -10^9 <= nums[i] <= 10^9

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-harmonious-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findLHS(nums: IntArray): Int {
    val m = hashMapOf<Int, Int>()
    var result = 0
    for (num in nums) {
        val v1 = m.compute(num) { _, v -> (v ?: 0) + 1 }!!
        result = kotlin.math.max(result, v1 + m.getOrDefault(num + 1, -v1))
        result = kotlin.math.max(result, v1 + m.getOrDefault(num - 1, -v1))
    }
    return result
}

@Solution(
    id = 598, title = "范围求和II", difficulty = Difficulty.EASY, description = """
给定一个初始元素全部为 0，大小为 m*n 的矩阵 M 以及在 M 上的一系列更新操作。

操作用二维数组表示，其中的每个操作用一个含有两个正整数 a 和 b 的数组表示，含义是
将所有符合 0 <= i < a 以及 0 <= j < b 的元素 M[i][j] 的值都增加 1。

在执行给定的一系列操作后，你需要返回矩阵中含有最大整数的元素个数。

示例 1:
输入: 
m = 3, n = 3
operations = [[2,2],[3,3]]
输出: 4
解释: 
初始状态, M = 
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]

执行完操作 [2,2] 后, M = 
[[1, 1, 0],
 [1, 1, 0],
 [0, 0, 0]]

执行完操作 [3,3] 后, M = 
[[2, 2, 1],
 [2, 2, 1],
 [1, 1, 1]]

M 中最大的整数是 2, 而且 M 中有4个值为2的元素。因此返回 4。

注意:
1. m 和 n 的范围是 [1,40000]。
2. a 的范围是 [1,m]，b 的范围是 [1,n]。
3. 操作数目不超过 10000。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/range-addition-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun maxCount(m: Int, n: Int, ops: Array<IntArray>): Int {
    var a = m
    var b = n
    for (op in ops) {
        a = kotlin.math.min(a, op[0])
        b = kotlin.math.min(b, op[1])
    }
    return a * b
}

fun main() {
    // 594
    println(findLHS(intArrayOf(1, 3, 2, 2, 5, 2, 3, 7)))
    println(findLHS(intArrayOf(1, 2, 3, 4)))
    println(findLHS(intArrayOf(1, 1, 1, 1)))
    // 598
    println(maxCount(3, 3, arrayOf(intArrayOf(2, 2), intArrayOf(3, 3))))
    println(maxCount(3, 3, arrayOf(intArrayOf(1, 3), intArrayOf(2, 2))))
    println(maxCount(3, 3, arrayOf(intArrayOf(1, 1))))
}
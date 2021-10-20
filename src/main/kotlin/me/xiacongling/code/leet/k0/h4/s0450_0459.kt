package me.xiacongling.code.leet.k0.h4

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 453, title = "最小操作次数使数组元素相等", difficulty = Difficulty.EASY, description = """
给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。

示例 1：

输入：nums = [1,2,3]
输出：3
解释：
只需要3次操作（注意每次操作会增加两个元素的值）：
[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

示例 2：
输入：nums = [1,1,1]
输出：0

提示：
* n == nums.length
* 1 <= nums.length <= 10^5
* -10^9 <= nums[i] <= 10^9
* 答案保证符合 32-bit 整数

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun minMoves(nums: IntArray): Int {
    // (n-1个元素加1) 与 (1个元素减1) 是等价的
    var sum = 0L
    var min: Int = Int.MAX_VALUE
    for (num in nums) {
        sum += num
        if (num < min) min = num
    }
    return (sum - min * nums.size).toInt()
}
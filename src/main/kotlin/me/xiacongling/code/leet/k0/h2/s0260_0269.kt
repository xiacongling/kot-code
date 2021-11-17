package me.xiacongling.code.leet.k0.h2

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 260, title = "", difficulty = Difficulty.MEDIUM, description = """
给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
找出只出现一次的那两个元素。你可以按任意顺序返回答案。

进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？

示例 1：
输入：nums = [1,2,1,3,2,5]
输出：[3,5]
解释：[5, 3] 也是有效的答案。

示例 2：
输入：nums = [-1,0]
输出：[-1,0]

示例 3：
输入：nums = [0,1]
输出：[1,0]

提示：
* 2 <= nums.length <= 3 * 10^4
* -2^31 <= nums[i] <= 2^31 - 1
* 除两个只出现一次的整数外，nums 中的其他数字都出现两次

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/single-number-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun singleNumber(nums: IntArray): IntArray {
    val f = nums.reduce { a, b -> a xor b }
    val b = f.takeLowestOneBit()
    var x = 0
    var y = 0
    for (num in nums) {
        if (num and b != 0) x = x xor num else y = y xor num
    }
    return intArrayOf(x, y)
}

@Solution(
    id = 268, title = "", difficulty = Difficulty.EASY, description = """
给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。

示例 1：
输入：nums = [3,0,1]
输出：2
解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。

示例 2：
输入：nums = [0,1]
输出：2
解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。

示例 3：
输入：nums = [9,6,4,2,3,5,7,0,1]
输出：8
解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。

示例 4：
输入：nums = [0]
输出：1
解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。

提示：
* n == nums.length
* 1 <= n <= 10^4
* 0 <= nums[i] <= n
nums 中的所有数字都 独一无二

进阶：你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/missing-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun missingNumber(nums: IntArray): Int {
    fun solution1(): Int {
        val n = nums.size
        for (num in nums) {
            var t = num
            while (t < n && nums[t] != t) {
                nums[t] = t.also { t = nums[t] }
            }
        }
        return nums.withIndex().find { it.index != it.value }?.index ?: n
    }

    fun solution2(): Int {
        val t = nums.reduce { a, b -> a xor b }
        return (1..(nums.size)).fold(t) { a, b -> a xor b }
    }

    return solution2()
}

fun main() {
    println(missingNumber(intArrayOf(3, 0, 1)))
    println(missingNumber(intArrayOf(1, 0)))
    println(missingNumber(intArrayOf(9, 6, 4, 2, 3, 5, 7, 0, 1)))
    println(missingNumber(intArrayOf(0)))
}
package me.xiacongling.code.leet.k0.h4

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 421, title = "数组中两个数的最大异或值", difficulty = Difficulty.MEDIUM, description = """
给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。

进阶：你可以在 O(n) 的时间解决这个问题吗？

示例 1：
输入：nums = [3,10,5,25,2,8]
输出：28
解释：最大运算结果是 5 XOR 25 = 28.

示例 2：
输入：nums = [0]
输出：0

示例 3：
输入：nums = [2,4]
输出：6

示例 4：
输入：nums = [8,10,2]
输出：10

示例 5：
输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
输出：127

提示：
* 1 <= nums.length <= 2 * 10^4
* 0 <= nums[i] <= 2^31 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findMaximumXOR(nums: IntArray): Int {
    if (nums.size < 3) {
        return nums.reduce { x, y -> x xor y }
    }
    return 0
}

@Solution(
    id = 423, title = "从英文中重建数字", difficulty = Difficulty.MEDIUM, description = """
给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。

示例 1：
输入：s = "owoztneoer"
输出："012"

示例 2：
输入：s = "fviefuro"
输出："45"

提示：
* 1 <= s.length <= 10^5
* s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一
* s 保证是一个符合题目要求的字符串

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reconstruct-original-digits-from-english
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun originalDigits(s: String): String {
    // zero one two three four five six seven eight nine
    val cnt = s.groupingBy { it }.eachCount()
    val digits = IntArray(10)
    digits[0] = cnt.getOrDefault('z', 0)
    digits[2] = cnt.getOrDefault('w', 0)
    digits[4] = cnt.getOrDefault('u', 0)
    digits[6] = cnt.getOrDefault('x', 0)
    digits[8] = cnt.getOrDefault('g', 0)

    digits[1] = cnt.getOrDefault('o', 0) - digits[0] - digits[2] - digits[4]
    digits[3] = cnt.getOrDefault('h', 0) - digits[8]
    digits[5] = cnt.getOrDefault('f', 0) - digits[4]
    digits[7] = cnt.getOrDefault('v', 0) - digits[5]
    digits[9] = cnt.getOrDefault('i', 0) - digits[5] - digits[6] - digits[8]

    return digits.withIndex().joinToString("") { it.index.toString().repeat(it.value) }
}

fun main() {
    // 421
    println(findMaximumXOR(intArrayOf(0)))
    println(findMaximumXOR(intArrayOf(2)))
    println(findMaximumXOR(intArrayOf(2, 4)))
    println(findMaximumXOR(intArrayOf(3, 10, 5, 25, 2, 8)))
    println(findMaximumXOR(intArrayOf(8, 10, 2)))
    println(findMaximumXOR(intArrayOf(14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70)))

    // 423
    println(originalDigits("oneoneone"))
    println(originalDigits("owoztneoer"))
    println(originalDigits("fviefuro"))
}
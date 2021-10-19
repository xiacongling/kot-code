package me.xiacongling.code.leet.k0.h1

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution
import kotlin.math.absoluteValue
import kotlin.math.sign

@Solution(
    id = 162, title = "寻找峰值", difficulty = Difficulty.MEDIUM, description = """
峰值元素是指其值严格大于左右相邻值的元素。

给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。

你必须实现时间复杂度为 O(log n) 的算法来解决此问题。

示例 1：

输入：nums = [1,2,3,1]
输出：2
解释：3 是峰值元素，你的函数应该返回其索引 2。

示例 2：
输入：nums = [1,2,1,3,5,6,4]
输出：1 或 5 
解释：你的函数可以返回索引 1，其峰值元素为 2；或者返回索引 5， 其峰值元素为 6。

提示：
* 1 <= nums.length <= 1000
* -2^31 <= nums[i] <= 2^31 - 1
* 对于所有有效的 i 都有 nums[i] != nums[i + 1]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-peak-element
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findPeakElement(nums: IntArray): Int {
    if (nums.size <= 1) return 0
    var (s, e) = arrayOf(0, nums.size - 1)
    while (s < e) {
        val m = (s + e) / 2
        if (nums[m] > nums[m + 1]) {
            e = m
        } else {
            s = m + 1
        }
    }
    return s
}

@Solution(
    id = 166, title = "分数到小数", difficulty = Difficulty.MEDIUM, description = """
给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。

如果小数部分为循环小数，则将循环的部分括在括号内。

如果存在多个答案，只需返回任意一个 。

对于所有给定的输入，保证答案字符串的长度小于 10^4 。

示例 1：
输入：numerator = 1, denominator = 2
输出："0.5"

示例 2：
输入：numerator = 2, denominator = 1
输出："2"

示例 3：
输入：numerator = 2, denominator = 3
输出："0.(6)"
示例 4：

输入：numerator = 4, denominator = 333
输出："0.(012)"

示例 5：
输入：numerator = 1, denominator = 5
输出："0.2"

提示：
* -2^31 <= numerator, denominator <= 2^31 - 1
* denominator != 0

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun fractionToDecimal(numerator: Int, denominator: Int): String {
    if (numerator == 0) return "0"

    var t: Long = numerator.toLong().absoluteValue
    val d: Long = denominator.toLong().absoluteValue
    val sign = if (numerator.sign * denominator.sign < 0) "-" else ""

    if (t % d == 0L) return sign + (t / d).toString()

    val head: String
    if (t > d) {
        head = sign + (t / d).toString() + "."
        t %= d
    } else {
        head = sign + "0."
    }

    val start: HashMap<Long, Int> = hashMapOf()
    val buf: StringBuilder = StringBuilder()
    var i = 0
    while (t != 0L) {
        t *= 10
        if (t in start) {
            val split = start[t]!!
            return head + buf.take(split) + "(" + buf.slice(split until buf.length) + ")"
        }
        start[t] = i
        i += 1
        if (t >= d) {
            buf.append(t / d)
            t %= d
        } else {
            buf.append("0")
        }
    }

    return head + buf.toString()
}

fun main() {
    println(fractionToDecimal(0, 1))
    println(fractionToDecimal(1, 1))
    println(fractionToDecimal(1, -1))
    println(fractionToDecimal(-1, -1))
    println(fractionToDecimal(-1, 1))
    println(fractionToDecimal(Int.MAX_VALUE, Int.MIN_VALUE))
    // println(fractionToDecimal(Int.MIN_VALUE, Int.MAX_VALUE))
    println(fractionToDecimal(1, 2))
    println(fractionToDecimal(2, 1))
    println(fractionToDecimal(2, 3))
    println(fractionToDecimal(4, 333))
}
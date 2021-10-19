package me.xiacongling.code.leet.k0.h0

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 29, title = "两数相除", difficulty = Difficulty.MEDIUM, description = """
给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2


示例1:
输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = truncate(3.33333..) = truncate(3) = 3

示例2:
输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = truncate(-2.33333..) = -2

提示：
* 被除数和除数均为 32 位有符号整数。
* 除数不为 0。
* 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31, 2^31− 1]。本题中，
  如果除法结果溢出，则返回 2^31− 1。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/divide-two-integers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun divide(dividend: Int, divisor: Int): Int {
    if (dividend == 0) return 0
    if (divisor == 1) return dividend
    if (divisor == -1) return if (dividend == Int.MIN_VALUE) Int.MAX_VALUE else -dividend

    val positive = dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0
    val longDividend: Long = if (dividend > 0) dividend.toLong() else -dividend.toLong()
    val longDivisor: Long = if (divisor > 0) divisor.toLong() else -divisor.toLong()

    if (longDividend < longDivisor) return 0

    var s = 0
    var d = longDivisor
    var u = longDividend
    while (d <= longDividend) {
        s += 1
        d = d shl 1
    }
    var result = 0
    var r = 1 shl (s - 1)
    while (r > 0) {
        d = d shr 1
        if (u >= d) {
            u -= d
            result += r
        }
        r = r shr 1
    }
    return if (positive) result else -result
}

fun main() {
    println(divide(-2147483648, -1))
    println(divide(1, -1))
    println(divide(2, 2))
    println(divide(10, 3))
    println(divide(7, -3))
}
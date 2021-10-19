package me.xiacongling.code.leet.k0.h3

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 371, title = "两整数之和", difficulty = Difficulty.MEDIUM, description = """
给你两个整数 a 和 b，不使用 运算符 + 和 -，计算并返回两整数之和。

示例 1：
输入：a = 1, b = 2
输出：3

示例 2：
输入：a = 2, b = 3
输出：5

提示：
* -1000 <= a, b <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sum-of-two-integers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun getSum(a: Int, b: Int): Int {
    var s = a xor b
    var c = (a and b) shl 1
    while (c != 0) {
        val t = s xor c
        c = (c and s) shl 1
        s = t
    }
    return s
}

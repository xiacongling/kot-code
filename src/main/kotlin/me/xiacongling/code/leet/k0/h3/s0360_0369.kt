package me.xiacongling.code.leet.k0.h3

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(id = 367, title = "有效的完全平方数", difficulty = Difficulty.EASY, description = """
给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。

进阶：不要 使用任何内置的库函数，如 sqrt 。

示例 1：
输入：num = 16
输出：true

示例 2：
输入：num = 14
输出：false

提示：
* 1 <= num <= 2^31 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-perfect-square
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
""")
fun isPerfectSquare(num: Int): Boolean {
    val n: Long = num.toLong()
    var start: Long = 1L
    var end: Long = n
    while (start <= end) {
        val candidate: Long = (start + end) / 2
        val sqr: Long = candidate * candidate
        if (sqr == n) return true
        if (sqr > num) {
            end = candidate - 1
        } else {
            start = candidate + 1
        }
    }
    return false
}

fun main() {
    println(isPerfectSquare(1))
    println(isPerfectSquare(2))
    println(isPerfectSquare(3))
    println(isPerfectSquare(4))
    println(isPerfectSquare(100))
    println(isPerfectSquare(16))
    println(isPerfectSquare(14))
}
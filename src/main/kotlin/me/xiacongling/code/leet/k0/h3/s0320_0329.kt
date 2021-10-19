package me.xiacongling.code.leet.k0.h3

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

val powers3: Set<Int> = hashSetOf(
    1,
    3,
    9,
    27,
    81,
    243,
    729,
    2187,
    6561,
    19683,
    59049,
    177147,
    531441,
    1594323,
    4782969,
    14348907,
    43046721,
    129140163,
    387420489,
    1162261467
)

@Solution(
    id = 326, title = "3的幂", difficulty = Difficulty.EASY, description = """
给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true；否则，返回 false 。
整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x

示例 1：
输入：n = 27
输出：true

示例 2：
输入：n = 0
输出：false

示例 3：
输入：n = 9
输出：true

示例 4：
输入：n = 45
输出：false

提示：
* -2^31 <= n <= 2^31 - 1

进阶：你能不使用循环或者递归来完成本题吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/power-of-three
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun isPowerOfThree(n: Int): Boolean {
    return n in powers3
}

fun main() {
    isPowerOfThree(1)
}
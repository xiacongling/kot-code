package me.xiacongling.code.leet.k0.h6

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

val primes: IntArray = intArrayOf(
    2, 3, 5, 7,
    11, 13, 17, 19,
    23, 29, 31
)

@Solution(
    id = 650, title = "只有两个键的键盘", difficulty = Difficulty.MEDIUM, description = """
最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：

Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
Paste（粘贴）：粘贴上一次复制的字符。
给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。

示例 1：
输入：3
输出：3
解释：
最初, 只有一个字符 'A'。
第 1 步, 使用 Copy All 操作。
第 2 步, 使用 Paste 操作来获得 'AA'。
第 3 步, 使用 Paste 操作来获得 'AAA'。

示例 2：
输入：n = 1
输出：0


提示：
* 1 <= n <= 1000


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/2-keys-keyboard
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun minSteps(n: Int): Int {
    var t = n
    var steps = 0

    for (p in primes) {
        if (t < p) break
        while (t % p == 0) {
            steps += p
            t /= p
        }
    }
    return if (t == 1) steps else steps + t
}

fun main() {
    println(minSteps(1))    // 0
    println(minSteps(2))    // 2
    println(minSteps(3))    // 3
    println(minSteps(4))    // 4
    println(minSteps(5))    // 5
    println(minSteps(6))    // 5
    println(minSteps(7))    // 7
    println(minSteps(8))    // 6
    println(minSteps(9))    // 6
    println(minSteps(10))   // 7
    println(minSteps(11))   // 11
    println(minSteps(12))   // 7
    println(minSteps(13))   // 13
    println(minSteps(14))   // 9
    println(minSteps(15))   // 8
    println(minSteps(16))   // 8
}
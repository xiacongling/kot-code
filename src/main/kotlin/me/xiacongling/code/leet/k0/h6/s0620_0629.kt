package me.xiacongling.code.leet.k0.h6

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 629, title = "K个逆序对数组", difficulty = Difficulty.HARD, description = """
给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。

逆序对的定义如下：对于数组的第 i 个和第 j 个元素，如果满 i < j 且 a[i] > a[j]，则其为一个逆序对；否则不是。

由于答案可能很大，只需要返回 答案 mod 10^9 + 7 的值。

示例 1:
输入: n = 3, k = 0
输出: 1
解释: 
只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。

示例 2:
输入: n = 3, k = 1
输出: 2
解释: 
数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。

说明:
* n 的范围是 [1, 1000] 并且 k 的范围是 [0, 1000]。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/k-inverse-pairs-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun kInversePairs(n: Int, k: Int): Int {
    val m: Long = 1000000007
    val dp: Array<IntArray> = arrayOf(IntArray(k + 1), IntArray(k + 1))
    dp[0][0] = 1
    for (i in 1..n) {
        for (j in 0..k) {
            val ci = i % 2
            val pi = 1 - ci
            var t: Long = dp[pi][j].toLong()
            if (j - 1 >= 0) t += dp[ci][j - 1]
            if (j - i >= 0) t -= dp[pi][j - i]
            dp[ci][j] = ((t + m) % m).toInt()
        }
    }
    return dp[n % 2][k]
}

fun main() {
    println(kInversePairs(3, 0))
    println(kInversePairs(3, 1))
}
package me.xiacongling.code.leet.k0.h5

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 583, title = "两个字符串的删除操作", difficulty = Difficulty.MEDIUM, description = """
给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，
每步可以删除任意一个字符串中的一个字符。

示例：
输入: "sea", "eat"
输出: 2
解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"

提示：
1. 给定单词的长度不超过500。
2. 给定单词中的字符只含有小写字母。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun minDistance(word1: String, word2: String): Int {
    fun lcsLength(a: String, b: String): Int {
        val dp: IntArray = IntArray(b.length + 1)
        for (t in a) {
            var tmp: Int = 0
            for ((i, p) in b.withIndex()) {
                val l = if (p == t) tmp + 1 else maxOf(dp[i], dp[i + 1])
                tmp = dp[i + 1]
                dp[i + 1] = l
            }
        }
        return dp[b.length]
    }

    val commonLength: Int = lcsLength(word1, word2)
    return word1.length + word2.length - commonLength - commonLength
}

fun main() {
    println(minDistance("sea", "eat"))
}
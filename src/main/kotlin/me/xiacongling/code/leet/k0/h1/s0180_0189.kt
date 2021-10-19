package me.xiacongling.code.leet.k0.h1

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

val DNA_ENC = mapOf('A' to 0, 'C' to 1, 'G' to 2, 'T' to 3)
val DNA_MSK = (1 shl 20) - 1

@Solution(
    id = 187, title = "重复的 DNA 序列", difficulty = Difficulty.MEDIUM, description = """
所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。
在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。

编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。


示例 1：
输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
输出：["AAAAACCCCC","CCCCCAAAAA"]

示例 2：
输入：s = "AAAAAAAAAAAAA"
输出：["AAAAAAAAAA"]

提示：

* 0 <= s.length <= 10^5
* s[i] 为 'A'、'C'、'G' 或 'T'

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/repeated-dna-sequences
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findRepeatedDnaSequences(s: String): List<String> {
    if (s.length <= 10) return emptyList()
    val subSequences: MutableSet<Int> = hashSetOf()
    val result: MutableSet<String> = mutableSetOf()

    var t: Int = 0
    for ((i, c) in s.withIndex()) {
        t = t shl 2
        t = (t or DNA_ENC[c]!!) and DNA_MSK
        if (i >= 9) {
            if (!subSequences.add(t)) {
                result.add(s.substring(i - 9, i + 1))
            }
        }
    }
    return result.toList()
}


fun main() {
    println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"))
    println(findRepeatedDnaSequences("AAAAAAAAAAAAA"))
}

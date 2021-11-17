package me.xiacongling.code.leet.k0.h5

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 520, title = "检测大写字母", difficulty = Difficulty.EASY, description = """
我们定义，在以下情况时，单词的大写用法是正确的：
* 全部字母都是大写，比如 "USA" 。
* 单词中所有字母都不是大写，比如 "leetcode" 。
* 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
* 给你一个字符串 word 。如果大写用法正确，返回 true；否则，返回 false 。

示例 1：
输入：word = "USA"
输出：true

示例 2：
输入：word = "FlaG"
输出：false

提示：
* 1 <= word.length <= 100
* word 由小写和大写英文字母组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/detect-capital
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun detectCapitalUse(word: String): Boolean {
    if (word.length < 2) return true
    val firstCap = word[0].isUpperCase()
    val secondCap = word[1].isUpperCase()
    if (!firstCap && secondCap) return false
    val expect = firstCap && secondCap
    for (i in 2 until word.length) {
        val actual = word[i].isUpperCase()
        if (expect != actual) return false
    }
    return true
}

@Solution(
    id = 524, title = "通过删除字母匹配到字典里最长单词", difficulty = Difficulty.MEDIUM, description = """
给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。

如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。

示例 1：
输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
输出："apple"

示例 2：
输入：s = "abpcplea", dictionary = ["a","b","c"]
输出："a"

提示：
* 1 <= s.length <= 1000
* 1 <= dictionary.length <= 1000
* 1 <= dictionary[i].length <= 1000
* s 和 dictionary[i] 仅由小写英文字母组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findLongestWord(s: String, dictionary: List<String>): String {
    fun match(p: String, t: String): Boolean {
        var j: Int = 0
        for ((i, c) in t.withIndex()) {
            if (j >= p.length) {
                return true
            }
            if (p.length - j > t.length - i) {
                return false
            }
            if (p[j] == c) {
                j += 1
            }
        }
        return j >= p.length
    }

    var result: String = ""
    for (p in dictionary) {
        if (match(p, s) && (p.length > result.length || (p.length == result.length && p < result))) {
            result = p
        }
    }
    return result
}
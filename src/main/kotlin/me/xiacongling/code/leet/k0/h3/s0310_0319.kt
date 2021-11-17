package me.xiacongling.code.leet.k0.h3

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 318, title = "最大单词长度乘积", difficulty = Difficulty.MEDIUM, description = """
给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。
你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。

示例 1:
输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
输出: 16 
解释: 这两个单词为 "abcw", "xtfn"。

示例 2:
输入: ["a","ab","abc","d","cd","bcd","abcd"]
输出: 4 
解释: 这两个单词为 "ab", "cd"。

示例 3:
输入: ["a","aa","aaa","aaaa"]
输出: 0 
解释: 不存在这样的两个单词。

提示：
* 2 <= words.length <= 1000
* 1 <= words[i].length <= 1000
* words[i] 仅包含小写字母

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-product-of-word-lengths
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun maxProduct(words: Array<String>): Int {
    val dict = words.map { it.fold(0) { acc, c -> acc or (1 shl (c - 'a')) } }
    val n = words.size
    var result = 0
    for (i in 0 until n - 1) {
        for (j in i until n) {
            if (dict[i] and dict[j] == 0) {
                result = kotlin.math.max(result, words[i].length * words[j].length)
            }
        }
    }
    return result
}

fun main() {
    println(maxProduct(arrayOf("abcw", "baz", "foo", "bar", "xtfn", "abcdef")))
    println(maxProduct(arrayOf("a", "ab", "abc", "d", "cd", "bcd", "abcd")))
    println(maxProduct(arrayOf("a", "aa", "aaa", "aaaa")))
}

@Solution(
    id = 319, title = "", difficulty = Difficulty.MEDIUM, description = """
初始时有 n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭一个。

第三轮，你每三个灯泡就切换一个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，
你每 i 个灯泡就切换一个灯泡的开关。直到第 n 轮，你只需要切换最后一个灯泡的开关。

找出并返回 n 轮后有多少个亮着的灯泡。

示例 1：

            0  0  0
Round 1:    1  1  1
Round 2:    1  0  1
Round 3:    1  0  0

输入：n = 3
输出：1 
解释：
初始时, 灯泡状态 [关闭, 关闭, 关闭].
第一轮后, 灯泡状态 [开启, 开启, 开启].
第二轮后, 灯泡状态 [开启, 关闭, 开启].
第三轮后, 灯泡状态 [开启, 关闭, 关闭]. 
你应该返回 1，因为只有一个灯泡还亮着。

示例 2：
输入：n = 0
输出：0

示例 3：
输入：n = 1
输出：1

提示：
* 0 <= n <= 10^9

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/bulb-switcher
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun bulbSwitch(n: Int): Int {
    return kotlin.math.sqrt(n + .5).toInt()
}
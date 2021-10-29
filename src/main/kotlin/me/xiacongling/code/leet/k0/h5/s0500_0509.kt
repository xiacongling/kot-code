package me.xiacongling.code.leet.k0.h5

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

val KB_LINES: List<Set<Char>> = listOf("qwertyuiop".toSet(), "asdfghjkl".toSet(), "zxcvbnm".toSet())

@Solution(
    id = 500, title = "键盘行", difficulty = Difficulty.EASY, description = """
给你一个字符串数组 words，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。

美式键盘 中：
* 第一行由字符 "qwertyuiop" 组成。
* 第二行由字符 "asdfghjkl" 组成。
* 第三行由字符 "zxcvbnm" 组成。

示例 1：
输入：words = ["Hello","Alaska","Dad","Peace"]
输出：["Alaska","Dad"]

示例 2：
输入：words = ["omk"]
输出：[]

示例 3：
输入：words = ["adsdf","sfd"]
输出：["adsdf","sfd"]

提示：
* 1 <= words.length <= 20
* 1 <= words[i].length <= 100
* words[i] 由英文字母（小写和大写字母）组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/keyboard-row
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findWords(words: Array<String>): Array<String> {
    return words.filter { word -> KB_LINES.any { it.containsAll(word.toList()) } }.toTypedArray()
}
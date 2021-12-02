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
    return words.map { it.lowercase() }
        .filter { word -> KB_LINES.any { it.containsAll(word.lowercase().toList()) } }.toTypedArray()
}

@Solution(
    id = 506, title = "相对名次", difficulty = Difficulty.EASY, description = """
给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都互不相同 。

运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。
运动员的名次决定了他们的获奖情况：

* 名次第 1 的运动员获金牌 "Gold Medal" 。
* 名次第 2 的运动员获银牌 "Silver Medal" 。
* 名次第 3 的运动员获铜牌 "Bronze Medal" 。
* 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
* 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。

示例 1：
输入：score = [5,4,3,2,1]
输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
解释：名次为 [1st, 2nd, 3rd, 4th, 5th] 。

示例 2：
输入：score = [10,3,8,9,4]
输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
解释：名次为 [1st, 5th, 3rd, 2nd, 4th] 。

提示：
* n == score.length
* 1 <= n <= 10^4
* 0 <= score[i] <= 10^6
* score 中的所有值互不相同

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/relative-ranks
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findRelativeRanks(score: IntArray): Array<String> {
    fun award(seq: Int): String = when (seq) {
        0 -> "Gold Medal"
        1 -> "Silver Medal"
        2 -> "Bronze Medal"
        else -> "${seq + 1}"
    }

    val awards = score.withIndex().sortedByDescending { it.value }
        .withIndex().associate { it.value.value to award(it.index) }

    return score.map { awards[it]!! }.toTypedArray()
}
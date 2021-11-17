package me.xiacongling.code.leet.k0.h8

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 852, title = "山峰数组的峰顶索引", difficulty = Difficulty.EASY,
    description = """
符合下列属性的数组 arr 称为 山峰数组（山脉数组）：

* arr.length >= 3
* 存在 i（0 < i < arr.length - 1）使得：
  - arr[0] < arr[1] < ... arr[i-1] < arr[i]
  - arr[i] > arr[i+1] > ... > arr[arr.length - 1]
给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/B1IidL
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun peakIndexInMountainArray(arr: IntArray): Int {
    var i = 1
    var j = arr.size - 2
    while (i <= j) {
        val m = (i + j) / 2
        val a = arr[m - 1]
        val b = arr[m]
        val c = arr[m + 1]
        if (b > a && b > c) return m
        if (a > b) j = m - 1 else i = m + 1
    }
    return arr[j]   // never reaches
}

@Solution(
    id = 859, title = "亲密字符串", difficulty = Difficulty.EASY, description = """
给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true；
否则返回 false 。

交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。

例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。

示例 1：
输入：s = "ab", goal = "ba"
输出：true
解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。

示例 2：
输入：s = "ab", goal = "ab"
输出：false
解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。

示例 3：
输入：s = "aa", goal = "aa"
输出：true
解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。

示例 4：
输入：s = "aaaaaaabc", goal = "aaaaaaacb"
输出：true

提示：
* 1 <= s.length, goal.length <= 2 * 10^4
* s 和 goal 由小写英文字母组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/buddy-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun buddyStrings(s: String, goal: String): Boolean {
    val n = s.length
    var diff1 = -1
    var diff2 = -1
    val charArray = IntArray(26) { 0 }
    for (i in 0 until n) {
        charArray[s[i] - 'a'] += 1
        if (s[i] != goal[i]) {
            if (diff1 < 0) {
                diff1 = i
            } else if (diff2 < 0) {
                diff2 = i
            } else {
                return false
            }
        }
    }
    if (diff1 < 0) {
        return charArray.any { it > 1 }
    }
    return diff2 > 0 && s[diff1] == goal[diff2] && s[diff2] == goal[diff1]
}

fun main() {
    println(buddyStrings("ab", "ba"))
    println(buddyStrings("ab", "ab"))
    println(buddyStrings("aa", "aa"))
    println(buddyStrings("aaaaaaabc", "aaaaaaacb"))
}

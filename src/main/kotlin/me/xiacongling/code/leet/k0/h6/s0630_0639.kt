package me.xiacongling.code.leet.k0.h6

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

val DECODE_BASE: Map<String, Long> = hashMapOf(
    "1" to 1L, "2" to 1L, "3" to 1L, "4" to 1L, "5" to 1L, "6" to 1L, "7" to 1L, "8" to 1L,
    "9" to 1L, "10" to 1L, "11" to 1L, "12" to 1L, "13" to 1L, "14" to 1L, "15" to 1L, "16" to 1L,
    "17" to 1L, "18" to 1L, "19" to 1L, "20" to 1L, "21" to 1L, "22" to 1L, "23" to 1L, "24" to 1L,
    "25" to 1L, "26" to 1L, "*" to 9L, "*0" to 2L, "*1" to 2L, "*2" to 2L, "*3" to 2L, "*4" to 2L,
    "*5" to 2L, "*6" to 2L, "*7" to 1L, "*8" to 1L, "*9" to 1L, "**" to 15L, "1*" to 9L, "2*" to 6L
)

@Solution(
    id = 639, title = "", difficulty = Difficulty.HARD, description = """
一条包含字母 A-Z 的消息通过以下的方式进行了编码：

```
'A' -> 1
'B' -> 2
...
'Z' -> 26
```

要解码一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母
（可能存在多种方式）。例如，"11106" 可以映射为：

* "AAJF" 对应分组 (1 1 10 6)
* "KJF" 对应分组 (11 10 6)

注意，像 (1 11 06) 这样的分组是无效的，因为 "06" 不可以映射为 'F'，因为 "6" 与 "06" 不同。

除了上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符，可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）。
例如，编码字符串 "1*" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条消息。
对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息。

给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目 。

由于答案数目可能非常大，返回对 10^9 + 7 取余 的结果。

示例 1：
输入：s = "*"
输出：9
解释：这一条编码消息可以表示 "1"、"2"、"3"、"4"、"5"、"6"、"7"、"8" 或 "9" 中的任意一条。
可以分别解码成字符串 "A"、"B"、"C"、"D"、"E"、"F"、"G"、"H" 和 "I" 。因此，"*" 总共有 9 种解码方法。

示例 2：
输入：s = "1*"
输出：18
解释：这一条编码消息可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条。
每种消息都可以由 2 种方法解码（例如，"11" 可以解码成 "AA" 或 "K"）。因此，"1*" 共有 9 * 2 = 18 种解码方法。

示例 3：
输入：s = "2*"
输出：15
解释：这一条编码消息可以表示 "21"、"22"、"23"、"24"、"25"、"26"、"27"、"28" 或 "29" 中的任意一条。
"21"、"22"、"23"、"24"、"25" 和 "26" 由 2 种解码方法，但 "27"、"28" 和 "29" 仅有 1 种解码方法。
因此，"2*" 共有 (6 * 2) + (3 * 1) = 12 + 3 = 15 种解码方法。

提示：

* 1 <= s.length <= 10^5
* s[i] 是 0 - 9 中的一位数字或字符 '*'


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/decode-ways-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun numDecodings(s: String): Int {
    val n = s.length
    val m = 1000000007L
    var l = 1L
    var r = DECODE_BASE.getOrDefault(s.take(1), 0L)

    for (i in 1 until n) {
        val t = l * DECODE_BASE.getOrDefault(s.substring(i - 1, i + 1), 0L) % m +
                r * DECODE_BASE.getOrDefault(s.substring(i, i + 1), 0L) % m
        if (t == 0L) return t.toInt()
        l = r
        r = t % m
    }
    return r.toInt()
}

fun main() {
    println(numDecodings("01241902846019246"))
    println(numDecodings("*"))
    println(numDecodings("1*"))
    println(numDecodings("2*"))
    println(numDecodings("11106"))
    println(numDecodings("7*9*3*6*3*0*5*4*9*7*3*7*1*8*3*2*0*0*6*"))
}
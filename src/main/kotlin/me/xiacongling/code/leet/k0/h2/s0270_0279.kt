package me.xiacongling.code.leet.k0.h2

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

val NUM_MAP: Map<Int, String> = hashMapOf(
    1 to "One", 2 to "Two", 3 to "Three", 4 to "Four", 5 to "Five",
    6 to "Six", 7 to "Seven", 8 to "Eight", 9 to "Nine", 10 to "Ten",
    11 to "Eleven", 12 to "Twelve", 13 to "Thirteen", 14 to "Fourteen", 15 to "Fifteen",
    16 to "Sixteen", 17 to "Seventeen", 18 to "Eighteen", 19 to "Nineteen",
    20 to "Twenty", 30 to "Thirty", 40 to "Forty", 50 to "Fifty",
    60 to "Sixty", 70 to "Seventy", 80 to "Eighty", 90 to "Ninety",
)

@Solution(
    id = 273, title = "整数转换英文表示", difficulty = Difficulty.HARD, description = """
将非负整数 num 转换为其对应的英文表示。

示例 1：
输入：num = 123
输出："One Hundred Twenty Three"

示例 2：
输入：num = 12345
输出："Twelve Thousand Three Hundred Forty Five"

示例 3：
输入：num = 1234567
输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

示例 4：
输入：num = 1234567891
输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"

提示：
* 0 <= num <= 2^31 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/integer-to-english-words
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun numberToWords(num: Int): String {
    if (num == 0) return "Zero"

    fun k(n: Int, buf: StringBuilder): StringBuilder {
        if (n in NUM_MAP) {
            if (buf.isNotEmpty()) buf.append(' ')
            buf.append(NUM_MAP[n])
        } else {
            var p = n
            if (p >= 100) {
                if (buf.isNotEmpty()) buf.append(' ')
                buf.append(NUM_MAP[p / 100]).append(" Hundred")
                p %= 100
            }
            if (p > 0) {
                if (buf.isNotEmpty()) buf.append(' ')
                if (p in NUM_MAP) {
                    buf.append(NUM_MAP[p])
                } else {
                    buf.append(NUM_MAP[p / 10 * 10]).append(' ').append(NUM_MAP[p % 10])
                }
            }
        }
        return buf
    }

    val buf: StringBuilder = StringBuilder()
    var p = num
    if (p >= 1_000_000_000) {
        k(p / 1_000_000_000, buf).append(" Billion")
        p %= 1_000_000_000
    }
    if (p >= 1_000_000) {
        k(p / 1_000_000, buf).append(" Million")
        p %= 1_000_000
    }
    if (p >= 1_000) {
        k(p / 1_000, buf).append(" Thousand")
        p %= 1_000
    }
    if (p > 0) k(p, buf)
    return buf.toString()
}

fun main() {
    println(numberToWords(1021))
    println(numberToWords(100))
    println(numberToWords(123))
    println(numberToWords(12345))
    println(numberToWords(1234567))
    println(numberToWords(1234567891))
    println(numberToWords(0))
    println(numberToWords(1))
    println(numberToWords(10))
    println(numberToWords(11))
    println(numberToWords(19))
    println(numberToWords(100))
    println(numberToWords(1000))
    println(numberToWords(10000))
    println(numberToWords(1000000))
    println(numberToWords(1000000000))
    println(numberToWords(2000000000))
}
package me.xiacongling.code.leet.k0.h4

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 412, title = "Fizz Buzz", Difficulty.EASY, description = """
写一个程序，输出从 1 到 n 数字的字符串表示。

1. 如果 n 是3的倍数，输出“Fizz”；
2. 如果 n 是5的倍数，输出“Buzz”；
3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。

示例：
n = 15,
返回:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fizz-buzz
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun fizzBuzz(n: Int): List<String> {
    fun Int.fizzBuzz(): String {
        val mod3 = this % 3 == 0
        val mod5 = this % 5 == 0
        return if (mod3 && mod5) "FizzBuzz" else if (mod3) "Fizz" else if (mod5) "Buzz" else this.toString()
    }
    return (1..n).map(Int::fizzBuzz)
}

@Solution(
    id = 414, title = "第三大的数", difficulty = Difficulty.EASY, description = """
给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。

示例 1：
输入：[3, 2, 1]
输出：1
解释：第三大的数是 1。

示例 2：
输入：[1, 2]
输出：2
解释：第三大的数不存在, 所以返回最大的数 2。

示例 3：
输入：[2, 2, 3, 1]
输出：1
解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1。

提示：

* 1 <= nums.length <= 10^4
* -2^31 <= nums[i] <= 2^31 - 1

进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/third-maximum-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun thirdMax(nums: IntArray): Int {
    val t: ArrayList<Int> = arrayListOf(nums[0])
    for (num in nums) {
        var mi = 0
        var m = t[0]
        for (i in 0 until t.size) {
            if (num == t[i]) {
                mi = i
                break
            }
            if (t[i] < m) {
                m = t[i]
                mi = i
            }
        }
        if (t.size < 3 && num != t[mi]) {
            t.add(num)
        } else if (num > t[mi]) {
            t[mi] = num
        }
    }
    return if (t.size >= 3) t.minOrNull()!! else t.maxOrNull()!!
}

fun main() {
    println(fizzBuzz(0))
    println(fizzBuzz(5))
    println(fizzBuzz(15))
}
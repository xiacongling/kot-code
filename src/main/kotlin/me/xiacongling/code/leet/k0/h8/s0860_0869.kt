package me.xiacongling.code.leet.k0.h8

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

var ALL_FORM_OF_PO2: MutableSet<String> = mutableSetOf()

@Solution(
    id = 869, title = "重新排序得到的 2 的幂", difficulty = Difficulty.MEDIUM, description = """
给定正整数 N，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。

如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。

示例 1：
输入：1
输出：true

示例 2：
输入：10
输出：false

示例 3：
输入：16
输出：true

示例 4：
输入：24
输出：false

示例 5：
输入：46
输出：true

提示：

* 1 <= N <= 10^9

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reordered-power-of-2
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun reorderedPowerOf2(n: Int): Boolean {
    fun form(x: Int): String {
        return String(x.toString().toCharArray().sortedArray())
    }
    if (ALL_FORM_OF_PO2.isEmpty()) {
        ALL_FORM_OF_PO2.addAll((0..29).map(1::shl).map(::form))
    }
    return ALL_FORM_OF_PO2.contains(form(n))
}

fun main() {
    println(reorderedPowerOf2(1))
    println(reorderedPowerOf2(10))
    println(reorderedPowerOf2(16))
    println(reorderedPowerOf2(61))
    println(reorderedPowerOf2(24))
    println(reorderedPowerOf2(46))
    for (i in 0..30) {
        val pow = 1 shl i
        println("$pow: ${reorderedPowerOf2(pow)}")
    }
}
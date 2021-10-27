package me.xiacongling.code.leet.k0.h3

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 301, title = "删除无效的括号", difficulty = Difficulty.HARD, description = """
给你一个由若干括号和字母组成的字符串 s，删除最小数量的无效括号，使得输入的字符串有效。

返回所有可能的结果。答案可以按任意顺序返回。

示例 1：
输入：s = "()())()"
输出：["(())()","()()()"]

示例 2：
输入：s = "(a)())()"
输出：["(a())()","(a)()()"]

示例 3：
输入：s = ")("
输出：[""]


提示：
* 1 <= s.length <= 25
* s 由小写英文字母以及括号 '(' 和 ')' 组成
* s 中至多含 20 个括号

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-invalid-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun removeInvalidParentheses(s: String): List<String> {
    fun valid(str: String): Boolean {
        var cnt = 0;
        for (c in str) {
            if (c == '(') {
                cnt += 1
            } else if (c == ')') {
                cnt -= 1
            }
            if (cnt < 0) return false;
        }
        return cnt == 0;
    }

    val result: MutableList<String> = mutableListOf()
    var layer: MutableSet<String> = hashSetOf();

    layer.add(s);
    while (layer.isNotEmpty()) {
        result.addAll(layer.filter(::valid))
        if (result.size > 0) break
        val nextLayer: MutableSet<String> = hashSetOf()
        for (str in layer) {
            for ((i, c) in str.withIndex()) {
                if (i > 0 && c == str[i - 1]) continue // remove some duplicated
                if (c == '(' || c == ')') {
                    nextLayer.add(str.substring(0, i) + str.substring(i + 1))
                }
            }
        }
        layer = nextLayer
    }
    return result
}

fun main() {
    println(removeInvalidParentheses("()())()"))
    println(removeInvalidParentheses("(a)())()"))
    println(removeInvalidParentheses(")("))
}
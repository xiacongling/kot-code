package me.xiacongling.code.leet.k0.h2

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 282, title = "给表达式添加运算符", difficulty = Difficulty.HARD, description = """
给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target，
在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。

示例 1:
输入: num = "123", target = 6
输出: ["1+2+3", "1*2*3"] 

示例 2:
输入: num = "232", target = 8
输出: ["2*3+2", "2+3*2"]

示例 3:
输入: num = "105", target = 5
输出: ["1*0+5","10-5"]

示例 4:
输入: num = "00", target = 0
输出: ["0+0", "0-0", "0*0"]

示例 5:
输入: num = "3456237490", target = 9191
输出: []

提示：

* 1 <= num.length <= 10
* num 仅含数字
* -2^31 <= target <= 2^31 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/expression-add-operators
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    """
)
fun addOperators(num: String, target: Int): List<String> {
    val n: Int = num.length

    fun tryAddOp(
        result: MutableList<String>,
        buf: MutableList<String>,
        nextIdx: Int,
        prefixResult: Long,
        tail: Long
    ) {
        fun plus(operand: Long, idx: Int) {
            buf.add("+$operand")
            tryAddOp(result, buf, idx, prefixResult + operand, operand)
            buf.removeLast()
        }

        fun minus(operand: Long, idx: Int) {
            buf.add("-$operand")
            tryAddOp(result, buf, idx, prefixResult - operand, -operand)
            buf.removeLast()
        }

        fun multiply(operand: Long, idx: Int) {
            buf.add("*$operand")
            val newTail = tail * operand
            tryAddOp(result, buf, idx, prefixResult - tail + newTail, newTail)
            buf.removeLast()
        }

        if (nextIdx >= n && prefixResult == target.toLong()) {
            result.add(buf.joinToString(""))
            return
        }
        if (nextIdx >= n) return
        if (num[nextIdx] == '0') {
            plus(0, nextIdx + 1)
            minus(0, nextIdx + 1)
            multiply(0, nextIdx + 1)
        } else {
            for (i in nextIdx + 1..n) {
                val operand = num.substring(nextIdx, i).toLong()
                plus(operand, i)
                minus(operand, i)
                multiply(operand, i)
            }
        }
    }

    val result: MutableList<String> = mutableListOf()
    if (num.first() == '0') {
        tryAddOp(result, mutableListOf("0"), 1, 0L, 0)
    } else {
        for (i in 1..n) {
            val firstNumStr = num.take(i)
            val firstNum = firstNumStr.toLong()
            tryAddOp(result, mutableListOf(firstNumStr), i, firstNum, firstNum)
        }
    }
    return result
}

fun main() {
    println(addOperators("0", 0))
    println(addOperators("2147483647", 2147483647))
    println(addOperators("123", 6))
    println(addOperators("232", 8))
    println(addOperators("105", 5))
    println(addOperators("00", 0))
    println(addOperators("3456237490", 9191))
}

@Solution(
    id = 284, title = "顶端迭代器", difficulty = Difficulty.MEDIUM, description = """
请你设计一个迭代器，除了支持 hasNext 和 next 操作外，还支持 peek 操作。

实现 PeekingIterator 类：

* PeekingIterator(int[] nums) 使用指定整数数组 nums 初始化迭代器。
* int next() 返回数组中的下一个元素，并将指针移动到下个元素处。
* bool hasNext() 如果数组中存在下一个元素，返回 true ；否则，返回 false 。
* int peek() 返回数组中的下一个元素，但 不 移动指针。

示例：

输入：
["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
[[[1, 2, 3]], [], [], [], [], []]
输出：
[null, 1, 2, 2, 3, false]

解释：
PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [*1*,2,3]
peekingIterator.next();    // 返回 1 ，指针移动到下一个元素 [1,*2*,3]
peekingIterator.peek();    // 返回 2 ，指针未发生移动 [1,*2*,3]
peekingIterator.next();    // 返回 2 ，指针移动到下一个元素 [1,2,*3*]
peekingIterator.next();    // 返回 3 ，指针移动到下一个元素 [1,2,3]
peekingIterator.hasNext(); // 返回 False

提示：
* 1 <= nums.length <= 1000
* 1 <= nums[i] <= 1000
* 对 next 和 peek 的调用均有效
* next、hasNext 和 peek 最多调用 1000 次

进阶：你将如何拓展你的设计？使之变得通用化，从而适应所有的类型，而不只是整数型？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/peeking-iterator
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
class PeekingIterator(iterator: Iterator<Int>) : Iterator<Int> {
    private val iter = iterator
    var next: Int? = null

    init {
        hasNext()
    }

    fun peek(): Int {
        return next!!
    }

    override fun next(): Int {
        val result = next!!
        next = null
        hasNext()
        return result
    }

    override fun hasNext(): Boolean {
        if (next != null) {
            return true
        }
        if (iter.hasNext()) {
            next = iter.next()
            return true
        }
        return false
    }
}
package me.xiacongling.code.leet.k0.h3

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution
import java.util.*

@Solution(
    id = 352, title = "将数据流变为多个不相交区间", difficulty = Difficulty.HARD, description = """
给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。

实现 SummaryRanges 类：

* SummaryRanges() 使用一个空数据流初始化对象。
* void addNum(int val) 向数据流中加入整数 val 。
* int[][] getIntervals() 以不相交区间[start_i, end_i] 的列表形式返回对数据流中整数的总结。

示例：
输入：
["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
[[], [1], [], [3], [], [7], [], [2], [], [6], []]
输出：
[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]

解释：
SummaryRanges summaryRanges = new SummaryRanges();
summaryRanges.addNum(1);      // arr = [1]
summaryRanges.getIntervals(); // 返回 [[1, 1]]
summaryRanges.addNum(3);      // arr = [1, 3]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
summaryRanges.addNum(7);      // arr = [1, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]

提示：

0 <= val <= 10^4
最多调用 addNum 和 getIntervals 方法 3 * 10^4 次

进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
class SummaryRanges() {
    private val rangeSet: TreeMap<Int, IntArray> = TreeMap()

    fun addNum(`val`: Int) {
        val l = rangeSet.floorEntry(`val`)
        if (l == null) {
            val rv = rangeSet.remove(`val` + 1)
            if (rv == null) {
                rangeSet[`val`] = intArrayOf(`val`, `val`)
            } else {
                rangeSet[`val`] = intArrayOf(`val`, rv[1])
            }
        } else if (`val` > l.value[1]) {
            val rv = rangeSet.remove(`val` + 1)
            if (rv != null) {
                if (l.value[1] + 1 == `val`) {
                    l.value[1] = rv[1]
                } else {
                    rangeSet[`val`] = intArrayOf(`val`, rv[1])
                }
            } else if (l.value[1] + 1 == `val`) {
                l.value[1] = `val`
            } else {
                rangeSet[`val`] = intArrayOf(`val`, `val`)
            }
        }
    }

    fun getIntervals(): Array<IntArray> {
        return rangeSet.values.toTypedArray()
    }
}

fun main() {
    val summaryRanges = SummaryRanges()
    summaryRanges.addNum(1)     // arr = [1]
    println(summaryRanges.getIntervals().map { it.toList() }) // 返回 [[1, 1]]
    summaryRanges.addNum(3)     // arr = [1, 3]
    println(summaryRanges.getIntervals().map { it.toList() }) // 返回 [[1, 1], [3, 3]]
    summaryRanges.addNum(7)     // arr = [1, 3, 7]
    println(summaryRanges.getIntervals().map { it.toList() }) // 返回 [[1, 1], [3, 3], [7, 7]]
    summaryRanges.addNum(2)     // arr = [1, 2, 3, 7]
    println(summaryRanges.getIntervals().map { it.toList() }) // 返回 [[1, 3], [7, 7]]
    summaryRanges.addNum(6)     // arr = [1, 2, 3, 6, 7]
    println(summaryRanges.getIntervals().map { it.toList() }) // 返回 [[1, 3], [6, 7]]
}
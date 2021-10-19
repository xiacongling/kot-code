package me.xiacongling.code.leet.k0.h4

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution
import kotlin.math.floor
import kotlin.math.sqrt

@Solution(
    id = 441, title = "排列硬币", difficulty = Difficulty.EASY, description = """
你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，
其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。

给你一个数字 n ，计算并返回可形成完整阶梯行的总行数。

示例 1：
+---+
| $ |
+---+---+
| $ | $ |
+---+---+---+
| $ | $ |   |
+---+---+---+
输入：n = 5
输出：2
解释：因为第三行不完整，所以返回 2 。

示例 2：
+---+
| $ |
+---+---+
| $ | $ |
+---+---+---+
| $ | $ | $ |
+---+---+---+---+
| $ | $ |   |   |
+---+---+---+---+
输入：n = 8
输出：3
解释：因为第四行不完整，所以返回 3 。

提示：
* 1 <= n <= 2^31 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/arranging-coins
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun arrangeCoins(n: Int): Int {
    return floor(sqrt(2L * n + 0.25) - 0.5).toInt()
}

@Solution(
    id = 447, title = "回旋镖的数量", difficulty = Difficulty.MEDIUM, description = """
给定平面上 n 对 互不相同的点 points，其中 points[i] = [xi, yi] 。
回旋镖是由点 (i, j, k) 表示的元组，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。

返回平面上所有回旋镖的数量。

示例 1：
输入：points = [[0,0],[1,0],[2,0]]
输出：2
解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]

示例 2：
输入：points = [[1,1],[2,2],[3,3]]
输出：2

示例 3：
输入：points = [[1,1]]
输出：0

提示：
* n == points.length
* 1 <= n <= 500
* points[i].length == 2
* -10^4 <= xi, yi <= 10^4
* 所有点都 互不相同

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-boomerangs
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun numberOfBoomerangs(points: Array<IntArray>): Int {
    fun len(p1: IntArray, p2: IntArray): Int {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1])
    }

    fun trans(p: IntArray): Int {
        return p[0] * 100000 + p[1]
    }

    val plp: MutableMap<Int, MutableMap<Int, Int>> = mutableMapOf()

    for ((i, p) in points.withIndex()) {
        for (j in i + 1 until points.size) {
            val q: IntArray = points[j]
            val dist = len(p, q)
            plp.getOrPut(trans(p)) { mutableMapOf() }
                .compute(dist) { _, v -> v?.plus(1) ?: 1 }
            plp.getOrPut(trans(q)) { mutableMapOf() }
                .compute(dist) { _, v -> v?.plus(1) ?: 1 }
        }
    }
    return plp.values.fold(0) { result, groups ->
        result + groups.values.fold(0) { count, points ->
            count + points * (points - 1)
        }
    }
}

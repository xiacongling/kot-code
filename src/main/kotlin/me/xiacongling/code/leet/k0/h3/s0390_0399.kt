package me.xiacongling.code.leet.k0.h3

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.FailReason
import me.xiacongling.code.leet.Frustrating
import me.xiacongling.code.leet.Solution

@Solution(
    id = 391, title = "完美矩形", difficulty = Difficulty.HARD, description = """
给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。
这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。

如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。

示例 1：

  ^
5 +
  |
4 +   +---+---+---+
  |   |   |   |   |
3 +   +---+---+   |
  |   |       |   |
2 +   |       +---+
  |   |       |   |
1 +   +-------+---+
  |
  +---+---+---+---+---+--->
0     1   2   3   4   5

输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
输出：true
解释：5 个矩形一起可以精确地覆盖一个矩形区域。 

示例 2：

  ^
5 +
  |
4 +   +---+   +---+
  |   |   |///|   |
3 +   +---+///+   |
  |   |   |///|   |
2 +   |   |///+---+
  |   |   |///|   |
1 +   +----   +---+
  |
  +---+---+---+---+---+--->
0     1   2   3   4   5

输入：rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
输出：false
解释：两个矩形之间有间隔，无法覆盖成一个矩形。

示例 3：

  ^
5 +
  |
4 +   +---+   +---+
  |   |   |///|   |
3 +   +---+---+   |
  |   |       |   |
2 +   |       +---+
  |   |       |   |
1 +   +-------+---+
  |
  +---+---+---+---+---+--->
0     1   2   3   4   5

输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[3,2,4,4]]
输出：false
解释：图形顶端留有空缺，无法覆盖成一个矩形。

示例 4：

  ^
5 +
  |
4 +   +---+---+---+
  |   |   |       |
3 +   +---+---+   |
  |   |   |///|   |
2 +   |   +---+---+
  |   |       |   |
1 +   +-------+---+
  |
  +---+---+---+---+---+--->
0     1   2   3   4   5

输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
输出：false
解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
 
提示：
* 1 <= rectangles.length <= 2 * 10^4
* rectangles[i].length == 4
* -10^5 <= xi, yi, ai, bi <= 10^5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/perfect-rectangle
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun isRectangleCover(rectangles: Array<IntArray>): Boolean {
    @Frustrating(reason = FailReason.WA)
    fun solution1(): Boolean {
        var lx = Int.MAX_VALUE
        var ly = Int.MAX_VALUE
        var rx = Int.MIN_VALUE
        var ry = Int.MIN_VALUE
        var area: Long = 0
        for (r in rectangles) {
            if (r[0] < lx || r[0] == lx && r[1] < ly) {
                lx = r[0]
                ly = r[1]
            }
            if (r[2] > rx || r[2] == rx && r[3] > ry) {
                rx = r[2]
                ry = r[3]
            }
            area += (r[2] - r[0]).toLong() * (r[3] - r[1]).toLong()
        }
        println("range=[$lx, $ly, $rx, $ry], area=$area")
        return area == (rx - lx).toLong() * (ry - ly).toLong()
    }

    fun p(x: Int, y: Int): Int = x * 100000 + y

    val cnt: HashMap<Int, Int> = hashMapOf()
    var area = 0L
    var lx = Int.MAX_VALUE
    var ly = Int.MAX_VALUE
    var rx = Int.MIN_VALUE
    var ry = Int.MIN_VALUE
    for (r in rectangles) {
        area += (r[2] - r[0]).toLong() * (r[3] - r[1]).toLong()
        lx = kotlin.math.min(lx, r[0])
        ly = kotlin.math.min(ly, r[1])
        rx = kotlin.math.max(rx, r[2])
        ry = kotlin.math.max(ry, r[3])
        cnt.compute(p(r[0], r[1])) { _, v -> (v ?: 0) + 1 }
        cnt.compute(p(r[0], r[3])) { _, v -> (v ?: 0) + 1 }
        cnt.compute(p(r[2], r[1])) { _, v -> (v ?: 0) + 1 }
        cnt.compute(p(r[2], r[3])) { _, v -> (v ?: 0) + 1 }
    }
    if (area != (rx - lx).toLong() * (ry - ly).toLong()) return false
    if ((cnt.remove(p(lx, ly)) ?: 0) != 1 ||
        (cnt.remove(p(rx, ly)) ?: 0) != 1 ||
        (cnt.remove(p(rx, ry)) ?: 0) != 1 ||
        (cnt.remove(p(lx, ry)) ?: 0) != 1
    ) {
        return false
    }
    return cnt.all { e -> e.value == 2 || e.value == 4 }
}

fun main() {
    println(
        isRectangleCover(
            arrayOf(
                intArrayOf(0, 0, 1, 1),
                intArrayOf(0, 1, 3, 2),
                intArrayOf(1, 0, 2, 2),
            )
        )
    )
    println(
        isRectangleCover(
            arrayOf(
                intArrayOf(1, 1, 3, 3),
                intArrayOf(3, 1, 4, 2),
                intArrayOf(3, 2, 4, 4),
                intArrayOf(1, 3, 2, 4),
                intArrayOf(2, 3, 3, 4)
            )
        )
    )
    println(
        isRectangleCover(
            arrayOf(
                intArrayOf(1, 1, 2, 3),
                intArrayOf(1, 3, 2, 4),
                intArrayOf(3, 1, 4, 2),
                intArrayOf(3, 2, 4, 4)
            )
        )
    )
    println(
        isRectangleCover(
            arrayOf(
                intArrayOf(1, 1, 3, 3),
                intArrayOf(3, 1, 4, 2),
                intArrayOf(1, 3, 2, 4),
                intArrayOf(3, 2, 4, 4)
            )
        )
    )
    println(
        isRectangleCover(
            arrayOf(
                intArrayOf(1, 1, 3, 3),
                intArrayOf(3, 1, 4, 2),
                intArrayOf(1, 3, 2, 4),
                intArrayOf(2, 2, 4, 4),
            )
        )
    )
}
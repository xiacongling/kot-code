package me.xiacongling.code.leet.k0.h3

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 335, title = "路径交叉", difficulty = Difficulty.HARD, description = """
给你一个整数数组 distance 。

从 X-Y 平面上的点 (0,0) 开始，先向北移动 distance[0] 米，然后向西移动 distance[1] 米，
向南移动 distance[2] 米，向东移动 distance[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。

判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。

示例 1：
输入：distance = [2,1,1,2]
输出：true

示例 2：
输入：distance = [1,2,3,4]
输出：false

示例 3：
输入：distance = [1,1,1,1]
输出：true

提示：
* 1 <=distance.length <= 10^5
* 1 <=distance[i] <= 10^5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/self-crossing
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun isSelfCrossing(distance: IntArray): Boolean {
    val n = distance.size
    if (n < 4) return false

    var f1 = (distance[2] <= distance[0])
    if (f1 && distance[3] >= distance[1]) return true

    var f2 = distance[3] == distance[1]
    for (i in 4 until distance.size) {
        val d = distance[i]
        val pre = distance[i - 2]
        if (f1) {
            if (d >= pre) return true
        } else if (f2) {
            if (d >= pre - distance[i - 4]) return true
            f1 = true
        } else if (d < pre - distance[i - 4]) {
            f1 = true
        } else if (d <= pre) {
            f2 = true
        }
    }
    return false
}

fun main() {
    println(isSelfCrossing(intArrayOf(2, 1, 1, 2))) // true
    println(isSelfCrossing(intArrayOf(1, 2, 3, 4))) // false
    println(isSelfCrossing(intArrayOf(1, 1, 1, 1))) // true
}
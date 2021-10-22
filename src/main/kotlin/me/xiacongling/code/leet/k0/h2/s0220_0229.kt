package me.xiacongling.code.leet.k0.h2

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 223, title = "矩形面积", difficulty = Difficulty.MEDIUM, description = """
给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。

每个矩形由其 左下 顶点和 右上 顶点坐标表示：

第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。

示例 1：
![img](https://assets.leetcode.com/uploads/2021/05/08/rectangle-plane.png)
输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, 
     bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
输出：45

示例 2：
输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, 
     bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
输出：16

提示：
* -10^4 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 10^4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rectangle-area
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun computeArea(ax1: Int, ay1: Int, ax2: Int, ay2: Int, bx1: Int, by1: Int, bx2: Int, by2: Int): Int {
    fun intersectLen(a1: Int, a2: Int, b1: Int, b2: Int) = maxOf(0, minOf(b2, a2) - maxOf(b1, a1))

    val areaA = (ax2 - ax1) * (ay2 - ay1)
    val areaB = (bx2 - bx1) * (by2 - by1)
    val areaIntersect = intersectLen(ax1, ax2, bx1, bx2) * intersectLen(ay1, ay2, by1, by2)
    return areaA - areaIntersect + areaB
}

@Solution(
    id = 229, title = "求众数II", difficulty = Difficulty.MEDIUM, description = """
给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊n/3⌋ 次的元素。

示例 1：
输入：[3,2,3]
输出：[3]

示例 2：
输入：nums = [1]
输出：[1]

示例 3：
输入：[1,1,1,3,3,2,2,2]
输出：[1,2]

提示：
* 1 <= nums.length <= 5 * 10^4
* -10^9 <= nums[i] <= 10^9

进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/majority-element-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun majorityElement(nums: IntArray): List<Int> {
    if (nums.size < 2) return nums.toList()
    val candidates = intArrayOf(nums[0], nums[1])
    val counts = intArrayOf(0, 0)

    for (num in nums) {
        if (counts[0] == 0) {
            if (num == candidates[1] && counts[1] > 0) continue
            counts[0] = 1
            candidates[0] = num
        } else if (num == candidates[0]) {
            counts[0] += 1
        } else if (counts[1] == 0) {
            if (num == candidates[0]) continue
            counts[1] = 1
            candidates[1] = num
        } else if (num == candidates[1]) {
            counts[1] += 1
        } else {
            counts[0] -= 1
            counts[1] -= 1
        }
    }
    counts[0] = 0
    counts[1] = 0
    for (num in nums) {
        if (num == candidates[0]) {
            counts[0] += 1
        } else if (num == candidates[1]) {
            counts[1] += 1
        }
    }
    val threshold = nums.size / 3
    return candidates.withIndex().filter { counts[it.index] > threshold }.map { it.value }
}

fun main() {
    println(majorityElement(intArrayOf(3, 2, 3)))
    println(majorityElement(intArrayOf()))
    println(majorityElement(intArrayOf(1)))
    println(majorityElement(intArrayOf(1, 1)))
    println(majorityElement(intArrayOf(1, 1, 1, 3, 3, 2, 2, 2)))
}
package me.xiacongling.code.leet.k0.h4

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution
import me.xiacongling.code.util.MonoStack

@Solution(
    id = 492, title = "构造矩形", difficulty = Difficulty.EASY, description = """
作为一位 web 开发者，懂得怎样去规划一个页面的尺寸是很重要的。
现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 
且满足以下要求的矩形的页面。要求：

1. 你设计的矩形页面必须等于给定的目标面积。
2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
3. 长度 L 和宽度 W 之间的差距应当尽可能小。

你需要按顺序输出你设计的页面的长度 L 和宽度 W。

示例：
输入: 4
输出: [2, 2]
解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1] 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。

说明:
给定的面积不大于 10,000,000 且为正整数。
你设计的页面的长度和宽度必须都是正整数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/construct-the-rectangle
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun constructRectangle(area: Int): IntArray {
    if (area == 1) return intArrayOf(1, 1)
    val max = kotlin.math.floor(kotlin.math.sqrt(area.toDouble())).toInt()
    for (i in max downTo 1) {
        if (area % i == 0) return intArrayOf(area / i, i)
    }
    return intArrayOf(area, 1)
}

@Solution(
    id = 496, title = "下一个更大元素 I", difficulty = Difficulty.EASY, description = """
给你两个没有重复元素的数组 nums1 和 nums2 ，其中 nums1 是 nums2 的子集。

请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。

nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1。

示例 1:

输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
输出: [-1,3,-1]
解释:
    对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
    对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
    对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
    
示例 2:
输入: nums1 = [2,4], nums2 = [1,2,3,4].
输出: [3,-1]
解释:
    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。

提示：
* 1 <= nums1.length <= nums2.length <= 1000
* 0 <= nums1[i], nums2[i] <= 10^4
* nums1 和 nums2 中所有整数互不相同
* nums1 中的所有整数同样出现在 nums2 中

进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/next-greater-element-i
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
    val stack = MonoStack(asc = false)
    val next: MutableMap<Int, Int> = mutableMapOf()
    for (num in nums2.reversed()) {
        next[num] = stack.push(num) ?: -1
    }
    return nums1.map { next[it] ?: -1 }.toIntArray()
}


fun main() {
    println(nextGreaterElement(intArrayOf(4, 1, 2), intArrayOf(1, 3, 4, 2)).toList())
    println(nextGreaterElement(intArrayOf(2, 4), intArrayOf(1, 2, 3, 4)).toList())
}

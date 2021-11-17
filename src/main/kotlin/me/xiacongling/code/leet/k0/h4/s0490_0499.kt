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
    id = 495, title = "提莫攻击", difficulty = Difficulty.EASY, description = """
在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄。他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。

当提莫攻击艾希，艾希的中毒状态正好持续 duration 秒。

正式地讲，提莫在 t 发起发起攻击意味着艾希在时间区间 [t, t + duration - 1]（含 t 和 t + duration - 1）
处于中毒状态。如果提莫在中毒影响结束 前 再次攻击，中毒状态计时器将会 重置 ，在新的攻击之后，中毒影响将会在 
duration 秒后结束。

给你一个非递减的整数数组 timeSeries，其中 timeSeries[i] 表示提莫在 timeSeries[i] 秒时对艾希发起攻击，
以及一个表示中毒持续时间的整数 duration 。

返回艾希处于中毒状态的总秒数。

示例 1：
输入：timeSeries = [1,4], duration = 2
输出：4
解释：提莫攻击对艾希的影响如下：
- 第 1 秒，提莫攻击艾希并使其立即中毒。中毒状态会维持 2 秒，即第 1 秒和第 2 秒。
- 第 4 秒，提莫再次攻击艾希，艾希中毒状态又持续 2 秒，即第 4 秒和第 5 秒。
艾希在第 1、2、4、5 秒处于中毒状态，所以总中毒秒数是 4 。

示例 2：
输入：timeSeries = [1,2], duration = 2
输出：3
解释：提莫攻击对艾希的影响如下：
- 第 1 秒，提莫攻击艾希并使其立即中毒。中毒状态会维持 2 秒，即第 1 秒和第 2 秒。
- 第 2 秒，提莫再次攻击艾希，并重置中毒计时器，艾希中毒状态需要持续 2 秒，即第 2 秒和第 3 秒。
艾希在第 1、2、3 秒处于中毒状态，所以总中毒秒数是 3 。

提示：
* 1 <= timeSeries.length <= 10^4
* 0 <= timeSeries[i], duration <= 10^7
* timeSeries 按 非递减 顺序排列

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/teemo-attacking
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findPoisonedDuration(timeSeries: IntArray, duration: Int): Int {
    var result = 0
    for (i in 1 until timeSeries.size) {
        val t = timeSeries[i]
        val gap = t - timeSeries[i - 1]
        result += if (gap < duration) gap else duration
    }
    return result + duration
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

    // 495
    println(findPoisonedDuration(intArrayOf(1, 4), 2))
    println(findPoisonedDuration(intArrayOf(1, 2), 2))
}

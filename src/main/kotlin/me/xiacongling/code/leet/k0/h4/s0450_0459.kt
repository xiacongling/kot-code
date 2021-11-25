package me.xiacongling.code.leet.k0.h4

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 453, title = "最小操作次数使数组元素相等", difficulty = Difficulty.EASY, description = """
给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。

示例 1：

输入：nums = [1,2,3]
输出：3
解释：
只需要3次操作（注意每次操作会增加两个元素的值）：
[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

示例 2：
输入：nums = [1,1,1]
输出：0

提示：
* n == nums.length
* 1 <= nums.length <= 10^5
* -10^9 <= nums[i] <= 10^9
* 答案保证符合 32-bit 整数

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun minMoves(nums: IntArray): Int {
    // (n-1个元素加1) 与 (1个元素减1) 是等价的
    var sum = 0L
    var min: Int = Int.MAX_VALUE
    for (num in nums) {
        sum += num
        if (num < min) min = num
    }
    return (sum - min * nums.size).toInt()
}

@Solution(
    id = 458, title = "可怜的小猪", difficulty = Difficulty.HARD, description = """
有 buckets 桶液体，其中 正好 有一桶含有毒药，其余装的都是水。它们从外观看起来都一样。
为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，通过观察猪是否会死进行判断。不幸的是，
你只有 minutesToTest 分钟时间来确定哪桶液体是有毒的。

喂猪的规则如下：
1. 选择若干活猪进行喂养
2. 可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。
3. 小猪喝完水后，必须有 minutesToDie 分钟的冷却时间。在这段时间里，你只能观察，而不允许继续喂猪。
4. 过了 minutesToDie 分钟后，所有喝到毒药的猪都会死去，其他所有猪都会活下来。
5. 重复这一过程，直到时间用完。

给你桶的数目 buckets ，minutesToDie 和 minutesToTest ，返回在规定时间内判断哪个桶有毒所需的 最小 猪数。

示例 1：
输入：buckets = 1000, minutesToDie = 15, minutesToTest = 60
输出：5

示例 2：
输入：buckets = 4, minutesToDie = 15, minutesToTest = 15
输出：2

示例 3：
输入：buckets = 4, minutesToDie = 15, minutesToTest = 30
输出：2

提示：

* 1 <= buckets <= 1000
* 1 <=minutesToDie <=minutesToTest <= 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/poor-pigs
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun poorPigs(buckets: Int, minutesToDie: Int, minutesToTest: Int): Int {
    val states = minutesToTest / minutesToDie + 1
    return kotlin.math.ceil(kotlin.math.log(buckets.toDouble(), states.toDouble())).toInt()
}

package me.xiacongling.code.leet.k0.h0

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.ListNode
import me.xiacongling.code.leet.Solution


@Solution(
    id = 1, title = "两数之和", difficulty = Difficulty.EASY, description = """
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target 的那两个整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。你可以按任意顺序返回答案。

示例 1：
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

示例 2：
输入：nums = [3,2,4], target = 6
输出：[1,2]

示例 3：
输入：nums = [3,3], target = 6
输出：[0,1]

提示：
* 2 <= nums.length <= 10^4
* -10^9 <= nums[i] <= 10^9
* -10^9 <= target <= 10^9
* 只会存在一个有效答案

进阶：你可以想出一个时间复杂度小于 O(n^2) 的算法吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/two-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun twoSum(nums: IntArray, target: Int): IntArray {
    val complements: MutableMap<Int, Int> = HashMap()
    for ((i, num) in nums.withIndex()) {
        val c = target - num
        if (c in complements) {
            return intArrayOf(complements[c]!!, i)
        }
        complements[num] = i
    }
    return intArrayOf(0, 0);
}

@Solution(
    id = 2, title = "两数相加", difficulty = Difficulty.MEDIUM, description = """
给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字 0 之外，这两个数都不会以 0 开头。


示例 1：
 
  2 -> 4 -> 3
  5 -> 6 -> 4
  -----------
  7 -> 0 -> 8
 
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.

示例 2：
输入：l1 = [0], l2 = [0]
输出：[0]

示例 3：
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]

提示：
* 每个链表中的节点数在范围 [1, 100] 内
* 0 <= Node.val <= 9
* 题目数据保证列表表示的数字不含前导零

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-two-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    fun reverse(l: ListNode?): ListNode? {
        var h: ListNode? = null
        var p: ListNode? = l
        while (p?.next != null) {
            val n = p
            p = n?.next
            n?.next = h
            h = n
        }
        return h
    }

    var r1: ListNode? = reverse(l1)
    var r2: ListNode? = reverse(l2)
    var p: ListNode? = null
    var c = 0

    while (r1 != null && r2 != null) {
        val sum = r1.`val` + r2.`val` + c
        val head = ListNode(sum % 10)
        c = sum % 10
        head.next = p
        p = head

        r1 = r1.next
        r2 = r2.next
    }

    if (r2 != null) {
        r1 = r2
    }
    while (r1 != null) {
        val sum = r1.`val` + c
        val head = ListNode(sum % 10)
        c = sum % 10
        head.next = p
        p = head
    }
    if (c > 0) {
        val head = ListNode(c)
        head.next = p
        p = head
    }

    return p
}

@Solution(
    id = 3, title = "无重复字符的最长子串", difficulty = Difficulty.MEDIUM, description = """
给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。

示例 1:
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

示例 4:
输入: s = ""
输出: 0

提示：
* 0 <= s.length <= 5 * 10^4
* s 由英文字母、数字、符号和空格组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun lengthOfLongestSubstring(s: String): Int {
    return 0
}
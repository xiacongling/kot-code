package me.xiacongling.code.leet.contest.week

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.ListNode
import me.xiacongling.code.leet.Solution

//

@Solution(
    id = 5914, title = "值相等的最小索引", difficulty = Difficulty.EASY, description = """
给你一个下标从 0 开始的整数数组 nums ，返回 nums 中满足 i mod 10 == nums[i] 的最小下标 i ；
如果不存在这样的下标，返回 -1。

x mod y 表示 x 除以 y 的 余数 。

示例 1：
输入：nums = [0,1,2]
输出：0
解释：
i=0: 0 mod 10 = 0 == nums[0].
i=1: 1 mod 10 = 1 == nums[1].
i=2: 2 mod 10 = 2 == nums[2].
所有下标都满足 i mod 10 == nums[i] ，所以返回最小下标 0

示例 2：
输入：nums = [4,3,2,1]
输出：2
解释：
i=0: 0 mod 10 = 0 != nums[0].
i=1: 1 mod 10 = 1 != nums[1].
i=2: 2 mod 10 = 2 == nums[2].
i=3: 3 mod 10 = 3 != nums[3].
2 唯一一个满足 i mod 10 == nums[i] 的下标

示例 3：
输入：nums = [1,2,3,4,5,6,7,8,9,0]
输出：-1
解释：不存在满足 i mod 10 == nums[i] 的下标
示例 4：

输入：nums = [2,1,3,5,2]
输出：1
解释：1 是唯一一个满足 i mod 10 == nums[i] 的下标

提示：
* 1 <= nums.length <= 100
* 0 <= nums[i] <= 9

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/smallest-index-with-equal-value
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun smallestEqual(nums: IntArray): Int {
    for ((i, v) in nums.withIndex()) {
        if (i % 10 == v) return i
    }
    return -1
}

@Solution(
    id = 5915, title = "", difficulty = Difficulty.MEDIUM, description = """
链表中的 临界点 定义为一个 局部极大值点 或 局部极小值点 。

如果当前节点的值 严格大于 前一个节点和后一个节点，那么这个节点就是一个 局部极大值点 。

如果当前节点的值 严格小于 前一个节点和后一个节点，那么这个节点就是一个 局部极小值点 。

注意：节点只有在同时存在前一个节点和后一个节点的情况下，才能成为一个 局部极大值点 / 极小值点 。

给你一个链表 head ，返回一个长度为 2 的数组 [minDistance, maxDistance]，其中 minDistance 是任意两个
不同临界点之间的最小距离，maxDistance 是任意两个不同临界点之间的最大距离。如果临界点少于两个，则返回 [-1，-1]。

示例 1：

3 -> 1

输入：head = [3,1]
输出：[-1,-1]
解释：链表 [3,1] 中不存在临界点。

示例 2：

5 -> 3 -> 1 -> 2 -> 5 -> 1 -> 2

输入：head = [5,3,1,2,5,1,2]
输出：[1,3]
解释：存在三个临界点：
- [5,3,1,2,5,1,2]：第三个节点是一个局部极小值点，因为 1 比 3 和 2 小。
- [5,3,1,2,5,1,2]：第五个节点是一个局部极大值点，因为 5 比 2 和 1 大。
- [5,3,1,2,5,1,2]：第六个节点是一个局部极小值点，因为 1 比 5 和 2 小。
第五个节点和第六个节点之间距离最小。minDistance = 6 - 5 = 1 。
第三个节点和第六个节点之间距离最大。maxDistance = 6 - 3 = 3 。

示例 3：

1 -> 3 -> 2 -> 2 -> 3 -> 2 -> 2 -> 2 -> 7

输入：head = [1,3,2,2,3,2,2,2,7]
输出：[3,3]
解释：存在两个临界点：
- [1,3,2,2,3,2,2,2,7]：第二个节点是一个局部极大值点，因为 3 比 1 和 2 大。
- [1,3,2,2,3,2,2,2,7]：第五个节点是一个局部极大值点，因为 3 比 2 和 2 大。
最小和最大距离都存在于第二个节点和第五个节点之间。
因此，minDistance 和 maxDistance 是 5 - 2 = 3 。
注意，最后一个节点不算一个局部极大值点，因为它之后就没有节点了。

示例 4：

2 -> 3 -> 3 -> 2

输入：head = [2,3,3,2]
输出：[-1,-1]
解释：链表 [2,3,3,2] 中不存在临界点。

提示：
* 链表中节点的数量在范围 [2, 10^5] 内
* 1 <= Node.val <= 10^5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun nodesBetweenCriticalPoints(head: ListNode?): IntArray {
//    if (head?.next == null) {
//        return intArrayOf(-1, -1)
//    }
//    var minCp = -1
//    var preCp = -1
//    var pre = head.`val`
//    var p = head.next
//    var asc: Boolean? = null
//    while (p != null) {
//
//        p = p.next
//    }
    return intArrayOf()
}

@Solution(
    id = 5916, title = "转化数字的最小运算数", difficulty = Difficulty.MEDIUM, description = """
给你一个下标从 0 开始的整数数组 nums ，该数组由 互不相同 的数字组成。另给你两个整数 start 和 goal 。

整数 x 的值最开始设为 start ，你打算执行一些运算使 x 转化为 goal 。你可以对数字 x 重复执行下述运算：

如果 0 <= x <= 1000 ，那么，对于数组中的任一下标 i（0 <= i < nums.length），可以将 x 设为下述任一值：
* x + nums[i]
* x - nums[i]
* x ^ nums[i]（按位异或 XOR）

注意，你可以按任意顺序使用每个 nums[i] 任意次。使 x 越过 0 <= x <= 1000 范围的运算同样可以生效，
但该该运算执行后将不能执行其他运算。

返回将 x = start 转化为 goal 的最小操作数；如果无法完成转化，则返回 -1 。

示例 1：
输入：nums = [1,3], start = 6, goal = 4
输出：2
解释：
可以按 6 → 7 → 4 的转化路径进行，只需执行下述 2 次运算：
- 6 ^ 1 = 7
- 7 ^ 3 = 4

示例 2：
输入：nums = [2,4,12], start = 2, goal = 12
输出：2
解释：
可以按 2 → 14 → 12 的转化路径进行，只需执行下述 2 次运算：
- 2 + 12 = 14
- 14 - 2 = 12

示例 3：
输入：nums = [3,5,7], start = 0, goal = -4
输出：2
解释：
可以按 0 → 3 → -4 的转化路径进行，只需执行下述 2 次运算：
- 0 + 3 = 3
- 3 - 7 = -4
注意，最后一步运算使 x 超过范围 0 <= x <= 1000 ，但该运算仍然可以生效。

示例 4：
输入：nums = [2,8,16], start = 0, goal = 1
输出：-1
解释：
无法将 0 转化为 1

示例 5：
输入：nums = [1], start = 0, goal = 3
输出：3
解释：
可以按 0 → 1 → 2 → 3 的转化路径进行，只需执行下述 3 次运算：
- 0 + 1 = 1 
- 1 + 1 = 2
- 2 + 1 = 3
 
提示：
* 1 <= nums.length <= 1000
* -10^9 <= nums[i], goal <= 10^9
* 0 <= start <= 1000
* start != goal
* nums 中的所有整数互不相同

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-operations-to-convert-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun minimumOperations(nums: IntArray, start: Int, goal: Int): Int {
    // todo
    return 0
}

@Solution(
    id = 5917, title = "同源字符串检测", difficulty = Difficulty.HARD, description = """
原字符串由小写字母组成，可以按下述步骤编码：
* 任意将其分割为由若干非空子字符串组成的一个序列。
* 任意选择序列中的一些元素（也可能不选择），然后将这些元素替换为元素各自的长度（作为一个数字型的字符串）。
* 重新 顺次连接 序列，得到编码后的字符串。

例如，编码 "abcdefghijklmnop" 的一种方法可以描述为：
* 将原字符串分割得到一个序列：["ab", "cdefghijklmn", "o", "p"] 。
* 选出其中第二个和第三个元素并分别替换为它们自身的长度。序列变为 ["ab", "12", "1", "p"] 。
* 重新顺次连接序列中的元素，得到编码后的字符串："ab121p" 。

给你两个编码后的字符串 s1 和 s2 ，由小写英文字母和数字 1-9 组成。如果存在能够同时编码得到 s1 和 s2 原字符串，返回 true ；否则，返回 false。

注意：生成的测试用例满足 s1 和 s2 中连续数字数不超过 3 。 

示例 1：
输入：s1 = "internationalization", s2 = "i18n"
输出：true
解释："internationalization" 可以作为原字符串
- "internationalization" 
  -> 分割：      ["internationalization"]
  -> 不替换任何元素
  -> 连接：      "internationalization"，得到 s1
- "internationalization"
  -> 分割：      ["i", "nternationalizatio", "n"]
  -> 替换：      ["i", "18",                 "n"]
  -> 连接：      "i18n"，得到 s2

示例 2：
输入：s1 = "l123e", s2 = "44"
输出：true
解释："leetcode" 可以作为原字符串
- "leetcode" 
  -> 分割：       ["l", "e", "et", "cod", "e"]
  -> 替换：       ["l", "1", "2",  "3",   "e"]
  -> 连接：       "l123e"，得到 s1
- "leetcode" 
  -> 分割：       ["leet", "code"]
  -> 替换：       ["4",    "4"]
  -> 连接：       "44"，得到 s2

示例 3：
输入：s1 = "a5b", s2 = "c5b"
输出：false
解释：不存在这样的原字符串
- 编码为 s1 的字符串必须以字母 'a' 开头
- 编码为 s2 的字符串必须以字母 'c' 开头

示例 4：
输入：s1 = "112s", s2 = "g841"
输出：true
解释："gaaaaaaaaaaaas" 可以作为原字符串
- "gaaaaaaaaaaaas"
  -> 分割：       ["g", "aaaaaaaaaaaa", "s"]
  -> 替换：       ["1", "12",           "s"]
  -> 连接：       "112s"，得到 s1
- "gaaaaaaaaaaaas"
  -> 分割：       ["g", "aaaaaaaa", "aaaa", "s"]
  -> 替换：       ["g", "8",        "4",    "1"]
  -> 连接         "g841"，得到 s2

示例 5：
输入：s1 = "ab", s2 = "a2"
输出：false
解释：不存在这样的原字符串
- 编码为 s1 的字符串由两个字母组成
- 编码为 s2 的字符串由三个字母组成

提示：
* 1 <= s1.length, s2.length <= 40
* s1 和 s2 仅由数字 1-9 和小写英文字母组成
* s1 和 s2 中连续数字数不超过 3

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/check-if-an-original-string-exists-given-two-encoded-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)

fun possiblyEquals(s1: String, s2: String): Boolean {
    // todo
    return false
}

fun main() {
    println(smallestEqual(intArrayOf(0, 1, 2)))
    println(smallestEqual(intArrayOf(4, 3, 2, 1)))
    println(smallestEqual(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)))
    println(smallestEqual(intArrayOf(2, 1, 3, 5, 2)))
}
package me.xiacongling.code.leet.k0.h2

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.ListNode
import me.xiacongling.code.leet.Solution
import me.xiacongling.code.leet.TreeNode

@Solution(
    id = 230, title = "二叉搜索树中第K小的元素", difficulty = Difficulty.MEDIUM, description = """
给定一个二叉搜索树的根节点 root，和一个整数 k，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。

示例 1：
  3
 / \
1   4
 \
  2
输入：root = [3,1,4,null,2], k = 1
输出：1

示例 2：
      5
     / \
    3   6
   / \
  2   4
 /
1
输入：root = [5,3,6,2,4,null,null,1], k = 3
输出：3

提示：
* 树中的节点数为 n 。
* 1 <= k <= n <= 10^4
* 0 <= Node.val <= 10^4

进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun kthSmallest(root: TreeNode?, k: Int): Int {
    var found = -1
    fun lessCnt(node: TreeNode?, base: Int): Int {
        if (found >= 0 || node == null) return base
        val c = lessCnt(node.left, base)
        if (c == k - 1) {
            found = node.`val`
        }
        return lessCnt(node.right, c + 1)
    }
    lessCnt(root, 0)
    return found
}

@Solution(
    id = 237, title = "", difficulty = Difficulty.EASY, description = """
请编写一个函数，用于删除单链表中某个特定节点。在设计函数时需要注意，你无法访问链表的头节点head，
只能直接访问要被删除的节点 。

题目数据保证需要删除的节点不是末尾节点 。

示例 1：

4 -> 5 -> 1 -> 9
     |    
     v
4 ------> 1 -> 9

输入：head = [4,5,1,9], node = 5
输出：[4,1,9]
解释：指定链表中值为5的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9

示例 2：

4 -> 5 -> 1 -> 9
          |    
          v
4 -> 5 ------> 9

输入：head = [4,5,1,9], node = 1
输出：[4,5,9]
解释：指定链表中值为1的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9

示例 3：

1 -> 2 -> 3 -> 4
          |
          v
1 -> 2 ------> 4

输入：head = [1,2,3,4], node = 3
输出：[1,2,4]

示例 4：

0 -> 1
|
v
     1

输入：head = [0,1], node = 0
输出：[1]

示例 5：

-3 -> 5 -> -99
 |
 v
      5 -> -99

输入：head = [-3,5,-99], node = -3
输出：[5,-99]

提示：
* 链表中节点的数目范围是 [2, 1000]
* -1000 <= Node.val <= 1000
* 链表中每个节点的值都是唯一的
* 需要删除的节点 node 是链表中的一个有效节点，且不是末尾节点

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun deleteNode(node: ListNode?) {
    var p = node
    var pre: ListNode? = null
    while (p?.next != null) {
        p.`val` = p.next!!.`val`
        pre = p
        p = p.next
    }
    pre?.next = null
}

fun main() {
    // 230
    println("== Test Cases for #230 ==")

    val r1 = TreeNode(3)
    r1.left = TreeNode(1)
    r1.left!!.right = TreeNode(2)
    r1.right = TreeNode(4)
    println(kthSmallest(r1, 1))

    val r2 = TreeNode(5)
    r2.left = TreeNode(3)
    r2.right = TreeNode(6)
    r2.left!!.left = TreeNode(2)
    r2.left!!.right = TreeNode(4)
    r2.left!!.left!!.left = TreeNode(1)
    println(kthSmallest(r2, 3))

    val r3 = TreeNode(1)
    r3.right = TreeNode(2)
    println(kthSmallest(r3, 2))

    // 237
    println("== Test Cases for #237 ==")
    var input237 = ListNode.of(4, 5, 1, 9)
    deleteNode(input237!!.next!!.next)
    println(input237 == ListNode.of(4, 5, 9))

    input237 = ListNode.of(4, 5, 1, 9)
    deleteNode(input237!!.next)
    println(input237 == ListNode.of(4, 1, 9))

    input237 = ListNode.of(1, 2, 3, 4)
    deleteNode(input237!!.next!!.next)
    println(input237 == ListNode.of(1, 2, 4))

    input237 = ListNode.of(0, 1)
    deleteNode(input237)
    println(input237 == ListNode(1))

    input237 = ListNode.of(-3, 5, 99)
    deleteNode(input237)
    println(input237 == ListNode.of(5, 99))
}
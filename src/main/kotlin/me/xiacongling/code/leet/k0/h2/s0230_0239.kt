package me.xiacongling.code.leet.k0.h2

import me.xiacongling.code.leet.Difficulty
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

fun main() {
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
}
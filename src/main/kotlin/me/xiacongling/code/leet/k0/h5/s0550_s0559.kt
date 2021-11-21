package me.xiacongling.code.leet.k0.h5

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.NTreeNode
import me.xiacongling.code.leet.Solution

typealias Node = NTreeNode

@Solution(
    id = 559, title = "N 叉树的最大深度", difficulty = Difficulty.EASY, description = """
给定一个 N 叉树，找到其最大深度。

最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。

N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。

示例 1：

      1
    / | \
   3  2  4
  / \
 5   6

输入：root = [1,null,3,2,4,null,5,6]
输出：3

示例 2：

        1
   /   / \   \ 
  2   3   4   5
     / \  |  / \
    6   7 8  9  10
        | |  |
       11 12 13
        |  
       14
输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
输出：5

提示：

* 树的深度不会超过 1000 。
* 树的节点数目位于 [0, 10^4] 之间。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)

fun maxDepth(root: Node?): Int {
    if (root == null) return 0
    var subDepth = 0
    for (child in root.children) {
        subDepth = kotlin.math.max(subDepth, maxDepth(child))
    }
    return 1 + subDepth
}
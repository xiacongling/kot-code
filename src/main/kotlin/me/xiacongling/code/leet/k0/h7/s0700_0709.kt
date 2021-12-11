package me.xiacongling.code.leet.k0.h7

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution
import me.xiacongling.code.leet.TreeNode

@Solution(
    id = 700, title = "二叉搜索树中的搜索", difficulty = Difficulty.EASY, description = """
给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。返回以该节点为根的子树。
如果节点不存在，则返回 NULL。

例如，给定二叉搜索树:

        4
       / \
      2   7
     / \
    1   3

和值 2，你应该返回如下子树:

      2     
     / \   
    1   3

在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-in-a-binary-search-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
    if (root == null || root.`val` == `val`) return root
    return if (root.`val` > `val`) searchBST(root.left, `val`) else searchBST(root.right, `val`)
}

@Solution(
    id = 709, title = "转换成小写字母", difficulty = Difficulty.EASY, description = """
给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。

示例 1：
输入：s = "Hello"
输出："hello"

示例 2：
输入：s = "here"
输出："here"

示例 3：
输入：s = "LOVELY"
输出："lovely"
 
提示：

* 1 <= s.length <= 100
* s 由 ASCII 字符集中的可打印字符组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/to-lower-case
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun toLowerCase(s: String): String {
    return s.lowercase()
}

package me.xiacongling.code.leet.k0.h4

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Node
import me.xiacongling.code.leet.Solution
import me.xiacongling.code.leet.TreeNode

@Solution(
    id = 430, title = "扁平化多级双向链表", difficulty = Difficulty.MEDIUM, description = """
多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。
这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。

给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。

示例 1：
输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
输出：[1,2,3,7,8,11,12,9,10,4,5,6]
解释：输入的多级列表如下图所示：
![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/multilevellinkedlist.png)
扁平化后的链表如下图：
![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/multilevellinkedlistflattened.png)

示例 2：
输入：head = [1,2,null,3]
输出：[1,3,2]
解释：输入的多级列表如下图所示：
```
  1---2---NULL
  |
  3---NULL
```

示例 3：
输入：head = []
输出：[]

如何表示测试用例中的多级链表？
以 示例 1 为例：
```
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
```
序列化其中的每一级之后：

```
[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
```

为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。

```
[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
```

合并所有序列化结果，并去除末尾的 null。

```
[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
```

提示：
* 节点数目不超过 1000
* 1 <= Node.val <= 10^5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun flatten(root: Node?): Node? {
    fun doFlatten(h: Node): Pair<Node, Node> {
        var p: Node? = h
        var t: Node = h
        while (p != null) {
            t = p
            if (p.child != null) {
                val child: Node = p.child!!
                val next = p.next
                val (ch, ct) = doFlatten(child)
                p.child = null
                p.next = ch
                ch.prev = p
                p = next
                ct.next = next
                if (next == null) {
                    t = ct
                } else {
                    next.prev = ct
                }
            } else {
                p = p.next
            }
        }
        return Pair(h, t)
    }
    if (root != null) {
        doFlatten(root)
    }
    return root
}

@Solution(
    id = 434, title = "字符串中的单词数", difficulty = Difficulty.EASY, description = """
统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。

请注意，你可以假定字符串里不包括任何不可打印的字符。

示例:

输入: "Hello, my name is John"
输出: 5
解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-segments-in-a-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun countSegments(s: String): Int {
    var wordCnt = 0
    var inWord = false
    for (c in s) {
        if (c == ' ' && inWord) {
            wordCnt += 1
            inWord = false
        } else if (c != ' ') {
            inWord = true
        }
    }
    if (inWord) {
        wordCnt += 1
    }
    return wordCnt
}

@Solution(
    id = 437, title = "路径总和 III", difficulty = Difficulty.MEDIUM, description = """
给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

示例 1：
输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
输出：3
解释：和等于 8 的路径有 3 条，如图所示。

示例 2：
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：3

提示:

* 二叉树的节点个数的范围是 [0,1000]
* -10^9 <= Node.val <= 10^9
* -1000 <= targetSum <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/path-sum-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun pathSum(root: TreeNode?, targetSum: Int): Int {
    val ts = targetSum.toLong()
    fun startingPathSum(n: TreeNode?, s: Long): Int {
        if (n == null) return 0
        val term = if (n.`val`.toLong() == s) 1 else 0
        return term + startingPathSum(n.left, s - n.`val`) + startingPathSum(n.right, s - n.`val`)
    }

    fun pathCount(n: TreeNode?): Int {
        if (n == null) return 0
        return startingPathSum(n, ts) + pathCount(n.left) + pathCount(n.right)
    }

    return pathCount(root)
}

@Solution(
    id = 438, title = "找到字符串中所有字母异位词", difficulty = Difficulty.MEDIUM, description = """
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。
不考虑答案输出的顺序。

异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。

示例 1:
输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。

示例 2:
输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 

提示:
* 1 <= s.length, p.length <= 3 * 10^4
* s 和 p 仅包含小写字母

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findAnagrams(s: String, p: String): List<Int> {
    if (p.length > s.length) return listOf()
    val expect = IntArray(26)
    p.forEach { expect[it - 'a'] += 1 }

    val actual = IntArray(26)
    val result = mutableListOf<Int>()
    for ((i, c) in s.withIndex()) {
        actual[c - 'a'] += 1
        if (i >= p.length) actual[s[i - p.length] - 'a'] -= 1
        if (i >= p.length - 1 && expect.contentEquals(actual)) result.add(i - p.length + 1)
    }
    return result
}

fun main() {
    // 434
    println(countSegments("Hello, my name is John"))
    println(countSegments(""))
    println(countSegments("hello"))
    println(countSegments("hello w"))
    println(countSegments("hello world "))

    // 438
    println(findAnagrams("cbaebabacd", "abc"))
    println(findAnagrams("abab", "ab"))
}
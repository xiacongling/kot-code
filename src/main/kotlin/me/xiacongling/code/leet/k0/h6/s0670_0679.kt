package me.xiacongling.code.leet.k0.h6

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 673, title = "最长递增子序列的个数", difficulty = Difficulty.MEDIUM, description = """
给定一个未排序的整数数组，找到最长递增子序列的个数。

示例 1:
输入: [1,3,5,4,7]
输出: 2
解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。

示例 2:
输入: [2,2,2,2,2]
输出: 5
解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。

注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findNumberOfLIS(nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    var lengths: Array<Pair<Int, Int>> = arrayOf(Pair(1, 1))
    var globalMaxLen = 1

    for (i in 1 until nums.size) {
        var maxLen = 1
        var cnt = 1
        for (j in 0 until i) {
            if (nums[j] < nums[i]) {
                val len = lengths[j].first + 1
                if (len > maxLen) {
                    maxLen = len
                    cnt = lengths[j].second
                } else if (len == maxLen) {
                    cnt += lengths[j].second
                }
            }
        }
        lengths += Pair(maxLen, cnt)
        globalMaxLen = maxOf(globalMaxLen, maxLen)
    }
    return lengths
        .filter { it.first == globalMaxLen }
        .fold(0) { s, p -> s + p.second }
}

@Solution(
    id = 677, title = "键值映射", difficulty = Difficulty.MEDIUM, description = """
实现一个 MapSum 类，支持两个方法，insert 和 sum：

* MapSum() 初始化 MapSum 对象
* void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key，
  整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
* int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 
示例：
输入：
["MapSum", "insert", "sum", "insert", "sum"]
[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
输出：
[null, null, 3, null, 5]
解释：
MapSum mapSum = new MapSum();
mapSum.insert("apple", 3);  
mapSum.sum("ap");           // return 3 (apple = 3)
mapSum.insert("app", 2);    
mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)

提示：
* 1 <= key.length, prefix.length <= 50
* key 和 prefix 仅由小写英文字母组成
* 1 <= val <= 1000
* 最多调用 50 次 insert 和 sum

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/map-sum-pairs
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
class MapSum() {
    private var fin: Boolean = false
    private var v: Int = 0
    private var s: Int = 0
    private val children: MutableMap<Char, MapSum> = mutableMapOf()

    fun insert(key: String, `val`: Int) {
        insert(key, 0, `val`)
    }

    private fun insert(w: String, idx: Int, toAdd: Int): Int {
        if (idx < w.length) {
            val c = w[idx]
            val child: MapSum = children.compute(c) { _, v -> v ?: MapSum() }!!
            val delta = if (idx == w.length - 1) {
                val d = toAdd - child.v
                child.s = if (child.fin) toAdd else child.s + d
                child.fin = true
                child.v = toAdd
                d
            } else {
                child.insert(w, idx + 1, toAdd)
            }
            this.s += delta
            return delta
        }
        return 0
    }

    fun sum(prefix: String): Int {
        var p: MapSum = this
        for (c in prefix) {
            p = p.children[c] ?: return 0
        }
        return p.s
    }
}

@Solution(
    id = 678, title = "", difficulty = Difficulty.MEDIUM, description = """
给定一个只包含三种字符的字符串：（，）和 *，写一个函数来检验这个字符串是否为有效字符串。
有效字符串具有如下规则：

1. 任何左括号 ( 必须有相应的右括号 )。
2. 任何右括号 ) 必须有相应的左括号 (。
3. 左括号 ( 必须在对应的右括号之前 )。
4. * 可以被视为单个右括号 )，或单个左括号 (，或一个空字符串。
5. 一个空字符串也被视为有效字符串。

示例 1:
输入: "()"
输出: True

示例 2:
输入: "(*)"
输出: True

示例 3:
输入: "(*))"
输出: True

注意:
1. 字符串大小将在 [1，100] 范围内。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-parenthesis-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun checkValidString(s: String): Boolean {
    fun solutionDfsTLE(): Boolean {
        fun dfs(depth: Int, idx: Int): Boolean {
            if (idx >= s.length) return depth == 0
            if (depth > s.length - idx) return false
            val c = s[idx]
            if (c == '(') return dfs(depth + 1, idx + 1)
            if (c == ')') return depth > 0 && return dfs(depth - 1, idx + 1)
            return depth > 0 && dfs(depth - 1, idx + 1)
                    || dfs(depth, idx + 1)
                    || dfs(depth + 1, idx + 1)
        }
        return dfs(0, 0)
    }

    var min = 0
    var max = 0
    for (c in s) {
        if (c == '(') {
            min += 1
            max += 1
        } else if (c == ')') {
            if (max == 0) return false
            max -= 1
            if (min > 0) min -= 1
        } else {
            if (min > 0) min -= 1
            max += 1
        }
    }
    return min == 0
}

fun main() {
    println(checkValidString("()"))
    println(checkValidString(""))
    println(checkValidString("(*)"))
    println(checkValidString("(*))"))
    println(checkValidString("(*)))"))
    println(checkValidString("**************************************************))))))))))))))))))))))))))))))))))))))))))))))))))"))
    println(checkValidString("(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"))

    // 677
    val ms = MapSum()
    ms.insert("apple", 3)
    println(ms.sum("ap"))
    ms.insert("app", 2)
    println(ms.sum("ap"))
    println(ms.sum(""))
    ms.insert("apple", 2)
    println(ms.sum("ap"))
}


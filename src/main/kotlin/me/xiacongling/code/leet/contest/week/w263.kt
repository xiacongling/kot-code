package me.xiacongling.code.leet.contest.week

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 2042, title = "检查句子中的数字是否递增", difficulty = Difficulty.EASY, description = """
句子是由若干 token 组成的一个列表，token 间用 单个 空格分隔，句子没有前导或尾随空格。
每个 token 要么是一个由数字 0-9 组成的不含前导零的正整数，要么是一个由小写英文字母
组成的单词 。

示例，"a puppy has 2 eyes 4 legs" 是一个由 7 个 token 组成的句子：
"2" 和 "4" 是数字，其他像 "puppy" 这样的 tokens 属于单词。
给你一个表示句子的字符串 s ，你需要检查 s 中的 全部 数字是否从左到右严格递增（即，
除了最后一个数字，s 中的 每个 数字都严格小于它 右侧 的数字）。

如果满足题目要求，返回 true，否则，返回 false。

示例 1：
输入：s = "1 box has 3 blue 4 red 6 green and 12 yellow marbles"
输出：true
解释：句子中的数字是：1, 3, 4, 6, 12 。
这些数字是按从左到右严格递增的 1 < 3 < 4 < 6 < 12 。

示例 2：
输入：s = "hello world 5 x 5"
输出：false
解释：句子中的数字是：5, 5 。这些数字不是严格递增的。

示例 3：
输入：s = "sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s"
输出：false
解释：s 中的数字是：7, 51, 50, 60 。这些数字不是严格递增的。

示例 4：
输入：s = "4 5 11 26"
输出：true
解释：s 中的数字是：4, 5, 11, 26 。
这些数字是按从左到右严格递增的：4 < 5 < 11 < 26 。

提示：
* 3 <= s.length <= 200
* s 由小写英文字母、空格和数字 0 到 9 组成（包含 0 和 9）
* s 中数字 token 的数目在 2 和 100 之间（包含 2 和 100）
* s 中的 token 之间由单个空格分隔
* s 中至少有 两个 数字
* s 中的每个数字都是一个 小于 100 的 正 数，且不含前导零
* s 不含前导或尾随空格

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/check-if-numbers-are-ascending-in-a-sentence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun areNumbersAscending(s: String): Boolean {
    val numbers = s.split(" ").mapNotNull { it.toIntOrNull() }
    var prefix = -1
    for (n in numbers) {
        if (n <= prefix) {
            return false
        }
        prefix = n
    }
    return true
}

@Solution(
    id = 2043, title = "简易银行系统", difficulty = Difficulty.MEDIUM, description = """
你的任务是为一个很受欢迎的银行设计一款程序，以自动化执行所有传入的交易（转账，存款和取款）。
银行共有 n 个账户，编号从 1 到 n 。每个账号的初始余额存储在一个下标从 0 开始的整数数组
balance 中，其中第 (i + 1) 个账户的初始余额是 balance[i] 。

请你执行所有 有效的 交易。如果满足下面全部条件，则交易 有效 ：
* 指定的账户数量在 1 和 n 之间，且
* 取款或者转账需要的钱的总数 小于或者等于 账户余额。

实现 Bank 类：
* Bank(long[] balance) 使用下标从 0 开始的整数数组 balance 初始化该对象。
* boolean transfer(int account1, int account2, long money) 
  从编号为 account1 的账户向编号为 account2 的账户转帐 money 美元。
  如果交易成功，返回 true ，否则，返回 false 。
* boolean deposit(int account, long money) 向编号为 account 的账户
  存款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
* boolean withdraw(int account, long money) 从编号为 account 的账户
  取款 money 美元。如果交易成功，返回 true ；否则，返回 false。

示例：
输入：
["Bank", "withdraw", "transfer", "deposit", "transfer", "withdraw"]
[[[10, 100, 20, 50, 30]], [3, 10], [5, 1, 20], [5, 20], [3, 4, 15], [10, 50]]
输出：
[null, true, true, true, false, false]
解释：
Bank bank = new Bank([10, 100, 20, 50, 30]);
bank.withdraw(3, 10);    // 返回 true ，账户 3 的余额是 ${'$'}20 ，所以可以取款 ${'$'}10 。
                         // 账户 3 余额为 ${'$'}20 - ${'$'}10 = ${'$'}10 。
bank.transfer(5, 1, 20); // 返回 true ，账户 5 的余额是 ${'$'}30 ，所以可以转账 ${'$'}20 。
                         // 账户 5 的余额为 ${'$'}30 - ${'$'}20 = ${'$'}10 ，账户 1 的余额为 ${'$'}10 + ${'$'}20 = ${'$'}30 。
bank.deposit(5, 20);     // 返回 true ，可以向账户 5 存款 ${'$'}20 。
                         // 账户 5 的余额为 ${'$'}10 + ${'$'}20 = ${'$'}30 。
bank.transfer(3, 4, 15); // 返回 false ，账户 3 的当前余额是 ${'$'}10 。
                         // 所以无法转账 ${'$'}15 。
bank.withdraw(10, 50);   // 返回 false ，交易无效，因为账户 10 并不存在。

提示：
* n == balance.length
* 1 <= n, account, account1, account2 <= 10^5
* 0 <= balance[i], money <= 10^12
* transfer, deposit, withdraw 三个函数，每个 最多调用 10^4 次

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/simple-bank-system
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
class Bank(balance: LongArray) {
    val b = longArrayOf(0) + balance

    private fun check(acc: Int): Boolean {
        return acc > 0 && acc <= b.size
    }

    fun transfer(account1: Int, account2: Int, money: Long): Boolean {
        if (!check(account1) || !check(account2)) return false

        if (b[account1] >= money) {
            b[account1] -= money
            b[account2] += money
            return true
        }
        return false
    }

    fun deposit(account: Int, money: Long): Boolean {
        if (!check(account)) return false

        b[account] += money
        return true
    }

    fun withdraw(account: Int, money: Long): Boolean {
        if (!check(account)) return false
        if (b[account] >= money) {
            b[account] -= money
            return true
        }
        return false
    }

}


@Solution(
    id = 2044, title = "统计按位或能得到最大值的子集数目", difficulty = Difficulty.MEDIUM, description = """
给你一个整数数组 nums，请你找出 nums 子集按位或可能得到的最大值，
并返回按位或能得到最大值的 不同非空子集的数目 。

如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个子集。
如果选中的元素下标位置不一样，则认为两个子集不同 。

对数组 a 执行 按位或，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。

示例 1：
输入：nums = [3,1]
输出：2
解释：子集按位或能得到的最大值是 3 。有 2 个子集按位或可以得到 3 ：
- [3]
- [3,1]

示例 2：
输入：nums = [2,2,2]
输出：7
解释：[2,2,2] 的所有非空子集的按位或都可以得到 2 。总共有 23 - 1 = 7 个子集。

示例 3：
输入：nums = [3,2,1,5]
输出：6
解释：子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
- [3,5]
- [3,1,5]
- [3,2,5]
- [3,2,1,5]
- [2,5]
- [2,1,5]

提示：
* 1 <= nums.length <= 16
* 1 <= nums[i] <= 10^5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun countMaxOrSubsets(nums: IntArray): Int {
    val max = nums.reduce { a, b -> a or b }
    var result = 0
    fun find(idx: Int, cur: Int) {
        if (idx >= nums.size) return
        val next = cur or nums[idx]
        if (next == max) {
            result += 1
        }
        find(idx + 1, cur)
        find(idx + 1, next)
    }

    find(0, 0)
    return result
}

@Solution(
    id = 2045, title = "到达目的地的第二短时间", difficulty = Difficulty.HARD, description = """
城市用一个 双向连通 图表示，图中有 n 个节点，从 1 到 n 编号（包含 1 和 n）。
图中的边用一个二维整数数组 edges 表示，其中每个 edges[i] = [ui, vi] 表示一条
节点 ui 和节点 vi 之间的双向连通边。每组节点对由 最多一条 边连通，顶点不存在连接到自身的边。
穿过任意一条边的时间是 time 分钟。

每个节点都有一个交通信号灯，每 change 分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。
所有信号灯都同时 改变。你可以在 任何时候 进入某个节点，但是 只能 在节点信号灯是绿色时 才能离开。
如果信号灯是绿色 ，你不能 在节点等待，必须离开。

第二小的值 是严格大于 最小值的所有值中最小的值。
* 例如，[2, 3, 4] 中第二小的值是 3 ，而 [2, 2, 4] 中第二小的值是 4 。

给你 n、edges、time 和 change ，返回从节点 1 到节点 n 需要的 第二短时间 。

注意：
* 你可以 任意次 穿过任意顶点，包括 1 和 n 。
* 你可以假设在 启程时 ，所有信号灯刚刚变成 绿色 。

示例 1：
  1
 /|\
2 4 3
  |
  5
输入：n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
输出：13
解释：
上面的左图展现了给出的城市交通图。
右图中的蓝色路径是最短时间路径。
花费的时间是：
- 从节点 1 开始，总花费时间=0
- 1 -> 4：3 分钟，总花费时间=3
- 4 -> 5：3 分钟，总花费时间=6
因此需要的最小时间是 6 分钟。

右图中的红色路径是第二短时间路径。
- 从节点 1 开始，总花费时间=0
- 1 -> 3：3 分钟，总花费时间=3
- 3 -> 4：3 分钟，总花费时间=6
- 在节点 4 等待 4 分钟，总花费时间=10
- 4 -> 5：3 分钟，总花费时间=13
因此第二短时间是 13 分钟。  
    
示例 2：
1--2
输入：n = 2, edges = [[1,2]], time = 3, change = 2
输出：11
解释：
最短时间路径是 1 -> 2 ，总花费时间 = 3 分钟
最短时间路径是 1 -> 2 -> 1 -> 2 ，总花费时间 = 11 分钟

提示：
* 2 <= n <= 104
* n - 1 <= edges.length <= min(2 * 10^4, n * (n - 1) / 2)
* edges[i].length == 2
* 1 <= ui, vi <= n
* ui != vi
* 不含重复边
* 每个节点都可以从其他节点直接或者间接到达
* 1 <= time, change <= 10^3

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/second-minimum-time-to-reach-destination
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun secondMinimum(n: Int, edges: Array<IntArray>, time: Int, change: Int): Int {
    // TODO: failed
    val m: MutableMap<Int, MutableList<Int>> = mutableMapOf()
    for (edge in edges) {
        m.getOrPut(edge[0]) { mutableListOf() }.add(edge[1])
        m.getOrPut(edge[1]) { mutableListOf() }.add(edge[0])
    }

    val vis = IntArray(n + 1) { 0 }
    var layer: List<Int> = mutableListOf(1)
    var len = 1
    var flag = false
    var base = n
    while (layer.isNotEmpty()) {
        val nextLayer: MutableList<Int> = mutableListOf()
        for (node in layer) {
            val neighbors = m[node] ?: mutableListOf()
            for (p in neighbors) {
                if (vis[p] == 0) {
                    if (p == n) base = len
                    vis[p] = len
                    nextLayer.add(p)
                } else if (vis[p] == len - 1) {
                    flag = base == n || p == n
                }
            }
        }
        len += 1
        layer = nextLayer
    }
    base += if (flag) 1 else 2

    var c = 1
    var delay = 0
    var firstStop = -1
    for (t in 1..base) {
        val arrive = t * time
        while ((2 * c - 1) * change <= arrive) {
            c += 1
        }
        val r = kotlin.math.max(1, c - 1)
        val stop = (2 * r - 1) * change
        if (stop <= arrive && stop + change > arrive) {
            firstStop = t
            delay = stop + change - arrive
            break
        }
    }
    return if (firstStop < 0) {
        base * time
    } else {
        base * time + (base - 1) / firstStop * delay
    }
    // 57 / 59
}

fun main() {
    println("week contest #263")
    println(secondMinimum(2, arrayOf(intArrayOf(1, 2)), 3, 2))
    println(
        secondMinimum(
            5,
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(1, 4),
                intArrayOf(3, 4),
                intArrayOf(4, 5)
            ), 3, 5
        )
    )
}
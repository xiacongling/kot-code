package me.xiacongling.code.leet.contest.biweek

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution
import kotlin.math.absoluteValue

@Solution(
    id = 5585, title = "使每位学生都有座位的最少移动次数", difficulty = Difficulty.EASY, description = """
一个房间里有 n 个座位和 n 名学生，房间用一个数轴表示。给你一个长度为 n 的数组 seats，
其中 seats[i] 是第 i 个座位的位置。同时给你一个长度为 n 的数组 students ，其中 students[j] 
是第 j 位学生的位置。

你可以执行以下操作任意次：

* 增加或者减少第 i 位学生的位置，每次变化量为 1 （也就是将第 i 位学生从位置 x 移动到 x + 1 或者 x - 1）

请你返回使所有学生都有座位坐的 最少移动次数 ，并确保没有两位学生的座位相同。

请注意，初始时有可能有多个座位或者多位学生在 同一 位置。 

示例 1：
输入：seats = [3,1,5], students = [2,7,4]
输出：4
解释：学生移动方式如下：
- 第一位学生从位置 2 移动到位置 1 ，移动 1 次。
- 第二位学生从位置 7 移动到位置 5 ，移动 2 次。
- 第三位学生从位置 4 移动到位置 3 ，移动 1 次。
总共 1 + 2 + 1 = 4 次移动。

示例 2：
输入：seats = [4,1,5,9], students = [1,3,2,6]
输出：7
解释：学生移动方式如下：
- 第一位学生不移动。
- 第二位学生从位置 3 移动到位置 4 ，移动 1 次。
- 第三位学生从位置 2 移动到位置 5 ，移动 3 次。
- 第四位学生从位置 6 移动到位置 9 ，移动 3 次。
总共 0 + 1 + 3 + 3 = 7 次移动。

示例 3：
输入：seats = [2,2,6,6], students = [1,3,2,6]
输出：4
解释：学生移动方式如下：
- 第一位学生从位置 1 移动到位置 2 ，移动 1 次。
- 第二位学生从位置 3 移动到位置 6 ，移动 3 次。
- 第三位学生不移动。
- 第四位学生不移动。
总共 1 + 3 + 0 + 0 = 4 次移动。
 
提示：
* n == seats.length == students.length
* 1 <= n <= 100
* 1 <= seats[i], students[j] <= 100
"""
)
fun minMovesToSeat(seats: IntArray, students: IntArray): Int {
    seats.sort()
    students.sort()
    var result = 0
    for (i in seats.indices) {
        result += (seats[i] - students[i]).absoluteValue
    }
    return result
}

@Solution(
    id = 5886, title = "如果相邻两个颜色均相同则删除当前颜色", difficulty = Difficulty.MEDIUM, description = """
总共有 n 个颜色片段排成一列，每个颜色片段要么是 'A' 要么是 'B' 。
给你一个长度为 n 的字符串 colors ，其中 colors[i] 表示第 i 个颜色片段的颜色。

Alice 和 Bob 在玩一个游戏，他们 轮流 从这个字符串中删除颜色。Alice 先手 。

* 如果一个颜色片段为 'A' 且 相邻两个颜色 都是颜色 'A' ，那么 Alice 可以删除该颜色片段。
  Alice 不可以 删除任何颜色 'B' 片段。
* 如果一个颜色片段为 'B' 且 相邻两个颜色 都是颜色 'B' ，那么 Bob 可以删除该颜色片段。
  Bob 不可以 删除任何颜色 'A' 片段。
* Alice 和 Bob 不能 从字符串两端删除颜色片段。
* 如果其中一人无法继续操作，则该玩家 输 掉游戏且另一玩家 获胜 。

假设 Alice 和 Bob 都采用最优策略，如果 Alice 获胜，请返回 true，否则 Bob 获胜，返回 false。

示例 1：
输入：colors = "AAABABB"
输出：true
解释：
AAABABB -> AABABB
Alice 先操作。
她删除从左数第二个 'A' ，这也是唯一一个相邻颜色片段都是 'A' 的 'A' 。

现在轮到 Bob 操作。
Bob 无法执行任何操作，因为没有相邻位置都是 'B' 的颜色片段 'B' 。
因此，Alice 获胜，返回 true 。

示例 2：
输入：colors = "AA"
输出：false
解释：
Alice 先操作。
只有 2 个 'A' 且它们都在字符串的两端，所以她无法执行任何操作。
因此，Bob 获胜，返回 false 。

示例 3：
输入：colors = "ABBBBBBBAAA"
输出：false
解释：
ABBBBBBBAAA -> ABBBBBBBAA
Alice 先操作。
她唯一的选择是删除从右数起第二个 'A' 。

ABBBBBBBAA -> ABBBBBBAA
接下来轮到 Bob 操作。
他有许多选择，他可以选择任何一个 'B' 删除。

然后轮到 Alice 操作，她无法删除任何片段。
所以 Bob 获胜，返回 false 。
 
提示：
* 1 <= colors.length <= 105
* colors 只包含字母 'A' 和 'B'
"""
)
fun winnerOfGame(colors: String): Boolean {
    var a = 0
    var b = 0
    var cnt = 0
    var flag = 'N'
    for (c in colors) {
        if (c == flag) {
            cnt += 1
        } else {
            if (flag == 'A' && cnt > 2) {
                a += cnt - 2
            } else if (flag == 'B' && cnt > 2) {
                b += cnt - 2
            }
            flag = c
            cnt = 1
        }
    }
    if (flag == 'A' && cnt > 2) {
        a += cnt - 2
    } else if (flag == 'B' && cnt > 2) {
        b += cnt - 2
    }
    return a > b
}

@Solution(
    id = 5888, title = "网络空闲的时刻", difficulty = Difficulty.MEDIUM, description = """
给你一个有 n 个服务器的计算机网络，服务器编号为 0 到 n - 1 。同时给你一个二维整数数组 edges ，
其中 edges[i] = [ui, vi] 表示服务器 ui 和 vi 之间有一条信息线路，
在 一秒 内它们之间可以传输 任意 数目的信息。再给你一个长度为 n 且下标从 0 开始的整数数组 patience 。

题目保证所有服务器都是 相通 的，也就是说一个信息从任意服务器出发，
都可以通过这些信息线路直接或间接地到达任何其他服务器。

编号为 0 的服务器是 主 服务器，其他服务器为 数据 服务器。每个数据服务器都要向主服务器发送信息，
并等待回复。信息在服务器之间按 最优 线路传输，也就是说每个信息都会以 最少时间 到达主服务器。
主服务器会处理 所有 新到达的信息并 立即 按照每条信息来时的路线 反方向 发送回复信息。

在 0 秒的开始，所有数据服务器都会发送各自需要处理的信息。从第 1 秒开始，每 一秒最 开始 时，
每个数据服务器都会检查它是否收到了主服务器的回复信息（包括新发出信息的回复信息）：

* 如果还没收到任何回复信息，那么该服务器会周期性 重发 信息。数据服务器 i 每 patience[i] 秒
  都会重发一条信息，也就是说，数据服务器 i 在上一次发送信息给主服务器后的 patience[i] 秒后
  会重发一条信息给主服务器。
* 否则，该数据服务器 不会重发 信息。

当没有任何信息在线路上传输或者到达某服务器时，该计算机网络变为 空闲 状态。

请返回计算机网络变为 空闲 状态的 最早秒数 。

示例 1：

输入：edges = [[0,1],[1,2]], patience = [0,2,1]
输出：8
解释：
0 秒最开始时，
- 数据服务器 1 给主服务器发出信息（用 1A 表示）。
- 数据服务器 2 给主服务器发出信息（用 2A 表示）。

1 秒时，
- 信息 1A 到达主服务器，主服务器立刻处理信息 1A 并发出 1A 的回复信息。
- 数据服务器 1 还没收到任何回复。距离上次发出信息过去了 1 秒（1 < patience[1] = 2），所以不会重发信息。
- 数据服务器 2 还没收到任何回复。距离上次发出信息过去了 1 秒（1 == patience[2] = 1），所以它重发一条信息（用 2B 表示）。

2 秒时，
- 回复信息 1A 到达服务器 1 ，服务器 1 不会再重发信息。
- 信息 2A 到达主服务器，主服务器立刻处理信息 2A 并发出 2A 的回复信息。
- 服务器 2 重发一条信息（用 2C 表示）。
...
4 秒时，
- 回复信息 2A 到达服务器 2 ，服务器 2 不会再重发信息。
...
7 秒时，回复信息 2D 到达服务器 2 。

从第 8 秒开始，不再有任何信息在服务器之间传输，也不再有信息到达服务器。
所以第 8 秒是网络变空闲的最早时刻。

示例 2：

输入：edges = [[0,1],[0,2],[1,2]], patience = [0,10,10]
输出：3
解释：数据服务器 1 和 2 第 2 秒初收到回复信息。
从第 3 秒开始，网络变空闲。
 

提示：
* n == patience.length
* 2 <= n <= 10^5
* patience[0] == 0
* 对于 1 <= i < n ，满足 1 <= patience[i] <= 10^5
* 1 <= edges.length <= min(10^5, n * (n - 1) / 2)
* edges[i].length == 2
* 0 <= ui, vi < n
* ui != vi
* 不会有重边。
每个服务器都直接或间接与别的服务器相连。
"""
)
fun networkBecomesIdle(edges: Array<IntArray>, patience: IntArray): Int {
    val n = patience.size
    val m: MutableMap<Int, MutableList<Int>> = mutableMapOf()
    for (edge in edges) {
        m.getOrPut(edge[0]) { mutableListOf() }.add(edge[1])
        m.getOrPut(edge[1]) { mutableListOf() }.add(edge[0])
    }
    val len = IntArray(n + 1) { 0 }
    val vis = IntArray(n + 1) { 0 }
    var layer: MutableList<Int> = mutableListOf(0)

    var l = 1
    vis[0] = 1
    while (layer.isNotEmpty()) {
        val nextLayer: MutableList<Int> = mutableListOf()
        for (i in layer) {
            val neighbors = m[i] ?: mutableListOf()
            for (p in neighbors) {
                if (vis[p] == 0) {
                    vis[p] = 1
                    len[p] = l
                    nextLayer.add(p)
                }
            }
        }
        l += 1
        layer = nextLayer
    }

    val empty = IntArray(n) { 0 }
    for (i in 1 until n) {
        val ttl = 2 * len[i]
        val p = patience[i]
        if (p >= ttl) {
            empty[i] = ttl
        } else {
            empty[i] = ttl + ttl / p * p
            if (ttl % patience[i] == 0) {
                empty[i] -= p
            }
        }
    }

    return empty.maxOrNull()!! + 1
}

@Solution(
    id = 5887, title = "两个有序数组的第 K 小乘积", difficulty = Difficulty.HARD, description = """
给你两个从小到大排好序且下标从 0 开始的整数数组 nums1 和 nums2 以及一个整数 k ，
请你返回第 k（从 1 开始编号）小的 nums1[i] * nums2[j] 的乘积，其中 
0 <= i < nums1.length 且 0 <= j < nums2.length 。
 
示例 1：
输入：nums1 = [2,5], nums2 = [3,4], k = 2
输出：8
解释：第 2 小的乘积计算如下：
- nums1[0] * nums2[0] = 2 * 3 = 6
- nums1[0] * nums2[1] = 2 * 4 = 8
第 2 小的乘积为 8 。

示例 2：
输入：nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
输出：0
解释：第 6 小的乘积计算如下：
- nums1[0] * nums2[1] = (-4) * 4 = -16
- nums1[0] * nums2[0] = (-4) * 2 = -8
- nums1[1] * nums2[1] = (-2) * 4 = -8
- nums1[1] * nums2[0] = (-2) * 2 = -4
- nums1[2] * nums2[0] = 0 * 2 = 0
- nums1[2] * nums2[1] = 0 * 4 = 0
第 6 小的乘积为 0 。

示例 3：
输入：nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
输出：-6
解释：第 3 小的乘积计算如下：
- nums1[0] * nums2[4] = (-2) * 5 = -10
- nums1[0] * nums2[3] = (-2) * 4 = -8
- nums1[4] * nums2[0] = 2 * (-3) = -6
第 3 小的乘积为 -6 。
 
提示：
* 1 <= nums1.length, nums2.length <= 5 * 10^4
* -10^5 <= nums1[i], nums2[j] <= 10^5
* 1 <= k <= nums1.length * nums2.length
* nums1 和 nums2 都是从小到大排好序的。
"""
)
fun kthSmallestProduct(nums1: IntArray, nums2: IntArray, k: Long): Long {

    return 0L
}


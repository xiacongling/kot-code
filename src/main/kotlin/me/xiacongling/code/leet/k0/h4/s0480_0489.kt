package me.xiacongling.code.leet.k0.h4

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 482, title = "密钥格式化", difficulty = Difficulty.EASY, description = """
有一个密钥字符串 S ，只包含字母，数字以及 '-'（破折号）。其中，N 个 '-' 将字符串分成了 N+1 组。

给你一个数字 K，请你重新格式化字符串，使每个分组恰好包含 K 个字符。特别地，
第一个分组包含的字符个数必须小于等于 K，但至少要包含 1 个字符。
两个分组之间需要用 '-'（破折号）隔开，并且将所有的小写字母转换为大写字母。

给定非空字符串 S 和数字 K，按照上面描述的规则进行格式化。

示例 1：

输入：S = "5F3Z-2e-9-w", K = 4
输出："5F3Z-2E9W"
解释：字符串 S 被分成了两个部分，每部分 4 个字符；注意，两个额外的破折号需要删掉。

示例 2：
输入：S = "2-5g-3-J", K = 2
输出："2-5G-3J"
解释：字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。

提示:
1. S 的长度可能很长，请按需分配大小。K 为正整数。
2. S 只包含字母数字（a-z，A-Z，0-9）以及破折号'-'
3. S 非空


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/license-key-formatting
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun licenseKeyFormatting(s: String, k: Int): String {
    val str = s.replace("-", "").uppercase()
    if (str.length <= k) return str

    val m = if (str.length % k > 0) str.length % k else k
    val buf = StringBuilder(str.take(m))
    var cnt = 0
    for (c in str.drop(m)) {
        if (cnt == 0) {
            buf.append('-')
        }
        if (c != '-') {
            buf.append(c)
            cnt += 1
        }
        if (cnt == k) {
            cnt = 0
        }
    }
    return buf.toString()
}

@Solution(
    id = 488, title = "祖玛游戏", difficulty = Difficulty.HARD, description = """
你正在参与祖玛游戏的一个变种。

在这个祖玛游戏变体中，桌面上有一排彩球，每个球的颜色可能是：红色 'R'、黄色 'Y'、蓝色 'B'、绿色 'G' 或白色 'W'。
你的手中也有一些彩球。

你的目标是清空桌面上所有的球。每一回合：

* 从你手上的彩球中选出任意一颗 ，然后将其插入桌面上那一排球中：两球之间或这一排球的任一端。
* 接着，如果有出现三个或者三个以上且颜色相同的球相连的话，就把它们移除掉。
  * 如果这种移除操作同样导致出现三个或者三个以上且颜色相同的球相连，则可以继续移除这些球，直到不再满足移除条件。
  * 如果桌面上所有球都被移除，则认为你赢得本场游戏。
* 重复这个过程，直到你赢了游戏或者手中没有更多的球。
* 给你一个字符串 board ，表示桌面上最开始的那排球。另给你一个字符串 hand，表示手里的彩球。
  请你按上述操作步骤移除掉桌上所有球，计算并返回所需的 最少 球数。如果不能移除桌上所有的球，返回 -1 。

示例 1：
输入：board = "WRRBBW", hand = "RB"
输出：-1
解释：无法移除桌面上的所有球。可以得到的最好局面是：
- 插入一个 'R' ，使桌面变为 WRRRBBW 。WRRRBBW -> WBBW
- 插入一个 'B' ，使桌面变为 WBBBW 。WBBBW -> WW
桌面上还剩着球，没有其他球可以插入。

示例 2：
输入：board = "WWRRBBWW", hand = "WRBRW"
输出：2
解释：要想清空桌面上的球，可以按下述步骤：
- 插入一个 'R' ，使桌面变为 WWRRRBBWW 。WWRRRBBWW -> WWBBWW
- 插入一个 'B' ，使桌面变为 WWBBBWW 。WWBBBWW -> WWWW -> empty
只需从手中出 2 个球就可以清空桌面。

示例 3：
输入：board = "G", hand = "GGGGG"
输出：2
解释：要想清空桌面上的球，可以按下述步骤：
- 插入一个 'G' ，使桌面变为 GG 。
- 插入一个 'G' ，使桌面变为 GGG 。GGG -> empty
只需从手中出 2 个球就可以清空桌面。

示例 4：
输入：board = "RBYYBBRRB", hand = "YRBGB"
输出：3
解释：要想清空桌面上的球，可以按下述步骤：
- 插入一个 'Y' ，使桌面变为 RBYYYBBRRB 。RBYYYBBRRB -> RBBBRRB -> RRRB -> B
- 插入一个 'B' ，使桌面变为 BB 。
- 插入一个 'B' ，使桌面变为 BBB 。BBB -> empty
只需从手中出 3 个球就可以清空桌面。

提示：
* 1 <= board.length <= 16
* 1 <= hand.length <= 5
* board 和 hand 由字符 'R'、'Y'、'B'、'G' 和 'W' 组成
* 桌面上一开始的球中，不会有三个及三个以上颜色相同且连着的球

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zuma-game
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findMinStep(board: String, hand: String): Int {
    return 0
}

fun main() {
    // 482
    println(licenseKeyFormatting("5F3Z-2e-9-w", 4))
    println(licenseKeyFormatting("2-5G-3J", 2))

    // 488
    print(findMinStep("WRRBBW", "RB"))
    print(findMinStep("WWRRBBWW", "WRBRW"))
    print(findMinStep("G", "GGGGG"))
    print(findMinStep("RBYYBBRRB", "YRBGB"))
}
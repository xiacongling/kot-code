package me.xiacongling.code.leet.k0.h0

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

var SAID = false
val SAY_ARR: Array<String> = Array(31) { "1" }


@Solution(
    id = 36, title = "有效的数独", difficulty = Difficulty.MEDIUM, description = """
请你判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

* 数字 1-9 在每一行只能出现一次。
* 数字 1-9 在每一列只能出现一次。
* 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例）
* 数独部分空格内已填入了数字，空白格用 '.' 表示。

注意：

* 一个有效的数独（部分已被填充）不一定是可解的。
* 只需要根据以上规则，验证已经填入的数字是否有效即可。

示例 1：
输入：board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：true

示例 2：
输入：board = 
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：false
解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。

提示：
* board.length == 9
* board[i].length == 9
* board[i][j] 是一位数字或者 '.'

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-sudoku
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun isValidSudoku(board: Array<CharArray>): Boolean {
    val rows: IntArray = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    val cols: IntArray = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    val grids: IntArray = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)

    for ((i, row) in board.withIndex()) {
        for ((j, c) in row.withIndex()) {
            if (c == '.') continue

            val m: Int = 1 shl (c - '9')
            val k: Int = i / 3 * 3 + j / 3

            if (rows[i] and m != 0)
                return false
            if (cols[j] and m != 0)
                return false
            if (grids[k] and m != 0)
                return false

            rows[i] = rows[i] or m
            cols[j] = cols[j] or m
            grids[k] = grids[k] or m
        }
    }
    return true
}

@Solution(
    id = 38, title = "外观数列", difficulty = Difficulty.MEDIUM, description = """
给定一个正整数 n ，输出外观数列的第 n 项。
「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。

你可以将其视作是由递归公式定义的数字字符串序列：

countAndSay(1) = "1"
countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。

前五项如下：
1.     1
2.     11
3.     21
4.     1211
5.     111221
第一项是数字 1 
描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
要 描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符 组成。然后对于每个组，先描述字符的数量，然后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来。

例如，数字字符串 "3322251" 的描述如下图：
            "3322251"
two 3's, three 2's, one 5, and one 1
  2 3   +    3 2   +  1 5 +      1 1
            "23321511"

示例 1：
输入：n = 1
输出："1"
解释：这是一个基本样例。

示例 2：
输入：n = 4
输出："1211"
解释：
countAndSay(1) = "1"
countAndSay(2) = 读 "1" = 一 个 1 = "11"
countAndSay(3) = 读 "11" = 二 个 1 = "21"
countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"

提示：
* 1 <= n <= 30

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-and-say
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。 
"""
)
fun countAndSay(n: Int): String {
    fun say(s: String): String {
        var cnt: Int = 0
        var pre: Char? = null
        val buf: StringBuilder = StringBuilder()
        for (c in s) {
            if (c != pre) {
                if (pre != null) buf.append(cnt).append(pre)
                cnt = 1
                pre = c
            } else {
                cnt += 1
            }
        }
        return buf.append(cnt).append(pre).toString()
    }
    if (!SAID) {
        for (i in 2..30) {
            SAY_ARR[i] = say(SAY_ARR[i - 1])
        }
        SAID = true
    }
    return SAY_ARR[n]
}

fun main() {
    println(countAndSay(1))
    println(countAndSay(4))
    println(countAndSay(30))
}
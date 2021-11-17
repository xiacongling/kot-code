package me.xiacongling.code.leet.k0.h4

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

private const val HEX_MASK: Int = 15
private const val HEX_CHARS: String = "0123456789abcdef"

@Solution(
    id = 405, title = "数字转换为十六进制", difficulty = Difficulty.EASY, description = """
给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用补码运算方法。

注意:
1. 十六进制中所有字母(a-f)都必须是小写。
2. 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；
   对于其他情况，十六进制字符串中的第一个字符将不会是0字符。
3. 给定的数确保在32位有符号整数范围内。
4. 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。

示例 1：
输入:26
输出:"1a"

示例 2：
输入:-1
输出:"ffffffff"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun toHex(num: Int): String {
    var result = ""
    var t = num
    while (t != 0) {
        result = HEX_CHARS[t and HEX_MASK] + result
        t = t ushr 4
    }
    return result.ifEmpty { "0" }
}

@Solution(
    id = 407, title = "接雨水 II", difficulty = Difficulty.HARD, description = """
给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。

示例 1:
输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
输出: 4
解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为 1+2+1=4。

示例2:
输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
输出: 10

提示:
* m == heightMap.length
* n == heightMap[i].length
* 1 <= m, n <= 200
* 0 <= heightMap[i][j] <= 2 * 10^4


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/trapping-rain-water-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun trapRainWater(heightMap: Array<IntArray>): Int {
    val m = heightMap.size
    val n = heightMap[0].size
    val dirs: IntArray = intArrayOf(-1, 0, 1, 0, -1)
    var maxHeight = 0

    for (i in 0 until m) {
        for (j in 0 until n) {
            maxHeight = kotlin.math.max(maxHeight, heightMap[i][j])
        }
    }
    val water: Array<IntArray> = Array(m) { IntArray(n) }
    for (i in 0 until m) {
        for (j in 0 until n) {
            water[i][j] = maxHeight
        }
    }
    val q: MutableList<IntArray> = mutableListOf()
    for (i in 0 until m) {
        for (j in 0 until n) {
            if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                if (water[i][j] > heightMap[i][j]) {
                    water[i][j] = heightMap[i][j]
                    q.add(intArrayOf(i, j))
                }
            }
        }
    }
    while (q.isNotEmpty()) {
        val curr = q.removeLast()
        val x = curr[0]
        val y = curr[1]
        for (i in 0 until 4) {
            val nx = x + dirs[i]
            val ny = y + dirs[i + 1]
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue
            if (water[x][y] < water[nx][ny] && water[nx][ny] > heightMap[nx][ny]) {
                water[nx][ny] = kotlin.math.max(water[x][y], heightMap[nx][ny])
                q.add(intArrayOf(nx, ny))
            }
        }
    }

    var res = 0
    for (i in 0 until m) {
        for (j in 0 until n) {
            res += water[i][j] - heightMap[i][j]
        }
    }
    return res
}

fun main() {
    println(toHex(16))
    println(toHex(26))
    println(toHex(0))
    println(toHex(-1))
    println(toHex(Int.MAX_VALUE))
    println(toHex(Int.MIN_VALUE))
}
package me.xiacongling.code.leet.k1.h0

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 1034, title = "边界着色", difficulty = Difficulty.MEDIUM, description = """
给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color。
网格中的每个值表示该位置处的网格块的颜色。

当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一连通分量。

连通分量的边界是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上
（第一行/列或最后一行/列）的所有网格块。

请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的连通分量的边界进行着色，并返回最终的网格 grid 。

示例 1：
输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
输出：[[3,3],[3,2]]

示例 2：
输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
输出：[[1,3,3],[2,3,3]]

示例 3：
输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
输出：[[2,2,2],[2,1,2],[2,2,2]]

提示：
* m == grid.length
* n == grid[i].length
* 1 <= m, n <= 50
* 1 <= grid[i][j], color <= 1000
* 0 <= row < m
* 0 <= col < n

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/coloring-a-border
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun colorBorder(grid: Array<IntArray>, row: Int, col: Int, color: Int): Array<IntArray> {
    val m = grid.size
    val n = grid[0].size
    val from = grid[row][col]
    if (from != color) {
        var layer = listOf(Pair(row, col))
        val visited = Array(m) { BooleanArray(n) }
        val border = mutableListOf<Pair<Int, Int>>()
        while (layer.isNotEmpty()) {
            layer.forEach { visited[it.first][it.second] = true }
            val adj = layer.map {
                it to listOf(
                    Pair(it.first - 1, it.second),
                    Pair(it.first + 1, it.second),
                    Pair(it.first, it.second - 1),
                    Pair(it.first, it.second + 1)
                ).filter { p ->
                    p.first in 0 until m && p.second in 0 until n && grid[p.first][p.second] == from
                }
            }
            border += adj.filter { it.second.size < 4 }.map { it.first }
            layer = adj.flatMap { it.second }.distinct().filterNot { visited[it.first][it.second] }
        }
        border.forEach { grid[it.first][it.second] = color }
    }
    return grid
}


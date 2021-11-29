package me.xiacongling.code.leet.k0.h5

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 517, title = "超级洗衣机", difficulty = Difficulty.HARD, description = """
假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。

在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。

给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的
最少的操作步数。如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。

示例 1：
输入：machines = [1,0,5]
输出：3
解释：
第一步:    1     0 <-- 5    =>    1     1     4
第二步:    1 <-- 1 <-- 4    =>    2     1     3    
第三步:    2     1 <-- 3    =>    2     2     2   

示例 2：
输入：machines = [0,3,0]
输出：2
解释：
第一步:    0 <-- 3     0    =>    1     2     0    
第二步:    1     2 --> 0    =>    1     1     1     

示例 3：
输入：machines = [0,2,0]
输出：-1
解释：
不可能让所有三个洗衣机同时剩下相同数量的衣物。

提示：
* n == machines.length
* 1 <= n <= 10^4
* 0 <= machines[i] <= 10^5


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/super-washing-machines
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findMinMoves(machines: IntArray): Int {
    val totalMachines = machines.size
    val totalClothes = machines.sum()
    if (totalClothes % totalMachines != 0) return -1

    val averageClothes = totalClothes / totalMachines

    var result = 0
    var totalMoveOut = 0
    for (numClothes in machines) {
        val toMoveOut = numClothes - averageClothes
        totalMoveOut += toMoveOut
        result = maxOf(result, kotlin.math.abs(totalMoveOut), toMoveOut)
    }
    return result
}

@Solution(
    id = 519, title = "阴机翻转矩阵", difficulty = Difficulty.MEDIUM, description = """
给你一个 m x n 的二元矩阵 matrix，且所有值被初始化为 0。请你设计一个算法，随机选取一个满足 
matrix[i][j] == 0 的下标 (i, j)，并将它的值变为 1 。所有满足 matrix[i][j] == 0 的下标
(i, j) 被选取的概率应当均等。

尽量最少调用内置的随机函数，并且优化时间和空间复杂度。

实现 Solution 类：
* Solution(int m, int n) 使用二元矩阵的大小 m 和 n 初始化该对象
* int[] flip() 返回一个满足 matrix[i][j] == 0 的随机下标 [i, j] ，并将其对应格子中的值变为 1
* void reset() 将矩阵中所有的值重置为 0

示例：
输入
["Solution", "flip", "flip", "flip", "reset", "flip"]
[[3, 1], [], [], [], [], []]
输出
[null, [1, 0], [2, 0], [0, 0], null, [2, 0]]

解释
Solution solution = new Solution(3, 1);
solution.flip();  // 返回 [1, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
solution.flip();  // 返回 [2, 0]，因为 [1,0] 已经返回过了，此时返回 [2,0] 和 [0,0] 的概率应当相同
solution.flip();  // 返回 [0, 0]，根据前面已经返回过的下标，此时只能返回 [0,0]
solution.reset(); // 所有值都重置为 0 ，并可以再次选择下标返回
solution.flip();  // 返回 [2, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同

提示：
* 1 <= m, n <= 10^4
* 每次调用flip 时，矩阵中至少存在一个值为 0 的格子。
* 最多调用 1000 次 flip 和 reset 方法。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/random-flip-matrix
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
class Solution(m: Int, n: Int) {
    private val map = hashMapOf<Int, Int>()
    private val cols = n
    private val total = m * n
    private var remain = total

    fun flip(): IntArray {
        remain -= 1
        val r = kotlin.random.Random.nextInt(remain + 1)
        val t = map.put(r, map.getOrDefault(remain, remain)) ?: r
        return intArrayOf(t / cols, t % cols)
    }

    fun reset() {
        remain = total
        map.clear()
    }
}

fun main() {
    println(findMinMoves(intArrayOf(1, 3, 3, 1)))
}

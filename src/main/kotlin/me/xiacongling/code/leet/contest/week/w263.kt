package me.xiacongling.code.leet.contest.week

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 0, title = "", difficulty = Difficulty.EASY, description = """

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
    id = 0, title = "", difficulty = Difficulty.EASY, description = """

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
    id = 0, title = "", difficulty = Difficulty.EASY, description = """

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
    id = 0, title = "", difficulty = Difficulty.EASY, description = """

"""
)
fun secondMinimum(n: Int, edges: Array<IntArray>, time: Int, change: Int): Int {
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
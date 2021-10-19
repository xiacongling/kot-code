package me.xiacongling.code.leet.jianzhi2

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 69, title = "剑指 Offer II 069. 山峰数组的顶部", difficulty = Difficulty.EASY,
    description = """
符合下列属性的数组 arr 称为 山峰数组（山脉数组）：

* arr.length >= 3
* 存在 i（0 < i < arr.length - 1）使得：
  - arr[0] < arr[1] < ... arr[i-1] < arr[i]
  - arr[i] > arr[i+1] > ... > arr[arr.length - 1]
给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/B1IidL
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun peakIndexInMountainArray(arr: IntArray): Int {
    var i = 1
    var j = arr.size - 2
    while (i <= j) {
        val m = (i + j) / 2
        val a = arr[m - 1]
        val b = arr[m]
        val c = arr[m + 1]
        if (b > a && b > c) return m
        if (a > b) j = m - 1 else i = m + 1
    }
    return arr[j]   // never reaches
}

fun main() {
    println(peakIndexInMountainArray(intArrayOf(0, 1, 0)))
    println(peakIndexInMountainArray(intArrayOf(1, 3, 5, 4, 2)))
    println(peakIndexInMountainArray(intArrayOf(0, 10, 5, 2)))
    println(peakIndexInMountainArray(intArrayOf(3, 4, 5, 1)))
    println(peakIndexInMountainArray(intArrayOf(24, 69, 100, 99, 79, 78, 67, 36, 26, 19)))
}
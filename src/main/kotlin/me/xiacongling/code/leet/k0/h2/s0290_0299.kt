package me.xiacongling.code.leet.k0.h2

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 292, title = "Nim 游戏", difficulty = Difficulty.EASY, description = """
你和你的朋友，两个人一起玩 [Nim 游戏](https://baike.baidu.com/item/Nim%E6%B8%B8%E6%88%8F/6737105)：

* 桌子上有一堆石头。
* 你们轮流进行自己的回合，你作为先手。
* 每一回合，轮到的人拿掉 1 - 3 块石头。
* 拿掉最后一块石头的人就是获胜者。

假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。
如果可以赢，返回 true；否则，返回 false 。

示例 1：
输入：n = 4
输出：false 
解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；
     因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。

示例 2：
输入：n = 1
输出：true

示例 3：
输入：n = 2
输出：true

提示：
* 1 <= n <= 2^31 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/nim-game
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun canWinNim(n: Int): Boolean {
    return n % 4 != 0
}

@Solution(
    id = 299, title = "猜数字游戏", difficulty = Difficulty.MEDIUM, description = """
你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：

写出一个秘密数字，并请朋友猜这个数字是多少。朋友每猜测一次，你就会给他一个包含下述信息的提示：

* 猜测数字中有多少位属于数字和确切位置都猜对了（称为 "Bulls", 公牛），
* 有多少位属于数字猜对了但是位置不对（称为 "Cows", 奶牛）。
  也就是说，这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字。

给你一个秘密数字 secret 和朋友猜测的数字 guess ，请你返回对朋友这次猜测的提示。

提示的格式为 "xAyB" ，x 是公牛个数， y 是奶牛个数，A 表示公牛，B 表示奶牛。

请注意秘密数字和朋友猜测的数字都可能含有重复数字。

示例 1:
输入: secret = "1807", guess = "7810"
输出: "1A3B"
解释: 数字和位置都对（公牛）用 '|' 连接，数字猜对位置不对（奶牛）的采用斜体加粗标识。
"1807"
  |
"7810"

示例 2:
输入: secret = "1123", guess = "0111"
输出: "1A1B"
解释: 数字和位置都对（公牛）用 '|' 连接，数字猜对位置不对（奶牛）的采用斜体加粗标识。
"1123"        "1123"
  |      or     |
"0111"        "0111"
注意，两个不匹配的 1 中，只有一个会算作奶牛（数字猜对位置不对）。通过重新排列非公牛数字，其中仅有一个 1 可以成为公牛数字。

示例 3：
输入：secret = "1", guess = "0"
输出："0A0B"

示例 4：
输入：secret = "1", guess = "1"
输出："1A0B"
 

提示：

* 1 <= secret.length, guess.length <= 1000
* secret.length == guess.length
* secret 和 guess 仅由数字组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/bulls-and-cows
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun getHint(secret: String, guess: String): String {
    val sCounter: MutableMap<Char, Int> = hashMapOf()
    val gCounter: MutableMap<Char, Int> = hashMapOf()
    var a = 0
    for (i in secret.indices) {
        if (secret[i] == guess[i]) {
            a += 1
        } else {
            sCounter.compute(secret[i]) { _, v -> (v ?: 0) + 1 }
            gCounter.compute(guess[i]) { _, v -> (v ?: 0) + 1 }
        }
    }
    val b = sCounter.map {
        kotlin.math.min(it.value, gCounter.getOrDefault(it.key, 0))
    }.sum()
    return "${a}A${b}B"
}

fun main() {
    println(canWinNim(4))
    println(canWinNim(1))
    println(canWinNim(2))

    println(getHint("1807", "7810"))
    println(getHint("1123", "0111"))
    println(getHint("1", "0"))
    println(getHint("1", "1"))
}
package me.xiacongling.code.leet.contest.week

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 5918, title = "统计字符串中的元音子字符串", difficulty = Difficulty.EASY, description = """
子字符串 是字符串中的一个连续（非空）的字符序列。

元音子字符串是仅由元音（'a'、'e'、'i'、'o' 和 'u'）组成的一个子字符串，且必须包含全部五种元音。

给你一个字符串 word ，统计并返回 word 中 元音子字符串的数目。

示例 1：
输入：word = "aeiouu"
输出：2
解释：下面列出 word 中的元音子字符串：
- "_aeiou_u"
- "_aeiouu_"

示例 2：
输入：word = "unicornarihan"
输出：0
解释：word 中不含 5 种元音，所以也不会存在元音子字符串。

示例 3：
输入：word = "cuaieuouac"
输出：7
解释：下面列出 word 中的元音子字符串（斜体加粗部分）：
- "c_uaieuo_uac"
- "c_uaieuou_ac"
- "c_uaieuoua_c"
- "cu_aieuo_uac"
- "cu_aieuou_ac"
- "cu_aieuoua_c"
- "cua_ieuoua_c"

示例 4：
输入：word = "bbaeixoubb"
输出：0
解释：所有包含全部五种元音的子字符串都含有辅音，所以不存在元音子字符串。
 
提示：
* 1 <= word.length <= 100
* word 仅由小写英文字母组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-vowel-substrings-of-a-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun countVowelSubstrings(word: String): Int {
    // todo
    return 0
}

@Solution(
    id = 5919, title = "所有子字符串中的元音", difficulty = Difficulty.MEDIUM, description = """
给你一个字符串 word ，返回 word 的所有子字符串中 元音的总数 ，元音是指 'a'、'e'、'i'、'o' 和 'u' 。

子字符串 是字符串中一个连续（非空）的字符序列。

注意：由于对 word 长度的限制比较宽松，答案可能超过有符号 32 位整数的范围。计算时需当心。

示例 1：
输入：word = "aba"
输出：6
解释：
所有子字符串是："a"、"ab"、"aba"、"b"、"ba" 和 "a" 。
- "b" 中有 0 个元音
- "a"、"ab"、"ba" 和 "a" 每个都有 1 个元音
- "aba" 中有 2 个元音
因此，元音总数 = 0 + 1 + 1 + 1 + 1 + 2 = 6 。

示例 2：
输入：word = "abc"
输出：3
解释：
所有子字符串是："a"、"ab"、"abc"、"b"、"bc" 和 "c" 。
- "a"、"ab" 和 "abc" 每个都有 1 个元音
- "b"、"bc" 和 "c" 每个都有 0 个元音
因此，元音总数 = 1 + 1 + 1 + 0 + 0 + 0 = 3 。

示例 3：
输入：word = "ltcd"
输出：0
解释："ltcd" 的子字符串均不含元音。

示例 4：
输入：word = "noosabasboosa"
输出：237
解释：所有子字符串中共有 237 个元音。
 
提示：
* 1 <= word.length <= 10^5
* word 由小写英文字母组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/vowels-of-all-substrings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun countVowels(word: String): Long {
    // todo
    return 0L
}

@Solution(
    id = 5920, title = "分配给商店的最多商品的最小值", difficulty = Difficulty.MEDIUM, description = """
给你一个整数 n ，表示有 n 间零售商店。总共有 m 种产品，每种产品的数目用一个下标从 0 开始的整数数组 quantities 表示，其中 quantities[i] 表示第 i 种商品的数目。

你需要将 所有商品 分配到零售商店，并遵守这些规则：

一间商店 至多 只能有 一种商品 ，但一间商店拥有的商品数目可以为 任意 件。
分配后，每间商店都会被分配一定数目的商品（可能为 0 件）。用 x 表示所有商店中分配商品数目的最大值，你希望 x 越小越好。
也就是说，你想 最小化 分配给任意商店商品数目的 最大值 。
请你返回最小的可能的 x 。

示例 1：
输入：n = 6, quantities = [11,6]
输出：3
解释： 一种最优方案为：
- 11 件种类为 0 的商品被分配到前 4 间商店，分配数目分别为：2，3，3，3 。
- 6 件种类为 1 的商品被分配到另外 2 间商店，分配数目分别为：3，3 。
分配给所有商店的最大商品数目为 max(2, 3, 3, 3, 3, 3) = 3 。

示例 2：
输入：n = 7, quantities = [15,10,10]
输出：5
解释：一种最优方案为：
- 15 件种类为 0 的商品被分配到前 3 间商店，分配数目为：5，5，5 。
- 10 件种类为 1 的商品被分配到接下来 2 间商店，数目为：5，5 。
- 10 件种类为 2 的商品被分配到最后 2 间商店，数目为：5，5 。
分配给所有商店的最大商品数目为 max(5, 5, 5, 5, 5, 5, 5) = 5 。

示例 3：
输入：n = 1, quantities = [100000]
输出：100000
解释：唯一一种最优方案为：
- 所有 100000 件商品 0 都分配到唯一的商店中。
分配给所有商店的最大商品数目为 max(100000) = 100000 。
 

提示：
* m == quantities.length
* 1 <= m <= n <= 10^5
* 1 <= quantities[i] <= 10^5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimized-maximum-of-products-distributed-to-any-store
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun minimizedMaximum(n: Int, quantities: IntArray): Int {
    // todo
    return 0
}

@Solution(
    id = 5921, title = "最大化一张图中的路径价值", difficulty = Difficulty.HARD, description = """
给你一张无向图，图中有 n 个节点，节点编号从 0 到 n - 1 （都包括）。同时给你一个下标从 0 开始的整数数组 values，
其中 values[i] 是第 i 个节点的价值。同时给你一个下标从 0 开始的二维整数数组 edges，其中 edges[j] = [uj, vj, timej] 
表示节点 uj 和 vj 之间有一条需要 timej 秒才能通过的无向边。最后，给你一个整数 maxTime 。

合法路径 指的是图中任意一条从节点 0 开始，最终回到节点 0 ，且花费的总时间 不超过 maxTime 秒的一条路径。
你可以访问一个节点任意次。一条合法路径的 价值 定义为路径中 不同节点 的价值 之和 （每个节点的价值 至多 算入价值总和中一次）。

请你返回一条合法路径的 最大 价值。

注意：每个节点 至多 有 四条 边与之相连。

示例 1：
      (32)  15  (10)
       1 ------- 2
   10 /
     /
(0) 0
     \
   10 \ 
       3 (43)

输入：values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
输出：75
解释：
一条可能的路径为：0 -> 1 -> 0 -> 3 -> 0 。总花费时间为 10 + 10 + 10 + 10 = 40 <= 49 。
访问过的节点为 0 ，1 和 3 ，最大路径价值为 0 + 32 + 43 = 75 。

示例 2：
      (10)  10  (15)
       1 ------- 2
   10 /
     /
(5) 0
     \
   10 \ 
       3 (20)

输入：values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
输出：25
解释：
一条可能的路径为：0 -> 3 -> 0 。总花费时间为 10 + 10 = 20 <= 30 。
访问过的节点为 0 和 3 ，最大路径价值为 5 + 20 = 25 。
示例 3：
         (2)
          1
        /   \
    10 /     \ 11
      /       \
 (1) 0 ------- 2 (3)
         13   /
             / 12
            /
          3
         (4)

输入：values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
输出：7
解释：
一条可能的路径为：0 -> 1 -> 3 -> 1 -> 0 。总花费时间为 10 + 13 + 13 + 10 = 46 <= 50 。
访问过的节点为 0 ，1 和 3 ，最大路径价值为 1 + 2 + 4 = 7 。

示例 4：
 (0)    (1)  10  (2)
  0      1 ------ 2

输入：values = [0,1,2], edges = [[1,2,10]], maxTime = 10
输出：0
解释：
唯一一条路径为 0 。总花费时间为 0 。
唯一访问过的节点为 0 ，最大路径价值为 0。 

提示：
* n == values.length
* 1 <= n <= 1000
* 0 <= values[i] <= 108
* 0 <= edges.length <= 2000
* edges[j].length == 3
* 0 <= uj < vj <= n - 1
* 10 <= timej, maxTime <= 100
* [uj, vj] 所有节点对 互不相同 。
* 每个节点 至多有四条 边。
* 图可能不连通。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-path-quality-of-a-graph
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun maximalPathQuality(values: IntArray, edges: Array<IntArray>, maxTime: Int): Int {
    // todo
    return 0
}
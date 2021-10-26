package me.xiacongling.code.leet.contest.week

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution
import java.util.regex.Pattern

@Solution(
    id = 5906, title = "句子中的有效单词数", difficulty = Difficulty.EASY, description = """
句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及
空格（' '）组成。每个句子可以根据空格分解成一个或者多个 token，这些 token 之间由一个或者多个空格 ' ' 分隔。

如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：
* 仅由小写字母、连字符和/或标点（不含数字）。
* 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 
  不是有效单词）。
* 至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。

这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。

给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。

示例 1：
输入：sentence = "cat and  dog"
输出：3
解释：句子中的有效单词是 "cat"、"and" 和 "dog"

示例 2：
输入：sentence = "!this  1-s b8d!"
输出：0
解释：句子中没有有效单词
"!this" 不是有效单词，因为它以一个标点开头
"1-s" 和 "b8d" 也不是有效单词，因为它们都包含数字

示例 3：
输入：sentence = "alice and  bob are playing stone-game10"
输出：5
解释：句子中的有效单词是 "alice"、"and"、"bob"、"are" 和 "playing"
"stone-game10" 不是有效单词，因为它含有数字
示例 4：

输入：sentence = "he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."
输出：6
解释：句子中的有效单词是 "he"、"bought"、"pencils,"、"erasers,"、"and" 和 "pencil-sharpener."

提示：
* 1 <= sentence.length <= 1000
* sentence 由小写英文字母、数字（0-9）、以及字符（' '、'-'、'!'、'.' 和 ','）组成
* 句子中至少有 1 个 token

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-valid-words-in-a-sentence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun countValidWords(sentence: String): Int {
    return sentence.split(Pattern.compile("\\s+"))
        .count { it.isNotEmpty() && it.matches(Regex("([a-z]+(-[a-z]+)?)?[!.,]?")) }
}

val BEAUTIFUL_NUMBERS = listOf(
    1, 22, 333, 4444, 55555, 666666,
    122, 212, 221,
    1333, 3133, 3313, 3331,
    14444, 41444, 44144, 44414, 44441,
    155555, 515555, 551555, 555155, 555515, 555551,
    22333, 23233, 23323, 23332, 32233, 32323, 32332, 33223, 33232, 33322,
    224444, 242444, 244244, 244424, 244442, 422444, 424244, 424424, 424442,
    442244, 442424, 442442, 444224, 444242, 444422,
    122333, 123233, 123323, 123332, 132233, 132323, 132332, 133223, 133232, 133322,
    212333, 213233, 213323, 213332, 312233, 312323, 312332, 313223, 313232, 313322,
    221333, 231233, 231323, 231332, 321233, 321323, 321332, 331223, 331232, 331322,
    223133, 232133, 233123, 233132, 322133, 323123, 323132, 332123, 332132, 333122,
    223313, 232313, 233213, 233312, 322313, 323213, 323312, 332213, 332312, 333212,
    223331, 232331, 233231, 233321, 322331, 323231, 323321, 332231, 332321, 333221,
    1224444
).sorted()

@Solution(
    id = 5907, title = "下一个更大的数值平衡数", difficulty = Difficulty.MEDIUM, description = """
如果整数 x 满足：对于每个数位 d，这个数位恰好在 x 中出现 d 次。那么整数 x 就是一个数值平衡数 。
给你一个整数 n ，请你返回 严格大于 n 的 最小数值平衡数 。

示例 1：
输入：n = 1
输出：22
解释：
22 是一个数值平衡数，因为：
- 数字 2 出现 2 次 
这也是严格大于 1 的最小数值平衡数。

示例 2：
输入：n = 1000
输出：1333
解释：
1333 是一个数值平衡数，因为：
- 数字 1 出现 1 次。
- 数字 3 出现 3 次。 
这也是严格大于 1000 的最小数值平衡数。
注意，1022 不能作为本输入的答案，因为数字 0 的出现次数超过了 0 。

示例 3：
输入：n = 3000
输出：3133
解释：
3133 是一个数值平衡数，因为：
- 数字 1 出现 1 次。
- 数字 3 出现 3 次。 
这也是严格大于 3000 的最小数值平衡数。

提示：
* 0 <= n <= 10^6

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/next-greater-numerically-balanced-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun nextBeautifulNumber(n: Int): Int {
    if (n == 0) return 1
    val idx = BEAUTIFUL_NUMBERS.binarySearch(n, 0, BEAUTIFUL_NUMBERS.size)
    if (idx >= 0) {
        return BEAUTIFUL_NUMBERS[idx + 1]
    }
    return BEAUTIFUL_NUMBERS[-idx - 1]
}

@Solution(
    id = 2049, title = "统计最高分的节点数目", difficulty = Difficulty.MEDIUM, description = """
给你一棵根节点为 0 的二叉树，它总共有 n 个节点，节点编号为 0 到 n - 1。同时给你一个下标从 0 开始的
整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 
parents[0] == -1。

一个子树的大小为这个子树内节点的数目。每个节点都有一个与之关联的分数。求出某个节点分数的方法是，
将这个节点和与它相连的边全部删除，剩余部分是若干个非空子树，这个节点的分数为所有这些子树大小的乘积。

请你返回有 最高得分节点的数目。

示例 1:
              Removed 0    Removed 1    Removed 2    Removed 3    Removed 4 
             +---------+  +---------+  +---------+  +---------+  +---------+
    0        |         |  |     0   |  |     0   |  |     0   |  |     0   |
   / \       |         |  |    / \  |  |      \  |  |    / \  |  |    /    |
  2   4      |   2   4 |  |   2   4 |  |       4 |  |   2   4 |  |   2     |
 / \         |  / \    |  |  /      |  |         |  |    \    |  |  / \    |
3   1        | 3   1   |  | 3       |  | 3   1   |  |     1   |  | 3   1   |
             +---------+  +---------+  +---------+  +---------+  +---------+
输入：parents = [-1,2,0,2,0]
输出：3
解释：
- 节点 0 的分数为：3 * 1 = 3
- 节点 1 的分数为：4 = 4
- 节点 2 的分数为：1 * 1 * 2 = 2
- 节点 3 的分数为：4 = 4
- 节点 4 的分数为：4 = 4
最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。

示例 2：
  0
   \
    2
   /
  1
输入：parents = [-1,2,0]
输出：2
解释：
- 节点 0 的分数为：2 = 2
- 节点 1 的分数为：2 = 2
- 节点 2 的分数为：1 * 1 = 1
最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。

提示：
* n == parents.length
* 2 <= n <= 10^5
* parents[0] == -1
* 对于 i != 0，有 0 <= parents[i] <= n - 1
* parents 表示一棵二叉树。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-nodes-with-the-highest-score
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun countHighestScoreNodes(parents: IntArray): Int {
    val sizes = IntArray(parents.size) { 1 }
    val scores = LongArray(parents.size) { 0L }  // score may > Int.MAX_VALUE
    var maxScore = 1L

    // create the tree
    val successors: MutableMap<Int, MutableList<Int>> = mutableMapOf()
    parents.withIndex().filter { it.value >= 0 }.forEach {
        successors.computeIfAbsent(it.value) { mutableListOf() }.add(it.index)
    }

    fun countSizes(node: Int): Int {
        val size = 1 + (successors[node]?.sumOf { countSizes(it) } ?: 0)
        sizes[node] = size
        return size
    }

    fun countScores(node: Int) {
        val base = kotlin.math.max(1, (sizes[0] - sizes[node]).toLong())
        val score = successors[node]?.map { sizes[it] }?.fold(base) { a, b -> a * b } ?: base
        scores[node] = score
        if (score > maxScore) {
            maxScore = score
        }
        successors[node]?.forEach { countScores(it) }
    }
    // count sizes for each sub-tree
    countSizes(0)
    // score = base [* left_child_size [* right_child_size]] where base is size[root] - size[node]
    countScores(0)
    return scores.count { it == maxScore }
}

@Solution(
    id = 5909, title = "并行课程III", difficulty = Difficulty.HARD, description = """
给你一个整数 n ，表示有 n 节课，课程编号从 1 到 n。同时给你一个二维整数数组 relations，其中
relations[j] = [prevCourse_j, nextCourse_j]，表示课程 prevCourse_j 必须在课程 nextCourse_j 之前
完成（先修课的关系）。同时给你一个下标从 0 开始的整数数组 time ，其中 time[i] 表示完成第 (i+1) 门课程
需要花费的月份数。

请你根据以下规则算出完成所有课程所需要的最少月份数：
* 如果一门课的所有先修课都已经完成，你可以在任意时间开始这门课程。
* 你可以同时上任意门课程 。

请你返回完成所有课程所需要的最少月份数。

注意：测试数据保证一定可以完成所有课程（也就是先修课的关系构成一个有向无环图）。

示例 1:

  ( 1 ) ------,
3 months       \       
                >--> ( 3 )
               /    5 months
  ( 2 ) ------'
2 months

输入：n = 3, relations = [[1,3],[2,3]], time = [3,2,5]
输出：8
解释：上图展示了输入数据所表示的先修关系图，以及完成每门课程需要花费的时间。
你可以在月份 0 同时开始课程 1 和 2 。
课程 1 花费 3 个月，课程 2 花费 2 个月。
所以，最早开始课程 3 的时间是月份 3 ，完成所有课程所需时间为 3 + 5 = 8 个月。

示例 2：

 ( 1 )--------------,
1 month              \
 ( 2 ) ---------------+--> ( 5 )
2 months             /    5 months
 ( 3 ) ---> ( 4 ) --'
3 months   4 months

输入：n = 5, relations = [[1,5],[2,5],[3,5],[3,4],[4,5]], time = [1,2,3,4,5]
输出：12
解释：上图展示了输入数据所表示的先修关系图，以及完成每门课程需要花费的时间。
你可以在月份 0 同时开始课程 1 ，2 和 3 。
在月份 1，2 和 3 分别完成这三门课程。
课程 4 需在课程 3 之后开始，也就是 3 个月后。课程 4 在 3 + 4 = 7 月完成。
课程 5 需在课程 1，2，3 和 4 之后开始，也就是在 max(1,2,3,7) = 7 月开始。
所以完成所有课程所需的最少时间为 7 + 5 = 12 个月。

提示：
* 1 <= n <= 5 * 104
* 0 <= relations.length <= min(n * (n - 1) / 2, 5 * 104)
* relations[j].length == 2
* 1 <= prevCourse_j, nextCourse_j <= n
* prevCourse_j != nextCourse_j
* 所有的先修课程对 [prevCourse_j, nextCourse_j] 都是 互不相同 的。
* time.length == n
* 1 <= time[i] <= 104
* 先修课程图是一个有向无环图。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/parallel-courses-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun minimumTime(n: Int, relations: Array<IntArray>, time: IntArray): Int {
    // TODO
    return 0
}
package me.xiacongling.code.leet.k0.h2

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution
import me.xiacongling.code.util.Trie
import java.util.*

@Solution(
    id = 211, title = "添加与搜索单词 - 数据结构设计", difficulty = Difficulty.MEDIUM, description = """
请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配。

实现词典类 WordDictionary ：
* WordDictionary() 初始化词典对象
* void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
* bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true；
  否则，返回 false 。word 中可能包含一些 '.' ，每个 '.' 都可以表示任何一个字母。

示例：

输入：
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
输出：
[null,null,null,null,false,true,true,true]

解释：
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True

提示：
* 1 <= word.length <= 500
* addWord 中的 word 由小写英文字母组成
* search 中的 word 由 '.' 或小写英文字母组成
* 最多调用 50000 次 addWord 和 search

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
class WordDictionary() {
    private val dict: Trie = Trie()

    fun addWord(word: String) {
        dict.write(word)
    }

    fun search(word: String): Boolean {
        var found = false
        fun search(node: Trie, idx: Int): Boolean {
            if (found) return found
            if (idx >= word.length) {
                found = node.isTerminated()
            } else {
                if (word[idx] == '.') {
                    for (next in node.children()) {
                        found = search(next, idx + 1)
                        if (found) break
                    }
                } else {
                    val next = node.match(word[idx])
                    found = next != null && search(next, idx + 1)
                }
            }
            return found
        }
        return search(dict, 0)
    }
}

@Solution(
    id = 212, title = "单词搜索 II", difficulty = Difficulty.HARD, description = """
给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
同一个单元格内的字母在一个单词中不允许被重复使用。

示例 1：
+-----+-----+-----+-----+
|  o  |  a  |  a  |  n  |
+-----+-----+-----+-----+
|  e  |  t  |  a  |  e  |
+-----+-----+-----+-----+
|  i  |  h  |  k  |  r  |
+-----+-----+-----+-----+
|  i  |  f  |  l  |  v  |
+-----+-----+-----+-----+
输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
输出：["eat","oath"]

示例 2：
+-----+-----+
|  a  |  b  |
+-----+-----+
|  c  |  d  |
+-----+-----+
输入：board = [["a","b"],["c","d"]], words = ["abcb"]
输出：[]

提示：
* m == board.length
* n == board[i].length
* 1 <= m, n <= 12
* board[i][j] 是一个小写英文字母
* 1 <= words.length <= 3 * 10^4
* 1 <= words[i].length <= 10
* words[i] 由小写英文字母组成
* words 中的所有字符串互不相同

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-search-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
    val root = Trie()
    val nRows = board.size
    val nCols = board[0].size
    val maxLength = nRows * nCols

    words.filter { it.length <= maxLength }.forEach { root.write(it) }

    val result: MutableList<String> = mutableListOf()
    val visited = BitSet(maxLength)
    fun travel(x: Int, y: Int, t: Trie) {
        val next = t.match(board[x][y]) ?: return
        if (next.isTerminated()) {
            result.add(next.destroy())
        }
        visited[nCols * x + y] = true
        if (x > 0 && !visited[nCols * (x - 1) + y]) travel(x - 1, y, next)
        if (y > 0 && !visited[nCols * x + y - 1]) travel(x, y - 1, next)
        if (x < nRows - 1 && !visited[nCols * (x + 1) + y]) travel(x + 1, y, next)
        if (y < nCols - 1 && !visited[nCols * x + y + 1]) travel(x, y + 1, next)
        visited[nCols * x + y] = false
    }

    for (i in 0 until nCols) {
        for (j in 0 until nRows) {
            travel(i, j, root)
        }
    }
    return result
}

fun main() {
    val wordDictionary: WordDictionary = WordDictionary();
    wordDictionary.addWord("bad")
    wordDictionary.addWord("dad")
    wordDictionary.addWord("mad")
    println(wordDictionary.search("pad")) // return False
    println(wordDictionary.search("bad")) // return True
    println(wordDictionary.search(".ad")) // return True
    println(wordDictionary.search("b..")) // return True
}

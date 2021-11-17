package me.xiacongling.code.leet.k0.h2

import me.xiacongling.code.leet.Difficulty
import me.xiacongling.code.leet.Solution

@Solution(
    id = 208, title = "", difficulty = Difficulty.MEDIUM, description = """
Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：
* Trie() 初始化前缀树对象。
* void insert(String word) 向前缀树中插入字符串 word 。
* boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即在检索之前已经插入）；
  否则，返回 false 。
* boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix，
  返回 true ；否则，返回 false 。


示例：
输入
inputs = ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
inputs = [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True


提示：
* 1 <= word.length, prefix.length <= 2000
* word 和 prefix 仅由小写英文字母组成
* insert、search 和 startsWith 调用次数 总计 不超过 3 * 10^4 次

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/QC3q1f
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
"""
)
class Trie() {
    private val subTries: MutableMap<Char, Trie> = mutableMapOf()
    private var fin = false


    /** Inserts a word into the trie. */
    fun insert(word: String) {
        insert(word, 0)
    }

    private fun insert(word: String, idx: Int) {
        if (idx >= word.length) {
            fin = true
        } else {
            subTries.compute(word[idx]) { _, v -> v ?: Trie() }!!.insert(word, idx + 1)
        }
    }

    /** Returns if the word is in the trie. */
    fun search(word: String): Boolean {
        return search(word, 0)
    }

    private fun search(word: String, idx: Int): Boolean {
        if (idx == word.length && fin) return true
        if (idx >= word.length) return false
        return subTries[word[idx]]?.search(word, idx + 1) ?: false
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    fun startsWith(prefix: String): Boolean {
        return startsWith(prefix, 0)
    }

    private fun startsWith(prefix: String, idx: Int): Boolean {
        if (idx == prefix.length) return true
        return subTries[prefix[idx]]?.startsWith(prefix, idx + 1) ?: false
    }
}

fun main() {
    val trie = Trie()
    trie.insert("apple")
    println(trie.search("apple"))
}
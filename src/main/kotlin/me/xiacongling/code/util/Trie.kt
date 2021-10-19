package me.xiacongling.code.util

class Trie(private val parent: Trie? = null, char: Char? = null) {
    private var fin: Boolean = false
    private val children: MutableMap<Char, Trie> = mutableMapOf()
    private val prefix: String

    init {
        prefix = (parent?.prefix ?: "") + (char ?: "")
    }

    fun write(word: String) = write(word, 0)

    private fun write(word: String, index: Int) {
        if (index >= word.length) {
            this.fin = true
        } else {
            val c = word[index]
            children.compute(c) { _, v -> v ?: Trie(this, c) }?.write(word, index + 1)
        }
    }

    fun children(): Collection<Trie> {
        return children.values
    }

    fun match(char: Char): Trie? {
        return children[char]
    }

    fun matches(str: String): Boolean {
        var p: Trie? = this
        for (c in str) {
            p = match(c) ?: return false
        }
        return p?.fin ?: false
    }

    fun isTerminated(): Boolean {
        return fin
    }

    fun destroy(): String {
        this.fin = false
        var p: Trie? = this
        while (p != null && p.children.isEmpty() && !p.fin) {
            p.parent?.children?.remove(p.prefix.last())
            p = p.parent
        }
        return prefix
    }
}
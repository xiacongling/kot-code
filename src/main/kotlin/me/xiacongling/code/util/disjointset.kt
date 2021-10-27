package me.xiacongling.code.util

/**
 * Disjoint Set / Union-Find Data Structure
 */
class DisjointSet(val n: Int) {
    private var size = n
    private var sets = IntArray(n + 1) { it }

    fun capacity() = n

    fun size() = size

    fun joint(x: Int, y: Int) = union(x, y)

    fun isJoint(x: Int, y: Int): Boolean = find(x) == find(y)

    fun union(x: Int, y: Int) {
        val px = find(x)
        val py = find(y)
        if (px != py) {
            sets[py] = px
            size -= 1
        }
    }

    fun find(x: Int): Int {
        // find root
        var t: Int = x
        var r: Int = sets[x]
        while (r != t) {
            t = r
            r = sets[r]
        }
        // path compression
        t = x
        while (t != r) {
            val pt = sets[t]
            sets[t] = r
            t = pt
        }
        return r
    }
}

typealias UnionFind = DisjointSet
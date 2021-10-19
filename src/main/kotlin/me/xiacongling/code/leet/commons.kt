package me.xiacongling.code.leet

enum class Difficulty {
    EASY, MEDIUM, HARD
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Solution(
    val id: Int,
    val title: String,
    val difficulty: Difficulty,
    val description: String = ""
)

class Node(var `val`: Int) {
    var prev: Node? = null
    var next: Node? = null
    var child: Node? = null

    override fun toString(): String = `val`.toString()
}

class ListNode(val `val`: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        var str: String = ""
        var p: ListNode? = this
        while (p != null) {
            str += p.`val`.toString() + " -> "
            p = p.next
        }
        str += "null"
        return str
    }

    companion object {
        fun of(vararg arr: Int): ListNode? {
            if (arr.isEmpty()) {
                return null
            }
            var head: ListNode? = null
            var tail: ListNode? = null
            for (item in arr) {
                val p = ListNode(item)
                if (head == null) {
                    head = p
                    tail = p
                } else {
                    tail!!.next = p
                    tail = p
                }
            }
            return head
        }
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

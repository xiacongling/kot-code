package me.xiacongling.code.leet

enum class Difficulty {
    EASY, MEDIUM, HARD
}

enum class FailReason(desc: String) {
    TLE("Time Limit Exceeds"),
    WA("Wrong Answer")
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Frustrating(
    val reason: FailReason,
)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Trick

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

class NTreeNode(var `val`: Int) {
    var children: List<NTreeNode?> = listOf()
}

class ListNode(var `val`: Int) {
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ListNode

        if (`val` != other.`val`) return false
        if (next != other.next) return false

        return true
    }

    override fun hashCode(): Int {
        var result = `val`
        result = 31 * result + (next?.hashCode() ?: 0)
        return result
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

    override fun toString(): String {
        return "[$`val`, left=${left?.`val`}, right=${right?.`val`}]"
    }
}

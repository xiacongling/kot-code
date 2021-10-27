package me.xiacongling.code.util

/**
 * 栈
 */
class Stack {
    private val arr: MutableList<Int> = ArrayList(32)

    fun peek(): Int? {
        return arr.lastOrNull()
    }

    fun pop(): Int? {
        return arr.removeLastOrNull()
    }

    fun push(x: Int) {
        arr.add(x)
    }
}

/**
 * 单调栈
 */
class MonoStack(asc: Boolean = true) {
    private val target = if (asc) 1 else -1
    private val stack: Stack = Stack()

    fun push(x: Int): Int? {
        while (stack.peek()?.compareTo(x) == target) {
            stack.pop()
        }
        val top = stack.peek()
        stack.push(x)
        return top
    }
}
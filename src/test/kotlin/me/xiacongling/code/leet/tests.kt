package me.xiacongling.code.leet

import com.github.benmanes.caffeine.cache.Caffeine
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.reflections.Reflections
import org.reflections.scanners.Scanners
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import org.reflections.util.FilterBuilder
import java.io.InputStream
import java.lang.reflect.Method
import java.lang.reflect.Type
import java.nio.file.FileSystems
import kotlin.reflect.jvm.kotlinFunction

private val fsep = FileSystems.getDefault().separator
private val root = "me/xiacongling/code/leet"

@ExperimentalSerializationApi
private val cache = Caffeine.newBuilder().maximumSize(100).build<String, List<TestData>> {
    val resource: InputStream? = TestData::class.java.classLoader.getResourceAsStream(it)
    if (resource != null) {
        Json.decodeFromStream<List<TestData>>(resource)
    } else {
        listOf()
    }
}

private fun allSolutions(): List<Pair<Solution, Method>> {
    val refs = Reflections(
        ConfigurationBuilder()
            .setScanners(Scanners.SubTypes.filterResultsBy { true }, Scanners.Resources)
            .setUrls(
                ClasspathHelper.forClassLoader(
                    ClasspathHelper.contextClassLoader(),
                    ClasspathHelper.staticClassLoader()
                )
            )
            .filterInputsBy(FilterBuilder().includePackage("me.xiacongling.code.leet"))
    )
    return refs.getSubTypesOf(Any::class.java)
        .flatMap { it.methods.toList() }
        .filter { it.isAnnotationPresent(Solution::class.java) }
        .map { Pair(it.getAnnotation(Solution::class.java), it) }
}

private fun findSolution(id: Int): Pair<Solution, Method>? {
    return allSolutions().find { it.first.id == id }
}

@Serializable
data class TestData(val id: Int, val io: List<TestIO>)

@Serializable
data class TestIO(val input: String, val output: String)

class TestGroup(private val method: Method, private val data: TestData) {
    fun judge() {
        data.io
            .map { TestCase(method, it) }
            .forEach { it.judge() }
    }
}

data class TestCase(val method: Method, val io: TestIO) {
    fun judge() {
        val parser = Parser(io.input)
        val params = method.parameters.map { parser.parseParam(it.parameterizedType) }.toTypedArray()

        val result = method.kotlinFunction?.call(*params)
        if (result is IntArray) {
            println(result.toList())
        }
    }
}

@ExperimentalSerializationApi
private fun Pair<Solution, Method>.testGroup(): TestGroup? {
    val id = first.id
    val dir = listOf(root, "k${id / 1000}", "h${id % 1000 / 100}").joinToString(fsep)
    val name = "s%03d0_%03d9".format(id / 10, id / 10)
    val key = listOf(dir, name + ".json").joinToString(fsep)

    val testData: TestData? = cache.get(key)?.find { it.id == this.first.id }
    return testData?.with(this.second)
}

private fun TestData.with(method: Method): TestGroup {
    return TestGroup(method, this)
}


class Parser(private val value: String) {
    private var p = 0

    fun parseParam(type: Type): Any {
        return when (type) {
            Int::class.java -> parseInt()
            Long::class.java -> parseLong()
            String::class -> parseString()
            IntArray::class.java -> parseIntArray()
            else ->
                throw UnsupportedOperationException("parameter type is not supported: $type")
        }
    }

    private fun parseInt(): Int {
        return parseNumberString().toInt()
    }

    private fun parseLong(): Long {
        return parseNumberString().toLong()
    }

    private fun parseDouble(): Double {
        return parseNumberString().toDouble()
    }

    private fun parseFloat(): Float {
        return parseNumberString().toFloat()
    }

    private fun parseNumberString(): String {
        passKey()
        while (!value[p].isDigit() && value[p] != '-' && value[p] != '+') p += 1
        val start = p
        var dot = false
        while (p < value.length && value[p].isDigit() || value[p] == '.') {
            if (value[p] == '.') {
                if (!dot) dot = true else throw IllegalArgumentException("not a valid number")
            }
            p += 1
        }
        return value.substring(start, p)
    }

    private fun parseString(): String {
        passKey()
        while (p < value.length && value[p] != '"') p += 1
        p += 1
        val start = p + 1
        while (p < value.length) {
            p += if (value[p] == '\\') 2 else if (value[p] == '"') break else 1
        }
        val result = value.substring(start until p)
        p += 1
        return result
    }

    private fun parseIntArray(): IntArray {
        return parseNumberStringArray().map { it.toInt() }.toIntArray()
    }

    private fun parseLongArray(): LongArray {
        return parseNumberStringArray().map { it.toLong() }.toLongArray()
    }

    private fun parseNumberStringArray(): Array<String> {
        passKey()
        while (value[p] != '[') p += 1
        val start = p + 1
        while (p < value.length) {
            p += if (value[p] == '\\') 2 else if (value[p] == ']') break else 1
        }
        val result = value.substring(start until p)
            .split(",")
            .toTypedArray()
        while (value[p] != ']') p += 1
        p += 1
        return result
    }

    private fun passKey() {
        while (p < value.length && value[p] != '=') p += 1
        p += 1
    }
}

@ExperimentalSerializationApi
fun main() {
    findSolution(1)?.testGroup()?.judge()
}
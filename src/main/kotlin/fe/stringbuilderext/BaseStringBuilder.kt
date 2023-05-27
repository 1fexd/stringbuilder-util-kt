package fe.stringbuilderext

interface BaseStringBuilder<T, B> {
    fun applyBuilder(stringBuilder: StringBuilder, block: B)

    fun build(stringBuilder: StringBuilder, block: B): StringBuilder {
        applyBuilder(stringBuilder, block)
        return stringBuilder
    }
}

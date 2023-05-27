package fe.stringbuilderext

class SeparatedStringBuilder(
    private val separator: String,
    private val items: MutableList<StringBuilder.() -> Unit> = mutableListOf()
) : BaseStringBuilder<SeparatedStringBuilder, (SeparatedStringBuilder.() -> Unit)?> {
    fun item(block: StringBuilder.() -> Unit) = items.add(block)

    fun <T> itemNotNull(predicate: T?, block: StringBuilder.() -> Unit) {
        if (predicate != null) item(block)
    }

    fun items(blocks: Array<out StringBuilder.() -> Unit>) = items.addAll(blocks)

    override fun applyBuilder(
        stringBuilder: StringBuilder,
        block: (SeparatedStringBuilder.() -> Unit)?
    ) {
        if (block != null) {
            block()
        }

        for ((index, element) in items.withIndex()) {
            val last = index + 1 == items.size

            stringBuilder.apply(element)
            if (!last) stringBuilder.append(separator)
        }
    }
}

fun buildSeparatedString(
    separator: String,
    stringBuilder: SeparatedStringBuilder.() -> Unit
) = StringBuilder().separated(separator, stringBuilder).toString()

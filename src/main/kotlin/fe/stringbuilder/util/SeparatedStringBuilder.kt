package fe.stringbuilder.util


class SeparatedStringBuilder<T : Appendable>(
    private val separator: String,
    private val items: MutableList<T.() -> Unit> = mutableListOf()
) : BaseAppendable<T, BuilderScope<SeparatedStringBuilder<T>>?> {
    @Deprecated("Use item(prefix, block) instead", replaceWith = ReplaceWith("item(prefix, block)"))
    fun item(block: T.() -> Unit) = items.add(block)

    @Suppress("MemberVisibilityCanBePrivate")
    fun item(prefix: String? = null, block: T.() -> Unit): SeparatedStringBuilder<T> {
        items.add { append(prefix) }
        items.add(block)

        return this
    }

    @Deprecated(
        "Use itemNotNull(predicate, prefix, block) instead",
        replaceWith = ReplaceWith("itemNotNull(predicate, prefix, block)")
    )
    fun <P> itemNotNull(predicate: P?, block: T.() -> Unit) {
        if (predicate != null) item(block)
    }

    fun <P> itemNotNull(predicate: P?, prefix: String? = null, block: T.() -> Unit): SeparatedStringBuilder<T> {
        if (predicate != null) {
            item(prefix, block)
        }

        return this
    }


    fun items(blocks: Array<out Appendable.() -> Unit>) = items.addAll(blocks)

    override fun apply(appendable: T, block: (SeparatedStringBuilder<T>.() -> Unit)?) {
        if (block != null) {
            block()
        }

        for ((index, element) in items.withIndex()) {
            val last = index + 1 == items.size

            appendable.apply(element)
            if (!last) appendable.append(separator)
        }
    }
}

fun buildSeparatedString(
    separator: String,
    builder: BuilderScope<SeparatedStringBuilder<StringBuilder>>
): String {
    return StringBuilder().separated(separator, builder).toString()
}

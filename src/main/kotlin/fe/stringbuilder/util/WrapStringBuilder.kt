package fe.stringbuilder.util

class WrapStringBuilder<T : Appendable>(
    private val start: CharSequence,
    private val end: CharSequence,
) : BaseAppendable<T, BuilderScope<T>> {

    override fun apply(appendable: T, block: BuilderScope<T>) {
        appendable.append(start)
        appendable.apply(block)
        appendable.append(end)
    }
}

//interface NewBaseStringBuilder<B> {
//    fun applyBuilder(stringBuilder: Appendable, block: B)
//
//    fun build(stringBuilder: Appendable, block: B): Appendable {
//        applyBuilder(stringBuilder, block)
//        return stringBuilder
//    }
//}


fun buildWrappedString(wrapWith: String, builder: BuilderScope<StringBuilder>): String {
    return buildWrappedString(wrapWith, wrapWith, builder)
}

fun buildWrappedString(wrapStart: String, wrapEnd: String, builder: BuilderScope<StringBuilder>): String {
    return StringBuilder().wrapped(wrapStart, wrapEnd, builder).toString()
}

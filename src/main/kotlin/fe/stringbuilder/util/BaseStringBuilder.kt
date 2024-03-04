package fe.stringbuilder.util


interface BaseAppendable<T : Appendable, B> {
    fun apply(appendable: T, block: B)

    fun build(appendable: T, block: B): T {
        apply(appendable, block)
        return appendable
    }
}

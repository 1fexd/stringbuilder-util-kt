package fe.stringbuilder.util


fun StringBuilder.extend(fn: StringBuilder.() -> Unit): StringBuilder {
    fn(this)
    return this
}

fun StringBuilder.separated(
    separator: String,
    builder: SeparatedStringBuilder.() -> Unit
) = SeparatedStringBuilder(separator).build(this, builder)

fun StringBuilder.slashSeparated(
    builder: SeparatedStringBuilder.() -> Unit
) = separated("/", builder)

fun StringBuilder.commaSeparated(
    builder: SeparatedStringBuilder.() -> Unit
) = separated(",", builder)

fun StringBuilder.separated(
    separator: String,
    vararg items: StringBuilder.() -> Unit
) = SeparatedStringBuilder(separator, items = items.toMutableList()).build(this, null)

fun StringBuilder.slashSeparated(
    vararg items: StringBuilder.() -> Unit
) = separated("/", *items)

fun StringBuilder.commaSeparated(
    vararg items: StringBuilder.() -> Unit
) = separated(",", *items)

fun StringBuilder.wrapped(
    wrapWith: String,
    builder: StringBuilder.() -> Unit
) = wrapped(wrapWith, wrapWith, builder)


fun StringBuilder.wrapped(
    wrapStart: String,
    wrapEnd: String,
    builder: StringBuilder.() -> Unit
) = WrapStringBuilder(wrapStart, wrapEnd).build(this, builder)

fun StringBuilder.roundWrapped(
    builder: StringBuilder.() -> Unit
) = wrapped("(", ")", builder)

fun StringBuilder.squareWrapped(
    builder: StringBuilder.() -> Unit
) = wrapped("[", "]", builder)

fun StringBuilder.curlyWrapped(
    builder: StringBuilder.() -> Unit
) = wrapped("{", "}", builder)

package fe.stringbuilder.util


fun <T : Appendable> T.extend(builder: BuilderScope<T>): T {
    builder(this)
    return this
}


fun <T : Appendable> T.separated(separator: String, builder: BuilderScope<SeparatedStringBuilder<T>>): T {
    return SeparatedStringBuilder<T>(separator).build(this, builder)
}

enum class Separator(val separator: String) {
    Slash("/"), Comma(",")
}

fun <T : Appendable> T.separated(separator: Separator, builder: BuilderScope<SeparatedStringBuilder<T>>): T {
    return separated(separator.separator, builder)
}

fun <T : Appendable> T.separated(separator: Separator, vararg items: BuilderScope<T>): T {
    return separated(separator.separator, *items)
}

@Deprecated(
    message = "Deprecated in favor of new API",
    replaceWith = ReplaceWith("separated(Separator.Slash, builder)")
)
fun <T : Appendable> T.slashSeparated(builder: BuilderScope<SeparatedStringBuilder<T>>): T {
    return separated("/", builder)
}

@Deprecated(
    message = "Deprecated in favor of new API",
    replaceWith = ReplaceWith("separated(Separator.Comma, builder)")
)
fun <T : Appendable> T.commaSeparated(builder: BuilderScope<SeparatedStringBuilder<T>>): T {
    return separated(",", builder)
}

fun <T : Appendable> T.separated(separator: String, vararg items: BuilderScope<T>): T {
    return SeparatedStringBuilder(separator, items = items.toMutableList()).build(this, null)
}

@Deprecated(
    message = "Deprecated in favor of new API",
    replaceWith = ReplaceWith("separated(Separator.Slash, items)")
)
fun <T : Appendable> T.slashSeparated(vararg items: BuilderScope<T>): T {
    return separated("/", *items)
}

@Deprecated(
    message = "Deprecated in favor of new API",
    replaceWith = ReplaceWith("separated(Separator.Comma, items)")
)
fun <T : Appendable> T.commaSeparated(vararg items: BuilderScope<T>): T {
    return separated(",", *items)
}

enum class Bracket(val open: String, val close: String) {
    Round("(", ")"),
    Square("[", "]"),
    Curly("{", "}")
}

fun <T : Appendable> T.wrapped(bracket: Bracket, builder: BuilderScope<T>): T {
    return wrapped(bracket.open, bracket.close, builder)
}

fun <T : Appendable> T.wrapped(wrapStart: CharSequence, wrapEnd: CharSequence, builder: BuilderScope<T>): T {
    return WrapStringBuilder<T>(wrapStart, wrapEnd).build(this, builder)
}

@Deprecated(
    message = "Deprecated in favor of new API",
    replaceWith = ReplaceWith("wrapped(WrapWith.Single(wrapWith), builder)")
)
fun <T : Appendable> T.wrapped(wrapWith: String, builder: BuilderScope<T>): T {
    return wrapped(wrapWith, wrapWith, builder)
}


@Deprecated(
    message = "Deprecated in favor of new API",
    replaceWith = ReplaceWith("wrapped(Bracket.Round, builder)")
)
fun <T : Appendable> T.roundWrapped(builder: BuilderScope<T>): T {
    return wrapped("(", ")", builder)
}

@Deprecated(
    message = "Deprecated in favor of new API",
    replaceWith = ReplaceWith("wrapped(Bracket.Square, builder)")
)
fun <T : Appendable> T.squareWrapped(builder: BuilderScope<T>): T {
    return wrapped("[", "]", builder)
}

@Deprecated(
    message = "Deprecated in favor of new API",
    replaceWith = ReplaceWith("wrapped(Bracket.Curly, builder)")
)
fun <T : Appendable> T.curlyWrapped(builder: BuilderScope<T>): T {
    return wrapped("{", "}", builder)
}

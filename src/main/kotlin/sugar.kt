operator fun StringBuilder.plus(s: Any): StringBuilder {
    this.append(s)
    return this
}

operator fun StringBuilder.invoke(): String {
    return toString()
}


val Number.d get() = this.toDouble()
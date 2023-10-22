operator fun StringBuilder.plus(s: String): StringBuilder {
    this.append(s)
    return this
}


val Number.d get() = this.toDouble()
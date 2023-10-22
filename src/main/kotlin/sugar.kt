operator fun StringBuilder.plus(s: String): StringBuilder {
    this.append(s)
    return this
}


val Int.d get() = this.toDouble()
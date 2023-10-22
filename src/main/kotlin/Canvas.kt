data class Canvas(val width: Int, val height: Int)

infix fun Int.by(height: Int): Canvas = Canvas(this, height)


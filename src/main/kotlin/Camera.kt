class Camera {
    private val lowerLeft = Point(-2, -1, -1)
    private val origin = Point(0, 0, 0)
    private val horizontal = Vec3(4, 0, 0)
    private val vertical = Vec3(0, 2, 0)

    fun getRay(u: Double, v: Double): Ray =
        Ray(origin, lowerLeft + u * horizontal + v * vertical - origin)

}
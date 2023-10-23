fun interface Hittable {
    fun hit(ray:Ray, tMin:Double, tMax:Double):HitRecord?
}

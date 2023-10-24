import kotlin.math.sqrt

data class Sphere(val center: Point, val radius: Double, val material: Material) : Hittable {
    constructor(center: Point, radius: Number, material: Material) : this(center, radius.d, material)

    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        // TODO lar16: functionalise this!
        val oc = ray.origin - center
        val a = ray.direction.lengthSquared
        val halfB = oc dot ray.direction
        val c = oc.lengthSquared - radius * radius
        val discriminant = (halfB * halfB) - (a * c)

        if (discriminant < 0) return null
        val sqrtD = sqrt(discriminant)

        var root = (-halfB - sqrtD) / a
        if (root !in tMin..tMax) root = (-halfB + sqrtD) / a
        if (root !in tMin..tMax) return null

        val hitPoint = ray[root]
        val normal = (hitPoint - center) / radius
        val normalFaceOut = ray.direction dot normal < 0
        return HitRecord(hitPoint, normal, normalFaceOut, material, root)
    }
}

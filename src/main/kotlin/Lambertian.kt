class Lambertian(private val albedo: Colour) : Material {
    override fun scatter(ray: Ray, hitRecord: HitRecord): Pair<Ray, Colour> {
        val scatterDirection = hitRecord.outwardNormal + Vector.randomUnitVector()
        // ensure we're not scattering to 0 vector...
        val direction = if (scatterDirection.nearZero()) hitRecord.outwardNormal else scatterDirection
        val scattered = Ray(hitRecord.hitPoint, direction)

        return scattered to albedo
    }
}

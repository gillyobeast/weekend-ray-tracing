class Metal(private val albedo: Colour, fuzz: Number = 0.0) : Material {
    private val fuzz = fuzz.d.clamp(0.0, 1.0)


    override fun scatter(ray: Ray, hitRecord: HitRecord): Pair<Ray, Colour>? {

        val reflected = reflect(ray.direction.normalised(), hitRecord.outwardNormal)
        return if (reflected dot hitRecord.outwardNormal <= 0) null
        else {
            val direction = reflected + fuzz * Vector.randomInUnitSphere()
            Ray(hitRecord.hitPoint, direction) to albedo
        }

    }

}

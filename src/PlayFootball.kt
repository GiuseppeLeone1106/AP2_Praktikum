class PlayFootball : Activity(
    energyImpact = -15,
    happinessImpact = 25,
    description = "Fußball spielen"
) {
    override fun execute(pet: Pet) {
        val hasBall = pet.inventory.any { it.name == "Ball" }
        if (hasBall) {
            super.execute(pet)
            val ball = Item(
                name = "Ball",
                category = ItemCategory.TOY,
                amount = 1,
                energyImpact = 10,
                happinessImpact = 5
            )
            pet.inventory.add(ball)
        }
    }
}
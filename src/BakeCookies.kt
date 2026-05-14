class BakeCookies : Activity(
    energyImpact = -5,
    happinessImpact = 15,
    description = "Kekse backen"
) {
    override fun execute(pet: Pet) {
        super.execute(pet)
        val cookie = Item(
            name = "Keks",
            category = ItemCategory.FOOD,
            amount = 1,
            energyImpact = 10,
            happinessImpact = 5
        )
        pet.inventory.add(cookie)
    }
}
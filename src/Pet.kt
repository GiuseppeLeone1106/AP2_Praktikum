import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.Stage
import de.th_koeln.basicstage.coordinatesystem.WorldConstants
import de.th_koeln.imageprovider.Assets
import kotlin.random.Random
import Health

class Pet {
    var Actor = Actor(Assets.KODEE)

    var Name = "Kodee"

    var health = Health()

    var happiness = 50

    var amount = 3

    var hungry: Boolean = false
        get() =     health.energy <= 20

    val inventory: MutableList<Item> = mutableListOf()

    fun addItem(item: Item) {
        if(item.category == ItemCategory.FOOD) {
            feed(item)
        }else {
            inventory.add(item)
            applyItemEffects(item)
        }
        inventory.add(item)
        happiness += item.happinessImpact ?:0
        println("Added ${item.name} — happiness is now $happiness")
    }

    fun removeItem(item: Item) {
        if (inventory.remove(item)) {
            happiness -= item.happinessImpact ?:0
            println("Removed ${item.name} — happiness is now $happiness")
        } else {
            println("Item ${item.name} not found in inventory")
        }
    }

    fun countItems(): Int {
        return inventory.size
    }

    fun feed(item:Item){

    }
    private fun applyItemEffects(item: Item) {
        health.energy += item.energyImpact
        happiness += item.happinessImpact
    }
    fun doActivity(activity: Activity) {
        activity.execute(this)
    }

}

import java.io.File

private const val TAVERN_MASTER = "Tavernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's Folly"

private val lastNames = setOf(
    "Lupin", "Proton", "Vitla",
    "MacGregor", "Butovski"
)
private val firstNames = setOf("Eli", "Mordik", "Sophie")

const val welcomeHead = "*** Welcome to $TAVERN_NAME ***"

private val menuData = File("data/tavern-menu-data.txt")
    .readText()
    .split("\n")
private val menuItems: List<String> = menuData.map { menuEntry: String ->
    val (_, name, _) = menuEntry.split(",")
    name
}

private val menuItemsTypes: Map<String, String> = List(menuData.size) { index ->
    val (type, name, _) = menuData[index].split(",")
    name to type
}.toMap()

private val menuItemPrices: Map<String, Double> = List(menuData.size) { index ->
    val (_, name, price) = menuData[index].split(",")
    name to price.toDouble()
}.toMap()

fun visitTavern() {
    narrate("$heroName enters $TAVERN_NAME")
    narrate("There are several items for sale:")
    println(welcomeHead)
    val patrons: MutableSet<String> = mutableSetOf()
    val patronGold = mutableMapOf(
        TAVERN_MASTER to 86.00,
        heroName to 4.50
    )
    while (patrons.size < 5) {
        val patronName = "${firstNames.random()} ${lastNames.random()}"
        patrons += patronName
        patronGold += patronName to 20.0
    }

    displayPatronBalances(patronGold)

    narrate("$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())

    repeat(3) {
        placeOrder(patrons.random(), menuItems.random(), patronGold)
        displayPatronBalances(patronGold)
    }
}

private fun placeOrder(
    patronName: String,
    menuItemName: String,
    patronGold: MutableMap<String, Double>
) {
    val itemPrice = menuItemPrices.getValue(menuItemName)
    narrate("$patronName speaks with $TAVERN_MASTER to place an order")
    if (itemPrice <= patronGold.getOrDefault(patronName, 0.0)) {
            val action = when (menuItemsTypes[menuItemName]) {
            "shandy", "elixir" -> "pours"
            "meal" -> "serves"
            else -> "hands"
        }
        narrate("$TAVERN_MASTER $action $patronName a $menuItemName")
        narrate("$patronName pays $TAVERN_MASTER $itemPrice gold")
        patronGold[patronName] = patronGold.getValue(patronName) - itemPrice
        patronGold[TAVERN_MASTER] = patronGold.getValue(TAVERN_MASTER) + itemPrice
    } else {
        narrate("$TAVERN_MASTER says, \"You need more coin\"")
    }

}

private fun displayPatronBalances(patronGold: Map<String, Double>) {
    narrate("$heroName intuitively knows how much money each patron has")
    patronGold.forEach{ (patron, balance) ->
        narrate("$patron has ${"%.2f".format(balance)} gold")
    }
}
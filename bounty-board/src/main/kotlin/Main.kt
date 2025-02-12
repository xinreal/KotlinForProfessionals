const val HERO_NAME = "Ksenia"
var playerLevel = 5
fun main() {
    println("the Hero $HERO_NAME announces her presence into the world.")
    var totalExperience = 555
    var playerLevel: Int = totalExperience / 100 + 1
    println("Current player level: $playerLevel")

    val race = "elf"
    val playerTitle: String = when (playerLevel) {
        1 -> "Apprentice."
        in 2..8 -> "Level $playerLevel Warrior."
        9 -> "Vanquisher of Nogartse"
        else -> "Distinguished Knight."
    }
    val faction: String = when (race) {
        "dwarf" -> "Keepers on the Mines."
        "gnome" -> "Tinkerers of the Underground"
        "orc", "human" -> "Free people of the Rolling Hills."
        else -> "Shadow Cabal of the Unseen Realm."
    }

    readBountyBoard()
    println("Time passes...\nThe hero returns from the quest.")
    playerLevel++
    println("Current player level: $playerLevel")
}

private fun obtainQuest(
    playerLevel: Int,
    hasAngeredBarbarians: Boolean = true,
    hasBefriendedBarbarians: Boolean = false,
    playerClass: String = "paladin"
) :String = when (playerLevel) {
    1 -> "Meet Mr.Bubbles at the land of soft things."
    in 2..5 -> {
        val canTalkToBarbarians = !hasAngeredBarbarians && (hasBefriendedBarbarians || playerClass == "barbarian")
        if (canTalkToBarbarians) "Convince the barbarians to call off their invasion."
        else "Save the town from barbarian invasions."
    }

    6 -> "Locate the enchanted Sward."
    7 -> "Recover the long-lost artifact of creation."
    8 -> "Defeat Nogartse, bringer of death and eater of worlds."
    else -> "There are no quests right now."
}

private fun readBountyBoard() {
    println("The hero approaches the bounty board. It reads:")
    println(obtainQuest(playerLevel))

}
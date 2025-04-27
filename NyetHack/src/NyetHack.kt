var heroName = ""
fun main() {
    heroName = promptHeroName()
    //changeNarratorMood()
    narrate("$heroName, ${createTitle(heroName)}, heads to the square")
    visitTavern()
}

private fun createTitle(name: String): String {
    return when {
        name.all { it.isDigit() } -> "The Identifiable"
        name.none { it.isLetter() } -> "The Witness Protection Member"
        name.count {it.lowercase() in "aeiou"} > 4 -> "The Master of Vowel"
        name.all { it.isUpperCase()  } -> "Outstanding"
        name.length > 12 -> "Spacious"
        name.lowercase() == name.reversed().lowercase() -> "Palindrome"
        else -> "The Renowned Hero"
    }
}

private fun promptHeroName(): String {
    narrate("The hero enters Kronstadt. What is their name?") { message ->
        //yellow message
        "\u001b[33;1m$message\u001b[0m"
    }
    /*val heroName = readlnOrNull()
    require(!heroName.isNullOrEmpty()) {
        "The hero must have a name!"
    }
    return input
     */
    println("Ksenia")
    return "Ksenia"
}
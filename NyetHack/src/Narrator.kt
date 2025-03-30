import kotlin.random.Random
import kotlin.random.nextInt

var narratorModifier: (String) -> String = { it }
inline fun narrate(
    message: String,
    modifier: (String) -> String = { narratorModifier(it) }
) {
    println(modifier(message))
}

fun changeNarratorMood() {
    val mood: String
    val modifier: (String) -> String
    when (Random.nextInt(1..8)) {
        1 -> {
            mood = "loud"
            modifier = { message ->
                val numExplanationPoints = 3
                message.uppercase() + "!".repeat(numExplanationPoints)
            }
        }

        2 -> {
            mood = "tired"
            modifier = { message ->
                message.lowercase().replace(" ", "...")
            }
        }

        3 -> {
            mood = "unsure"
            modifier = { message ->
                "$message?"
            }
        }

        4 -> {
            var narratorsGiven = 0
            mood = " like sending itemized bill"
            modifier = { message ->
                narratorsGiven++
                "$message. \n(I have narrated $narratorsGiven things)"
            }
        }

        5 -> {
            mood = "lazy"
            modifier = { message ->
                "${message.take(12)}...Ohhhh"
            }
        }

        6 -> {
            mood = "mystery"
            modifier = { message ->
                message.map {
                    when (it) {
                        'L' -> '1'
                        else -> it
                    }
                }.joinToString("")
            }
        }

        else -> {
            mood = "professional"
            modifier = { message ->
                "$message."
            }
        }
    }
    narratorModifier = modifier
    narrate("The narrator begins to feel $mood")
}
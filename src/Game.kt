fun main(args: Array<String>) {


    val player = Player()
    player.castFireball()

    val auraColor = player.auraColor()
    printPlayerStatus(player)



}

private fun printPlayerStatus(
    player: Player
) {
    println(
        "(Aura: ${player.auraColor()}" +
                "(Blessed: ${if (player.isBlessed) "YES" else "No"})"
    )
    //Player status
    println("${player.name} ${player.formatHealthStatus()}")
}





package com.bignerdranch.nyethack

fun main(args: Array<String>) {


    val player = Player("Madrigal",89,true,false)
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
    //com.bignerdranch.nyethack.Player status
    println("${player.name} ${player.formatHealthStatus()}")
}





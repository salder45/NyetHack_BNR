package com.bignerdranch.nyethack

fun main(args: Array<String>) {

    Game.play()

}



object Game{

    private val player = Player("Madrigal")
    private var currentRoom = TownSquare()


    init {
        println("Welcome adventurer.")
        player.castFireball()
    }

    fun play(){
        while (true){
            println(currentRoom.description())
            println(currentRoom.load())

            //Player status
            printPlayerStatus(player)

            //control section
            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
        }
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

    private class GameInput(arg: String?){
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1,{ "" })

        fun processCommand() = when(command.toLowerCase()){
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }

}





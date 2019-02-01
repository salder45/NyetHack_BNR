package com.bignerdranch.nyethack

import java.lang.IllegalStateException

fun main(args: Array<String>) {

    Game.play()

}



object Game{

    private val player = Player("Madrigal")
    private var currentRoom: Room = TownSquare()

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room"))
        )

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
            val inputResponse = GameInput(readLine()).processCommand()
            println(inputResponse)
            if(inputResponse == "Goodbye!"){
                break
            }
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
            "quit" -> "Goodbye!"
            "move" -> move(argument)
            "map" -> printMap()
            "ring" -> ringTownSquareBell()
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }

    private fun move(directionInput: String) =
            try{
                val direction = Direction.valueOf(directionInput.toUpperCase())
                val newPosition = direction.updateCoordinate(player.currentPosition)
                if(!newPosition.isInBounds){
                    throw IllegalStateException("$direction is out of bounds")
                }

                val newRoom = worldMap[newPosition.y][newPosition.x]
                player.currentPosition = newPosition
                currentRoom = newRoom
                "Ok, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
            }catch (e: Exception){
                "Invalid direction: $directionInput"
            }

    private fun printMap(): String {
        var map = ""
        worldMap.forEachIndexed { indexY, list ->
            list.forEachIndexed { indexX, room ->
                if (indexY == player.currentPosition.y && indexX == player.currentPosition.x){
                    map += " X"
                }else{
                    map += " 0"
                }
            }
            map +="\n"
        }
        return map
    }

    private fun ringTownSquareBell(): String{
        return if(currentRoom is TownSquare)
            (currentRoom as TownSquare).ringBell()
        else
            "You can't ring the bells from here."

    }



}





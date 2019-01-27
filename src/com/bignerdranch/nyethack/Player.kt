package com.bignerdranch.nyethack

class Player(_name: String, var healthPoints: Int = 100, val isBlessed: Boolean, private val isImmortal: Boolean){
    var name = _name
    get() = field.capitalize()
    private set(value){
        field = value.trim()
    }

    init{
        require(healthPoints > 0 , { "healthPoints must be greater than zero." })
        require(name.isNotBlank(), { "Player must have a name." })
    }

    constructor(name: String):this(name, isBlessed = true, isImmortal = false){
        if(name.toLowerCase() == "kar") healthPoints = 40
    }

    fun castFireball(numFireballs: Int = 2) =
        println("A glass of Fireball springs into existence. (x$numFireballs)")

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal

        //Aura
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }

    fun formatHealthStatus(): String =
        when (healthPoints) {
            100 -> "is in excellent condition!"
            in 90..99 -> "has a few scratches."
            in 75..89 -> if (isBlessed) {
                "has some minor wounds but is healing quite quickly!"
            } else {
                "has some minor wounds."
            }
            in 15..74 -> "looks pretty hurt."
            else -> "is in awful condition!"
        }
}
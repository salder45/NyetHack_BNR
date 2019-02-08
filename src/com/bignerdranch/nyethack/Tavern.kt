package com.bignerdranch.nyethack

import java.io.File
import com.bignerdranch.nyethack.extensions.random


const val TAVERN_NAME = "Taernly's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot","Fernsworth","Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt").readText().split("\n")
val patronGold = mutableMapOf<String, Double>()


fun main(args: Array<String>) {
    if(patronList.contains("Eli")){
        println("The tavern master says: Eli's in the back playing cards.")
    }else{
        println("The tavern master says: Eli isn't here")
    }
    //
    if(patronList.containsAll(listOf("Mordoc","Sophie"))){
        println("The tavern master says: Yea they're seated by the stew kettle.")
    }else{
        println("The tavern master says: Nay, they departed hours ago.")
    }

    (0..9).forEach{
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"
        uniquePatrons += name
    }

    uniquePatrons.forEach{
        patronGold[it] = 6.0
    }
    //
    var orderCount = 0
    while(orderCount <= 9 ){
        placeOrder(
            uniquePatrons.random(),
            menuList.random()
        )
        orderCount++
    }

    displayPatronBalances()
    //
    print("Welcome, Madrigal".frame(5))
}

fun perfomrPurchase(price: Double, patronName: String){
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}

private fun String.toDragonsSpeak()=
    this.replace(Regex("[aeiou]")){
        when(it.value){
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }

private fun String.frame( padding: Int, formatChar: String = "*") : String{
    val greeting = "$this"
    val middle = formatChar.padEnd(padding)
        .plus(greeting)
        .plus(formatChar.padStart(padding))
    val end = (0 until middle.length).joinToString("") { formatChar }
    return "$end\n$middle\n$end"
}

private fun placeOrder(patronName: String,menuData: String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type,name,price) = menuData.split(',')

    val message = "$patronName buys a $name ($type) for $price"
    println(message)

    perfomrPurchase(price.toDouble(), patronName)

   val phrase = if (name == "Dragon's Breath"){
       "$patronName exclaims ${"Ah, delicious $name!".toDragonsSpeak()}"
   }else{
       "$patronName says: Thank you for the $name."
   }

    println(phrase)
}

private fun displayPatronBalances(){
    patronGold.forEach{ patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}
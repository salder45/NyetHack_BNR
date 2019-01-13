import kotlin.math.roundToInt
const val TAVERN_NAME = "Taernly's Folly"
const val CASK_DRAGONS_BREATH = 1
const val GALLONS_PER_CASK = 5
const val PINT = 0.125

var playerGold = 10
var playerSilver = 10

fun main(args: Array<String>) {
    placeOrder("shandy,Dragon's Breath,5.91")
}

fun perfomrPurchase(price: Double){
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
    println("Total purse: $totalPurse")
    println("Purchasing item for $price")

    val remainingBalance = totalPurse - price
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")

    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).toInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()

    //GALLONS AFTER 12 DRAGONS BREATH
    val totalPints: Double = GALLONS_PER_CASK / PINT
    println("Total Pints: $totalPints")
    val remainingPints = totalPints - 12
    println("After selling 12 dragon's breath the remaining pints are: $remainingPints")
    println("Remainin gallons right now are ${remainingPints * 0.125}")
}

private fun displayBalance(){
    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
}

private fun toDragonsSpeak(prhase: String)=
    prhase.replace(Regex("[aeiou]")){
        when(it.value){
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }

private fun placeOrder(menuData: String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

    val (type,name,price) = menuData.split(',')

    val message = "Madrigals buys a $name ($type) for $price"
    println(message)

    perfomrPurchase(price.toDouble())

   val phrase = if (name == "Dragon's Breath"){
       "Madrigal exclaims ${toDragonsSpeak("Ah, delicious $name!")}"
   }else{
       "Madrigal says: Thank you for the $name."
   }

    println(phrase)
}
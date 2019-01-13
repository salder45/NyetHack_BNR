import kotlin.math.roundToInt
const val TAVERN_NAME = "Taernly's Folly"

var dragonCoin = 5.0

fun main(args: Array<String>) {
    placeOrder("shandy,Dragon's Breath,5.91")
}

fun perfomrPurchase(price: Double){
    displayBalance()
    val totalPurse = dragonCoin * 1.43
    println("Total purse: $totalPurse")
    println("Purchasing item for $price")

    val remainingBalance = totalPurse - price
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")


    dragonCoin = remainingBalance / 1.43
    displayBalance()
}

private fun displayBalance(){
    println("Player's purse balance: Dragoncoin: ${"%.4f".format(dragonCoin)}")
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
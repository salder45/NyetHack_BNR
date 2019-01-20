import kotlin.math.roundToInt
import java.io.File

const val TAVERN_NAME = "Taernly's Folly"

var playerGold = 10
var playerSilver = 10
val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot","Fernsworth","Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt").readText().split("\n")
val menuStandarLenght = 18

fun main(args: Array<String>) {
    //
    printMenu(menuList)
    //
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
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons+= name
    }

    println(uniquePatrons)
    //
    var orderCount = 0;
    while(orderCount <= 9 ){
        placeOrder(uniquePatrons.shuffled().first(), menuList.shuffled().first())
        orderCount++
    }
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

private fun placeOrder(patronName: String,menuData: String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type,name,price) = menuData.split(',')

    val message = "$patronName buys a $name ($type) for $price"
    println(message)

    //perfomrPurchase(price.toDouble())

   val phrase = if (name == "Dragon's Breath"){
       "$patronName exclaims ${toDragonsSpeak("Ah, delicious $name!")}"
   }else{
       "$patronName says: Thank you for the $name."
   }

    println(phrase)
}

private fun printMenu(menuList :List<String>){
    println("**** Welcome to ${TAVERN_NAME} ****")
    println()
    var nameMaxLenght = 0
    var priceIntMaxLenght = 0
    var priceIntCentsMaxLenght = 0
    var typesNames = mutableSetOf<String>()
    menuList.forEach { menuItem ->
        val (type,name,price) = menuItem.split(',')
        nameMaxLenght = if(nameMaxLenght < name.length) name.length else nameMaxLenght
        val (numbers,cents) = price.split('.')
        priceIntMaxLenght = if(priceIntMaxLenght < numbers.length) numbers.length else priceIntMaxLenght
        priceIntCentsMaxLenght = if(priceIntCentsMaxLenght < cents.length) cents.length else priceIntCentsMaxLenght
        typesNames.add("~[${type}]~")
    }
    //
    var menuListWithFormat = mutableListOf<String>()
    //
    menuList.forEach { menuItem ->
        val (type,name,price) = menuItem.split(',')
        val (numbers,cents) = price.split('.')
        //
        val zeroes = priceIntCentsMaxLenght - cents.length
        val dotsBeforePrice = priceIntMaxLenght - numbers.length
        val dotsAfterName = (nameMaxLenght - name.length) + (menuStandarLenght - (priceIntMaxLenght + priceIntCentsMaxLenght + 1)) +dotsBeforePrice
        //
        menuListWithFormat.add("~[${type}]~,${name.capitalize()}${".".repeat(dotsAfterName)}${numbers}.${cents}${"0".repeat(zeroes)}")
    }
    //
    typesNames.forEach {typeStr ->
        val spaces = ((nameMaxLenght + menuStandarLenght) - typeStr.length) / 2
        println("${" ".repeat(spaces)}$typeStr")
        menuListWithFormat.forEach{menuItem ->
            val (type,item ) = menuItem.split(',')
            if(type.equals(typeStr)) {
                println(item)
            }
        }
    }
    println()
}
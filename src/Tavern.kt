const val TAVERN_NAME = "Taernly's Folly"

fun main(args: Array<String>) {
    placeOrder("shandy,Dragon's Breath,5.91")
    //placeOrder("elixir, Shirley's Temple,4.12")
}

private fun toDragonsSpeak(prhase: String)=
    prhase.replace(Regex("[aeiouAEIOU]")){
        when(it.value){
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            "A" -> "4"
            "E" -> "3"
            "I" -> "1"
            "O" -> "0"
            "U" -> "|_|"
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

   val phrase = if (name == "Dragon's Breath"){
       "Madrigal exclaims ${toDragonsSpeak("DRAGON'S BREATH: IT'S GOT WHAT ADVENTURERS CRAVE!")}"
   }else{
       "Madrigal says: Thank you for the $name."
   }

    println(phrase)
}
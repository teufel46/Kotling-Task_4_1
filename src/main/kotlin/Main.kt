const val TYPE_1 = "Maestro"
const val TYPE_2 = "MasterCard"
const val MAXAMOUNT_TYPE_1 = 75000_00
const val FIXCOMMISSION_TYPE_1 = 20_00
const val COMMISSIONPERCENTAGE_TYPE_1 = 0.06
const val TYPE_3 = "VISA"
const val TYPE_4 = "Мир"
const val FIXCOMMISSION_TYPE_3 = 35_00
const val COMMISSIONPERCENTAGE_TYPE_3 = 0.0075
const val TYPE_5 = "VK Pay"
const val COMMISSIONPERCENTAGE_TYPE_5 = 0

fun main() {
    val amountTransfer = 50_00
    val amountOfPreviousPurchases = 100_000_00
    val type = TYPE_1

    println("=============================================================================================");
    println("Сумма перевода: " + convertToRubKop(amountTransfer));
    println("Тип карты: $type");
    println("Сумма предыдущих переводов в этом месяце: " + convertToRubKop(amountOfPreviousPurchases));
    println("Комиссия: " + convertToRubKop(calcCommission(type, amountOfPreviousPurchases, amountTransfer)));
    println("=============================================================================================");
}

fun convertToRubKop(amount : Int) : String{
    return (amount / 100).toInt().toString() + " руб " + amount % 100 + " коп"
}
fun calcCommission(type : String = TYPE_5,
                   amountOfPreviousPurchases : Int = 0,
                   amountTransfer : Int) : Int{
    when(type){
        TYPE_1, TYPE_2 -> {
            if ((amountOfPreviousPurchases + amountTransfer) in (1..MAXAMOUNT_TYPE_1)) return 0
            else return ( FIXCOMMISSION_TYPE_1 + COMMISSIONPERCENTAGE_TYPE_1 * amountTransfer).toInt()
        }
        TYPE_3, TYPE_4 -> {
            if (COMMISSIONPERCENTAGE_TYPE_3 * amountTransfer > FIXCOMMISSION_TYPE_3)
                 return (COMMISSIONPERCENTAGE_TYPE_3 * amountTransfer).toInt()
            else return  FIXCOMMISSION_TYPE_3
        }
        TYPE_5 -> {
            return (COMMISSIONPERCENTAGE_TYPE_5 * amountTransfer).toInt()
        }
    }
    return 0
}

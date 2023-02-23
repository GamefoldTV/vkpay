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
    testCalcPrint(type = TYPE_1, amountTransfer = 100_00)
    testCalcPrint(TYPE_1, 400_00, 100_00)
    testCalcPrint(TYPE_1, 100_000_00, 50_00)
    testCalcPrint(TYPE_2, 0, 100_00)
    testCalcPrint(TYPE_2, 400_00, 100_00)
    testCalcPrint(TYPE_2, 100_000_00, 50_00)
    testCalcPrint(TYPE_3, 0, 100_00)
    testCalcPrint(TYPE_3, 400_00, 1000_00)
    testCalcPrint(TYPE_3, 100_000_00, 5000_00)
    testCalcPrint(TYPE_4, 0, 100_00)
    testCalcPrint(TYPE_4, 400_00, 1000_00)
    testCalcPrint(TYPE_4, 100_000_00, 5000_00)
    testCalcPrint(TYPE_5, 100_000_00, 5000_00)
    testCalcPrint(amountOfPreviousPurchases = 100_000_00, amountTransfer = 15000_00)
    testCalcPrint(amountTransfer = 25000_00)
}

fun testCalcPrint(type : String = TYPE_5,
                  amountOfPreviousPurchases : Int = 0,
                  amountTransfer : Int){
    println("*************");
    println("Сумма перевода: " + convertToRubKop(amountTransfer));
    println("Тип карты: $type");
    println("Сумма предыдущих переводов в этом месяце: " + convertToRubKop(amountOfPreviousPurchases));
    println("Комиссия получилась: " + convertToRubKop(calcCommission(type, amountOfPreviousPurchases, amountTransfer)));
}

fun convertToRubKop(amount : Int) : String{
    return (amount / 100).toInt().toString() + " руб " + amount % 100 + " коп"
}
fun calcCommission(type : String,
                   amountOfPreviousPurchases : Int,
                   amountTransfer : Int) : Int{
    when(type){
        TYPE_1, TYPE_2 -> {
            if ((amountOfPreviousPurchases + amountTransfer) in (1..MAXAMOUNT_TYPE_1)) return 0
            else return ( FIXCOMMISSION_TYPE_1 + COMMISSIONPERCENTAGE_TYPE_1 * amountTransfer).toInt()
        }
        TYPE_3, TYPE_4 -> {
            if (COMMISSIONPERCENTAGE_TYPE_3 * amountTransfer > FIXCOMMISSION_TYPE_1)
                return (COMMISSIONPERCENTAGE_TYPE_3 * amountTransfer).toInt()
            else return  FIXCOMMISSION_TYPE_3
        }
        TYPE_5 -> {
            return (COMMISSIONPERCENTAGE_TYPE_5 * amountTransfer).toInt()
        }
    }
    return 0
}
fun main() {
    // Константы для лимитов по картам
    val dailyLimit = 150000
    val monthlyLimit = 600000
    // Константы для лимитов по VK Pay
    val vkPayLimit = 15000
    val vkPayMonthlyLimit = 40000

    // Функция расчета комиссии для переводов с карт Mastercard и Maestro
    fun calculateMastercardMaestroCommission(amount: Int, monthlyTransferSum: Int): Double {
        return if (monthlyTransferSum <= monthlyLimit && amount <= dailyLimit) {
            0.0
        } else {
            0.006 * amount + 20
        }
    }

    // Функция расчета комиссии для переводов с карт Visa и Mir
    fun calculateVisaMirCommission(amount: Int): Double {
        val commission = amount * 0.0075
        return if (commission < 35) {
            35.0
        } else {
            commission
        }
    }

    // Функция проверки лимитов для переводов на VK Pay
    fun checkVkPayLimits(amount: Int, monthlyTransferSum: Int): Boolean {
        return amount <= vkPayLimit && monthlyTransferSum <= vkPayMonthlyLimit
    }

    // Пример использования функций
    val amount = 17800
    val cardType = "VK Pay"
    val monthlyTransferSum = 0

    val commission = when (cardType) {
        "Mastercard", "Maestro" -> calculateMastercardMaestroCommission(amount, monthlyTransferSum)
        "Visa", "Mir" -> calculateVisaMirCommission(amount)
        "VK Pay" -> 0.0
        else -> error("Неизвестный тип карты")
    }

    val total = amount + commission

    if (cardType == "VK Pay" && checkVkPayLimits(amount, vkPayMonthlyLimit)) {
        println("Перевод на VK Pay прошел успешно: $amount рублей.")
    } else if (total <= dailyLimit && monthlyTransferSum <= monthlyLimit) {
        println("Перевод с карты $cardType на сумму $amount рублей прошел успешно.")
    } else {
        println("Превышен лимит: сумма перевода в день не должна превышать $dailyLimit рублей, а сумма перевода в месяц не должна превышать $monthlyLimit рублей.")
    }

}

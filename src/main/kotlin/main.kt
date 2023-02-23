fun main() {
    calculatePayment(transferAmount = 10000) // OK
    calculatePayment(previousTransfers = 30000, transferAmount = 1000) // OK
    calculatePayment(transferAmount = 20000) // Ошибка: максимальная сумма перевода VK Pay - 15000 рублей
    calculatePayment(previousTransfers = 39000, transferAmount = 2000) // Ошибка: максимальная сумма переводов VK Pay в месяц - 40000 рублей
}

fun calculatePayment(type: String = "VK Pay", previousTransfers: Int = 0, transferAmount: Int) {
    val maxTransferAmount = 15000 // максимальная сумма перевода за раз
    val maxMonthlyTransferAmount = 40000 // максимальная сумма переводов в месяц
    val remainingMonthlyTransferAmount = maxMonthlyTransferAmount - previousTransfers // сколько еще можно перевести в этом месяце

    if (type == "VK Pay") {
        if (transferAmount > maxTransferAmount) {
            println("Ошибка, нельзя столько переводить за 1 раз -  максимальная сумма перевода VK Pay - $maxTransferAmount рублей")
            return
        }

        if (previousTransfers + transferAmount > maxMonthlyTransferAmount) {
            println("Ошибка, вы исчерпали лимит в месяц переводов - максимальная сумма переводов VK Pay в месяц - $maxMonthlyTransferAmount рублей")
            return
        }
        println("***********")
        println("Тип карты: $type")
        println("Сумма предыдущих переводов в этом месяце: $previousTransfers рублей")
        println("Сумма совершенного перевода: $transferAmount рублей")
        println("Осталось доступно для перевода в этом месяце: $remainingMonthlyTransferAmount рублей")
    } else {
        println("Тип карты $type не поддерживается")
    }
}

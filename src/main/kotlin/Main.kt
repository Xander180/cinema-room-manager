package cinema
var totalIncome = 0
var currentIncome = 0
var ticketCount = 0

fun mainMenu(theater: List<MutableList<Char>>, rows: Int, seats: Int) {
    println(
        """
        1. Show the seats
        2. Buy a ticket
        3. Statistics
        0. Exit
    """.trimIndent()
    )
    println()
    do {
        when (readln().toInt()) {
            1 -> printRoom(theater, rows, seats)
            2 -> buyTickets(theater, rows, seats)
            3 -> statistics(theater, rows, seats)
            0 -> break
            else -> {
                println("Invalid input!\n")
                mainMenu(theater, rows, seats)
            }
        }
    } while (readln().toInt() != 0)
}

fun printRoom(theater: List<MutableList<Char>>, rows: Int, seats: Int) {
    println("Cinema:")
    for (i in 0..seats) {
        // Creates whitespace at 0 (top left corner)
        if (i == 0) {
            print("  ")
        } else {
            print(i)
            print(' ')
        }
    }
    println()
    for (i in 0 until rows) {
        print(i + 1).toString()
        print(' ')
        // Prints the seats ('S') by iterating on each list created in 'theater', which will display them in a grid
        println(theater[i].joinToString(" "))
    }
    println()
    mainMenu(theater, rows, seats)
}

fun buyTickets(theater: List<MutableList<Char>>, rows: Int, seats: Int) {
    var rowNum: Int
    var seatNum: Int
    // Continues asking for row/seat if user input is invalid
    while (true) {
        println("Enter a row number:")
        rowNum = readln().toInt()
        if (rowNum > rows) {
            println("Wrong input!\n")
            continue
        }
        println("Enter a seat number in that row:")
        seatNum = readln().toInt()
        if (seatNum > rows * seats) {
            println("Wrong input!\n")
            continue
        } else if (theater[rowNum -1][seatNum - 1] == 'B') {
            println("That ticket has already been purchased!\n")
            continue
        } else {
            theater[rowNum - 1][seatNum - 1] = 'B'
            println()

            val half1 = rows / 2
            var ticketPrice = 10
            if (rows * seats <= 60) {
                println("Ticket price: $$ticketPrice")
                currentIncome += 10
                ticketCount += 1
            } else if (rows * seats > 60) {
                if (rowNum <= half1) {
                    println("Ticket price: $$ticketPrice")
                    currentIncome += 10
                    ticketCount += 1
                } else {
                    ticketPrice = 8
                    println("Ticket price: $$ticketPrice")
                    currentIncome += 8
                    ticketCount += 1
                }
            }
            println()
            mainMenu(theater, rows, seats)
        }
    }
}
fun statistics(theater: List<MutableList<Char>>, rows: Int, seats:Int) {
    println()
    val totalTickets = rows * seats
    val half1 = rows / 2
    val half2 = rows - half1
    totalIncome = if (totalTickets < 60) {
        totalTickets * 10

    }else {

        (half1 * seats * 10) + (half2 * seats * 8)
    }
    val percentage: Double = ((ticketCount.toDouble() * 100.00)/totalTickets.toDouble())
    val formatPercentage = "%.2f".format(percentage)
    println("Number of purchased tickets: $ticketCount")
    println("Percentage: $formatPercentage%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome \n")

    mainMenu(theater, rows, seats)

}
fun main() {
    println("Enter the number of rows:")
    val rows: Int = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats: Int = readln().toInt()
    val theater = List(rows) { MutableList(seats) { 'S' } }
    println()
    mainMenu(theater, rows, seats)
}
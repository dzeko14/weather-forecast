package my.dzeko.weatherforecast.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.isSameDay(other: Date) :Boolean{
    val cal1 = Calendar.getInstance()
    val cal2 = Calendar.getInstance()
    cal1.time = this
    cal2.time = other

    return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
            && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
}

fun Date.getDayAndMonthString() :String{
    val formatter = SimpleDateFormat("MMM", Locale.getDefault())
    val month = formatter.format(this)

    val calendar = Calendar.getInstance()
    calendar.time = this
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    return "$day $month"
}

fun Date.getTimeString() :String{
    val formatter = SimpleDateFormat("HH:MM", Locale.getDefault())
    return formatter.format(this)
}
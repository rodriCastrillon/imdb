package com.imdb.core.extensionFunctions

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.time.Duration.Companion.milliseconds

@SuppressLint("SimpleDateFormat")
fun String.toYYYY(): String =
    try {
        SimpleDateFormat("yyyy")
            .format(
                checkNotNull(
                    SimpleDateFormat("yyyy-MM-dd")
                        .parse(this)
                )
            )
    } catch (e: ParseException) {
        ""
    }
fun timeStampExample(timeSession:Calendar): Long {

    val diffInMillis = Calendar.getInstance().timeInMillis - timeSession.timeInMillis
    return diffInMillis.milliseconds.inWholeMinutes
}

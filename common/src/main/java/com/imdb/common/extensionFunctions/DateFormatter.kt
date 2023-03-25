package com.imdb.common.extensionFunctions

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat

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
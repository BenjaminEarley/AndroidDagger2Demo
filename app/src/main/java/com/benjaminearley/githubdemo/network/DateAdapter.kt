package com.benjaminearley.githubdemo.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

class DateAdapter {

    companion object {
        const val format = "yyyy'-'MM'-'dd HH':'mm':'ss'Z'"
    }

    @ToJson fun toJson(date: Date): String {
        try {
            return SimpleDateFormat(format, Locale.getDefault()).format(date)
        } catch (e: Exception) {
            throw JsonDataException("Unknown Date: " + e)
        }
    }

    @FromJson fun fromJson(date: String): Date {
        try {
            return SimpleDateFormat(format, Locale.getDefault()).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }.parse(date)
        } catch (e: Exception) {
            throw JsonDataException("Unknown DateString: " + e)
        }
    }
}

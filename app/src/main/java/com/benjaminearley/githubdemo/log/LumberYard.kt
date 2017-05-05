package com.benjaminearley.githubdemo.log

import android.util.Log
import timber.log.Timber

class ProductionTree(private val mLogPriority: Int = Log.ERROR) : Timber.Tree() {

    override fun isLoggable(tag: String, priority: Int): Boolean {
        return priority >= mLogPriority
    }

    override fun log(priority: Int, tag: String, message: String, t: Throwable?) {

        if (priority >= mLogPriority) {
            return
        }

        // When Fabric or firebase is added
//        if (t != null) {
//            Crashlytics.logException(t)
//        } else {
//            val formattedMessage = format(priority, tag, message)
//            Crashlytics.logException(Throwable(formattedMessage))
//        }
    }
}

private fun format(priority: Int, tag: String?, message: String): String {

    fun prefixForPriority(priority: Int): String {
        when (priority) {
            Log.VERBOSE -> return "[VERBOSE] "
            Log.DEBUG -> return "[DEBUG] "
            Log.INFO -> return "[INFO] "
            Log.WARN -> return "[WARN] "
            Log.ERROR -> return "[ERROR] "
            Log.ASSERT -> return "[ASSERT] "
            else -> return "[UNKNOWN($priority)] "
        }
    }

    val messageWithTag = if (tag != null) "[$tag] $message" else message
    return prefixForPriority(priority) + messageWithTag
}
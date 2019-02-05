package com.example.rssfeeder.util

/**
 * Interface define to capture users click events and perform specific task.
 */
interface ActionListener {
    fun onAction(action: String, data: Any?)
}
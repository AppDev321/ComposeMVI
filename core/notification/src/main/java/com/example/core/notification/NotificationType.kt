package com.example.core.notification

enum class NotificationType(val value: String) {
    TOKEN("token"),
    SENT("sent"),
    RECEIVED("received"),
    CUSTOM("custom"),
    UNKNOWN("unknown");

    fun isTransaction(): Boolean {
        return this == SENT || this == RECEIVED
    }

    companion object {
        fun getByValue(value: String?): NotificationType {
            return entries.firstOrNull { it.value == value } ?: UNKNOWN
        }
    }
}
package com.sharedcalendar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SharedCalendarApplication

fun main(args: Array<String>) {
    runApplication<SharedCalendarApplication>(*args)
}

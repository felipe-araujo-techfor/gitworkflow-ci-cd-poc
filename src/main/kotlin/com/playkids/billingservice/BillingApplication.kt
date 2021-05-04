package com.playkids.billingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.playkids.billingservice"])
open class BillingApplication

fun main(args: Array<String>) {
    runApplication<BillingApplication>(*args)
}

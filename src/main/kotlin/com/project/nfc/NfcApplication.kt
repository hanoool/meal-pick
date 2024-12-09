package com.project.nfc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NfcApplication

fun main(args: Array<String>) {
    runApplication<NfcApplication>(*args)
}

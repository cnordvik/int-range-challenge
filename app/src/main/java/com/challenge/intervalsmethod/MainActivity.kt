package com.challenge.intervalsmethod

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var utilRanges = UtilRanges()

    private var inclusiveRange: MutableSet<IntRange> = mutableSetOf(5..13, 2..8) // define any range here
    private var exclusiveRange: MutableSet<IntRange> = mutableSetOf(4..10, 5..11) // define any range here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val validRanges = utilRanges.getValidRanges(inclusiveRange, exclusiveRange)

        validRanges.forEach { println("${it.first}-${it.last}") }

    }
}
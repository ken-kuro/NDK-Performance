package com.example.anative

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.anative.databinding.ActivityMainBinding
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val introTextField = binding.intro
        val actionButton = binding.action
        val inputTxtField = binding.input
        val outputTxtField = binding.output

        introTextField.text = introFromJni()
        actionButton.setOnClickListener {
            val input = Integer.parseInt(inputTxtField.text.toString())
            var start: Long = 0
            var stop: Long = 0
            var output: String = ""

            // Dalvik - Recursive
            start = System.nanoTime()
            var result: Int = fibJ(input)
            stop = System.nanoTime()
            output += String.format(
                "Dalvik recursive: %d (%d nsec)",
                result,
                stop - start
            )
            // Dalvik - Iterative
            start = System.nanoTime()
            result = fibJI(input)
            stop = System.nanoTime()
            output += String.format(
                "\nDalvik iterative: %d (%d nsec)",
                result,
                stop - start
            )

            // Native - Recursive
            start = System.nanoTime()
            result = fibN(input)
            stop = System.nanoTime()
            output += String.format(
                "\nNative recursive: %d (%d nsec)",
                result,
                stop - start
            )
            // Native - Iterative
            start = System.nanoTime()
            result = fibNI(input)
            stop = System.nanoTime()
            output += String.format(
                "\nNative iterative: %d (%d nsec)",
                result,
                stop - start
            )

            // Loop Dalvik
            start = System.nanoTime()
            result = loopJ(input * 10000)
            stop = System.nanoTime()
            output += String.format(
                "\nJ: %d (%d nsec)",
                result,
                stop - start
            )

            // Loop Native
            start = System.nanoTime()
            result = loopN(input * 10000)
            stop = System.nanoTime()
            output += String.format(
                "\nN: %d (%d nsec)",
                result,
                stop - start
            )

            // Prime Dalvik
            start = System.nanoTime()
            var resultBool: Boolean = isPrimeJ(input.toLong())
            stop = System.nanoTime()
            output += String.format(
                "\nJ: %b (%d nsec)",
                resultBool,
                stop - start
            )

            // Prime Native
            start = System.nanoTime()
            resultBool = isPrimeN(input.toLong())
            stop = System.nanoTime()
            output += String.format(
                "\nN: %b (%d nsec)",
                resultBool,
                stop - start
            )

            outputTxtField.text = output
        }
    }

    private fun fibJ(n: Int): Int {
        if (n <= 0)
            return 0
        if (n == 1)
            return 1
        return fibJ(n - 1) + fibJ(n - 2)
    }

    private fun fibJI(n: Int): Int {
        var previous: Int = -1
        var result: Int = 1
        for (i in 0..n) {
            val sum: Int = result + previous
            previous = result
            result = sum
        }
        return result
    }

    private fun loopJ(count: Int): Int {
        var result = 0
        for (i in 0 until count) {
            for (j in 0..99) {
                result += 34432
                result++
                result -= 34431
                result--
            }
        }
        return result
    }

    private fun isPrimeJ(a: Long): Boolean {
        if (a == 2L) {
            return true
        } else if (a <= 1 || a % 2 == 0L) {
            return false
        }
        val max = sqrt(a.toDouble()).toLong()
        var n: Long = 3
        while (n <= max) {
            if (a % n == 0L) {
                return false
            }
            n += 2
        }
        return true
    }

    private external fun introFromJni(): String

    private external fun fibN(n: Int): Int

    private external fun fibNI(n: Int): Int

    private external fun loopN(n: Int): Int

    private external fun isPrimeN(a: Long): Boolean

    companion object {
        // Used to load the 'anative' library on application startup.
        init {
            System.loadLibrary("anative")
        }
    }
}
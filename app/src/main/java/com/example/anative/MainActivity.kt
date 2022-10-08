package com.example.anative

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.anative.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

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
            start = System.currentTimeMillis()
            var result: Int = fibJ(input)
            stop = System.currentTimeMillis()
            output += String.format(
                "Dalvik recursive: %d (%d msec)",
                result,
                stop - start
            )

            // Dalvik - Iterative
            start = System.currentTimeMillis()
            result = fibJI(input)
            stop = System.currentTimeMillis()
            output += String.format(
                "\nDalvik iterative: %d (%d msec)",
                result,
                stop - start
            )

            // Native - Recursive
            start = System.currentTimeMillis()
            result = fibN(input)
            stop = System.currentTimeMillis()
            output += String.format(
                "\nNative recursive: %d (%d msec)",
                result,
                stop - start
            )

            // Native - Iterative
            start = System.currentTimeMillis()
            result = fibNI(input)
            stop = System.currentTimeMillis()
            output += String.format(
                "\nNative iterative: %d (%d msec)",
                result,
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
        var previous = -1
        var result = 1
        for (i in 0..n) {
            val sum = result + previous
            previous = result
            result = sum
        }
        return result
    }

    private external fun introFromJni(): String

    private external fun fibN(n: Int): Int

    external fun fibNI(n: Int): Int

    companion object {
        // Used to load the 'anative' library on application startup.
        init {
            System.loadLibrary("anative")
        }
    }
}
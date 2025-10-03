package com.example.colormyviews

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setListeners()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setListeners() {
            val box1 = findViewById<View>(R.id.box_one_text)
            val box2 = findViewById<View>(R.id.box_two_text)
            val box3 = findViewById<View>(R.id.box_three_text)
            val box4 = findViewById<View>(R.id.box_four_text)
            val box5 = findViewById<View>(R.id.box_five_text)
            val red = findViewById<View>(R.id.red_button)
            val yellow = findViewById<View>(R.id.yellow_button)
            val green = findViewById<View>(R.id.green_button)

            listOf(box1, box2, box3, box4, box5, red, yellow, green).forEach { v ->
                v.setOnClickListener { makeColored(v) }
            }
    }

    fun makeColored(view: View) {
        when (view.id) {

            // Boxes using Color class colors for background
            R.id.box_one_text -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two_text -> view.setBackgroundColor(Color.GRAY)

            // Boxes using Android color resources for background
            R.id.box_three_text -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.box_four_text -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.box_five_text -> view.setBackgroundResource(android.R.color.holo_green_light)

            // Boxes using custom colors for background
            R.id.red_button    -> findViewById<View>(R.id.box_three_text)
                .setBackgroundResource(R.color.my_red)
            R.id.yellow_button -> findViewById<View>(R.id.box_four_text)
                .setBackgroundResource(R.color.my_yellow)
            R.id.green_button  -> findViewById<View>(R.id.box_five_text)
                .setBackgroundResource(R.color.my_green)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}
package com.lonelyy.viewpager2example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lonelyy.viewpager2example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSimple.setOnClickListener {
                val intent = Intent(this@MainActivity, SimplePagerActivity::class.java)
                startActivity(intent)
            }

            btnStateful.setOnClickListener {
                val intent = Intent(this@MainActivity, StatefulActivity::class.java)
                startActivity(intent)
            }

            btnSimple2.setOnClickListener {
                val intent = Intent(this@MainActivity, SimplePagerActivity2::class.java)
                startActivity(intent)
            }

            btnStateful2.setOnClickListener {
                val intent = Intent(this@MainActivity, StatefulActivity2::class.java)
                startActivity(intent)
            }
        }
    }
}
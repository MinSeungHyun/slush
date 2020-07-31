package com.example.slush

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.slush.example.BasicExampleActivity
import com.example.slush.example.LiveDataExampleActivity
import com.example.slush.example.ObservableListExampleActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        basicButton.setOnClickListener {
            startActivity(Intent(this, BasicExampleActivity::class.java))
        }

        liveDataButton.setOnClickListener {
            startActivity(Intent(this, LiveDataExampleActivity::class.java))
        }

        observableListButton.setOnClickListener {
            startActivity(Intent(this, ObservableListExampleActivity::class.java))
        }
    }
}

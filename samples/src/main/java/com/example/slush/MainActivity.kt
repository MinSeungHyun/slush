package com.example.slush

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_book.view.*
import slush.Slush

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf(
            Book("Book 1"),
            Book("Book 2"),
            Book("Book 3")
        )

        Slush.SingleTypeBuilder<Book>()
            .setItemLayout(R.layout.item_book)
            .setItems(items)
            .setLayoutManager(LinearLayoutManager(this))
            .setOnBindListener { book ->
                bookName.text = book.name
            }
            .into(recyclerView)
    }
}

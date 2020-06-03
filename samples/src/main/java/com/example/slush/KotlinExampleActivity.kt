package com.example.slush

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_book.view.*
import slush.Slush

private const val TAG = "SlushTest"

val items = listOf(
    Book("Book 0"),
    Book("Book 1"),
    Book("Book 2"),
    Book("Book 3"),
    Book("Book 4"),
    Book("Book 5")
)

class KotlinExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listEditor = Slush.SingleTypeAdapter<Book>()
            .setItemLayout(R.layout.item_book)
            .setItems(items)
            .setLayoutManager(LinearLayoutManager(this))
            .onBind { view, book ->
                view.bookName.text = book.name
            }
            .onItemClick { clickedView, position ->
                Log.d(TAG, "Clicked: $position")
            }
            .into(recyclerView)
            .itemListEditor

        listEditor.addItem(Book("New Book"))
    }
}

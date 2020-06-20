package com.example.slush

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slush.databinding.ItemDataBinding
import kotlinx.android.synthetic.main.activity_main.*
import slush.Slush
import slush.utils.BasicDiffCallback

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

        val listEditor = Slush.SingleType<Book>()
            .setItemLayout(R.layout.item_data)
            .setItems(items)
            .setLayoutManager(LinearLayoutManager(this))
            .onBindData<ItemDataBinding> { binding, book ->
                binding.book = book
            }
            .onItemClick { clickedView, position ->
                Log.d(TAG, "Clicked: $position")
            }
            .setDiffCallback(BasicDiffCallback())
            .into(recyclerView)
            .itemListEditor

        var count = 0
        testButton.setOnClickListener {
            listEditor.addItem(3, Book("New Book ${count++}"))
        }
    }
}

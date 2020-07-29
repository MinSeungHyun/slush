package com.example.slush

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slush.databinding.SimpleItemBinding
import kotlinx.android.synthetic.main.activity_main.*
import slush.Slush
import slush.utils.BasicDiffCallback

private const val TAG = "SlushTest"

val items = listOf(
    SimpleItem("Sample Item", "item 0"),
    SimpleItem("Sample Item", "item 1"),
    SimpleItem("Sample Item", "item 2")
)

class KotlinExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listEditor = Slush.SingleType<SimpleItem>()
            .setItemLayout(R.layout.simple_item)
            .setItems(items)
            .setLayoutManager(LinearLayoutManager(this))
            .onBindData<SimpleItemBinding> { binding, item ->
                binding.item = item
            }
            .onItemClick { clickedView, position ->
                Log.d(TAG, "Clicked: $position")
            }
            .setDiffCallback(BasicDiffCallback())
            .into(recyclerView)
            .itemListEditor

        var count = items.size
        testButton.setOnClickListener {
            listEditor.addItem(SimpleItem("New Item", "item ${count++}"))
        }
    }
}

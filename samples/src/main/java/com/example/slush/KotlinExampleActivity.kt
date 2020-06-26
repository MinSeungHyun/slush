package com.example.slush

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slush.databinding.SimpleBindingItemBinding
import kotlinx.android.synthetic.main.activity_main.*
import slush.Slush
import slush.utils.BasicDiffCallback

private const val TAG = "SlushTest"

val items = listOf(
    SimpleItem("Item 0"),
    SimpleItem("Item 1"),
    SimpleItem("Item 2"),
    SimpleItem("Item 3"),
    SimpleItem("Item 4"),
    SimpleItem("Item 5")
)

class KotlinExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listEditor = Slush.SingleType<SimpleItem>()
            .setItemLayout(R.layout.simple_binding_item)
            .setItems(items)
            .setLayoutManager(LinearLayoutManager(this))
            .onBindData<SimpleBindingItemBinding> { binding, item ->
                binding.item = item
            }
            .onItemClick { clickedView, position ->
                Log.d(TAG, "Clicked: $position")
            }
            .setDiffCallback(BasicDiffCallback())
            .into(recyclerView)
            .itemListEditor

        var count = 0
        testButton.setOnClickListener {
            listEditor.addItem(SimpleItem("New Item ${count++}"))
        }
    }
}

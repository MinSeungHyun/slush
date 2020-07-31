package com.example.slush.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slush.R
import com.example.slush.SimpleItem
import com.example.slush.databinding.SimpleItemBinding
import kotlinx.android.synthetic.main.activity_recyclerview.*
import slush.Slush

class BasicExampleActivity : AppCompatActivity() {
    private val initialItems = listOf(
        SimpleItem("Simple Item", "item 0"),
        SimpleItem("Simple Item", "item 1"),
        SimpleItem("Simple Item", "item 2")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        val result = Slush.SingleType<SimpleItem>()
            .setItemLayout(R.layout.simple_item)
            .setLayoutManager(LinearLayoutManager(this))
            .onBindData<SimpleItemBinding> { binding, simpleItem ->
                binding.item = simpleItem
            }
//          If you are not using DataBinding,
//          .onBind { view, simpleItem ->
//              view.itemName.text = simpleItem.name
//              view.itemDescription.text = simpleItem.description
//          }
            .setItems(initialItems) // Optional
            .into(recyclerView)

        val itemListEditor = result.itemListEditor

        var count = 0
        testButton.setOnClickListener {
            itemListEditor.addItem(SimpleItem("New Item", "new item ${count++}"))
        }
    }
}

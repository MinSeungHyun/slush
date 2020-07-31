package com.example.slush.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slush.R
import com.example.slush.SimpleItem
import com.example.slush.databinding.SimpleItemBinding
import kotlinx.android.synthetic.main.activity_recyclerview.*
import slush.Slush

class ObservableListExampleActivity : AppCompatActivity() {
    private val initialItems = listOf(
        SimpleItem("Simple Item", "item 0"),
        SimpleItem("Simple Item", "item 1"),
        SimpleItem("Simple Item", "item 2")
    )
    private val observableItems = ObservableArrayList<SimpleItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        Slush.SingleType<SimpleItem>()
            .setItemLayout(R.layout.simple_item)
            .setLayoutManager(LinearLayoutManager(this))
            .onBindData<SimpleItemBinding> { binding, simpleItem ->
                binding.item = simpleItem
            }
            .setItems(observableItems)
            .into(recyclerView)

        observableItems.addAll(initialItems)

        var count = 0
        testButton.setOnClickListener {
            observableItems.add(SimpleItem("New Item", "new item ${count++}"))
        }
    }
}

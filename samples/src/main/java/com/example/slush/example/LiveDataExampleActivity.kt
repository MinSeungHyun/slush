package com.example.slush.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slush.R
import com.example.slush.SimpleItem
import com.example.slush.databinding.SimpleItemBinding
import kotlinx.android.synthetic.main.activity_recyclerview.*
import slush.Slush

class LiveDataExampleActivity : AppCompatActivity() {
    private val initialItems = listOf(
        SimpleItem("Simple Item", "item 0"),
        SimpleItem("Simple Item", "item 1"),
        SimpleItem("Simple Item", "item 2")
    )
    private val itemsLiveData = MutableLiveData<List<SimpleItem>>(initialItems)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        Slush.SingleType<SimpleItem>()
            .setItemLayout(R.layout.simple_item)
            .setLayoutManager(LinearLayoutManager(this))
            .onBindData<SimpleItemBinding> { binding, simpleItem ->
                binding.item = simpleItem
            }
            .setItems(itemsLiveData, this)
            .into(recyclerView)

        var count = 0
        testButton.setOnClickListener {
            val mutableList = itemsLiveData.value?.toMutableList() ?: mutableListOf()
            mutableList.add(SimpleItem("New Item", "new item ${count++}"))
            itemsLiveData.value = mutableList
        }
    }
}

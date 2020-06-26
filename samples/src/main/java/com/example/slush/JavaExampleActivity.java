package com.example.slush;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slush.databinding.SimpleBindingItemBinding;

import java.util.ArrayList;
import java.util.List;

import slush.ItemListEditor;
import slush.Slush;
import slush.utils.BasicDiffCallback;

public class JavaExampleActivity extends AppCompatActivity {
    private static final String TAG = "SlushTest";
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<SimpleItem> items = new ArrayList<>();
        items.add(new SimpleItem("Item 0"));
        items.add(new SimpleItem("Item 1"));
        items.add(new SimpleItem("Item 2"));
        items.add(new SimpleItem("Item 3"));
        items.add(new SimpleItem("Item 4"));
        items.add(new SimpleItem("Item 5"));

        ItemListEditor<SimpleItem> listEditor = new Slush.SingleType<SimpleItem>()
                .setItemLayout(R.layout.simple_binding_item)
                .setItems(items)
                .setLayoutManager(new LinearLayoutManager(this))
                .onBindData((dataBinding, item) -> {
                    SimpleBindingItemBinding binding = (SimpleBindingItemBinding) dataBinding;
                    binding.setItem(item);
                })
                .onItemClick((clickedView, position) -> {
                    Log.d(TAG, "Clicked: " + position);
                })
                .setDiffCallback(new BasicDiffCallback<>())
                .into(recyclerView)
                .getItemListEditor();

        findViewById(R.id.testButton).setOnClickListener(v ->
                listEditor.addItem(new SimpleItem("New Item " + count++))
        );
    }
}

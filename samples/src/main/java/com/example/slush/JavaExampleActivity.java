package com.example.slush;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slush.databinding.ItemDataBinding;

import java.util.ArrayList;
import java.util.List;

import slush.ItemListEditor;
import slush.Slush;

public class JavaExampleActivity extends AppCompatActivity {
    private static final String TAG = "Slush test";
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<Book> items = new ArrayList<>();
        items.add(new Book("Book 0"));
        items.add(new Book("Book 1"));
        items.add(new Book("Book 2"));
        items.add(new Book("Book 3"));
        items.add(new Book("Book 4"));
        items.add(new Book("Book 5"));

        ItemListEditor<Book> listEditor = new Slush.SingleType<Book>()
                .setItemLayout(R.layout.item_data)
                .setItems(items)
                .setLayoutManager(new LinearLayoutManager(this))
                .onBindData((dataBinding, book) -> {
                    ItemDataBinding binding = (ItemDataBinding) dataBinding;
                    binding.setBook(book);
                })
                .onItemClick((clickedView, position) -> {
                    Log.d(TAG, "Clicked: " + position);
                })
                .into(recyclerView)
                .getItemListEditor();

        findViewById(R.id.testButton).setOnClickListener(v ->
                listEditor.addItemAt(3, new Book("New Book " + count++))
        );
    }
}

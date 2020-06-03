package com.example.slush;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import slush.ItemListEditor;
import slush.Slush;

public class JavaExampleActivity extends AppCompatActivity {
    private static final String TAG = "Slush test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<Book> items = new ArrayList<>();
        items.add(new Book("Book 0"));
        items.add(new Book("Book 1"));
        items.add(new Book("Book 2"));

        ItemListEditor<Book> listEditor = new Slush.SingleTypeAdapter<Book>()
                .setItemLayout(R.layout.item_book)
                .setItems(items)
                .setLayoutManager(new LinearLayoutManager(this))
                .onBind((view, book) -> {
                    TextView bookName = view.findViewById(R.id.bookName);
                    bookName.setText(book.getName());
                })
                .onItemClick((clickedView, position) -> {
                    Log.d(TAG, "Clicked: " + position);
                })
                .into(recyclerView)
                .getItemListEditor();

        listEditor.addItem(new Book("New Book"));
    }
}

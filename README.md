# Slush

A simple and easy adapter for RecyclerView

***No more boilerplate adapters and view holders.***

Slush will make using RecyclerView easy and fast.

## Preview

This library is still under development.

```kotlin
Slush.SingleTypeAdapter<Book>()
    .setItemLayout(R.layout.item_book) // Required
    .setItems(items) // Required
    .setLayoutManager(LinearLayoutManager(this)) // Optional (if layout manager is already set)
    .setOnBindListener { book -> // Optional
        bookName.text = book.name // (bookName is an id of TextView in R.layout.item_book)
    }
    .setOnItemClickListener { view, position -> // Optional (view: clicked item view, position: clicked item's position)
        Log.d(TAG, "Clicked: $position")
    }
    .into(recyclerView) // Required (initialize RecyclerView)
```

## Example

### Kotlin

```kotlin
class KotlinExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf(
            Book("Book 1"),
            Book("Book 2"),
            Book("Book 3")
        )

        Slush.SingleTypeAdapter<Book>()
            .setItemLayout(R.layout.item_book)
            .setItems(items)
            .setLayoutManager(LinearLayoutManager(this))
            .setOnBindListener { book ->
                bookName.text = book.name
            }
            .setOnItemClickListener { view, position ->
                Log.d(TAG, "Clicked: $position")
            }
            .into(recyclerView)
    }
}
```

### Java

```java
public class JavaExampleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<Book> items = new ArrayList<>();
        items.add(new Book("Book 1"));
        items.add(new Book("Book 2"));
        items.add(new Book("Book 3"));

        new Slush.SingleTypeAdapter<Book>()
                .setItemLayout(R.layout.item_book)
                .setItems(items)
                .setLayoutManager(new LinearLayoutManager(this))
                .setOnBindListener((view, book) -> {
                    TextView bookName = view.findViewById(R.id.bookName);
                    bookName.setText(book.getName());
                })
                .setOnItemClickListener((view, position) -> {
                    Log.d(TAG, "Clicked: " + position);
                })
                .into(recyclerView);
    }
}
```


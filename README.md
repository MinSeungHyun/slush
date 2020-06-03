# Slush
[![](https://jitpack.io/v/MinSeungHyun/slush.svg)](https://jitpack.io/#MinSeungHyun/slush)

A simple and easy adapter for RecyclerView

***No more boilerplate adapters and view holders.***  
Slush will make using RecyclerView easy and fast.

## Setup
```groovy
// build.gradle (Project root)
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

```groovy
// build.gradle (app)
dependencies {
    implementation 'com.github.minseunghyun:slush:{version}'
}
```

## Usage

This library is still under development.

```kotlin
val listEditor = Slush.SingleTypeAdapter<Book>()
    .setItemLayout(R.layout.item_book)
    .setItems(items)
    .setLayoutManager(LinearLayoutManager(this))
    .onBind { view, book ->
        view.bookName.text = book.name
    }
    .onItemClick { clickedView, position ->
        Log.d(TAG, "Clicked: $position")
    }
    .into(recyclerView)
    .itemListEditor

listEditor.addItem(Book("New Book"))
```

## Document
The full document is at [Wiki](https://github.com/MinSeungHyun/slush/wiki)

## License
```
MIT License

Copyright (c) 2020 SeungHyun

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

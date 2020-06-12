# Slush
[![Download](https://api.bintray.com/packages/minseunghyun/maven/slush/images/download.svg)](https://bintray.com/minseunghyun/maven/slush/_latestVersion)
![Android CI](https://github.com/MinSeungHyun/slush/workflows/Android%20CI/badge.svg)

A simple and easy adapter for RecyclerView

***No more boilerplate adapters and view holders.***  
Slush will make using RecyclerView easy and fast.  
The goal of this project is to make RecyclerView, which is not very complicated, possible with Slush.

## Setup
At your build.gradle(app)  
```groovy
dependencies {
    implementation 'in.seunghyun:slush:version'
    implementation 'androidx.recyclerview:recyclerview:1.1.0' //RecyclerView
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
The full document is at [Wiki](https://github.com/MinSeungHyun/slush/wiki).  
You can also see the sample codes [here](https://github.com/MinSeungHyun/slush/tree/master/samples/src/main/java/com/example/slush).

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

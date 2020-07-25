<img src="./images/logo.png" width="360"><br>

[한국어](./README_ko.md)

[![Download](https://api.bintray.com/packages/minseunghyun/maven/slush/images/download.svg)](https://bintray.com/minseunghyun/maven/slush/_latestVersion)
![Android CI](https://github.com/MinSeungHyun/slush/workflows/Android%20CI/badge.svg)

**_No more boilerplate adapters and view holders._**  
Slush will make using RecyclerView easy and fast.

_The goal of this project is to make RecyclerView, which is not very complicated, possible with Slush._

## Setup

Add a dependency below at your build.gradle(app)

```groovy
dependencies {
    implementation 'in.seunghyun:slush:1.1.1'
}
```

## Basic Usage

You can use all the features with a single class called `Slush`.

Basically, you have to write some codes below.
(Here, Replace the `SimpleItem` part into your item class to use.)

```kotlin
Slush.SingleType<SimpleItem>()
    .setItemLayout(R.layout.simple_binding_item) // Set item layout
    .setLayoutManager(LinearLayoutManager(this)) // Set LayoutManager (No need to write if already exists)
    .onBind { view, item ->
        view.itemName.text = item.name
    }
    .into(recyclerView)
```

Initialize views in item layout in `onBind`. (Similar to `onBindViewHolder` of RecyclerView Adapter)

### ItemListEditor

ItemListEditor is an interface that allows you to edit the item list.

```kotlin
val result = Slush.SingleType<SimpleItem>()
    // Omit intermediate code
    .into(recyclerView)
val itemListEditor = result.itemListEditor
```

The available methods are listed [here](https://github.com/MinSeungHyun/slush/wiki/ItemListEditor).

## DataBinding

Slush supports data binding.

You should change `onBind` part to `onBindData` to use data binding.

```kotlin
.onBindData<SimpleItemBinding> { binding, item ->
    binding.item = item
}
```

And put your [generated binding class](https://developer.android.com/topic/libraries/data-binding/generated-binding) into `SimpleItemBinding` part.

## Options

### setItems

```kotlin
.setItems(items)
```

Set an initial item list.

### onItemClick

```kotlin
.onItemClick { clickedView, position ->
    Log.d(TAG, "Clicked: $position")
}
```

Set a listener to be executed when item is clicked.

### setDiffCallback

```kotlin
.setDiffCallback(BasicDiffCallback())
```

Set a [DiffCallback](https://developer.android.com/reference/androidx/leanback/widget/DiffCallback) to be used when you call `changeAll` in [ItemListEditor](#ItemListEditor)  
By default, calling `changeAll` replaces all data in the list, but if you set DiffCallback, only the modified part is changed, so animation occurs.  
You can also use custom DiffCallback by inheriting `SlushDiffCallback`.

## Samples

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

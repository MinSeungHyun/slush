# Slush

[![Download](https://api.bintray.com/packages/minseunghyun/maven/slush/images/download.svg)](https://bintray.com/minseunghyun/maven/slush/_latestVersion)
![Android CI](https://github.com/MinSeungHyun/slush/workflows/Android%20CI/badge.svg)

**_더이상 반복적인 Adapter와 ViewHolder 코드를 작성하지 마세요._**  
슬러시가 RecyclerView를 쉽고 빠르게 사용할 수 있도록 도와줍니다.

_이 프로젝트의 목표는 엄청 복잡하지 않은 RecyclerView는 슬러시로 만들 수 있게 하는 것입니다._

## 설정

build.gradle(app)에 아래 dependency를 추가해주세요.

```groovy
dependencies {
    implementation 'in.seunghyun:slush:1.1.0'
}
```

## 사용

```kotlin
val listEditor = Slush.SingleType<SimpleItem>()
    .setItemLayout(R.layout.simple_binding_item)
    .setItems(items)
    .setLayoutManager(LinearLayoutManager(this))
    .onBindData<SimpleBindingItemBinding> { binding, item ->
        binding.item = item
    }
    .onItemClick { clickedView, position ->
        Log.d(TAG, "Clicked: $position")
    }
    .setDiffCallback(BasicDiffCallback())
    .into(recyclerView)
    .itemListEditor

listEditor.addItem(SimpleItem("New Item"))
```

## 문서

모든 문서는 [위키](https://github.com/MinSeungHyun/slush/wiki)에 있습니다.  
[여기](https://github.com/MinSeungHyun/slush/tree/master/samples/src/main/java/com/example/slush)에서 샘플 코드를 볼 수 있습니다.

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
